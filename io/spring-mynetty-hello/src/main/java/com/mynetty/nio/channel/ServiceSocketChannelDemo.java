package com.mynetty.nio.channel;
 
import com.mynetty.nio.buffer.Buffers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
 
 
/*服务器端，:接收客户端发送过来的数据并显示，
 *服务器把上接收到的数据加上"吗?"再发送回去*/
public class ServiceSocketChannelDemo {

    private static class TCPServer implements Runnable{

        private InetSocketAddress localAddress;

        private ServerSocketChannel ssc = null;
        private Selector selector = null;

        private Random rnd = new Random();
        private Charset utf8 = Charset.forName("UTF-8");

        public TCPServer (int port){
            init(port);
        }

        private void init(int port){
            try {
                localAddress = new InetSocketAddress(port);
                // 创建选择器
                selector = Selector.open();

                // 创建服务器通道
                ssc = ServerSocketChannel.open();
                ssc.configureBlocking(false);

                ssc.bind(localAddress, 100);

                // 服务器通道处理tcp连接
                ssc.register(selector,SelectionKey.OP_ACCEPT);
            } catch (IOException e){
                e.printStackTrace();
            }

            System.out.println("server start with address ： "+localAddress);

        }


        private void doAcceptable(ServerSocketChannel ssc) throws IOException{
            // accept 返回一个普通的通道，每个通道的内核中对应一个socket缓冲区
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);

            // 向选择器注册这个通道和普通通道感兴趣的事件，同时提供这个新通道的缓冲区
            int interestSet = SelectionKey.OP_READ;
            sc.register(selector,interestSet,new Buffers(256,256));

            System.out.println("accept from "+sc.getRemoteAddress());
        }

        private void doReadable(SelectionKey key) throws IOException{
            // 获取对应的缓冲区
            Buffers buffers = (Buffers)key.attachment();
            ByteBuffer readBuffer = buffers.getReadBuffer();
            ByteBuffer writeBuffer = buffers.getWriteBuffer();

            // 获取对应的通道
            SocketChannel sc = (SocketChannel)key.channel();

            // 从底层socket读缓冲去中读入数据
            sc.read(readBuffer);
            readBuffer.flip();

            CharBuffer cb = utf8.decode(readBuffer);
            System.out.println(cb.array());
            readBuffer.rewind();

            writeBuffer.put(readBuffer);
            writeBuffer.put("吗?".getBytes("UTF-8"));

            readBuffer.clear();

            key.interestOps(key.interestOps()|SelectionKey.OP_WRITE);
        }

        private void doWritable(SelectionKey key) throws IOException{
            Buffers buffers = (Buffers)key.attachment();

            ByteBuffer writeBuffer = buffers.getWriteBuffer();
            writeBuffer.flip();

            SocketChannel sc = (SocketChannel)key.channel();

            int len = 0;

            while (writeBuffer.hasRemaining()){
                len = sc.write(writeBuffer);

                if (0 == len){
                    break;
                }
            }

            writeBuffer.compact();

            if (len != 0){
                key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
            }
        }


        private void doWork(ServerSocketChannel ssc,SelectionKey key) throws IOException{
            // 连接
            if (key.isAcceptable()){
                doAcceptable(ssc);
            }

            // 读事件
            if (key.isReadable()){
                doReadable(key);
            }
            // 写事件
            if (key.isWritable()){
                doWritable(key);
            }
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()){
                    int n = selector.select();
                    if( n == 0 ){
                        continue;
                    }
                    Set<SelectionKey> keySet = selector.selectedKeys();
                    Iterator<SelectionKey> it = keySet.iterator();
                    SelectionKey key = null;
                    while (it.hasNext()){
                        key = it.next();
                        it.remove();
                        doWork(ssc,key);
                    }
                    Thread.sleep(rnd.nextInt(500));
                }
            }catch(InterruptedException e){
                System.out.println("serverThread is interrupted");
            }catch (IOException e){
                e.printStackTrace();
            }

        }


        public static void main(String[] args) throws InterruptedException, IOException{
            Thread thread = new Thread(new TCPServer(9999));
            thread.start();
            Thread.sleep(100000);
            /*结束服务器线程*/
            thread.interrupt();
        }
    }

     
}
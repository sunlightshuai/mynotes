package com.mynetty.nio.channel;
 
import com.mynetty.nio.buffer.Buffers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
 
/*客户端:客户端每隔1~2秒自动向服务器发送数据，接收服务器接收到数据并显示*/
public class ClientSocketChannelDemo {

    private static class TCPClient implements Runnable{

        private String name ;
        private Random rnd = new Random();

        private InetSocketAddress remoteAddress;


        private Charset utf8 = Charset.forName("UTF-8");
        private Selector selector = null;

        public TCPClient(String name,InetSocketAddress remoteAddress){
            this.name = name;
            this.remoteAddress = remoteAddress;
            init();
        }


        private void init(){
            try {
                SocketChannel sc = SocketChannel.open();
                // 设置通道为非阻塞
                sc.configureBlocking(false);
                // 常见选择器
                selector = Selector.open();
                // 注册事件
                int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
                // 向选择器注册通道
                sc.register(selector,interestSet,new Buffers(256,256));
                // 向服务器发起连接
                sc.connect(remoteAddress);
                // 等待三次握手完成
                while (!sc.finishConnect()){
                    // System.out.println("wait for connecting");
                    ;
                }

                System.out.println(name+" "+"finished connection !");

            }catch (IOException e){
                System.out.println("client connect failed");
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                int i = 1;
                while (!Thread.currentThread().isInterrupted()){
                    // 阻塞等待
                    selector.select();
                    // Set中的每个key代表一个通道
                    Set<SelectionKey> keySet = selector.selectedKeys();

                    Iterator<SelectionKey> it = keySet.iterator();

                    while(it.hasNext()){
                        SelectionKey key = it.next();
                        it.remove();

                        // 通过SelectKey获取对应的通道
                        Buffers buffers = (Buffers)key.attachment();
                        ByteBuffer readBuffer = buffers.getReadBuffer();
                        ByteBuffer writeBuffer = buffers.getWriteBuffer();

                        // 通过selectionKey获取通道对应的缓冲区
                        SocketChannel sc = (SocketChannel)key.channel();

                        // 表示底层socket的读缓冲区有数据可读
                        if (key.isReadable()){
                            // 从socket 的读缓冲区读取到自定义的缓冲区中
                            sc.read(readBuffer);
                            readBuffer.flip();
                            CharBuffer cb = utf8.decode(readBuffer);

                            System.out.println("客户端接收到的消息："+new String(cb.array()));
                            readBuffer.clear();
                        }

                        // socket的写缓冲区可写
                        if (key.isWritable()){
                            writeBuffer.put(("第"+i+"次发送消息").getBytes("UTF-8"));

                            writeBuffer.flip();
                            sc.write(writeBuffer);
                            writeBuffer.clear();
                            i++;
                        }

                        Thread.sleep(1000+rnd.nextInt(100));

                    }
                }
            }catch(InterruptedException e){
                System.out.println(name + " is interrupted");
            }catch (IOException s){
                System.out.println("client select failed");
                s.printStackTrace();
            } finally {
                try {
                    selector.close();
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    System.out.println(name+" closed" );
                }
            }

        }
    }


    public static void main(String[] args) throws InterruptedException {
        InetSocketAddress remoteAddress = new InetSocketAddress("127.0.0.1",9999);
        Thread t1 = new Thread(new TCPClient("thread1",remoteAddress));
        Thread t2 = new Thread(new TCPClient("thread2",remoteAddress));
        Thread t3 = new Thread(new TCPClient("thread3",remoteAddress));
        Thread t4 = new Thread(new TCPClient("thread4",remoteAddress));

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(1000);

        t1.interrupt();

        t4.start();

    }

}
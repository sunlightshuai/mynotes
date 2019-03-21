package com.nio.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;


public class SocketNIoAdapter {

	private final String IP = "138.138.0.33";
	
	private final int PORT = 8080;
	
	private final int READ_LEN = 1024;
	
	public Object doComm(String param) {
		long begin = System.currentTimeMillis();
		byte[] result = null;
		String addres = IP;
		SocketChannel client = null;
		Selector selector = null;
		try {
		    selector = Selector.open();
			SocketAddress address = new InetSocketAddress(addres, PORT);
			
			client = SocketChannel.open(address);
			client.configureBlocking(false);
			client.register(selector, SelectionKey.OP_READ);
			byte[] sendstr = param.toString().getBytes();
			
			int leng = sendstr.length;
			ByteBuffer sendbuffer = ByteBuffer.allocateDirect(leng);
			sendbuffer.put(sendstr);
			sendbuffer.flip();
			client.write(sendbuffer);
			int length = -1;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while (true) {
				if (!client.isOpen())
					break;
				int shijian = selector.select();
				if (shijian == 0) {
					continue;
				}
				SocketChannel sc;
				for (SelectionKey key : selector.selectedKeys()) {
					selector.selectedKeys().remove(key);
					if (key.isReadable()) {
						sc = (SocketChannel) key.channel();
						ByteBuffer readBuffer = null;
						int i = -1;
						do{
							length = READ_LEN;
							byte[] rd = new byte[length];
							readBuffer = ByteBuffer.wrap(rd);
							
							i = sc.read(readBuffer);
							if(i > 0){
								byte[] by = new byte[i];
								readBuffer.flip();
								readBuffer.get(by,0,i);
								bos.write(by);
							}
						} while (i > 0);
						  if(i == -1){
							client.close();
					    }					  	  
					}
				}
			}
			if (bos.size() > 0) {
				String stres = bos.toString().trim();
				System.err.println("返回的数据是:"+stres);
			}
		} catch (UnresolvedAddressException ue){
			System.err.println(ue.getMessage());
		} catch (ConnectException ce){
			System.err.println(ce.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if(null != selector && selector.isOpen())
					selector.close();
				if(null != client && client.isOpen())
					client.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
			long end = System.currentTimeMillis();
			long time = end - begin;
			System.out.println("ִ执行时间:"+time);
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		SocketNIoAdapter socketAdapter = new SocketNIoAdapter();
		String hello = "nihao";
		socketAdapter.doComm(hello);
	}
}

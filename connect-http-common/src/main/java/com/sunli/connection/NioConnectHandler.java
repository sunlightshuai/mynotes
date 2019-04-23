package com.sunli.connection;

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
import java.util.Properties;

import com.sunli.resource.PropertiesPathResource;
import com.sunli.util.StringUtil;

/**
 * nio方式的发送请求
 * @author sunli
 *
 */
public class NioConnectHandler extends AbstractConnectHandler {

	public NioConnectHandler(ConnectHandler connectHandler) {
		super(connectHandler);
	}

	private final String IP = "IP";
	
	private final String PORT = "PORT";
	
	private final int READ_LEN = 1024;

	@Override
	public String sendMessage(String... params) {

		long begin = System.currentTimeMillis();
		byte[] result = null;
		SocketChannel client = null;
		Selector selector = null;
		try {
		    selector = Selector.open();
			SocketAddress address = new InetSocketAddress(getIp(), getPort());
			
			client = SocketChannel.open(address);
			client.configureBlocking(false);
			client.register(selector, SelectionKey.OP_READ);
			byte[] sendstr = params[1].toString().getBytes();
			
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
		return new String(result);
	}
	
	
	public String getIp(){
		String urlPath = StringUtil.getDefultApplicationConfigPath();
		PropertiesPathResource resource = new PropertiesPathResource(urlPath);
		Properties properties = resource.getProperties();
		String ip = (String) properties.get(IP);
		return ip;
	}
	
	public int getPort(){
		String urlPath = StringUtil.getDefultApplicationConfigPath();
		PropertiesPathResource resource = new PropertiesPathResource(urlPath);
		Properties properties = resource.getProperties();
		String port = (String) properties.get(PORT);
		return Integer.parseInt(port);
	}
	
}

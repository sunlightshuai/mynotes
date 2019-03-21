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
	
	private final String PORT = "8018";
	
	private final int READ_LEN = 1024;
	
	public Object doComm(Object... param) {
		long begin = System.currentTimeMillis();
		byte[] result = null;
		String addres = IP;
		// �����첽�ͻ���
		SocketChannel client = null;
		Selector selector = null;
		try {
			// ����һ����¼�׽���ͨ���¼��Ķ���
		    selector = Selector.open();
			//�˿�
			int port = 1000;
			String str = PORT;
			if(null != str && !str.equals("")){
				port = Integer.parseInt(str);
			}
			// ����һ����������ַ�Ķ���
			SocketAddress address = new InetSocketAddress(addres, port);
			
			// �����첽�ͻ���
			client = SocketChannel.open(address);
			// ���ͻ����趨Ϊ�첽
			client.configureBlocking(false);
			// ����Ѷ������ע��˿ͻ��˵Ķ�ȡ�¼�(���ǵ���������˿ͻ��˷������ݵ�ʱ��)
			client.register(selector, SelectionKey.OP_READ);
			// ���������洢�������ݵ�byte������
			byte[] sendstr = param[0].toString().getBytes();
			
			int leng = sendstr.length;
			ByteBuffer sendbuffer = ByteBuffer.allocateDirect(leng);
			sendbuffer.put(sendstr);
			// ������������־��λ,��Ϊ������put�����ݱ�־���ı�Ҫ����ж�ȡ���ݷ��������,��Ҫ��λ
			sendbuffer.flip();
			// ���������������
			client.write(sendbuffer);
			int length = -1;
			// ����һ�����ڴ洢���з��������͹���������
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			// ����ѭ������ȡ���������ص�����
			while (true) {
				// ����ͻ�������û�д򿪾��˳�ѭ��
				if (!client.isOpen())
					break;
				// �˷���Ϊ��ѯ�Ƿ����¼��������û�о�����,�еĻ������¼�����
				int shijian = selector.select();
				// ���û���¼�����ѭ��
				if (shijian == 0) {
					continue;
				}
				// ����һ����ʱ�Ŀͻ���socket����
				SocketChannel sc;
				// �������е��¼�
				for (SelectionKey key : selector.selectedKeys()) {
					// ɾ�������¼�
					selector.selectedKeys().remove(key);
					// ������¼�������Ϊreadʱ,��ʾ�������򱾿ͻ��˷���������
					if (key.isReadable()) {
						// ����ʱ�ͻ��˶���ʵ��Ϊ���¼���socket����
						sc = (SocketChannel) key.channel();
						// ������������Ա��´ζ�ȡ
						// �������ڽ��շ��������ص����ݵĻ�����
						ByteBuffer readBuffer = null;
						// ��ѭ���ӱ��¼��Ŀͻ��˶����ȡ�����������������ݵ���������
						int i = -1;
						do{
							length = READ_LEN;
							byte[] rd = new byte[length];
							readBuffer = ByteBuffer.wrap(rd);
							
							i = sc.read(readBuffer);
							// �����ζ�ȡ�����ݴ浽byte����
							//�����ڶ�ȡ����ʱ��һ���޷�����ʱ�м䲹λ�Ŀո�
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
				System.err.println("���ؽ��:"+stres);
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
			System.out.println("ִ��ʱ��:"+time);
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		SocketNIoAdapter socketAdapter = new SocketNIoAdapter();
		socketAdapter.doComm();
	}
}

package com.hoperun.http.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public abstract class AbstractHttpConnectClient {
	
	public abstract String frontMessage(String servicesName,Map<String,Object> bodyMap,Map<String,Object> headMap);
	
	public abstract Map<String,Object> backMessage(String message);

	public String doSendMessage(String url,String inputParam){
        URL realUrl = null;
		try {
			realUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        // 打开和URL之间的连接
        URLConnection conn = null;
		try {
			conn = realUrl.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setAttribution(conn);
        // 获取URLConnection对象对应的输出流
        OutputStream ops = null;
        try {
        	ops = conn.getOutputStream();
        	// 发送请求参数
        	ops.write(inputParam.getBytes());
        	// flush输出流的缓冲
        	ops.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        String result = "";
        try {
        	InputStream inps = conn.getInputStream();
        	int len = inps.available();
        	byte[] inputByte = new byte[len];
        	inps.read(inputByte);
        	result = new String(inputByte);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return result;
	}
	
	
	
	
	private void setAttribution(URLConnection conn) {
		 // 设置通用的请求属性
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("Content-Type", "application/json");
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);
	}
	
}

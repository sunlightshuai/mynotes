package com.sunli.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import com.sunli.resource.PropertiesPathResource;
import com.sunli.util.StringUtil;

/**
 * http方式的发送请求
 * @author sunli
 * 
 */
public class HttpConnectHandler extends AbstractConnectHandler {
	
	public HttpConnectHandler(ConnectHandler connectHandler) {
		super(connectHandler);
	}

	private static final String HTTP_URI = "HTTP_URI";
	
	private static final String SPLIT = "/";

	@Override
	public String sendMessage(String ... params) {
		
		URL realUrl = null;
		try {
			String url = getUrl()+SPLIT+params[0];
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
        	ops.write(params[0].getBytes());
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
	
	
	public String getUrl() {
		String urlPath = StringUtil.getDefultApplicationConfigPath();
		PropertiesPathResource resource = new PropertiesPathResource(urlPath);
		Properties properties = resource.getProperties();
		String uri = (String) properties.get(HTTP_URI);
		return uri;
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

package com.sunli.http.client;

import java.util.Map;
import java.util.Properties;

import com.sunli.resource.PropertiesPathResource;
import com.sunli.util.StringUtil;

public abstract class AbstractHttpHandler extends AbstractHttpConnectClient {
	
	private static final String HTTP_URI = "HTTP_URI";
	
	private static final String SPLIT = "/";
	
	public Map<String,Object> sendMessage(String servicesFileName,String serviceName) {
		String inputStr = frontMessage(servicesFileName,serviceName);
		String urlPath = StringUtil.getDefultApplicationConfigPath();
		PropertiesPathResource resource = new PropertiesPathResource(urlPath);
		Properties properties = resource.getProperties();
		String uri = (String) properties.get(HTTP_URI);
		String receive = doSendMessage(uri+SPLIT+serviceName,inputStr);
		return backMessage(receive);
	}
	
	
}

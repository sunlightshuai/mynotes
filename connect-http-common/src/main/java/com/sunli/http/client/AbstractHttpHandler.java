package com.sunli.http.client;

import java.util.Map;

import com.sunli.resource.ObtainResource;
import com.sunli.resource.service.LoadDocumentService;

public abstract class AbstractHttpHandler extends AbstractHttpConnectClient {
	
	private static final String HTTP_URI = "HTTP_URI";
	
	private static final String SPLIT = "/";
	
	public Map<String,Object> sendMessage(String servicesFileName,String serviceName) {
		String inputStr = frontMessage(servicesFileName,serviceName);
		ObtainResource resource = new ObtainResource();
		Map<String,Object> resourceMap = resource.getResource();
		String uri = (String) resourceMap.get(HTTP_URI);
		String receive = doSendMessage(uri+SPLIT+serviceName,inputStr);
		return backMessage(receive);
	}
	
	
}

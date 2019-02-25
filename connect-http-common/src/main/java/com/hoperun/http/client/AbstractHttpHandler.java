package com.hoperun.http.client;

import java.util.Map;

import com.hoperun.factory.ServerFactory;
import com.hoperun.resource.ObtainResourceService;
import com.hoperun.resource.ObtainResourceServiceImpl;

public abstract class AbstractHttpHandler extends AbstractHttpConnectClient {
	
	private static final String HTTP_URI = "HTTP_URI";
	
	private static final String SPLIT = "/";
	
	public Map<String,Object> sendMessage(String servicesName,Map<String,Object> bodyMap,Map<String,Object> headMap) {
		String inputStr = frontMessage(servicesName,bodyMap,headMap);
		ObtainResourceService obtainResource = ServerFactory.getInstance().getObtainResourceService(ObtainResourceServiceImpl.class);
		Map<String,Object> resourceMap = obtainResource.getAppContextPath();
		String uri = (String) resourceMap.get(HTTP_URI);
		String receive = doSendMessage(uri+SPLIT+servicesName,inputStr);
		return backMessage(receive);
	}
	
}

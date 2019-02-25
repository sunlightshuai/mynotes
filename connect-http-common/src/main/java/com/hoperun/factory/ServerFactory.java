package com.hoperun.factory;

import com.hoperun.http.client.HttpClientService;
import com.hoperun.http.client.impl.HttpClientServiceImpl;
import com.hoperun.resource.ObtainResourceService;
import com.hoperun.resource.service.ExecuteObtainParseResultService;
import com.hoperun.resource.service.impl.BodyParseImpl;
import com.hoperun.resource.service.impl.HeaderParseImpl;

public class ServerFactory  extends SFactory{
	
	private ServerFactory() {}
	
	public static final ServerFactory getInstance() {
		return InnerClass.serverFactory;
	}
	
	public ObtainResourceService getObtainResourceService(Class<?> clazz) {
		return (ObtainResourceService) createObject(clazz);
	}
	
	public HttpClientService getHttpClientService() {
		return (HttpClientService) createObject(HttpClientServiceImpl.class);
	}
	
	
	public ExecuteObtainParseResultService getHeaderParse() {
		return (ExecuteObtainParseResultService) createObject(HeaderParseImpl.class);
	}
	
	public ExecuteObtainParseResultService getBodyParse() {
		return (ExecuteObtainParseResultService) createObject(BodyParseImpl.class);
	}
	
	
	private static final class InnerClass{
		private static final ServerFactory serverFactory = new ServerFactory();
	}

}

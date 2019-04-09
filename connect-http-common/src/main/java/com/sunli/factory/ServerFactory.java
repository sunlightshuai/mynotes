package com.sunli.factory;

import com.sunli.http.client.HttpClientService;
import com.sunli.http.client.impl.HttpClientServiceImpl;
import com.sunli.resource.ObtainResourceService;
import com.sunli.resource.service.ExecuteObtainParseResultService;
import com.sunli.resource.service.impl.BodyParseImpl;
import com.sunli.resource.service.impl.HeaderParseImpl;

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

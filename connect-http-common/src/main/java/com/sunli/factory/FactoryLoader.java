package com.sunli.factory;

import com.sunli.http.client.HttpClientService;
import com.sunli.http.client.HttpClientServiceImpl;

public class FactoryLoader  extends Factory{
	
	private FactoryLoader() {}
	
	public static final FactoryLoader getInstance() {
		return InnerClass.serverFactory;
	}
	
	public HttpClientService getHttpClientService() {
		return (HttpClientService) createObject(HttpClientServiceImpl.class);
	}
	
	private static final class InnerClass{
		private static final FactoryLoader serverFactory = new FactoryLoader();
	}

}

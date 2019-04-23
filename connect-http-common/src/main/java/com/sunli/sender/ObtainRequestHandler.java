package com.sunli.sender;


import com.sunli.connection.ConnectHandler;
import com.sunli.connection.ConnectionDecorator;


public class ObtainRequestHandler extends ConnectionDecorator {

	public ObtainRequestHandler(ConnectHandler connectHandler) {
		super(connectHandler);
	}

	@Override
	public void preRequest(String... params) {
		
	}

	@Override
	public String backRequest(String message) {
		return message;
	}
	
	

}

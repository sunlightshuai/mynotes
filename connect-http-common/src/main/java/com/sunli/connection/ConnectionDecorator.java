package com.sunli.connection;

public abstract class ConnectionDecorator implements ConnectHandler {
	
	private ConnectHandler connectHandler; 
	
	public ConnectionDecorator(ConnectHandler connectHandler){
		this.connectHandler = connectHandler;
	}
	
	public String sendMessage(String ... params){
		preRequest(params);
		String messages = connectHandler.sendMessage(params);
		return backRequest(messages);
	}
	
	/**
	 * 发送数据前处理
	 * @param input
	 */
	public abstract void preRequest(String ... params);
	
	/**
	 * 接收到数据后处理
	 * @param message
	 */
	public abstract String backRequest(String message);

}

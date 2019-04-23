package com.sunli.connection;

/**
 * 使用责任链模式获取连接方式
 * @author sunli
 *
 */
public abstract class AbstractConnectHandler implements ConnectHandler {
	
	private ConnectHandler connectHandler;

	public AbstractConnectHandler(ConnectHandler connectHandler) {
		this.connectHandler = connectHandler;
	}
	
	public String sendMessage(String ... params){
		return connectHandler.sendMessage(params);
	};
	
}

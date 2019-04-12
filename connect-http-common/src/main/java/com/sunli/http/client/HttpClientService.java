package com.sunli.http.client;

import java.util.Map;

public interface HttpClientService {

	/**
	 * 发送http请求
	 * @param servicesFileName 文件名称
	 * @param serviceName	文件名称中具体的文件服务名
	 * @return
	 */
	public Map<String,? extends Object> sendMessage(String servicesFileName,String serviceName);
	
}

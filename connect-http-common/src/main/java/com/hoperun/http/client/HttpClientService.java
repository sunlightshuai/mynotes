package com.hoperun.http.client;

import java.util.Map;

public interface HttpClientService {

	public Map<String,? extends Object> sendMessage(String servicesName,Map<String,Object> bodyMap,Map<String,Object> headMap);
	
}

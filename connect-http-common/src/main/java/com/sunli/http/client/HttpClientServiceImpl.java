package com.sunli.http.client;

import java.util.HashMap;
import java.util.Map;

import com.sunli.resource.service.BodyDocumentService;
import com.sunli.resource.service.HeaderDocumentService;
import com.sunli.resource.service.LoadDocumentDecorator;
import com.sunli.util.JsonUtil;

public class HttpClientServiceImpl extends AbstractHttpHandler {
	
	@Override
	public String frontMessage(String servicesFileName,String serviceName) {
		Map<String,Object> inputMap = new HashMap<String,Object>();
		Map<String,Object> serviceMap = new HashMap<String,Object>();
		BodyDocumentService bodyDocument = new BodyDocumentService();
		LoadDocumentDecorator documentDecorator = new LoadDocumentDecorator(bodyDocument);
		HeaderDocumentService headerDoc = new HeaderDocumentService();
		serviceMap.put("Body", documentDecorator.getXMLDocument());
		documentDecorator = new LoadDocumentDecorator(headerDoc);
		serviceMap.put("header", documentDecorator.getXMLDocument());
		inputMap.put("Service", serviceMap);
		return JsonUtil.mapToJsonStr(inputMap);
	}

	@Override
	public Map<String,Object> backMessage(String message) {
		return JsonUtil.jsonToMap(message);
	}

}

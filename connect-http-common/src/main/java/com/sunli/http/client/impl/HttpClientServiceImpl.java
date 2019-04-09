package com.sunli.http.client.impl;

import java.util.HashMap;
import java.util.Map;

import com.sunli.factory.ServerFactory;
import com.sunli.http.client.AbstractHttpHandler;
import com.sunli.http.client.HttpClientService;
import com.sunli.util.DateUtil;
import com.sunli.util.JsonUtil;

public class HttpClientServiceImpl extends AbstractHttpHandler implements HttpClientService {
	
	
	private static final String[] heads = {"BranchId","Channel","ConsumerId","LegalRepCode","LocalLang","ServiceCode","ServiceName","SourceSysId",
			"TranCode","TranDate","TranSeq","TranTeller","TranTime"};

	@Override
	public String frontMessage(String servicesName,Map<String,Object> bodyMap,Map<String,Object> headMap) {
		Map<String,Object> inputMap = new HashMap<String,Object>();
		Map<String,Object> serviceMap = new HashMap<String,Object>();
		// headMap为空
		if (null == headMap) {
			Map<String,Object> headerMap = ServerFactory.getInstance().getHeaderParse().doHandler();
			for (String head : heads) {
				if (!headerMap.containsKey(head) || "".equals(headerMap.get(head))) {
					// 只处理时间
					if ("TranTime".equals(head)) {
						headerMap.put("TranTime",DateUtil.nowDateStr("yyyyMMddHH:mm:ss.SSS"));
					}else if ("TranDate".equals(head)) {
						headerMap.put("TranTime",DateUtil.nowDateStr("yyyyMMdd"));
					}
				}
			}
			serviceMap.put("Header", headerMap);
		} else {
			serviceMap.put("Header", headMap);
		}
		serviceMap.put("Body", bodyMap);
		inputMap.put("Service", serviceMap);
		return JsonUtil.mapToJsonStr(inputMap);
	}

	@Override
	public Map<String,Object> backMessage(String message) {
		return JsonUtil.jsonToMap(message);
	}


}

package com.hoperun.send.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.factory.ServerFactory;
import com.hoperun.http.client.HttpClientService;
import com.hoperun.resource.service.ExecuteObtainParseResultService;
import com.hoperun.resource.service.model.AttrParamModel;
import com.hoperun.resource.service.model.FormatModel;
import com.hoperun.send.ISendMessageService;
import com.hoperun.util.StringUtil;

public class SendMessageServiceImpl implements ISendMessageService {

	@SuppressWarnings("unchecked")
	public void sendMessage(String fileName,String serviceName) {
		fileName = StringUtil.changeSuffix(fileName);
		HttpClientService httpclient = ServerFactory.getInstance().getHttpClientService();
		ExecuteObtainParseResultService body = ServerFactory.getInstance().getBodyParse();
		Map<String,Object> bodyResultMap = body.doHandler();
		Map<String,FormatModel> fileModelMap = (Map<String,FormatModel>) bodyResultMap.get(fileName);
		FormatModel formatModel =  fileModelMap.get(serviceName);
		List<AttrParamModel> listMap = formatModel.getAttrParamModel();
		Map<String,Object> bodyMap = new HashMap<String,Object>();
		for (AttrParamModel tempAttr : listMap) {
			if ("IN".equals(tempAttr.getMode())) {
				bodyMap.put(tempAttr.getName(), tempAttr.getValue());
			} 
		}
		System.out.println(httpclient.sendMessage(serviceName, bodyMap, null));
	}
	
	
	public static void main(String[] args) {
		SendMessageServiceImpl sendMessageServiceImpl = new SendMessageServiceImpl();
		sendMessageServiceImpl.sendMessage("services_identify", "selectPersonCustDiscernInfo");
	}
}

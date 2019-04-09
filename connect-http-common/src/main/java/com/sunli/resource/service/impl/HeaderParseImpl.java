package com.sunli.resource.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sunli.resource.service.AbstractLoadDocumentService;
import com.sunli.resource.service.ExecuteObtainParseResultService;
import com.sunli.util.StringUtil;

public class HeaderParseImpl extends AbstractLoadDocumentService implements ExecuteObtainParseResultService {
	
	private static final String ROOT = "services";
	
	private static final String HEADER = "header";
	
	private static final String TEXT = "#text";

	public Map<String,Object> doHandler(){
		Document document = loadToDocument(StringUtil.getDefaultHeaderConfigPath());
		Map<String,Object> resultMap = new ConcurrentHashMap<String, Object>();
		NodeList headerNode = document.getElementsByTagName(ROOT);
		for (int i = 0;i<headerNode.getLength();i++) {
			Node nodeUp = headerNode.item(i);
			NodeList headerNodeList = nodeUp.getChildNodes();
			for (int j = 0;j<headerNodeList.getLength();j++) {
				Node header = headerNodeList.item(j);
				getHeader(resultMap,header);
			}
		}
		return resultMap;
	}
	
	private void getHeader(Map<String,Object> resultMap,Node header) {
		if (HEADER.equals(header.getNodeName())) {
			NodeList list = header.getChildNodes();
			for(int z = 0 ; z < list.getLength() ; z ++) {
				Node context = list.item(z);
				String name = context.getNodeName();
				if (!TEXT.equals(name)) {
					String value = StringUtil.getTextValue(context.getTextContent());
					resultMap.put(name, value);
				}
			}
		}
	}
	
}

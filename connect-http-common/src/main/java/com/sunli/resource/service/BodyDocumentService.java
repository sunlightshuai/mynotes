package com.sunli.resource.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sunli.resource.service.model.AttrParamModel;
import com.sunli.resource.service.model.FormatModel;
import com.sunli.util.StringUtil;

/**
 * 解析body体
 * @author sunli
 *
 */
public class BodyDocumentService extends AbstractLoadDocumentService {
	
	private static final String ROOT = "services";
	
	private static final String SERVICE = "service";
	
	private static final String SERVICE_NAME = "name";
	
	private static final String SERVICE_ENGINE = "engine";
	
	private static final String SERVICE_EXPORT = "export";
	
	private static final String SERVICE_VALIDATE = "validate";
	
	private static final String SERVICE_LOCATION = "location";
	
	private static final String SERVICE_INVOKE = "invoke";
	
	private static final String SERVICE_DOWN_DESCRIPTION = "description";
	
	private static final String SERVICE_DOWN_ATTRIBUTE = "attribute";
	
	private static final String ATTRIBUTE_NAME = "name";
	
	private static final String ATTRIBUTE_TYPE = "type";
	
	private static final String ATTRIBUTE_MODE = "mode";
	
	private static final String ATTRIBUTE_OPT = "optional";
	
	private static final String ATTRIBUTE_TITLE = "title";
	
	private static final String ATTRIBUTE_VALUE = "value";
	
	private static final String TYPE_LIST = "List";
	
	private static final String TYPE_MAP = "Map";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getXMLDocument() {
		File files = new File(StringUtil.getDefaultBodyPath());
		Map<String,Map<String, FormatModel>> result = new ConcurrentHashMap<String,Map<String, FormatModel>>();
		if (files.isDirectory()) {
			String[] fileNames = files.list();
			File fileAbsoluteFile = files.getAbsoluteFile();
			String foldName = fileAbsoluteFile.getAbsolutePath();
			for (String tempName : fileNames) {
				Document document = loadToDocument(foldName+File.separator+tempName);
				NodeList nodeList = document.getElementsByTagName(ROOT);
				Map<String,FormatModel> resultMap = new ConcurrentHashMap<String,FormatModel>();
				for (int i = 0;i<nodeList.getLength();i++) {
					Node node = nodeList.item(i);
					NodeList nodeListChildNodes = node.getChildNodes();
					parseServiceTag(resultMap,nodeListChildNodes);
				}
				result.put(tempName, resultMap);
			}
		}
		return result;
	}
	
	public void parseServiceTag(Map<String,FormatModel> resultMap,NodeList services) {
		for (int j = 0;j<services.getLength();j++) {
			Node nodeTemp = services.item(j);
			if (SERVICE.equals(nodeTemp.getNodeName())) {
				NamedNodeMap nameMap = nodeTemp.getAttributes();
				Node nodeName = nameMap.getNamedItem(SERVICE_NAME);
				
				Node nodeEngine = nameMap.getNamedItem(SERVICE_ENGINE);
				Node nodeExport = nameMap.getNamedItem(SERVICE_EXPORT);
				Node nodeLocation = nameMap.getNamedItem(SERVICE_LOCATION);
				Node nodeInvoke = nameMap.getNamedItem(SERVICE_INVOKE);
				Node nodeVolidate = nameMap.getNamedItem(SERVICE_VALIDATE);
				FormatModel formatModel = new FormatModel();
				if (null != nodeName && null != nodeName.getNodeValue()) {
					formatModel.setName(nodeName.getNodeValue());
				}
				
				if (null != nodeEngine && null != nodeEngine.getNodeValue()) {
					formatModel.setEngine(nodeEngine.getNodeValue());
				}
				
				if (null != nodeExport && null != nodeExport.getNodeValue()) {
					formatModel.setExport(nodeExport.getNodeValue());				
				}
				
				if (null != nodeInvoke && null != nodeInvoke.getNodeValue()) {
					formatModel.setInvoke(nodeInvoke.getNodeValue());
				}
				
				if (null != nodeLocation && null != nodeLocation.getNodeValue()) {
					formatModel.setLocation(nodeLocation.getNodeValue());
				}
				
				if (null != nodeVolidate && null != nodeVolidate.getNodeValue()) {
					formatModel.setValidate(nodeVolidate.getNodeValue());
				}
				
				parseServiceTagDown(formatModel,nodeTemp.getChildNodes());
				resultMap.put(nodeName.getNodeValue(), formatModel);
// System.out.println(formatModel);
			}
			
		}
	}
	
	public void parseServiceTagDown(FormatModel formatModel,NodeList serviceTag) {
		
		List<AttrParamModel> attrParamModel = new ArrayList<AttrParamModel>();
		boolean flag = true;
		for (int j = 0;j<serviceTag.getLength();j++) {
			Node nodeTemp = serviceTag.item(j);
			if (SERVICE_DOWN_DESCRIPTION.equals(nodeTemp.getNodeName()) && flag) {
				flag = false;
				formatModel.setTitle(nodeTemp.getTextContent());
			} else if (SERVICE_DOWN_ATTRIBUTE.equals(nodeTemp.getNodeName())) {
				parseAttribute(attrParamModel,nodeTemp);
			}
		}
		formatModel.setAttrParamModel(attrParamModel);
	}
	
	
	public void parseAttribute (List<AttrParamModel> attrParam,Node serviceTag) {
		AttrParamModel attrParamModel = null;
		NamedNodeMap namedNodeMap = serviceTag.getAttributes();
		Node nodename = namedNodeMap.getNamedItem(ATTRIBUTE_NAME);
		Node nodeType = namedNodeMap.getNamedItem(ATTRIBUTE_TYPE);
		Node nodeOpt = namedNodeMap.getNamedItem(ATTRIBUTE_OPT);
		Node nodeTile = namedNodeMap.getNamedItem(ATTRIBUTE_TITLE);
		Node nodeMode = namedNodeMap.getNamedItem(ATTRIBUTE_MODE);
		
		Node nodevalue = namedNodeMap.getNamedItem(ATTRIBUTE_VALUE);
		
		if (null != nodeMode && null != nodeMode.getNodeValue() 
				&& null != nodeType && null != nodeType.getNodeValue()) {
			attrParamModel = new AttrParamModel(); 
			attrParamModel.setMode((null != nodeMode)?nodeMode.getNodeValue():"");
			attrParamModel.setName((null != nodename)?nodename.getNodeValue():"");
			attrParamModel.setOptional((null != nodeOpt)?nodeOpt.getNodeValue():"");
			attrParamModel.setTitle((null != nodeTile)?nodeTile.getNodeValue():"");
			attrParamModel.setType((null != nodeType)?nodeType.getNodeValue():"");
			attrParamModel.setValue((null != nodevalue)?nodevalue.getNodeValue():"");
			if (TYPE_LIST.equals(nodeType.getNodeValue()) || TYPE_MAP.equals(nodeType.getNodeValue())) {
				List<AttrParamModel> attrParamDown = new ArrayList<AttrParamModel>();
				NodeList nodeList = serviceTag.getChildNodes();
				if(nodeList != null) {
					for (int j = 0;j<nodeList.getLength();j++) {
						Node nodeTemp = nodeList.item(j);
						if (null != nodeTemp && SERVICE_DOWN_ATTRIBUTE.equals(nodeTemp.getNodeName())) {
							parseAttribute(attrParamDown,nodeTemp);
						}
					}
					attrParamModel.setParamOutputModel(attrParamDown);
				}
				
			}
			attrParam.add(attrParamModel);
		}
	}
}

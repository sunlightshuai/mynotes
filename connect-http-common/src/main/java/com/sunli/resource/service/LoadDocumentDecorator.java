package com.sunli.resource.service;

import java.util.Map;

public class LoadDocumentDecorator extends AbstractLoadDocumentDecorator {

	public LoadDocumentDecorator(LoadDocumentService loadDocumentService) {
		super(loadDocumentService);
	}
	
	public Map<String,Object> getXMLDocument(){
		return super.getXMLDocument();
	}

}

package com.sunli.resource.service;

import java.util.Map;

public abstract class AbstractLoadDocumentDecorator implements LoadDocumentService {

	private LoadDocumentService loadDocumentService;
	
	public AbstractLoadDocumentDecorator(LoadDocumentService loadDocumentService){
		this.loadDocumentService = loadDocumentService;
	}
	
	public Map<String,Object> getXMLDocument(){
		return loadDocumentService.getXMLDocument();
	}
}

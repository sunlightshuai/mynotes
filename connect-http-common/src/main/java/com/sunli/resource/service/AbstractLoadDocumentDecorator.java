package com.sunli.resource.service;

import java.util.List;
import java.util.Map;

public abstract class AbstractLoadDocumentDecorator implements LoadDocumentService {

	private LoadDocumentService loadDocumentService;
	
	private List<LoadDocumentService> loadDocumentServices;
	
	public LoadDocumentService getLoadDocumentService() {
		return loadDocumentService;
	}

	public AbstractLoadDocumentDecorator(LoadDocumentService loadDocumentService){
		this.loadDocumentService = loadDocumentService;
		loadDocumentServices.add(loadDocumentService);
	}
	
	public Map<String,Object> getXMLDocument(){
		return loadDocumentService.getXMLDocument();
	}
}

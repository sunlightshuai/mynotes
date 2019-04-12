package com.sunli.resource.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.sunli.resource.PropertiesPathResource;


public abstract class AbstractLoadDocumentService implements LoadDocumentService{
	
	public DocumentBuilder loadServicesXml() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbuilder = null;
		try {
			dbuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return dbuilder;
	}
	
	public Document loadToDocument(String pathFile) {
		InputStream inputStream = null;
		try {
			DocumentBuilder documentBuilder = loadServicesXml();
			PropertiesPathResource resource = new PropertiesPathResource(pathFile);
			inputStream = resource.getInputStream();
			Document document = documentBuilder.parse(inputStream);
			return document;
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} finally {
			closeInputStream(inputStream);
		}
		return null;
	}
	
	public void closeInputStream(InputStream inputStream) {
		if (null != inputStream) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}

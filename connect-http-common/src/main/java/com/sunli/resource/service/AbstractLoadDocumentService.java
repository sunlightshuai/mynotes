package com.sunli.resource.service;


import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.sunli.util.StringUtil;


public abstract class AbstractLoadDocumentService extends AbstractReadFileService{


	public Document loadToDocument(String pathFile) {
		InputStream inputStream = null;
		try {
			DocumentBuilder documentBuilder = StringUtil.loadServicesXml();
			inputStream = readFile(pathFile);
			Document document = documentBuilder.parse(inputStream);
			return document;
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} finally {
			closeInputStream(inputStream);
		}
		return null;
	}
	

}

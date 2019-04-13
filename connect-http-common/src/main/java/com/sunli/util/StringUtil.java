package com.sunli.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class StringUtil {
	
	public static ClassLoader getDefaultClassLoader(){
		ClassLoader cl = null;
		try{
			cl = Thread.currentThread().getContextClassLoader();
		}catch (Exception e) {
		}
		if(null == cl){
			cl = StringUtil.class.getClassLoader();
			
			if(null == cl){
				try{
					cl = ClassLoader.getSystemClassLoader();
				}catch(Throwable ex){
				}
			}
		}
		return cl;
	}
	
	
	public static final String getUserDir() {
		return System.getProperty("user.dir");
	}
	
	public static final String getParentPath() {
		return new File(getUserDir()).getParent();
	}
	
	public static String getDefaultHeaderConfigPath() {
		StringBuilder sbler = new StringBuilder();
		sbler.append(StringUtil.getUserDir()).append(File.separator).append(Constant.PARENT_DIR).append(File.separator).append(Constant.HEADER_NAME);
		return sbler.toString();
	}
	
	public static String getDefaultBodyPath() {
		StringBuilder sbler = new StringBuilder();
		sbler.append(StringUtil.getUserDir()).append(File.separator).append(Constant.PARENT_DIR).append(File.separator).append(Constant.SERVICEDEF).append(File.separator);
		return sbler.toString();
	}
	
	
	public static String getTextValue(String param) {
		return (null != param)?param:"";
	}
	
	public static String changeSuffix(String name) {
		if (!name.endsWith(".xml")) {
			return name+".xml";
		}
		return name;
	}
	
	public static String getDefultApplicationConfigPath() {
		StringBuilder sbler = new StringBuilder();
		sbler.append(StringUtil.getUserDir()).append(File.separator).append(Constant.PARENT_DIR).append(File.separator).append(Constant.APP_CONFIG);
		return sbler.toString();
	}
	
}

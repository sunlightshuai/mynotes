package com.sunli.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 * @author sunli
 *
 */
public class PropertiesPathResource {

	private Properties properties;
	
	private String path;
	
	public PropertiesPathResource(String path) {
		this(path, new Properties());
	}
	
	public PropertiesPathResource(String path,Properties properties) {
		this.path = path;
		this.properties = properties;
	}
	
	public final String getPath() {
		return this.path;
	}
	
	public final Properties getProperties() {
		if (null == properties) {
			this.properties = new Properties();
		}
		try {
			properties.load(getInputStream());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.properties;
	}
	
	public final InputStream getInputStream() throws FileNotFoundException {
		if (null == this.path || "".equals(this.path)) {
			throw new NullPointerException("the path is empty...");
		}
		File file = new File(this.path);
		return new FileInputStream(file);
	}
}

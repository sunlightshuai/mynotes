package com.sunli.resource;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.sunli.util.Constant;
import com.sunli.util.StringUtil;

/**
 * 获取application.properties
 * @author sunli
 *
 */
public class ObtainResource {
	
	private final Map<String,Object> resource = new ConcurrentHashMap<>();
	
	public Map<String, Object> getResource() {
		return resource;
	}

	public ObtainResource(){
		resource.putAll(getAppContextPath());
	}
	
	private Map<String,Object> getAppContextPath() {
		String path = getDefultApplicationConfigPath();
		PropertiesPathResource propertiesPathResource = new PropertiesPathResource(path);
		Properties ppts = propertiesPathResource.getProperties();
		Set<Entry<Object, Object>> setppts = ppts.entrySet();
		Map<String,Object> resultMap = new ConcurrentHashMap<String,Object>();
		for (Entry<Object, Object> entry:setppts){
			String key = (String) entry.getKey();
			if (null != key) {
				resultMap.put(key, entry.getValue());
			}
		}
		return resultMap;
	}
	
	private String getDefultApplicationConfigPath() {
		StringBuilder sbler = new StringBuilder();
		sbler.append(StringUtil.getUserDir()).append(File.separator).append(Constant.PARENT_DIR).append(File.separator).append(Constant.APP_CONFIG);
		return sbler.toString();
	}
	
}

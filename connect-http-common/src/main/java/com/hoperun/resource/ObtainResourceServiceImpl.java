package com.hoperun.resource;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.hoperun.constant.Constant;
import com.hoperun.util.StringUtil;

public class ObtainResourceServiceImpl implements ObtainResourceService{
	
	public Map<String,Object> getAppContextPath() {
		String path = getDefultApplicationConfigPath();
		PropertiesPathResource propertiesPathResource = new PropertiesPathResource(path);
		Properties ppts = propertiesPathResource.getProperties();
		Set<Entry<Object, Object>> setppts = ppts.entrySet();
		Iterator<Entry<Object, Object>> iterator = setppts.iterator();
		Map<String,Object> resultMap = new ConcurrentHashMap<String,Object>();
		while (iterator.hasNext()) {
			Entry<Object, Object> entry = iterator.next();
			String key = (String) entry.getKey();
			if (null != key) {
				resultMap.put(key, entry.getValue());
			}
		}
		return resultMap;
	}
	
	public String getDefultApplicationConfigPath() {
		StringBuilder sbler = new StringBuilder();
		sbler.append(StringUtil.getUserDir()).append(File.separator).append(Constant.PARENT_DIR).append(File.separator).append(Constant.APP_CONFIG);
		return sbler.toString();
	}
	
}

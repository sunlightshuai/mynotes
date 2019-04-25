package com.sunli.factory;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.sunli.resource.PropertiesPathResource;
import com.sunli.util.StringUtil;

public class Factory implements BeanFactory ,Serializable{
	
	private static Map<String,Object> register = new HashMap<String,Object>();
	
	private volatile static Factory factory = new Factory();
	
	private Factory(){
		String factory = StringUtil.getDefultFactoryPath();
		PropertiesPathResource resource = new PropertiesPathResource(factory);
		Properties properties = resource.getProperties();
		for (Entry<Object, Object> entry:properties.entrySet()){
			String className = (String) entry.getValue();
			try {
				Class<?> clazzObj = Class.forName(className);
				register.put((String) entry.getKey(), clazzObj.newInstance());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Factory getInstance(){
		return factory;
	}

	@Override
	public Object getBean(String className) {
		Object clazz = register.get(className);
		return clazz;
	}
	
	private Object readResolve() throws ObjectStreamException {    
        return factory;
    }

}

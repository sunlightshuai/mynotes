package com.design.singleton.reg;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RegSingleton implements Serializable{
	
	protected RegSingleton(){}

	private static Map<String,Object> register = new HashMap<String,Object>();
	
	static {
		RegSingleton reg = new RegSingleton();
		register.put(reg.getClass().getName(), reg);
	}
	
	public static RegSingleton getInstance(String name){
		
		if (null == name){
			name = RegSingleton.class.getName();
		}
		
		if (register.get(name) == null){
			try {
				register.put(name, Class.forName(name).newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return (RegSingleton)register.get(name);
	}
}

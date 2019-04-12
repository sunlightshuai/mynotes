package com.sunli.factory;

public abstract class Factory {
	
	public Object createObject(Class<?> clazz) {
		if (null == clazz) {
			throw new NullPointerException("the clazz is null....");
		}
		Object resultObject = null;
		try {
			resultObject = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return resultObject;
	}

}

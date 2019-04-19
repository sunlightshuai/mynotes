package com.design.factory.simple;

public class Creator {

	protected static Fruit factory(Class<?> clazzName){
		if (null != clazzName){
			try {
				return (Fruit) clazzName.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}

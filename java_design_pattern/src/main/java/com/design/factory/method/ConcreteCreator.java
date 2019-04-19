package com.design.factory.method;

public class ConcreteCreator implements Creator{

	public Fruit facotry(Class<?> clazzName) {
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

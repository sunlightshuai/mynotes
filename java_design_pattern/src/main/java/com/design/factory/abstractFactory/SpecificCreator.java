package com.design.factory.abstractFactory;


public class SpecificCreator implements Creator {

	public Drinks getDrinks(Class<?> className) {
		if (null != className){
			try {
				return (Drinks) className.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Fruit getFruit(Class<?> className) {
		if (null != className){
			try {
				return (Fruit) className.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}

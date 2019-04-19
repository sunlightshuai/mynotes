package com.design.factory.abstractFactory;

public interface Creator {

	public Drinks getDrinks(Class<?> className);
	
	public Fruit getFruit(Class<?> className);
}

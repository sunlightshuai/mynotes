package com.design.factory.abstractFactory;

public class Apple implements Fruit {

	public void eat() {
		System.out.println("eat "+this.getClass().getSimpleName());
	}

}

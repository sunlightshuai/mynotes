package com.design.factory.abstractFactory;

public class CocaCola implements Drinks {

	public void drink() {
		System.out.println("drink "+this.getClass().getSimpleName());
	}
}

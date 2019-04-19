package com.design.factory.method;

public class Tangerine implements Fruit {

	public void grow() {
		System.out.println(this.getClass().getSimpleName()+"'s grow");
	}

	public void harvest() {
		System.out.println(this.getClass().getSimpleName()+"'s harvest");
	}

	public void plant() {
		System.out.println(this.getClass().getSimpleName()+"'s plant");
	}

}

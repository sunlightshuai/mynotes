package com.design.adapter.methodway;

public class Adapter implements Target {

	private Adaptee adaptee;
	
	public Adapter(Adaptee adaptee){
		super();
		this.adaptee = adaptee;
	}
	
	@Override
	public void sayHello() {
		adaptee.sayHello();
	}

	@Override
	public void sayGoodBye() {
		System.out.println(Adapter.class.getSimpleName()+" sayGoodBye");
	}

}

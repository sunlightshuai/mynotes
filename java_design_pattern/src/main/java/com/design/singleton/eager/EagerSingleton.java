package com.design.singleton.eager;

public class EagerSingleton {

	private volatile static EagerSingleton eager = new EagerSingleton();
	
	private EagerSingleton(){}
	
	public static EagerSingleton getInstance(){
		return eager;
	}
}

package com.design.singleton.reg;

public class SubRegSingleton extends RegSingleton {
	
	public SubRegSingleton(){}

	public static SubRegSingleton getInstance(){
		return (SubRegSingleton) RegSingleton.getInstance(SubRegSingleton.class.getName());
	}
}

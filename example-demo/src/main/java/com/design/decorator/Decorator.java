package com.design.decorator;

/**
 * 装饰角色
 * @author sunli
 *
 */
public abstract class Decorator implements Component {
	
	private Component component;
	
	public Decorator(){}
	
	public Decorator(Component component){
		this.component = component;
	}
	
	public abstract void frontHandler();

	public void sampleOperation(){
		frontHandler();
		component.sampleOperation();
		backHandler();
	}
	
	public abstract void backHandler();
}

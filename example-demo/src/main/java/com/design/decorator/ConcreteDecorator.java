package com.design.decorator;


/**
 * 具体的装饰角色
 * @author sunli
 *
 */
public class ConcreteDecorator extends Decorator {
	
	public ConcreteDecorator(){}
	
	public ConcreteDecorator(Component component){
		super(component);
	}

	public void sampleOperation() {
		super.sampleOperation();
	}

	public void frontHandler() {
		System.out.println(this.getClass().getSimpleName()+"'s frontHandler");
	}

	@Override
	public void backHandler() {
		System.out.println(this.getClass().getSimpleName()+"'s backHandler");
	}
}

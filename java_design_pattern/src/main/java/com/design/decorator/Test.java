package com.design.decorator;

public class Test {

	public static void main(String[] args) {
		ConcreteComponent cc = new ConcreteComponent();
		// cc.sampleOperation();
		
		ConcreteDecorator cd = new ConcreteDecorator(cc);
		cd.sampleOperation();
	}
}

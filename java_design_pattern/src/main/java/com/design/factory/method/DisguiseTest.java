package com.design.factory.method;

public class DisguiseTest {

	public static void main(String[] args){
		Creator creator = new ConcreteCreator();
		Fruit apple = creator.facotry(Apple.class);
		apple.grow();
		Fruit tangerine = creator.facotry(Tangerine.class);
		tangerine.grow();
	}
}

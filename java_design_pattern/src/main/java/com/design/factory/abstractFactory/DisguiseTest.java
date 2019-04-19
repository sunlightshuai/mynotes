package com.design.factory.abstractFactory;

public class DisguiseTest {

	public static void main(String[] args) {
		Creator creator = new SpecificCreator();
		Drinks cocaCola = creator.getDrinks(CocaCola.class);
		cocaCola.drink();
		Fruit apple = creator.getFruit(Apple.class);
		apple.eat();
		
	}
}

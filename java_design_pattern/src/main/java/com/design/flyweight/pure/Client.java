package com.design.flyweight.pure;

public class Client {

	public static void main(String[] args) {
		FlyweightFactory factory = new FlyweightFactory();
		Flyweight fly = factory.factory(new Character('a'));
		fly.operation("First");
		fly = factory.factory(new Character('b'));
		fly.operation("secend");
		fly = factory.factory(new Character('a'));
		fly.operation("third");
		
		factory.checkFlyweight();
	}
}

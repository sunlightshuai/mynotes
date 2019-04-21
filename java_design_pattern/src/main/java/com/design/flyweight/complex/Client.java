package com.design.flyweight.complex;

public class Client {

	public static void main(String[] args) {
		FlyweightFactory factory = new FlyweightFactory();
		Flyweight fly = factory.factory("aba");
		fly.operation("First");
		
		factory.checkFlyweight();
	}
}

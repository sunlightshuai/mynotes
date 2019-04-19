package com.design.factory.simple;

public class DisguiseTest {

	public static void main(String[] args) {
		Fruit apple = Creator.factory(Apple.class);
		apple.grow();
	}
}

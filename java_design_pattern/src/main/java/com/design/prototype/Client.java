package com.design.prototype;

public class Client {

	public static void main(String[] args) {
		SpecificPrototype prototype = new SpecificPrototype();
		prototype.setName("sunli");
		Book one = new Book();
		one.setName("Java设计模式");
		prototype.setBook(one);
		
		SpecificPrototype a = (SpecificPrototype) prototype.clone();
		a.getBook().setName("C语言设计");
		a.setName("yang");
		System.out.println(prototype);
		System.out.println(a);
		
	}
}

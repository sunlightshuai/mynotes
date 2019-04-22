package com.design.observer;

public class Client {
	
	public static void main(String[] args) {
		
		
		Theme sub = new SubTheme();
		Observer ob1 = new SubObserver("警察A","张三");
		Observer ob2 = new SubObserver("警察B","张三");
		sub.register(ob1);
		sub.register(ob2);
		
		sub.notifyObservers();
	}
}

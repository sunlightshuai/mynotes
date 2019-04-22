package com.design.chain;

public class Client {
	
	public static void main(String[] args) {
		Handler myOne = new SubHandler();
		Handler myTwo = new SubHandler();
		
		myOne.setMyHandler(myTwo);
		myOne.handlerRequest();
	}
}

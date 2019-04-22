package com.design.observer;

public class SubObserver implements Observer {
	
	private String name;
	
	private String message;
	
	public SubObserver(String name,String message){
		this.name = name;
		this.message = message;
	}

	@Override
	public void update() {
		read();
	}
	
	public void read(){
		System.out.println(name + " 发现   " + message);
	}
	
}

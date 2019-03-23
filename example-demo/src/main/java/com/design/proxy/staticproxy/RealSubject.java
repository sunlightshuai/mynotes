package com.design.proxy.staticproxy;

public class RealSubject extends Subject{

	@Override
	public void request() {
		System.out.println("this is real request ...");
	}

}

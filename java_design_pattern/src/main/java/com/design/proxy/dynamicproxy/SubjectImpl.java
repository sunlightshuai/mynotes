package com.design.proxy.dynamicproxy;

public class SubjectImpl implements ISubject{
	public void sayHello(String a){
		System.out.println("hello "+a);
	}
}

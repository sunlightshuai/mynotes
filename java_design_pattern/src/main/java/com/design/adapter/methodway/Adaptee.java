package com.design.adapter.methodway;


/**
 * 原有的类的功能
 * @author sunli
 *
 */
public class Adaptee {

	public void sayHello(){
		System.out.println(Adaptee.class.getSimpleName()+" say hello");
	}
	
}

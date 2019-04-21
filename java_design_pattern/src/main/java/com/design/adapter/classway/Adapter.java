package com.design.adapter.classway;


/**
 * 原有功能的实现类
 * @author sunli
 *
 */
public class Adapter extends Adaptee implements Target{

	public void sayAge() {
		System.out.println("age is 18");
	}

}

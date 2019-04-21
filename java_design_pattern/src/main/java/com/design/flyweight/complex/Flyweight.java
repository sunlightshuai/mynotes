package com.design.flyweight.complex;

/**
 * 抽象享元角色
 * @author sunli
 *
 */
public abstract class Flyweight {

	/**
	 * 
	 * @param state 外蕴状态
	 */
	public abstract void operation(String state);
}

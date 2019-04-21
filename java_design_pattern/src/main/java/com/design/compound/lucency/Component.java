package com.design.compound.lucency;

import java.util.Enumeration;

/**
 * 透明合成模式
 * 抽象的构建角色
 * @author sunli
 *
 */
public interface Component {

	/**
	 * 全部的角色
	 * @return
	 */
	Composite getComposite();
	
	/**
	 * 定义共有的通用方法
	 */
	void sampleOperation();
	
	/**
	 * 增加一个对象
	 * @param component
	 */
	void add(Component component);
	
	/**
	 * 移除一个对象
	 * @param component
	 */
	void remove(Component component);
	
	/**
	 * 获得全部对象
	 * @return
	 */
	Enumeration<Component> components();
}

package com.design.compound.safe;

/**
 * 安全的合成模式
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
}

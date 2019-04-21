package com.design.decorator;

/**
 * 实现装饰的顶层设计
 * 具体的构件
 * @author sunli
 *
 */
public class ConcreteComponent implements Component{
	
	public ConcreteComponent(){
		
	}

	@Override
	public void sampleOperation() {
		System.out.println(this.getClass().getSimpleName()+"' method sampleOperation");
	}

}

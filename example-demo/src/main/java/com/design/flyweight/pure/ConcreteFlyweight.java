package com.design.flyweight.pure;

/**
 * 具体的享元角色
 * @author sunli
 *
 */
public class ConcreteFlyweight extends Flyweight {
	
	private Character character;
	
	/**
	 * 
	 * @param character 内蕴状态
	 */
	public ConcreteFlyweight(Character character){
		this.character = character;
	}

	/**
	 * 外蕴状态作为作为入参，改变对象的行为
	 * 但是不改变内蕴状态
	 */
	public void operation(String state) {
		System.out.println("character="+character+",state="+state);
	}

}

package com.design.flyweight.complex;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 享元工厂
 * @author sunli
 *
 */
public class FlyweightFactory {

	private Map<Character,Flyweight> files = new HashMap<Character,Flyweight>();
	
	public FlyweightFactory(){}
	
	public Flyweight factory(Character state){
		if (files.containsKey(state)){
			return files.get(state);
		} else {
			Flyweight fly = new ConcreteFlyweight(state);
			files.put(state, fly);
			return fly;
		}
	}
	
	public Flyweight factory(String compositeState){
		ContreteCompositeFlyweight flyweight = new ContreteCompositeFlyweight();
		Character state = null;
		for (int i=0;i<compositeState.length();i++){
			state = new Character(compositeState.charAt(i));
			flyweight.add(state, this.factory(state));
		}
		return flyweight;
	}
	
	public void checkFlyweight(){
		System.out.println("\ncheckFlyweight()---------------");
		for ( Entry<Character, Flyweight> entry :files.entrySet()){
			System.out.println("character="+entry.getKey()+",Flyweight="+entry.getValue());
		}
		System.out.println("checkFlyweight()---------------");
	}
}

package com.design.flyweight.complex;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ContreteCompositeFlyweight extends Flyweight {
	
	private Map<Character,Flyweight> files = new HashMap<Character,Flyweight>(10);
	
	private Flyweight flyweight;
	
	public ContreteCompositeFlyweight(){}
	
	public void add(Character key,Flyweight fly){
		files.put(key, fly);
	}

	@Override
	public void operation(String state) {
		for ( Entry<Character, Flyweight> entry :files.entrySet()){
			flyweight = entry.getValue();
			flyweight.operation(state);
		}
	}

}

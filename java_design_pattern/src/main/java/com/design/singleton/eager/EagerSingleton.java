package com.design.singleton.eager;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class EagerSingleton implements Serializable{

	private volatile static EagerSingleton eager = new EagerSingleton();
	
	private EagerSingleton(){
	}
	
	public static EagerSingleton getInstance(){
		return eager;
	}
	
	private Object readResolve() throws ObjectStreamException {    
        return eager;
    } 
}

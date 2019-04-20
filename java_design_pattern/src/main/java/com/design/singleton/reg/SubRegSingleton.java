package com.design.singleton.reg;

import java.io.ObjectStreamException;

public class SubRegSingleton extends RegSingleton {
	
	public SubRegSingleton(){}
	
	private static SubRegSingleton reg = (SubRegSingleton) RegSingleton.getInstance(SubRegSingleton.class.getName());

	public static SubRegSingleton getInstance(){
		return reg;
	}
	
	private Object readResolve() throws ObjectStreamException {    
        return reg;
    }
	
}

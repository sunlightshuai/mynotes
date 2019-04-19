package com.design.singleton.lazy;

public class LazySingleton {

	private static LazySingleton lazy = null;
	
	private LazySingleton(){}
	
	public synchronized static LazySingleton getInstance(){
		if (null == lazy){
			lazy = new LazySingleton();
		}
		return lazy;
	}
}

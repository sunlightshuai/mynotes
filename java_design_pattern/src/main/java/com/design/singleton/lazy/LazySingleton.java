package com.design.singleton.lazy;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class LazySingleton implements Serializable{

	private static LazySingleton lazy = null;
	
	private volatile static boolean flag = true;
	
	private LazySingleton(){
		if (flag) {  
            throw new RuntimeException("单例模式被侵犯！");  
        }
		flag = true;
	}
	
	public synchronized static LazySingleton getInstance(){
		if (null == lazy){
			lazy = new LazySingleton();
		}
		return lazy;
	}
	
	private Object readResolve() throws ObjectStreamException {    
        return lazy;
    }
}

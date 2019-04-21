package com.design.singleton.eager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Test {

	public static void main(String[] args) {
		
		/*for (int i=0;i<100;i++){
			new Thread(()->{
				EagerSingleton one = EagerSingleton.getInstance();
				EagerSingleton two = EagerSingleton.getInstance();
				if (one.equals(two)){
					System.out.println(true);
				} else {
					System.out.println(false);
				}
			}).start();;
		}*/
	    try {
	    	 Class<EagerSingleton> clazz = EagerSingleton.class; 
	    	 Constructor<EagerSingleton> c = clazz.getDeclaredConstructor(null);
		     c.setAccessible(true); // 跳过权限检查 
		     EagerSingleton sc3 = c.newInstance();
		     System.out.println(sc3);
	     }catch(Exception e){
	    	 e.printStackTrace();
	     }
	    
	}
}

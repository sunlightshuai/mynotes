package com.design.singleton.lazy;

public class Test {

	public static void main(String[] args) {
		for (int i=0;i<100;i++){
			new Thread(()->{
				LazySingleton one = LazySingleton.getInstance();
				LazySingleton two = LazySingleton.getInstance();
				if (one.equals(two)){
					System.out.println(true);
				} else {
					System.out.println(false);
				}
				
			}
			).start();;
			
		}
	}
}

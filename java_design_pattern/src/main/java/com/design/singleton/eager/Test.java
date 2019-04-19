package com.design.singleton.eager;

public class Test {

	public static void main(String[] args) {
		
		for (int i=0;i<100;i++){
			new Thread(()->{
				EagerSingleton one = EagerSingleton.getInstance();
				EagerSingleton two = EagerSingleton.getInstance();
				if (one.equals(two)){
					System.out.println(true);
				} else {
					System.out.println(false);
				}
			}).start();;
		}
	}
}

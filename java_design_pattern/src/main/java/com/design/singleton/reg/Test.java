package com.design.singleton.reg;

public class Test {

	public static void main(String[] args) {

		for (int i=0;i<100;i++){
			new Thread(()->{
				SubRegSingleton one = SubRegSingleton.getInstance();
				SubRegSingleton two = SubRegSingleton.getInstance();
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

package com.thread.visibility;

/**
 * 并发的可见性问题
 * @author sunli
 *
 */
public class VisibilityDemo {

	private static boolean flag = true;
	// private volatile static boolean flag = true;
	
	public static void main(String[] args) {
		
		for(int i=0;i<100;i++){
			Thread td = new Thread(new Runnable() {
				@Override
				public void run() {
					while(flag){
					}
				}
			});
			td.start();
		}
		flag = false;
	}
}

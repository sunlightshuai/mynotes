package com.compile.demo;

/**
 * 使用javap -verbose TryFinallyDemo.class查看不同
 * @author sunli
 *
 */
public class TryFinallyDemo {
	
	public static void main(String[] args) {
		String main = "123";
		System.out.println("test(main)'s result="+test(main));
		System.out.println("a="+main);
	}
	
	public static String test(String param){
		String a = param;
		a = "hello";
		try{
			return a;
		} finally {
			a = "thank you xiaoyang";
			System.out.println("test:a="+a);
		}
		
	}
}

package com.compile.demo;

public class TryFinallyDemo {
	public static void main(String[] args) {
		// System.out.println(test());
	}
	
	public static String test(){
		String a = "hello World";
		try{
			return a;
		} finally{
			a = "thank you xiaoyang";
		}
		
	}
}

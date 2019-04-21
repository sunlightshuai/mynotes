package com.design.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy implements InvocationHandler{
	
	private Object proxyObj;
	
	public Object getProxyObj() {
		return proxyObj;
	}

	public MyProxy(){}
	
	public MyProxy(Object obj){
		proxyObj= obj;
	}
	
	
	public static Object getObj(Object obj){
		Class<? extends Object> cls = obj.getClass();
		return Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), new MyProxy(obj));
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before calling "+method);
		Object o = method.invoke(proxyObj, args);
		System.out.println("after calling "+method);
		return o;
	}
	
	public static void main(String[] args) {
		ISubject subject = new SubjectImpl();
		ISubject mySub = (ISubject)getObj(subject);
		mySub.sayHello("Yang");
	}
	
}

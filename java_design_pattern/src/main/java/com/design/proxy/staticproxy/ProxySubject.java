package com.design.proxy.staticproxy;

public class ProxySubject extends Subject{
	
	private RealSubject realSubject;

	@Override
	public void request() {
		
		preRequest();
		
		if (null == realSubject){
			realSubject = new RealSubject();
		}
		
		realSubject.request();
		
		postRequest();
		
	}
	
	private void preRequest(){
		
	}
	
	private void postRequest(){
		
	}

}

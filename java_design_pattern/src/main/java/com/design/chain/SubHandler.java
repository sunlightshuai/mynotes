package com.design.chain;

public class SubHandler extends Handler {

	@Override
	public void handlerRequest() {
		if (null != getMyHandler()){
			System.out.println("请求是："+getMyHandler());
		}
	}

}

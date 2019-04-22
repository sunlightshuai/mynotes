package com.design.chain;

public abstract class Handler {

	public Handler myHandler;
	
	public Handler getMyHandler() {
		return myHandler;
	}

	public void setMyHandler(Handler myHandler) {
		this.myHandler = myHandler;
	}

	public abstract void handlerRequest();
	
}

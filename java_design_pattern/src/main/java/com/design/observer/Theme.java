package com.design.observer;

public interface Theme {
	
	
	/**
	 * 注册观察者
	 * @param observer
	 */
	public void register(Observer observer);
	
	/**
	 * 移除观察者
	 * @param observer
	 */
	public void remove(Observer observer);
	
	/**
	 * 通知观察者
	 */
	public void notifyObservers();
}

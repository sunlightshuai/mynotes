package com.design.observer;

import java.util.ArrayList;
import java.util.List;

public class SubTheme implements Theme {
	
	private List<Observer> observers = new ArrayList<Observer>();
	
	@Override
	public void register(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void remove(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers){
			observer.update();
		}
	}
	
}

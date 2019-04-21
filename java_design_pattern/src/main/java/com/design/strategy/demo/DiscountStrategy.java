package com.design.strategy.demo;

public abstract class DiscountStrategy {

	private int price = 0;
	
	private int copies = 0;
	
	public DiscountStrategy(int price,int copies){
		this.price = price;
		this.copies = copies;
	}
	
	public abstract int calcDiscount();
}

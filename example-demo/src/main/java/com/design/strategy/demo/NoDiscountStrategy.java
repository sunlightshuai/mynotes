package com.design.strategy.demo;

public class NoDiscountStrategy extends DiscountStrategy {

	public NoDiscountStrategy(int price, int copies) {
		super(price, copies);
	}

	private int price = 0;
	
	private int copies = 0;
	
	@Override
	public int calcDiscount() {
		return 0;
	}
	
}

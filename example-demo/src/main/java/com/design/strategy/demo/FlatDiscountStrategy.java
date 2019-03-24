package com.design.strategy.demo;

public class FlatDiscountStrategy extends DiscountStrategy {

	public FlatDiscountStrategy(int price, int copies) {
		super(price, copies);
	}

	private int price = 0;
	
	private int copies = 0;
	
	private int amount = 0;
	
	public int getAmount(){
		return amount;
	}
	
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	@Override
	public int calcDiscount() {
		return amount * copies;
	}
	
}

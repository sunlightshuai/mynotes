package com.design.strategy.demo;

public class PercentageDiscountStrategy extends DiscountStrategy {

	public PercentageDiscountStrategy(int price, int copies) {
		super(price, copies);
	}

	private int price = 0;
	
	private int copies = 0;
	
	private int percent = 0;
	
	public int getPercent(){
		return percent;
	}
	
	public void setPercent(int percent){
		this.percent = percent;
	}
	
	@Override
	public int calcDiscount() {
		return percent * copies * price;
	}
	
}

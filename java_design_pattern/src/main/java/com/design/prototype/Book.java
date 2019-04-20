package com.design.prototype;

public class Book implements Prototype {

	private String name;

	@Override
	public String toString() {
		return "Book [name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Object clone(){
		try {
			Book book = (Book)super.clone();
			return book;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package com.design.prototype;

public class SpecificPrototype implements Prototype{
	
	private String name;
	
	private Book book;

	@Override
	public String toString() {
		return "SpecificPrototype [name=" + name + ", book=" + book + "]";
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object clone(){
		try {
			SpecificPrototype prototype = (SpecificPrototype)super.clone();
			prototype.setBook((Book)prototype.getBook().clone());
			return prototype;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package com.design.compound.lucency;

import java.util.Enumeration;

/**
 * 部分角色
 * @author sunli
 *
 */
public class Leaf implements Component{
	
	private Composite composite = new Composite();

	@Override
	public Composite getComposite() {
		return composite;
	}

	@Override
	public void sampleOperation() {
		System.out.println(this.getClass().getSimpleName()+"sampleOperation()");
	}

	@Override
	public void add(Component component) {
		composite.add(component);
	}

	@Override
	public void remove(Component component) {
		composite.remove(component);
	}

	@Override
	public Enumeration<Component> components() {
		return composite.components();
	}
	
}

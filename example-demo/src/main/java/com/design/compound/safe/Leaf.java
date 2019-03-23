package com.design.compound.safe;

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
	
}

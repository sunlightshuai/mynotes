package com.design.compound.lucency;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 定义的全部行为的对象角色
 * @author sunli
 *
 */
public class Composite implements Component {
	
	private Vector<Component> componentVector = new Vector<Component>();

	@Override
	public Composite getComposite() {
		return this;
	}

	@Override
	public void sampleOperation() {
		Enumeration<Component> enumeration = components();
		while(enumeration.hasMoreElements()){
			enumeration.nextElement().sampleOperation();
		}
	}
	
	public void add(Component component){
		componentVector.addElement(component);
	}
	
	public void remove(Component component){
		componentVector.removeElement(component);
	}
	
	public Enumeration<Component> components(){
		return componentVector.elements();
	}
	

}

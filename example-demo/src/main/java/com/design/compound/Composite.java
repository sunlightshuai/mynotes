package com.design.compound;

import java.util.Enumeration;
import java.util.Vector;

public class Composite implements Component {
	
	private Vector componentVector = new Vector();

	@Override
	public Composite getComposite() {
		return this;
	}

	@Override
	public void sampleOperation() {
		Enumeration enumeration = components();
		while(enumeration.hasMoreElements()){
			((Component)enumeration.nextElement()).sampleOperation();
		}
	}
	
	public void add(Component component){
		componentVector.addElement(component);
	}
	
	public void remove(Component component){
		componentVector.removeElement(component);
	}
	
	public Enumeration components(){
		return componentVector.elements();
	}
	

}

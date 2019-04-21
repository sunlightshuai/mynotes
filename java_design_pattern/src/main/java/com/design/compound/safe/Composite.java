package com.design.compound.safe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 定义的全部行为的对象角色
 * @author sunli
 *
 */
public class Composite implements Component {
	
	private List<Component> componentList = new ArrayList<Component>();

	@Override
	public Composite getComposite() {
		return this;
	}

	@Override
	public void sampleOperation() {
		List<Component> enumeration = components();
		Iterator<Component> iterator = enumeration.iterator();
		while(iterator.hasNext()){
			iterator.next().sampleOperation();
		}
	}
	
	/**
	 * 增加一个对象
	 * @param component
	 */
	public void add(Component component){
		componentList.add(component);
	}
	
	/**
	 * 移除一个对象
	 * @param component
	 */
	public void remove(Component component){
		componentList.remove(component);
	}
	
	/**
	 * 获取一个全部对象
	 * @return
	 */
	public List<Component> components(){
		return componentList;
	}
	

}

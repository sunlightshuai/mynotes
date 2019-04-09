package com.sunli.resource.service.model;


import java.util.List;

public class AttrParamModel extends ParseModel{
	
	private static final long serialVersionUID = 3458223041313143794L;

	private String name;
	
	private String type;
	
	private String mode;
	
	private String optional;
	
	private String title;
	
	private String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private List<AttrParamModel> paramOutputModel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getOptional() {
		return optional;
	}

	public void setOptional(String optional) {
		this.optional = optional;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

	public List<AttrParamModel> getParamOutputModel() {
		return paramOutputModel;
	}

	public void setParamOutputModel(List<AttrParamModel> paramOutputModel) {
		this.paramOutputModel = paramOutputModel;
	}

	@Override
	public String toString() {
		return "ParamOutputModel [name=" + name + ", type=" + type + ", mode=" + mode + ", optional=" + optional
				+ ", title=" + title + ", paramOutputModel=" + paramOutputModel + "]";
	}

}

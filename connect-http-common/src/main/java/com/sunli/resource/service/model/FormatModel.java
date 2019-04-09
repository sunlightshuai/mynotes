package com.sunli.resource.service.model;



import java.util.List;

public class FormatModel extends ParseModel{
	
	private static final long serialVersionUID = 5302212465567651921L;

	private String name;
	
	private String engine;
	
	private String export;
	
	private String location;
	
	private String invoke;
	
	private String title;
	
	private String validate;
	
	private List<AttrParamModel> attrParamModel;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getExport() {
		return export;
	}

	public void setExport(String export) {
		this.export = export;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getInvoke() {
		return invoke;
	}

	public void setInvoke(String invoke) {
		this.invoke = invoke;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public List<AttrParamModel> getAttrParamModel() {
		return attrParamModel;
	}

	public void setAttrParamModel(List<AttrParamModel> attrParamModel) {
		this.attrParamModel = attrParamModel;
	}

	@Override
	public String toString() {
		return "FormatModel [name=" + name + ", engine=" + engine + ", export=" + export + ", location=" + location
				+ ", invoke=" + invoke + ", title=" + title + ", validate=" + validate + ", attrParamModel="
				+ attrParamModel + "]";
	}


}

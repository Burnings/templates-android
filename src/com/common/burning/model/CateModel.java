package com.common.burning.model;

import java.util.List;

 public class CateModel extends BaseModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8046126747551942825L;
	private String name;
	private String id;
	private String maincate;
	private String tagcolor;
	private String icon;
	private List<CateModel>subcate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMaincate() {
		return maincate;
	}
	public void setMaincate(String maincate) {
		this.maincate = maincate;
	}
	public String getTagcolor() {
		return tagcolor;
	}
	public void setTagcolor(String tagcolor) {
		this.tagcolor = tagcolor;
	}
	public List<CateModel> getSubcate() {
		return subcate;
	}
	public void setSubcate(List<CateModel> subcate) {
		this.subcate = subcate;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}

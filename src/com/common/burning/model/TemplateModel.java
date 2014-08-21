package com.common.burning.model;

import java.util.List;
import java.util.Map;

public class TemplateModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2305455179381852464L;
	private String name;
	private String id;
	private String prefix;
	private String datatable;
	
	private List<FieldModel> fields;
	private List<CateModel> cate;
	private List<Map<String, String>> mapList;
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
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getDatatable() {
		return datatable;
	}
	public void setDatatable(String datatable) {
		this.datatable = datatable;
	}
	public List<FieldModel> getFields() {
		return fields;
	}
	public void setFields(List<FieldModel> fields) {
		this.fields = fields;
	}
	public List<CateModel> getCate() {
		return cate;
	}
	public void setCate(List<CateModel> cate) {
		this.cate = cate;
	}
	public List<Map<String, String>> getMapList() {
		return mapList;
	}
	public void setMapList(List<Map<String, String>> mapList) {
		this.mapList = mapList;
	}
}

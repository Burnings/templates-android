package com.common.burning.model;



public class FieldModel extends BaseModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4823377427493061346L;
	private String field;
	private String name;
	private String fieldtype;
	private String minlen;
	private String maxlen;
	private String placeholder;
	private String required;
	private String showtag;
	private String valuetype;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFieldtype() {
		return fieldtype;
	}
	public void setFieldtype(String fieldtype) {
		this.fieldtype = fieldtype;
	}
	public String getMinlen() {
		return minlen;
	}
	public void setMinlen(String minlen) {
		this.minlen = minlen;
	}
	public String getMaxlen() {
		return maxlen;
	}
	public void setMaxlen(String maxlen) {
		this.maxlen = maxlen;
	}
	public String getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	public String getRequired() {
		return required;
	}
	public void setRequired(String required) {
		this.required = required;
	}
	public String getShowtag() {
		return showtag;
	}
	public void setShowtag(String showtag) {
		this.showtag = showtag;
	}
	public String getValuetype() {
		return valuetype;
	}
	public void setValuetype(String valuetype) {
		this.valuetype = valuetype;
	}
}


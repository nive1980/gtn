package com.gtn.dto;

import java.io.Serializable;

public class ValidationErrorDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7629529752643972134L;
	private String msg;
	private String url;
	private String field;
	private String section;
	private Integer itemId;
	private Integer cartonId;
	
	public ValidationErrorDto(){
	}
	
	public ValidationErrorDto(String msg, String url, String field, String section, Integer itemId, Integer cartonId){
		this.msg = msg;
		this.url = url;
		this.field = field;
		this.section = section;
		this.itemId = itemId;
		this.cartonId = cartonId;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getCartonId() {
		return cartonId;
	}

	public void setCartonId(Integer cartonId) {
		this.cartonId = cartonId;
	}

	@Override
	public String toString() {
		return "ValidationErrorDto [msg=" + msg + ", url=" + url + ", field=" + field + ", section=" + section
				+ ", itemId=" + itemId + ", cartonId=" + cartonId + "]";
	}

	
}

package com.gtn.dto;

public class SbuConfigView {

	private String sbu;
	private String paramName;
	private String paramValue;
	private String paramDescription;
	private String reqType;
	
	public String getSbu() {
		return sbu;
	}
	public void setSbu(String sbu) {
		this.sbu = sbu;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getParamDescription() {
		return paramDescription;
	}
	public void setParamDescription(String paramDescription) {
		this.paramDescription = paramDescription;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	
}

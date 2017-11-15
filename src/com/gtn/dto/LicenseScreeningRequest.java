package com.gtn.dto;

public class LicenseScreeningRequest {

	private String ctrlCtry;
	private String expCtry;
	private String prodNo;
	private String userName;
	private String deviceType;
	private String deviceToken;
	
	
	public String getCtrlCtry() {
		return ctrlCtry;
	}
	public void setCtrlCtry(String ctrlCtry) {
		this.ctrlCtry = ctrlCtry;
	}
	public String getExpCtry() {
		return expCtry;
	}
	public void setExpCtry(String expCtry) {
		this.expCtry = expCtry;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	
	
}

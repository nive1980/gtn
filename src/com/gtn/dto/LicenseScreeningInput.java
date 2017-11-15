package com.gtn.dto;

public class LicenseScreeningInput {

	private String type;
	private String input;
	private String expCountry;
	private String impCountry;
	
	private String expCountryName;
	private String impCountryName;
	
	private String partNo;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getExpCountry() {
		return expCountry;
	}
	public void setExpCountry(String expCountry) {
		this.expCountry = expCountry;
	}
	public String getImpCountry() {
		return impCountry;
	}
	public void setImpCountry(String impCountry) {
		this.impCountry = impCountry;
	}
	
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
		
	public String getExpCountryName() {
		return expCountryName;
	}
	public void setExpCountryName(String expCountryName) {
		this.expCountryName = expCountryName;
	}
	public String getImpCountryName() {
		return impCountryName;
	}
	public void setImpCountryName(String impCountryName) {
		this.impCountryName = impCountryName;
	}
	@Override
	public String toString() {
		return "LicenseScreeningInput [type=" + type + ", input=" + input + ", expCountry=" + expCountry
				+ ", impCountry=" + impCountry + "]";
	}
	
	
	
}

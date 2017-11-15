package com.gtn.dto;

public class ProductLSResponseDto {

	private ProductLSResponse response;
	private LicenseScreeningInput input;
	private String status;
	public ProductLSResponse getResponse() {
		return response;
	}
	public void setResponse(ProductLSResponse response) {
		this.response = response;
	}
	public LicenseScreeningInput getInput() {
		return input;
	}
	public void setInput(LicenseScreeningInput input) {
		this.input = input;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}

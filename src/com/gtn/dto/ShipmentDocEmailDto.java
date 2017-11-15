package com.gtn.dto;

import java.util.List;

public class ShipmentDocEmailDto {

	private String to;
	private String subject;
	private String message;
	private Integer shipId;
	private String shipmentNo;
	private String docType;
	
	private String errorMsr;
	private List<String> validationErrors;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getShipId() {
		return shipId;
	}
	public void setShipId(Integer shipId) {
		this.shipId = shipId;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getErrorMsr() {
		return errorMsr;
	}
	public void setErrorMsr(String errorMsr) {
		this.errorMsr = errorMsr;
	}
	public List<String> getValidationErrors() {
		return validationErrors;
	}
	public void setValidationErrors(List<String> validationErrors) {
		this.validationErrors = validationErrors;
	}
	public String getShipmentNo() {
		return shipmentNo;
	}
	public void setShipmentNo(String shipmentNo) {
		this.shipmentNo = shipmentNo;
	}
	
	
}

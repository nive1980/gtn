package com.gtn.dto;

import java.util.List;

public class LicenseScreeningResponse {

	private String status;
	private String regDate;
	private int resultSize;
	private List<LicenseScreeningResultList> eldsHostedServiceView;
	private String message;
	private String reportDate;
	
	private LicenseScreeningInput inp;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getResultSize() {
		return resultSize;
	}
	public void setResultSize(int resultSize) {
		this.resultSize = resultSize;
	}
	
	public List<LicenseScreeningResultList> getEldsHostedServiceView() {
		return eldsHostedServiceView;
	}
	public void setEldsHostedServiceView(List<LicenseScreeningResultList> eldsHostedServiceView) {
		this.eldsHostedServiceView = eldsHostedServiceView;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LicenseScreeningInput getInp() {
		return inp;
	}
	public void setInp(LicenseScreeningInput inp) {
		this.inp = inp;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	
}

package com.gtn.dto;

import java.util.List;

public class LicenseScreeningCollect {
	
	private String description;
	private String reasonForControl;
	private String eccnNo;
	private String subEccnNo;
	private String licAgency;
	private String report;
	private String licRquire;
	private String reportReq;
	private String cclLicExcep;
	private String exceptions;
	private String licExcepUsed;	
	private String productNo;
	private String addtlReq;
	
	private String basicLic;
	private List<LicenseScreeningReportResult> reportResult;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReasonForControl() {
		return reasonForControl;
	}
	public void setReasonForControl(String reasonForControl) {
		this.reasonForControl = reasonForControl;
	}
	public String getEccnNo() {
		return eccnNo;
	}
	public void setEccnNo(String eccnNo) {
		this.eccnNo = eccnNo;
	}
	public String getSubEccnNo() {
		return subEccnNo;
	}
	public void setSubEccnNo(String subEccnNo) {
		this.subEccnNo = subEccnNo;
	}
	public String getLicAgency() {
		return licAgency;
	}
	public void setLicAgency(String licAgency) {
		this.licAgency = licAgency;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getLicRquire() {
		return licRquire;
	}
	public void setLicRquire(String licRquire) {
		this.licRquire = licRquire;
	}
	public String getReportReq() {
		return reportReq;
	}
	public void setReportReq(String reportReq) {
		this.reportReq = reportReq;
	}
	public String getCclLicExcep() {
		return cclLicExcep;
	}
	public void setCclLicExcep(String cclLicExcep) {
		this.cclLicExcep = cclLicExcep;
	}
	public String getExceptions() {
		return exceptions;
	}
	public void setExceptions(String exceptions) {
		this.exceptions = exceptions;
	}
	public String getLicExcepUsed() {
		return licExcepUsed;
	}
	public void setLicExcepUsed(String licExcepUsed) {
		this.licExcepUsed = licExcepUsed;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getBasicLic() {
		return basicLic;
	}
	public void setBasicLic(String basicLic) {
		this.basicLic = basicLic;
	}
	public List<LicenseScreeningReportResult> getReportResult() {
		return reportResult;
	}
	public void setReportResult(List<LicenseScreeningReportResult> reportResult) {
		this.reportResult = reportResult;
	}
	public String getAddtlReq() {
		return addtlReq;
	}
	public void setAddtlReq(String addtlReq) {
		this.addtlReq = addtlReq;
	}
	
	
	
}

package com.gtn.dto;

import java.util.List;

public class LicenseScreeningResultList {

	private String category;
	private String lvsLimit;
	private int resultSize;
	
	private String licExcep;
	private String reportReq;
	private String salesOrderNo;
	private String prodNo;
	private String subProd;
	private String itarCtrl;
	private String nrcCtrl;
	private String expCtry;
	private String licReq;
	private String ctrlCtry;
	private List<LicenseScreeningCollect> eldsSummaryViews;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLvsLimit() {
		return lvsLimit;
	}
	public void setLvsLimit(String lvsLimit) {
		this.lvsLimit = lvsLimit;
	}
	public int getResultSize() {
		return resultSize;
	}
	public void setResultSize(int resultSize) {
		this.resultSize = resultSize;
	}
	public String getLicExcep() {
		return licExcep;
	}
	public void setLicExcep(String licExcep) {
		this.licExcep = licExcep;
	}
	public String getReportReq() {
		return reportReq;
	}
	public void setReportReq(String reportReq) {
		this.reportReq = reportReq;
	}
	public String getSalesOrderNo() {
		return salesOrderNo;
	}
	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public String getSubProd() {
		return subProd;
	}
	public void setSubProd(String subProd) {
		this.subProd = subProd;
	}
	public String getItarCtrl() {
		return itarCtrl;
	}
	public void setItarCtrl(String itarCtrl) {
		this.itarCtrl = itarCtrl;
	}
	public String getNrcCtrl() {
		return nrcCtrl;
	}
	public void setNrcCtrl(String nrcCtrl) {
		this.nrcCtrl = nrcCtrl;
	}
	public String getExpCtry() {
		return expCtry;
	}
	public void setExpCtry(String expCtry) {
		this.expCtry = expCtry;
	}
	public String getLicReq() {
		return licReq;
	}
	public void setLicReq(String licReq) {
		this.licReq = licReq;
	}
	public String getCtrlCtry() {
		return ctrlCtry;
	}
	public void setCtrlCtry(String ctrlCtry) {
		this.ctrlCtry = ctrlCtry;
	}
	public List<LicenseScreeningCollect> getEldsSummaryViews() {
		return eldsSummaryViews;
	}
	public void setEldsSummaryViews(List<LicenseScreeningCollect> eldsSummaryViews) {
		this.eldsSummaryViews = eldsSummaryViews;
	}
	
	
	
}

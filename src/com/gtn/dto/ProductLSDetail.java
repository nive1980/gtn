package com.gtn.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;



/**
 * @Project : EJBX_WEBSERVICES:LicenseScreeningDetail.java
 * @DateTime: 2:22 PM 6/14/2017
 * @author Sunil Kumar
 */
@XmlRootElement(name = "PRODUCT_LS_DETAIL")
@XmlType(name = "", propOrder = { "level", "partNo", "description", "countryOfClassification",
		"countryCodeOfClassification", "exportClass", "productFlag", "screeningResult", "availableLicenses",
		"productFlagColor", "countryOfImport", "countryOfImportCode", "parentPartNo", "manufacturerName", "serialNo",
		"shippedFromStatus", "shippedToStatus", "reportReq" })
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductLSDetail {
	@JsonProperty("LEVEL")
	private Long level;
	@JsonProperty("PART_NO")
	private String partNo;
	@JsonProperty("DESCRIPTION")
	private String description;
	@JsonProperty("COUNTRY_OF_CLASSIFICATION")
	private String countryOfClassification;
	@JsonProperty("COUNTRY_CODE_OF_CLASSIFICATION")
	private String countryCodeOfClassification;
	@JsonProperty("EXPORT_CLASS")
	private String exportClass;
	@JsonProperty("PRODUCT_FLAG")
	private String productFlag;
	@JsonProperty("SCREENING_RESULT")
	private String screeningResult;
	@JsonProperty("AVAILABLE_LICENSES")
	private String availableLicenses;
	@JsonProperty("PRODUCT_FLAG_COLOR")
	private String productFlagColor;
	@JsonProperty("COUNTRY_OF_IMPORT")
	private String countryOfImport;
	@JsonProperty("COUNTRY_OF_IMPORT_CODE")
	private String countryOfImportCode;
	@JsonProperty("PARENT_PART_NO")
	private String parentPartNo;
	@JsonProperty("MANUFACTURER_NAME")
	private String manufacturerName;
	@JsonProperty("SERIAL_NO")
	private String serialNo;
	@JsonProperty("SHIPPED_FROM_STATUS")
	private String shippedFromStatus;
	@JsonProperty("SHIPPED_TO_STATUS")
	private String shippedToStatus;
	@JsonProperty("REPORT_REQ")
	private String reportReq;
	
	public Long getLevel() {
		return level;
	}

	@XmlElement(name = "LEVEL")
	public void setLevel(Long level) {
		this.level = level;
	}

	public String getPartNo() {
		return partNo;
	}

	@XmlElement(name = "PART_NO")
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement(name = "DESCRIPTION")
	public void setDescription(String description) {
		this.description = description;
	}

	public String getCountryOfClassification() {
		return countryOfClassification;
	}

	@XmlElement(name = "COUNTRY_OF_CLASSIFICATION")
	public void setCountryOfClassification(String countryOfClassification) {
		this.countryOfClassification = countryOfClassification;
	}

	public String getCountryCodeOfClassification() {
		return countryCodeOfClassification;
	}

	@XmlElement(name = "COUNTRY_CODE_OF_CLASSIFICATION")
	public void setCountryCodeOfClassification(String countryCodeOfClassification) {
		this.countryCodeOfClassification = countryCodeOfClassification;
	}

	public String getExportClass() {
		return exportClass;
	}

	@XmlElement(name = "EXPORT_CLASS")
	public void setExportClass(String exportClass) {
		this.exportClass = exportClass;
	}

	public String getProductFlag() {
		return productFlag;
	}

	@XmlElement(name = "PRODUCT_FLAG")
	public void setProductFlag(String productFlag) {
		this.productFlag = productFlag;
	}

	public String getScreeningResult() {
		return screeningResult;
	}

	@XmlElement(name = "SCREENING_RESULT")
	public void setScreeningResult(String screeningResult) {
		this.screeningResult = screeningResult;
	}

	public String getAvailableLicenses() {
		return availableLicenses;
	}

	@XmlElement(name = "AVAILABLE_LICENSES")
	public void setAvailableLicenses(String availableLicenses) {
		this.availableLicenses = availableLicenses;
	}

	public String getProductFlagColor() {
		return productFlagColor;
	}

	@XmlElement(name = "PRODUCT_FLAG_COLOR")
	public void setProductFlagColor(String productFlagColor) {
		this.productFlagColor = productFlagColor;
	}

	public String getCountryOfImport() {
		return countryOfImport;
	}

	@XmlElement(name = "COUNTRY_OF_IMPORT")
	public void setCountryOfImport(String countryOfImport) {
		this.countryOfImport = countryOfImport;
	}

	public String getCountryOfImportCode() {
		return countryOfImportCode;
	}

	@XmlElement(name = "COUNTRY_OF_IMPORT_CODE")
	public void setCountryOfImportCode(String countryOfImportCode) {
		this.countryOfImportCode = countryOfImportCode;
	}

	public String getParentPartNo() {
		return parentPartNo;
	}

	@XmlElement(name = "PARENT_PART_NO")
	public void setParentPartNo(String parentPartNo) {
		this.parentPartNo = parentPartNo;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	@XmlElement(name = "MANUFACTURER_NAME")
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getSerialNo() {
		return serialNo;
	}

	@XmlElement(name = "SERIAL_NO")
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getShippedFromStatus() {
		return shippedFromStatus;
	}

	@XmlElement(name = "SHIPPED_FROM_STATUS")
	public void setShippedFromStatus(String shippedFromStatus) {
		this.shippedFromStatus = shippedFromStatus;
	}

	public String getShippedToStatus() {
		return shippedToStatus;
	}

	@XmlElement(name = "SHIPPED_TO_STATUS")
	public void setShippedToStatus(String shippedToStatus) {
		this.shippedToStatus = shippedToStatus;
	}

	public String getReportReq() {
		return reportReq;
	}
	@XmlElement(name = "REPORT_REQ")
	public void setReportReq(String reportReq) {
		this.reportReq = reportReq;
	}

}

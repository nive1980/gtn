package com.gtn.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;



/**
 * @Project : EJBX_WEBSERVICES:ProductLSCriteria.java
 * @DateTime: 2:22 PM 6/14/2017
 * @author Sunil Kumar
 */
@XmlRootElement(name = "PRODUCT_LS_CRITERIA")
@XmlType(name = "", propOrder = { "partNumber", "eccnNumber", "exportCountryCode", "destinationCountryCode",
		"screenSubCompFlag", "userName", "activeSbu", "directUser", "referenceNo", "directUserSbu", "accountNumber",
		"persistAudit", "bomNumber" })
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductLSCriteria {

	/**
	 * Attribute partNumber
	 */
	@JsonProperty("PART_NUMBER")
	private String partNumber;

	/**
	 * Attribute eccnNumber
	 */
	@JsonProperty("ECCN_NUMBER")
	private String eccnNumber;

	/**
	 * Attribute exportCountryCode
	 */
	@JsonProperty("EXPORT_COUNTRY_CODE")
	private String exportCountryCode;

	/**
	 * Attribute destinationCountryCode
	 */
	@JsonProperty("DESTINATION_COUNTRY_CODE")
	private String destinationCountryCode;

	/**
	 * Attribute screenSubCompFlag
	 */
	@JsonProperty("SCREEN_SUB_COMP_FLAG")
	private Boolean screenSubCompFlag;

	/**
	 * Attribute userName
	 */
	@JsonProperty("USER_NAME")
	private String userName;

	/**
	 * Attribute activeSbu
	 */
	@JsonProperty("ACTIVE_SBU")
	private String activeSbu;

	/**
	 * Attribute directUser
	 */
	@JsonProperty("DIRECT_USER")
	private String directUser;

	/**
	 * Attribute referenceNo
	 */
	@JsonProperty("REFERENCE_NO")
	private String referenceNo;

	/**
	 * Attribute directUserSbu
	 */
	@JsonProperty("DIRECT_USER_SBU")
	private String directUserSbu;

	/**
	 * Attribute accountNumber
	 */
	@JsonProperty("ACCOUNT_NUMBER")
	private String accountNumber;

	/**
	 * Attribute accountNumber
	 */
	@JsonProperty("PERSIST_AUDIT")
	private Boolean persistAudit;

	/**
	 * Attribute bomNumber
	 */
	@JsonProperty("BOM_NUMBER")
	private String bomNumber;

	public String getPartNumber() {
		return partNumber;
	}

	@XmlElement(name = "PART_NUMBER")
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getEccnNumber() {
		return eccnNumber;
	}

	@XmlElement(name = "ECCN_NUMBER")
	public void setEccnNumber(String eccnNumber) {
		this.eccnNumber = eccnNumber;
	}

	public String getExportCountryCode() {
		return exportCountryCode;
	}

	@XmlElement(name = "EXPORT_COUNTRY_CODE")
	public void setExportCountryCode(String exportCountryCode) {
		this.exportCountryCode = exportCountryCode;
	}

	public String getDestinationCountryCode() {
		return destinationCountryCode;
	}

	@XmlElement(name = "DESTINATION_COUNTRY_CODE")
	public void setDestinationCountryCode(String destinationCountryCode) {
		this.destinationCountryCode = destinationCountryCode;
	}

	public Boolean getScreenSubCompFlag() {
		return screenSubCompFlag;
	}

	@XmlElement(name = "SCREEN_SUB_COMP_FLAG")
	public void setScreenSubCompFlag(Boolean screenSubCompFlag) {
		this.screenSubCompFlag = screenSubCompFlag;
	}

	public String getUserName() {
		return userName;
	}

	@XmlElement(name = "USER_NAME")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getActiveSbu() {
		return activeSbu;
	}

	@XmlElement(name = "ACTIVE_SBU")
	public void setActiveSbu(String activeSbu) {
		this.activeSbu = activeSbu;
	}

	public String getDirectUser() {
		return directUser;
	}

	@XmlElement(name = "DIRECT_USER")
	public void setDirectUser(String directUser) {
		this.directUser = directUser;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	@XmlElement(name = "REFERENCE_NO")
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getDirectUserSbu() {
		return directUserSbu;
	}

	@XmlElement(name = "DIRECT_USER_SBU")
	public void setDirectUserSbu(String directUserSbu) {
		this.directUserSbu = directUserSbu;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	@XmlElement(name = "ACCOUNT_NUMBER")
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Boolean getPersistAudit() {
		return persistAudit;
	}

	@XmlElement(name = "PERSIST_AUDIT")
	public void setPersistAudit(Boolean persistAudit) {
		this.persistAudit = persistAudit;
	}

	public String getBomNumber() {
		return bomNumber;
	}

	@XmlElement(name = "BOM_NUMBER")
	public void setBomNumber(String bomNumber) {
		this.bomNumber = bomNumber;
	}

}

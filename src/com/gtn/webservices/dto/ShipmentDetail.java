package com.gtn.webservices.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * PRJ#
 * 
 * @Project : EJBX_WEBSERVICES:ShipmentDetail.java
 * @DateTime: 3:04 PM 1/22/2017
 * @author Sunil Kumar
 */
@XmlRootElement(name = "SHIPMENT_DETAIL")
@XmlType(name = "", propOrder = { "shipmentNo", "shipmentStatus", "ultDestination", "countryShipFrom", "dateOfExpot",
		"shipmentWLS", "exporterWLS", "ultConsWLS", "intConsWLS", "ffAgentWLS", "manualHoldOnShipment", "itnNo", "xtn",
		"correctionCode", "shipmentCustomFilingStatus", "aesOption", "invoicesDetails" })
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ShipmentDetail {
	@JsonProperty("SHIPMENT_NO")
	private String shipmentNo;
	@JsonProperty("SHIPMENT_STATUS")
	private String shipmentStatus;
	@JsonProperty("ULT_DESTINATION")
	private String ultDestination;
	@JsonProperty("COUNTRY_SHIP_FROM")
	private String countryShipFrom;
	@JsonProperty("DATE_OF_EXPORT")
	private String dateOfExpot;
	@JsonProperty("SHIPMENT_WLS")
	private String shipmentWLS;
	@JsonProperty("EXPORTER_WLS")
	private String exporterWLS;
	@JsonProperty("ULT_CONS_WLS")
	private String ultConsWLS;
	@JsonProperty("INT_CONS_WLS")
	private String intConsWLS;
	@JsonProperty("FF_AGENT_WLS")
	private String ffAgentWLS;
	@JsonProperty("MANUAL_HOLD_ON_SHIPMENT")
	private String manualHoldOnShipment;		/* #7629: adding Manual Hold feature */
	
	
	@JsonProperty("ITN_NO")
	private String itnNo;
	@JsonProperty("XTN_NO")
	private String xtn;
	@JsonProperty("CORRECTION_CODE")
	private String correctionCode;
	
	@JsonProperty("SHIPMENT_CUSTOM_FILING_STATUS")
	private String shipmentCustomFilingStatus;
	@JsonProperty("AES_OPTION")
	private String aesOption;
		
	@JsonProperty("INVOICES_DETAILS")
	private InvoicesDetails invoicesDetails;

	public String getShipmentNo() {
		return shipmentNo;
	}

	@XmlElement(name = "SHIPMENT_NO")
	public void setShipmentNo(String shipmentNo) {
		this.shipmentNo = shipmentNo;
	}

	public String getShipmentStatus() {
		return shipmentStatus;
	}

	@XmlElement(name = "SHIPMENT_STATUS")
	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	public String getUltDestination() {
		return ultDestination;
	}

	@XmlElement(name = "ULT_DESTINATION")
	public void setUltDestination(String ultDestination) {
		this.ultDestination = ultDestination;
	}

	public String getCountryShipFrom() {
		return countryShipFrom;
	}

	@XmlElement(name = "COUNTRY_SHIP_FROM")
	public void setCountryShipFrom(String countryShipFrom) {
		this.countryShipFrom = countryShipFrom;
	}

	public String getDateOfExpot() {
		return dateOfExpot;
	}

	@XmlElement(name = "DATE_OF_EXPORT")
	public void setDateOfExpot(String dateOfExpot) {
		this.dateOfExpot = dateOfExpot;
	}

	public String getShipmentWLS() {
		return shipmentWLS;
	}

	@XmlElement(name = "SHIPMENT_WLS")
	public void setShipmentWLS(String shipmentWLS) {
		this.shipmentWLS = shipmentWLS;
	}

	public String getExporterWLS() {
		return exporterWLS;
	}

	@XmlElement(name = "EXPORTER_WLS")
	public void setExporterWLS(String exporterWLS) {
		this.exporterWLS = exporterWLS;
	}

	public String getUltConsWLS() {
		return ultConsWLS;
	}

	@XmlElement(name = "ULT_CONS_WLS")
	public void setUltConsWLS(String ultConsWLS) {
		this.ultConsWLS = ultConsWLS;
	}

	public String getIntConsWLS() {
		return intConsWLS;
	}

	@XmlElement(name = "INT_CONS_WLS")
	public void setIntConsWLS(String intConsWLS) {
		this.intConsWLS = intConsWLS;
	}

	public String getFfAgentWLS() {
		return ffAgentWLS;
	}

	@XmlElement(name = "FF_AGENT_WLS")
	public void setFfAgentWLS(String ffAgentWLS) {
		this.ffAgentWLS = ffAgentWLS;
	}

	public String getManualHoldOnShipment() {
		return manualHoldOnShipment;
	}

	@XmlElement(name = "MANUAL_HOLD_ON_SHIPMENT")
	public void setManualHoldOnShipment(String manualHoldOnShipment) {
		this.manualHoldOnShipment = manualHoldOnShipment;
	}
	
	public InvoicesDetails getInvoicesDetails() {
		return invoicesDetails;
	}

	@XmlElement(name = "INVOICES_DETAILS")
	public void setInvoicesDetails(InvoicesDetails invoicesDetails) {
		this.invoicesDetails = invoicesDetails;
	}

	
	/**
	 * @return the itnNo
	 */
	public String getItnNo() {
		return itnNo;
	}

	/**
	 * @param itnNo the itnNo to set
	 */
	@XmlElement(name = "ITN_NO")
	public void setItnNo(String itnNo) {
		this.itnNo = itnNo;
	}

	/**
	 * @return the xtn
	 */
	public String getXtn() {
		return xtn;
	}

	/**
	 * @param xtn the xtn to set
	 */
	@XmlElement(name = "XTN_NO")
	public void setXtn(String xtn) {
		this.xtn = xtn;
	}

	/**
	 * @return the correctionCode
	 */
	public String getCorrectionCode() {
		return correctionCode;
	}

	/**
	 * @param correctionCode the correctionCode to set
	 */
	@XmlElement(name = "CORRECTION_CODE")
	public void setCorrectionCode(String correctionCode) {
		this.correctionCode = correctionCode;
	}

	/**
	 * @return the shipmentCustomFilingStatus
	 */
	public String getShipmentCustomFilingStatus() {
		return shipmentCustomFilingStatus;
	}

	/**
	 * @param shipmentCustomFilingStatus the shipmentCustomFilingStatus to set
	 */
	@XmlElement(name = "SHIPMENT_CUSTOM_FILING_STATUS")
	public void setShipmentCustomFilingStatus(String shipmentCustomFilingStatus) {
		this.shipmentCustomFilingStatus = shipmentCustomFilingStatus;
	}

	/**
	 * @return the aesOption
	 */
	public String getAesOption() {
		return aesOption;
	}

	/**
	 * @param aesOption the aesOption to set
	 */
	@XmlElement(name = "AES_OPTION")
	public void setAesOption(String aesOption) {
		this.aesOption = aesOption;
	}

	
	
	
}

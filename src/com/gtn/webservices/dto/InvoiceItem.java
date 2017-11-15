package com.gtn.webservices.dto;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="INVOICE_ITEM")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvoiceItem {

	@XmlElement(name="ITEM_NO")
	private int itemNo;
		
	@XmlElement(name="PART_NO")
	private String partNo;
	
	@XmlElement(name="USML_CATEGORY_CODE")
	private String usmlCategory;
	
	@XmlElement(name="HTS_SCHEDULE_B_NO")
	private String htsSchedule;
	
	/*@XmlElement(name="SHIPMENT_HEADER")
	private String htsScheduleUom;*/
	
	@XmlElement(name="ECCN_UNDER_ITAR")
	private String eccn;
	
	/*@XmlElement(name="SHIPMENT_HEADER")
	private String importHts;*/
	
	/*@XmlElement(name="SHIPMENT_HEADER")
	private String importHtsUom;*/
	
	/*@XmlElement(name="COUNTRY_OF_ORIGIN")
	private String coo;*/
	
	@XmlElement(name="COUNTRY_OF_ORIGIN")
	private String cooCode;
	
	@XmlElement(name="MANUFACTURER")
	private String manufacturer;
	
	@XmlElement(name="SERIAL_NO")
	private String serialNos;
	
	@XmlElement(name="DESCRIPTION")
	private String partDescription;
	
	@XmlElement(name="PRIMARY_NET_QUANTITY")
	private Double invoiceQty;
	
	@XmlElement(name="INVOICE_VALUE")
	private Double invoiceValue;
	
	@XmlElement(name="DISCOUNT_VALUE")
	private Double discount;
	
	/*@XmlElement(name="CURRENCY_TYPE")
	private String currency;*/
	
	/*@XmlElement(name="SHIPMENT_HEADER")
	private Double conversionRate;*/
	
	/*@XmlElement(name="SHIPMENT_HEADER")
	private Double wholeSaleRate;*/
	
	@XmlElement(name="UNIT_PRICE")
	private Double unitePrice;
	
	@XmlElement(name="WHOLESALE_VALUE")
	private Double wholeSaleValue;
	
	@XmlElement(name="GROSS_WEIGHT")
	private Double grossWeight;
	
	@XmlElement(name="ITEM_NET_WEIGHT")
	private Double netWeight;
	
	@XmlElement(name="CONTROLLING_AGENCY")
	private String controllingAgency;
	
	@XmlElement(name="LICENSE_EXCEPTION")
	private String bisLicenseException;
	
	@XmlElement(name="ITAR_EXEMPTION_NO")
	private String itarExemption;
	
	@XmlElement(name="LICENSE_NO")
	private String licenseNo;
	
	@XmlElement(name="LICENSE_CODE")
	private String licenseCode;
	
	@XmlElement(name="LICENSE_TYPE")
	private String licenseType;
	
	/*@XmlElement(name="SHIPMENT_HEADER")
	private Date licenseExpDate;*/
	
	@XmlElement(name="SME_INDICATOR")
	private String smeIndicator;
	
	@XmlElement(name="ODTC_QUANTITY")
	private BigDecimal ddtcQuantity;
	
	@XmlElement(name="ODTC_UNIT_OF_MEASURE")
	private String ddtcUom;
	
	@XmlElement(name="ELIGIBLE_PARTY_INDICATOR")
	private String ddtcEligibilityParty;
	
	@XmlElement(name="REGISTRATION_NO")
	private String ddtcRegistrationNo;
	
	/*@XmlElement(name="SHIPMENT_HEADER")
	private String approvedCommMemberNo;*/
	
	/*@XmlElement(name="SHIPMENT_HEADER")
	private Date ftrEffDate;*/
	
	/*@XmlElement(name="SHIPMENT_HEADER")
	private String licReq;*/
	
	/*@XmlElement(name="SHIPMENT_HEADER")
	private String repReq;*/
	
	/*@XmlElement(name="SHIPMENT_HEADER")
	private String screenType;*/
	
	/*@XmlElement(name="SHIPMENT_HEADER")
	private Date screenedOn;*/
	
	@XmlElement(name="LOAD_DATA_FROM_TXN")
	private String loadFromTrans = "Y";

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public String getUsmlCategory() {
		return usmlCategory;
	}

	public void setUsmlCategory(String usmlCategory) {
		this.usmlCategory = usmlCategory;
	}

	public String getHtsSchedule() {
		return htsSchedule;
	}

	public void setHtsSchedule(String htsSchedule) {
		this.htsSchedule = htsSchedule;
	}


	public String getEccn() {
		return eccn;
	}

	public void setEccn(String eccn) {
		this.eccn = eccn;
	}

	public String getCooCode() {
		return cooCode;
	}

	public void setCooCode(String cooCode) {
		this.cooCode = cooCode;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getSerialNos() {
		return serialNos;
	}

	public void setSerialNos(String serialNos) {
		this.serialNos = serialNos;
	}

	public String getPartDescription() {
		return partDescription;
	}

	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}

	public Double getInvoiceQty() {
		return invoiceQty;
	}

	public void setInvoiceQty(Double invoiceQty) {
		this.invoiceQty = invoiceQty;
	}

	public Double getInvoiceValue() {
		return invoiceValue;
	}

	public void setInvoiceValue(Double invoiceValue) {
		this.invoiceValue = invoiceValue;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getUnitePrice() {
		return unitePrice;
	}

	public void setUnitePrice(Double unitePrice) {
		this.unitePrice = unitePrice;
	}

	public Double getWholeSaleValue() {
		return wholeSaleValue;
	}

	public void setWholeSaleValue(Double wholeSaleValue) {
		this.wholeSaleValue = wholeSaleValue;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public String getControllingAgency() {
		return controllingAgency;
	}

	public void setControllingAgency(String controllingAgency) {
		this.controllingAgency = controllingAgency;
	}

	public String getBisLicenseException() {
		return bisLicenseException;
	}

	public void setBisLicenseException(String bisLicenseException) {
		this.bisLicenseException = bisLicenseException;
	}

	public String getItarExemption() {
		return itarExemption;
	}

	public void setItarExemption(String itarExemption) {
		this.itarExemption = itarExemption;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getSmeIndicator() {
		return smeIndicator;
	}

	public void setSmeIndicator(String smeIndicator) {
		this.smeIndicator = smeIndicator;
	}

	public BigDecimal getDdtcQuantity() {
		return ddtcQuantity;
	}

	public void setDdtcQuantity(BigDecimal ddtcQuantity) {
		this.ddtcQuantity = ddtcQuantity;
	}

	public String getDdtcUom() {
		return ddtcUom;
	}

	public void setDdtcUom(String ddtcUom) {
		this.ddtcUom = ddtcUom;
	}

	public String getDdtcEligibilityParty() {
		return ddtcEligibilityParty;
	}

	public void setDdtcEligibilityParty(String ddtcEligibilityParty) {
		this.ddtcEligibilityParty = ddtcEligibilityParty;
	}

	public String getDdtcRegistrationNo() {
		return ddtcRegistrationNo;
	}

	public void setDdtcRegistrationNo(String ddtcRegistrationNo) {
		this.ddtcRegistrationNo = ddtcRegistrationNo;
	}

	public String getLoadFromTrans() {
		return loadFromTrans;
	}

	public void setLoadFromTrans(String loadFromTrans) {
		this.loadFromTrans = loadFromTrans;
	}
	
	
	
}

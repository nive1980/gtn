package com.gtn.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="SHIPMENT_ITEM")
@Access(AccessType.PROPERTY)
public class ShipmentItem implements Model{

	private int id;
	private int itemNo;
	private int shipmentId;
	private String partNo;
	private String usmlCategory;
	private String htsSchedule;
	private String htsScheduleUom;
	private String eccn;
	private String importHts;
	private String importHtsUom;
	private String coo;
	private String cooCode;
	private String manufacturer;
	private String serialNos;
	private String partDescription;
	private Double invoiceQty;
	private Double invoiceValue;
	private Double discount;
	private String currency;
	private Double conversionRate;
	private Double wholeSaleRate;
	private Double unitePrice;
	private Double wholeSaleValue;
	private Double grossWeight;
	private Double netWeight;
	
	private String controllingAgency;
	private String bisLicenseException;
	private String itarExemption;
	private String licenseNo;
	private String licenseCode;
	private String licenseType;
	private Date licenseExpDate;
	private String smeIndicator;
	private BigDecimal ddtcQuantity;
	private String ddtcUom;
	private String ddtcEligibilityParty;
	private String ddtcRegistrationNo;
	private String approvedCommMemberNo;
	private Date ftrEffDate;
	
	private String licReq;
	private String repReq;
	private String screenType;
	private Date screenedOn;
	
	private List<String> msgs;
	private String shipLsStatus;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "SHIPMENT_ID")
	public int getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}

	@Column(name = "PART_NO")
	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	@Column(name = "USML_CATEGORY")
	public String getUsmlCategory() {
		return usmlCategory;
	}

	public void setUsmlCategory(String usmlCategory) {
		this.usmlCategory = usmlCategory;
	}

	@Column(name = "HTS_SCHEDULE_B")
	public String getHtsSchedule() {
		return htsSchedule;
	}

	public void setHtsSchedule(String htsSchedule) {
		this.htsSchedule = htsSchedule;
	}

	@Column(name = "HTS_SCHEDULE_UOM")
	public String getHtsScheduleUom() {
		return htsScheduleUom;
	}

	public void setHtsScheduleUom(String htsScheduleUom) {
		this.htsScheduleUom = htsScheduleUom;
	}

	@Column(name = "ECCN")
	public String getEccn() {
		return eccn;
	}

	public void setEccn(String eccn) {
		this.eccn = eccn;
	}

	@Column(name = "IMPORT_HTS")
	public String getImportHts() {
		return importHts;
	}

	public void setImportHts(String importHts) {
		this.importHts = importHts;
	}

	@Column(name = "IMPORT_HTS_UOM")
	public String getImportHtsUom() {
		return importHtsUom;
	}

	public void setImportHtsUom(String importHtsUom) {
		this.importHtsUom = importHtsUom;
	}

	@Column(name = "COO")
	public String getCoo() {
		return coo;
	}

	public void setCoo(String coo) {
		this.coo = coo;
	}
	
	@Column(name = "COO_CODE")
	public String getCooCode() {
		return cooCode;
	}

	public void setCooCode(String cooCode) {
		this.cooCode = cooCode;
	}

	@Column(name = "MANUFACTURER")
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "SERIAL_NUMBERS")
	public String getSerialNos() {
		return serialNos;
	}

	public void setSerialNos(String serialNos) {
		this.serialNos = serialNos;
	}

	@Column(name = "PRODUCT_DESC")
	public String getPartDescription() {
		return partDescription;
	}

	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}

	@Column(name = "INVOICE_QTY")
	public Double getInvoiceQty() {
		return invoiceQty;
	}

	public void setInvoiceQty(Double invoiceQty) {
		this.invoiceQty = invoiceQty;
	}

	@Column(name = "INVOICE_VALUE")
	public Double getInvoiceValue() {
		return invoiceValue;
	}

	public void setInvoiceValue(Double invoiceValue) {
		this.invoiceValue = invoiceValue;
	}

	@Column(name = "DISCOUNT")
	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@Column(name = "CURRENCY")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "CONVERSION_RATE")
	public Double getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(Double conversionRate) {
		this.conversionRate = conversionRate;
	}

	@Column(name = "WHOLESALE_RATE")
	public Double getWholeSaleRate() {
		return wholeSaleRate;
	}

	public void setWholeSaleRate(Double wholeSaleRate) {
		this.wholeSaleRate = wholeSaleRate;
	}

	@Column(name = "UNIT_PRICE")
	public Double getUnitePrice() {
		return unitePrice;
	}

	public void setUnitePrice(Double unitePrice) {
		this.unitePrice = unitePrice;
	}

	@Column(name = "WHOLESALE_VALUE")
	public Double getWholeSaleValue() {
		return wholeSaleValue;
	}

	public void setWholeSaleValue(Double wholeSaleValue) {
		this.wholeSaleValue = wholeSaleValue;
	}

	@Column(name = "GROSS_WEIGHT")
	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	@Column(name = "NET_WEIGHT")
	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	@Column(name = "ITEM_NO")
	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
		
	@Column(name = "LICENSE_REQUIRED")
	public String getLicReq() {
		return licReq;
	}

	public void setLicReq(String licReq) {
		this.licReq = licReq;
	}

	@Column(name = "SCREEN_TYPE")
	public String getScreenType() {
		return screenType;
	}

	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}

	@Column(name = "SCREENED_ON")
	public Date getScreenedOn() {
		return screenedOn;
	}

	public void setScreenedOn(Date screenedOn) {
		this.screenedOn = screenedOn;
	}
	
	
	
	@Column(name = "CONTROLLING_AGENCY")
	public String getControllingAgency() {
		return controllingAgency;
	}

	public void setControllingAgency(String controllingAgency) {
		this.controllingAgency = controllingAgency;
	}

	@Column(name = "BIS_LICENSE_EXCEPTION")
	public String getBisLicenseException() {
		return bisLicenseException;
	}

	public void setBisLicenseException(String bisLicenseException) {
		this.bisLicenseException = bisLicenseException;
	}

	@Column(name = "ITAR_EXEMPTION")
	public String getItarExemption() {
		return itarExemption;
	}

	public void setItarExemption(String itarExemption) {
		this.itarExemption = itarExemption;
	}

	@Column(name = "LICENSE_NO")
	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	@Column(name = "LICENSE_CODE")
	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	@Column(name = "LICENSE_TYPE")
	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	@Column(name = "LICENSE_EXP_DATE")
	public Date getLicenseExpDate() {
		return licenseExpDate;
	}

	public void setLicenseExpDate(Date licenseExpDate) {
		this.licenseExpDate = licenseExpDate;
	}

	@Column(name = "SME_INDICATOR")
	public String getSmeIndicator() {
		return smeIndicator;
	}

	public void setSmeIndicator(String smeIndicator) {
		this.smeIndicator = smeIndicator;
	}

	@Column(name = "DDTC_QUANTITY")
	public BigDecimal getDdtcQuantity() {
		return ddtcQuantity;
	}

	public void setDdtcQuantity(BigDecimal ddtcQuantity) {
		this.ddtcQuantity = ddtcQuantity;
	}

	@Column(name = "DDTC_UOM")
	public String getDdtcUom() {
		return ddtcUom;
	}

	public void setDdtcUom(String ddtcUom) {
		this.ddtcUom = ddtcUom;
	}

	@Column(name = "DDTC_ELIGIBILITY_PARTY")
	public String getDdtcEligibilityParty() {
		return ddtcEligibilityParty;
	}

	public void setDdtcEligibilityParty(String ddtcEligibilityParty) {
		this.ddtcEligibilityParty = ddtcEligibilityParty;
	}

	@Column(name = "DDTC_REGISTRATION_NO")
	public String getDdtcRegistrationNo() {
		return ddtcRegistrationNo;
	}

	public void setDdtcRegistrationNo(String ddtcRegistrationNo) {
		this.ddtcRegistrationNo = ddtcRegistrationNo;
	}

	@Column(name = "APPROVED_COMMUNITY_NO")
	public String getApprovedCommMemberNo() {
		return approvedCommMemberNo;
	}

	public void setApprovedCommMemberNo(String approvedCommMemberNo) {
		this.approvedCommMemberNo = approvedCommMemberNo;
	}

	@Column(name = "FTR_EFFECTIVE_DATE")
	public Date getFtrEffDate() {
		return ftrEffDate;
	}

	public void setFtrEffDate(Date ftrEffDate) {
		this.ftrEffDate = ftrEffDate;
	}

	@Transient
	public List<String> getMsgs() {
		return msgs;
	}
	public void setMsgs(List<String> msgs) {
		this.msgs = msgs;
	}

	@Column(name = "REPORT_REQ")
	public String getRepReq() {
		return repReq;
	}

	public void setRepReq(String repReq) {
		this.repReq = repReq;
	}

	
	@Transient
	public String getShipLsStatus() {
		return shipLsStatus;
	}

	public void setShipLsStatus(String shipLsStatus) {
		this.shipLsStatus = shipLsStatus;
	}
	
	
}

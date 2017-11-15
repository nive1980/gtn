package com.gtn.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ShipmentItemDto {
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
	
	private Integer limit;
	private Integer page;
	private String filter;
	
	private String delete;
	private Integer offset;
	private String copy;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public int getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
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
	public String getHtsScheduleUom() {
		return htsScheduleUom;
	}
	public void setHtsScheduleUom(String htsScheduleUom) {
		this.htsScheduleUom = htsScheduleUom;
	}
	public String getEccn() {
		return eccn;
	}
	public void setEccn(String eccn) {
		this.eccn = eccn;
	}
	public String getImportHts() {
		return importHts;
	}
	public void setImportHts(String importHts) {
		this.importHts = importHts;
	}
	public String getImportHtsUom() {
		return importHtsUom;
	}
	public void setImportHtsUom(String importHtsUom) {
		this.importHtsUom = importHtsUom;
	}
	public String getCoo() {
		return coo;
	}
	public void setCoo(String coo) {
		this.coo = coo;
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
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getConversionRate() {
		return conversionRate;
	}
	public void setConversionRate(Double conversionRate) {
		this.conversionRate = conversionRate;
	}
	public Double getWholeSaleRate() {
		return wholeSaleRate;
	}
	public void setWholeSaleRate(Double wholeSaleRate) {
		this.wholeSaleRate = wholeSaleRate;
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
	public Date getLicenseExpDate() {
		return licenseExpDate;
	}
	public void setLicenseExpDate(Date licenseExpDate) {
		this.licenseExpDate = licenseExpDate;
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
	public String getApprovedCommMemberNo() {
		return approvedCommMemberNo;
	}
	public void setApprovedCommMemberNo(String approvedCommMemberNo) {
		this.approvedCommMemberNo = approvedCommMemberNo;
	}
	public Date getFtrEffDate() {
		return ftrEffDate;
	}
	public void setFtrEffDate(Date ftrEffDate) {
		this.ftrEffDate = ftrEffDate;
	}
	public String getLicReq() {
		return licReq;
	}
	public void setLicReq(String licReq) {
		this.licReq = licReq;
	}
	public String getRepReq() {
		return repReq;
	}
	public void setRepReq(String repReq) {
		this.repReq = repReq;
	}
	public String getScreenType() {
		return screenType;
	}
	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}
	public Date getScreenedOn() {
		return screenedOn;
	}
	public void setScreenedOn(Date screenedOn) {
		this.screenedOn = screenedOn;
	}
	public List<String> getMsgs() {
		return msgs;
	}
	public void setMsgs(List<String> msgs) {
		this.msgs = msgs;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public String getCopy() {
		return copy;
	}
	public void setCopy(String copy) {
		this.copy = copy;
	}
	
	
}

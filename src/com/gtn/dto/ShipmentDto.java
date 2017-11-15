package com.gtn.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ShipmentDto {
	private int id;
	private String shipmentNo;
	private Date exportDate;
	private String countryOfExportName;
	private String countryOfExportCode;
	private String countryOfUltConsigneeName;
	private String countryOfUltConsigneeCode;
	
	private String exporterCode;
	private String exporterFirstName;
	private String exporterLastName;
	private String exporterCompanyName;
	private String exporterPhoneNo;
	private String exporterEmail;
	private String exporterName;
	private String exporterAddressLine1;
	private String exporterAddressLine2;
	private String exporterCity;
	private String exporterState;
	private String exporterStateName;
	private String exporterCountry;
	private String exporterCountryName;
	private String exporterZip;
		
	private String usppiMot;
	private String usspiFirstName;
	private String usspiLastName;
	private String usspiPhone;
	private String usppiPortOfExport;
	private String usppiExporterId;
	private String usppiExporterIdType;
	
	private String ultConsigneeCode;
	private String ultConsigneeName;
	private String ultConsigneeType;
	private String ultConsigneeFirstName;
	private String ultConsigneeLastName;
	private String ultConsigneeAddrLine1;
	private String ultConsigneeAddrLine2;
	private String ultConsigneeCity;
	private String ultConsigneeState;
	private String ultConsigneeStateName;
	private String ultConsigneeCountryCode;
	private String ultConsigneeCountryName;
	private String ultConsigneeZip;
	private String ultConsigneeCompanyName;
	private String ultConsigneePhone;
	private String ultConsigneeEmail;
	
	private String interConsigneeCode;
	private String interConsigneeName;
	private String interConsigneeFirstName;
	private String interConsigneeLastName;
	private String interConsigneeAddrLine1;
	private String interConsigneeAddrLine2;
	private String interConsigneeCity;
	private String interConsigneeState;
	private String interConsigneeStateName;
	private String interConsigneeCountryCode;
	private String interConsigneeCountryName;
	private String interConsigneeZip;
	private String interConsigneeCompanyName;
	private String interConsigneePhone;
	private String interConsigneeEmail;
	
	private String freightForwaderCode;
	private String freightForwaderName;
	private String freightForwaderFirstName;
	private String freightForwaderLastName;
	private String freightForwaderAddrLine1;
	private String freightForwaderAddrLine2;
	private String freightForwaderCity;
	private String freightForwaderState;
	private String freightForwaderStateName;
	private String freightForwaderCountryCode;
	private String freightForwaderCountryName;
	private String freightForwaderZip;
	private String freightForwaderCompanyName;
	private String freightForwaderPhone;
	private String freightForwaderEmail;
	private String transmitToAes;
	private String ffFilerIdType;
	private String ffFilerIdNo;
	
	private String shipToCode;
	private String shipToName;
	private String shipToCity;
	private String shipToState;
	private String shipToStateName;
	private String shipToCountry;
	private String shipToCountryName;
	private String shipToZip;
	private String shipToAddr1;
	private String shipToAddr2;

	private String billToCode;
	private String billToName;
	private String billToCity;
	private String billToState;
	private String billToStateName;
	private String billToCountry;
	private String billToCountryName;
	private String billToZip;
	private String billToAddr1;
	private String billToAddr2;
	
	private String paymentTerms;
	private String incoTerms;
	private String currency;
	private Double conversionRate;
	private String poNumber;
	private String modeOfTransport;
	private String modeOfTransportCode;
	private String freightChargesPaidBy;
	private String foreignVatPaidBy;
	private String customDutyPaidBy;
	private String chargeDesc;
	private Double chargeAmt;	
	private String dropShipment;
	private String shipTo;
	private String billTo;
	private String containPersonalEffect;
	private String insuranceRequired;
	private String chargeToCustomer;
	private String printOnDoc;
	
	private String specialInstruction;
	private String shipmentInstruction;
	
	private String screeningStatus;
	private String lsStatus;
	private Date screenedOn;
	
	private String bookingNo;
	private String trackingNo;
	private String countryOfOriginName;
	private String countryOfOriginCode;
	private String portOfExportName;
	private String portOfExportCode;
	private String portOfUnloadName;
	private String portOfUnloadCode;
	private String portOfArrivalName;
	private String portOfArrivalCode;
	private String shipmentCarrierName;
	private String shipmentCarrierCode;
	private String finalDestAirport;
	private String vesseFlightNo;
	private String transportDocNo;
	
	private String eeiNo;
	private String aesStatus;
	private String itn;
	private String xtn;
	private String routedExpTxn;
	private String relatedPrtyTxn;
	private String stateOfOrigin;
	private String ftzNo;
	private String correctionCode;
	
	private String shipmentRefNo;
	private String hazdarous;
	private String motCode;
	private String portOfUnlanding;
	private String portOfUnlandingCode;
	private String transportRefNo;
	private String entryNumber;
	private String conveyanceName;
	
	private String status;
	private Date createdOn;
	private String createdBy;
	private Date modifiedOn;
	private String modifiedBy;
	
	private List<String> msgs;
	private String aesOption;
	private String sbuCode;
	
	private String statusDesc;
	
	private String relatedPartTransaction;
	private String easeStatus;
	private Date easeSubmitDate;
	private BigDecimal shipmentValue;
	
	
	private String delete;
	private Integer offset;
	private Integer limit;
	private String rates;
	
	private String sortBy;
	private String direction;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShipmentNo() {
		return shipmentNo;
	}
	public void setShipmentNo(String shipmentNo) {
		this.shipmentNo = shipmentNo;
	}
	public Date getExportDate() {
		return exportDate;
	}
	public void setExportDate(Date exportDate) {
		this.exportDate = exportDate;
	}
	public String getCountryOfExportName() {
		return countryOfExportName;
	}
	public void setCountryOfExportName(String countryOfExportName) {
		this.countryOfExportName = countryOfExportName;
	}
	public String getCountryOfExportCode() {
		return countryOfExportCode;
	}
	public void setCountryOfExportCode(String countryOfExportCode) {
		this.countryOfExportCode = countryOfExportCode;
	}
	public String getCountryOfUltConsigneeName() {
		return countryOfUltConsigneeName;
	}
	public void setCountryOfUltConsigneeName(String countryOfUltConsigneeName) {
		this.countryOfUltConsigneeName = countryOfUltConsigneeName;
	}
	public String getCountryOfUltConsigneeCode() {
		return countryOfUltConsigneeCode;
	}
	public void setCountryOfUltConsigneeCode(String countryOfUltConsigneeCode) {
		this.countryOfUltConsigneeCode = countryOfUltConsigneeCode;
	}
	public String getExporterCode() {
		return exporterCode;
	}
	public void setExporterCode(String exporterCode) {
		this.exporterCode = exporterCode;
	}
	public String getExporterFirstName() {
		return exporterFirstName;
	}
	public void setExporterFirstName(String exporterFirstName) {
		this.exporterFirstName = exporterFirstName;
	}
	public String getExporterLastName() {
		return exporterLastName;
	}
	public void setExporterLastName(String exporterLastName) {
		this.exporterLastName = exporterLastName;
	}
	public String getExporterCompanyName() {
		return exporterCompanyName;
	}
	public void setExporterCompanyName(String exporterCompanyName) {
		this.exporterCompanyName = exporterCompanyName;
	}
	public String getExporterPhoneNo() {
		return exporterPhoneNo;
	}
	public void setExporterPhoneNo(String exporterPhoneNo) {
		this.exporterPhoneNo = exporterPhoneNo;
	}
	public String getExporterEmail() {
		return exporterEmail;
	}
	public void setExporterEmail(String exporterEmail) {
		this.exporterEmail = exporterEmail;
	}
	public String getExporterName() {
		return exporterName;
	}
	public void setExporterName(String exporterName) {
		this.exporterName = exporterName;
	}
	public String getExporterAddressLine1() {
		return exporterAddressLine1;
	}
	public void setExporterAddressLine1(String exporterAddressLine1) {
		this.exporterAddressLine1 = exporterAddressLine1;
	}
	public String getExporterAddressLine2() {
		return exporterAddressLine2;
	}
	public void setExporterAddressLine2(String exporterAddressLine2) {
		this.exporterAddressLine2 = exporterAddressLine2;
	}
	public String getExporterCity() {
		return exporterCity;
	}
	public void setExporterCity(String exporterCity) {
		this.exporterCity = exporterCity;
	}
	public String getExporterState() {
		return exporterState;
	}
	public void setExporterState(String exporterState) {
		this.exporterState = exporterState;
	}
	public String getExporterStateName() {
		return exporterStateName;
	}
	public void setExporterStateName(String exporterStateName) {
		this.exporterStateName = exporterStateName;
	}
	public String getExporterCountry() {
		return exporterCountry;
	}
	public void setExporterCountry(String exporterCountry) {
		this.exporterCountry = exporterCountry;
	}
	public String getExporterCountryName() {
		return exporterCountryName;
	}
	public void setExporterCountryName(String exporterCountryName) {
		this.exporterCountryName = exporterCountryName;
	}
	public String getExporterZip() {
		return exporterZip;
	}
	public void setExporterZip(String exporterZip) {
		this.exporterZip = exporterZip;
	}
	public String getUsppiMot() {
		return usppiMot;
	}
	public void setUsppiMot(String usppiMot) {
		this.usppiMot = usppiMot;
	}
	public String getUsspiFirstName() {
		return usspiFirstName;
	}
	public void setUsspiFirstName(String usspiFirstName) {
		this.usspiFirstName = usspiFirstName;
	}
	public String getUsspiLastName() {
		return usspiLastName;
	}
	public void setUsspiLastName(String usspiLastName) {
		this.usspiLastName = usspiLastName;
	}
	public String getUsspiPhone() {
		return usspiPhone;
	}
	public void setUsspiPhone(String usspiPhone) {
		this.usspiPhone = usspiPhone;
	}
	public String getUsppiPortOfExport() {
		return usppiPortOfExport;
	}
	public void setUsppiPortOfExport(String usppiPortOfExport) {
		this.usppiPortOfExport = usppiPortOfExport;
	}
	public String getUsppiExporterId() {
		return usppiExporterId;
	}
	public void setUsppiExporterId(String usppiExporterId) {
		this.usppiExporterId = usppiExporterId;
	}
	public String getUsppiExporterIdType() {
		return usppiExporterIdType;
	}
	public void setUsppiExporterIdType(String usppiExporterIdType) {
		this.usppiExporterIdType = usppiExporterIdType;
	}
	public String getUltConsigneeCode() {
		return ultConsigneeCode;
	}
	public void setUltConsigneeCode(String ultConsigneeCode) {
		this.ultConsigneeCode = ultConsigneeCode;
	}
	public String getUltConsigneeName() {
		return ultConsigneeName;
	}
	public void setUltConsigneeName(String ultConsigneeName) {
		this.ultConsigneeName = ultConsigneeName;
	}
	public String getUltConsigneeType() {
		return ultConsigneeType;
	}
	public void setUltConsigneeType(String ultConsigneeType) {
		this.ultConsigneeType = ultConsigneeType;
	}
	public String getUltConsigneeFirstName() {
		return ultConsigneeFirstName;
	}
	public void setUltConsigneeFirstName(String ultConsigneeFirstName) {
		this.ultConsigneeFirstName = ultConsigneeFirstName;
	}
	public String getUltConsigneeLastName() {
		return ultConsigneeLastName;
	}
	public void setUltConsigneeLastName(String ultConsigneeLastName) {
		this.ultConsigneeLastName = ultConsigneeLastName;
	}
	public String getUltConsigneeAddrLine1() {
		return ultConsigneeAddrLine1;
	}
	public void setUltConsigneeAddrLine1(String ultConsigneeAddrLine1) {
		this.ultConsigneeAddrLine1 = ultConsigneeAddrLine1;
	}
	public String getUltConsigneeAddrLine2() {
		return ultConsigneeAddrLine2;
	}
	public void setUltConsigneeAddrLine2(String ultConsigneeAddrLine2) {
		this.ultConsigneeAddrLine2 = ultConsigneeAddrLine2;
	}
	public String getUltConsigneeCity() {
		return ultConsigneeCity;
	}
	public void setUltConsigneeCity(String ultConsigneeCity) {
		this.ultConsigneeCity = ultConsigneeCity;
	}
	public String getUltConsigneeState() {
		return ultConsigneeState;
	}
	public void setUltConsigneeState(String ultConsigneeState) {
		this.ultConsigneeState = ultConsigneeState;
	}
	public String getUltConsigneeStateName() {
		return ultConsigneeStateName;
	}
	public void setUltConsigneeStateName(String ultConsigneeStateName) {
		this.ultConsigneeStateName = ultConsigneeStateName;
	}
	public String getUltConsigneeCountryCode() {
		return ultConsigneeCountryCode;
	}
	public void setUltConsigneeCountryCode(String ultConsigneeCountryCode) {
		this.ultConsigneeCountryCode = ultConsigneeCountryCode;
	}
	public String getUltConsigneeCountryName() {
		return ultConsigneeCountryName;
	}
	public void setUltConsigneeCountryName(String ultConsigneeCountryName) {
		this.ultConsigneeCountryName = ultConsigneeCountryName;
	}
	public String getUltConsigneeZip() {
		return ultConsigneeZip;
	}
	public void setUltConsigneeZip(String ultConsigneeZip) {
		this.ultConsigneeZip = ultConsigneeZip;
	}
	public String getUltConsigneeCompanyName() {
		return ultConsigneeCompanyName;
	}
	public void setUltConsigneeCompanyName(String ultConsigneeCompanyName) {
		this.ultConsigneeCompanyName = ultConsigneeCompanyName;
	}
	public String getUltConsigneePhone() {
		return ultConsigneePhone;
	}
	public void setUltConsigneePhone(String ultConsigneePhone) {
		this.ultConsigneePhone = ultConsigneePhone;
	}
	public String getUltConsigneeEmail() {
		return ultConsigneeEmail;
	}
	public void setUltConsigneeEmail(String ultConsigneeEmail) {
		this.ultConsigneeEmail = ultConsigneeEmail;
	}
	public String getInterConsigneeCode() {
		return interConsigneeCode;
	}
	public void setInterConsigneeCode(String interConsigneeCode) {
		this.interConsigneeCode = interConsigneeCode;
	}
	public String getInterConsigneeName() {
		return interConsigneeName;
	}
	public void setInterConsigneeName(String interConsigneeName) {
		this.interConsigneeName = interConsigneeName;
	}
	public String getInterConsigneeFirstName() {
		return interConsigneeFirstName;
	}
	public void setInterConsigneeFirstName(String interConsigneeFirstName) {
		this.interConsigneeFirstName = interConsigneeFirstName;
	}
	public String getInterConsigneeLastName() {
		return interConsigneeLastName;
	}
	public void setInterConsigneeLastName(String interConsigneeLastName) {
		this.interConsigneeLastName = interConsigneeLastName;
	}
	public String getInterConsigneeAddrLine1() {
		return interConsigneeAddrLine1;
	}
	public void setInterConsigneeAddrLine1(String interConsigneeAddrLine1) {
		this.interConsigneeAddrLine1 = interConsigneeAddrLine1;
	}
	public String getInterConsigneeAddrLine2() {
		return interConsigneeAddrLine2;
	}
	public void setInterConsigneeAddrLine2(String interConsigneeAddrLine2) {
		this.interConsigneeAddrLine2 = interConsigneeAddrLine2;
	}
	public String getInterConsigneeCity() {
		return interConsigneeCity;
	}
	public void setInterConsigneeCity(String interConsigneeCity) {
		this.interConsigneeCity = interConsigneeCity;
	}
	public String getInterConsigneeState() {
		return interConsigneeState;
	}
	public void setInterConsigneeState(String interConsigneeState) {
		this.interConsigneeState = interConsigneeState;
	}
	public String getInterConsigneeStateName() {
		return interConsigneeStateName;
	}
	public void setInterConsigneeStateName(String interConsigneeStateName) {
		this.interConsigneeStateName = interConsigneeStateName;
	}
	public String getInterConsigneeCountryCode() {
		return interConsigneeCountryCode;
	}
	public void setInterConsigneeCountryCode(String interConsigneeCountryCode) {
		this.interConsigneeCountryCode = interConsigneeCountryCode;
	}
	public String getInterConsigneeCountryName() {
		return interConsigneeCountryName;
	}
	public void setInterConsigneeCountryName(String interConsigneeCountryName) {
		this.interConsigneeCountryName = interConsigneeCountryName;
	}
	public String getInterConsigneeZip() {
		return interConsigneeZip;
	}
	public void setInterConsigneeZip(String interConsigneeZip) {
		this.interConsigneeZip = interConsigneeZip;
	}
	public String getInterConsigneeCompanyName() {
		return interConsigneeCompanyName;
	}
	public void setInterConsigneeCompanyName(String interConsigneeCompanyName) {
		this.interConsigneeCompanyName = interConsigneeCompanyName;
	}
	public String getInterConsigneePhone() {
		return interConsigneePhone;
	}
	public void setInterConsigneePhone(String interConsigneePhone) {
		this.interConsigneePhone = interConsigneePhone;
	}
	public String getInterConsigneeEmail() {
		return interConsigneeEmail;
	}
	public void setInterConsigneeEmail(String interConsigneeEmail) {
		this.interConsigneeEmail = interConsigneeEmail;
	}
	public String getFreightForwaderCode() {
		return freightForwaderCode;
	}
	public void setFreightForwaderCode(String freightForwaderCode) {
		this.freightForwaderCode = freightForwaderCode;
	}
	public String getFreightForwaderName() {
		return freightForwaderName;
	}
	public void setFreightForwaderName(String freightForwaderName) {
		this.freightForwaderName = freightForwaderName;
	}
	public String getFreightForwaderFirstName() {
		return freightForwaderFirstName;
	}
	public void setFreightForwaderFirstName(String freightForwaderFirstName) {
		this.freightForwaderFirstName = freightForwaderFirstName;
	}
	public String getFreightForwaderLastName() {
		return freightForwaderLastName;
	}
	public void setFreightForwaderLastName(String freightForwaderLastName) {
		this.freightForwaderLastName = freightForwaderLastName;
	}
	public String getFreightForwaderAddrLine1() {
		return freightForwaderAddrLine1;
	}
	public void setFreightForwaderAddrLine1(String freightForwaderAddrLine1) {
		this.freightForwaderAddrLine1 = freightForwaderAddrLine1;
	}
	public String getFreightForwaderAddrLine2() {
		return freightForwaderAddrLine2;
	}
	public void setFreightForwaderAddrLine2(String freightForwaderAddrLine2) {
		this.freightForwaderAddrLine2 = freightForwaderAddrLine2;
	}
	public String getFreightForwaderCity() {
		return freightForwaderCity;
	}
	public void setFreightForwaderCity(String freightForwaderCity) {
		this.freightForwaderCity = freightForwaderCity;
	}
	public String getFreightForwaderState() {
		return freightForwaderState;
	}
	public void setFreightForwaderState(String freightForwaderState) {
		this.freightForwaderState = freightForwaderState;
	}
	public String getFreightForwaderStateName() {
		return freightForwaderStateName;
	}
	public void setFreightForwaderStateName(String freightForwaderStateName) {
		this.freightForwaderStateName = freightForwaderStateName;
	}
	public String getFreightForwaderCountryCode() {
		return freightForwaderCountryCode;
	}
	public void setFreightForwaderCountryCode(String freightForwaderCountryCode) {
		this.freightForwaderCountryCode = freightForwaderCountryCode;
	}
	public String getFreightForwaderCountryName() {
		return freightForwaderCountryName;
	}
	public void setFreightForwaderCountryName(String freightForwaderCountryName) {
		this.freightForwaderCountryName = freightForwaderCountryName;
	}
	public String getFreightForwaderZip() {
		return freightForwaderZip;
	}
	public void setFreightForwaderZip(String freightForwaderZip) {
		this.freightForwaderZip = freightForwaderZip;
	}
	public String getFreightForwaderCompanyName() {
		return freightForwaderCompanyName;
	}
	public void setFreightForwaderCompanyName(String freightForwaderCompanyName) {
		this.freightForwaderCompanyName = freightForwaderCompanyName;
	}
	public String getFreightForwaderPhone() {
		return freightForwaderPhone;
	}
	public void setFreightForwaderPhone(String freightForwaderPhone) {
		this.freightForwaderPhone = freightForwaderPhone;
	}
	public String getFreightForwaderEmail() {
		return freightForwaderEmail;
	}
	public void setFreightForwaderEmail(String freightForwaderEmail) {
		this.freightForwaderEmail = freightForwaderEmail;
	}
	public String getTransmitToAes() {
		return transmitToAes;
	}
	public void setTransmitToAes(String transmitToAes) {
		this.transmitToAes = transmitToAes;
	}
	public String getFfFilerIdType() {
		return ffFilerIdType;
	}
	public void setFfFilerIdType(String ffFilerIdType) {
		this.ffFilerIdType = ffFilerIdType;
	}
	public String getFfFilerIdNo() {
		return ffFilerIdNo;
	}
	public void setFfFilerIdNo(String ffFilerIdNo) {
		this.ffFilerIdNo = ffFilerIdNo;
	}
	public String getShipToCode() {
		return shipToCode;
	}
	public void setShipToCode(String shipToCode) {
		this.shipToCode = shipToCode;
	}
	public String getShipToName() {
		return shipToName;
	}
	public void setShipToName(String shipToName) {
		this.shipToName = shipToName;
	}
	public String getShipToCity() {
		return shipToCity;
	}
	public void setShipToCity(String shipToCity) {
		this.shipToCity = shipToCity;
	}
	public String getShipToState() {
		return shipToState;
	}
	public void setShipToState(String shipToState) {
		this.shipToState = shipToState;
	}
	public String getShipToStateName() {
		return shipToStateName;
	}
	public void setShipToStateName(String shipToStateName) {
		this.shipToStateName = shipToStateName;
	}
	public String getShipToCountry() {
		return shipToCountry;
	}
	public void setShipToCountry(String shipToCountry) {
		this.shipToCountry = shipToCountry;
	}
	public String getShipToCountryName() {
		return shipToCountryName;
	}
	public void setShipToCountryName(String shipToCountryName) {
		this.shipToCountryName = shipToCountryName;
	}
	public String getShipToZip() {
		return shipToZip;
	}
	public void setShipToZip(String shipToZip) {
		this.shipToZip = shipToZip;
	}
	public String getShipToAddr1() {
		return shipToAddr1;
	}
	public void setShipToAddr1(String shipToAddr1) {
		this.shipToAddr1 = shipToAddr1;
	}
	public String getShipToAddr2() {
		return shipToAddr2;
	}
	public void setShipToAddr2(String shipToAddr2) {
		this.shipToAddr2 = shipToAddr2;
	}
	public String getBillToCode() {
		return billToCode;
	}
	public void setBillToCode(String billToCode) {
		this.billToCode = billToCode;
	}
	public String getBillToName() {
		return billToName;
	}
	public void setBillToName(String billToName) {
		this.billToName = billToName;
	}
	public String getBillToCity() {
		return billToCity;
	}
	public void setBillToCity(String billToCity) {
		this.billToCity = billToCity;
	}
	public String getBillToState() {
		return billToState;
	}
	public void setBillToState(String billToState) {
		this.billToState = billToState;
	}
	public String getBillToStateName() {
		return billToStateName;
	}
	public void setBillToStateName(String billToStateName) {
		this.billToStateName = billToStateName;
	}
	public String getBillToCountry() {
		return billToCountry;
	}
	public void setBillToCountry(String billToCountry) {
		this.billToCountry = billToCountry;
	}
	public String getBillToCountryName() {
		return billToCountryName;
	}
	public void setBillToCountryName(String billToCountryName) {
		this.billToCountryName = billToCountryName;
	}
	public String getBillToZip() {
		return billToZip;
	}
	public void setBillToZip(String billToZip) {
		this.billToZip = billToZip;
	}
	public String getBillToAddr1() {
		return billToAddr1;
	}
	public void setBillToAddr1(String billToAddr1) {
		this.billToAddr1 = billToAddr1;
	}
	public String getBillToAddr2() {
		return billToAddr2;
	}
	public void setBillToAddr2(String billToAddr2) {
		this.billToAddr2 = billToAddr2;
	}
	public String getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	public String getIncoTerms() {
		return incoTerms;
	}
	public void setIncoTerms(String incoTerms) {
		this.incoTerms = incoTerms;
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
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getModeOfTransport() {
		return modeOfTransport;
	}
	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}
	public String getModeOfTransportCode() {
		return modeOfTransportCode;
	}
	public void setModeOfTransportCode(String modeOfTransportCode) {
		this.modeOfTransportCode = modeOfTransportCode;
	}
	public String getFreightChargesPaidBy() {
		return freightChargesPaidBy;
	}
	public void setFreightChargesPaidBy(String freightChargesPaidBy) {
		this.freightChargesPaidBy = freightChargesPaidBy;
	}
	public String getForeignVatPaidBy() {
		return foreignVatPaidBy;
	}
	public void setForeignVatPaidBy(String foreignVatPaidBy) {
		this.foreignVatPaidBy = foreignVatPaidBy;
	}
	public String getCustomDutyPaidBy() {
		return customDutyPaidBy;
	}
	public void setCustomDutyPaidBy(String customDutyPaidBy) {
		this.customDutyPaidBy = customDutyPaidBy;
	}
	public String getChargeDesc() {
		return chargeDesc;
	}
	public void setChargeDesc(String chargeDesc) {
		this.chargeDesc = chargeDesc;
	}
	public Double getChargeAmt() {
		return chargeAmt;
	}
	public void setChargeAmt(Double chargeAmt) {
		this.chargeAmt = chargeAmt;
	}
	public String getDropShipment() {
		return dropShipment;
	}
	public void setDropShipment(String dropShipment) {
		this.dropShipment = dropShipment;
	}
	public String getShipTo() {
		return shipTo;
	}
	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}
	public String getBillTo() {
		return billTo;
	}
	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}
	public String getContainPersonalEffect() {
		return containPersonalEffect;
	}
	public void setContainPersonalEffect(String containPersonalEffect) {
		this.containPersonalEffect = containPersonalEffect;
	}
	public String getInsuranceRequired() {
		return insuranceRequired;
	}
	public void setInsuranceRequired(String insuranceRequired) {
		this.insuranceRequired = insuranceRequired;
	}
	public String getChargeToCustomer() {
		return chargeToCustomer;
	}
	public void setChargeToCustomer(String chargeToCustomer) {
		this.chargeToCustomer = chargeToCustomer;
	}
	public String getPrintOnDoc() {
		return printOnDoc;
	}
	public void setPrintOnDoc(String printOnDoc) {
		this.printOnDoc = printOnDoc;
	}
	public String getSpecialInstruction() {
		return specialInstruction;
	}
	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}
	public String getShipmentInstruction() {
		return shipmentInstruction;
	}
	public void setShipmentInstruction(String shipmentInstruction) {
		this.shipmentInstruction = shipmentInstruction;
	}
	public String getScreeningStatus() {
		return screeningStatus;
	}
	public void setScreeningStatus(String screeningStatus) {
		this.screeningStatus = screeningStatus;
	}
	public String getLsStatus() {
		return lsStatus;
	}
	public void setLsStatus(String lsStatus) {
		this.lsStatus = lsStatus;
	}
	public Date getScreenedOn() {
		return screenedOn;
	}
	public void setScreenedOn(Date screenedOn) {
		this.screenedOn = screenedOn;
	}
	public String getBookingNo() {
		return bookingNo;
	}
	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	public String getCountryOfOriginName() {
		return countryOfOriginName;
	}
	public void setCountryOfOriginName(String countryOfOriginName) {
		this.countryOfOriginName = countryOfOriginName;
	}
	public String getCountryOfOriginCode() {
		return countryOfOriginCode;
	}
	public void setCountryOfOriginCode(String countryOfOriginCode) {
		this.countryOfOriginCode = countryOfOriginCode;
	}
	public String getPortOfExportName() {
		return portOfExportName;
	}
	public void setPortOfExportName(String portOfExportName) {
		this.portOfExportName = portOfExportName;
	}
	public String getPortOfExportCode() {
		return portOfExportCode;
	}
	public void setPortOfExportCode(String portOfExportCode) {
		this.portOfExportCode = portOfExportCode;
	}
	public String getPortOfUnloadName() {
		return portOfUnloadName;
	}
	public void setPortOfUnloadName(String portOfUnloadName) {
		this.portOfUnloadName = portOfUnloadName;
	}
	public String getPortOfUnloadCode() {
		return portOfUnloadCode;
	}
	public void setPortOfUnloadCode(String portOfUnloadCode) {
		this.portOfUnloadCode = portOfUnloadCode;
	}
	public String getPortOfArrivalName() {
		return portOfArrivalName;
	}
	public void setPortOfArrivalName(String portOfArrivalName) {
		this.portOfArrivalName = portOfArrivalName;
	}
	public String getPortOfArrivalCode() {
		return portOfArrivalCode;
	}
	public void setPortOfArrivalCode(String portOfArrivalCode) {
		this.portOfArrivalCode = portOfArrivalCode;
	}
	public String getShipmentCarrierName() {
		return shipmentCarrierName;
	}
	public void setShipmentCarrierName(String shipmentCarrierName) {
		this.shipmentCarrierName = shipmentCarrierName;
	}
	public String getShipmentCarrierCode() {
		return shipmentCarrierCode;
	}
	public void setShipmentCarrierCode(String shipmentCarrierCode) {
		this.shipmentCarrierCode = shipmentCarrierCode;
	}
	public String getFinalDestAirport() {
		return finalDestAirport;
	}
	public void setFinalDestAirport(String finalDestAirport) {
		this.finalDestAirport = finalDestAirport;
	}
	public String getVesseFlightNo() {
		return vesseFlightNo;
	}
	public void setVesseFlightNo(String vesseFlightNo) {
		this.vesseFlightNo = vesseFlightNo;
	}
	public String getTransportDocNo() {
		return transportDocNo;
	}
	public void setTransportDocNo(String transportDocNo) {
		this.transportDocNo = transportDocNo;
	}
	public String getEeiNo() {
		return eeiNo;
	}
	public void setEeiNo(String eeiNo) {
		this.eeiNo = eeiNo;
	}
	public String getAesStatus() {
		return aesStatus;
	}
	public void setAesStatus(String aesStatus) {
		this.aesStatus = aesStatus;
	}
	public String getItn() {
		return itn;
	}
	public void setItn(String itn) {
		this.itn = itn;
	}
	public String getXtn() {
		return xtn;
	}
	public void setXtn(String xtn) {
		this.xtn = xtn;
	}
	public String getRoutedExpTxn() {
		return routedExpTxn;
	}
	public void setRoutedExpTxn(String routedExpTxn) {
		this.routedExpTxn = routedExpTxn;
	}
	public String getRelatedPrtyTxn() {
		return relatedPrtyTxn;
	}
	public void setRelatedPrtyTxn(String relatedPrtyTxn) {
		this.relatedPrtyTxn = relatedPrtyTxn;
	}
	public String getStateOfOrigin() {
		return stateOfOrigin;
	}
	public void setStateOfOrigin(String stateOfOrigin) {
		this.stateOfOrigin = stateOfOrigin;
	}
	public String getFtzNo() {
		return ftzNo;
	}
	public void setFtzNo(String ftzNo) {
		this.ftzNo = ftzNo;
	}
	public String getCorrectionCode() {
		return correctionCode;
	}
	public void setCorrectionCode(String correctionCode) {
		this.correctionCode = correctionCode;
	}
	public String getShipmentRefNo() {
		return shipmentRefNo;
	}
	public void setShipmentRefNo(String shipmentRefNo) {
		this.shipmentRefNo = shipmentRefNo;
	}
	public String getHazdarous() {
		return hazdarous;
	}
	public void setHazdarous(String hazdarous) {
		this.hazdarous = hazdarous;
	}
	public String getMotCode() {
		return motCode;
	}
	public void setMotCode(String motCode) {
		this.motCode = motCode;
	}
	public String getPortOfUnlanding() {
		return portOfUnlanding;
	}
	public void setPortOfUnlanding(String portOfUnlanding) {
		this.portOfUnlanding = portOfUnlanding;
	}
	public String getPortOfUnlandingCode() {
		return portOfUnlandingCode;
	}
	public void setPortOfUnlandingCode(String portOfUnlandingCode) {
		this.portOfUnlandingCode = portOfUnlandingCode;
	}
	public String getTransportRefNo() {
		return transportRefNo;
	}
	public void setTransportRefNo(String transportRefNo) {
		this.transportRefNo = transportRefNo;
	}
	public String getEntryNumber() {
		return entryNumber;
	}
	public void setEntryNumber(String entryNumber) {
		this.entryNumber = entryNumber;
	}
	public String getConveyanceName() {
		return conveyanceName;
	}
	public void setConveyanceName(String conveyanceName) {
		this.conveyanceName = conveyanceName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public List<String> getMsgs() {
		return msgs;
	}
	public void setMsgs(List<String> msgs) {
		this.msgs = msgs;
	}
	public String getAesOption() {
		return aesOption;
	}
	public void setAesOption(String aesOption) {
		this.aesOption = aesOption;
	}
	public String getSbuCode() {
		return sbuCode;
	}
	public void setSbuCode(String sbuCode) {
		this.sbuCode = sbuCode;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getRelatedPartTransaction() {
		return relatedPartTransaction;
	}
	public void setRelatedPartTransaction(String relatedPartTransaction) {
		this.relatedPartTransaction = relatedPartTransaction;
	}
	public String getEaseStatus() {
		return easeStatus;
	}
	public void setEaseStatus(String easeStatus) {
		this.easeStatus = easeStatus;
	}
	public Date getEaseSubmitDate() {
		return easeSubmitDate;
	}
	public void setEaseSubmitDate(Date easeSubmitDate) {
		this.easeSubmitDate = easeSubmitDate;
	}
	public BigDecimal getShipmentValue() {
		return shipmentValue;
	}
	public void setShipmentValue(BigDecimal shipmentValue) {
		this.shipmentValue = shipmentValue;
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
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getRates() {
		return rates;
	}
	public void setRates(String rates) {
		this.rates = rates;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	
}

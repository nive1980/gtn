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
@Table(name="SHIPMENTS")
@Access(AccessType.PROPERTY)
public class Shipment implements Model{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6531981690771917145L;
	
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "SHIPMENT_NO", unique = true)
	public String getShipmentNo() {
		return shipmentNo;
	}
	public void setShipmentNo(String shipmentNo) {
		this.shipmentNo = shipmentNo;
	}
	
	@Column(name = "EXPORT_DATE")
	public Date getExportDate() {
		return exportDate;
	}
	public void setExportDate(Date exportDate) {
		this.exportDate = exportDate;
	}
	
	@Column(name = "EXPORT_COUNTRY_NAME")
	public String getCountryOfExportName() {
		return countryOfExportName;
	}
	public void setCountryOfExportName(String countryOfExportName) {
		this.countryOfExportName = countryOfExportName;
	}
	
	@Column(name = "EXPORT_COUNTRY_CODE")
	public String getCountryOfExportCode() {
		return countryOfExportCode;
	}
	public void setCountryOfExportCode(String countryOfExportCode) {
		this.countryOfExportCode = countryOfExportCode;
	}
	
	@Column(name = "ULT_CONSIGNEE_COUNTRY_NAME")
	public String getCountryOfUltConsigneeName() {
		return countryOfUltConsigneeName;
	}
	public void setCountryOfUltConsigneeName(String countryOfUltConsigneeName) {
		this.countryOfUltConsigneeName = countryOfUltConsigneeName;
	}
	
	@Column(name = "ULT_CONSIGNEE_COUNTRY_CODE")
	public String getCountryOfUltConsigneeCode() {
		return countryOfUltConsigneeCode;
	}
	public void setCountryOfUltConsigneeCode(String countryOfUltConsigneeCode) {
		this.countryOfUltConsigneeCode = countryOfUltConsigneeCode;
	}
	
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "CREATED_ON")
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "MODIFIED_ON")
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	@Column(name = "MODIFIED_BY")
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@Column(name = "EXPORTER_FIRST_NAME")
	public String getExporterFirstName() {
		return exporterFirstName;
	}
	public void setExporterFirstName(String exporterFirstName) {
		this.exporterFirstName = exporterFirstName;
	}
	
	@Column(name = "EXPORTER_LAST_NAME")
	public String getExporterLastName() {
		return exporterLastName;
	}
	public void setExporterLastName(String exporterLastName) {
		this.exporterLastName = exporterLastName;
	}
	
	@Column(name = "EXPORTER_COMPANY_NAME")
	public String getExporterCompanyName() {
		return exporterCompanyName;
	}
	public void setExporterCompanyName(String exporterCompanyName) {
		this.exporterCompanyName = exporterCompanyName;
	}
	
	@Column(name = "EXPORTER_PHONE_NO")
	public String getExporterPhoneNo() {
		return exporterPhoneNo;
	}
	public void setExporterPhoneNo(String exporterPhoneNo) {
		this.exporterPhoneNo = exporterPhoneNo;
	}
	
	@Column(name = "EXPORTER_EMAIL")
	public String getExporterEmail() {
		return exporterEmail;
	}
	public void setExporterEmail(String exporterEmail) {
		this.exporterEmail = exporterEmail;
	}
	
	@Column(name = "EXPORTER_NAME")
	public String getExporterName() {
		return exporterName;
	}
	public void setExporterName(String exporterName) {
		this.exporterName = exporterName;
	}
	
	@Column(name = "EXPORTER_ADDR_LINE1")
	public String getExporterAddressLine1() {
		return exporterAddressLine1;
	}
	public void setExporterAddressLine1(String exporterAddressLine1) {
		this.exporterAddressLine1 = exporterAddressLine1;
	}
	
	@Column(name = "EXPORTER_ADDR_LINE2")
	public String getExporterAddressLine2() {
		return exporterAddressLine2;
	}
	public void setExporterAddressLine2(String exporterAddressLine2) {
		this.exporterAddressLine2 = exporterAddressLine2;
	}
	
	@Column(name = "EXPORTER_CITY")
	public String getExporterCity() {
		return exporterCity;
	}
	public void setExporterCity(String exporterCity) {
		this.exporterCity = exporterCity;
	}
	
	@Column(name = "EXPORTER_STATE")
	public String getExporterState() {
		return exporterState;
	}
	public void setExporterState(String exporterState) {
		this.exporterState = exporterState;
	}
	
	@Column(name = "EXPORTER_STATE_NAME")
	public String getExporterStateName() {
		return exporterStateName;
	}
	public void setExporterStateName(String exporterStateName) {
		this.exporterStateName = exporterStateName;
	}
	@Column(name = "EXPORTER_COUNTRY")
	public String getExporterCountry() {
		return exporterCountry;
	}
	public void setExporterCountry(String exporterCountry) {
		this.exporterCountry = exporterCountry;
	}
	
	@Column(name = "EXPORTER_ZIP")
	public String getExporterZip() {
		return exporterZip;
	}
	public void setExporterZip(String exporterZip) {
		this.exporterZip = exporterZip;
	}
	
	@Column(name = "USPPI_MOT")
	public String getUsppiMot() {
		return usppiMot;
	}
	public void setUsppiMot(String usppiMot) {
		this.usppiMot = usppiMot;
	}
	
	@Column(name = "USPPI_FIRST_NAME")
	public String getUsspiFirstName() {
		return usspiFirstName;
	}
	public void setUsspiFirstName(String usspiFirstName) {
		this.usspiFirstName = usspiFirstName;
	}
	
	@Column(name = "USPPI_LAST_NAME")
	public String getUsspiLastName() {
		return usspiLastName;
	}
	public void setUsspiLastName(String usspiLastName) {
		this.usspiLastName = usspiLastName;
	}
	
	@Column(name = "USPPI_PHONE")
	public String getUsspiPhone() {
		return usspiPhone;
	}
	public void setUsspiPhone(String usspiPhone) {
		this.usspiPhone = usspiPhone;
	}
	
	@Column(name = "USPPI_PORT_EXPORT")
	public String getUsppiPortOfExport() {
		return usppiPortOfExport;
	}
	public void setUsppiPortOfExport(String usppiPortOfExport) {
		this.usppiPortOfExport = usppiPortOfExport;
	}
	
	@Column(name = "USPPI_EXPORTER_ID")
	public String getUsppiExporterId() {
		return usppiExporterId;
	}
	public void setUsppiExporterId(String usppiExporterId) {
		this.usppiExporterId = usppiExporterId;
	}
	
	@Column(name = "USPPI_EXPORTER_IDTYPE")
	public String getUsppiExporterIdType() {
		return usppiExporterIdType;
	}
	public void setUsppiExporterIdType(String usppiExporterIdType) {
		this.usppiExporterIdType = usppiExporterIdType;
	}
	
	@Column(name = "EXPORTER_COUNTRY_NAME")
	public String getExporterCountryName() {
		return exporterCountryName;
	}
	public void setExporterCountryName(String exporterCountryName) {
		this.exporterCountryName = exporterCountryName;
	}
	
	
	@Column(name = "UTL_CONS_NAME")
	public String getUltConsigneeName() {
		return ultConsigneeName;
	}
	public void setUltConsigneeName(String ultConsigneeName) {
		this.ultConsigneeName = ultConsigneeName;
	}
	
	@Column(name = "UTL_CONS_FIRST_NAME")
	public String getUltConsigneeFirstName() {
		return ultConsigneeFirstName;
	}
	public void setUltConsigneeFirstName(String ultConsigneeFirstName) {
		this.ultConsigneeFirstName = ultConsigneeFirstName;
	}
	
	@Column(name = "UTL_CONS_ADDR1")
	public String getUltConsigneeAddrLine1() {
		return ultConsigneeAddrLine1;
	}
	public void setUltConsigneeAddrLine1(String ultConsigneeAddrLine1) {
		this.ultConsigneeAddrLine1 = ultConsigneeAddrLine1;
	}
	
	@Column(name = "UTL_CONS_ADDR2")
	public String getUltConsigneeAddrLine2() {
		return ultConsigneeAddrLine2;
	}
	public void setUltConsigneeAddrLine2(String ultConsigneeAddrLine2) {
		this.ultConsigneeAddrLine2 = ultConsigneeAddrLine2;
	}
	
	@Column(name = "UTL_CONS_CITY")
	public String getUltConsigneeCity() {
		return ultConsigneeCity;
	}
	public void setUltConsigneeCity(String ultConsigneeCity) {
		this.ultConsigneeCity = ultConsigneeCity;
	}
	
	@Column(name = "UTL_CONS_STATE")
	public String getUltConsigneeState() {
		return ultConsigneeState;
	}
	public void setUltConsigneeState(String ultConsigneeState) {
		this.ultConsigneeState = ultConsigneeState;
	}
	
	@Column(name = "UTL_CONS_STATE_NAME")
	public String getUltConsigneeStateName() {
		return ultConsigneeStateName;
	}
	public void setUltConsigneeStateName(String ultConsigneeStateName) {
		this.ultConsigneeStateName = ultConsigneeStateName;
	}
	@Column(name = "UTL_CONS_COUNTRY_CODE")
	public String getUltConsigneeCountryCode() {
		return ultConsigneeCountryCode;
	}
	public void setUltConsigneeCountryCode(String ultConsigneeCountryCode) {
		this.ultConsigneeCountryCode = ultConsigneeCountryCode;
	}
	
	@Column(name = "UTL_CONS_COUNTRY_NAME")
	public String getUltConsigneeCountryName() {
		return ultConsigneeCountryName;
	}
	public void setUltConsigneeCountryName(String ultConsigneeCountryName) {
		this.ultConsigneeCountryName = ultConsigneeCountryName;
	}
	
	@Column(name = "UTL_CONS_ZIP")
	public String getUltConsigneeZip() {
		return ultConsigneeZip;
	}
	public void setUltConsigneeZip(String ultConsigneeZip) {
		this.ultConsigneeZip = ultConsigneeZip;
	}
	
	@Column(name = "UTL_CONS_COMPANY_NAME")
	public String getUltConsigneeCompanyName() {
		return ultConsigneeCompanyName;
	}
	public void setUltConsigneeCompanyName(String ultConsigneeCompanyName) {
		this.ultConsigneeCompanyName = ultConsigneeCompanyName;
	}
	
	@Column(name = "UTL_CONS_PHONE")
	public String getUltConsigneePhone() {
		return ultConsigneePhone;
	}
	public void setUltConsigneePhone(String ultConsigneePhone) {
		this.ultConsigneePhone = ultConsigneePhone;
	}
	
	@Column(name = "UTL_CONS_EMAIL")
	public String getUltConsigneeEmail() {
		return ultConsigneeEmail;
	}
	public void setUltConsigneeEmail(String ultConsigneeEmail) {
		this.ultConsigneeEmail = ultConsigneeEmail;
	}
	
	@Column(name = "UTL_CONS_LAST_NAME")
	public String getUltConsigneeLastName() {
		return ultConsigneeLastName;
	}
	public void setUltConsigneeLastName(String ultConsigneeLastName) {
		this.ultConsigneeLastName = ultConsigneeLastName;
	}
	
	
	@Column(name = "INTER_CONS_NAME")
	public String getInterConsigneeName() {
		return interConsigneeName;
	}
	public void setInterConsigneeName(String interConsigneeName) {
		this.interConsigneeName = interConsigneeName;
	}
	
	@Column(name = "INTER_CONS_FIRST_NAME")
	public String getInterConsigneeFirstName() {
		return interConsigneeFirstName;
	}
	public void setInterConsigneeFirstName(String interConsigneeFirstName) {
		this.interConsigneeFirstName = interConsigneeFirstName;
	}
	
	@Column(name = "INTER_CONS_LAST_NAME")
	public String getInterConsigneeLastName() {
		return interConsigneeLastName;
	}
	public void setInterConsigneeLastName(String interConsigneeLastName) {
		this.interConsigneeLastName = interConsigneeLastName;
	}
	
	@Column(name = "INTER_CONS_ADDR1")
	public String getInterConsigneeAddrLine1() {
		return interConsigneeAddrLine1;
	}
	public void setInterConsigneeAddrLine1(String interConsigneeAddrLine1) {
		this.interConsigneeAddrLine1 = interConsigneeAddrLine1;
	}
	
	@Column(name = "INTER_CONS_ADDR2")
	public String getInterConsigneeAddrLine2() {
		return interConsigneeAddrLine2;
	}
	public void setInterConsigneeAddrLine2(String interConsigneeAddrLine2) {
		this.interConsigneeAddrLine2 = interConsigneeAddrLine2;
	}
	
	@Column(name = "INTER_CONS_CITY")
	public String getInterConsigneeCity() {
		return interConsigneeCity;
	}
	public void setInterConsigneeCity(String interConsigneeCity) {
		this.interConsigneeCity = interConsigneeCity;
	}
	
	@Column(name = "INTER_CONS_STATE")
	public String getInterConsigneeState() {
		return interConsigneeState;
	}
	public void setInterConsigneeState(String interConsigneeState) {
		this.interConsigneeState = interConsigneeState;
	}
	
	@Column(name = "INTER_CONS_STATE_NAME")
	public String getInterConsigneeStateName() {
		return interConsigneeStateName;
	}
	public void setInterConsigneeStateName(String interConsigneeStateName) {
		this.interConsigneeStateName = interConsigneeStateName;
	}
	@Column(name = "INTER_CONS_COUNTRY_CODE")
	public String getInterConsigneeCountryCode() {
		return interConsigneeCountryCode;
	}
	public void setInterConsigneeCountryCode(String interConsigneeCountryCode) {
		this.interConsigneeCountryCode = interConsigneeCountryCode;
	}
	
	@Column(name = "INTER_CONS_COUNTRY_NAME")
	public String getInterConsigneeCountryName() {
		return interConsigneeCountryName;
	}
	public void setInterConsigneeCountryName(String interConsigneeCountryName) {
		this.interConsigneeCountryName = interConsigneeCountryName;
	}
	
	@Column(name = "INTER_CONS_ZIP")
	public String getInterConsigneeZip() {
		return interConsigneeZip;
	}
	public void setInterConsigneeZip(String interConsigneeZip) {
		this.interConsigneeZip = interConsigneeZip;
	}
	
	@Column(name = "INTER_CONS_COMPANY_NAME")
	public String getInterConsigneeCompanyName() {
		return interConsigneeCompanyName;
	}
	public void setInterConsigneeCompanyName(String interConsigneeCompanyName) {
		this.interConsigneeCompanyName = interConsigneeCompanyName;
	}
	
	@Column(name = "INTER_CONS_PHONE")
	public String getInterConsigneePhone() {
		return interConsigneePhone;
	}
	public void setInterConsigneePhone(String interConsigneePhone) {
		this.interConsigneePhone = interConsigneePhone;
	}
	
	@Column(name = "INTER_CONS_EMAIL")
	public String getInterConsigneeEmail() {
		return interConsigneeEmail;
	}
	public void setInterConsigneeEmail(String interConsigneeEmail) {
		this.interConsigneeEmail = interConsigneeEmail;
	}
	
	@Column(name = "FREIGHT_FW_NAME")
	public String getFreightForwaderName() {
		return freightForwaderName;
	}
	public void setFreightForwaderName(String freightForwaderName) {
		this.freightForwaderName = freightForwaderName;
	}
	
	@Column(name = "FREIGHT_FW_FIRST_NAME")
	public String getFreightForwaderFirstName() {
		return freightForwaderFirstName;
	}
	public void setFreightForwaderFirstName(String freightForwaderFirstName) {
		this.freightForwaderFirstName = freightForwaderFirstName;
	}
	
	@Column(name = "FREIGHT_FW_LAST_NAME")
	public String getFreightForwaderLastName() {
		return freightForwaderLastName;
	}
	public void setFreightForwaderLastName(String freightForwaderLastName) {
		this.freightForwaderLastName = freightForwaderLastName;
	}
	
	@Column(name = "FREIGHT_FW_ADDR1")
	public String getFreightForwaderAddrLine1() {
		return freightForwaderAddrLine1;
	}
	public void setFreightForwaderAddrLine1(String freightForwaderAddrLine1) {
		this.freightForwaderAddrLine1 = freightForwaderAddrLine1;
	}
	
	@Column(name = "FREIGHT_FW_ADDR2")
	public String getFreightForwaderAddrLine2() {
		return freightForwaderAddrLine2;
	}
	public void setFreightForwaderAddrLine2(String freightForwaderAddrLine2) {
		this.freightForwaderAddrLine2 = freightForwaderAddrLine2;
	}
	
	@Column(name = "FREIGHT_FW_CITY")
	public String getFreightForwaderCity() {
		return freightForwaderCity;
	}
	public void setFreightForwaderCity(String freightForwaderCity) {
		this.freightForwaderCity = freightForwaderCity;
	}
	
	@Column(name = "FREIGHT_FW_STATE")
	public String getFreightForwaderState() {
		return freightForwaderState;
	}
	public void setFreightForwaderState(String freightForwaderState) {
		this.freightForwaderState = freightForwaderState;
	}
	
	@Column(name = "FREIGHT_FW_STATE_NAME")
	public String getFreightForwaderStateName() {
		return freightForwaderStateName;
	}
	public void setFreightForwaderStateName(String freightForwaderStateName) {
		this.freightForwaderStateName = freightForwaderStateName;
	}
	@Column(name = "FREIGHT_FW_COUNTRY_CODE")
	public String getFreightForwaderCountryCode() {
		return freightForwaderCountryCode;
	}
	public void setFreightForwaderCountryCode(String freightForwaderCountryCode) {
		this.freightForwaderCountryCode = freightForwaderCountryCode;
	}
	
	@Column(name = "FREIGHT_FW_COUNTRY_NAME")
	public String getFreightForwaderCountryName() {
		return freightForwaderCountryName;
	}
	public void setFreightForwaderCountryName(String freightForwaderCountryName) {
		this.freightForwaderCountryName = freightForwaderCountryName;
	}
	
	@Column(name = "FREIGHT_FW_ZIP")
	public String getFreightForwaderZip() {
		return freightForwaderZip;
	}
	public void setFreightForwaderZip(String freightForwaderZip) {
		this.freightForwaderZip = freightForwaderZip;
	}
	
	@Column(name = "FREIGHT_FW_COMPANY_NAME")
	public String getFreightForwaderCompanyName() {
		return freightForwaderCompanyName;
	}
	public void setFreightForwaderCompanyName(String freightForwaderCompanyName) {
		this.freightForwaderCompanyName = freightForwaderCompanyName;
	}
	
	@Column(name = "FREIGHT_FW_PHONE")
	public String getFreightForwaderPhone() {
		return freightForwaderPhone;
	}
	public void setFreightForwaderPhone(String freightForwaderPhone) {
		this.freightForwaderPhone = freightForwaderPhone;
	}
	
	@Column(name = "FREIGHT_FW_EMAIL")
	public String getFreightForwaderEmail() {
		return freightForwaderEmail;
	}
	public void setFreightForwaderEmail(String freightForwaderEmail) {
		this.freightForwaderEmail = freightForwaderEmail;
	}
	
	
	@Column(name = "PAYMENT_TERMS")
	public String getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	
	@Column(name = "INCO_TERMS")
	public String getIncoTerms() {
		return incoTerms;
	}
	public void setIncoTerms(String incoTerms) {
		this.incoTerms = incoTerms;
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
	
	@Column(name = "PO_NUMBER")
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	
	@Column(name = "MODE_OF_TRANSPORT")
	public String getModeOfTransport() {
		return modeOfTransport;
	}
	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}
	
	@Column(name = "FREIGHT_CHARGES_PAID_BY")
	public String getFreightChargesPaidBy() {
		return freightChargesPaidBy;
	}
	public void setFreightChargesPaidBy(String freightChargesPaidBy) {
		this.freightChargesPaidBy = freightChargesPaidBy;
	}
	
	@Column(name = "FOREIGN_VAT_PAID_BY")
	public String getForeignVatPaidBy() {
		return foreignVatPaidBy;
	}
	public void setForeignVatPaidBy(String foreignVatPaidBy) {
		this.foreignVatPaidBy = foreignVatPaidBy;
	}
	
	@Column(name = "CUSTOM_DUTY_PAID_BY")
	public String getCustomDutyPaidBy() {
		return customDutyPaidBy;
	}
	public void setCustomDutyPaidBy(String customDutyPaidBy) {
		this.customDutyPaidBy = customDutyPaidBy;
	}
	
	@Column(name = "CHARGE_DESC")
	public String getChargeDesc() {
		return chargeDesc;
	}
	public void setChargeDesc(String chargeDesc) {
		this.chargeDesc = chargeDesc;
	}
	
	@Column(name = "CHARGE_AMT")
	public Double getChargeAmt() {
		return chargeAmt;
	}
	public void setChargeAmt(Double chargeAmt) {
		this.chargeAmt = chargeAmt;
	}
	
	@Column(name = "DROP_SHIPMENT")
	public String getDropShipment() {
		return dropShipment;
	}
	public void setDropShipment(String dropShipment) {
		this.dropShipment = dropShipment;
	}
	
	@Column(name = "SHIP_TO")
	public String getShipTo() {
		return shipTo;
	}
	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}
	
	@Column(name = "BILL_TO")
	public String getBillTo() {
		return billTo;
	}
	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}
	
	@Column(name = "CONTAIN_PERSONAL_EFFECT")
	public String getContainPersonalEffect() {
		return containPersonalEffect;
	}
	public void setContainPersonalEffect(String containPersonalEffect) {
		this.containPersonalEffect = containPersonalEffect;
	}
	
	@Column(name = "INSURANCE_REQUIRED")
	public String getInsuranceRequired() {
		return insuranceRequired;
	}
	public void setInsuranceRequired(String insuranceRequired) {
		this.insuranceRequired = insuranceRequired;
	}
	
	@Column(name = "CHARGE_TO_CUSTOMER")
	public String getChargeToCustomer() {
		return chargeToCustomer;
	}
	public void setChargeToCustomer(String chargeToCustomer) {
		this.chargeToCustomer = chargeToCustomer;
	}
	
	@Column(name = "PRINT_ON_DOC")
	public String getPrintOnDoc() {
		return printOnDoc;
	}
	public void setPrintOnDoc(String printOnDoc) {
		this.printOnDoc = printOnDoc;
	}
	
	@Column(name = "SPECIAL_INSTRUCTION")
	public String getSpecialInstruction() {
		return specialInstruction;
	}
	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}
	
	@Column(name = "SHIPMENT_INSTRUCTION")
	public String getShipmentInstruction() {
		return shipmentInstruction;
	}
	public void setShipmentInstruction(String shipmentInstruction) {
		this.shipmentInstruction = shipmentInstruction;
	}
	
	@Column(name = "SCREENING_STATUS")
	public String getScreeningStatus() {
		return screeningStatus;
	}
	public void setScreeningStatus(String screeningStatus) {
		this.screeningStatus = screeningStatus;
	}
	
	@Column(name = "SCREENED_ON")
	public Date getScreenedOn() {
		return screenedOn;
	}
	public void setScreenedOn(Date screenedOn) {
		this.screenedOn = screenedOn;
	}
	
	@Column(name = "LS_STATUS")
	public String getLsStatus() {
		return lsStatus;
	}
	public void setLsStatus(String lsStatus) {
		this.lsStatus = lsStatus;
	}
	
	@Transient
	public List<String> getMsgs() {
		return msgs;
	}
	public void setMsgs(List<String> msgs) {
		this.msgs = msgs;
	}
	
	@Transient
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	@Column(name = "EXPORTER_CODE")
	public String getExporterCode() {
		return exporterCode;
	}
	public void setExporterCode(String exporterCode) {
		this.exporterCode = exporterCode;
	}
	
	@Column(name = "ULT_CONSIGNEE_CODE")
	public String getUltConsigneeCode() {
		return ultConsigneeCode;
	}
	public void setUltConsigneeCode(String ultConsigneeCode) {
		this.ultConsigneeCode = ultConsigneeCode;
	}
	
	@Column(name = "INTER_CONSIGNEE_CODE")
	public String getInterConsigneeCode() {
		return interConsigneeCode;
	}
	public void setInterConsigneeCode(String interConsigneeCode) {
		this.interConsigneeCode = interConsigneeCode;
	}
	
	@Column(name = "FF_CODE")
	public String getFreightForwaderCode() {
		return freightForwaderCode;
	}
	public void setFreightForwaderCode(String freightForwaderCode) {
		this.freightForwaderCode = freightForwaderCode;
	}
	
	@Column(name = "SHIP_TO_CODE")
	public String getShipToCode() {
		return shipToCode;
	}
	public void setShipToCode(String shipToCode) {
		this.shipToCode = shipToCode;
	}
	
	@Column(name = "SHIP_TO_NAME")
	public String getShipToName() {
		return shipToName;
	}
	public void setShipToName(String shipToName) {
		this.shipToName = shipToName;
	}
	
	@Column(name = "SHIP_TO_CITY")
	public String getShipToCity() {
		return shipToCity;
	}
	public void setShipToCity(String shipToCity) {
		this.shipToCity = shipToCity;
	}
	
	@Column(name = "SHIP_TO_STATE")
	public String getShipToState() {
		return shipToState;
	}
	public void setShipToState(String shipToState) {
		this.shipToState = shipToState;
	}
	
	@Column(name = "SHIP_TO_COUNTRY")
	public String getShipToCountry() {
		return shipToCountry;
	}
	public void setShipToCountry(String shipToCountry) {
		this.shipToCountry = shipToCountry;
	}
	
	@Column(name = "SHIP_TO_ZIP")
	public String getShipToZip() {
		return shipToZip;
	}
	public void setShipToZip(String shipToZip) {
		this.shipToZip = shipToZip;
	}
	
	@Column(name = "SHIP_TO_ADDR1")
	public String getShipToAddr1() {
		return shipToAddr1;
	}
	public void setShipToAddr1(String shipToAddr1) {
		this.shipToAddr1 = shipToAddr1;
	}
	
	@Column(name = "SHIP_TO_ADDR2")
	public String getShipToAddr2() {
		return shipToAddr2;
	}
	public void setShipToAddr2(String shipToAddr2) {
		this.shipToAddr2 = shipToAddr2;
	}
	
	@Column(name = "BILL_TO_CODE")
	public String getBillToCode() {
		return billToCode;
	}
	public void setBillToCode(String billToCode) {
		this.billToCode = billToCode;
	}
	
	@Column(name = "BILL_TO_NAME")
	public String getBillToName() {
		return billToName;
	}
	public void setBillToName(String billToName) {
		this.billToName = billToName;
	}
	
	@Column(name = "BILL_TO_CITY")
	public String getBillToCity() {
		return billToCity;
	}
	public void setBillToCity(String billToCity) {
		this.billToCity = billToCity;
	}
	
	@Column(name = "BILL_TO_STATE")
	public String getBillToState() {
		return billToState;
	}
	public void setBillToState(String billToState) {
		this.billToState = billToState;
	}
	
	@Column(name = "BILL_TO_COUNTRY")
	public String getBillToCountry() {
		return billToCountry;
	}
	public void setBillToCountry(String billToCountry) {
		this.billToCountry = billToCountry;
	}
	
	@Column(name = "BILL_TO_ZIP")
	public String getBillToZip() {
		return billToZip;
	}
	public void setBillToZip(String billToZip) {
		this.billToZip = billToZip;
	}
	
	@Column(name = "BILL_TO_ADDR1")
	public String getBillToAddr1() {
		return billToAddr1;
	}
	public void setBillToAddr1(String billToAddr1) {
		this.billToAddr1 = billToAddr1;
	}
	
	@Column(name = "BILL_TO_ADDR2")
	public String getBillToAddr2() {
		return billToAddr2;
	}
	public void setBillToAddr2(String billToAddr2) {
		this.billToAddr2 = billToAddr2;
	}
	
	@Column(name = "AES_OPTION")
	public String getAesOption() {
		return aesOption;
	}
	public void setAesOption(String aesOption) {
		this.aesOption = aesOption;
	}
	
	@Column(name = "ULT_CONS_TYPE")
	public String getUltConsigneeType() {
		return ultConsigneeType;
	}
	public void setUltConsigneeType(String ultConsigneeType) {
		this.ultConsigneeType = ultConsigneeType;
	}
	
	@Column(name = "TRANSMIT_TO_AES")
	public String getTransmitToAes() {
		return transmitToAes;
	}
	public void setTransmitToAes(String transmitToAes) {
		this.transmitToAes = transmitToAes;
	}
	
	@Column(name = "FF_FILTER_ID_TYPE")
	public String getFfFilerIdType() {
		return ffFilerIdType;
	}
	public void setFfFilerIdType(String ffFilerIdType) {
		this.ffFilerIdType = ffFilerIdType;
	}
	
	@Column(name = "FF_FILTER_ID_NO")
	public String getFfFilerIdNo() {
		return ffFilerIdNo;
	}
	public void setFfFilerIdNo(String ffFilerIdNo) {
		this.ffFilerIdNo = ffFilerIdNo;
	}
	
	
	
	@Column(name = "RELATED_PARTY_TRANSACTION")
	public String getRelatedPartTransaction() {
		return relatedPartTransaction;
	}
	public void setRelatedPartTransaction(String relatedPartTransaction) {
		this.relatedPartTransaction = relatedPartTransaction;
	}
	

	@Column(name = "SHIPMENT_REF_NO")
	public String getShipmentRefNo() {
		return shipmentRefNo;
	}
	public void setShipmentRefNo(String shipmentRefNo) {
		this.shipmentRefNo = shipmentRefNo;
	}
	
	@Column(name = "HAZDAROUS")
	public String getHazdarous() {
		return hazdarous;
	}
	public void setHazdarous(String hazdarous) {
		this.hazdarous = hazdarous;
	}
	
	@Column(name = "MOT_CODE")
	public String getMotCode() {
		return motCode;
	}
	public void setMotCode(String motCode) {
		this.motCode = motCode;
	}
	
	@Column(name = "PORT_UNLANDING")
	public String getPortOfUnlanding() {
		return portOfUnlanding;
	}
	public void setPortOfUnlanding(String portOfUnlanding) {
		this.portOfUnlanding = portOfUnlanding;
	}
	
	@Column(name = "PORT_UNLANDING_CODE")
	public String getPortOfUnlandingCode() {
		return portOfUnlandingCode;
	}
	public void setPortOfUnlandingCode(String portOfUnlandingCode) {
		this.portOfUnlandingCode = portOfUnlandingCode;
	}
	
	@Column(name = "TRANSPORT_REF_NO")
	public String getTransportRefNo() {
		return transportRefNo;
	}
	public void setTransportRefNo(String transportRefNo) {
		this.transportRefNo = transportRefNo;
	}
	
	@Column(name = "ENTRY_NUMBER")
	public String getEntryNumber() {
		return entryNumber;
	}
	public void setEntryNumber(String entryNumber) {
		this.entryNumber = entryNumber;
	}
	
	@Column(name = "CONVEYANCE_NAME")
	public String getConveyanceName() {
		return conveyanceName;
	}
	public void setConveyanceName(String conveyanceName) {
		this.conveyanceName = conveyanceName;
	}
	
	@Column(name = "BOOKING_NO")
	public String getBookingNo() {
		return bookingNo;
	}
	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}
	
	@Column(name = "TRACKING_NO")
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	
	@Column(name = "COO_NAME")
	public String getCountryOfOriginName() {
		return countryOfOriginName;
	}
	public void setCountryOfOriginName(String countryOfOriginName) {
		this.countryOfOriginName = countryOfOriginName;
	}
	
	@Column(name = "COO_CODE")
	public String getCountryOfOriginCode() {
		return countryOfOriginCode;
	}
	public void setCountryOfOriginCode(String countryOfOriginCode) {
		this.countryOfOriginCode = countryOfOriginCode;
	}
	
	@Column(name = "PORT_OF_EXPORT_NAME")
	public String getPortOfExportName() {
		return portOfExportName;
	}
	public void setPortOfExportName(String portOfExportName) {
		this.portOfExportName = portOfExportName;
	}
	
	@Column(name = "PORT_OF_EXPORT_CODE")
	public String getPortOfExportCode() {
		return portOfExportCode;
	}
	public void setPortOfExportCode(String portOfExportCode) {
		this.portOfExportCode = portOfExportCode;
	}
	
	@Column(name = "PORT_OF_UNLOAD_NAME")
	public String getPortOfUnloadName() {
		return portOfUnloadName;
	}
	public void setPortOfUnloadName(String portOfUnloadName) {
		this.portOfUnloadName = portOfUnloadName;
	}
	
	@Column(name = "PORT_OF_UNLOAD_CODE")
	public String getPortOfUnloadCode() {
		return portOfUnloadCode;
	}
	public void setPortOfUnloadCode(String portOfUnloadCode) {
		this.portOfUnloadCode = portOfUnloadCode;
	}
	
	@Column(name = "PORT_OF_ARRIVAL_NAME")
	public String getPortOfArrivalName() {
		return portOfArrivalName;
	}
	public void setPortOfArrivalName(String portOfArrivalName) {
		this.portOfArrivalName = portOfArrivalName;
	}
	
	@Column(name = "PORT_OF_ARRIVAL_CODE")
	public String getPortOfArrivalCode() {
		return portOfArrivalCode;
	}
	public void setPortOfArrivalCode(String portOfArrivalCode) {
		this.portOfArrivalCode = portOfArrivalCode;
	}
	
	@Column(name = "SHIPMENT_CAERRIR_NAME")
	public String getShipmentCarrierName() {
		return shipmentCarrierName;
	}
	public void setShipmentCarrierName(String shipmentCarrierName) {
		this.shipmentCarrierName = shipmentCarrierName;
	}
	
	@Column(name = "SHIPMENT_CAERRIR_CODE")
	public String getShipmentCarrierCode() {
		return shipmentCarrierCode;
	}
	public void setShipmentCarrierCode(String shipmentCarrierCode) {
		this.shipmentCarrierCode = shipmentCarrierCode;
	}
	
	@Column(name = "FINAL_DEST_AIRPORT")
	public String getFinalDestAirport() {
		return finalDestAirport;
	}
	public void setFinalDestAirport(String finalDestAirport) {
		this.finalDestAirport = finalDestAirport;
	}
	
	@Column(name = "VESSEL_FLIGHT_NO")
	public String getVesseFlightNo() {
		return vesseFlightNo;
	}
	public void setVesseFlightNo(String vesseFlightNo) {
		this.vesseFlightNo = vesseFlightNo;
	}
	
	@Column(name = "TRANSPORT_DOC_NO")
	public String getTransportDocNo() {
		return transportDocNo;
	}
	public void setTransportDocNo(String transportDocNo) {
		this.transportDocNo = transportDocNo;
	}
	
	@Column(name = "EEI_NO")
	public String getEeiNo() {
		return eeiNo;
	}
	public void setEeiNo(String eeiNo) {
		this.eeiNo = eeiNo;
	}
	
	@Column(name = "AES_STATUS")
	public String getAesStatus() {
		return aesStatus;
	}
	public void setAesStatus(String aesStatus) {
		this.aesStatus = aesStatus;
	}
	
	@Column(name = "ITN")
	public String getItn() {
		return itn;
	}
	public void setItn(String itn) {
		this.itn = itn;
	}
	
	@Column(name = "ROUTED_EXP_TXN")
	public String getRoutedExpTxn() {
		return routedExpTxn;
	}
	public void setRoutedExpTxn(String routedExpTxn) {
		this.routedExpTxn = routedExpTxn;
	}
	
	@Column(name = "RELATED_PRTY_TXN")
	public String getRelatedPrtyTxn() {
		return relatedPrtyTxn;
	}
	public void setRelatedPrtyTxn(String relatedPrtyTxn) {
		this.relatedPrtyTxn = relatedPrtyTxn;
	}
	
	@Column(name = "STATE_OF_ORIGIN")
	public String getStateOfOrigin() {
		return stateOfOrigin;
	}
	public void setStateOfOrigin(String stateOfOrigin) {
		this.stateOfOrigin = stateOfOrigin;
	}
	
	@Column(name = "FTZ_NO")
	public String getFtzNo() {
		return ftzNo;
	}
	public void setFtzNo(String ftzNo) {
		this.ftzNo = ftzNo;
	}
	
	@Column(name = "SBU_CODE")
	public String getSbuCode() {
		return sbuCode;
	}
	public void setSbuCode(String sbuCode) {
		this.sbuCode = sbuCode;
	}
	
	@Column(name = "MODE_OF_TRANSPORT_CODE")
	public String getModeOfTransportCode() {
		return modeOfTransportCode;
	}
	public void setModeOfTransportCode(String modeOfTransportCode) {
		this.modeOfTransportCode = modeOfTransportCode;
	}
	
	@Column(name = "SHIP_TO_STATE_NAME")
	public String getShipToStateName() {
		return shipToStateName;
	}
	public void setShipToStateName(String shipToStateName) {
		this.shipToStateName = shipToStateName;
	}
	
	@Column(name = "SHIP_TO_COUNTRY_NAME")
	public String getShipToCountryName() {
		return shipToCountryName;
	}
	public void setShipToCountryName(String shipToCountryName) {
		this.shipToCountryName = shipToCountryName;
	}
	
	@Column(name = "BILL_TO_STATE_NAME")
	public String getBillToStateName() {
		return billToStateName;
	}
	public void setBillToStateName(String billToStateName) {
		this.billToStateName = billToStateName;
	}
	
	@Column(name = "BILL_TO_COUNTRY_NAME")
	public String getBillToCountryName() {
		return billToCountryName;
	}
	public void setBillToCountryName(String billToCountryName) {
		this.billToCountryName = billToCountryName;
	}
	
	@Column(name = "CORRECTION_CODE")
	public String getCorrectionCode() {
		return correctionCode;
	}
	public void setCorrectionCode(String correctionCode) {
		this.correctionCode = correctionCode;
	}
	
	@Column(name = "XTN_NUMBER")
	public String getXtn() {
		return xtn;
	}
	public void setXtn(String xtn) {
		this.xtn = xtn;
	}
	
	@Column(name = "EASE_STATUS")
	public String getEaseStatus() {
		return easeStatus;
	}
	public void setEaseStatus(String easeStatus) {
		this.easeStatus = easeStatus;
	}
	
	@Column(name = "EASE_SUBMIT_DATE")
	public Date getEaseSubmitDate() {
		return easeSubmitDate;
	}
	public void setEaseSubmitDate(Date easeSubmitDate) {
		this.easeSubmitDate = easeSubmitDate;
	}
	
	@Column(name = "SHIPMENT_VALUE")
	public BigDecimal getShipmentValue() {
		return shipmentValue;
	}
	public void setShipmentValue(BigDecimal shipmentValue) {
		this.shipmentValue = shipmentValue;
	}
	
}

package com.gtn.webservices.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="SHIPMENT_HEADER")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(propOrder = {})
public class ShipmentHeader {

	@XmlElement(name="SHIPMENT_NO")
	private String shipmentNo;
	
	@XmlElement(name="EXPORTER_CODE")
	private String exporterCode;
	
	@XmlElement(name="EXPORTER_NAME")
	private String exporterName;
	
	@XmlElement(name="EXPORTER_ID_NUMBER")
	private String usppiExporterId;
	
	@XmlElement(name="EXPORTER_ID_TYPE")
	private String usppiExporterIdType;
	
	@XmlElement(name="EXPORTER_ADDRESS_LINE1")
	private String exporterAddressLine1;
	
	@XmlElement(name="EXPORTER_ADDRESS_LINE2")
	private String exporterAddressLine2;
	
	@XmlElement(name="EXPORTER_CITY")
	private String exporterCity;
	
	@XmlElement(name="EXPORTER_STATE")
	private String exporterState;
	
	@XmlElement(name="EXPORTER_ZIP_CODE")
	private String exporterZip;
	
	@XmlElement(name="EXPORTER_CONTACT_FIRST_NAME")
	private String exporterFirstName;
	
	@XmlElement(name="EXPORTER_CONTACT_MIDDLE_INITIAL")
	private String exporterMiddleName;
	
	@XmlElement(name="EXPORTER_CONTACT_LAST_NAME")
	private String exporterLastName;
	
	@XmlElement(name="EXPORTER_CONTACT_PHONE")
	private String exporterPhoneNo;
	
	@XmlElement(name="DATE_OF_EXPORT")
	private String exportDate;
	
	@XmlElement(name="ULT_CONS_CODE")
	private String ultConsigneeCode;
	
	@XmlElement(name="ULT_CONS_NAME")
	private String ultConsigneeName;
	
	@XmlElement(name="ULT_CONS_CONTACT_NAME")
	private String ultConsigneeContactName;
	
	@XmlElement(name="ULT_CONS_CONTACT_PHONE")
	private String ultConsigneePhone;
	
	@XmlElement(name="ULT_CONS_ADDR1")
	private String ultConsigneeAddrLine1;
	
	@XmlElement(name="ULT_CONS_ADDR2")
	private String ultConsigneeAddrLine2;
	
	@XmlElement(name="ULT_CONS_CITY")
	private String ultConsigneeCity;
	
	@XmlElement(name="ULT_CONS_STATE")
	private String ultConsigneeState;
	
	@XmlElement(name="ULT_CONS_STATE_NAME")
	private String ultConsigneeStateName;
	
	@XmlElement(name="ULT_CONS_COUNTRY")
	private String ultConsigneeCountryCode;
	
	@XmlElement(name="ULT_CONS_COUNTRY_NAME")
	private String ultConsigneeCountryName;
	
	@XmlElement(name="ULT_CONS_ZIP")
	private String ultConsigneeZip;
	
	@XmlElement(name="ULT_CONS_TYPE")
	private String ultConsigneeType;
	
	@XmlElement(name="ULT_CONS_STATUS")
	private String ultConsigneeStatus;
	
	@XmlElement(name="COUNTRY_OF_ULT_DEST_CODE")
	private String countryOfUltConsigneeCode;
	
	@XmlElement(name="COUNTRY_OF_ULT_DEST")
	private String countryOfUltConsigneeName;
	
	@XmlElement(name="COUNTRY_OF_ORIGIN_CODE")
	private String countryOfExportCode;
	
	@XmlElement(name="COUNTRY_OF_ORIGIN")
	private String countryOfExportName;

    @XmlElement(name="SBU_CODE")
    private String sbuCode;
    
    @XmlElement(name="CREATED_ON")
    private String createdOn;
    
    @XmlElement(name="CREATED_BY")
    private String createdBy;
    
    @XmlElement(name="MODIFIED_ON")
    private String modifiedOn;
    
    @XmlElement(name="MODIFIED_BY")
    private String modifiedBy;
    
    @XmlElement(name="AES_OPTION")
    private String aesOption;
    
    @XmlElement(name="SHIPMENT_WORK_STATUS")
    private String status;
    
    @XmlElement(name="SCREENING_STATUS")
    private String screeningStatus;
    
    @XmlElement(name="ULT_CONS_STATUS")
    private String utlConsStatus;
    
    @XmlElement(name="INT_CONS_STATUS")
    private String interConsStatus;
    
    @XmlElement(name="FF_AGENT_STATUS")
    private String ffStatus;
    
    @XmlElement(name="SKIP_WLS_SCREENING")
    private String skipWlsCheck;
    
    @XmlElement(name="STATE_OF_ORIGIN")
    private String stateOfOrigin;
    
    
    @XmlElement(name="PORT_OF_EXPORT")
    private String portOfExportName;
    @XmlElement(name="PORT_OF_EXPORT_CODE")
	private String portOfExportCode;
    
    @XmlElement(name="PORT_OF_UNLOADING")
	private String portOfUnloadName;
    @XmlElement(name="PORT_OF_UNLOADING_CODE")
	private String portOfUnloadCode;
    
    @XmlElement(name="PORT_OF_ARRIVAL")
	private String portOfArrivalName;
    @XmlElement(name="PORT_OF_ARRIVAL_CODE")
	private String portOfArrivalCode;
	
    @XmlElement(name="MODE_OF_TRANSPORT")
    private String modeOfTransport;
    
    @XmlElement(name="MODE_OF_TRANSPORT_CODE")
	private String modeOfTransportCode;
    
    @XmlElement(name="SHIPMENT_CUSTOM_FILING_STATUS")
    private String aesStatus;
            
    @XmlElement(name="ROUTED_EXPORT_TRANSACTION")
    private String routedExpTxn;
      
    @XmlElement(name="HAZARDOUS")
    private String hazdarous;
    
    @XmlElement(name="SHIPPER_REFNO")
    private String shipmentRefNo;    
    
    @XmlElement(name="CORRECTION_CODE")
    private String correctionCode;
    
    @XmlElement(name="XTN")
    private String xtn;
    
    @XmlElement(name="ITN")
    private String itn;
    
	public String getShipmentNo() {
		return shipmentNo;
	}

	public void setShipmentNo(String shipmentNo) {
		this.shipmentNo = shipmentNo;
	}

	public String getExporterCode() {
		return exporterCode;
	}

	public void setExporterCode(String exporterCode) {
		this.exporterCode = exporterCode;
	}

	public String getExporterName() {
		return exporterName;
	}

	public void setExporterName(String exporterName) {
		this.exporterName = exporterName;
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

	public String getExporterZip() {
		return exporterZip;
	}

	public void setExporterZip(String exporterZip) {
		this.exporterZip = exporterZip;
	}

	public String getExporterFirstName() {
		return exporterFirstName;
	}

	public void setExporterFirstName(String exporterFirstName) {
		this.exporterFirstName = exporterFirstName;
	}

	public String getExporterMiddleName() {
		return exporterMiddleName;
	}

	public void setExporterMiddleName(String exporterMiddleName) {
		this.exporterMiddleName = exporterMiddleName;
	}

	public String getExporterLastName() {
		return exporterLastName;
	}

	public void setExporterLastName(String exporterLastName) {
		this.exporterLastName = exporterLastName;
	}

	public String getExporterPhoneNo() {
		return exporterPhoneNo;
	}

	public void setExporterPhoneNo(String exporterPhoneNo) {
		this.exporterPhoneNo = exporterPhoneNo;
	}

	public String getExportDate() {
		return exportDate;
	}

	public void setExportDate(String exportDate) {
		this.exportDate = exportDate;
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

	public String getUltConsigneePhone() {
		return ultConsigneePhone;
	}

	public void setUltConsigneePhone(String ultConsigneePhone) {
		this.ultConsigneePhone = ultConsigneePhone;
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

	public String getUltConsigneeType() {
		return ultConsigneeType;
	}

	public void setUltConsigneeType(String ultConsigneeType) {
		this.ultConsigneeType = ultConsigneeType;
	}

	public String getUltConsigneeStatus() {
		return ultConsigneeStatus;
	}

	public void setUltConsigneeStatus(String ultConsigneeStatus) {
		this.ultConsigneeStatus = ultConsigneeStatus;
	}

	public String getCountryOfUltConsigneeCode() {
		return countryOfUltConsigneeCode;
	}

	public void setCountryOfUltConsigneeCode(String countryOfUltConsigneeCode) {
		this.countryOfUltConsigneeCode = countryOfUltConsigneeCode;
	}

	public String getCountryOfUltConsigneeName() {
		return countryOfUltConsigneeName;
	}

	public void setCountryOfUltConsigneeName(String countryOfUltConsigneeName) {
		this.countryOfUltConsigneeName = countryOfUltConsigneeName;
	}

	public String getCountryOfExportCode() {
		return countryOfExportCode;
	}

	public void setCountryOfExportCode(String countryOfExportCode) {
		this.countryOfExportCode = countryOfExportCode;
	}

	public String getCountryOfExportName() {
		return countryOfExportName;
	}

	public void setCountryOfExportName(String countryOfExportName) {
		this.countryOfExportName = countryOfExportName;
	}

	public String getSbuCode() {
		return sbuCode;
	}

	public void setSbuCode(String sbuCode) {
		this.sbuCode = sbuCode;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getAesOption() {
		return aesOption;
	}

	public void setAesOption(String aesOption) {
		this.aesOption = aesOption;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getScreeningStatus() {
		return screeningStatus;
	}

	public void setScreeningStatus(String screeningStatus) {
		this.screeningStatus = screeningStatus;
	}

	public String getUtlConsStatus() {
		return utlConsStatus;
	}

	public void setUtlConsStatus(String utlConsStatus) {
		this.utlConsStatus = utlConsStatus;
	}

	public String getInterConsStatus() {
		return interConsStatus;
	}

	public void setInterConsStatus(String interConsStatus) {
		this.interConsStatus = interConsStatus;
	}

	public String getFfStatus() {
		return ffStatus;
	}

	public void setFfStatus(String ffStatus) {
		this.ffStatus = ffStatus;
	}

	public String getSkipWlsCheck() {
		return skipWlsCheck;
	}

	public void setSkipWlsCheck(String skipWlsCheck) {
		this.skipWlsCheck = skipWlsCheck;
	}

	public String getStateOfOrigin() {
		return stateOfOrigin;
	}

	public void setStateOfOrigin(String stateOfOrigin) {
		this.stateOfOrigin = stateOfOrigin;
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

	public String getAesStatus() {
		return aesStatus;
	}

	public void setAesStatus(String aesStatus) {
		this.aesStatus = aesStatus;
	}

	public String getRoutedExpTxn() {
		return routedExpTxn;
	}

	public void setRoutedExpTxn(String routedExpTxn) {
		this.routedExpTxn = routedExpTxn;
	}

	public String getHazdarous() {
		return hazdarous;
	}

	public void setHazdarous(String hazdarous) {
		this.hazdarous = hazdarous;
	}

	public String getShipmentRefNo() {
		return shipmentRefNo;
	}

	public void setShipmentRefNo(String shipmentRefNo) {
		this.shipmentRefNo = shipmentRefNo;
	}

	public String getUltConsigneeContactName() {
		return ultConsigneeContactName;
	}

	public void setUltConsigneeContactName(String ultConsigneeContactName) {
		this.ultConsigneeContactName = ultConsigneeContactName;
	}

	public String getCorrectionCode() {
		return correctionCode;
	}

	public void setCorrectionCode(String correctionCode) {
		this.correctionCode = correctionCode;
	}

	
	
}

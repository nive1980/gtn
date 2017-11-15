package com.gtn.webservices.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="INVOICE_HEADER")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvoiceHeader {

	@XmlElement(name="INVOICE_NO")
	private String invoiceNo;
	
	@XmlElement(name="INVOICE_DATE")
	private String exportDate;
	
	@XmlElement(name="SHIPMENT_NO")
	private String shipmentNo;
	
	@XmlElement(name="ORDER_DATE")
	private String orderDate;
	
	@XmlElement(name="SHIP_TO_CODE")
	private String shipToCode;
	
	@XmlElement(name="SHIP_TO_NAME")
	private String shipToName;
	
	@XmlElement(name="SHIP_TO_CITY")
	private String shipToCity;
	
	@XmlElement(name="SHIP_TO_STATE_CODE")
	private String shipToState;
	
	@XmlElement(name="SHIP_TO_COUNTRY_CODE")
	private String shipToCountry;
	
	@XmlElement(name="SHIP_TO_ZIP")
	private String shipToZip;
	
	@XmlElement(name="SHIP_TO_ADDR1")
	private String shipToAddr1;
	
	@XmlElement(name="SHIP_TO_ADDR2")
	private String shipToAddr2;

	@XmlElement(name="SHIP_TO_ADDR3")
	private String shipToAddr3;
	
	@XmlElement(name="SHIP_TO_STATE_NAME")
	private String shipToStateName;
	
	@XmlElement(name="SHIP_TO_COUNTRY_NAME")
	private String shipToCountryName;
	
    @XmlElement(name="BILL_TO_STATUS")
	private String billToStatus;
    
	@XmlElement(name="BILL_TO_CODE")
	private String billToCode;
	
	@XmlElement(name="BILL_TO_NAME")
	private String billToName;
	
	@XmlElement(name="BILL_TO_CITY")
	private String billToCity;
	
	@XmlElement(name="BILL_TO_STATE_CODE")
	private String billToState;
	
	@XmlElement(name="BILL_TO_COUNTRY_CODE")
	private String billToCountry;
	
	@XmlElement(name="BILL_TO_ZIP")
	private String billToZip;
	
	@XmlElement(name="BILL_TO_ADDR1")
	private String billToAddr1;
	
	@XmlElement(name="BILL_TO_ADDR2")
	private String billToAddr2;

	@XmlElement(name="BILL_TO_ADDR3")
	private String billToAddr3;
	
	@XmlElement(name="BILL_TO_STATE_NAME")
	private String billToStateName;
	
	@XmlElement(name="BILL_TO_COUNTRY_NAME")
	private String billToCountryName;
	
	
	@XmlElement(name="SELLER_STATUS")
 	private String sellerStatus;
 	
	@XmlElement(name="SELLER_CODE")
	private String sellerCode;
	
	@XmlElement(name="SELLER_NAME")
	private String sellerName;
	
	
	@XmlElement(name="SELLER_ADDR1")
	private String sellerAddrLine1;
	
	@XmlElement(name="SELLER_ADDR2")
	private String sellerAddrLine2;
	
	@XmlElement(name="SELLER_ADDR3")
	private String sellerAddrLine3;
	
	@XmlElement(name="SELLER_CITY")
	private String sellerCity;
	
	@XmlElement(name="SELLER_STATE_CODE")
	private String sellerState;
	
	@XmlElement(name="SELLER_STATE_NAME")
	private String sellerStateName;
	
	@XmlElement(name="SELLER_COUNTRY_CODE")
	private String sellerCountryCode;
	
	@XmlElement(name="SELLER_COUNTRY_NAME")
	private String sellerCountryName;
	
	@XmlElement(name="SELLER_ZIP")
	private String sellerZip;
	
    
    @XmlElement(name="FORWARDING_AGENT_STATUS")
 	private String freightForwaderStatus;
 	
	@XmlElement(name="FORWARDING_AGENT_CODE")
	private String freightForwaderCode;
	
	@XmlElement(name="FORWARDING_AGENT_NAME")
	private String freightForwaderName;
	
	
	@XmlElement(name="FORWARDING_AGENT_ADDR1")
	private String freightForwaderAddrLine1;
	
	@XmlElement(name="FORWARDING_AGENT_ADDR2")
	private String freightForwaderAddrLine2;
	
	@XmlElement(name="FORWARDING_AGENT_ADDR3")
	private String freightForwaderAddrLine3;
	
	@XmlElement(name="FORWARDING_AGENT_CITY")
	private String freightForwaderCity;
	
	@XmlElement(name="FORWARD_AGENT_STATE_CODE")
	private String freightForwaderState;
	
	@XmlElement(name="FORWARD_AGENT_STATE_NAME")
	private String freightForwaderStateName;
	
	@XmlElement(name="FORWARD_AGENT_COUNTRY_CODE")
	private String freightForwaderCountryCode;
	
	@XmlElement(name="FORWARD_AGENT_COUNTRY_NAME")
	private String freightForwaderCountryName;
	
	@XmlElement(name="FORWARD_AGENT_ZIP")
	private String freightForwaderZip;
		
	@XmlElement(name="COUNTRY_OF_ULT_DEST_CODE")
	private String countryOfUltConsigneeCode;
	
	@XmlElement(name="CURRENCY_TYPE")
	private String currency;

	@XmlElement(name="CARTON_HEADER")
	List<CartonHeader> cartons;
	
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getExportDate() {
		return exportDate;
	}

	public void setExportDate(String exportDate) {
		this.exportDate = exportDate;
	}

	public String getShipmentNo() {
		return shipmentNo;
	}

	public void setShipmentNo(String shipmentNo) {
		this.shipmentNo = shipmentNo;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
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

	public String getShipToCountry() {
		return shipToCountry;
	}

	public void setShipToCountry(String shipToCountry) {
		this.shipToCountry = shipToCountry;
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

	public String getShipToAddr3() {
		return shipToAddr3;
	}

	public void setShipToAddr3(String shipToAddr3) {
		this.shipToAddr3 = shipToAddr3;
	}

	public String getShipToStateName() {
		return shipToStateName;
	}

	public void setShipToStateName(String shipToStateName) {
		this.shipToStateName = shipToStateName;
	}

	public String getShipToCountryName() {
		return shipToCountryName;
	}

	public void setShipToCountryName(String shipToCountryName) {
		this.shipToCountryName = shipToCountryName;
	}

	public String getBillToStatus() {
		return billToStatus;
	}

	public void setBillToStatus(String billToStatus) {
		this.billToStatus = billToStatus;
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

	public String getBillToCountry() {
		return billToCountry;
	}

	public void setBillToCountry(String billToCountry) {
		this.billToCountry = billToCountry;
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

	public String getBillToAddr3() {
		return billToAddr3;
	}

	public void setBillToAddr3(String billToAddr3) {
		this.billToAddr3 = billToAddr3;
	}

	public String getBillToStateName() {
		return billToStateName;
	}

	public void setBillToStateName(String billToStateName) {
		this.billToStateName = billToStateName;
	}

	public String getBillToCountryName() {
		return billToCountryName;
	}

	public void setBillToCountryName(String billToCountryName) {
		this.billToCountryName = billToCountryName;
	}

	public String getSellerStatus() {
		return sellerStatus;
	}

	public void setSellerStatus(String sellerStatus) {
		this.sellerStatus = sellerStatus;
	}

	public String getSellerCode() {
		return sellerCode;
	}

	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerAddrLine1() {
		return sellerAddrLine1;
	}

	public void setSellerAddrLine1(String sellerAddrLine1) {
		this.sellerAddrLine1 = sellerAddrLine1;
	}

	public String getSellerAddrLine2() {
		return sellerAddrLine2;
	}

	public void setSellerAddrLine2(String sellerAddrLine2) {
		this.sellerAddrLine2 = sellerAddrLine2;
	}

	public String getSellerAddrLine3() {
		return sellerAddrLine3;
	}

	public void setSellerAddrLine3(String sellerAddrLine3) {
		this.sellerAddrLine3 = sellerAddrLine3;
	}

	public String getSellerCity() {
		return sellerCity;
	}

	public void setSellerCity(String sellerCity) {
		this.sellerCity = sellerCity;
	}

	public String getSellerState() {
		return sellerState;
	}

	public void setSellerState(String sellerState) {
		this.sellerState = sellerState;
	}

	public String getSellerStateName() {
		return sellerStateName;
	}

	public void setSellerStateName(String sellerStateName) {
		this.sellerStateName = sellerStateName;
	}

	public String getSellerCountryCode() {
		return sellerCountryCode;
	}

	public void setSellerCountryCode(String sellerCountryCode) {
		this.sellerCountryCode = sellerCountryCode;
	}

	public String getSellerCountryName() {
		return sellerCountryName;
	}

	public void setSellerCountryName(String sellerCountryName) {
		this.sellerCountryName = sellerCountryName;
	}

	public String getSellerZip() {
		return sellerZip;
	}

	public void setSellerZip(String sellerZip) {
		this.sellerZip = sellerZip;
	}

	public String getFreightForwaderStatus() {
		return freightForwaderStatus;
	}

	public void setFreightForwaderStatus(String freightForwaderStatus) {
		this.freightForwaderStatus = freightForwaderStatus;
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

	public String getFreightForwaderAddrLine3() {
		return freightForwaderAddrLine3;
	}

	public void setFreightForwaderAddrLine3(String freightForwaderAddrLine3) {
		this.freightForwaderAddrLine3 = freightForwaderAddrLine3;
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

	public String getCountryOfUltConsigneeCode() {
		return countryOfUltConsigneeCode;
	}

	public void setCountryOfUltConsigneeCode(String countryOfUltConsigneeCode) {
		this.countryOfUltConsigneeCode = countryOfUltConsigneeCode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<CartonHeader> getCartons() {
		return cartons;
	}

	public void setCartons(List<CartonHeader> cartons) {
		this.cartons = cartons;
	}
	
	
}

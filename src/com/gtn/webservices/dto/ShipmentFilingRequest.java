package com.gtn.webservices.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="SHIPMENTS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"msgHeader", "shipmentHeader", "invoiceHeader", "invoiceItem", "invoiceBackItem"})
public class ShipmentFilingRequest {

	@XmlElement(name="MESSAGE_HEADER")
	private MessageHeader msgHeader;
	
	@XmlElement(name="SHIPMENT_HEADER")
	private ShipmentHeader shipmentHeader;
	
	@XmlElement(name="INVOICE_HEADER")
	private InvoiceHeader invoiceHeader;
	
	@XmlElement(name="INVOICE_ITEM")
	private List<InvoiceItem> invoiceItem;
	
	@XmlElement(name="INVOICE_BACKORDERED_ITEM")
	private InvoiceBackOrderedItem invoiceBackItem;

	public MessageHeader getMsgHeader() {
		return msgHeader;
	}

	public void setMsgHeader(MessageHeader msgHeader) {
		this.msgHeader = msgHeader;
	}

	public ShipmentHeader getShipmentHeader() {
		return shipmentHeader;
	}

	public void setShipmentHeader(ShipmentHeader shipmentHeader) {
		this.shipmentHeader = shipmentHeader;
	}

	public InvoiceHeader getInvoiceHeader() {
		return invoiceHeader;
	}

	public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}

	public List<InvoiceItem> getInvoiceItem() {
		return invoiceItem;
	}

	public void setInvoiceItem(List<InvoiceItem> invoiceItem) {
		this.invoiceItem = invoiceItem;
	}

	public InvoiceBackOrderedItem getInvoiceBackItem() {
		return invoiceBackItem;
	}

	public void setInvoiceBackItem(InvoiceBackOrderedItem invoiceBackItem) {
		this.invoiceBackItem = invoiceBackItem;
	}
	
	
}

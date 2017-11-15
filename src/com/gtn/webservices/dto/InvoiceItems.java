package com.gtn.webservices.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * PRJ#
 * 
 * @Project : EJBX_WEBSERVICES:InvoiceItems.java
 * @DateTime: 3:04 PM 1/22/2017
 * @author Sunil Kumar
 */
@XmlRootElement(name = "INVOICE_ITEMS")
@XmlType(name = "", propOrder = { "invoiceItem" })
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class InvoiceItems {
	@JsonProperty("INVOICE_ITEM")
	private List<InvoiceItem> invoiceItem;

	public List<InvoiceItem> getInvoiceItem() {
		return invoiceItem;
	}

	@XmlElement(name = "INVOICE_ITEM")
	public void setInvoiceItem(List<InvoiceItem> invoiceItem) {
		this.invoiceItem = invoiceItem;
	}

	@Override
	public String toString() {
		return "InvoiceItems [invoiceItem=" + invoiceItem + "]";
	}

}

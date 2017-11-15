package com.gtn.webservices.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * PRJ#
 * 
 * @Project : EJBX_WEBSERVICES:InvoiceDetail.java
 * @DateTime: 3:04 PM 1/22/2017
 * @author Sunil Kumar
 */
@XmlRootElement(name = "INVOICE_DETAIL")
@XmlType(name = "", propOrder = { "invoiceNo", "invoiceItems" })
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class InvoiceDetail {
	@JsonProperty("INVOICE_NO")
	private String invoiceNo;
	@JsonProperty("INVOICE_ITEMS")
	private InvoiceItems invoiceItems;

	public String getInvoiceNo() {
		return invoiceNo;
	}

	@XmlElement(name = "INVOICE_NO")
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public InvoiceItems getInvoiceItems() {
		return invoiceItems;
	}

	@XmlElement(name = "INVOICE_ITEMS")
	public void setInvoiceItems(InvoiceItems invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	@Override
	public String toString() {
		return "InvoiceDetail [invoiceNo=" + invoiceNo + ", invoiceItems=" + invoiceItems + "]";
	}

}

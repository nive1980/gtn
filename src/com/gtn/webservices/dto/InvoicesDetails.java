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
 * @Project : EJBX_WEBSERVICES:InvoicesDetails.java
 * @DateTime: 3:04 PM 1/22/2017
 * @author Sunil Kumar
 */
@XmlRootElement(name = "INVOICES_DETAILS")
@XmlType(name = "", propOrder = { "invoiceDetail" })
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class InvoicesDetails {
	@JsonProperty("INVOICE_DETAIL")
	private List<InvoiceDetail> invoiceDetail;

	public List<InvoiceDetail> getInvoiceDetail() {
		return invoiceDetail;
	}

	@XmlElement(name = "INVOICE_DETAIL")
	public void setInvoiceDetail(List<InvoiceDetail> invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}

	@Override
	public String toString() {
		return "InvoicesDetails [invoiceDetail=" + invoiceDetail + "]";
	}

}

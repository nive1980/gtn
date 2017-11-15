package com.gtn.webservices.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * PRJ#
 * 
 * @Project : EJBX_WEBSERVICES:ShipmentDetailsRes.java
 * @DateTime: 3:04 PM 1/22/2017
 * @author Sunil Kumar
 */
@XmlRootElement(name = "SHIPMENT_DETAILS_RES")
@XmlType(name = "", propOrder = { "status", "shipmentsDetails", "messageHeader", "hostedError" })
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ShipmentDetailsRes {
	@JsonProperty("STATUS")
	private String status;
	@JsonProperty("SHIPMENTS_DETAILS")
	private ShipmentsDetails shipmentsDetails;
	@JsonProperty("MESSAGE_HEADER")
	private MessageHeader messageHeader;
	@JsonProperty("HOSTED_ERROR")
	private HostedError hostedError;

	public String getStatus() {
		return status;
	}

	@XmlElement(name = "STATUS")
	public void setStatus(String status) {
		this.status = status;
	}

	public ShipmentsDetails getShipmentsDetails() {
		return shipmentsDetails;
	}

	@XmlElement(name = "SHIPMENTS_DETAILS")
	public void setShipmentsDetails(ShipmentsDetails shipmentsDetails) {
		this.shipmentsDetails = shipmentsDetails;
	}

	public MessageHeader getMessageHeader() {
		return messageHeader;
	}

	@XmlElement(name = "MESSAGE_HEADER")
	public void setMessageHeader(MessageHeader messageHeader) {
		this.messageHeader = messageHeader;
	}

	public HostedError getHostedError() {
		return hostedError;
	}

	@XmlElement(name = "HOSTED_ERROR")
	public void setHostedError(HostedError hostedError) {
		this.hostedError = hostedError;
	}

	@Override
	public String toString() {
		return "ShipmentDetailsRes [status=" + status + ", shipmentsDetails=" + shipmentsDetails + ", messageHeader="
				+ messageHeader + ", hostedError=" + hostedError + "]";
	}

}
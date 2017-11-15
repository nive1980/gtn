package com.gtn.webservices.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * PRJ#
 * 
 * @Project : EJBX_WEBSERVICES:ShipmentDetailsReq.java
 * @DateTime: 3:04 PM 1/22/2017
 * @author Sunil Kumar
 */
@XmlRootElement(name = "SHIPMENT_DETAILS_REQ")
@XmlType(name = "", propOrder = { "messageHeader", "shipments" })
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ShipmentDetailsReq {
	@JsonProperty("MESSAGE_HEADER")
	private MessageHeader messageHeader;
	@JsonProperty("SHIPMENTS")
	private Shipments shipments;

	public MessageHeader getMessageHeader() {
		return messageHeader;
	}

	@XmlElement(name = "MESSAGE_HEADER")
	public void setMessageHeader(MessageHeader messageHeader) {
		this.messageHeader = messageHeader;
	}

	public Shipments getShipments() {
		return shipments;
	}
	@XmlElement(name = "SHIPMENTS")
	public void setShipments(Shipments shipments) {
		this.shipments = shipments;
	}

	@Override
	public String toString() {
		return "ShipmentDetailsReq [messageHeader=" + messageHeader + ", shipments=" + shipments + "]";
	}

}

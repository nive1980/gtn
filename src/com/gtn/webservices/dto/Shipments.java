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
 * @Project : EJBX_WEBSERVICES:Shipments.java
 * @DateTime: 3:04 PM 1/22/2017
 * @author Sunil Kumar
 */
@XmlRootElement(name = "SHIPMENTS")
@XmlType(name = "", propOrder = { "shipment" })
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Shipments {
	@JsonProperty("SHIPMENT")
	private List<String> shipment;

	public List<String> getShipment() {
		return shipment;
	}

	@XmlElement(name = "SHIPMENT")
	public void setShipment(List<String> shipment) {
		this.shipment = shipment;
	}

	@Override
	public String toString() {
		return "Shipments [shipment=" + shipment + "]";
	}

}

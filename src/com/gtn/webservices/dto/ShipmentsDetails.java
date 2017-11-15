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
 * @Project : EJBX_WEBSERVICES:ShipmentsDetails.java
 * @DateTime: 3:04 PM 1/22/2017
 * @author Sunil Kumar
 */
@XmlRootElement(name = "SHIPMENTS_DETAILS")
@XmlType(name = "", propOrder = { "shipmentDetail" })
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ShipmentsDetails {
	@JsonProperty("SHIPMENT_DETAIL")
	List<ShipmentDetail> shipmentDetail;

	public List<ShipmentDetail> getShipmentDetail() {
		return shipmentDetail;
	}

	@XmlElement(name = "SHIPMENT_DETAIL")
	public void setShipmentDetail(List<ShipmentDetail> shipmentDetail) {
		this.shipmentDetail = shipmentDetail;
	}

	@Override
	public String toString() {
		return "ShipmentsDetails [shipmentDetail=" + shipmentDetail + "]";
	}

}

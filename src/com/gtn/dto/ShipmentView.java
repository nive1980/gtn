package com.gtn.dto;

import java.util.Date;

public class ShipmentView {

	private int id;
	
	private String shipmentNo;
	
	private Date exportDate;

	private String countryOfExportName;
	private String countryOfExportCode;
	
	private String countryOfUltConsigneeName;
	
	private String countryOfUltConsigneeCode;
	private String sbu;
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShipmentNo() {
		return shipmentNo;
	}

	public void setShipmentNo(String shipmentNo) {
		this.shipmentNo = shipmentNo;
	}

	public Date getExportDate() {
		return exportDate;
	}

	public void setExportDate(Date exportDate) {
		this.exportDate = exportDate;
	}

	public String getCountryOfExportName() {
		return countryOfExportName;
	}

	public void setCountryOfExportName(String countryOfExportName) {
		this.countryOfExportName = countryOfExportName;
	}

	public String getCountryOfExportCode() {
		return countryOfExportCode;
	}

	public void setCountryOfExportCode(String countryOfExportCode) {
		this.countryOfExportCode = countryOfExportCode;
	}

	public String getCountryOfUltConsigneeName() {
		return countryOfUltConsigneeName;
	}

	public void setCountryOfUltConsigneeName(String countryOfUltConsigneeName) {
		this.countryOfUltConsigneeName = countryOfUltConsigneeName;
	}

	public String getCountryOfUltConsigneeCode() {
		return countryOfUltConsigneeCode;
	}

	public void setCountryOfUltConsigneeCode(String countryOfUltConsigneeCode) {
		this.countryOfUltConsigneeCode = countryOfUltConsigneeCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSbu() {
		return sbu;
	}

	public void setSbu(String sbu) {
		this.sbu = sbu;
	}
	
	
}

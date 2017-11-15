package com.gtn.model;

import java.io.Serializable;

public class ProductManufacturerValuePk implements Serializable{

	private String partNo;
	private long itemNo;
	
	public ProductManufacturerValuePk(){}
	
	public ProductManufacturerValuePk(String partNo, long itemNo) {
		super();
		this.partNo = partNo;
		this.itemNo = itemNo;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public long getItemNo() {
		return itemNo;
	}
	public void setItemNo(long itemNo) {
		this.itemNo = itemNo;
	}
	
	
}

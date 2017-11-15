package com.gtn.enums;

public enum ShipmentEaseStatus {

	PENDING("pending"),
	SUBMITTED("submitted"),
	RECEIVED("received"),
	VIEWED("viewed");
	
	private String type;
	
	ShipmentEaseStatus(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	
}

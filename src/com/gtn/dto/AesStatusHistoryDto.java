package com.gtn.dto;

import java.util.Date;

public class AesStatusHistoryDto {

	private int id;
	private String shipmentNo;
	private Date submittedOn;
	private Date respondedOn;
	private String oldAes;
	private String newAes;
	private String oldItn;
	private String newItn;
	private String oldXtn;
	private String newXtn;
	private String oldCorrectionCode;
	private String newCorrectionCode;
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
	public Date getSubmittedOn() {
		return submittedOn;
	}
	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}
	public Date getRespondedOn() {
		return respondedOn;
	}
	public void setRespondedOn(Date respondedOn) {
		this.respondedOn = respondedOn;
	}
	public String getOldAes() {
		return oldAes;
	}
	public void setOldAes(String oldAes) {
		this.oldAes = oldAes;
	}
	public String getNewAes() {
		return newAes;
	}
	public void setNewAes(String newAes) {
		this.newAes = newAes;
	}
	public String getOldItn() {
		return oldItn;
	}
	public void setOldItn(String oldItn) {
		this.oldItn = oldItn;
	}
	public String getNewItn() {
		return newItn;
	}
	public void setNewItn(String newItn) {
		this.newItn = newItn;
	}
	public String getOldXtn() {
		return oldXtn;
	}
	public void setOldXtn(String oldXtn) {
		this.oldXtn = oldXtn;
	}
	public String getNewXtn() {
		return newXtn;
	}
	public void setNewXtn(String newXtn) {
		this.newXtn = newXtn;
	}
	public String getOldCorrectionCode() {
		return oldCorrectionCode;
	}
	public void setOldCorrectionCode(String oldCorrectionCode) {
		this.oldCorrectionCode = oldCorrectionCode;
	}
	public String getNewCorrectionCode() {
		return newCorrectionCode;
	}
	public void setNewCorrectionCode(String newCorrectionCode) {
		this.newCorrectionCode = newCorrectionCode;
	}
	
	
}

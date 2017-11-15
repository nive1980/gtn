package com.gtn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AES_STATUS_HISTORY")
public class AesStatusHistory implements Model{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="SHIPMENT_NO")
	private String shipmentNo;
	
	@Column(name="SUBMITTED_ON")
	private Date submittedOn;
	
	@Column(name="RESPONDED_ON")
	private Date respondedOn;
	
	@Column(name="OLD_AES_STATUS")
	private String oldAes;
	
	@Column(name="NEW_AES_STATUS")
	private String newAes;
	
	@Column(name="OLD_ITN_NO")
	private String oldItn;
	
	@Column(name="NEW_ITN_NO")
	private String newItn;
	
	@Column(name="OLD_XTN_NO")
	private String oldXtn;
	
	@Column(name="NEW_XTN_NO")
	private String newXtn;
	
	@Column(name="OLD_CORRECTION_CODE")
	private String oldCorrectionCode;
	
	@Column(name="NEW_CORRECTION_CODE")
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

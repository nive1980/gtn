package com.gtn.dto;

import java.util.Date;

public class DplReasonDto {
	
	private String reasonCode;


	/**
	 * Attribute reason.
	 */
	private String reason;
	
	/**
	 * Attribute denyOverride.
	 */
	private String denyOverride;
	/**
	 * Attribute createdOn.
	 */
	private Date createdOn;
	/**
	 * Attribute createdBy.
	 */
    private String createdBy;
    /**
	 * Attribute modifiedOn.
	 */
	private Date modifiedOn;
    /**
	 * Attribute modifiedBy.
	 */
	private String modifiedBy;
	 /**
	  * Attribute active.
	  */
	private String active;
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getDenyOverride() {
		return denyOverride;
	}
	public void setDenyOverride(String denyOverride) {
		this.denyOverride = denyOverride;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	
}

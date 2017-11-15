package com.gtn.model;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SBU")
@Access(AccessType.PROPERTY)
public class SbuEntity implements Model{

	private String sbuCode;
	private String sbuName;
	private String sbuStatus;
	private String sbuType;
	private String countyName;
	private String countryCode;
	private String timezone;
	private Date createdOn;
	private Date createdBy;
	private Date modifiedOn;
	private String modifiedBy;
	
	@Id
	@Column(name= "SBU_CODE")
	public String getSbuCode() {
		return sbuCode;
	}
	public void setSbuCode(String sbuCode) {
		this.sbuCode = sbuCode;
	}
	
	@Id
	@Column(name= "SBU_NAME")
	public String getSbuName() {
		return sbuName;
	}
	public void setSbuName(String sbuName) {
		this.sbuName = sbuName;
	}
	
	@Column(name= "SBU_STATUS")
	public String getSbuStatus() {
		return sbuStatus;
	}
	public void setSbuStatus(String sbuStatus) {
		this.sbuStatus = sbuStatus;
	}
	
	@Column(name= "SBU_TYPE")
	public String getSbuType() {
		return sbuType;
	}
	public void setSbuType(String sbuType) {
		this.sbuType = sbuType;
	}
	
	@Column(name= "COUNTRY_NAME")
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	
	@Column(name= "COUNTRY_CODE")
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	@Column(name= "TIMEZONE")
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	
	@Column(name= "CREATED_ON")
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@Column(name= "CREATED_BY")
	public Date getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Date createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name= "MODIFIED_ON")
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	@Column(name= "MODIFIED_BY")
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
}

package com.gtn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DPL_SCREENINGS")
public class DplScreeningValue implements Model{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3545295287194010143L;

	@Column(name="ENTITY_TYPE")
	private String entityType;
	
	@Column(name="ORG_TYPE")
	private String orgType;
	
	@Id
	@Column(name="ENTITY_ID", unique=true)
	private String entityId;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="STATE_NAME")
	private String stateName;
	
	@Column(name="ADDR1")
	private String addressLine1;
	
	@Column(name="ADDR2")
	private String addressLine2;
	
	@Column(name="ADDR3")
	private String addressLine3;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Column(name="COUNTRY_NAME")
	private String countryName;
	
	@Column(name="ZIP")
	private String zip;
	
	@Column(name="HITS")
	private Integer hits;
	
	@Column(name="MASTER_LINK")
	private String masterLink;
	
	@Column(name="ENTITY_CODE")
	private String entityCode;

	@Column(name="SCREENED_BY")
	private String screenedBy;
	
	@Column(name="SCREENED_ON")
	private Date screenedOn;	
	
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public String getMasterLink() {
		return masterLink;
	}
	public void setMasterLink(String masterLink) {
		this.masterLink = masterLink;
	}
	public String getScreenedBy() {
		return screenedBy;
	}
	public void setScreenedBy(String screenedBy) {
		this.screenedBy = screenedBy;
	}
	public Date getScreenedOn() {
		return screenedOn;
	}
	public void setScreenedOn(Date screenedOn) {
		this.screenedOn = screenedOn;
	}
	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	
}

package com.gtn.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(ProductManufacturerValuePk.class)
@Table(name="PRODUCTS_MANUFACTURER")
public class ProductManufacturerValue implements Serializable, Model{

	private String partNo;
	private long itemNo;
	private boolean defaultManufacturer;
	private String manufactureId;
	private String name;
	private String modelNo;
	private String manufPartNo;
	private String cooCode;
	private String cooName;
	private String contactName;
	private String email;
	private String telephone;
	private BigDecimal assistValue;
	private BigDecimal assistBalance;
	
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	
	@Id
	@Column(name="PRODUCT_ID")
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	
	@Id
	@Column(name="ITEM_NO")
	public long getItemNo() {
		return itemNo;
	}
	public void setItemNo(long itemNo) {
		this.itemNo = itemNo;
	}
	
	@Column(name="DEFAULT_MANUF")
	public boolean isDefaultManufacturer() {
		return defaultManufacturer;
	}
	public void setDefaultManufacturer(boolean defaultManufacturer) {
		this.defaultManufacturer = defaultManufacturer;
	}
	
	@Column(name="MANUF_ID")
	public String getManufactureId() {
		return manufactureId;
	}
	public void setManufactureId(String manufactureId) {
		this.manufactureId = manufactureId;
	}
	
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="MANUF_MODEL_NO")
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	
	@Column(name="MANUF_PART_NO")
	public String getManufPartNo() {
		return manufPartNo;
	}
	public void setManufPartNo(String manufPartNo) {
		this.manufPartNo = manufPartNo;
	}
	
	@Column(name="COO_CODE")
	public String getCooCode() {
		return cooCode;
	}
	public void setCooCode(String cooCode) {
		this.cooCode = cooCode;
	}
	
	@Column(name="COO_NAME")
	public String getCooName() {
		return cooName;
	}
	public void setCooName(String cooName) {
		this.cooName = cooName;
	}
	
	@Column(name="CONTACT_NAME")
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="TELEPHONE")
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Column(name="ASSIST_VALUE")
	public BigDecimal getAssistValue() {
		return assistValue;
	}
	public void setAssistValue(BigDecimal assistValue) {
		this.assistValue = assistValue;
	}
	
	@Column(name="ASSIST_BALANCE")
	public BigDecimal getAssistBalance() {
		return assistBalance;
	}
	public void setAssistBalance(BigDecimal assistBalance) {
		this.assistBalance = assistBalance;
	}
	
	
	@Column(name="CREATED_BY", updatable = false)
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="CREATED_ON", updatable = false)
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@Column(name="UPDATED_BY")
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name="UPDATED_ON")
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}

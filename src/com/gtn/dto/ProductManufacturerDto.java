package com.gtn.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductManufacturerDto {
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
	
	private String reqType;
	private List<String> msgs;
	
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	
	private String delete;
	
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
	public boolean isDefaultManufacturer() {
		return defaultManufacturer;
	}
	public void setDefaultManufacturer(boolean defaultManufacturer) {
		this.defaultManufacturer = defaultManufacturer;
	}
	public String getManufactureId() {
		return manufactureId;
	}
	public void setManufactureId(String manufactureId) {
		this.manufactureId = manufactureId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public String getManufPartNo() {
		return manufPartNo;
	}
	public void setManufPartNo(String manufPartNo) {
		this.manufPartNo = manufPartNo;
	}
	public String getCooCode() {
		return cooCode;
	}
	public void setCooCode(String cooCode) {
		this.cooCode = cooCode;
	}
	public String getCooName() {
		return cooName;
	}
	public void setCooName(String cooName) {
		this.cooName = cooName;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public BigDecimal getAssistValue() {
		return assistValue;
	}
	public void setAssistValue(BigDecimal assistValue) {
		this.assistValue = assistValue;
	}
	public BigDecimal getAssistBalance() {
		return assistBalance;
	}
	public void setAssistBalance(BigDecimal assistBalance) {
		this.assistBalance = assistBalance;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public List<String> getMsgs() {
		return msgs;
	}
	public void setMsgs(List<String> msgs) {
		this.msgs = msgs;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	
	
}

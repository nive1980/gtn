package com.gtn.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ExporterDto implements Serializable{

	private String sbu;
	private String exporterCode;
	private String status;
	private String department;
	private String addrLine1;
	private String addrLine2;
	private String city;
	private String state;
	private String stateName;
	private String zipCode;
	private String country;
	private String countryName;
	private String telephone;
	private String fax;
	private boolean active;
	private String activeStr;
	
	private String sortBy;
	private String direction;
	
	private String shipperAuthSymbol;
	private String usppIdType;
	private String usppId;
	private Long transmitterId;
	private String filerTypeId;
	private String filerId;
	private Date docRegistrationDate;
	private Date docRegistrationExpDate;
	private String docFacilityRegNo;
	
	private Date dosRegistrationDate;
	private Date dosRegistrationExpDate;
	private Date dosAgreementExpDate;
	private String dosRegistrationNo;
	
	private boolean dos;
	private boolean doc;
	private boolean aes;
	
	private List<String> msgs;
	
	private String type;
	private String email;
	private String reqType;
	private Integer limit;
	private Integer page;
	private String filter;
	
	private String delete;
	private Integer offset;
	
	private Date createdOn;
	private String createdBy;
	
	public String getSbu() {
		return sbu;
	}
	public void setSbu(String sbu) {
		this.sbu = sbu;
	}
	public String getExporterCode() {
		return exporterCode;
	}
	public void setExporterCode(String exporterCode) {
		this.exporterCode = exporterCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getAddrLine1() {
		return addrLine1;
	}
	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}
	public String getAddrLine2() {
		return addrLine2;
	}
	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public boolean isDos() {
		return dos;
	}
	public void setDos(boolean dos) {
		this.dos = dos;
	}
	public boolean isDoc() {
		return doc;
	}
	public void setDoc(boolean doc) {
		this.doc = doc;
	}
	public boolean isAes() {
		return aes;
	}
	public void setAes(boolean aes) {
		this.aes = aes;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getShipperAuthSymbol() {
		return shipperAuthSymbol;
	}
	public void setShipperAuthSymbol(String shipperAuthSymbol) {
		this.shipperAuthSymbol = shipperAuthSymbol;
	}
	public String getUsppIdType() {
		return usppIdType;
	}
	public void setUsppIdType(String usppIdType) {
		this.usppIdType = usppIdType;
	}
	public String getUsppId() {
		return usppId;
	}
	public void setUsppId(String usppId) {
		this.usppId = usppId;
	}
	
	
	
	public Long getTransmitterId() {
		return transmitterId;
	}
	public void setTransmitterId(Long transmitterId) {
		this.transmitterId = transmitterId;
	}
	public String getFilerTypeId() {
		return filerTypeId;
	}
	public void setFilerTypeId(String filerTypeId) {
		this.filerTypeId = filerTypeId;
	}
	public String getFilerId() {
		return filerId;
	}
	public void setFilerId(String filerId) {
		this.filerId = filerId;
	}
	public Date getDocRegistrationDate() {
		return docRegistrationDate;
	}
	public void setDocRegistrationDate(Date docRegistrationDate) {
		this.docRegistrationDate = docRegistrationDate;
	}
	public Date getDocRegistrationExpDate() {
		return docRegistrationExpDate;
	}
	public void setDocRegistrationExpDate(Date docRegistrationExpDate) {
		this.docRegistrationExpDate = docRegistrationExpDate;
	}
	public String getDocFacilityRegNo() {
		return docFacilityRegNo;
	}
	public void setDocFacilityRegNo(String docFacilityRegNo) {
		this.docFacilityRegNo = docFacilityRegNo;
	}
	public Date getDosRegistrationDate() {
		return dosRegistrationDate;
	}
	public void setDosRegistrationDate(Date dosRegistrationDate) {
		this.dosRegistrationDate = dosRegistrationDate;
	}
	public Date getDosRegistrationExpDate() {
		return dosRegistrationExpDate;
	}
	public void setDosRegistrationExpDate(Date dosRegistrationExpDate) {
		this.dosRegistrationExpDate = dosRegistrationExpDate;
	}
	
	public Date getDosAgreementExpDate() {
		return dosAgreementExpDate;
	}
	public void setDosAgreementExpDate(Date dosAgreementExpDate) {
		this.dosAgreementExpDate = dosAgreementExpDate;
	}
	public String getDosRegistrationNo() {
		return dosRegistrationNo;
	}
	public void setDosRegistrationNo(String dosRegistrationNo) {
		this.dosRegistrationNo = dosRegistrationNo;
	}
	public List<String> getMsgs() {
		return msgs;
	}
	public void setMsgs(List<String> msgs) {
		this.msgs = msgs;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getActiveStr() {
		return activeStr;
	}
	public void setActiveStr(String activeStr) {
		this.activeStr = activeStr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
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
	
	
	
	
	
	
}

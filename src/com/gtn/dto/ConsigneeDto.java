package com.gtn.dto;

import java.util.List;

public class ConsigneeDto {

	private String consigneeId;
	private String sbuCode;
	
	private String consigneeName;
	private String status;
	private String consigneeAddr1;
	private String consigneeCity;
	private String consigneeAddr2;
	private String consigneeState;
	private String consigneeStateName;
	private String phone;
	private String fax;
	private String email;
	private String consigneeCountryName;
	private String consigneeCountry;
	private String zip;
	private String activeStr;
	private String active;
	private String useForDos;
	private String useForDoc;
	private String useForAes;
	private String typeOfConsignee;
	private String salesPerson;
	private String sortBy;
	private String direction;
	private String ownedByGovt;
	private String aviationMilNucEndUse;
	private String meuser;
	
	private String assurance;
	private String type;
	private String intermediateConsigneeCode;
	private String ffCode;
	private String contact;
	private String reqType;
	private String reqNavType;
	
	private List<String> msgs;
	
	private String remarks;
	private Integer offset;
	private Integer limit;
	private String delete;
	
	public String getConsigneeId() {
		return consigneeId;
	}
	public void setConsigneeId(String consigneeId) {
		this.consigneeId = consigneeId;
	}
	public String getSbuCode() {
		return sbuCode;
	}
	public void setSbuCode(String sbuCode) {
		this.sbuCode = sbuCode;
	}
	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getConsigneeAddr1() {
		return consigneeAddr1;
	}
	public void setConsigneeAddr1(String consigneeAddr1) {
		this.consigneeAddr1 = consigneeAddr1;
	}
	public String getConsigneeCity() {
		return consigneeCity;
	}
	public void setConsigneeCity(String consigneeCity) {
		this.consigneeCity = consigneeCity;
	}
	public String getConsigneeAddr2() {
		return consigneeAddr2;
	}
	public void setConsigneeAddr2(String consigneeAddr2) {
		this.consigneeAddr2 = consigneeAddr2;
	}
	public String getConsigneeState() {
		return consigneeState;
	}
	public void setConsigneeState(String consigneeState) {
		this.consigneeState = consigneeState;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getConsigneeCountryName() {
		return consigneeCountryName;
	}
	public void setConsigneeCountryName(String consigneeCountryName) {
		this.consigneeCountryName = consigneeCountryName;
	}
	public String getConsigneeCountry() {
		return consigneeCountry;
	}
	public void setConsigneeCountry(String consigneeCountry) {
		this.consigneeCountry = consigneeCountry;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getActiveStr() {
		return activeStr;
	}
	public void setActiveStr(String activeStr) {
		this.activeStr = activeStr;
	}
	public String getUseForDos() {
		return useForDos;
	}
	public void setUseForDos(String useForDos) {
		this.useForDos = useForDos;
	}
	public String getUseForDoc() {
		return useForDoc;
	}
	public void setUseForDoc(String useForDoc) {
		this.useForDoc = useForDoc;
	}
	public String getUseForAes() {
		return useForAes;
	}
	public void setUseForAes(String useForAes) {
		this.useForAes = useForAes;
	}
	public String getTypeOfConsignee() {
		return typeOfConsignee;
	}
	public void setTypeOfConsignee(String typeOfConsignee) {
		this.typeOfConsignee = typeOfConsignee;
	}
	public String getSalesPerson() {
		return salesPerson;
	}
	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
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
	public String getOwnedByGovt() {
		return ownedByGovt;
	}
	public void setOwnedByGovt(String ownedByGovt) {
		this.ownedByGovt = ownedByGovt;
	}
	public String getAviationMilNucEndUse() {
		return aviationMilNucEndUse;
	}
	public void setAviationMilNucEndUse(String aviationMilNucEndUse) {
		this.aviationMilNucEndUse = aviationMilNucEndUse;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAssurance() {
		return assurance;
	}
	public void setAssurance(String assurance) {
		this.assurance = assurance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIntermediateConsigneeCode() {
		return intermediateConsigneeCode;
	}
	public void setIntermediateConsigneeCode(String intermediateConsigneeCode) {
		this.intermediateConsigneeCode = intermediateConsigneeCode;
	}
	public String getFfCode() {
		return ffCode;
	}
	public void setFfCode(String ffCode) {
		this.ffCode = ffCode;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
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
	public String getMeuser() {
		return meuser;
	}
	public void setMeuser(String meuser) {
		this.meuser = meuser;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getConsigneeStateName() {
		return consigneeStateName;
	}
	public void setConsigneeStateName(String consigneeStateName) {
		this.consigneeStateName = consigneeStateName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getReqNavType() {
		return reqNavType;
	}
	public void setReqNavType(String reqNavType) {
		this.reqNavType = reqNavType;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	
	
	
}

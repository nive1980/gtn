package com.gtn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FREIGHT_FORWARDER")
public class FreightForwarderValue implements Serializable, Model  {


	/**
	 * Attribute ffCode.
	 */
	private String ffCode;


	/**
	 * Attribute ffName.
	 */
	private String ffName;


	/**
	 * Attribute ffAddr1.
	 */
	private String ffAddr1;


	/**
	 * Attribute ffAddr2.
	 */
	private String ffAddr2;


	/**
	 * Attribute ffAddr3.
	 */
	private String ffAddr3;


	/**
	 * Attribute ffCity.
	 */
	private String ffCity;


	/**
	 * Attribute ffState.
	 */
	private String ffState;


	/**
	 * Attribute ffStateName.
	 */
	private String ffStateName;


	/**
	 * Attribute ffCountry.
	 */
	private String ffCountry;


	/**
	 * Attribute ffCountryName.
	 */
	private String ffCountryName;


	/**
	 * Attribute zip.
	 */
	private String zip;


	/**
	 * Attribute phone.
	 */
	private String phone;


	/**
	 * Attribute fax.
	 */
	private String fax;


	/**
	 * Attribute ffIataCode.
	 */
	private String ffIataCode;


	/**
	 * Attribute userCol1.
	 */
	private String userCol1;


	/**
	 * Attribute userCol2.
	 */
	private String userCol2;


	/**
	 * Attribute contactPerson.
	 */
	private String contactPerson;


	/**
	 * Attribute status.
	 */
	private String status;


	/**
	 * Attribute createdBy.
	 */
	private String createdBy;


	/**
	 * Attribute screenedBy.
	 */
	private String screenedBy;


	/**
	 * Attribute overriddenBy.
	 */
	private String overriddenBy;


	/**
	 * Attribute createdOn.
	 */
	private Date createdOn;

	/**
	 * Attribute uploadedOn.
	 */
	private Date uploadedOn;


	/**
	 * Attribute screenedOn.
	 */
	private Date screenedOn;


	/**
	 * Attribute overriddenOn.
	 */
	private Date overriddenOn;


	/**
	 * Attribute filerIdType.
	 */
	private String filerIdType;


	/**
	 * Attribute filerId.
	 */
	private String filerId;


	/**
	 * Attribute modifiedBy.
	 */
	private String modifiedBy;


	/**
	 * Attribute modifiedOn.
	 */
	private Date modifiedOn;


	/**
	 * Attribute sbuCode.
	 */
	private String sbuCode;


	/**
	 * Attribute actmgrName.
	 */
	private String actmgrName;


	/**
	 * Attribute authorizedAgent.
	 */
	private String authorizedAgent;


	/**
	 * Attribute transmitterId.
	 */
	private Long transmitterId;


	/**
	 * Attribute useForDos.
	 */
	private String useForDos;


	/**
	 * Attribute useForAes.
	 */
	private String useForAes;

	/**
	 * Attribute sourceSystemId.
	 */
	private String sourceSystemId;

	/**
	 * Attribute lastExtractedDate.
	 */
	private Date lastExtractedDate;

	/**
	 * Attribute sourceSystemPartyId.
	 */
	private String sourceSystemPartyId;

	/**
	 * Attribute email.
	 */
	private String email;
	
	/**
	 * Attribute POA Required
	 */
	private String poaRequired;
	/**
	 * Attribute Provided Date
	 */
	private Date providedDate;
	/**
	 * Attribute Expiry Date
	 */
	private Date expiryDate;
	/**
	 * Attribute Shipping Label Account Provider
	 */
	private String shippingLabAccProvider;
	/**
	 * Attribute Freight Forwarder No.
	 */
	private String ffAccountNo;
	/**
	 * Attribute Carrier Name.
	 */
	private String carrierName;
	/**
	 * Attribute Carrier Code
	 */
	private String carrierCode;
	
	private String remarks;
	
	private String active;

	/**
	 * Instantiates a new FreightForwarderValue.
	 */
	public FreightForwarderValue() {
	}

	/**
	 * Instantiates a new FreightForwarderValue.
	 *
	 * @param ffCode
	 * @param ffName
	 * @param ffAddr1
	 * @param ffAddr2
	 * @param ffAddr3
	 * @param ffCity
	 * @param ffState
	 * @param ffStateName
	 * @param ffCountry
	 * @param ffCountryName
	 * @param zip
	 * @param phone
	 * @param fax
	 * @param ffIataCode
	 * @param userCol1
	 * @param userCol2
	 * @param contactPerson
	 * @param status
	 * @param createdBy
	 * @param screenedBy
	 * @param overriddenBy
	 * @param createdOn
	 * @param uploadedOn
	 * @param screenedOn
	 * @param overriddenOn
	 * @param filerIdType
	 * @param filerId
	 * @param modifiedBy
	 * @param modifiedOn
	 * @param sbuCode
	 * @param actmgrName
	 * @param authorizedAgent
	 * @param transmitterId
	 * @param useForDos
	 * @param useForAes
	 * @param sourceSystemId
	 * @param lastExtractedDate
	 * @param sourceSystemPartyId
	 * @param email
	 * @param poa
	 * @param providedDate
	 * @param expiryDate
	 * @param shippingLabAccProvider
	 * @param ffAccountNo
	 * @param carrierName
	 * @param carrierCode
	 * @param remarks
	 * @param active
	 */
	public FreightForwarderValue (String ffCode, String ffName, String ffAddr1, String ffAddr2, String ffAddr3, String ffCity,
			String ffState, String ffStateName, String ffCountry, String ffCountryName, String zip, String phone, String fax,
			String ffIataCode, String userCol1, String userCol2, String contactPerson, String status, String createdBy,
			String screenedBy, String overriddenBy, Date createdOn, Date uploadedOn, Date screenedOn, Date overriddenOn,
			String filerIdType, String filerId, String modifiedBy, Date modifiedOn, String sbuCode, String actmgrName,
			String authorizedAgent, Long transmitterId, String useForDos, String useForAes, String sourceSystemId,
			Date lastExtractedDate, String sourceSystemPartyId, String email,String poaRequired, Date providedDate, Date expiryDate, String shippingLabAccProvider,
			String ffAccountNo, String carrierName, String carrierCode , String remarks,String active) {

		this.ffCode = ffCode;
		this.ffName = ffName;
		this.ffAddr1 = ffAddr1;
		this.ffAddr2 = ffAddr2;
		this.ffAddr3 = ffAddr3;
		this.ffCity = ffCity;
		this.ffState = ffState;
		this.ffStateName = ffStateName;
		this.ffCountry = ffCountry;
		this.ffCountryName = ffCountryName;
		this.zip = zip;
		this.phone = phone;
		this.fax = fax;
		this.ffIataCode = ffIataCode;
		this.userCol1 = userCol1;
		this.userCol2 = userCol2;
		this.contactPerson = contactPerson;
		this.status = status;
		this.createdBy = createdBy;
		this.screenedBy = screenedBy;
		this.overriddenBy = overriddenBy;
		this.createdOn = createdOn;
		this.uploadedOn = uploadedOn;
		this.screenedOn = screenedOn;
		this.overriddenOn = overriddenOn;
		this.filerIdType = filerIdType;
		this.filerId = filerId;
		this.modifiedBy = modifiedBy;
		this.modifiedOn = modifiedOn;
		this.sbuCode = sbuCode;
		this.actmgrName = actmgrName;
		this.authorizedAgent = authorizedAgent;
		this.transmitterId = transmitterId;
		this.useForDos = useForDos;
		this.useForAes = useForAes;
		this.sourceSystemId = sourceSystemId;
		this.lastExtractedDate = lastExtractedDate;
		this.sourceSystemPartyId = sourceSystemPartyId;
		this.email = email;
		this.poaRequired=poaRequired;
		this.providedDate=providedDate;
		this.expiryDate=expiryDate;
		this.shippingLabAccProvider=shippingLabAccProvider;
		this.ffAccountNo=ffAccountNo;
		this.carrierCode=carrierCode;
		this.carrierName=carrierName;
		this.remarks=remarks;
		this.active=active;
	}


	/**
	 * @return ffCode
	 */
	@Id
	@Column(name = "FF_CODE")
	public String getFfCode() {
		return ffCode;
	}

	/**
	 * @param ffCode new value for ffCode
	 */
	public void setFfCode(String ffCode) {
		this.ffCode = ffCode;
	}


	/**
	 * @return ffName
	 */
	@Column(name = "FF_NAME")
	public String getFfName() {
		return ffName;
	}

	/**
	 * @param ffName new value for ffName
	 */
	public void setFfName(String ffName) {
		this.ffName = ffName;
	}


	/**
	 * @return ffAddr1
	 */
	@Column(name = "FF_ADDR1")
	public String getFfAddr1() {
		return ffAddr1;
	}

	/**
	 * @param ffAddr1 new value for ffAddr1
	 */
	public void setFfAddr1(String ffAddr1) {
		this.ffAddr1 = ffAddr1;
	}


	/**
	 * @return ffAddr2
	 */
	@Column(name = "FF_ADDR2")
	public String getFfAddr2() {
		return ffAddr2;
	}

	/**
	 * @param ffAddr2 new value for ffAddr2
	 */
	public void setFfAddr2(String ffAddr2) {
		this.ffAddr2 = ffAddr2;
	}


	/**
	 * @return ffAddr3
	 */
	@Column(name = "FF_ADDR3")
	public String getFfAddr3() {
		return ffAddr3;
	}

	/**
	 * @param ffAddr3 new value for ffAddr3
	 */
	public void setFfAddr3(String ffAddr3) {
		this.ffAddr3 = ffAddr3;
	}


	/**
	 * @return ffCity
	 */
	@Column(name = "FF_CITY")
	public String getFfCity() {
		return ffCity;
	}

	/**
	 * @param ffCity new value for ffCity
	 */
	public void setFfCity(String ffCity) {
		this.ffCity = ffCity;
	}


	/**
	 * @return ffState
	 */
	@Column(name = "FF_STATE")
	public String getFfState() {
		return ffState;
	}

	/**
	 * @param ffState new value for ffState
	 */
	public void setFfState(String ffState) {
		this.ffState = ffState;
	}


	/**
	 * @return ffStateName
	 */
	@Column(name = "FF_STATE_NAME")
	public String getFfStateName() {
		return ffStateName;
	}

	/**
	 * @param ffStateName new value for ffStateName
	 */
	public void setFfStateName(String ffStateName) {
		this.ffStateName = ffStateName;
	}


	/**
	 * @return ffCountry
	 */
	@Column(name = "FF_COUNTRY")
	public String getFfCountry() {
		return ffCountry;
	}

	/**
	 * @param ffCountry new value for ffCountry
	 */
	public void setFfCountry(String ffCountry) {
		this.ffCountry = ffCountry;
	}


	/**
	 * @return ffCountryName
	 */
	@Column(name = "FF_COUNTRY_NAME")
	public String getFfCountryName() {
		return ffCountryName;
	}

	/**
	 * @param ffCountryName new value for ffCountryName
	 */
	public void setFfCountryName(String ffCountryName) {
		this.ffCountryName = ffCountryName;
	}


	/**
	 * @return zip
	 */
	@Column(name = "ZIP")
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip new value for zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}


	/**
	 * @return phone
	 */
	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone new value for phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * @return fax
	 */
	@Column(name = "FAX")
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax new value for fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}


	/**
	 * @return ffIataCode
	 */
	@Column(name = "FF_IATA_CODE")
	public String getFfIataCode() {
		return ffIataCode;
	}

	/**
	 * @param ffIataCode new value for ffIataCode
	 */
	public void setFfIataCode(String ffIataCode) {
		this.ffIataCode = ffIataCode;
	}


	/**
	 * @return userCol1
	 */
	@Column(name = "USER_COL_1")
	public String getUserCol1() {
		return userCol1;
	}

	/**
	 * @param userCol1 new value for userCol1
	 */
	public void setUserCol1(String userCol1) {
		this.userCol1 = userCol1;
	}


	/**
	 * @return userCol2
	 */
	@Column(name = "USER_COL_2")
	public String getUserCol2() {
		return userCol2;
	}

	/**
	 * @param userCol2 new value for userCol2
	 */
	public void setUserCol2(String userCol2) {
		this.userCol2 = userCol2;
	}


	/**
	 * @return contactPerson
	 */
	@Column(name = "CONTACT_PERSON")
	public String getContactPerson() {
		return contactPerson;
	}

	/**
	 * @param contactPerson new value for contactPerson
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}


	/**
	 * @return status
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * @param status new value for status
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return createdBy
	 */
	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy new value for createdBy
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	/**
	 * @return screenedBy
	 */
	@Column(name = "SCREENED_BY")
	public String getScreenedBy() {
		return screenedBy;
	}

	/**
	 * @param screenedBy new value for screenedBy
	 */
	public void setScreenedBy(String screenedBy) {
		this.screenedBy = screenedBy;
	}


	/**
	 * @return overriddenBy
	 */
	@Column(name = "OVERRIDDEN_BY")
	public String getOverriddenBy() {
		return overriddenBy;
	}

	/**
	 * @param overriddenBy new value for overriddenBy
	 */
	public void setOverriddenBy(String overriddenBy) {
		this.overriddenBy = overriddenBy;
	}


	/**
	 * @return createdOn
	 */
	@Column(name = "CREATED_ON")
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn new value for createdOn
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the uploadedOn
	 */
	@Column(name = "UPLOADED_ON")
	public Date getUploadedOn() {
		return uploadedOn;
	}

	/**
	 * @param uploadedOn the uploadedOn to set
	 */
	public void setUploadedOn(Date uploadedOn) {
		this.uploadedOn = uploadedOn;
	}

	/**
	 * @return screenedOn
	 */
	@Column(name = "SCREENED_ON")
	public Date getScreenedOn() {
		return screenedOn;
	}

	/**
	 * @param screenedOn new value for screenedOn
	 */
	public void setScreenedOn(Date screenedOn) {
		this.screenedOn = screenedOn;
	}


	/**
	 * @return overriddenOn
	 */
	@Column(name = "OVERRIDDEN_ON")
	public Date getOverriddenOn() {
		return overriddenOn;
	}

	/**
	 * @param overriddenOn new value for overriddenOn
	 */
	public void setOverriddenOn(Date overriddenOn) {
		this.overriddenOn = overriddenOn;
	}


	/**
	 * @return filerIdType
	 */
	@Column(name = "FILER_ID_TYPE")
	public String getFilerIdType() {
		return filerIdType;
	}

	/**
	 * @param filerIdType new value for filerIdType
	 */
	public void setFilerIdType(String filerIdType) {
		this.filerIdType = filerIdType;
	}


	/**
	 * @return filerId
	 */
	@Column(name = "FILER_ID")
	public String getFilerId() {
		return filerId;
	}

	/**
	 * @param filerId new value for filerId
	 */
	public void setFilerId(String filerId) {
		this.filerId = filerId;
	}


	/**
	 * @return modifiedBy
	 */
	@Column(name = "MODIFIED_BY")
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy new value for modifiedBy
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	/**
	 * @return modifiedOn
	 */
	@Column(name = "MODIFIED_ON")
	public Date getModifiedOn() {
		return modifiedOn;
	}

	/**
	 * @param modifiedOn new value for modifiedOn
	 */
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}


	/**
	 * @return sbuCode
	 */
	@Column(name = "SBU_CODE")
	public String getSbuCode() {
		return sbuCode;
	}

	/**
	 * @param sbuCode new value for sbuCode
	 */
	public void setSbuCode(String sbuCode) {
		this.sbuCode = sbuCode;
	}


	/**
	 * @return actmgrName
	 */
	@Column(name = "ACTMGR_NAME")
	public String getActmgrName() {
		return actmgrName;
	}

	/**
	 * @param actmgrName new value for actmgrName
	 */
	public void setActmgrName(String actmgrName) {
		this.actmgrName = actmgrName;
	}


	/**
	 * @return authorizedAgent
	 */
	@Column(name = "AUTHORIZED_AGENT")
	public String getAuthorizedAgent() {
		return authorizedAgent;
	}

	/**
	 * @param authorizedAgent new value for authorizedAgent
	 */
	public void setAuthorizedAgent(String authorizedAgent) {
		this.authorizedAgent = authorizedAgent;
	}


	/**
	 * @return transmitterId
	 */
	@Column(name = "TRANSMITTER_ID")
	public Long getTransmitterId() {
		return transmitterId;
	}

	/**
	 * @param transmitterId new value for transmitterId
	 */
	public void setTransmitterId(Long transmitterId) {
		this.transmitterId = transmitterId;
	}


	/**
	 * @return useForDos
	 */
	@Column(name = "USE_FOR_DOS")
	public String getUseForDos() {
		return useForDos;
	}

	/**
	 * @param useForDos new value for useForDos
	 */
	public void setUseForDos(String useForDos) {
		this.useForDos = useForDos;
	}


	/**
	 * @return useForAes
	 */
	@Column(name = "USE_FOR_AES")
	public String getUseForAes() {
		return useForAes;
	}

	/**
	 * @param useForAes new value for useForAes
	 */
	public void setUseForAes(String useForAes) {
		this.useForAes = useForAes;
	}

	//Added by Anoop on 05-May-2011 for ENHT-548
	/**
	 * @return the lastExtractedDate
	 */
	@Column(name = "LAST_EXTRACTED_DATE")
	public Date getLastExtractedDate() {
		return lastExtractedDate;
	}

	/**
	 * @param lastExtractedDate the lastExtractedDate to set
	 */
	public void setLastExtractedDate(Date lastExtractedDate) {
		this.lastExtractedDate = lastExtractedDate;
	}

	/**
	 * @return the sourceSystemId
	 */
	@Column(name = "SOURCE_SYSTEM_ID")
	public String getSourceSystemId() {
		return sourceSystemId;
	}

	/**
	 * @param sourceSystemId the sourceSystemId to set
	 */
	public void setSourceSystemId(String sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
	}

	/**
	 * @return the sourceSystemPartyId
	 */
	@Column(name = "SOURCE_SYSTEM_PARTY_ID")
	public String getSourceSystemPartyId() {
		return sourceSystemPartyId;
	}

	/**
	 * @param sourceSystemPartyId the sourceSystemPartyId to set
	 */
	public void setSourceSystemPartyId(String sourceSystemPartyId) {
		this.sourceSystemPartyId = sourceSystemPartyId;
	}

	/**
	 * @return the email
	 */
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	//Anoop Ends

	/*
	 * Jinesh Gopinathan. 15/July/2011.
	 */

	/**
	 * @return Carrier Code.
	 */
	@Column(name="CARRIER_CODE")
	public String getCarrierCode() {
		return carrierCode;
	}

	/**
	 *
	 * @param carrierCode.
	 */
	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	
	/**
	 * @return remarks.
	 */
	@Column(name="REMARKS")
	public String getRemarks() {
		return remarks;
	}

	/**
	 *
	 * @param remarks.
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 *
	 * @return Carrier Name.
	 */
	@Column(name="CARRIER_NAME")
	public String getCarrierName() {
		return carrierName;
	}

	/**
	 *
	 * @param carrierName.
	 */
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	/**
	 *
	 * @return Expiry Date.
	 */
	@Column(name="EXPIRY_DATE")
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 *
	 * @param expiryDate.
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 *
	 * @return Frieght Forwarder Number.
	 */
	@Column(name="FF_ACCOUNT_NO")
	public String getFfAccountNo() {
		return ffAccountNo;
	}

	/**
	 *
	 * @param ffAccountNo.
	 */
	public void setFfAccountNo(String ffAccountNo) {
		this.ffAccountNo = ffAccountNo;
	}

	/**
	 *
	 * @return POA.
	 */
	@Column(name="POA_REQUIRED")
	public String getPoaRequired() {
		return poaRequired;
	}

	/**
	 *
	 * @param poa.
	 */
	public void setPoaRequired(String poa) {
		this.poaRequired = poa;
	}

	/**
	 *
	 * @return Provided Date.
	 */
	@Column(name="PROVIDED_DATE")
	public Date getProvidedDate() {
		return providedDate;
	}

	/**
	 *
	 * @param providedDate.
	 */
	public void setProvidedDate(Date providedDate) {
		this.providedDate = providedDate;
	}

	/**
	 *
	 * @return Shipping Label Account Provider.
	 */
	@Column(name="SHIPPING_LAB_ACC_PROVIDER")
	public String getShippingLabAccProvider() {
		return shippingLabAccProvider;
	}

	/**
	 *
	 * @param shippingLabAccProvider
	 */
	public void setShippingLabAccProvider(String shippingLabAccProvider) {
		this.shippingLabAccProvider = shippingLabAccProvider;
	}
	
	/**
	 *
	 * @return active.
	 */
	@Column(name="ACTIVE")
	public String getActive() {
		return active;
	}

	/**
	 *
	 * @param active
	 */
	public void setActive(String active) {
		this.active = active;
	}
	/**
	 * End for PRJ-33 
	 */


	/**
	 * Returns a string representation of the value object.
	 *
	 * @return the string
	 */
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("ffCode: " + ffCode);
		stringBuffer.append("ffName: " + ffName);
		stringBuffer.append("ffAddr1: " + ffAddr1);
		stringBuffer.append("ffAddr2: " + ffAddr2);
		stringBuffer.append("ffAddr3: " + ffAddr3);
		stringBuffer.append("ffCity: " + ffCity);
		stringBuffer.append("ffState: " + ffState);
		stringBuffer.append("ffStateName: " + ffStateName);
		stringBuffer.append("ffCountry: " + ffCountry);
		stringBuffer.append("ffCountryName: " + ffCountryName);
		stringBuffer.append("zip: " + zip);
		stringBuffer.append("phone: " + phone);
		stringBuffer.append("fax: " + fax);
		stringBuffer.append("ffIataCode: " + ffIataCode);
		stringBuffer.append("userCol1: " + userCol1);
		stringBuffer.append("userCol2: " + userCol2);
		stringBuffer.append("contactPerson: " + contactPerson);
		stringBuffer.append("status: " + status);
		stringBuffer.append("createdBy: " + createdBy);
		stringBuffer.append("screenedBy: " + screenedBy);
		stringBuffer.append("overriddenBy: " + overriddenBy);
		stringBuffer.append("createdOn: " + createdOn);
		stringBuffer.append("uploadedOn: " + uploadedOn);
		stringBuffer.append("screenedOn: " + screenedOn);
		stringBuffer.append("overriddenOn: " + overriddenOn);
		stringBuffer.append("filerIdType: " + filerIdType);
		stringBuffer.append("filerId: " + filerId);
		stringBuffer.append("modifiedBy: " + modifiedBy);
		stringBuffer.append("modifiedOn: " + modifiedOn);
		stringBuffer.append("sbuCode: " + sbuCode);
		stringBuffer.append("actmgrName: " + actmgrName);
		stringBuffer.append("authorizedAgent: " + authorizedAgent);
		stringBuffer.append("transmitterId: " + transmitterId);
		stringBuffer.append("useForDos: " + useForDos);
		stringBuffer.append("useForAes: " + useForAes);
		stringBuffer.append("sourceSystemId: " + sourceSystemId);
		stringBuffer.append("lastExtractedDate: " + lastExtractedDate);
		stringBuffer.append("sourceSystemPartyId: " + sourceSystemPartyId);
		stringBuffer.append("carrierCode: " + carrierCode);
		stringBuffer.append("carrierName: " + carrierName);
		stringBuffer.append("expiryDate: " + expiryDate);
		stringBuffer.append("ffAccountNo: " + ffAccountNo);
		stringBuffer.append("poaRequired: " + poaRequired);
		stringBuffer.append("providedDate: " + providedDate);
		stringBuffer.append("shippingLabAccProvider: " + shippingLabAccProvider);
		stringBuffer.append("email: " + email);
		stringBuffer.append("active: " + active);
		return stringBuffer.toString();
	}
}
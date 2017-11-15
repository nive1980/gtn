package com.gtn.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONSIGNEE")
public class ConsigneeValue implements Serializable, Model  {


	/**
	 * Attribute consigneeId.
	 */
	private String consigneeId;


	/**
	 * Attribute consigneeName.
	 */
	private String consigneeName;


	/**
	 * Attribute consigneeAddr1.
	 */
	private String consigneeAddr1;


	/**
	 * Attribute consigneeAddr2.
	 */
	private String consigneeAddr2;


	/**
	 * Attribute consigneeAddr3.
	 */
	private String consigneeAddr3;


	/**
	 * Attribute consigneeCity.
	 */
	private String consigneeCity;


	/**
	 * Attribute consigneeState.
	 */
	private String consigneeState;


	/**
	 * Attribute consigneeStateName.
	 */
	private String consigneeStateName;


	/**
	 * Attribute consigneeCountry.
	 */
	private String consigneeCountry;


	/**
	 * Attribute consigneeCountryName.
	 */
	private String consigneeCountryName;


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
	 * Attribute email.
	 */
	private String email;


	/**
	 * Attribute mt.
	 */
	private String mt;


	/**
	 * Attribute cb.
	 */
	private String cb;


	/**
	 * Attribute ns.
	 */
	private String ns;


	/**
	 * Attribute np.
	 */
	private String np;


	/**
	 * Attribute rs.
	 */
	private String rs;


	/**
	 * Attribute cc.
	 */
	private String cc;


	/**
	 * Attribute at.
	 */
	private String at;


	/**
	 * Attribute fc.
	 */
	private String fc;


	/**
	 * Attribute sbuCode.
	 */
	private String sbuCode;


	/**
	 * Attribute salesPerson.
	 */
	private String salesPerson;


	/**
	 * Attribute remarks.
	 */
	private String remarks;


	/**
	 * Attribute active.
	 */
	private String active;


	/**
	 * Attribute status.
	 */
	private String status;


	/**
	 * Attribute createdBy.
	 */
	private String createdBy;


	/**
	 * Attribute overriddenBy.
	 */
	private String overriddenBy;


	/**
	 * Attribute screenedBy.
	 */
	private String screenedBy;


	/**
	 * Attribute creationDate.
	 */
	private Date creationDate;


	/**
	 * Attribute overriddenDate.
	 */
	private Date overriddenDate;


	/**
	 * Attribute screenedOn.
	 */
	private Date screenedOn;

	/**
	 * Attribute uploadedOn
	 */
	private Date uploadedOn;

	/**
	 * Attribute userCol1.
	 */
	private String userCol1;


	/**
	 * Attribute userCol2.
	 */
	private String userCol2;


	/**
	 * Attribute consigneeType.
	 */
	private String consigneeType;


	/**
	 * Attribute epciStatus.
	 */
	private String epciStatus;


	/**
	 * Attribute assurance.
	 */
	private String assurance;


	/**
	 * Attribute meuser.
	 */
	private String meuser;


	/**
	 * Attribute intermediateConsigneeCode.
	 */
	private String intermediateConsigneeCode;


	/**
	 * Attribute ffCode.
	 */
	private String ffCode;


	/**
	 * Attribute soldtoBilltoPurchaser.
	 */
	private String soldtoBilltoPurchaser;


	/**
	 * Attribute markFor.
	 */
	private String markFor;


	/**
	 * Attribute endUser.
	 */
	private String endUser;


	/**
	 * Attribute lastChangeDate.
	 */
	private Date lastChangeDate;


	/**
	 * Attribute modifiedBy.
	 */
	private String modifiedBy;


	/**
	 * Attribute modifiedOn.
	 */
	private Date modifiedOn;


	/**
	 * Attribute actmgrName.
	 */
	private String actmgrName;


	/**
	 * Attribute related.
	 */
	private String related;


	/**
	 * Attribute useForDos.
	 */
	private String useForDos;


	/**
	 * Attribute useForDoc.
	 */
	private String useForDoc;


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
	//Anoop Ends
	
	/**
	 * Attribute sourceSystemPartyId.
	 */
	private String type;
	
	/**
	 * Attribute staLetterRequired.
	 */
	private String staLetterRequired;
	
	/**
	 * Attribute staLetterRequired.
	 */
	private String staLetterObtained;
	
	/**
	 * Attribute staDateObtained.
	 */
	private Date staDateObtained;
	
	/**
	 * Attribute typeOfConsignee.
	 */
	private String typeOfConsignee;
	
	/**
	 * Attribute contact.
	 */
	private String contact;
	
	/**
	 * Attribute ownedByGovt.
	 */
	private String ownedByGovt;
	
	/**
	 * Attribute interactWithGovt.
	 */
	private String interactWithGovt;
	
	/**
	 * Attribute aviationMilNucEndUse.
	 */
	private String aviationMilNucEndUse;
	
	/**
	 * Attribute estimatedSalesSpendsUSD.
	 */
	private BigDecimal estimatedSalesSpendsUSD;

	/**
	 * Attribute signedContract.
	 */
	private String signedContract;
	
	/**
	 * Attribute djScreenedOn.
	 */
	private Date djScreenedOn;
	
	/**
	 * Instantiates a new ConsigneeValue.
	 */
	public ConsigneeValue() {
	}

	/**
	 * Instantiates a new ConsigneeValue.
	 *
	 * @param consigneeId
	 * @param consigneeName
	 * @param consigneeAddr1
	 * @param consigneeAddr2
	 * @param consigneeAddr3
	 * @param consigneeCity
	 * @param consigneeState
	 * @param consigneeStateName
	 * @param consigneeCountry
	 * @param consigneeCountryName
	 * @param zip
	 * @param phone
	 * @param fax
	 * @param email
	 * @param mt
	 * @param cb
	 * @param ns
	 * @param np
	 * @param rs
	 * @param cc
	 * @param at
	 * @param fc
	 * @param sbuCode
	 * @param salesPerson
	 * @param remarks
	 * @param active
	 * @param status
	 * @param createdBy
	 * @param overriddenBy
	 * @param screenedBy
	 * @param creationDate
	 * @param overriddenDate
	 * @param screenedOn
	 * @param uploadedOn
	 * @param userCol1
	 * @param userCol2
	 * @param consigneeType
	 * @param epciStatus
	 * @param assurance
	 * @param meuser
	 * @param intermediateConsigneeCode
	 * @param ffCode
	 * @param soldtoBilltoPurchaser
	 * @param markFor
	 * @param endUser
	 * @param lastChangeDate
	 * @param modifiedBy
	 * @param modifiedOn
	 * @param actmgrName
	 * @param related
	 * @param useForDos
	 * @param useForDoc
	 * @param useForAes
	 * @param sourceSystemId
	 * @param lastExtractedDate
	 * @param sourceSystemPartyId
	 * @param type
	 * @param staLetterRequired
	 * @param staLetterObtained
	 * @param staDateObtained
	 * @param djScreenedOn
	 */
	public ConsigneeValue (String consigneeId, String consigneeName, String consigneeAddr1, String consigneeAddr2,
			String consigneeAddr3, String consigneeCity, String consigneeState, String consigneeStateName,
			String consigneeCountry, String consigneeCountryName, String zip, String phone, String fax,
			String mt, String cb, String ns, String np, String rs, String cc, String at, String fc,
			String sbuCode, String salesPerson, String remarks, String active, String status, String createdBy,
			String overriddenBy, String screenedBy, Date creationDate, Date overriddenDate, Date screenedOn, Date uploadedOn,
			String userCol1, String userCol2, String consigneeType, String epciStatus, String assurance,
			String meuser, String intermediateConsigneeCode, String ffCode, String soldtoBilltoPurchaser,
			String markFor, String endUser, Date lastChangeDate, String modifiedBy, Date modifiedOn,
			String actmgrName, String related, String useForDos, String useForDoc, String useForAes, String email,
			String sourceSystemId, Date lastExtractedDate, String sourceSystemPartyId,String type,String staLetterRequired,String staLetterObtained,Date staDateObtained,Date djScreenedOn) {

		this.consigneeId = consigneeId;
		this.consigneeName = consigneeName;
		this.consigneeAddr1 = consigneeAddr1;
		this.consigneeAddr2 = consigneeAddr2;
		this.consigneeAddr3 = consigneeAddr3;
		this.consigneeCity = consigneeCity;
		this.consigneeState = consigneeState;
		this.consigneeStateName = consigneeStateName;
		this.consigneeCountry = consigneeCountry;
		this.consigneeCountryName = consigneeCountryName;
		this.zip = zip;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.mt = mt;
		this.cb = cb;
		this.ns = ns;
		this.np = np;
		this.rs = rs;
		this.cc = cc;
		this.at = at;
		this.fc = fc;
		this.sbuCode = sbuCode;
		this.salesPerson = salesPerson;
		this.remarks = remarks;
		this.active = active;
		this.status = status;
		this.createdBy = createdBy;
		this.overriddenBy = overriddenBy;
		this.screenedBy = screenedBy;
		this.creationDate = creationDate;
		this.overriddenDate = overriddenDate;
		this.screenedOn = screenedOn;
		this.uploadedOn = uploadedOn;
		this.userCol1 = userCol1;
		this.userCol2 = userCol2;
		this.consigneeType = consigneeType;
		this.epciStatus = epciStatus;
		this.assurance = assurance;
		this.meuser = meuser;
		this.intermediateConsigneeCode = intermediateConsigneeCode;
		this.ffCode = ffCode;
		this.soldtoBilltoPurchaser = soldtoBilltoPurchaser;
		this.markFor = markFor;
		this.endUser = endUser;
		this.lastChangeDate = lastChangeDate;
		this.modifiedBy = modifiedBy;
		this.modifiedOn = modifiedOn;
		this.actmgrName = actmgrName;
		this.related = related;
		this.useForDos = useForDos;
		this.useForDoc = useForDoc;
		this.useForAes = useForAes;
		this.sourceSystemId	 = sourceSystemId;
		this.lastExtractedDate = lastExtractedDate;
		this.sourceSystemPartyId = sourceSystemPartyId;
		this.type = type;
		this.staLetterRequired=staLetterRequired;
		this.staLetterObtained=staLetterObtained;
		this.staDateObtained=staDateObtained;
		this.djScreenedOn=djScreenedOn;
	}


	/**
	 * @return consigneeId
	 */
	@Id
	@Column(name = "CONSIGNEE_ID")
	public String getConsigneeId() {
		return consigneeId;
	}

	/**
	 * @param consigneeId new value for consigneeId
	 */
	public void setConsigneeId(String consigneeId) {
		this.consigneeId = consigneeId;
	}


	/**
	 * @return consigneeName
	 */
	@Column(name = "CONSIGNEE_NAME")
	public String getConsigneeName() {
		return consigneeName;
	}

	/**
	 * @param consigneeName new value for consigneeName
	 */
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}


	/**
	 * @return consigneeAddr1
	 */
	@Column(name = "CONSIGNEE_ADDR1")
	public String getConsigneeAddr1() {
		return consigneeAddr1;
	}

	/**
	 * @param consigneeAddr1 new value for consigneeAddr1
	 */
	public void setConsigneeAddr1(String consigneeAddr1) {
		this.consigneeAddr1 = consigneeAddr1;
	}


	/**
	 * @return consigneeAddr2
	 */
	@Column(name = "CONSIGNEE_ADDR2")
	public String getConsigneeAddr2() {
		return consigneeAddr2;
	}

	/**
	 * @param consigneeAddr2 new value for consigneeAddr2
	 */
	public void setConsigneeAddr2(String consigneeAddr2) {
		this.consigneeAddr2 = consigneeAddr2;
	}


	/**
	 * @return consigneeAddr3
	 */
	@Column(name = "CONSIGNEE_ADDR3")
	public String getConsigneeAddr3() {
		return consigneeAddr3;
	}

	/**
	 * @param consigneeAddr3 new value for consigneeAddr3
	 */
	public void setConsigneeAddr3(String consigneeAddr3) {
		this.consigneeAddr3 = consigneeAddr3;
	}


	/**
	 * @return consigneeCity
	 */
	@Column(name = "CONSIGNEE_CITY")
	public String getConsigneeCity() {
		return consigneeCity;
	}

	/**
	 * @param consigneeCity new value for consigneeCity
	 */
	public void setConsigneeCity(String consigneeCity) {
		this.consigneeCity = consigneeCity;
	}


	/**
	 * @return consigneeState
	 */
	@Column(name = "CONSIGNEE_STATE")
	public String getConsigneeState() {
		return consigneeState;
	}

	/**
	 * @param consigneeState new value for consigneeState
	 */
	public void setConsigneeState(String consigneeState) {
		this.consigneeState = consigneeState;
	}


	/**
	 * @return consigneeStateName
	 */
	@Column(name = "CONSIGNEE_STATE_NAME")
	public String getConsigneeStateName() {
		return consigneeStateName;
	}

	/**
	 * @param consigneeStateName new value for consigneeStateName
	 */
	public void setConsigneeStateName(String consigneeStateName) {
		this.consigneeStateName = consigneeStateName;
	}


	/**
	 * @return consigneeCountry
	 */
	@Column(name = "CONSIGNEE_COUNTRY")
	public String getConsigneeCountry() {
		return consigneeCountry;
	}

	/**
	 * @param consigneeCountry new value for consigneeCountry
	 */
	public void setConsigneeCountry(String consigneeCountry) {
		this.consigneeCountry = consigneeCountry;
	}


	/**
	 * @return consigneeCountryName
	 */
	@Column(name = "CONSIGNEE_COUNTRY_NAME")
	public String getConsigneeCountryName() {
		return consigneeCountryName;
	}

	/**
	 * @param consigneeCountryName new value for consigneeCountryName
	 */
	public void setConsigneeCountryName(String consigneeCountryName) {
		this.consigneeCountryName = consigneeCountryName;
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

	/**
	 * @return mt
	 */
	@Column(name = "MT")
	public String getMt() {
		return mt;
	}

	/**
	 * @param mt new value for mt
	 */
	public void setMt(String mt) {
		this.mt = mt;
	}


	/**
	 * @return cb
	 */
	@Column(name = "CB")
	public String getCb() {
		return cb;
	}

	/**
	 * @param cb new value for cb
	 */
	public void setCb(String cb) {
		this.cb = cb;
	}


	/**
	 * @return ns
	 */
	@Column(name = "NS")
	public String getNs() {
		return ns;
	}

	/**
	 * @param ns new value for ns
	 */
	public void setNs(String ns) {
		this.ns = ns;
	}


	/**
	 * @return np
	 */
	@Column(name = "NP")
	public String getNp() {
		return np;
	}

	/**
	 * @param np new value for np
	 */
	public void setNp(String np) {
		this.np = np;
	}


	/**
	 * @return rs
	 */
	@Column(name = "RS")
	public String getRs() {
		return rs;
	}

	/**
	 * @param rs new value for rs
	 */
	public void setRs(String rs) {
		this.rs = rs;
	}


	/**
	 * @return cc
	 */
	@Column(name = "CC")
	public String getCc() {
		return cc;
	}

	/**
	 * @param cc new value for cc
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}


	/**
	 * @return at
	 */
	@Column(name = "AT")
	public String getAt() {
		return at;
	}

	/**
	 * @param at new value for at
	 */
	public void setAt(String at) {
		this.at = at;
	}


	/**
	 * @return fc
	 */
	@Column(name = "FC")
	public String getFc() {
		return fc;
	}

	/**
	 * @param fc new value for fc
	 */
	public void setFc(String fc) {
		this.fc = fc;
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
	 * @return salesPerson
	 */
	@Column(name = "SALES_PERSON")
	public String getSalesPerson() {
		return salesPerson;
	}

	/**
	 * @param salesPerson new value for salesPerson
	 */
	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}


	/**
	 * @return remarks
	 */
	@Column(name = "REMARKS")
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks new value for remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	/**
	 * @return active
	 */
	@Column(name = "ACTIVE")
	public String getActive() {
		return active;
	}

	/**
	 * @param active new value for active
	 */
	public void setActive(String active) {
		this.active = active;
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
	 * @return creationDate
	 */
	@Column(name = "CREATION_DATE")
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate new value for creationDate
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	/**
	 * @return overriddenDate
	 */
	@Column(name = "OVERRIDDEN_DATE")
	public Date getOverriddenDate() {
		return overriddenDate;
	}

	/**
	 * @param overriddenDate new value for overriddenDate
	 */
	public void setOverriddenDate(Date overriddenDate) {
		this.overriddenDate = overriddenDate;
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

	//Added by Anoop on 12-Apr-2011 for L3 Enhancement: Added field uploadedOn
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
	 * @return consigneeType
	 */
	@Column(name = "CONSIGNEE_TYPE")
	public String getConsigneeType() {
		return consigneeType;
	}

	/**
	 * @param consigneeType new value for consigneeType
	 */
	public void setConsigneeType(String consigneeType) {
		this.consigneeType = consigneeType;
	}


	/**
	 * @return epciStatus
	 */
	@Column(name = "EPCI_STATUS")
	public String getEpciStatus() {
		return epciStatus;
	}

	/**
	 * @param epciStatus new value for epciStatus
	 */
	public void setEpciStatus(String epciStatus) {
		this.epciStatus = epciStatus;
	}


	/**
	 * @return assurance
	 */
	@Column(name = "ASSURANCE")
	public String getAssurance() {
		return assurance;
	}

	/**
	 * @param assurance new value for assurance
	 */
	public void setAssurance(String assurance) {
		this.assurance = assurance;
	}


	/**
	 * @return meuser
	 */
	@Column(name = "MEUSER")
	public String getMeuser() {
		return meuser;
	}

	/**
	 * @param meuser new value for meuser
	 */
	public void setMeuser(String meuser) {
		this.meuser = meuser;
	}


	/**
	 * @return intermediateConsigneeCode
	 */
	@Column(name = "INTERMEDIATE_CONSIGNEE_CODE")
	public String getIntermediateConsigneeCode() {
		return intermediateConsigneeCode;
	}

	/**
	 * @param intermediateConsigneeCode new value for intermediateConsigneeCode
	 */
	public void setIntermediateConsigneeCode(String intermediateConsigneeCode) {
		this.intermediateConsigneeCode = intermediateConsigneeCode;
	}


	/**
	 * @return ffCode
	 */
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
	 * @return soldtoBilltoPurchaser
	 */
	@Column(name = "SOLDTO_BILLTO_PURCHASER")
	public String getSoldtoBilltoPurchaser() {
		return soldtoBilltoPurchaser;
	}

	/**
	 * @param soldtoBilltoPurchaser new value for soldtoBilltoPurchaser
	 */
	public void setSoldtoBilltoPurchaser(String soldtoBilltoPurchaser) {
		this.soldtoBilltoPurchaser = soldtoBilltoPurchaser;
	}


	/**
	 * @return markFor
	 */
	@Column(name = "MARK_FOR")
	public String getMarkFor() {
		return markFor;
	}

	/**
	 * @param markFor new value for markFor
	 */
	public void setMarkFor(String markFor) {
		this.markFor = markFor;
	}


	/**
	 * @return endUser
	 */
	@Column(name = "END_USER")
	public String getEndUser() {
		return endUser;
	}

	/**
	 * @param endUser new value for endUser
	 */
	public void setEndUser(String endUser) {
		this.endUser = endUser;
	}


	/**
	 * @return lastChangeDate
	 */
	@Column(name = "LAST_CHANGE_DATE")
	public Date getLastChangeDate() {
		return lastChangeDate;
	}

	/**
	 * @param lastChangeDate new value for lastChangeDate
	 */
	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
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
	 * @return related
	 */
	@Column(name = "related")
	public String getRelated() {
		return related;
	}

	/**
	 * @param related new value for related
	 */
	public void setRelated(String related) {
		this.related = related;
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
	 * @return useForDoc
	 */
	@Column(name = "USE_FOR_DOC")
	public String getUseForDoc() {
		return useForDoc;
	}

	/**
	 * @param useForDoc new value for useForDoc
	 */
	public void setUseForDoc(String useForDoc) {
		this.useForDoc = useForDoc;
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
	//Anoop Ends

	/**
	 * @return the type
	 */
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/*
	 * Modified for STA-001((Added STA Letter Required,STA Letter Obtained,Date Obtained)) 
	 * by Joseph Roy on 06 MAR 2014 requested by pamy/jessica
	 */
	
	/**
	 * @return staLetterRequired
	 */
	@Column(name = "STA_LETTER_REQUIRED")
	public String getStaLetterRequired() {
		return staLetterRequired;
	}

	/**
	 * @param staLetterRequired new value for staLetterRequired
	 */
	public void setStaLetterRequired(String staLetterRequired) {
		this.staLetterRequired = staLetterRequired;
	}
	
	/**
	 * @return staLetterObtained
	 */
	@Column(name = "STA_LETTER_OBTAINED")
	public String getStaLetterObtained() {
		return staLetterObtained;
	}

	/**
	 * @param staLetterObtained new value for staLetterObtained
	 */
	public void setStaLetterObtained(String staLetterObtained) {
		this.staLetterObtained = staLetterObtained;
	}
	
	
	/**
	 * @return staDateObtained
	 */
	@Column(name = "STA_DATE_OBTAINED")
	public Date getStaDateObtained() {
		return staDateObtained;
	}

	/**
	 * @param staDateObtained new value for staDateObtained
	 */
	public void setStaDateObtained(Date staDateObtained) {
		this.staDateObtained = staDateObtained;
	}

	/**
	 * @return typeOfConsignee
	 */
	@Column(name = "TYPE_OF_CONSIGNEE")
	public String getTypeOfConsignee() {
		return typeOfConsignee;
	}

	/**
	 * @param typeOfConsignee new value for typeOfConsignee
	 */
	public void setTypeOfConsignee(String typeOfConsignee) {
		this.typeOfConsignee = typeOfConsignee;
	}
	
	/**
	 * @return contact
	 */
	@Column(name = "CONTACT")
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact new value for contact
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	/**
	 * @return ownedByGovt
	 */
	@Column(name = "OWNED_BY_GOVT")
	public String getOwnedByGovt() {
		return ownedByGovt;
	}

	/**
	 * @param ownedByGovt new value for ownedByGovt
	 */
	public void setOwnedByGovt(String ownedByGovt) {
		this.ownedByGovt = ownedByGovt;
	}
	
	
	/**
	 * @return the interactWithGovt
	 */
	@Column(name = "INTERACT_WITH_GOVT")
	public String getInteractWithGovt() {
		return interactWithGovt;
	}

	/**
	 * @param interactWithGovt the interactWithGovt to set
	 */
	public void setInteractWithGovt(String interactWithGovt) {
		this.interactWithGovt = interactWithGovt;
	}

	/**
	 * @return the aviationMilNucEndUse
	 */
	@Column(name = "AVIATION_MIL_NUC_END_USE")
	public String getAviationMilNucEndUse() {
		return aviationMilNucEndUse;
	}

	/**
	 * @param aviationMilNucEndUse the aviationMilNucEndUse to set
	 */
	public void setAviationMilNucEndUse(String aviationMilNucEndUse) {
		this.aviationMilNucEndUse = aviationMilNucEndUse;
	}

	/**
	 * @return the estimatedSalesSpendsUSD
	 */
	@Column(name = "ESTIMATED_SALES_SPENDS_USD")
	public BigDecimal getEstimatedSalesSpendsUSD() {
		return estimatedSalesSpendsUSD;
	}

	/**
	 * @param estimatedSalesSpendsUSD the estimatedSalesSpendsUSD to set
	 */
	public void setEstimatedSalesSpendsUSD(BigDecimal estimatedSalesSpendsUSD) {
		this.estimatedSalesSpendsUSD = estimatedSalesSpendsUSD;
	}

	/**
	 * @return the signedContract
	 */
	@Column(name = "SIGNED_CONTRACT")
	public String getSignedContract() {
		return signedContract;
	}

	/**
	 * @param signedContract the signedContract to set
	 */
	public void setSignedContract(String signedContract) {
		this.signedContract = signedContract;
	}

	/**
	 * @return the djScreenedOn
	 */
	@Column(name = "DJ_SCREENED_ON")
	public Date getDjScreenedOn() {
		return djScreenedOn;
	}

	/**
	 * @param djScreenedOn the djScreenedOn to set
	 */
	public void setDjScreenedOn(Date djScreenedOn) {
		this.djScreenedOn = djScreenedOn;
	}

	/**
	 * Returns a string representation of the value object.
	 *
	 * @return the string
	 */
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("consigneeId: " + consigneeId);
		stringBuffer.append("consigneeName: " + consigneeName);
		stringBuffer.append("consigneeAddr1: " + consigneeAddr1);
		stringBuffer.append("consigneeAddr2: " + consigneeAddr2);
		stringBuffer.append("consigneeAddr3: " + consigneeAddr3);
		stringBuffer.append("consigneeCity: " + consigneeCity);
		stringBuffer.append("consigneeState: " + consigneeState);
		stringBuffer.append("consigneeStateName: " + consigneeStateName);
		stringBuffer.append("consigneeCountry: " + consigneeCountry);
		stringBuffer.append("consigneeCountryName: " + consigneeCountryName);
		stringBuffer.append("zip: " + zip);
		stringBuffer.append("phone: " + phone);
		stringBuffer.append("fax: " + fax);
		stringBuffer.append("mt: " + mt);
		stringBuffer.append("cb: " + cb);
		stringBuffer.append("ns: " + ns);
		stringBuffer.append("np: " + np);
		stringBuffer.append("rs: " + rs);
		stringBuffer.append("cc: " + cc);
		stringBuffer.append("at: " + at);
		stringBuffer.append("fc: " + fc);
		stringBuffer.append("sbuCode: " + sbuCode);
		stringBuffer.append("salesPerson: " + salesPerson);
		stringBuffer.append("remarks: " + remarks);
		stringBuffer.append("active: " + active);
		stringBuffer.append("status: " + status);
		stringBuffer.append("createdBy: " + createdBy);
		stringBuffer.append("overriddenBy: " + overriddenBy);
		stringBuffer.append("screenedBy: " + screenedBy);
		stringBuffer.append("creationDate: " + creationDate);
		stringBuffer.append("overriddenDate: " + overriddenDate);
		stringBuffer.append("screenedOn: " + screenedOn);
		stringBuffer.append("uploadedOn: " + uploadedOn);
		stringBuffer.append("userCol1: " + userCol1);
		stringBuffer.append("userCol2: " + userCol2);
		stringBuffer.append("consigneeType: " + consigneeType);
		stringBuffer.append("epciStatus: " + epciStatus);
		stringBuffer.append("assurance: " + assurance);
		stringBuffer.append("meuser: " + meuser);
		stringBuffer.append("intermediateConsigneeCode: " + intermediateConsigneeCode);
		stringBuffer.append("ffCode: " + ffCode);
		stringBuffer.append("soldtoBilltoPurchaser: " + soldtoBilltoPurchaser);
		stringBuffer.append("markFor: " + markFor);
		stringBuffer.append("endUser: " + endUser);
		stringBuffer.append("lastChangeDate: " + lastChangeDate);
		stringBuffer.append("modifiedBy: " + modifiedBy);
		stringBuffer.append("modifiedOn: " + modifiedOn);
		stringBuffer.append("actmgrName: " + actmgrName);
		stringBuffer.append("related: " + related);
		stringBuffer.append("useForDos: " + useForDos);
		stringBuffer.append("useForDoc: " + useForDoc);
		stringBuffer.append("useForAes: " + useForAes);
		stringBuffer.append("sourceSystemId: " + sourceSystemId);
		stringBuffer.append("lastExtractedDate: " + lastExtractedDate);
		stringBuffer.append("sourceSystemPartyId: " + sourceSystemPartyId);
		stringBuffer.append("type: " + type);
		stringBuffer.append("staLetterRequired: " + staLetterRequired);
		stringBuffer.append("staLetterObtained: " + staLetterObtained);
		stringBuffer.append("staDateObtained: " + staDateObtained);
		stringBuffer.append("typeOfConsignee: " + typeOfConsignee);
		stringBuffer.append("contact: " + contact);
		stringBuffer.append("ownedByGovt: " + ownedByGovt);
		stringBuffer.append("interactWithGovt: " + interactWithGovt);
		stringBuffer.append("aviationMilNucEndUse: " + aviationMilNucEndUse);
		stringBuffer.append("estimatedSalesSpendsUSD: " + estimatedSalesSpendsUSD);
		stringBuffer.append("signedContract: " + signedContract);
		stringBuffer.append("djScreenedOn: " + djScreenedOn);
		return stringBuffer.toString();
	}
}
package com.gtn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "EXPORTER")
public class ExporterValue implements Serializable, Model  {


	/**
	 * Attribute expCode.
	 */
	private String expCode;


	/**
	 * Attribute expName.
	 */
	private String expName;


	/**
	 * Attribute expAddr1.
	 */
	private String expAddr1;


	/**
	 * Attribute expAddr2.
	 */
	private String expAddr2;


	/**
	 * Attribute expAddr3.
	 */
	private String expAddr3;


	/**
	 * Attribute expCity.
	 */
	private String expCity;


	/**
	 * Attribute expState.
	 */
	private String expState;


	/**
	 * Attribute expStateName.
	 */
	private String expStateName;


	/**
	 * Attribute expCountry.
	 */
	private String expCountry;


	/**
	 * Attribute expCountryName.
	 */
	private String expCountryName;


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
	 * Attribute shipperAuthorizationSymbol.
	 */
	private String shipperAuthorizationSymbol;


	/**
	 * Attribute userCol1.
	 */
	private String userCol1;


	/**
	 * Attribute userCol2.
	 */
	private String userCol2;


	/**
	 * Attribute sbu.
	 */
	private String sbu;


	/**
	 * Attribute filerIdType.
	 */
	private String filerIdType;


	/**
	 * Attribute filerId.
	 */
	private String filerId;


	/**
	 * Attribute irsNumber.
	 */
	private String irsNumber;


	/**
	 * Attribute irsNumberType.
	 */
	private String irsNumberType;


	/**
	 * Attribute transmitterId.
	 */
	private Long transmitterId;


	/**
	 * Attribute sbuCode.
	 */
	private String sbuCode;


	/**
	 * Attribute createdBy.
	 */
	private String createdBy;


	/**
	 * Attribute createdOn.
	 */
	private Date createdOn;


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
	 * Attribute dosRegistrationDate.
	 */
	private Date dosRegistrationDate;


	/**
	 * Attribute dosRegistrationExpiryDate.
	 */
	private Date dosRegistrationExpiryDate;


	/**
	 * Attribute dosAgreementExpiryDate.
	 */
	private Date dosAgreementExpiryDate;


	/**
	 * Attribute dosRegistrationNumber.
	 */
	private String dosRegistrationNumber;


	/**
	 * Attribute docRegistrationDate.
	 */
	private Date docRegistrationDate;


	/**
	 * Attribute docRegistrationExpiryDate.
	 */
	private Date docRegistrationExpiryDate;


	/**
	 * Attribute docFacilityRegistrationNumber.
	 */
	private String docFacilityRegistrationNumber;


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
	 * Attribute sgExpPermitQualif.
	 */
	private String sgExpPermitQualif;




	/**
	 * Attribute screenedBy.
	 */
	private String screenedBy;


	/**
	 * Attribute screenedOn.
	 */
	private Date screenedOn;


	/**
	 * Attribute overriddenBy.
	 */
	private String overriddenBy;

	/**
	 * Attribute overriddenDate.
	 */
	private Date overriddenDate;


	/**
	 * Attribute status.
	 */
	private String status;
	
	/**
	 * 
	 */
	private String active;
	
	/**
	 * Attribute filerExpIdType.
	 */
	private String filerExpIdType;


	/**
	 * Attribute filerExpId.
	 */
	private String filerExpId;
	
	private String email;

    /**
     * Instantiates a new ExporterValue.
     */
    public ExporterValue() {
    }

    /**
     * Instantiates a new ExporterValue.
     *
          * @param expCode
          * @param expName
          * @param expAddr1
          * @param expAddr2
          * @param expAddr3
          * @param expCity
          * @param expState
          * @param expStateName
          * @param expCountry
          * @param expCountryName
          * @param zip
          * @param phone
          * @param fax
          * @param shipperAuthorizationSymbol
          * @param userCol1
          * @param userCol2
          * @param sbu
          * @param filerIdType
          * @param filerId
          * @param irsNumber
          * @param irsNumberType
          * @param transmitterId
          * @param sbuCode
          * @param createdBy
          * @param createdOn
          * @param modifiedBy
          * @param modifiedOn
          * @param actmgrName
          * @param dosRegistrationDate
          * @param dosRegistrationExpiryDate
          * @param dosAgreementExpiryDate
          * @param dosRegistrationNumber
          * @param docRegistrationDate
          * @param docRegistrationExpiryDate
          * @param docFacilityRegistrationNumber
          * @param useForDos
          * @param useForDoc
          * @param useForAes
          * @param sgExpPermitQualif
          * @param screenedBy
          * @param screenedOn
          * @param overriddenBy
          * @param overriddenDate
          * @param status
          * @param active
          * @param filerExpIdType
          * @param filerExpId
          */
    public ExporterValue (String expCode, String expName, String expAddr1, String expAddr2, String expAddr3, String expCity, String expState, String expStateName, String expCountry, String expCountryName, String zip, String phone, String fax, String shipperAuthorizationSymbol, String userCol1, String userCol2, String sbu, String filerIdType, String filerId, String irsNumber, String irsNumberType, Long transmitterId, String sbuCode, String createdBy, Date createdOn, String modifiedBy, Date modifiedOn, String actmgrName, Date dosRegistrationDate, Date dosRegistrationExpiryDate, Date dosAgreementExpiryDate, String dosRegistrationNumber, Date docRegistrationDate, Date docRegistrationExpiryDate, String docFacilityRegistrationNumber, String useForDos, String useForDoc, String useForAes, String sgExpPermitQualif,String screenedBy,Date screenedOn,String overriddenBy,Date overriddenDate,String status,String active, String filerExpIdType, String filerExpId) {
	        this.expCode = expCode;
	        this.expName = expName;
	        this.expAddr1 = expAddr1;
	        this.expAddr2 = expAddr2;
	        this.expAddr3 = expAddr3;
	        this.expCity = expCity;
	        this.expState = expState;
	        this.expStateName = expStateName;
	        this.expCountry = expCountry;
	        this.expCountryName = expCountryName;
	        this.zip = zip;
	        this.phone = phone;
	        this.fax = fax;
	        this.shipperAuthorizationSymbol = shipperAuthorizationSymbol;
	        this.userCol1 = userCol1;
	        this.userCol2 = userCol2;
	        this.sbu = sbu;
	        this.filerIdType = filerIdType;
	        this.filerId = filerId;
	        this.irsNumber = irsNumber;
	        this.irsNumberType = irsNumberType;
	        this.transmitterId = transmitterId;
	        this.sbuCode = sbuCode;
	        this.createdBy = createdBy;
	        this.createdOn = createdOn;
	        this.modifiedBy = modifiedBy;
	        this.modifiedOn = modifiedOn;
	        this.actmgrName = actmgrName;
	        this.dosRegistrationDate = dosRegistrationDate;
	        this.dosRegistrationExpiryDate = dosRegistrationExpiryDate;
	        this.dosAgreementExpiryDate = dosAgreementExpiryDate;
	        this.dosRegistrationNumber = dosRegistrationNumber;
	        this.docRegistrationDate = docRegistrationDate;
	        this.docRegistrationExpiryDate = docRegistrationExpiryDate;
	        this.docFacilityRegistrationNumber = docFacilityRegistrationNumber;
	        this.useForDos = useForDos;
	        this.useForDoc = useForDoc;
	        this.useForAes = useForAes;
	        this.sgExpPermitQualif = sgExpPermitQualif;
	        this.status = status;
			this.overriddenBy = overriddenBy;
			this.screenedBy = screenedBy;
			this.overriddenDate = overriddenDate;
			this.screenedOn = screenedOn;
			this.active=active;
			this.filerExpIdType = filerExpIdType;
			this.filerExpId=filerExpId;

	    }


	/**
	 * @return expCode
	 */
	@Id
	@Column(name = "EXP_CODE")
		public String getExpCode() {
		return expCode;
	}

	/**
	 * @param expCode new value for expCode
	 */
	public void setExpCode(String expCode) {
		this.expCode = expCode;
	}


	/**
	 * @return expName
	 */
	@Column(name = "EXP_NAME")
		public String getExpName() {
		return expName;
	}

	/**
	 * @param expName new value for expName
	 */
	public void setExpName(String expName) {
		this.expName = expName;
	}


	/**
	 * @return expAddr1
	 */
	@Column(name = "EXP_ADDR1")
		public String getExpAddr1() {
		return expAddr1;
	}

	/**
	 * @param expAddr1 new value for expAddr1
	 */
	public void setExpAddr1(String expAddr1) {
		this.expAddr1 = expAddr1;
	}


	/**
	 * @return expAddr2
	 */
	@Column(name = "EXP_ADDR2")
		public String getExpAddr2() {
		return expAddr2;
	}

	/**
	 * @param expAddr2 new value for expAddr2
	 */
	public void setExpAddr2(String expAddr2) {
		this.expAddr2 = expAddr2;
	}


	/**
	 * @return expAddr3
	 */
	@Column(name = "EXP_ADDR3")
		public String getExpAddr3() {
		return expAddr3;
	}

	/**
	 * @param expAddr3 new value for expAddr3
	 */
	public void setExpAddr3(String expAddr3) {
		this.expAddr3 = expAddr3;
	}


	/**
	 * @return expCity
	 */
	@Column(name = "EXP_CITY")
		public String getExpCity() {
		return expCity;
	}

	/**
	 * @param expCity new value for expCity
	 */
	public void setExpCity(String expCity) {
		this.expCity = expCity;
	}


	/**
	 * @return expState
	 */
	@Column(name = "EXP_STATE")
		public String getExpState() {
		return expState;
	}

	/**
	 * @param expState new value for expState
	 */
	public void setExpState(String expState) {
		this.expState = expState;
	}


	/**
	 * @return expStateName
	 */
	@Column(name = "EXP_STATE_NAME")
		public String getExpStateName() {
		return expStateName;
	}

	/**
	 * @param expStateName new value for expStateName
	 */
	public void setExpStateName(String expStateName) {
		this.expStateName = expStateName;
	}


	/**
	 * @return expCountry
	 */
	@Column(name = "EXP_COUNTRY")
		public String getExpCountry() {
		return expCountry;
	}

	/**
	 * @param expCountry new value for expCountry
	 */
	public void setExpCountry(String expCountry) {
		this.expCountry = expCountry;
	}


	/**
	 * @return expCountryName
	 */
	@Column(name = "EXP_COUNTRY_NAME")
		public String getExpCountryName() {
		return expCountryName;
	}

	/**
	 * @param expCountryName new value for expCountryName
	 */
	public void setExpCountryName(String expCountryName) {
		this.expCountryName = expCountryName;
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
	 * @return shipperAuthorizationSymbol
	 */
	@Column(name = "SHIPPER_AUTHORIZATION_SYMBOL")
		public String getShipperAuthorizationSymbol() {
		return shipperAuthorizationSymbol;
	}

	/**
	 * @param shipperAuthorizationSymbol new value for shipperAuthorizationSymbol
	 */
	public void setShipperAuthorizationSymbol(String shipperAuthorizationSymbol) {
		this.shipperAuthorizationSymbol = shipperAuthorizationSymbol;
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
	 * @return sbu
	 */
	@Column(name = "SBU")
		public String getSbu() {
		return sbu;
	}

	/**
	 * @param sbu new value for sbu
	 */
	public void setSbu(String sbu) {
		this.sbu = sbu;
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
	 * @return irsNumber
	 */
	@Column(name = "IRS_NUMBER")
		public String getIrsNumber() {
		return irsNumber;
	}

	/**
	 * @param irsNumber new value for irsNumber
	 */
	public void setIrsNumber(String irsNumber) {
		this.irsNumber = irsNumber;
	}


	/**
	 * @return irsNumberType
	 */
	@Column(name = "IRS_NUMBER_TYPE")
		public String getIrsNumberType() {
		return irsNumberType;
	}

	/**
	 * @param irsNumberType new value for irsNumberType
	 */
	public void setIrsNumberType(String irsNumberType) {
		this.irsNumberType = irsNumberType;
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
	 * @return createdBy
	 */
	@Column(name = "CREATED_BY", updatable=false)
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
	 * @return createdOn
	 */
	@Column(name = "CREATED_ON", updatable=false)
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
	 * @return dosRegistrationDate
	 */
	@Column(name = "DOS_REGISTRATION_DATE")
		public Date getDosRegistrationDate() {
		return dosRegistrationDate;
	}

	/**
	 * @param dosRegistrationDate new value for dosRegistrationDate
	 */
	public void setDosRegistrationDate(Date dosRegistrationDate) {
		this.dosRegistrationDate = dosRegistrationDate;
	}


	/**
	 * @return dosRegistrationExpiryDate
	 */
	@Column(name = "DOS_REGISTRATION_EXPIRY_DATE")
		public Date getDosRegistrationExpiryDate() {
		return dosRegistrationExpiryDate;
	}

	/**
	 * @param dosRegistrationExpiryDate new value for dosRegistrationExpiryDate
	 */
	public void setDosRegistrationExpiryDate(Date dosRegistrationExpiryDate) {
		this.dosRegistrationExpiryDate = dosRegistrationExpiryDate;
	}


	/**
	 * @return dosAgreementExpiryDate
	 */
	@Column(name = "DOS_AGREEMENT_EXPIRY_DATE")
		public Date getDosAgreementExpiryDate() {
		return dosAgreementExpiryDate;
	}

	/**
	 * @param dosAgreementExpiryDate new value for dosAgreementExpiryDate
	 */
	public void setDosAgreementExpiryDate(Date dosAgreementExpiryDate) {
		this.dosAgreementExpiryDate = dosAgreementExpiryDate;
	}


	/**
	 * @return dosRegistrationNumber
	 */
	@Column(name = "DOS_REGISTRATION_NO")
		public String getDosRegistrationNumber() {
		return dosRegistrationNumber;
	}

	/**
	 * @param dosRegistrationNumber new value for dosRegistrationNumber
	 */
	public void setDosRegistrationNumber(String dosRegistrationNumber) {
		this.dosRegistrationNumber = dosRegistrationNumber;
	}


	/**
	 * @return docRegistrationDate
	 */
	@Column(name = "DOC_REGISTRATION_DATE")
		public Date getDocRegistrationDate() {
		return docRegistrationDate;
	}

	/**
	 * @param docRegistrationDate new value for docRegistrationDate
	 */
	public void setDocRegistrationDate(Date docRegistrationDate) {
		this.docRegistrationDate = docRegistrationDate;
	}


	/**
	 * @return docRegistrationExpiryDate
	 */
	@Column(name = "DOC_REGISTRATION_EXPIRY_DATE")
		public Date getDocRegistrationExpiryDate() {
		return docRegistrationExpiryDate;
	}

	/**
	 * @param docRegistrationExpiryDate new value for docRegistrationExpiryDate
	 */
	public void setDocRegistrationExpiryDate(Date docRegistrationExpiryDate) {
		this.docRegistrationExpiryDate = docRegistrationExpiryDate;
	}


	/**
	 * @return docFacilityRegistrationNumber
	 */
	@Column(name = "DOC_FACILITY_REGISTRATION_NO")
		public String getDocFacilityRegistrationNumber() {
		return docFacilityRegistrationNumber;
	}

	/**
	 * @param docFacilityRegistrationNumber new value for docFacilityRegistrationNumber
	 */
	public void setDocFacilityRegistrationNumber(String docFacilityRegistrationNumber) {
		this.docFacilityRegistrationNumber = docFacilityRegistrationNumber;
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

	/**
	 * @return sgExpPermitQualif
	 */
	@Column(name = "SG_EXP_PERMIT_QUALIF")
		public String getSgExpPermitQualif() {
		return sgExpPermitQualif;
	}

	/**
	 * @param sgExpPermitQualif new value for sgExpPermitQualif
	 */
	public void setSgExpPermitQualif(String sgExpPermitQualif) {
		this.sgExpPermitQualif = sgExpPermitQualif;
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

	/**
	 * @return active
	 */
	@Column(name="ACTIVE")
	public String getActive() {
		return active;
	}

	/**
	 * @param active  new value for active
	 */
	public void setActive(String active) {
		this.active = active;
	}
	
	/**
	 * @return filerExpIdType
	 */
	@Column(name="FILER_EXP_ID_TYPE")
    public String getFilerExpIdType() {
		return filerExpIdType;
	}
	
	/**
	 * @param filerExpIdType  new value for filerExpIdType
	 */
	public void setFilerExpIdType(String filerExpIdType) {
		this.filerExpIdType = filerExpIdType;
	}
	
	/**
	 * @return filerExpId
	 */
	@Column(name="FILER_EXP_ID")
	public String getFilerExpId() {
		return filerExpId;
	}
	
	/**
	 * @param filerExpId  new value for filerExpId
	 */
	public void setFilerExpId(String filerExpId) {
		this.filerExpId = filerExpId;
	}

	
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
     * Returns a string representation of the value object.
     *
     * @return the string
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
	        stringBuffer.append("expCode: " + expCode);
	        stringBuffer.append("expName: " + expName);
	        stringBuffer.append("expAddr1: " + expAddr1);
	        stringBuffer.append("expAddr2: " + expAddr2);
	        stringBuffer.append("expAddr3: " + expAddr3);
	        stringBuffer.append("expCity: " + expCity);
	        stringBuffer.append("expState: " + expState);
	        stringBuffer.append("expStateName: " + expStateName);
	        stringBuffer.append("expCountry: " + expCountry);
	        stringBuffer.append("expCountryName: " + expCountryName);
	        stringBuffer.append("zip: " + zip);
	        stringBuffer.append("phone: " + phone);
	        stringBuffer.append("fax: " + fax);
	        stringBuffer.append("shipperAuthorizationSymbol: " + shipperAuthorizationSymbol);
	        stringBuffer.append("userCol1: " + userCol1);
	        stringBuffer.append("userCol2: " + userCol2);
	        stringBuffer.append("sbu: " + sbu);
	        stringBuffer.append("filerIdType: " + filerIdType);
	        stringBuffer.append("filerId: " + filerId);
	        stringBuffer.append("irsNumber: " + irsNumber);
	        stringBuffer.append("irsNumberType: " + irsNumberType);
	        stringBuffer.append("transmitterId: " + transmitterId);
	        stringBuffer.append("sbuCode: " + sbuCode);
	        stringBuffer.append("createdBy: " + createdBy);
	        stringBuffer.append("createdOn: " + createdOn);
	        stringBuffer.append("modifiedBy: " + modifiedBy);
	        stringBuffer.append("modifiedOn: " + modifiedOn);
	        stringBuffer.append("actmgrName: " + actmgrName);
	        stringBuffer.append("dosRegistrationDate: " + dosRegistrationDate);
	        stringBuffer.append("dosRegistrationExpiryDate: " + dosRegistrationExpiryDate);
	        stringBuffer.append("dosAgreementExpiryDate: " + dosAgreementExpiryDate);
	        stringBuffer.append("dosRegistrationNumber: " + dosRegistrationNumber);
	        stringBuffer.append("docRegistrationDate: " + docRegistrationDate);
	        stringBuffer.append("docRegistrationExpiryDate: " + docRegistrationExpiryDate);
	        stringBuffer.append("docFacilityRegistrationNumber: " + docFacilityRegistrationNumber);
	        stringBuffer.append("useForDos: " + useForDos);
	        stringBuffer.append("useForDoc: " + useForDoc);
	        stringBuffer.append("useForAes: " + useForAes);
	        stringBuffer.append("sgExpPermitQualif: " + sgExpPermitQualif);
			stringBuffer.append("status: " + status);
			stringBuffer.append("overriddenBy: " + overriddenBy);
			stringBuffer.append("screenedBy: " + screenedBy);
			stringBuffer.append("overriddenDate: " + overriddenDate);
			stringBuffer.append("screenedOn: " + screenedOn);
			stringBuffer.append("active: " + active);
			stringBuffer.append("filerExpIdType: " + filerExpIdType);
			stringBuffer.append("filerExpId: " + filerExpId);
	        return stringBuffer.toString();
    }
}
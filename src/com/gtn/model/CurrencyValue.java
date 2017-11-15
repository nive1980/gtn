package com.gtn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "CURRENCY")
@Access(AccessType.PROPERTY)
public class CurrencyValue implements Serializable, Model  {


	/**
	 * Attribute currencyCode.
	 */
	private String currencyCode;


	/**
	 * Attribute description.
	 */
	private String description;


	/**
	 * Attribute countryCode.
	 */
	private String countryCode;


	/**
	 * Attribute baseCurrFlag.
	 */
	private String baseCurrFlag;


	/**
	 * Attribute conversionRate.
	 */
	private Float conversionRate;


	/**
	 * Attribute status.
	 */
	private String status;
	
	
	 private Date lastUpdatedDate;
		
	 private Date createdDate = new Date();
		
	 private String createdBy;

	 private String modifiedBy;
	 
	 /**
	  * Attribute customCountryCode.
	  */
	 
	 private String customCountryCode;
	 /**
	  * Attribute customCurrencyCode.
	  */
	 
	 private String customCurrencyCode;

    /**
     * Instantiates a new CurrencyValue.
     */
    public CurrencyValue() {
    }

    /**
     * Instantiates a new CurrencyValue.
     *
          * @param currencyCode
          * @param description
          * @param countryCode
          * @param baseCurrFlag
          * @param conversionRate
          * @param status
          * @param customCountryCode
          */
    public CurrencyValue (String currencyCode, String description, String countryCode, String baseCurrFlag, Float conversionRate, String status,String customCountryCode,String customCurrencyCode) {
	        this.currencyCode = currencyCode;
	        this.description = description;
	        this.countryCode = countryCode;
	        this.baseCurrFlag = baseCurrFlag;
	        this.conversionRate = conversionRate;
	        this.status = status;
	        this.customCountryCode = customCountryCode;
	        this.customCurrencyCode = customCurrencyCode;
	    }


	/**
	 * @return currencyCode
	 */
	@Id
	@Column(name = "CURRENCY_CODE")
		public String getCurrencyCode() {
		return currencyCode;
	}
	/**
	 * @return customCountryCode
	 */
	@Id
	@Column(name = "CUSTOM_COUNTRY_CODE")
    public String getCustomCountryCode() {
		return customCountryCode;
	}

	public void setCustomCountryCode(String customCountryCode) {
		this.customCountryCode = customCountryCode;
	}
	/**
	 * @param currencyCode new value for currencyCode
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	/**
	 * @return description
	 */
	@Column(name = "DESCRIPTION")
		public String getDescription() {
		return description;
	}

	/**
	 * @param description new value for description
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return countryCode
	 */
	@Column(name = "COUNTRY_CODE")
		public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode new value for countryCode
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	/**
	 * @return baseCurrFlag
	 */
	@Column(name = "BASE_CURR_FLAG")
		public String getBaseCurrFlag() {
		return baseCurrFlag;
	}

	/**
	 * @param baseCurrFlag new value for baseCurrFlag
	 */
	public void setBaseCurrFlag(String baseCurrFlag) {
		this.baseCurrFlag = baseCurrFlag;
	}


	/**
	 * @return conversionRate
	 */
	@Column(name = "CONVERSION_RATE")
		public Float getConversionRate() {
		return conversionRate;
	}

	/**
	 * @param conversionRate new value for conversionRate
	 */
	public void setConversionRate(Float conversionRate) {
		this.conversionRate = conversionRate;
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
	
	
	@Type(type = "java.util.Date")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_ON")
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	@Type(type = "java.util.Date")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name ="CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "MODIFIED_BY")
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	

	@Column(name = "CUSTOM_CURRENCY_CODE")
	public String getCustomCurrencyCode() {
		return customCurrencyCode;
	}

	public void setCustomCurrencyCode(String customCurrencyCode) {
		this.customCurrencyCode = customCurrencyCode;
	}

	/**
     * Returns a string representation of the value object.
     *
     * @return the string
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
	        stringBuffer.append("currencyCode: " + currencyCode);
	        stringBuffer.append("description: " + description);
	        stringBuffer.append("countryCode: " + countryCode);
	        stringBuffer.append("baseCurrFlag: " + baseCurrFlag);
	        stringBuffer.append("conversionRate: " + conversionRate);
	        stringBuffer.append("status: " + status);
	        stringBuffer.append("customCountryCode: " + customCountryCode);
	        stringBuffer.append("customCurrencyCode: " + customCurrencyCode);
	        return stringBuffer.toString();
    }
}
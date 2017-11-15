package com.gtn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "SCH_B")
public class SchBValue implements Serializable, Model  {


	/**
	 * Attribute schbNo.
	 */
	private String schbNo;


	/**
	 * Attribute primaryUnit.
	 */
	private String primaryUnit;


	/**
	 * Attribute secondaryUnit.
	 */
	private String secondaryUnit;


	/**
	 * Attribute schbDesc.
	 */
	private String schbDesc;


	/**
	 * Attribute status.
	 */
	private String status;


    /**
     * Instantiates a new SchBValue.
     */
    public SchBValue() {
    }

    
    private Date lastUpdatedDate;

	private Date createdDate = new Date();

	private String createdBy;

	private String modifiedBy; 
    /**
     * Instantiates a new SchBValue.
     *
          * @param schbNo
          * @param primaryUnit
          * @param secondaryUnit
          * @param schbDesc
          * @param status
          */
    public SchBValue (String schbNo, String primaryUnit, String secondaryUnit, String schbDesc, String status) {
	        this.schbNo = schbNo;
	        this.primaryUnit = primaryUnit;
	        this.secondaryUnit = secondaryUnit;
	        this.schbDesc = schbDesc;
	        this.status = status;
	    }


	/**
	 * @return schbNo
	 */
	@Id
	@Column(name = "SCHB_NO")
		public String getSchbNo() {
		return schbNo;
	}

	/**
	 * @param schbNo new value for schbNo
	 */
	public void setSchbNo(String schbNo) {
		this.schbNo = schbNo;
	}


	/**
	 * @return primaryUnit
	 */
	@Column(name = "PRIMARY_UNIT")
		public String getPrimaryUnit() {
		return primaryUnit;
	}

	/**
	 * @param primaryUnit new value for primaryUnit
	 */
	public void setPrimaryUnit(String primaryUnit) {
		this.primaryUnit = primaryUnit;
	}


	/**
	 * @return secondaryUnit
	 */
	@Column(name = "SECONDARY_UNIT")
		public String getSecondaryUnit() {
		return secondaryUnit;
	}

	/**
	 * @param secondaryUnit new value for secondaryUnit
	 */
	public void setSecondaryUnit(String secondaryUnit) {
		this.secondaryUnit = secondaryUnit;
	}


	/**
	 * @return schbDesc
	 */
	@Column(name = "SCHB_DESC")
		public String getSchbDesc() {
		return schbDesc;
	}

	/**
	 * @param schbDesc new value for schbDesc
	 */
	public void setSchbDesc(String schbDesc) {
		this.schbDesc = schbDesc;
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

	
	/*Added By Rohit Asthana On 2/26/2013 for
	 created By,created on, Modified by,Modified on 
	*/
	
	

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_ON")
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	
	

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

    /**
     * Returns a string representation of the value object.
     *
     * @return the string
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
	        stringBuffer.append("schbNo: " + schbNo);
	        stringBuffer.append("primaryUnit: " + primaryUnit);
	        stringBuffer.append("secondaryUnit: " + secondaryUnit);
	        stringBuffer.append("schbDesc: " + schbDesc);
	        stringBuffer.append("status: " + status);
	        return stringBuffer.toString();
    }
}
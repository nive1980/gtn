package com.gtn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "MODE_OF_TRANSPORT")
public class ModeOfTransportValue implements Serializable, Model {


	/**
	 * Attribute motCode.
	 */
	private String motCode;


	/**
	 * Attribute motDes.
	 */
	private String motDes;


	/**
	 * Attribute motId.
	 */
	private String motId;


	/**
	 * Attribute status.
	 */
	private String status;

	/**
	 * Attribute country.
	 */
	private String country;
	
	
	private String typeOfTransport;

    /**
     * Instantiates a new ModeOfTransportValue.
     */
	
	private Date lastUpdatedDate;
	
	private Date createdDate = new Date();
	
    private String createdBy;

	private String modifiedBy; 
	
    public ModeOfTransportValue() {
    }

    /**
     * Instantiates a new ModeOfTransportValue.
     *
          * @param motCode
          * @param motDes
          * @param motId
          * @param status
          */
    public ModeOfTransportValue (String motCode, String motDes, String motId, String status, String country, String typeOfTransport) {
	        this.motCode = motCode;
	        this.motDes = motDes;
	        this.motId = motId;
	        this.status = status;
	        this.country = country;
	        this.typeOfTransport = typeOfTransport;
	    }


	/**
	 * @return motCode
	 */
	@Id
	@Column(name = "MOT_CODE")
		public String getMotCode() {
		return motCode;
	}

	/**
	 * @param motCode new value for motCode
	 */
	public void setMotCode(String motCode) {
		this.motCode = motCode;
	}


	/**
	 * @return motDes
	 */
	@Column(name = "MOT_DES")
		public String getMotDes() {
		return motDes;
	}

	/**
	 * @param motDes new value for motDes
	 */
	public void setMotDes(String motDes) {
		this.motDes = motDes;
	}


	/**
	 * @return motId
	 */
	@Column(name = "MOT_ID")
		public String getMotId() {
		return motId;
	}

	/**
	 * @param motId new value for motId
	 */
	public void setMotId(String motId) {
		this.motId = motId;
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
	 * @return country
	 */
	@Id
	@Column(name = "COUNTRY")
	public String getCountry() {
		return country;
	}

	/**
	 * @param country new value for country
	 */
	public void setCountry(String country) {
		this.country = country;
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
	
	@Column(name="Type_of_Transport")
    public String getTypeOfTransport() {
		return typeOfTransport;
	}

	public void setTypeOfTransport(String typeOfTransport) {
		this.typeOfTransport = typeOfTransport;
	}

	/**
     * Returns a string representation of the value object.
     *
     * @return the string
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
	        stringBuffer.append("motCode: " + motCode);
	        stringBuffer.append("motDes: " + motDes);
	        stringBuffer.append("motId: " + motId);
	        stringBuffer.append("status: " + status);
	        stringBuffer.append("country: " + country);
	        stringBuffer.append("typeOfTransport: " + typeOfTransport);
	        return stringBuffer.toString();
    }
}
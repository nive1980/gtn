package com.gtn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;


/**
 * <p>Entity Bean for the table DPL_REASON</p>
 * All Entity Beans should implement the marker interface EAOEntity<br>
 * This is used to generalize the CRUD functionalities for all the Entities<br>
 * The Entity Objects are passed with reference to this interface. As a result a single method
 * is enough for each of the crud functionalities <br><br> For e.g. <br>
 * <code>EAOEntity entity = new CommodityCodes();<br>
 * remoteObject.create(entity);</code><br>
 * where CommodityCodes is the Entity Bean which implements this interface
 *
 * @author Rajiv Radhakrishnan
 * @version 1.0
 *
 */
@Entity
@Table(name = "DPL_REASON")
public class DplReasonValue implements Serializable, Model {


	/**
	 * Attribute reasonCode.
	 */
	private String reasonCode;


	/**
	 * Attribute reason.
	 */
	private String reason;
	
	/**
	 * Attribute denyOverride.
	 */
	private String denyOverride;
	/**
	 * Attribute createdOn.
	 */
	private Date createdOn;
	/**
	 * Attribute createdBy.
	 */
    private String createdBy;
    /**
	 * Attribute modifiedOn.
	 */
	private Date modifiedOn;
    /**
	 * Attribute modifiedBy.
	 */
	private String modifiedBy;
	 /**
	  * Attribute active.
	  */
	private String active;


    /**
     * Instantiates a new DplReasonValue.
     */
    public DplReasonValue() {
    }

    /**
     * Instantiates a new DplReasonValue.
     * @param reasonCode
     * @param reason
     * @param denyOverride
     * @param createdOn
     * @param createdBy
     * @param modifiedOn
     * @param modifiedBy
     * @param active
     */
    public DplReasonValue (String reasonCode, String reason,String denyOverride,  Date createdOn, String createdBy, Date modifiedOn, String modifiedBy,String active) {
	        this.reasonCode = reasonCode;
	        this.reason = reason;
	        this.denyOverride = denyOverride;
	        this.createdOn = createdOn;
	        this.createdBy = createdBy;
	        this.modifiedOn = modifiedOn;
	        this.modifiedBy = modifiedBy;
	        this.active = active;
	    }


	/**
	 * @return reasonCode
	 */
	@Id
	@Column(name = "REASON_CODE")
		public String getReasonCode() {
		return reasonCode;
	}

	/**
	 * @param reasonCode new value for reasonCode
	 */
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}


	/**
	 * @return reason
	 */
	@Column(name = "REASON")
		public String getReason() {
		return reason;
	}

	/**
	 * @param reason new value for reason
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return denyOverride
	 */
	@Column(name = "DENY_OVERRIDE")
		public String getDenyOverride() {
		return denyOverride;
	}

	/**
	 * @param denyOverride new value for denyOverride
	 */
	public void setDenyOverride(String denyOverride) {
		this.denyOverride = denyOverride;
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
     * Returns a string representation of the value object.
     *
     * @return the string
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
	        stringBuffer.append("reasonCode: " + reasonCode);
	        stringBuffer.append("reason: " + reason);
	        stringBuffer.append("denyOverride: " + denyOverride);
	        stringBuffer.append("createdOn: " + createdOn);
	        stringBuffer.append("createdBy: " + createdBy);
	        stringBuffer.append("modifiedOn: " + modifiedOn);
	        stringBuffer.append("modifiedBy: " + modifiedBy);
	        stringBuffer.append("active: " + active);
	        return stringBuffer.toString();
    }
}
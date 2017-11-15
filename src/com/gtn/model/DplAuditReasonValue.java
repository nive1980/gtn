package com.gtn.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@ IdClass (DplAuditReasonPK.class)
@Table(name = "DPL_AUDIT_REASON")
public class DplAuditReasonValue implements Serializable, Model  {


	/**
	 * Attribute dplAuditNo.
	 */
	private Long dplAuditNo;


	/**
	 * Attribute reasonCode.
	 */
	private String reasonCode;


    /**
     * Instantiates a new DplAuditReasonValue.
     */
    public DplAuditReasonValue() {
    }

    /**
     * Instantiates a new DplAuditReasonValue.
     *
          * @param dplAuditNo
          * @param reasonCode
          */
    public DplAuditReasonValue (Long dplAuditNo, String reasonCode) {
	        this.dplAuditNo = dplAuditNo;
	        this.reasonCode = reasonCode;
	    }


	/**
	 * @return dplAuditNo
	 */
	@Id
	@Column(name = "DPL_AUDIT_NO")
		public Long getDplAuditNo() {
		return dplAuditNo;
	}

	/**
	 * @param dplAuditNo new value for dplAuditNo
	 */
	public void setDplAuditNo(Long dplAuditNo) {
		this.dplAuditNo = dplAuditNo;
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
     * Returns a string representation of the value object.
     *
     * @return the string
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
	        stringBuffer.append("dplAuditNo: " + dplAuditNo);
	        stringBuffer.append("reasonCode: " + reasonCode);
	        return stringBuffer.toString();
    }
}
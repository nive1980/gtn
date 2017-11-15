package com.gtn.model;

import java.io.Serializable;

import javax.persistence.Column;

public class DplAuditReasonPK implements Serializable {

	/**
	 * Attribute dplAuditNo
	 */
	private Long dplAuditNo;

	/**
	 * Attribute reasonCode
	 */
	private String reasonCode;


    /**
     * Instantiates a new DplAuditReasonPK.
     */
    public DplAuditReasonPK() {
    }

    /**
     * Instantiates a new DplAuditReasonPK.
     *
                   * @param dplAuditNo
                                                  * @param reasonCode
                    */
    public DplAuditReasonPK (Long dplAuditNo, String reasonCode) {
				this.dplAuditNo = dplAuditNo;
										this.reasonCode = reasonCode;
			    }

	/**
	 * Return dplAuditNo
	 */
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
	 * Return reasonCode
	 */
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
	 * calculate hashcode
	 */
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

	/**
	 * equals method
	*/
	@Override
	public boolean equals(Object object)
	{
		return super.equals(object);
	}

}
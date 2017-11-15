package com.gtn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "DPL_AUDIT")
public class DplAuditValue implements Serializable, Model  {


	/**
	 * Attribute dplAuditNo.
	 */
	private Long dplAuditNo;


	/**
	 * Attribute tablename.
	 */
	private String tablename;


	/**
	 * Attribute tablekey.
	 */
	private String tablekey;


	/**
	 * Attribute fieldType.
	 */
	private String fieldType;


	/**
	 * Attribute oldStatus.
	 */
	private String oldStatus;


	/**
	 * Attribute newStatus.
	 */
	private String newStatus;


	/**
	 * Attribute otherReason.
	 */
	private String otherReason;


	/**
	 * Attribute createdBy.
	 */
	private String createdBy;


	/**
	 * Attribute createdOn.
	 */
	private Date createdOn;


	/**
	 * Attribute sbuCode.
	 */
	private String sbuCode;


	/**
	 * Attribute actmgrName.
	 */
	private String actmgrName;


	/**
	 * Attribute entityName.
	 */
	private String entityName;


	/**
	 * Attribute partyName.
	 */
	private String partyName;


    /**
     * Instantiates a new DplAuditValue.
     */
    public DplAuditValue() {
    }

    /**
     * Instantiates a new DplAuditValue.
     *
          * @param dplAuditNo
          * @param tablename
          * @param tablekey
          * @param fieldType
          * @param oldStatus
          * @param newStatus
          * @param otherReason
          * @param createdBy
          * @param createdOn
          * @param sbuCode
          * @param actmgrName
          * @param entityName
          * @param partyName
          */
    public DplAuditValue (Long dplAuditNo, String tablename, String tablekey, String fieldType, String oldStatus, String newStatus, String otherReason, String createdBy, Date createdOn, String sbuCode, String actmgrName, String entityName, String partyName) {
	        this.dplAuditNo = dplAuditNo;
	        this.tablename = tablename;
	        this.tablekey = tablekey;
	        this.fieldType = fieldType;
	        this.oldStatus = oldStatus;
	        this.newStatus = newStatus;
	        this.otherReason = otherReason;
	        this.createdBy = createdBy;
	        this.createdOn = createdOn;
	        this.sbuCode = sbuCode;
	        this.actmgrName = actmgrName;
	        this.entityName = entityName;
	        this.partyName = partyName;
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
	 * @return tablename
	 */
	@Column(name = "TABLENAME")
		public String getTablename() {
		return tablename;
	}

	/**
	 * @param tablename new value for tablename
	 */
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}


	/**
	 * @return tablekey
	 */
	@Column(name = "TABLEKEY")
		public String getTablekey() {
		return tablekey;
	}

	/**
	 * @param tablekey new value for tablekey
	 */
	public void setTablekey(String tablekey) {
		this.tablekey = tablekey;
	}


	/**
	 * @return fieldType
	 */
	@Column(name = "FIELD_TYPE")
		public String getFieldType() {
		return fieldType;
	}

	/**
	 * @param fieldType new value for fieldType
	 */
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}


	/**
	 * @return oldStatus
	 */
	@Column(name = "OLD_STATUS")
		public String getOldStatus() {
		return oldStatus;
	}

	/**
	 * @param oldStatus new value for oldStatus
	 */
	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}


	/**
	 * @return newStatus
	 */
	@Column(name = "NEW_STATUS")
		public String getNewStatus() {
		return newStatus;
	}

	/**
	 * @param newStatus new value for newStatus
	 */
	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}


	/**
	 * @return otherReason
	 */
	@Column(name = "OTHER_REASON")
		public String getOtherReason() {
		return otherReason;
	}

	/**
	 * @param otherReason new value for otherReason
	 */
	public void setOtherReason(String otherReason) {
		this.otherReason = otherReason;
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
	 * @return entityName
	 */
	@Column(name = "entity_name")
		public String getEntityName() {
		return entityName;
	}

	/**
	 * @param entityName new value for entityName
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}


	/**
	 * @return partyName
	 */
	@Column(name = "PARTY_NAME")
		public String getPartyName() {
		return partyName;
	}

	/**
	 * @param partyName new value for partyName
	 */
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}


    /**
     * Returns a string representation of the value object.
     *
     * @return the string
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
	        stringBuffer.append("dplAuditNo: " + dplAuditNo);
	        stringBuffer.append("tablename: " + tablename);
	        stringBuffer.append("tablekey: " + tablekey);
	        stringBuffer.append("fieldType: " + fieldType);
	        stringBuffer.append("oldStatus: " + oldStatus);
	        stringBuffer.append("newStatus: " + newStatus);
	        stringBuffer.append("otherReason: " + otherReason);
	        stringBuffer.append("createdBy: " + createdBy);
	        stringBuffer.append("createdOn: " + createdOn);
	        stringBuffer.append("sbuCode: " + sbuCode);
	        stringBuffer.append("actmgrName: " + actmgrName);
	        stringBuffer.append("entityName: " + entityName);
	        stringBuffer.append("partyName: " + partyName);
	        return stringBuffer.toString();
    }
}
package com.gtn.dto;

import java.util.List;

import com.gtn.model.DplAuditValue;

public class DplAuditReasonView {

	private String code;
	private String otherReason;
	private List<String> reasonCodes;
	private String newStatus;
	private DplAuditValue auditValue;
	private List<DplReasonDto> reasons;
	private String entityType;
	private Integer shipmentId;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getOtherReason() {
		return otherReason;
	}
	public void setOtherReason(String otherReason) {
		this.otherReason = otherReason;
	}
	public List<String> getReasonCodes() {
		return reasonCodes;
	}
	public void setReasonCodes(List<String> reasonCodes) {
		this.reasonCodes = reasonCodes;
	}
	public String getNewStatus() {
		return newStatus;
	}
	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}
	public DplAuditValue getAuditValue() {
		return auditValue;
	}
	public void setAuditValue(DplAuditValue auditValue) {
		this.auditValue = auditValue;
	}
	public List<DplReasonDto> getReasons() {
		return reasons;
	}
	public void setReasons(List<DplReasonDto> reasons) {
		this.reasons = reasons;
	}
	public Integer getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(Integer shipmentId) {
		this.shipmentId = shipmentId;
	}
	

}

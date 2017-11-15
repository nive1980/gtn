package com.gtn.dto;

import java.util.Collection;

public class ShipmentScreeningResponse {
	
	private WlsScreeningResponse wlsResponse;
	private String oldStatus;
	private Collection<DplAuditDto> audits;
	
	public WlsScreeningResponse getWlsResponse() {
		return wlsResponse;
	}
	public void setWlsResponse(WlsScreeningResponse wlsResponse) {
		this.wlsResponse = wlsResponse;
	}
	public String getOldStatus() {
		return oldStatus;
	}
	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}
	public Collection<DplAuditDto> getAudits() {
		return audits;
	}
	public void setAudits(Collection<DplAuditDto> audits) {
		this.audits = audits;
	}
	
	

}

package com.gtn.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class WlsResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5528041468231494902L;
	private List<WlsScreeningResponse> screeningResponse;
	private List<ScreeningEntity> screeningInp;
	private boolean success;
	private String msg;
	private Map graphData;
	
	public List<WlsScreeningResponse> getScreeningResponse() {
		return screeningResponse;
	}
	public void setScreeningResponse(List<WlsScreeningResponse> screeningResponse) {
		this.screeningResponse = screeningResponse;
	}
	public List<ScreeningEntity> getScreeningInp() {
		return screeningInp;
	}
	public void setScreeningInp(List<ScreeningEntity> screeningInp) {
		this.screeningInp = screeningInp;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map getGraphData() {
		return graphData;
	}
	public void setGraphData(Map graphData) {
		this.graphData = graphData;
	}
	
}

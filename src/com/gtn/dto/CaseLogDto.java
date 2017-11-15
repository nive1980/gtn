package com.gtn.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CaseLogDto {
	private long ticketId;
	private String message;
	private String messageByName;
	private String messageOn;
	private String userId;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageByName() {
		return messageByName;
	}
	public void setMessageByName(String messageByName) {
		this.messageByName = messageByName;
	}
	public String getMessageOn() {
		return messageOn;
	}
	public void setMessageOn(Date messageOn) {
		SimpleDateFormat sdfr = new SimpleDateFormat("MM/dd/yyyy");
		this.messageOn = sdfr.format(messageOn);
	}
	public long getTicketId() {
		return ticketId;
	}
	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}

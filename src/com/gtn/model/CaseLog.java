package com.gtn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CASE_LOG")
public class CaseLog implements Model {

	private long id;
	private long ticketId;
	private String message;
	private long messageBy;
	private Date messageOn;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "TICKET_ID")
	public long getTicketId() {
		return ticketId;
	}
	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}
	@Column(name = "MESSAGE")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Column(name = "MESSAGE_BY")
	public long getMessageBy() {
		return messageBy;
	}
	public void setMessageBy(long messageBy) {
		this.messageBy = messageBy;
	}
	@Column(name = "MESSAGE_ON")
	public Date getMessageOn() {
		return messageOn;
	}
	public void setMessageOn(Date messageOn) {
		this.messageOn = messageOn;
	}
}

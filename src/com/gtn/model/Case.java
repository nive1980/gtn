package com.gtn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUPPORT_CASE")
public class Case implements Model {
	
	private long ticketId;
	private String description;
	private long category;
	private String priority;
	private long createdBy;
	private Date createdOn;
	private long assignedTo;
	private Date assignedOn;
	private String status;
	
	@Id
	@Column(name = "TICKET_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getTicketId() {
		return ticketId;
	}
	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "CATEGORY")
	public long getCategory() {
		return category;
	}
	public void setCategory(long category) {
		this.category = category;
	}
	@Column(name = "PRIORITY")
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	@Column(name = "CREATED_BY")
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name = "CREATED_ON")
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	@Column(name = "ASSIGNED_TO")
	public long getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(long assignedTo) {
		this.assignedTo = assignedTo;
	}
	@Column(name = "ASSIGNED_ON")
	public Date getAssignedOn() {
		return assignedOn;
	}
	public void setAssignedOn(Date assignedOn) {
		this.assignedOn = assignedOn;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

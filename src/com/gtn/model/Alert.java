package com.gtn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ALERTS")
public class Alert implements Model {

	private long id;
	private String description;
	private Date effStartDate;
	private Date effToDate;
	private String status;
	private Long userId;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "EFFECTIVE_FROM_DATE")
	public Date getEffStartDate() {
		return effStartDate;
	}
	public void setEffStartDate(Date effStartDate) {
		this.effStartDate = effStartDate;
	}
	@Column(name = "EFFECTIVE_TO_DATE")
	public Date getEffToDate() {
		return effToDate;
	}
	public void setEffToDate(Date effToDate) {
		this.effToDate = effToDate;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
}

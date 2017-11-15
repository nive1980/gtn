package com.gtn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(PublicationUserPk.class)
@Table(name = "PUBLICATION_USER")
public class PublicationUser implements Model {
	
	private long publicationId;
	private long userId;
	private Date createdOn;
	private Date modifiedOn;
	private String status;
	
	@Id
	@Column(name = "PUBLICATION_ID")
	public long getPublicationId() {
		return publicationId;
	}
	public void setPublicationId(long publicationId) {
		this.publicationId = publicationId;
	}
	
	@Id
	@Column(name = "USER_ID")
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	@Column(name = "CREATED_ON")
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@Column(name = "MODIFIED_ON")
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}

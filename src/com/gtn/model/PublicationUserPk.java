package com.gtn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class PublicationUserPk implements Serializable {

	private long publicationId;
	private long userId;
	

	@Column(name = "PUBLICATION_ID")
	public long getPublicationId() {
		return publicationId;
	}
	public void setPublicationId(long publicationId) {
		this.publicationId = publicationId;
	}
	

	@Column(name = "USER_ID")
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}

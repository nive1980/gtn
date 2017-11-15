package com.gtn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class SubscriptionUserPk implements Serializable {

	private long subscriptionId;
	private long userId;
	

	@Column(name = "SUBSCRIPTION_ID")
	public long getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	

	@Column(name = "USER_ID")
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}

package com.gtn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class SubscriptionProductPk implements Serializable {

	private long subscriptionId;
	private long productId;
	

	@Column(name = "SUBSCRIPTION_ID")
	public long getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	

	@Column(name = "PRODUCT_ID")
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
}

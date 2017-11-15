package com.gtn.model;



import java.util.Date;
import javax.persistence.GeneratedValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GenerationType;

import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "SUBSCRIPTION_TRANSACTION")
public class SubscriptionTransaction implements Model {

	private long subscriptionId;
	private long userBillingId;
	private long transactionId;
	private double amount;
	private Date createdOn;
	private String transactionStatus;
	
	//@Id
	@Column(name = "SUBSCRIPTION_ID")
	public long getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	
	@Column(name = "AMOUNT")
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Id
	@Column(name = "TRANSACTION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	//@Id
	@Column(name = "USER_BILLING_ID")
	public long getUserBillingId() {
		return userBillingId;
	}
	public void setUserBillingId(long userBillingId) {
		this.userBillingId = userBillingId;
	}
	
	@Column(name = "CREATED_ON")
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	
	
	@Column(name = "TRANSACTION_STATUS")
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
}

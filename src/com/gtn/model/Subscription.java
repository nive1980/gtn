package com.gtn.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUBSCRIPTION")
public class Subscription implements Model {

	private long subscriptionId;
	private BigDecimal totalCost;
	private String currencyCode;
	private long noOfUsers;
	private Long noOfChildUserCart;
	private long noOfCountries;
	private BigDecimal totalDiscount;
	private Date effFromDate;
	private Date effToDate;
	private Date createdOn;
	private Date modifiedOn;
	private String status;
	
	@Id
	@Column(name = "SUBSCRIPTION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	@Column(name = "TOTAL_COST")
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	@Column(name = "CURRENCY_CODE")
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	@Column(name = "NO_OF_USERS")
	public long getNoOfUsers() {
		return noOfUsers;
	}
	public void setNoOfUsers(long noOfUsers) {
		this.noOfUsers = noOfUsers;
	}
	@Column(name = "NO_OF_COUNTRIES")
	public long getNoOfCountries() {
		return noOfCountries;
	}
	public void setNoOfCountries(long noOfCountries) {
		this.noOfCountries = noOfCountries;
	}
	@Column(name = "TOTAL_DISCOUNT")
	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(BigDecimal totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	@Column(name = "EFFECTIVE_FROM_DATE")
	public Date getEffFromDate() {
		return effFromDate;
	}
	public void setEffFromDate(Date effFromDate) {
		this.effFromDate = effFromDate;
	}
	@Column(name = "EFFECTIVE_TO_DATE")
	public Date getEffToDate() {
		return effToDate;
	}
	public void setEffToDate(Date effToDate) {
		this.effToDate = effToDate;
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
	
	@Column(name = "NO_OF_CHILD_USERS_CART")
	public Long getNoOfChildUserCart() {
		return noOfChildUserCart;
	}
	public void setNoOfChildUserCart(Long noOfChildUserCart) {
		this.noOfChildUserCart = noOfChildUserCart;
	}
	
}

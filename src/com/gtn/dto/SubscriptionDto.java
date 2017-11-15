package com.gtn.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class SubscriptionDto implements Serializable{

	private long noOfCountries;
	private long noOfUsers;
	private BigDecimal totalCost;
	private String totalCurrencyCode;
	private String username;
	private String subscriptionId;
	private String isPaymentDone;
	private List<ProductDto> products;
	
	public String getIsPaymentDone() {
		return isPaymentDone;
	}
	public void setIsPaymentDone(String isPaymentDone) {
		this.isPaymentDone= isPaymentDone;
	}
	
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	public String getTotalCurrencyCode() {
		return totalCurrencyCode;
	}
	public void setTotalCurrencyCode(String totalCurrencyCode) {
		this.totalCurrencyCode = totalCurrencyCode;
	}
	
	public String getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public List<ProductDto> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
	public long getNoOfCountries() {
		return noOfCountries;
	}
	public void setNoOfCountries(long noOfCountries) {
		this.noOfCountries = noOfCountries;
	}
	public long getNoOfUsers() {
		return noOfUsers;
	}
	public void setNoOfUsers(long noOfUsers) {
		this.noOfUsers = noOfUsers;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}

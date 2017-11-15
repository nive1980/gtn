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
@Table(name = "SHOPPING_CART")
public class ShoppingCart implements Model {

	private long shoppingCartId;
	private BigDecimal totalCost;
	private String currencyCode;
	private String userName;
	private Date effFromDate;
	private Date effToDate;
	private Date createdOn;
	private Date modifiedOn;
	private String status;
	
	@Id
	@Column(name = "SHOPPING_CART_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	public long getShoppingCartId() {
		return shoppingCartId;
	}
	public void setShoppingCartId(long shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
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
	@Column(name = "USER")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	
	
}

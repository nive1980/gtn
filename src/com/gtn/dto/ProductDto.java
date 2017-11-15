package com.gtn.dto;

import java.math.BigDecimal;

public class ProductDto {
	private long productId;
	private String displayName;
	private String description;
	private long level;
	private String parentProduct;
	private BigDecimal cost;
	private String currencyCode;
	private String status;
	private BigDecimal discountPercentage;
	//TODO: This needs to be removed
	private int selectedCountry;
	private int selectedUser;
	
	private String labelText;
	private String addStatus;
	private Character active;
	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getLevel() {
		return level;
	}
	public void setLevel(long level) {
		this.level = level;
	}
	public String getParentProduct() {
		return parentProduct;
	}
	public void setParentProduct(String parentProduct) {
		this.parentProduct = parentProduct;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(BigDecimal discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public int getSelectedCountry() {
		return selectedCountry;
	}
	public void setSelectedCountry(int selectedCountry) {
		this.selectedCountry = selectedCountry;
	}
	public int getSelectedUser() {
		return selectedUser;
	}
	public void setSelectedUser(int selectedUser) {
		this.selectedUser = selectedUser;
	}
	public String getLabelText() {
		return labelText;
	}
	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}
	public String getAddStatus() {
		return addStatus;
	}
	public void setAddStatus(String addStatus) {
		this.addStatus = addStatus;
	}
	public Character getActive() {
		return active;
	}
	public void setActive(Character active) {
		this.active = active;
	}

	
}

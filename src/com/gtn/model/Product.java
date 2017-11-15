package com.gtn.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product implements Model {
	
	private long productId;
	private String displayName;
	private String description;
	private long level;
	private long parentProductId;
	private BigDecimal cost;
	private String currencyCode;
	private BigDecimal discountPercentage;
	private String status;
	private int itemPosition;
	private String itemUrl;
	private String faIcon;
	
	@Id
	@Column(name = "PRODUCT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	@Column(name = "DISPLAY_NAME")
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "LEVEL")
	public long getLevel() {
		return level;
	}
	public void setLevel(long level) {
		this.level = level;
	}
	@Column(name = "PARENT_PRODUCT_ID")
	public long getParentProductId() {
		return parentProductId;
	}
	public void setParentProductId(long parentProductId) {
		this.parentProductId = parentProductId;
	}
	@Column(name = "COST")
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	@Column(name = "CURRENCY_CODE")
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	@Column(name = "DISCOUNT_PERCENTAGE")
	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(BigDecimal discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "ITEM_POSITION")
	public int getItemPosition() {
		return itemPosition;
	}
	public void setItemPosition(int itemPosition) {
		this.itemPosition = itemPosition;
	}
	
	@Column(name="ITEM_URL")
	public String getItemUrl() {
		return itemUrl;
	}
	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}
	
	@Column(name="FA_ICON")
	public String getFaIcon() {
		return faIcon;
	}
	public void setFaIcon(String faIcon) {
		this.faIcon = faIcon;
	}

	@Override
	public boolean equals(Object o){
		
		if (o == this) {
            return true;
        }
		
		if (!(o instanceof Product)) {
            return false;
        }
		
		Product p = (Product) o;
		
		if(this.getProductId() == p.getProductId())
			return true;
		
		return false;
	}
}

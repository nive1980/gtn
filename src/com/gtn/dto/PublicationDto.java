package com.gtn.dto;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.Set;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
public  class PublicationDto {

	private long id;	// primary key
	private String title;
	private String author;
	private String publishDate;
	private String publicationType;
	private String description;
	private String pubUrl;
	private String currency;
	
	private boolean isUnlimitedAccess;
	private String buyNow;
	private boolean hasBeenIndividuallyPurchased;
	private boolean hasBeenMarkedForPurchase;
	public boolean isHasBeenMarkedForPurchase() {
		return hasBeenMarkedForPurchase;
	}
	public void setHasBeenMarkedForPurchase(boolean hasBeenMarkedForPurchase) {
		this.hasBeenMarkedForPurchase = hasBeenMarkedForPurchase;
	}
	public boolean getHasBeenIndividuallyPurchased() {
		return hasBeenIndividuallyPurchased;
	}
	public void setHasBeenIndividuallyPurchased(boolean hasBeenIndividuallyPurchased) {
		this.hasBeenIndividuallyPurchased = hasBeenIndividuallyPurchased;
	}

	public boolean isUnlimitedAccess() {
		return isUnlimitedAccess;
	}
	public void setUnlimitedAccess(boolean isUnlimitedAccess) {
		this.isUnlimitedAccess = isUnlimitedAccess;
	}
	public boolean isAnnotation() {
		return isAnnotation;
	}
	public void setAnnotation(boolean isAnnotation) {
		this.isAnnotation = isAnnotation;
	}
	private boolean isAnnotation;
	public String getPubUrl() {
		return pubUrl;
	}
	public void setPubUrl(String pubUrl) {
		this.pubUrl = pubUrl;
	}
	
	private String price;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublicationType() {
		return publicationType;
	}
	public void setPublicationType(String publicationType) {
		this.publicationType = publicationType;
	}
	public String getPublishDate() {
		return publicationType;
	}
	public void setPublishDate(Date newPublishDate) {
		publishDate = ""+newPublishDate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = ""+price;
	}
	public void setBuyNow(String buyNow) {
this.buyNow=""+buyNow;		
	}
	public String getBuyNow() {
		return ""+this.buyNow;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}

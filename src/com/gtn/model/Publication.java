package com.gtn.model;
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


@Entity
@Table(name = "PUBLICATION")
public  class Publication implements Model {

	private long id;	// primary key
	private String title;
	private String author;
	private Date publishDate;
	private String publicationType;
	private String description;
	private URL pubUrl;
	private String currency;
	private String type;
	
	@Column(name = "pub_url")
	public URL getPubUrl() {
		return pubUrl;
	}
	public void setPubUrl(URL pubUrl) {
		this.pubUrl = pubUrl;
	}
	private BigDecimal price;
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "AUTHOR")
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Column(name = "PUBLICATION_TYPE")
	public String getPublicationType() {
		return publicationType;
	}
	public void setPublicationType(String publicationType) {
		this.publicationType = publicationType;
	}
	@Column(name = "PUBLISH_DATE")
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date newPublishDate) {
		publishDate = newPublishDate;
	}
	@Column(name = "PRICE")
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Column(name = "currency_code")
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
}

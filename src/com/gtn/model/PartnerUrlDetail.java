package com.gtn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PARTNER_URL_DETAILS")
public class PartnerUrlDetail implements Model {
	
	private long id;
	private String partnerName;
	private String apiName;
	private String url;
	private String accountNumber;
	private String username;
	private String password;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	
	@Column(name = "PARTNER_NAME")
	public String getPartnerName() {
		return partnerName;
	}
	@Column(name = "API_NAME")
	public String getApiName() {
		return apiName;
	}
	@Column(name = "URL")
	public String getUrl() {
		return url;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "ACC_NO")
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}

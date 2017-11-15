package com.gtn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class ThemePk implements Serializable{

	private long userId;
	private String sbu;
	

	@Id
	@Column(name="USER_ID")
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	@Id
	@Column(name="SBU")
	public String getSbu() {
		return sbu;
	}
	public void setSbu(String sbu) {
		this.sbu = sbu;
	}
	
}

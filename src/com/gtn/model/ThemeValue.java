package com.gtn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(ThemePk.class)
@Table(name="THEMES")
public class ThemeValue implements Serializable, Model{

	private long userId;
	private String gtnClass;
	private String sbu;
	private String defaultClass;
	
	@Id
	@Column(name="USER_ID")
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	@Column(name="GTN_CLASS")
	public String getGtnClass() {
		return gtnClass;
	}
	public void setGtnClass(String gtnClass) {
		this.gtnClass = gtnClass;
	}
	
	@Id
	@Column(name="SBU")
	public String getSbu() {
		return sbu;
	}
	public void setSbu(String sbu) {
		this.sbu = sbu;
	}
	
	@Column(name="DEFAULT_CLASS")
	public String getDefaultClass() {
		return defaultClass;
	}
	public void setDefaultClass(String defaultClass) {
		this.defaultClass = defaultClass;
	}
	
	
	
}

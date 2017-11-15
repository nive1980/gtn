package com.gtn.model;
import javax.persistence.Lob;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import java.sql.Blob;
@Entity
@Table(name = "USER_GTN")
public class User implements Model, Serializable{

	private long id;
	private String title;
	private String firstName;
	private String lastName;
	private String emailId;
	private String phone;
	private String companyName;
	private byte[] image;
	private String password;
	private String resetPwdFlag;
	private String userType;
	private long parentUserId;
	private Date createdOn;
	private Date modifiedOn;
	private Date pwdChangedOn;
	private Date lastLoggedOn;
	private String status;
	private String sbu;
	
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
	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name = "IMAGE")
	//@Lob
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	@Column(name = "LAST_NAME")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name = "EMAIL_ID")
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "COMPANY_NAME")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "RESET_PASSWORD_FLAG")
	public String getResetPwdFlag() {
		return resetPwdFlag;
	}
	public void setResetPwdFlag(String resetPwdFlag) {
		this.resetPwdFlag = resetPwdFlag;
	}
	@Column(name = "USER_TYPE")
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Column(name = "PARENT_USER_ID")
	public long getParentUserId() {
		return parentUserId;
	}
	public void setParentUserId(long parentUserId) {
		this.parentUserId = parentUserId;
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
	@Column(name = "PWD_CHANGE_DATETIME")
	public Date getPwdChangedOn() {
		return pwdChangedOn;
	}
	public void setPwdChangedOn(Date pwdChangedOn) {
		this.pwdChangedOn = pwdChangedOn;
	}
	@Column(name = "LAST_LOGGED_IN")
	public Date getLastLoggedOn() {
		return lastLoggedOn;
	}
	public void setLastLoggedOn(Date lastLoggedOn) {
		this.lastLoggedOn = lastLoggedOn;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "SBU")
	public String getSbu() {
		return sbu;
	}
	public void setSbu(String sbu) {
		this.sbu = sbu;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}

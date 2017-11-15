package com.gtn.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class UserDto {

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String title;
	private String company;
	
	private String password;
	
	private String phone;
	
	private String otp;
	private String id;
	
	private String parentUserEmail;
	
	private long subscriptionId;
	
	public UserDto(){
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getParentUserEmail() {
		return parentUserEmail;
	}

	public void setParentUserEmail(String parentUserEmail) {
		this.parentUserEmail = parentUserEmail;
	}

	public long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}

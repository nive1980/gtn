package com.gtn.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="MESSAGE_HEADER")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"messageType", "userId", "password"})
public class WlsMessageHeader implements Serializable{

	@XmlElement(name="MESSAGE_TYPE")
	private String messageType;
	
	@XmlElement(name="USER_ID")
	private String userId;
	
	@XmlElement(name="PASSWORD")
	private String password;
	
	
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "WlsMessageHeader [messageType=" + messageType + ", userId=" + userId + ", password=" + password + "]";
	}
	
	
	
}

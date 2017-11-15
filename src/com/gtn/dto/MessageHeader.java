package com.gtn.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;



/**
 * @Project : EJBX_WEBSERVICES:MessageHeader.java
 * @DateTime: 12:44 PM 9/11/2015
 * @author Sunil Kumar
 */
@XmlRootElement(name = "MESSAGE_HEADER")
@XmlType(name = "", propOrder = { "userID", "password", "messageType" })
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class MessageHeader {
	@JsonProperty("USER_ID")
	private String userID;
	@JsonProperty("PASSWORD")
	private String password;
	@JsonProperty("MESSAGE_TYPE")
	private String messageType;

	public String getUserID() {
		return userID;
	}

	@XmlElement(name = "USER_ID")
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	@XmlElement(name = "PASSWORD")
	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessageType() {
		return messageType;
	}

	@XmlElement(name = "MESSAGE_TYPE")
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
}

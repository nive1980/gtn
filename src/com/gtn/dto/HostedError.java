package com.gtn.dto;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @Project : EJBX_WEBSERVICES:HostedError.java
 * @DateTime: 12:44 PM 9/11/2015
 * @author Sunil Kumar
 */
@XmlRootElement(name = "HOSTED_ERROR")
@XmlType(name = "", propOrder = { "errorTag" })
public class HostedError {

	private Collection<String> errorTag;

	public HostedError() {
		
	}

	public Collection<String> getErrorTag() {
		return errorTag;
	}

	@XmlElement(name = "ERROR_TAG")
	public void setErrorTag(Collection<String> errorTag) {
		this.errorTag = errorTag;
	}

}

package com.gtn.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;



/**
 * @Project : EJBX_WEBSERVICES:LicenseScreeningResponse.java
 * @DateTime: 2:22 PM 6/14/2017
 * @author Sunil Kumar
 */
@XmlRootElement(name = "PRODUCT_LS_RESPONSE")
@XmlType(name = "", propOrder = { "status", "messageHeader", "productLSDetails", "overallStatusData", "regDate", "overallStatus",
		"hostedError" })
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductLSResponse {
	@JsonProperty("STATUS")
	private String status;
	@JsonProperty("MESSAGE_HEADER")
	private MessageHeader messageHeader;
	@JsonProperty("PRODUCT_LS_DETAIL")
	private List<ProductLSDetail> productLSDetails;
	@JsonProperty("OVERALL_STATUS_DATA")
	private String overallStatusData;
	@JsonProperty("REG_DATE")
	private String regDate;
	@JsonProperty("OVERALL_STATUS")
	private String overallStatus;
	@JsonProperty("HOSTED_ERROR")
	private HostedError hostedError;

	public String getStatus() {
		return status;
	}

	@XmlElement(name = "STATUS")
	public void setStatus(String status) {
		this.status = status;
	}

	public MessageHeader getMessageHeader() {
		return messageHeader;
	}

	@XmlElement(name = "MESSAGE_HEADER")
	public void setMessageHeader(MessageHeader messageHeader) {
		this.messageHeader = messageHeader;
	}

	public List<ProductLSDetail> getProductLSDetails() {
		return productLSDetails;
	}

	@XmlElement(name = "PRODUCT_LS_DETAIL")
	public void setProductLSDetails(List<ProductLSDetail> productLSDetails) {
		this.productLSDetails = productLSDetails;
	}

	public String getOverallStatusData() {
		return overallStatusData;
	}

	@XmlElement(name = "OVERALL_STATUS_DATA")
	public void setOverallStatusData(String overallStatusData) {
		this.overallStatusData = overallStatusData;
	}

	public String getRegDate() {
		return regDate;
	}

	@XmlElement(name = "REG_DATE")
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getOverallStatus() {
		return overallStatus;
	}

	@XmlElement(name = "OVERALL_STATUS")
	public void setOverallStatus(String overallStatus) {
		this.overallStatus = overallStatus;
	}

	public HostedError getHostedError() {
		return hostedError;
	}

	@XmlElement(name = "HOSTED_ERROR")
	public void setHostedError(HostedError hostedError) {
		this.hostedError = hostedError;
	}

}

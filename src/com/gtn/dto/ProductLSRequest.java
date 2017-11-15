package com.gtn.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;




/**
 * @Project : EJBX_WEBSERVICES:ProductLSRequest.java
 * @DateTime: 2:22 PM 6/14/2017
 * @author Sunil Kumar
 */
@XmlRootElement(name = "PRODUCT_LS_REQUEST")
@XmlType(name = "", propOrder = { "messageHeader", "productLSCriteria" })
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductLSRequest {
	@JsonProperty("MESSAGE_HEADER")
	private MessageHeader messageHeader;
	@JsonProperty("PRODUCT_LS_CRITERIA")
	private ProductLSCriteria productLSCriteria;
	
	public ProductLSRequest(){}
	
	public ProductLSRequest(MessageHeader messageHeader, ProductLSCriteria productLSCriteria) {
		super();
		this.messageHeader = messageHeader;
		this.productLSCriteria = productLSCriteria;
	}

	public MessageHeader getMessageHeader() {
		return messageHeader;
	}

	@XmlElement(name = "MESSAGE_HEADER")
	public void setMessageHeader(MessageHeader messageHeader) {
		this.messageHeader = messageHeader;
	}

	public ProductLSCriteria getProductLSCriteria() {
		return productLSCriteria;
	}

	@XmlElement(name = "PRODUCT_LS_CRITERIA")
	public void setProductLSCriteria(ProductLSCriteria productLSCriteria) {
		this.productLSCriteria = productLSCriteria;
	}

}

package com.gtn.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="WLS_SCREENING_RESPONSE")
@XmlAccessorType(XmlAccessType.FIELD)
public class WlsScreeningResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2136650480544748873L;

	@XmlElement(name="STATUS")
	private String status;
	
	@XmlElement(name="MESSAGE_HEADER")
	private WlsMessageHeader msgHeader;
	
	@XmlElement(name="HITS_RETURNED")
	private HttsReturned httsReturned;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public WlsMessageHeader getMsgHeader() {
		return msgHeader;
	}

	public void setMsgHeader(WlsMessageHeader msgHeader) {
		this.msgHeader = msgHeader;
	}

	public HttsReturned getHttsReturned() {
		return httsReturned;
	}

	public void setHttsReturned(HttsReturned httsReturned) {
		this.httsReturned = httsReturned;
	}

	@Override
	public String toString() {
		return "WlsScreeningResponse [status=" + status + ", msgHeader=" + msgHeader + ", httsReturned=" + httsReturned
				+ "]";
	}
	
}

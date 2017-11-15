package com.gtn.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="WLS_SCREENING_REQUEST")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"msgHeader", "screeningInput"})
public class WlsScreeningRequest {
	
	@XmlElement(name="MESSAGE_HEADER")
	private WlsMessageHeader msgHeader;
	
	@XmlElement(name="WLS_INPUT_SCREENING")
	private WlsScreeningInput screeningInput;
	
	public WlsMessageHeader getMsgHeader() {
		return msgHeader;
	}
	public void setMsgHeader(WlsMessageHeader msgHeader) {
		this.msgHeader = msgHeader;
	}
	public WlsScreeningInput getScreeningInput() {
		return screeningInput;
	}
	public void setScreeningInput(WlsScreeningInput screeningInput) {
		this.screeningInput = screeningInput;
	}
	
}

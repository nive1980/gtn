package com.gtn.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="WLS_INPUT_SCREENING")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "country", "addr1", "addr2"})
public class WlsScreeningInput {

	@XmlElement(name="DPL_NAME")
	private String name;
	
	@XmlElement(name="COUNTRY")
	private String country;
	
	@XmlElement(name="DPL_ADDRESS1")
	private String addr1;
	
	@XmlElement(name="DPL_ADDRESS2")
	private String addr2;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	
}

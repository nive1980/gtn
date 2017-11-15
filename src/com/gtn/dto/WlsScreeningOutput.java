package com.gtn.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="WLS_OUTPUT_SCREENING")
@XmlAccessorType(XmlAccessType.FIELD)
public class WlsScreeningOutput implements Serializable{
	
	 @XmlElement(name="DPL_ID")
	 private String id;
	 
	 @XmlElement(name="DPL_NAME")
	 private String dpl_name;
	 
	 @XmlElement(name="CITY")
	 private String city;
	 
	 @XmlElement(name="COUNTRY")
	 private String country;
	 
	 @XmlElement(name="DPL_ADDRESS")
	 private String dpl_address;
	 
	 @XmlElement(name="DPL_AGENCY")
	 private String dpl_agency;
	 
	 @XmlElement(name="DPL_CATEGORY")
	 private String dpl_category;
	 
	 @XmlElement(name="DPL_COO_NAME")
	 private String dpl_coo_name;
	 
	 @XmlElement(name="DPL_EF_DATE")
	 private String dpl_eff_date;
	 
	 @XmlElement(name="DPL_EX_DATE")
	 private String dpl_ex_date;
	 
	 @XmlElement(name="DPL_FR_DATE")
	 private String dpl_fr_date;
	 
	 @XmlElement(name="DPL_PRIVLG")
	 private String dpl_privilage;
	 
	 @XmlElement(name="DPL_SCORE")
	 private String dpl_score;
	 
	 @XmlElement(name="DPL_CITATN")
	 private String dpl_citant;
	 
	 @XmlElement(name="DPL_SEE")
	 private String dpl_see;
	 
	 @XmlElement(name="DPL_TYPE")
	 private String dpl_type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDpl_name() {
		return dpl_name;
	}

	public void setDpl_name(String dpl_name) {
		this.dpl_name = dpl_name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDpl_address() {
		return dpl_address;
	}

	public void setDpl_address(String dpl_address) {
		this.dpl_address = dpl_address;
	}

	public String getDpl_agency() {
		return dpl_agency;
	}

	public void setDpl_agency(String dpl_agency) {
		this.dpl_agency = dpl_agency;
	}

	public String getDpl_category() {
		return dpl_category;
	}

	public void setDpl_category(String dpl_category) {
		this.dpl_category = dpl_category;
	}

	public String getDpl_coo_name() {
		return dpl_coo_name;
	}

	public void setDpl_coo_name(String dpl_coo_name) {
		this.dpl_coo_name = dpl_coo_name;
	}

	public String getDpl_eff_date() {
		return dpl_eff_date;
	}

	public void setDpl_eff_date(String dpl_eff_date) {
		this.dpl_eff_date = dpl_eff_date;
	}

	public String getDpl_ex_date() {
		return dpl_ex_date;
	}

	public void setDpl_ex_date(String dpl_ex_date) {
		this.dpl_ex_date = dpl_ex_date;
	}

	public String getDpl_fr_date() {
		return dpl_fr_date;
	}

	public void setDpl_fr_date(String dpl_fr_date) {
		this.dpl_fr_date = dpl_fr_date;
	}

	public String getDpl_privilage() {
		return dpl_privilage;
	}

	public void setDpl_privilage(String dpl_privilage) {
		this.dpl_privilage = dpl_privilage;
	}

	public String getDpl_score() {
		return dpl_score;
	}

	public void setDpl_score(String dpl_score) {
		this.dpl_score = dpl_score;
	}

	public String getDpl_citant() {
		return dpl_citant;
	}

	public void setDpl_citant(String dpl_citant) {
		this.dpl_citant = dpl_citant;
	}

	public String getDpl_see() {
		return dpl_see;
	}

	public void setDpl_see(String dpl_see) {
		this.dpl_see = dpl_see;
	}

	public String getDpl_type() {
		return dpl_type;
	}

	public void setDpl_type(String dpl_type) {
		this.dpl_type = dpl_type;
	}

	@Override
	public String toString() {
		return "WlsScreeningOutput [id=" + id + ", dpl_name=" + dpl_name + ", city=" + city + ", country=" + country
				+ ", dpl_address=" + dpl_address + ", dpl_agency=" + dpl_agency + ", dpl_category=" + dpl_category
				+ ", dpl_coo_name=" + dpl_coo_name + ", dpl_eff_date=" + dpl_eff_date + ", dpl_ex_date=" + dpl_ex_date
				+ ", dpl_fr_date=" + dpl_fr_date + ", dpl_privilage=" + dpl_privilage + ", dpl_score=" + dpl_score
				+ ", dpl_citant=" + dpl_citant + ", dpl_see=" + dpl_see + ", dpl_type=" + dpl_type + "]";
	}
}

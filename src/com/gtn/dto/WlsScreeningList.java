package com.gtn.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="WLS_OUTPUT_SCREENINGS")
@XmlAccessorType(XmlAccessType.FIELD)
public class WlsScreeningList implements Serializable{
	
	@XmlElement(name="WLS_OUTPUT_SCREENING")
	private List<WlsScreeningOutput> wlsScreenings;

	public List<WlsScreeningOutput> getWlsScreenings() {
		return wlsScreenings;
	}

	public void setWlsScreenings(List<WlsScreeningOutput> wlsScreenings) {
		this.wlsScreenings = wlsScreenings;
	}

	@Override
	public String toString() {
		return "WlsScreeningList [wlsScreenings=" + wlsScreenings + "]";
	}
}

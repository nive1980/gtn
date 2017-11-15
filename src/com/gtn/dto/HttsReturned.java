package com.gtn.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name="HITS_RETURNED")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({WlsScreeningOutput.class})
public class HttsReturned implements Serializable{

	@XmlElement(name="TOTAL_NUMBER_OF_HITS")
	private Integer hits;
	
	@XmlElement(name="WLS_OUTPUT_SCREENINGS")
	private WlsScreeningList wlsList;

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public WlsScreeningList getWlsList() {
		return wlsList;
	}

	public void setWlsList(WlsScreeningList wlsList) {
		this.wlsList = wlsList;
	}

	@Override
	public String toString() {
		return "HttsReturned [hits=" + hits + ", wlsList=" + wlsList + "]";
	}

}

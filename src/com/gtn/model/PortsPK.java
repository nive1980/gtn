package com.gtn.model;

import java.io.Serializable;
import javax.persistence.Column;

public class PortsPK implements Serializable {

	/**
	 * Attribute portId.
	 */
	private String portId;
	
	/**
	 * Added for EASE-EU
	 * Attribute country.
	 */
	private String country;


	/**
	 * Instantiates a new PortsPK.
	 */
	public PortsPK() {
	}

	/**
	 * Instantiates a new PortsPK.
	 *
	 * @param portId
	 * @param country
	 *
	 */
	public PortsPK (String portId, String country) {
		this.portId = portId;
		this.country = country;
	}

	
	/**
	 * @return the portId
	 */
	@Column(name = "PORT_ID")
	public String getPortId() {
		return portId;
	}

	/**
	 * @param portId the portId to set
	 */
	public void setPortId(String portId) {
		this.portId = portId;
	}

	/**
	 * @return country
	 */
	
	@Column(name = "COUNTRY")
	public String getCountry() {
		return country;
	}

	/**
	 * @param country new value for country
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/**
	 * calculate hashcode
	 */
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

	/**
	 * equals method
	 */
	@Override
	public boolean equals(Object object)
	{
		return super.equals(object);
	}


}
package com.gtn.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@IdClass(PortsPK.class)
@Table(name = "PORTS")
public class PortsValue implements Serializable, Model {

	/**
	 * Attribute portId.
	 */
	private String portId;

	/**
	 * Attribute portCode.
	 */
	private String portCode;

	/**
	 * Attribute portCountry.
	 */
	private String portCountry;

	/**
	 * Added for EASE-EU Attribute country.
	 */
	private String country;

	/**
	 * Attribute description.
	 */
	private String description;

	/**
	 * Attribute status.
	 */
	private String status;

	/**
	 * Attribute createdBy.
	 */
	private String createdBy;

	/**
	 * Attribute editedBy.
	 */
	private String modifiedBy;

	/**
	 * Attribute createdOnDate.
	 */
	private Date createdOnDate;

	/**
	 * Attribute editedOnDate.
	 */
	private Date modifiedOnDate;

	/**
	 * Attribute splOperationCode.
	 */
	private String splOperationCode;

	/**
	 * Instantiates a new PortsValue.
	 */
	public PortsValue() {
	}

	/**
	 * Instantiates a new PortsValue.
	 * 
	 * @param portId
	 * @param portCode
	 * @param portCountry
	 * @param country
	 * @param description
	 * @param status
	 * @param createdBy
	 * @param modifiedBy
	 * @param createdOnDate
	 * @param modifiedOnDate
	 * @param splOperationCode
	 */
	public PortsValue(String portId, String portCode, String portCountry,
			String country, String description, String status,
			String createdBy, String modifiedBy, Date createdOnDate,
			Date modifiedOnDate, String splOperationCode) {
		this.portId = portId;
		this.portCode = portCode;
		this.portCountry = portCountry;
		this.country = country;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdOnDate = createdOnDate;
		this.modifiedOnDate = modifiedOnDate;
		this.splOperationCode = splOperationCode;
	}

	/**
	 * @return the splOperationCode
	 */
	@Column(name = "SPECIAL_OPERATIONS")
	public String getSplOperationCode() {
		return splOperationCode;
	}

	/**
	 * @param splOperationCode
	 *            the splOperationCode to set
	 */
	public void setSplOperationCode(String splOperationCode) {
		this.splOperationCode = splOperationCode;
	}

	/**
	 * @return the portId
	 */
	@Id
	@Column(name = "PORT_ID")
	public String getPortId() {
		return portId;
	}

	/**
	 * @param portId
	 *            the portId to set
	 */
	public void setPortId(String portId) {
		this.portId = portId;
	}

	/**
	 * @return portCode
	 */
	@Column(name = "PORT_CODE")
	public String getPortCode() {
		return portCode;
	}

	/**
	 * @param portCode
	 *            new value for portCode
	 */
	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}

	/**
	 * @return portCountry
	 */
	@Column(name = "PORT_COUNTRY")
	public String getPortCountry() {
		return portCountry;
	}

	/**
	 * @param portCountry
	 *            new value for portCountry
	 */
	public void setPortCountry(String portCountry) {
		this.portCountry = portCountry;
	}

	/**
	 * @return country
	 */
	@Id
	@Column(name = "COUNTRY")
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            new value for country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return description
	 */
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            new value for description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return status
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            new value for status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return createdBy
	 */
	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            new value for createdBy
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifiedBy
	 */
	@Column(name = "MODIFIED_BY")
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return createdOnDate
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON")
	public Date getCreatedOnDate() {
		return createdOnDate;
	}

	/**
	 * @param createdOnDate
	 *            new value for createdOnDate
	 */
	public void setCreatedOnDate(Date createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	/**
	 * @return the modifiedOnDate
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_ON")
	public Date getModifiedOnDate() {
		return modifiedOnDate;
	}

	/**
	 * @param modifiedOnDate
	 *            the modifiedOnDate to set
	 */
	public void setModifiedOnDate(Date modifiedOnDate) {
		this.modifiedOnDate = modifiedOnDate;
	}

	/**
	 * Returns a string representation of the value object.
	 * 
	 * @return the string
	 */
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("portId: " + portId);
		stringBuffer.append("portCode: " + portCode);
		stringBuffer.append("portCountry: " + portCountry);
		stringBuffer.append("country: " + country);
		stringBuffer.append("description: " + description);
		stringBuffer.append("status: " + status);
		stringBuffer.append("createdBy: " + createdBy);
		stringBuffer.append("modifiedBy: " + modifiedBy);
		stringBuffer.append("createdOnDate: " + createdOnDate);
		stringBuffer.append("modifiedOnDate: " + modifiedOnDate);
		stringBuffer.append("splOperationCode: " + splOperationCode);
		return stringBuffer.toString();
	}

}

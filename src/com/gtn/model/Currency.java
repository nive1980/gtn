/*package com.gtn.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="currency")
@Access(AccessType.PROPERTY)
public class Currency {
	
	private String currencyCode;
	private String description;
	private String baseCurrencyCode;
	private String unitValue;
	
	@Id
	@Column(name = "CURRENCY_CODE", unique = true)
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "BASE_CURRENCY_CODE")
	public String getBaseCurrencyCode() {
		return baseCurrencyCode;
	}
	public void setBaseCurrencyCode(String baseCurrencyCode) {
		this.baseCurrencyCode = baseCurrencyCode;
	}
	
	@Column(name = "UNIT_VALUE")
	public String getUnitValue() {
		return unitValue;
	}
	public void setUnitValue(String unitValue) {
		this.unitValue = unitValue;
	}
	
	
}
*/
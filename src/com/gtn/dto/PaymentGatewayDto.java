package com.gtn.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PaymentGatewayDto {

	private String billingname;
	private long id;
	private String ccNumber;
	
	private String expiry;
	private String addNew;
	private String from;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getAddNew() {
		return addNew;
	}
	public void setAddNew(String addNew) {
		this.addNew = addNew;
	}

	private String stripeToken;
	private String add_1;
	private String add_2;
	private String card_city;
	private String card_state;
	private String card_zip;
	private String card_country;
	private String amount;
	private String firstName;
	private String  primary;
	private String  userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	private String  subscriptionId;
	
	
	public PaymentGatewayDto(){
	}
	public String getAdd_1() {
		return add_1;
	}
	public String getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setAdd_1(String add_1) {
		this.add_1 = add_1;
	}
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAdd_2() {
		return add_1;
	}
	public String getPrimary() {
		return primary;
	}
	public void setPrimary(String primary) {
		this.primary = primary;
	}
	
	public void setAdd_2(String add_2) {
		this.add_2 = add_2;
	}
		public void setCard_city(String card_city) {
		this.card_city = card_city;
	}
	public String getCard_city() {
		return card_city;
	}

	public void setCard_state(String card_state) {
		this.card_state = card_state;
	}
	public String getCard_state() {
		return card_state;
	}

	public void setCard_zip(String card_zip) {
		this.card_zip = card_zip;
	}
	public String getCard_zip() {
		return card_zip;
	}

	public String getCard_country() {
		return card_country;
	}

	public void setCard_country(String card_country) {
		this.card_country = card_country;
	}

	public String getBillingname() {
		return billingname;
	}

	public void setBillingname(String billingname) {
		this.billingname = billingname;
	}
	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public String getStripeToken() {
		return stripeToken;
	}

	public void setStripeToken(String stripeToken) {
		this.stripeToken = stripeToken;
	}

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}

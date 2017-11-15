package com.gtn.service;

import java.util.Collection;

import com.gtn.exception.ApplicationException;
import com.gtn.model.UserBilling;

public interface BillingService {
	public Collection<UserBilling> getBillingDetails(String userName) throws ApplicationException;
	public int deletePaymentMethod(String ccNumber,String userId) throws ApplicationException;
	public Collection<UserBilling> getExpiredBillingDetails() throws ApplicationException;
	public UserBilling getPrimaryBillingDetail(String email) throws ApplicationException;
}

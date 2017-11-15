package com.gtn.service;

import com.gtn.dto.PaymentGatewayDto;
import com.gtn.dto.UserBillingDto;
import com.gtn.exception.ApplicationException;
import com.gtn.model.User;
import com.gtn.model.UserBilling;


public interface PaymentGatewayService {

	
	public UserBilling getUserBilling(String cardNo) throws ApplicationException;
	public void saveCardDetails(PaymentGatewayDto userBilling,String subScriptionId,String amount, boolean paymentFlag) throws ApplicationException;
	public boolean chargeCard(PaymentGatewayDto paymentGatewayDto) throws ApplicationException;
	public void changePrimary(PaymentGatewayDto dto);
}

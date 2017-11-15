package com.gtn.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gtn.dto.PaymentGatewayDto;
import com.gtn.dto.ProductDto;
import com.gtn.dto.UserBillingDto;
import com.gtn.dto.UserDto;
import com.gtn.exception.ApplicationException;
import com.gtn.model.Country;
import com.gtn.model.Product;
import com.gtn.model.RestResponse;
import com.gtn.model.UserBilling;
import com.gtn.service.BillingService;
import com.gtn.service.PaymentGatewayService;
import com.gtn.util.CardType;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
public class BillingController {
	@Autowired
	private BillingService billingService;
	@Autowired
	private GtnControllerHelper gtnControllerHelper;
	private static final Logger logger = LoggerFactory
			.getLogger(BillingController.class);

	@RequestMapping(value="/getBillingDetails",method = RequestMethod.POST)
	public List<UserBillingDto> getBillingDetails(@RequestBody UserDto usersDto) throws Exception {
		logger.info("username is ",usersDto);
		Collection<UserBilling> billings= billingService.getBillingDetails(usersDto.getEmail());
		ArrayList<UserBillingDto> userBillingDtos= new ArrayList<UserBillingDto>();
		
		if (billings != null && !billings.isEmpty()) {
			for (UserBilling billing: billings) {
				
				UserBillingDto userbillingDto =gtnControllerHelper.prepareBillingDto(billing);
				
				String method = "<img style='width: 25px' src='img/"+(CardType.detect(""+userbillingDto.getCardNo())).toString()+".png'  /> &nbsp;&nbsp;"+billing.getCardNo();
				String billToAddr = userbillingDto.getAdd_1()+", " +userbillingDto.getAdd_2();
				
				userbillingDto.setMethod(method);
				userbillingDto.setBillTo(billToAddr);
				userbillingDto.setDelete(userbillingDto.getId()+"");
				userbillingDto.setEdit(userbillingDto.getId()+"");
				
				userBillingDtos.add(userbillingDto);
				
			}}
		logger.info("size is ",billings.size());
		
		return userBillingDtos;
			
	}
	@RequestMapping(value="/getPrimaryBillingDetail",method = RequestMethod.POST)
	public UserBillingDto getPrimaryBillingDetail(@RequestBody UserDto usersDto) throws Exception {
		logger.info("username is ",usersDto);
		UserBilling billing= billingService.getPrimaryBillingDetail(usersDto.getEmail());
				
		UserBillingDto userbillingDto =gtnControllerHelper.prepareBillingDto(billing);
		
		return userbillingDto;
			
	}
	@RequestMapping(value="/deletePaymentMethod",method = RequestMethod.POST)
	public ResponseEntity<RestResponse> deletePaymentMethod(@RequestBody PaymentGatewayDto paymentGatewayDto) throws Exception {
	int deletedNos =billingService.deletePaymentMethod(paymentGatewayDto.getCcNumber(),paymentGatewayDto.getUserId());
	HashMap<String, String> response = new HashMap<String,String>();
	 HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
   	ResponseEntity<RestResponse> result = null;
	RestResponse success = new RestResponse(true,"Successful",paymentGatewayDto);
    headers.set ("SUCCESS_MESSAGE","Successful");
	response.put("Status", "Success");
	result =new ResponseEntity<>(success,headers,HttpStatus.OK);

		return result;
			
	}
	
	

}

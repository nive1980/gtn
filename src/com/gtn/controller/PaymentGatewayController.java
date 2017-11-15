package com.gtn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtn.dto.PaymentGatewayDto;
import com.gtn.exception.ApplicationException;
import com.gtn.model.Country;
import com.gtn.model.RestResponse;
import com.gtn.model.UserBilling;
import com.gtn.service.CountryService;
import com.gtn.service.PaymentGatewayService;
import com.gtn.controller.GtnControllerHelper;
/**
 * REST layer for managing people.
 * 
 * @author Adapted from
 *         http://codetutr.com/2013/04/09/spring-mvc-easy-rest-based-json-services-with-responsebody/
 */
@RestController
public class PaymentGatewayController {

	@Autowired
	private PaymentGatewayService paymentGateway;
	@Autowired
	private GtnControllerHelper gtnControllerHelper;

	@Autowired
	private CountryService countryService;

	/**
	 * @param id
	 * @return Returns the person with the given id.
	 */
	@RequestMapping(value="/chargeCard",produces = "application/json",method = RequestMethod.POST)
	 @ResponseBody
	public ResponseEntity<RestResponse> chargeCard(@RequestBody PaymentGatewayDto paymentGatewayDto) throws ApplicationException{
		ResponseEntity<RestResponse> result = null;
		System.out.println("Paymentgatewaycontroller chargeCard--- " + paymentGatewayDto);
		HashMap<String, String> response = new HashMap<String,String>();
		 HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	    
	//	if(isCardCharged) {
		//	UserBilling billing =gtnControllerHelper.parseBillingInfo(paymentGatewayDto);
	        
	        boolean paymentFlag = false;
	        if(paymentGatewayDto.getAmount()!=null && !paymentGatewayDto.getAmount().equals("0")) {
	        	paymentFlag = true;
	        }
	        
			paymentGateway.saveCardDetails(paymentGatewayDto,paymentGatewayDto.getSubscriptionId(),paymentGatewayDto.getAmount(), paymentFlag);
			if(paymentGatewayDto.getAmount()!=null && !paymentGatewayDto.getAmount().equals("0")) {
				boolean isCardCharged=paymentGateway.chargeCard(paymentGatewayDto);
				System.out.println("ischargecreated "+isCardCharged);						
			}
			RestResponse success = new RestResponse(true,"Successful",paymentGatewayDto);
			    headers.set ("SUCCESS_MESSAGE","Successful");
		    	response.put("Status", "Success");
		    	result =new ResponseEntity<>(success,headers,HttpStatus.OK);
			
		//	}
	return result;
	//	return  new ResponseEntity<>("Error while charging card", new HttpHeaders(),HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping(value="/makeCardPrimary",method = RequestMethod.POST)
	public ResponseEntity<RestResponse> makeCardPrimary(@RequestBody PaymentGatewayDto dto) throws ApplicationException {
		paymentGateway.changePrimary(dto);
		RestResponse restResponse = new RestResponse(true,"Successful",dto);
		return new ResponseEntity<>(restResponse,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getCountries",method = RequestMethod.POST)
	public List<Country> getCountries() throws ApplicationException {
		Collection<Country> countries = countryService.getCountries();
		List list = new ArrayList();
		list.addAll(countries);
		return list;
			
	}
	@RequestMapping(value="/getUserBilling",method = RequestMethod.POST)
	public ResponseEntity<RestResponse> getUserBilling(String cardNo) throws ApplicationException {
		UserBilling userBilling=null;
		HashMap<String, Object> responseData = new HashMap<String, Object>();
		ResponseEntity<RestResponse> result = null;
		try{
			userBilling = paymentGateway.getUserBilling(cardNo);
		}
		catch(ApplicationException exc) {
			System.out.println("billing not found for card number");
			
		}
		if(userBilling!=null) {
			responseData.put("Status", "Fail");
			responseData.put("Message", "Payment method already exists for this Card Number");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestResponse success = new RestResponse(true, "Successful", responseData);
			result = new ResponseEntity<>(success, headers, HttpStatus.OK);
	
		} 
		else {
			responseData.put("Status", "Success");
			responseData.put("Message", "Payment method is new");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestResponse success = new RestResponse(true, "Successful", responseData);
			result = new ResponseEntity<>(success, headers, HttpStatus.OK);
	
		}
		return result;	
	}		
	}

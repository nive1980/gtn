package com.gtn.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gtn.dao.GenericDao;
import com.gtn.dto.PaymentGatewayDto;
import com.gtn.exception.ApplicationException;
import com.gtn.model.Country;
import com.gtn.model.SubscriptionProduct;
import com.gtn.model.SubscriptionTransaction;
import com.gtn.model.UserBilling;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PaymentGatewayServiceImpl implements PaymentGatewayService {
	@Autowired
	private GenericDao dao;

	public GenericDao getDao() {
		return dao;
	}

	public void setDao(GenericDao dao) {
		this.dao = dao;
	}

	@Override
	public UserBilling getUserBilling(String cardNo) throws ApplicationException {
		int count= 0;
		try{
			Long[] cardnos = new Long[1];
			cardnos[0]= Long.parseLong(cardNo);
			List<UserBilling> userBillingList= (List<UserBilling>)dao.findDynamicQuery("select ub from UserBilling ub where ub.cardNo=?",cardnos);
			if(userBillingList.size()==0)
					return null;
			UserBilling billing =(UserBilling)userBillingList.get(0);
			System.out.println("billing is "+billing);
				return billing;
				
		} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("Billing Not found ", e);
			}
	//	return null;
	}
	public boolean chargeCard(PaymentGatewayDto paymentGatewayDto) throws ApplicationException {
	    boolean isCardCharged=false;
	    try {
			Stripe.apiKey = "sk_test_ZcuiC1cuuwmGKNGA7K54D1Fy";
			String token = paymentGatewayDto.getStripeToken();
		/*	Map<String, Object> customerParams = new HashMap<String, Object>();
	//		customerParams.put("name", paymentGatewayDto.getBillingname());
			customerParams.put("source", token);
			Customer customer = Customer.create(customerParams);
			System.out.println("customer is "+customer);
		*/	Map<String, Object> chargeParams = new HashMap<String, Object>();
			chargeParams.put("amount", paymentGatewayDto.getAmount());
			chargeParams.put("currency", "usd");
			//chargeParams.put("customer", customer.getId());
			chargeParams.put("source", token);
			Charge charge = Charge.create(chargeParams);
			System.out.println("charge is "+charge);
			isCardCharged=true;
	    }
	    catch(AuthenticationException e)
	    {
	    	e.printStackTrace();
	    	throw new ApplicationException(e.getMessage(),e);
	    }
	    catch(InvalidRequestException e)
	    {
	    	e.printStackTrace();
	    	throw new ApplicationException(e.getMessage(),e);
	    	
	    }
	    catch(APIException e)
	    {
	    	e.printStackTrace();
	    	throw new ApplicationException(e.getMessage(),e);
	    	
	    }

	    catch(APIConnectionException e)
	    {
	    	e.printStackTrace();
	    	throw new ApplicationException(e.getMessage(),e);
	    	
	    }
	    catch(CardException e)
	    {
	    	e.printStackTrace();
	    	throw new ApplicationException(e.getMessage(),e);
	    	
	    }
	   
	    return isCardCharged;

	}


	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveCardDetails(PaymentGatewayDto dto,String subScriptionId,String amount, boolean paymentFlag) throws ApplicationException {
		EntityTransaction txn = null;
		boolean isNew=false;
		if(dto.getFrom().equals("addPayment")) {
			UserBilling userBilling=null;
			try{
				userBilling = getUserBilling(dto.getCcNumber());
			}
			catch(ApplicationException exc) {
				System.out.println("billing not found for card number");
				
			}	
			if(userBilling!=null) {
				throw new ApplicationException("Payment method already exists for this credit card number", new Exception());
			}
			else 
			{
				System.out.println("Credit card number is new ,so go ahead");
			}
		}
		if(dto.getAddNew()!=null && dto.getAddNew().equals("TRUE"))
			isNew=true;
		try {
		/*	System.out.println("in saveCardDetails "+dto);
			String queryString = "update User_Billing ub set ub.is_primary='N' where ub.user_Id= ?";
			Object[] params = { dto.getUserId() };
			int result = dao.updateNativeQuery(queryString, params);
			System.out.println("result is "+result);	
		*/	UserBilling userBillingInfo =getUserBilling(""+dto.getCcNumber());
		if(dto.getPrimary().equals("Y")) {
			makeOtherCardsNonPrimary(dto.getUserId());
		}
	
			if(userBillingInfo ==null || isNew)
			{

				System.out.println("user billing is new ");
				userBillingInfo = new UserBilling();
				userBillingInfo.setFirstName(dto.getBillingname());
				userBillingInfo.setCardNo(new Long(dto.getCcNumber()));
				userBillingInfo.setUserId(dto.getUserId());
				userBillingInfo.setAdd_1(dto.getAdd_1());
				userBillingInfo.setAdd_2(dto.getAdd_2());
				userBillingInfo.setCity(dto.getCard_city());
				userBillingInfo.setState(dto.getCard_state());
				userBillingInfo.setCountry_code(dto.getCard_country());
				userBillingInfo.setPrimary(dto.getPrimary());
				 DateFormat dateFormat= new SimpleDateFormat("MM/yy");
				System.out.println("card expiry "+dto.getExpiry());
				userBillingInfo.setExp_date((Date)(dateFormat.parse(dto.getExpiry())));
				userBillingInfo.setZipcode(Integer.parseInt(dto.getCard_zip()));
		
				dao.create(userBillingInfo);
			}
			else {
				System.out.println("user billing needs to be updated");

				userBillingInfo.setFirstName(dto.getBillingname());
				userBillingInfo.setUserId(dto.getUserId());
				userBillingInfo.setAdd_1(dto.getAdd_1());
				userBillingInfo.setAdd_2(dto.getAdd_2());
				userBillingInfo.setCity(dto.getCard_city());
				userBillingInfo.setCardNo(new Long(dto.getCcNumber()));
				userBillingInfo.setAdd_1(dto.getAdd_1());
				userBillingInfo.setAdd_2(dto.getAdd_2());
				userBillingInfo.setCity(dto.getCard_city());
				userBillingInfo.setState(dto.getCard_state());
				userBillingInfo.setCountry_code(dto.getCard_country());
				userBillingInfo.setPrimary(dto.getPrimary());
				 DateFormat dateFormat= new SimpleDateFormat("MM/yy");
					System.out.println("card expiry "+dto.getExpiry());
					userBillingInfo.setExp_date((Date)(dateFormat.parse(dto.getExpiry())));
					userBillingInfo.setZipcode(Integer.parseInt(dto.getCard_zip()));
				
				dao.update(userBillingInfo);
			}
			if(subScriptionId!=null) {
				SubscriptionTransaction subTransaction = new SubscriptionTransaction();
			boolean hasSubId=false;
				try {
					Long.parseLong(subScriptionId);
					hasSubId=true;
				}
				catch(Exception w){
					
				}
				if(hasSubId)
					subTransaction.setSubscriptionId(Long.parseLong(subScriptionId));
				
				
				subTransaction.setAmount(Double.parseDouble(amount)/100);
				subTransaction.setUserBillingId(userBillingInfo.getId());
				subTransaction.setTransactionStatus("COMPLETED");
				subTransaction.	setCreatedOn(new Date());
				
				//changed by naveen - 29-July-17
				if(paymentFlag){
					Collection<SubscriptionProduct> subProducts = updateProductPaymentStatus(Long.parseLong(subScriptionId));
					for(SubscriptionProduct prod: subProducts){
						prod.setStatus("A");
						dao.update(prod);
					}
				}
				
				dao.create(subTransaction);
			}
		} catch (Exception e) {
			System.out.println("error is "+e.getMessage());
			throw new ApplicationException("Error occured while saving card", e);
		}
	
	}
	
	private void makeOtherCardsNonPrimary(String userId) {
		String queryString = "Select Object(p) from UserBilling p where userId = ?1";
		Object[] params = { userId};
		Collection<UserBilling> currentPri = dao.findDynamicQuery(queryString, params);
		
		if(currentPri.size() > 0){
			for(UserBilling billing: currentPri){
				billing.setPrimary("N");
				dao.update(billing);
			}
		}
		
			}

	@Override
	public void changePrimary(PaymentGatewayDto dto){
		String queryString = "Select Object(p) from UserBilling p where userId = ?1 and primary = ?2";
		Object[] params = { dto.getUserId(), "Y" };
		Collection<UserBilling> currentPri = dao.findDynamicQuery(queryString, params);
		
		if(currentPri.size() > 0){
			for(UserBilling billing: currentPri){
				billing.setPrimary("N");
				dao.update(billing);
			}
		}
		
		UserBilling setPri = (UserBilling) dao.read(UserBilling.class, dto.getId());
		setPri.setPrimary("Y");
		dao.update(setPri);
	}
	
	private Collection<SubscriptionProduct> updateProductPaymentStatus(long subscriptionId){
		String queryString = "select Object(p) from SubscriptionProduct p where subscriptionId = ?1";
		Object[] params = { subscriptionId };
		Collection<SubscriptionProduct> subProducts = dao.findDynamicQuery(queryString, params);
		return subProducts;
	}
	
	
}

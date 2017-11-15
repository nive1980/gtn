package com.gtn.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gtn.dao.GenericDao;
import com.gtn.exception.ApplicationException;
import com.gtn.model.Product;
import com.gtn.model.UserBilling;
//import com.sun.org.apache.bcel.internal.generic.NEW;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BillingServiceImpl implements BillingService {
	@Autowired
	private GenericDao dao;

	public GenericDao getDao() {
		return dao;
	}

	public void setDao(GenericDao dao) {
		this.dao = dao;
	}
	@Override
	public Collection<UserBilling> getBillingDetails(String emailId) throws ApplicationException{
		try{
		Collection<UserBilling> result = null;
		
		String queryString = "select Object(ub) from UserBilling ub,User u where ub.userId= u.id  and u.emailId = ?";
		Object[] params = { emailId };
		result = dao.findDynamicQuery(queryString, params);
		System.out.println("result is "+result);
		return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("User Biilling Details Not found ", e);
		}
	}
	@Override
	public int deletePaymentMethod(String ccNumber,String userId) throws ApplicationException {
		try{
		String queryString = "delete from UserBilling ub where ub.cardNo = ? and ub.userId= ? ";
		Object[] params = { new Long(ccNumber),userId };
		int result = dao.deleteDynamic(queryString, params);
		System.out.println("result is "+result);
		return result;

		} catch (Exception e) {
			//e.printStackTrace();
			throw new ApplicationException("Error while deleting payment method"+e.getMessage(), e);
		}
	}

	@Override
	public  Collection<UserBilling> getExpiredBillingDetails() throws ApplicationException {
		try{
			String queryString = "select object(ub) from UserBilling ub where ub.exp_date < current_timestamp ";
			Object[] params = {};
			return dao.findDynamicQuery(queryString, params);
			
			} catch (Exception e) {
				//e.printStackTrace();
				throw new ApplicationException("System Error"+e.getMessage(), e);
			}
	}

	@Override
	public UserBilling getPrimaryBillingDetail(String email) throws ApplicationException {
		UserBilling returnValue=null;
		
		try{
			Collection<UserBilling> result = null;
			String queryString = "select Object(ub) from UserBilling ub,User u where ub.userId= u.id  and u.emailId = ?";
			Object[] params = { email };
			result = dao.findDynamicQuery(queryString, params);
			System.out.println("result is "+result);
			if(result!=null && result.size()==1)
				returnValue=(result.iterator().next());
			else {
				for(UserBilling billing:result) {
					if(billing.getPrimary().equalsIgnoreCase("Y")){
						returnValue= billing;
						break;
					}
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("User Biilling Details Not found ", e);
			}
		return returnValue;
		}
}

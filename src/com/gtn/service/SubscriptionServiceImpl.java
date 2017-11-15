package com.gtn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.gtn.dto.SubscriptionDto;
import com.gtn.dto.UserDto;
import com.gtn.dao.GenericDao;
import com.gtn.exception.ApplicationException;
import com.gtn.model.Subscription;
import com.gtn.model.SubscriptionProduct;
import com.gtn.model.SubscriptionUser;
import com.gtn.model.Product;
import java.util.Collection;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private GenericDao dao;
	private static final Logger logger = LoggerFactory
			.getLogger(SubscriptionServiceImpl.class);

	public GenericDao getDao() {
		return dao;
	}

	public void setDao(GenericDao dao) {
		this.dao = dao;
	}

	@Override
	public void saveSubscription(Subscription subscription)
			throws ApplicationException {
		try {
			if (subscription.getSubscriptionId() > 0)
				dao.update(subscription);
			else
				dao.create(subscription);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Subscription Not created ", e);
		}
	}

	@Override
	public void saveSubscriptionProduct(SubscriptionProduct subscriptionProduct)
			throws ApplicationException {
		try {
			dao.create(subscriptionProduct);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Subscription Product Not created ",
					e);
		}
	}

	@Override
	public void saveSubscriptionUser(SubscriptionUser subscriptionUser)
			throws ApplicationException {
		try {
			dao.create(subscriptionUser);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Subscription User Not created ", e);
		}
	}

	@Override
	public boolean deleteSubscriptionProduct(long subscriptionId)
			throws ApplicationException {
		boolean allDeleted = false;
		String queryString = "delete from SubscriptionProduct sp where sp.subscriptionId=?1";
		Object[] params = { subscriptionId };
		int result = dao.deleteDynamic(queryString, params);
		logger.info("result is ", result);
		return true;
	}

	@Override
	public boolean deleteSubscription(UserDto userDto)
			throws ApplicationException {
		boolean allDeleted = false;
		String queryString = "delete from SubscriptionProduct sp where sp.subscriptionId=?1";
		Object[] params = { new Long(userDto.getSubscriptionId()).longValue() };
		int result = dao.deleteDynamic(queryString, params);
		// System.out.println("result is "+result);
		queryString = "delete from SubscriptionUser su where su.subscriptionId=?1";
		Object[] params1 = { new Long(userDto.getSubscriptionId()).longValue() };
		result = dao.deleteDynamic(queryString, params1);
		// System.out.println("result is "+result);
		queryString = "delete from User u where u.parentUserId=?1";
		Object[] params2 = { new Long(userDto.getId()).longValue() };
		result = dao.deleteDynamic(queryString, params2);
		// System.out.println("result is "+result);
		queryString = "delete from Subscription s where s.subscriptionId=?1";
		Object[] params3 = { new Long(userDto.getSubscriptionId()).longValue() };
		result = dao.deleteDynamic(queryString, params3);
		// System.out.println("result is "+result);
		allDeleted = true;
		return allDeleted;
	}

	public Subscription getSubscription(long SubscriptionId)
			throws ApplicationException {
		try {
			return (Subscription) dao.read(Subscription.class, SubscriptionId);
		} catch (Exception e) {
			throw new ApplicationException("Subscription Not found ", e);
		}
	}

	public Subscription getSubscriptionFromUser(long id)
			throws ApplicationException {
		try {
			String queryString = "select Object(s) from Subscription s,SubscriptionUser su where s.subscriptionId = su.subscriptionId and su.userId=?1";
			Object[] params = { id };
			Collection<Subscription> subs = dao.findDynamicQuery(queryString,
					params);
			if (subs.size() > 0)
				return (Subscription) subs.toArray()[0];
			else
				throw new Exception("Subscription Not found");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Subscription Not found ", e);
		}
	}

	public Collection<Product> getSubscriptionProducts(long SubscriptionId)
			throws ApplicationException {
		try {
			String queryString = "select Object(p) from Product p,SubscriptionProduct sp where sp.subscriptionId = ?1 and sp.productId=p.productId";
			Object[] params = { SubscriptionId };
			return dao.findDynamicQuery(queryString, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Subscription Not found ", e);
		}
	}
	
	@Override
	public Collection<SubscriptionProduct> getCurrentSubscriptionProducts(long SubscriptionId)
			throws ApplicationException {
		try {
			String queryString = "select Object(sp) from Product p,SubscriptionProduct sp where sp.subscriptionId = ?1 and sp.productId=p.productId";
			Object[] params = { SubscriptionId };
			return dao.findDynamicQuery(queryString, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Subscription Not found ", e);
		}
	}
	
	
	
	@Override
	public Collection<Product> getSubscriptionProductsActive(long SubscriptionId)
			throws ApplicationException {
		try {
			String queryString = "select Object(p) from Product p,SubscriptionProduct sp where sp.subscriptionId = ?1 and sp.productId=p.productId and sp.status = ?2";
			Object[] params = { SubscriptionId, "A" };
			return dao.findDynamicQuery(queryString, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Subscription Not found ", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Subscription getSubscriptionTransact(long id) throws ApplicationException {
		try {
			String queryString = "select Object(s) from Subscription s,SubscriptionUser su, SubscriptionTransaction st where s.subscriptionId = su.subscriptionId and su.userId=?1 and su.subscriptionId=st.subscriptionId";
			Object[] params = { id };
			Collection<Subscription> subs = dao.findDynamicQuery(queryString,
					params);
			if (subs.size() > 0)
				return (Subscription) subs.toArray()[0];
			else
				throw new Exception("Subscription Payment Pending");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Subscription Payment Pending", e);
		}
	}
	
	@Override
	public Collection getUserCart(long subscriptionId) throws ApplicationException{
		String queryString = "select sum(sub.noOfUsers * p.cost) as CartSum, count(sp.productId) as CartNum from SubscriptionProduct sp, Subscription sub, Product p where sp.subscriptionId = ?1 and sp.status = 'P' and p.productId = sp.productId and sp.subscriptionId=sub.subscriptionId";
		
		Object[] params = {subscriptionId};
		try{
			Collection result = dao.findDynamicQuery(queryString, params);
			return result;
		}catch(Exception e){
			throw new ApplicationException("Error geting cart information : ", e);
		}
	}
	
	@Override
	public Collection<Product> getUserPurchasedProducts(long subscriptionId) throws ApplicationException{
		String queryString = "select p.cost, p.displayName, p.currencyCode, p.productId from SubscriptionProduct sp, Subscription sub, Product p where sp.subscriptionId = ?1 and sp.status = 'A' and p.productId = sp.productId and sp.subscriptionId=sub.subscriptionId";
		
		Object[] params = {subscriptionId};
		try{
			Collection<Product> result = dao.findDynamicQueryWithFields(queryString, params, Product.class);
			return result;
		}catch(Exception e){
			throw new ApplicationException("Error geting purchased products : ", e);
		}
	}
}

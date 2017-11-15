package com.gtn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gtn.dao.GenericDao;
import com.gtn.exception.ApplicationException;
import com.gtn.model.ShoppingCart;
import com.gtn.model.Subscription;
import com.gtn.model.SubscriptionProduct;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private GenericDao dao;

	public GenericDao getDao() {
		return dao;
	}

	public void setDao(GenericDao dao) {
		this.dao = dao;
	}
	@Override
	public void saveCart(ShoppingCart shoppingCart) throws ApplicationException {
		try{
		dao.create(shoppingCart);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("User Not created ", e);
		}
	}
	
}

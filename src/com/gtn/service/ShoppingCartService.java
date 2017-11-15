package com.gtn.service;

import com.gtn.exception.ApplicationException;
import com.gtn.model.ShoppingCart;
import com.gtn.model.Subscription;
import com.gtn.model.SubscriptionProduct;


public interface ShoppingCartService {
	
	public void saveCart(ShoppingCart shoppingCart) throws ApplicationException;

}

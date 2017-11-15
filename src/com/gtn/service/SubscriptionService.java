package com.gtn.service;

import com.gtn.exception.ApplicationException;
import com.gtn.model.Subscription;
import com.gtn.model.SubscriptionProduct;
import com.gtn.model.SubscriptionUser;
import com.gtn.model.Product;
import com.gtn.dto.UserDto;

import java.util.Collection;
public interface SubscriptionService {
	
	public void saveSubscription(Subscription subscription) throws ApplicationException;
	public void saveSubscriptionProduct(SubscriptionProduct subscriptionProduct) throws ApplicationException;
	public void saveSubscriptionUser(SubscriptionUser subscriptionUser) throws ApplicationException;
	public Subscription getSubscription(long SubscriptionId) throws ApplicationException;
	public Subscription getSubscriptionFromUser(long id) throws ApplicationException;
	public boolean deleteSubscription(UserDto userDto) throws ApplicationException;
	public boolean deleteSubscriptionProduct(long id) throws ApplicationException;

	public Collection<Product>  getSubscriptionProducts(long SubscriptionId) throws ApplicationException;
	public Collection<Product>  getSubscriptionProductsActive(long SubscriptionId) throws ApplicationException;
	public Subscription getSubscriptionTransact(long id) throws ApplicationException;
	public Collection<SubscriptionProduct> getCurrentSubscriptionProducts(long SubscriptionId) throws ApplicationException;
	public Collection getUserCart(long subscriptionId) throws ApplicationException;
	public Collection<Product> getUserPurchasedProducts(long subscriptionId) throws ApplicationException;
	
}

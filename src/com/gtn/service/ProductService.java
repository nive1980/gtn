package com.gtn.service;

import java.util.Collection;
import java.util.Map;

import com.gtn.exception.ApplicationException;
import com.gtn.model.Product;

public interface ProductService {

	public Collection<Product> getProducts() throws ApplicationException;

	public Collection<Product> getSubProducts(long parentId) throws ApplicationException;
	
	public Product getProduct(long productId)throws ApplicationException;
	public Collection<Product> getAllProducts() throws ApplicationException;
}

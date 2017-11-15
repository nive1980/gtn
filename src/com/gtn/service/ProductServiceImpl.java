package com.gtn.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gtn.dao.GenericDao;
import com.gtn.exception.ApplicationException;
import com.gtn.model.Product;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ProductServiceImpl implements ProductService {

	@Autowired
	private GenericDao dao;

	public GenericDao getDao() {
		return dao;
	}

	public void setDao(GenericDao dao) {
		this.dao = dao;
	}

	@Override
	public Collection<Product> getProducts() throws ApplicationException{
		try{
		Collection<Product> result = null;
		String queryString = "select Object(p) from Product p where p.status = 'A' and p.parentProductId = 0";
		Object[] params = {  };
		result = dao.findDynamicQuery(queryString, params);
		return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Product Not found ", e);
		}
	}
	@Override
	public Collection<Product> getSubProducts(long parentId) throws ApplicationException{
		try{
		Collection<Product> result = null;
		String queryString = "select Object(p) from Product p where p.status = 'A' and p.parentProductId =?1";
		Object[] params = { parentId };
		result = dao.findDynamicQuery(queryString, params);
		return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Product Not found ", e);
		}
	}
	
	public Product getProduct(long productId)throws ApplicationException{
		try{
			return (Product) dao.read(Product.class, productId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Product Not found ", e);
		}
	}
	
	@Override
	public Collection<Product> getAllProducts() throws ApplicationException{
		try{
			Collection<Product> result = null;
			String queryString = "select Object(p) from Product p where p.status = 'A' order by p.level ASC";
			Object[] params = {  };
			result = dao.findDynamicQuery(queryString, params);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Product Not found ", e);
		}
	}
	
	public List<Product> getUserProducts(int userId) throws ApplicationException{
		
		
		
		return null;
	}
}

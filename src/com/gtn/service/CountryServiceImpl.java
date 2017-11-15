package com.gtn.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gtn.dao.GenericDao;
import com.gtn.exception.ApplicationException;
import com.gtn.model.Country;
import com.gtn.model.Product;
import com.gtn.model.User;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CountryServiceImpl implements CountryService {

	@Autowired
	private GenericDao dao;

	public GenericDao getDao() {
		return dao;
	}

	public void setDao(GenericDao dao) {
		this.dao = dao;
	}

	@Override
	public Collection<Country> getCountries() throws ApplicationException {
		try {
			String queryString = "select Object(c)  from Country c";
			Object[] params = {  };
			Collection<Country> result = null;
			result = dao.findDynamicQuery(queryString, params);
			return result;
			} catch (Exception e) {
System.out.println("in error@@@@@@@@@@");
			e.printStackTrace();
			throw new ApplicationException("User Not found ", e);
		}
	}

}

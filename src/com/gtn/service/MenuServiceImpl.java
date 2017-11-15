package com.gtn.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gtn.dao.GenericDao;
import com.gtn.exception.ApplicationException;
import com.gtn.model.MenuValue;
import com.gtn.model.User;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MenuServiceImpl implements MenuService{

	@Autowired
	private GenericDao dao;
	
	@Override
	public Collection<MenuValue> getMenuList(User user) throws ApplicationException{
		try{
			Collection<MenuValue> result = null;
			String queryString = null;
			
			if("S".equals(user.getUserType())){
				queryString = "select Object(p) from MenuValue p where p.status = 'A' order by p.level ASC";
			}else{
				queryString = "select Object(p) from MenuValue p where p.status = 'A' and p.productId is not null order by p.level ASC";
			}
			
			Object[] params = {  };
			result = dao.findDynamicQuery(queryString, params);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("MenuValue Not found ", e);
		}
	}

}

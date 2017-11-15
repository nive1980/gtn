package com.gtn.service;

import java.util.Collection;
import java.util.List;

import com.gtn.exception.ApplicationException;
import com.gtn.model.MenuValue;
import com.gtn.model.User;

public interface MenuService {

	public Collection<MenuValue> getMenuList(User user)  throws ApplicationException;
	
}

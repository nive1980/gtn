package com.gtn.service;

import java.util.Collection;
import java.util.List;

import com.gtn.exception.ApplicationException;
import com.gtn.model.Country;
import com.gtn.model.User;


public interface CountryService {

	
	public Collection<Country> getCountries() throws ApplicationException;
}

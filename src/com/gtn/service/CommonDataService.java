package com.gtn.service;

import java.util.Collection;

import com.gtn.exception.ApplicationException;
import com.gtn.model.SbuConfigValue;

public interface CommonDataService {

	public SbuConfigValue getSbuConf(String sbu, String paramName) throws ApplicationException;
	public Collection<String> getSbuList() throws ApplicationException;
	/*public boolean validateCountry(String countryCode) throws ApplicationException;
	public boolean validateMot(String motCode) throws ApplicationException;
	public boolean validateExporter(String expCode) throws ApplicationException;
	public boolean validateConsignee(String consigneeCode) throws ApplicationException;
	boolean validateFreightForwarder(String ffCode) throws ApplicationException;
	boolean validateSchb(String schbCode) throws ApplicationException;
	boolean validateModel(String modelCode) throws ApplicationException;
	boolean validateEntity(E classType, String modelCode) throws ApplicationException;*/
	
}

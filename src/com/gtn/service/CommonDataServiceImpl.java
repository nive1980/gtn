package com.gtn.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtn.dao.GenericDao;
import com.gtn.exception.ApplicationException;
import com.gtn.helper.CommonDataHelper;
import com.gtn.model.SbuConfigValue;

@Service("commonDataService")
public class CommonDataServiceImpl implements CommonDataService{

	@Autowired
	private GenericDao dao;	
	
	@Override
	public SbuConfigValue getSbuConf(String sbu, String paramName) throws ApplicationException {
		
		String queryString = "select Object(p) from SbuConfigValue p where sbu = ?1 and paramName = ?2";
		
		if(CommonDataHelper.isEmpty(sbu) && CommonDataHelper.isEmpty(paramName)){
			return null;
		}else if(CommonDataHelper.isEmpty(sbu) && !CommonDataHelper.isEmpty(paramName)){
			queryString = "select Object(p) from SbuConfigValue p where paramName = ?2";
		}
		
		
		Object[] params = {sbu, paramName};
		
		Collection<SbuConfigValue> result = dao.findDynamicQuery(queryString, params);
		
		if(result!=null && result.size() > 0){
			return result.iterator().next();
		}
		
		return null;
	}

	@Override
	public Collection<String> getSbuList() throws ApplicationException{
		String queryString = "select distinct(sbu) from User where sbu is not null order by sbu";
		Object[] params = {};
		Collection<String> sbus = null;
		try{
			sbus = dao.findDynamicQuery(queryString, params);
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Error getting SBU list", e);
		}
		
		return sbus;
	}
	
	/*@Override
	public boolean validateCountry(String countryCode) throws ApplicationException {
		try{
			Country country = (Country) dao.read(Country.class, countryCode);
			if(country==null){
				return false;
			}else{
				return true;
			}
		}catch(Exception e){
			throw new ApplicationException("Error validating country", e);
		}
	}

	@Override
	public boolean validateMot(String motCode) throws ApplicationException {
		try{
			ModeOfTransport country = (ModeOfTransport) dao.read(ModeOfTransport.class, motCode);
			if(country==null){
				return false;
			}else{
				return true;
			}
		}catch(Exception e){
			throw new ApplicationException("Error validating ModeOfTransport", e);
		}
	}
	
	@Override
	public boolean validateExporter(String expCode) throws ApplicationException {
		try{
			ExporterValue exporter = (ExporterValue) dao.read(ExporterValue.class, expCode);
			if(exporter==null){
				return false;
			}else{
				return true;
			}
		}catch(Exception e){
			throw new ApplicationException("Error validating ExporterValue", e);
		}
	}

	
	
	@Override
	public boolean validateConsignee(String consigneeCode) throws ApplicationException {
		try{
			ConsigneeValue consignee = (ConsigneeValue) dao.read(ConsigneeValue.class, consigneeCode);
			if(consignee==null){
				return false;
			}else{
				return true;
			}
		}catch(Exception e){
			throw new ApplicationException("Error validating ConsigneeValue", e);
		}
	}
	
	@Override
	public boolean validateFreightForwarder(String ffCode) throws ApplicationException {
		try{
			FreightForwarderValue ff = (FreightForwarderValue) dao.read(FreightForwarderValue.class, ffCode);
			if(ff==null){
				return false;
			}else{
				return true;
			}
		}catch(Exception e){
			throw new ApplicationException("Error validating FreightForwarderValue", e);
		}
	}
	
	
	@Override
	public boolean validateSchb(String schbCode) throws ApplicationException {
		try{
			SchBValue schb = (SchBValue) dao.read(SchBValue.class, schbCode);
			if(schb==null){
				return false;
			}else{
				return true;
			}
		}catch(Exception e){
			throw new ApplicationException("Error validating FreightForwarderValue", e);
		}
	}
	
	@Override
	public boolean validateModel(String modelCode) throws ApplicationException {
		try{
			Model schb = (Model) dao.read(Model.class, modelCode);
			if(schb==null){
				return false;
			}else{
				return true;
			}
		}catch(Exception e){
			throw new ApplicationException("Error validating Model", e);
		}
	}
	
	
	@Override
	public <E> boolean validateEntity(E classType, String modelCode) throws ApplicationException {
		try{
			E entity = (E) dao.read(classType.getClass(), modelCode);
			if(entity==null){
				return false;
			}else{
				return true;
			}
		}catch(Exception e){
			throw new ApplicationException("Error validating Entity", e);
		}
	}*/
}

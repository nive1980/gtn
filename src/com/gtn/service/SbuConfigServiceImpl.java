package com.gtn.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtn.dao.GenericDao;
import com.gtn.dto.SbuConfigView;
import com.gtn.exception.ApplicationException;
import com.gtn.model.SbuConfigValue;
import com.gtn.util.ValidationUtil;

@Service
public class SbuConfigServiceImpl implements SbuConfigService{

	@Autowired
	private GenericDao dao;
	
	@Override
	public Collection<SbuConfigValue> searchSbuConfig(SbuConfigView view) throws ApplicationException {
		
		try{
			StringBuilder queryString = new StringBuilder("select Object(p) from SbuConfigValue p where");
			
			if(!ValidationUtil.isEmpty(view.getSbu())){
				queryString.append(" upper(p.sbu) = upper('"+view.getSbu()+"') AND ");
			}
			if(!ValidationUtil.isEmpty(view.getParamName())){
				queryString.append(" upper(p.paramName) like upper('%"+view.getParamName()+"%') AND ");
			}
			if(!ValidationUtil.isEmpty(view.getParamValue())){
				queryString.append(" upper(p.paramValue) like upper('%"+view.getParamValue()+"%') AND ");
			}
			if(!ValidationUtil.isEmpty(view.getParamDescription())){
				queryString.append(" upper(p.paramDescription) like upper('%"+view.getParamDescription()+"%') AND ");
			}
			
			queryString.append(" p.paramName is not null");
			
			System.out.println("sbu config search queryString --------------- " + queryString.toString());
			
			Object[] params = {};
			Collection<SbuConfigValue> result = null;
			result = dao.findDynamicQuery(queryString.toString(), params);
			
			return result;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("SBU configuration : Exception in search sbu configuration ", e);
		}
		
	}

}

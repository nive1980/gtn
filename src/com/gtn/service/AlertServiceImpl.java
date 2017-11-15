package com.gtn.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gtn.dao.GenericDao;
import com.gtn.exception.ApplicationException;
import com.gtn.model.Alert;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AlertServiceImpl implements AlertService {

	@Autowired
	private GenericDao dao;

	public GenericDao getDao() {
		return dao;
	}

	private static final Logger logger = LoggerFactory.getLogger(AlertServiceImpl.class);
	@Override
	public Collection<Alert> getAlerts(long userId) throws ApplicationException {
		try{
			String queryString = "select Object(a) from Alert a where current_timestamp between a.effStartDate and a.effToDate and a.status = 'A' and (a.userId is null or a.userId = ?1)" ;
			Object[] params = {userId};
			return dao.findDynamicQuery(queryString, params);
		} catch (Exception e) {
			logger.error("No alerts found for user {} ",userId,e);;
			throw new ApplicationException("Alert Not found ", e);
		}
	}

	@Override
	public void saveAlert(Alert alert) throws ApplicationException {
		try{
			if(alert.getId()>0)
				dao.update(alert);
			else
				dao.create(alert);
		} catch (Exception e) {
			logger.error("Alert Not created ",e);
			throw new ApplicationException("Alert Not created ", e);
		}
		
	}

}

package com.gtn.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gtn.dao.GenericDao;
import com.gtn.exception.ApplicationException;
import com.gtn.model.Case;
import com.gtn.model.CaseLog;
import com.gtn.model.Product;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CaseServiceImpl implements CaseService {
	@Autowired
	private GenericDao dao;

	public GenericDao getDao() {
		return dao;
	}

	public void setDao(GenericDao dao) {
		this.dao = dao;
	}
	@Override
	public void saveCase(Case newCase) throws ApplicationException{
		try{
			if(newCase.getTicketId()>0)
				dao.update(newCase);
			else
				dao.create(newCase);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Case Not created ", e);
		}
	}

	@Override
	public Case getCase(long caseId) throws ApplicationException {
		return (Case)dao.read(Case.class, caseId);
	}

	@Override
	public Collection<Case> getCasesByUser(long userId) throws ApplicationException {
		try{
			// ORDER BY c.createdOn DESC"
			String queryString = "select Object(c) from Case c where c.createdBy = ?1 ORDER BY c.createdOn DESC";
			Object[] params = {userId};
			return dao.findDynamicQuery(queryString, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Cases Not found ", e);
		}
	}

	@Override
	public Collection<Case> getOpenCasesByUser(long userId) throws ApplicationException {
		try{
			// ORDER BY c.createdOn DESC"
			String queryString = "select Object(c) from Case c where c.createdBy = ?1 and c.status = 'O' ORDER BY c.createdOn DESC";
			Object[] params = {userId};
			return dao.findDynamicQuery(queryString, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Cases Not found ", e);
		}
	}
	
	@Override
	public Collection<Case> getClosedCasesByUser(long userId) throws ApplicationException {
		try{
			// ORDER BY c.createdOn DESC"
			String queryString = "select Object(c) from Case c where c.createdBy = ?1 and c.status = 'C' ORDER BY c.createdOn DESC";
			Object[] params = {userId};
			return dao.findDynamicQuery(queryString, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Cases Not found ", e);
		}
	}
	@Override
	public Collection<Case> getCasesForAdmin(long userId) throws ApplicationException {
		try{
			String queryString = "select Object(c) from Case c where c.assignedTo in (0,?1) order by c.createdOn desc, c.assignedTo asc";
			Object[] params = {userId};
			return dao.findDynamicQuery(queryString, params);
		} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("Open Cases Not found ", e);
			}
	}
	
	@Override
	public void saveCaseLog(CaseLog newCaseLog) throws ApplicationException{
		try{
			if(newCaseLog.getId()>0)
				dao.update(newCaseLog);
			else
				dao.create(newCaseLog);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Case Not created ", e);
		}
	}
	
	@Override
	public Collection<CaseLog> getCaseLogs(long ticketId) throws ApplicationException{
		try{
			String queryString = "select Object(l) from CaseLog l where l.ticketId = ?1 order by l.messageOn desc";
			Object[] params = {ticketId};
			return dao.findDynamicQuery(queryString, params);
		} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("CaseLog Not found ", e);
			}
	}

}

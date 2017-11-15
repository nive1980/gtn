package com.gtn.service;

import java.util.Collection;

import com.gtn.exception.ApplicationException;
import com.gtn.model.Case;
import com.gtn.model.CaseLog;

public interface CaseService {
	public void saveCase(Case newCase) throws ApplicationException;
	public Case getCase(long caseId) throws ApplicationException;
	public Collection<Case> getCasesByUser(long userId)throws ApplicationException;
	public Collection<Case> getCasesForAdmin(long userId)throws ApplicationException;
	public void saveCaseLog(CaseLog newCaseLog) throws ApplicationException;
	public Collection<CaseLog> getCaseLogs(long ticketId) throws ApplicationException;
	public Collection<Case> getOpenCasesByUser(long userId) throws ApplicationException;
	public Collection<Case> getClosedCasesByUser(long userId) throws ApplicationException;
}

package com.gtn.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gtn.dao.GenericDao;
import com.gtn.exception.ApplicationException;
import com.gtn.model.Message;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MessageServiceImpl implements MessageService {

	@Autowired
	private GenericDao dao;

	public GenericDao getDao() {
		return dao;
	}

	@Override
	public long getUnreadMessageCountByUser(long userId) throws ApplicationException {
		try{
			//String queryString = "select count(m.id) from Message m where m.status = 'U' and m.userId = ?1 order by m.createdDate DESC";
			String queryString = "select count(m.id) from Message m where m.status = 'U' and m.userId = ?1";
			Object[] params = {userId};
			return dao.findDynamicScalar(queryString, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Message not found ", e);
		}
	}

	@Override
	public long getUnreadMessageCountForCasesByUser(long userId) throws ApplicationException {
		try{
			//String queryString = "select count(m.id) from Message m where m.status = 'U' and m.userId = ?1 and m.ticketId > 0 order by m.createdDate DESC";
			String queryString = "select count(m.id) from Message m where m.status = 'U' and m.userId = ?1 and m.ticketId > 0";
			Object[] params = {userId};
			return dao.findDynamicScalar(queryString, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Message not found ", e);
		}
	}

	@Override
	public void saveMessage(Message message) throws ApplicationException {
		try{
			if(message.getId()>0)
				dao.update(message);
			else
				dao.create(message);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Case Not created ", e);
		}
		
	}

	@Override
	public void markMessageAsReadForCases(long userId, long ticketId) throws ApplicationException {
		try{
			String queryString = "select Object(m) from Message m where m.userId = ?1 and m.ticketId = ?2 and m.status = 'U'";
			Object[] params = {userId,ticketId};
			Collection<Message> messagesToUpdate = dao.findDynamicQuery(queryString, params);
			for(Message messageToUpdate : messagesToUpdate){
				messageToUpdate.setStatus("R");
				saveMessage(messageToUpdate);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Cases Not found ", e);
		}
		
	}

}

package com.gtn.service;

import java.util.Collection;

import com.gtn.exception.ApplicationException;
import com.gtn.model.Message;

public interface MessageService {

	public long getUnreadMessageCountByUser(long userId) throws ApplicationException;
	public long getUnreadMessageCountForCasesByUser(long userId) throws ApplicationException;
	public void saveMessage(Message message) throws ApplicationException;
	public void markMessageAsReadForCases(long userId,long ticketId) throws ApplicationException;
}

package com.gtn.service;

import java.util.Collection;
import java.util.List;

import com.gtn.model.Mark;
import com.gtn.model.Product;
import com.gtn.dto.MarkDto;
import com.gtn.exception.ApplicationException;
import com.gtn.model.Publication;

public interface PublicationService {
	public Collection<Publication> getPublications(String type) throws ApplicationException;
	public Collection<Publication> getPublicationsByDescription(String description) throws ApplicationException;
	public Collection<Publication> getPublicationsById(String id) throws ApplicationException;
	public Collection<Mark> getAllMarks(MarkDto markInputDto) throws ApplicationException;
	public void saveMark(Mark mark,String operation)  throws ApplicationException;
	public int deleteMark(String id) throws ApplicationException;
	public Mark getMark(String id) throws ApplicationException;
	public boolean mapUserToPublication(long userId,String publicationId) throws ApplicationException;
	List<Product> getSubscribedPublicationProducts(String emailId) throws ApplicationException;
	public boolean isIndividuallyPurchased(long publicationId,long userId) throws ApplicationException;
	public boolean isMarkedForPurchase(long publicationId,long userId) throws ApplicationException;

	public void saveUserToPublicationStatus(long id, String itemId) throws ApplicationException;
	public Collection<Publication> getPublicationsMarkedForPurchaseByUser(
			long longValue) throws ApplicationException;
	public boolean unmapPublication(long id) throws ApplicationException;
}

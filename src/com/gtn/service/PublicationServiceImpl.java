package com.gtn.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gtn.dao.GenericDao;
import com.gtn.dto.MarkDto;
import com.gtn.exception.ApplicationException;
import com.gtn.model.Mark;
import com.gtn.model.Product;
import com.gtn.model.Publication;
import com.gtn.model.Subscription;
import com.gtn.model.SubscriptionProduct;
import com.gtn.model.SubscriptionUser;
import com.gtn.model.PublicationUser;
import com.gtn.model.UserBilling;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PublicationServiceImpl implements PublicationService {
	@Autowired
	private GenericDao dao;

	public GenericDao getDao() {
		return dao;
	}

	public void setDao(GenericDao dao) {
		this.dao = dao;
	}
	@Override
	public boolean mapUserToPublication(long userId,String publicationId) throws ApplicationException
	{
		boolean isMarkedForPurchseb=isMarkedForPurchase(new Long(publicationId).longValue(),userId);
		System.out.println("isMarkedForPurchseb@@@@@@@@@@ "+isMarkedForPurchseb);
		if(!isMarkedForPurchseb){
		PublicationUser publicationUser = new PublicationUser();
		publicationUser.setPublicationId(new Long(publicationId).longValue());
		publicationUser.setUserId(userId);
		publicationUser.setCreatedOn(new Date());
		publicationUser.setStatus("P");
		try{
			dao.create(publicationUser);
			return true;
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("Publication User Not created ", e);
			}
		}
		else return true;
		
		}
	@Override
	public List<Product>  getSubscribedPublicationProducts(String emailId) throws ApplicationException{
		List<Product> subscribedPublicationproducts=new ArrayList<Product>();  
		
		try{
		List<SubscriptionUser> subUsers = null;
		List<Product> products=new ArrayList<Product>();  
		SubscriptionUser subUser=null;
		String queryString = "select Object(su) from SubscriptionUser su,User u where su.userId= u.id  and u.emailId = ?";
		Object[] params = { emailId };
		subUsers = (List<SubscriptionUser>)dao.findDynamicQuery(queryString, params);
		System.out.println("result is "+subUsers);
		if(subUsers!=null && subUsers.size()>0) {
			subUser=subUsers.get(0);
		if(subUser!=null) {
			String string1="select Object(sp) from SubscriptionProduct sp where sp.subscriptionId =?1";

		//	String queryString1 = "select Object(p) from Product p where p.parentProductId =(select p1.productId from Product p1 where p1.displayName='Publications'))";
			Object[] params1= { subUser.getSubscriptionId()};
			
			List<SubscriptionProduct> subProducts=  (ArrayList<SubscriptionProduct>)dao.findDynamicQuery(string1, params1);
			StringBuffer sb = new StringBuffer();
			
			for(int i=0;i<subProducts.size();i++) {
				sb.append("'");
				sb.append(subProducts.get(i).getProductId());
				sb.append("'");
				sb.append(",");
			}
			System.out.println("sb is "+sb);
			String finalProductList=sb.toString().substring(0, sb.length()-1);
			String queryString1 = "select Object(p) from Product p where p.parentProductId =(select p1.productId from Product p1 where p1.displayName='Publications') and p.productId in ("+finalProductList+"))";
			Object[] params2= {};
			
			subscribedPublicationproducts=  (ArrayList<Product>)dao.findDynamicQuery(queryString1, params2);
		}}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("User Biilling Details Not found ", e);
		}
		return subscribedPublicationproducts;
	}

	@Override
	public Collection<Publication> getPublications(String type) throws ApplicationException {
		try{
			Collection<Publication> result = null;
			String queryString = null;
			Object[] params = new Object[1];
			
			if(type == null || "".equals(type)){
				queryString = "select Object(pub) from Publication pub";
			}else{
				queryString = "select Object(pub) from Publication pub where pub.type = ?1";
				params[0] = type;
			}
			
			result = dao.findDynamicQuery(queryString, params);
			return result;
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("Publications Not found ", e);
			}
	}

	@Override
	public Collection<Publication> getPublicationsByDescription(String description) throws ApplicationException {
		try{
			Collection<Publication> result = null;
			System.out.println("description here os "+description);
			String queryString = "select Object(pub) from Publication pub where pub.description like '%"+description+"%'";
			Object[] params = { };
			result = dao.findDynamicQuery(queryString, params);
			return result;
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("Publications Not found ", e);
			}}
	@Override
	public Collection<Publication> getPublicationsById(String id) throws ApplicationException {
		try{
			Collection<Publication> result = null;
			System.out.println("description here os "+id);
			String queryString = "select Object(pub) from Publication pub where pub.id ='"+id+"'";
			Object[] params = { };
			result = dao.findDynamicQuery(queryString, params);
			return result;
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("Publications Not found ", e);
			}}

	@Override
	public Collection<Mark> getAllMarks(MarkDto markInputDto) throws ApplicationException {
		try{
			Collection<Mark> result = null;
System.out.println("author "+markInputDto.getAuthor());

System.out.println("file "+markInputDto.getDocument_filename());
			String queryString = "select Object(m) from Mark m where m.document_filename=?1 and m.author=?2";
			Object[] params = { markInputDto.getDocument_filename(),markInputDto.getAuthor() };
			result = dao.findDynamicQuery(queryString, params);
			return result;
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("Marks Not found ", e);
			}}

	@Override
	public void saveMark(Mark mark ,String operation) throws ApplicationException {
		try{
			if(operation.equals("change"))
				dao.update(mark);
			else
			dao.create(mark);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Mark Not created ", e);
		}
		
	}

	@Override
	public int deleteMark(String id) throws ApplicationException {
		try{
			System.out.println("id is "+id);
		String queryString = "delete from Mark m where m.id = ? ";
		Object[] params = { id };
		int result = dao.deleteDynamic(queryString, params);
		System.out.println("result is "+result);
		return result;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new ApplicationException("Error while deleting payment method"+e.getMessage(), e);
		}
	}

	@Override
	public Mark getMark(String id) throws ApplicationException {
		try{
			return(Mark) dao.read(Mark.class,id);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("Mark Not found ", e);
			}
	}
	
	@Override
	public boolean isIndividuallyPurchased(long publicationId,long userId) throws ApplicationException {
		PublicationUser publicationUser = new PublicationUser();
		publicationUser.setPublicationId(publicationId);
		publicationUser.setUserId(userId);
		try {
			String queryString = "select Object(pu) from PublicationUser pu where pu.publicationId = ?1 and pu.userId=?2 and pu.status='A'";
			Object[] params = { publicationId,userId };
			List<PublicationUser> pubs= (List<PublicationUser>) dao.findDynamicQuery(queryString, params);
			if(pubs!=null && pubs.size()>0)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
		
		@Override
		public boolean isMarkedForPurchase(long publicationId,long userId) throws ApplicationException {
			PublicationUser publicationUser = new PublicationUser();
			publicationUser.setPublicationId(publicationId);
			publicationUser.setUserId(userId);
			try {
				String queryString = "select Object(pu) from PublicationUser pu where pu.publicationId = ?1 and pu.userId=?2 and pu.status='P'";
				Object[] params = { publicationId,userId };
				List<PublicationUser> pubs= (List<PublicationUser>) dao.findDynamicQuery(queryString, params);
				if(pubs!=null && pubs.size()>0)
					return true;
				else
					return false;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		
	}

	@Override
	public void saveUserToPublicationStatus(long userId,String publicationId)
			throws ApplicationException {
		PublicationUser publicationUser = new PublicationUser();
		publicationUser.setPublicationId(new Long(publicationId).longValue());
		publicationUser.setUserId(userId);
		publicationUser.setCreatedOn(new Date());
		publicationUser.setStatus("A");
		try{
			dao.update(publicationUser);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("Publication User Not created ", e);
			}
		
	}

	@Override
	public Collection<Publication> getPublicationsMarkedForPurchaseByUser(long userId)
			throws ApplicationException {
		PublicationUser publicationUser = new PublicationUser();
		publicationUser.setUserId(userId);
		List<Publication> publications=new ArrayList<Publication>();
		try {
			String queryString = "select Object(pu) from PublicationUser pu where pu.userId=?1 and pu.status='P'";
			Object[] params = { userId };
			List<PublicationUser> pubs= (List<PublicationUser>) dao.findDynamicQuery(queryString, params);
			if(pubs!=null && pubs.size()>0)
				
			{	for(PublicationUser pu:pubs){
				Publication publication =getPublicationsById(""+pu.getPublicationId()).iterator().next();
				publications.add(publication);
			}
			}
			else
			{
			System.out.println("publicaitons not mapped");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception occured during ", e);
		}
		return publications;
}

	@Override
	public boolean unmapPublication(long id) throws ApplicationException {
		try{
			System.out.println("id is "+id);
		String queryString = "delete from PublicationUser pu where pu.publicationId = ? ";
		Object[] params = { id };
		int result = dao.deleteDynamic(queryString, params);
		System.out.println("result is "+result);
		if(result>0)
			return true;
		else
			return false;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new ApplicationException("Error while deleting payment method"+e.getMessage(), e);
		}
	}
	}

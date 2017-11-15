package com.gtn.service;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gtn.dao.GenericDao;
import com.gtn.exception.ApplicationException;
import com.gtn.model.User;
import com.gtn.util.ValidationUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	@Autowired
	private GenericDao dao;

	public GenericDao getDao() {
		return dao;
	}

	public void setDao(GenericDao dao) {
		this.dao = dao;
	}

	@Override
	public User getUserByEmail(String emailId) throws ApplicationException {
		try {
			String queryString = "select Object(u) from User u where u.emailId = ?1)";
			Object[] params = { emailId };
			
			Collection<User> user = dao.findDynamicQuery(queryString, params);
			
			int size = user.size();
			//Changed to handle the case when there are no records in user table
				return (User) dao.findDynamicQuery(queryString, params).iterator().next();

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("User Not found - " + emailId, e);
		}
	}

	@Override
	public void saveUser(User user) throws ApplicationException {
		try {
			dao.create(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("User Not created ", e);
		}
	}
	@Override
	public void updateUser(User user) throws ApplicationException {
		try {
			dao.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("User Not updated ", e);
		}
	}

	@Override
	public boolean processForgotPassword(String emailId) throws ApplicationException{
		try{
			if (!ValidationUtil.validateEmail(emailId, 50))
				return false;
			User user = getUserByEmail(emailId);
			if(user != null){
				String otp = RandomStringUtils.randomAlphanumeric(8);
				user.setPassword(otp);
				user.setPwdChangedOn(new Date());
				user.setResetPwdFlag("Y");
				dao.update(user);
				return true;
			}else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public String getOtpForUser(String emailId) throws ApplicationException{
		String otp = null;
		
		String queryString = "select Object(p) from User p where p.emailId = ?1";
		Object[] params = { emailId };
		
		try{
			Collection result = dao.findDynamicQuery(queryString, params);
			
			if(result.size() > 0){
				User user = (User) result.iterator().next();
				otp = user.getPassword();
			}
		}catch(Exception e){
			throw new ApplicationException("Error getting OTP for user : "+emailId, e);
		}
		
		return otp;
	}
	
	@Override
	public void changePassword(String emailId,String oldPassword,String newPassword) throws ApplicationException{
			User user = getUserByEmail(emailId);
			if(user != null){
				String password = user.getPassword();
				System.out.println("password "+password);
/*				MessageDigest md = MessageDigest.getInstance("MD5");
				 md.update(password.getBytes());

			        byte byteData[] = md.digest();
			        StringBuffer sb = new StringBuffer();
			        for (int i = 0; i < byteData.length; i++) {
			         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			        }
			        String hashedPassword=	sb.toString();
			        if(hashedPassword.equals(oldPassword)) {

			        }*/
				if(password.equals(oldPassword)) {
					System.out.println("password is right");
					user.setPwdChangedOn(new Date());
					user.setPassword(newPassword);
					dao.update(user);
				}
			    else{
			    	throw new ApplicationException("Old Password entered is wrong",new Exception("Old Password entered is wrong"));
			    }
			        /*user.setPassword(otp);
				user.setPwdChangedOn(new Date());
				user.setResetPwdFlag("Y");*/
	//			dao.update(user);
			//	return true;
			}	//return false;

	}
	@Override
	public boolean processResetPassword(String emailId,String otp,String password) throws ApplicationException{
		try{
			String queryString = "select object(u) from User u where u.emailId = ?1 and u.password = ?2 and u.resetPwdFlag = ?3 and u.pwdChangedOn > ?4)";
			Date toCheckdateTime = org.apache.commons.lang3.time.DateUtils.addDays(new Date(), -3);
			Object[] params = { emailId,otp,"Y",toCheckdateTime };
			Collection<User> result = dao.findDynamicQuery(queryString, params);
			if(result!=null && !result.isEmpty()){
				User user = (User)result.iterator().next();
				user.setPassword(password);
				user.setPwdChangedOn(new Date());
				user.setResetPwdFlag("N");
				dao.update(user);
				return true;
			}else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Collection<User> getAdditionalUsers(long parentUserId) throws ApplicationException{
		try {
			String queryString = "select Object(u) from User u where u.parentUserId = ?1)";
			Object[] params = { parentUserId };
			return dao.findDynamicQuery(queryString, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("User Not found ", e);
		}
	}
public Collection<User> getSubscribedUsers(long parentUserId) throws ApplicationException {
	try {
		String queryString = "select Object(u),s.noOfUsers from User u,subscription s,SubscriptionUser su where u.parentUserId = ?1 and su.userId=u.parentUserId and su.subscriptionId=s.subscriptionId";
		Object[] params = { parentUserId };
		return dao.findDynamicQuery(queryString, params);
	} catch (Exception e) {
		e.printStackTrace();
		throw new ApplicationException("User Not found ", e);
	}
}
	@Override
	public User getUser(long id) throws ApplicationException {
		return (User) dao.read(User.class, id);
	}
	@Override
	public int deleteUser(String userId) throws ApplicationException {
		try{
		String queryString = "delete from User u where u.id= ? ";
		Object[] params = { new Long(userId) };
		int result = dao.deleteDynamic(queryString, params);
		System.out.println("result is "+result);
		return result;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new ApplicationException("Error while deleting payment method"+e.getMessage(), e);
		}
	}

	@Override
	public boolean checkSBUExist(String sbu) throws ApplicationException {
		try {
			String queryString = "select count(u.id) from User u where upper(u.sbu) = upper(?1)";
			Object[] params = { sbu };
			if(dao.findDynamicScalar(queryString, params)>0)
				return true;
			 else
				 return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("User Not found ", e);
		}
	}
}

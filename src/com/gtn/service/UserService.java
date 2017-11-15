package com.gtn.service;

import java.util.Collection;

import com.gtn.exception.ApplicationException;
import com.gtn.model.User;


public interface UserService {

	
	public User getUserByEmail(String emailId) throws ApplicationException;
	public void saveUser(User user) throws ApplicationException;
	public void updateUser(User user) throws ApplicationException;
	public boolean processForgotPassword(String userId) throws ApplicationException;
	public boolean processResetPassword(String emailId,String otp,String password) throws ApplicationException;
	public void changePassword(String email,String oldPassword,String newPassword) throws ApplicationException;
	public Collection<User> getAdditionalUsers(long parentUserId) throws ApplicationException;
	public Collection<User> getSubscribedUsers(long parentUserId) throws ApplicationException;

	public User getUser(long id) throws ApplicationException;
	public int deleteUser(String userId) throws ApplicationException;
	public boolean checkSBUExist(String sbu) throws ApplicationException;
	public String getOtpForUser(String emailId) throws ApplicationException;
	
}

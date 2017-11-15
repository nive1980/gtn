package com.gtn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gtn.exception.ApplicationException;
import com.gtn.model.User;
import com.gtn.service.UserService;

@Component
public class SecurityUserDetailService implements UserDetailsService {
	User user;
	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	//	User user;
		try {
			user = userService.getUserByEmail(userName);
			if (user == null)
				throw new UsernameNotFoundException("no user found with " + userName);
			return new SecurityUserDetail(user);
		} catch (ApplicationException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("no user found with " + userName);
		}
	}

}

package com.kisanmitra.service;

import com.kisanmitra.dao.AuthenticationDao;
import com.kisanmitra.dao.AuthenticationDaoImplementation;
import com.kisanmitra.dto.User;

public class AuthenticationServiceImplementation implements AuthenticationService {

	private AuthenticationDao authenticationDao=new AuthenticationDaoImplementation();  
	
	@Override
	public User loginUser(User user) {
		 
        User temp=authenticationDao.loginUser(user);
		
		return temp;
	}

	@Override
	public boolean createUser(User user) {
		boolean b =authenticationDao.createUser(user);
		
		return b;
	}

}

package com.revature.services;

import com.revature.exceptions.InvalidAccountException;
import com.revature.models.Credentials;
import com.revature.models.User;
import com.revature.repository.ERSUsersDao;
import com.revature.repository.ERSUsersDaoImpl;

public class LoginService {
	
	ERSUsersDao ersUsersDao = new ERSUsersDaoImpl(); 
	
	public boolean verifyCredentials(Credentials cred) {
		return ersUsersDao.verifyCredentials(cred);
		
	}
	
	public int getUserId(Credentials cred) {
		return ersUsersDao.getUserId(cred);
	}
	
	
	public User getUser(Credentials cred) {
		User user = null;
		try {
			user = ersUsersDao.getUser(cred);
		}
		catch(InvalidAccountException e) {
			System.out.println(e);
		}
		return user;
	}
	
	public User authenticateUser(Credentials cred) {
		if(verifyCredentials(cred)) {
			return getUser(cred);
			
		}
		else {
			return null;
		}
	}
	
}

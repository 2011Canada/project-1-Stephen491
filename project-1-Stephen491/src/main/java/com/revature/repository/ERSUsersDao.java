package com.revature.repository;

import com.revature.exceptions.InvalidAccountException;
import com.revature.models.Credentials;
import com.revature.models.User;

public interface ERSUsersDao {
	public boolean verifyCredentials(Credentials cred);
	public User getUser(int userid) throws InvalidAccountException;
	public int getUserId(Credentials cred);
	public User getUser(Credentials cred) throws InvalidAccountException;
}

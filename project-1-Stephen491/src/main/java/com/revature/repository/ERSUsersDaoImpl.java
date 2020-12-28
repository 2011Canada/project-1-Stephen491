package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.exceptions.InvalidAccountException;
import com.revature.models.Credentials;
import com.revature.models.User;
import com.revature.util.DatabaseConnectionPostgres;

public class ERSUsersDaoImpl implements ERSUsersDao{
	
	
	DatabaseConnectionPostgres dbConn = DatabaseConnectionPostgres.getConnectionFactory();
	Connection conn = dbConn.getConnection();

	public boolean verifyCredentials(Credentials cred) {
		String email = cred.getEmail();
		String password = cred.getPassword();
		String sql = "SELECT ers_password FROM reimbursement.ers_users WHERE user_email = ?;";
		try {
			
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setString(1,  email);
			st.executeQuery();
			ResultSet results = st.getResultSet();
			if(results.next()) {
				if(results.getString("ers_password").equals(password)) {
					return true;
				}
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		
	}

	public User getUser(int userid) throws InvalidAccountException{
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM reimbursement.ers_users WHERE ers_users_id= ?;";
		User user = null; 
		
		try {
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setInt(1, userid);
			st.executeQuery();
			ResultSet results = st.getResultSet();
			if(results.next()) {
				user = new User(results.getInt("ers_users_id"),results.getString("user_email"), 
						results.getString("ers_username"), results.getString("ers_password"), 
						results.getString("user_first_name"), results.getString("user_last_name"), 
						results.getInt("user_role_id"));
			}
			else {
				throw new InvalidAccountException();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
		
	}
	
	public User getUser(Credentials credentials) throws InvalidAccountException{
		String email = credentials.getEmail();
		String password = credentials.getPassword();
		
		String sql = "SELECT * FROM reimbursement.ers_users WHERE user_email= ? AND ers_password = ?;";
		User user = null; 
		
		try {
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setString(1, email);
			st.setString(2, password);
			st.executeQuery();
			ResultSet results = st.getResultSet();
			if(results.next()) {
				user = new User(results.getInt("ers_users_id"),results.getString("user_email"), 
						results.getString("ers_username"), results.getString("ers_password"), 
						results.getString("user_first_name"), results.getString("user_last_name"), 
						results.getInt("user_role_id"));
			}
			else {
				throw new InvalidAccountException();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
		
	}
	
	
	
	

	public int getUserId(Credentials cred) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

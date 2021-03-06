package com.revature.repository;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatusUpdate;
import com.revature.util.DatabaseConnectionPostgres;

public class ERSReimbursementsDaoImpl implements ERSReimbursementsDao{
	
	DatabaseConnectionPostgres dbConn = DatabaseConnectionPostgres.getConnectionFactory();
	Connection conn = dbConn.getConnection();
	
	
	
	
	public List<Reimbursement> getAllReimbursements() {
		String sql = "select reimb_id, reimb_amount, reimb_author, auth.ers_username as author_username, auth.user_first_name as author_first_name, auth.user_last_name as author_last_name,"
				+"auth.user_email as author_email , reimb_resolver, res.ers_username as resolver_username, res.user_first_name as resolver_first_name, res.user_last_name as resolver_last_name, res.user_email as resolver_email, "
				+"reimb_submitted, reimb_resolved, reimb_description, reimb_has_receipt, ers_reimbursement.reimb_status_id, ers_reimbursement.reimb_type_id, reimb_type, reimb_status "
				+"from reimbursement.ers_reimbursement "
				+"join reimbursement.ers_reimbursement_status on (ers_reimbursement.reimb_status_id=ers_reimbursement_status.reimb_status_id) "
				+"join reimbursement.ers_reimbursement_type on (ers_reimbursement.reimb_type_id=ers_reimbursement_type.reimb_type_id) "
				+"left join reimbursement.ers_users auth on (ers_reimbursement.reimb_author=auth.ers_users_id) "
				+"left join reimbursement.ers_users res on (ers_reimbursement.reimb_resolver=res.ers_users_id); ";
		ArrayList<Reimbursement> reimbursements = new ArrayList<>();
		try {
			PreparedStatement st = conn.prepareStatement(sql); 
			
			ResultSet results = st.executeQuery();
			
			while(results.next()) {
				Reimbursement reimb = new Reimbursement(); 

				reimb.setId(results.getInt("reimb_id"));
				reimb.setAmount(results.getDouble("reimb_amount"));
				reimb.setDescription(results.getString("reimb_description"));
				reimb.setDateSubmitted(results.getTimestamp("reimb_submitted"));
				reimb.setDateResolved(results.getTimestamp("reimb_resolved"));
				reimb.setAuthor(results.getInt("reimb_author"));
				reimb.setAuthor_username(results.getString("author_username"));
				reimb.setAuthor_firstName(results.getString("author_first_name"));
				reimb.setAuthor_lastName(results.getString("author_last_name"));
				reimb.setAuthor_email(results.getString("author_email"));
				reimb.setResolver(results.getInt("reimb_resolver"));
				reimb.setResolver_username(results.getString("resolver_username"));
				reimb.setResolver_firstName(results.getString("resolver_first_name"));
				reimb.setResolver_lastName(results.getString("resolver_last_name"));
				reimb.setResolver_email(results.getString("resolver_email"));
				reimb.setStatus_id(results.getInt("reimb_status_id"));
				reimb.setStatus(results.getString("reimb_status"));
				reimb.setType_id(results.getInt("reimb_type_id"));
				reimb.setType(results.getString("reimb_type"));
				reimb.setHasReceipt(results.getBoolean("reimb_has_receipt"));
				reimbursements.add(reimb);

			}
	
		}
		catch(PSQLException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return reimbursements;
		
		
	}
	
	
	
	
	public List<Reimbursement> getAllPendingReimbursements() {
		return null;
	}

	public List<Reimbursement> getCurrentUserReimbursements(int userid) {
		String sql = "select reimb_id, reimb_amount, reimb_author, auth.ers_username as author_username, auth.user_first_name as author_first_name, auth.user_last_name as author_last_name,"
				+"auth.user_email as author_email , reimb_resolver, res.ers_username as resolver_username, res.user_first_name as resolver_first_name, res.user_last_name as resolver_last_name, res.user_email as resolver_email, "
				+"reimb_submitted, reimb_resolved, reimb_description, reimb_has_receipt, ers_reimbursement.reimb_status_id, ers_reimbursement.reimb_type_id, reimb_type, reimb_status "
				+"from reimbursement.ers_reimbursement "
				+"join reimbursement.ers_reimbursement_status on (ers_reimbursement.reimb_status_id=ers_reimbursement_status.reimb_status_id) "
				+"join reimbursement.ers_reimbursement_type on (ers_reimbursement.reimb_type_id=ers_reimbursement_type.reimb_type_id) "
				+"left join reimbursement.ers_users auth on (ers_reimbursement.reimb_author=auth.ers_users_id) "
				+"left join reimbursement.ers_users res on (ers_reimbursement.reimb_resolver=res.ers_users_id) where reimb_author = ?; ";
		
		ArrayList<Reimbursement> reimbursements = new ArrayList<>();
		try {
			PreparedStatement st = conn.prepareStatement(sql); 
			
			st.setInt(1, userid);
			
			ResultSet results = st.executeQuery();
			
			while(results.next()) {
				Reimbursement reimb = new Reimbursement(); 

				reimb.setId(results.getInt("reimb_id"));
				reimb.setAmount(results.getDouble("reimb_amount"));
				reimb.setDescription(results.getString("reimb_description"));
				reimb.setDateSubmitted(results.getTimestamp("reimb_submitted"));
				reimb.setDateResolved(results.getTimestamp("reimb_resolved"));
				reimb.setAuthor(results.getInt("reimb_author"));
				reimb.setAuthor_username(results.getString("author_username"));
				reimb.setAuthor_firstName(results.getString("author_first_name"));
				reimb.setAuthor_lastName(results.getString("author_last_name"));
				reimb.setAuthor_email(results.getString("author_email"));
				reimb.setResolver(results.getInt("reimb_resolver"));
				reimb.setResolver_username(results.getString("resolver_username"));
				reimb.setResolver_firstName(results.getString("resolver_first_name"));
				reimb.setResolver_lastName(results.getString("resolver_last_name"));
				reimb.setResolver_email(results.getString("resolver_email"));
				reimb.setStatus_id(results.getInt("reimb_status_id"));
				reimb.setStatus(results.getString("reimb_status"));
				reimb.setType_id(results.getInt("reimb_type_id"));
				reimb.setType(results.getString("reimb_type"));
				reimb.setHasReceipt(results.getBoolean("reimb_has_receipt"));
				reimbursements.add(reimb);

			}
	
		}
		catch(PSQLException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return reimbursements;
	}

	public Reimbursement getReimbursement(int reimb_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean resolveReimbursement(int reimb_id, boolean decision) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Reimbursement> getReimbursementsByStatus(int status_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addReimbursement(Reimbursement reimb) {
		
		return false;
	}
	
	public boolean addReimbursement(double amount, String description, Timestamp dateSubmitted, int author, int status_id, int type_id, byte[] receipt, boolean hasReceipt) {
		String sql = "Insert into reimbursement.ers_reimbursement (reimb_amount, reimb_submitted, reimb_description, reimb_author, "
				+ "reimb_status_id, reimb_type_id, reimb_receipt, reimb_has_receipt) values (?, ?, ?, ?, ?, ?, ?, ?);";
		boolean Notcompleted = false;
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setDouble(1,  amount);
			st.setTimestamp(2, dateSubmitted);
			st.setString(3,  description);
			st.setInt(4, author);
			st.setInt(5, status_id);
			st.setInt(6, type_id);
			st.setBytes(7, receipt);
			st.setBoolean(8, hasReceipt);
			
			return (st.executeUpdate()==1);
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return Notcompleted;
		
		
	}
	
	public boolean updateReimbursementStatus(int resolver, int status, int reimb_id, Timestamp resolved) {
		String sql = "update reimbursement.ers_reimbursement set reimb_status_id = ?, reimb_resolver= ?, reimb_resolved = ? where reimb_id = ?;";
		
		boolean Notcompleted = false;
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, status);
			st.setInt(2, resolver);
			st.setTimestamp(3, resolved);
			st.setInt(4, reimb_id);
			
			return (st.executeUpdate()==1);
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return Notcompleted;
		

	}
	
	public byte[] getReceipt(int reimb_id) {
		String sql = "select reimb_receipt from reimbursement.ers_reimbursement where reimb_id = ?;"; 
		byte[] arrayResult = null;
		ResultSet results;
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, reimb_id);
			
			results = st.executeQuery();
			if(results.next()) {
				arrayResult = results.getBytes("reimb_receipt");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return arrayResult;
		}
		
		return arrayResult;
	}
 	

}

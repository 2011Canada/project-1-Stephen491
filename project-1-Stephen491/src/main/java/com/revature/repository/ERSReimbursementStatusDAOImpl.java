package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.util.DatabaseConnectionPostgres;

public class ERSReimbursementStatusDAOImpl implements ERSReimbursementStatusDAO{
	
	DatabaseConnectionPostgres dbConn = DatabaseConnectionPostgres.getConnectionFactory();
	Connection conn = dbConn.getConnection();
	
	public String getStatusName(int id) {
		String sql = "SELECT reimb_status FROM reimbursement.ers_reimbursement_status WHERE reimb_status_id = ?";
		String type = null;
		ResultSet results;
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			results = st.executeQuery();
			
			if(results.next()) {
				type = results.getString("reimb_status");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return type;
	}
	
}

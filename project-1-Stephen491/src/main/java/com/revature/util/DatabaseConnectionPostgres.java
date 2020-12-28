package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionPostgres {
	private static DatabaseConnectionPostgres cf = new DatabaseConnectionPostgres(1);
	
	public static DatabaseConnectionPostgres getConnectionFactory() {
		return cf;
	}
	
	private Connection conn[];
	
	private DatabaseConnectionPostgres(int connections) {
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		String url = System.getenv("DBURL");
		String user = System.getenv("DBUSER");
		String password= System.getenv("DBPASSWORD");
		
		try {
			this.conn = new Connection[connections];
			for(int i = 0; i<connections; i++) {
				Connection conn = DriverManager.getConnection(url, user, password);
				this.conn[i] = conn;
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public Connection getConnection() {
		return this.conn[0];
	}
	
}

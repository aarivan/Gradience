package org.csc540.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.csc540.session.Session;

public class Main {
	
	public static final Logger LOG = Logger.getLogger(Main.class);
	
	private static Connection conn;
	
	public static void main(String args[]) {
		try {
			conn = Session.getConnection();
			if (conn != null) {
				LOG.info("Connection to Database was successful!");
			} else {
				System.out.println("Connection is null");
			}
			
			/*String query = " select tablespace_name from user_tablespaces";
			Statement stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery(query);
			while (set.next()) {
				String data = set.getString("TABLESPACE_NAME");
				System.out.println(data);
			}*/
			
			Session.closeConnetion();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
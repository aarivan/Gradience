package org.csc540.session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.csc540.helper.DBReader;

public class Session {
	
	public static final Logger LOG = Logger.getLogger(Session.class);
	
	private static final String DB_DRIVER = "jdbc.driver";
	private static final String DB_CONNECTION = "jdbc.url";
	private static final String DB_USER = "jdbc.username";
	private static final String DB_PASSWORD = "jdbc.password";
	private static Connection dbConnection = null;
	private static boolean flag = true;
	
	static {
		if (dbConnection == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch(ClassNotFoundException e) {
				flag = false;
				e.printStackTrace();
			}
			
			LOG.info("Connecting to Database...");
			
			try {
				dbConnection = DriverManager.getConnection(DBReader.getPropertyValue(DB_CONNECTION),
						DBReader.getPropertyValue(DB_USER), 
						DBReader.getPropertyValue(DB_PASSWORD));
			} catch (SQLException e) {
				flag = false;
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getConnection() {
		if (flag) {
			return dbConnection;
		}
		return null;
	}
	
	public static void closeConnetion() {
		if (flag) {
			try {
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

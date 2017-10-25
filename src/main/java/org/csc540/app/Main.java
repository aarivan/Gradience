package org.csc540.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.csc540.pojo.Users;
import org.csc540.processor.LoginProcessor;
import org.csc540.session.Session;

public class Main {

	public static final Logger LOG = Logger.getLogger(Main.class);

	private static Connection conn;
	private static Scanner scanner;

	public static void main(String args[]) {
		try {
			conn = Session.getConnection();
			if (conn != null) {
				LOG.info("Connection to Database was successful!");
			} else {
				System.out.println("Connection is null");
			}

			/*
			 * String query = " select tablespace_name from user_tablespaces";
			 * Statement stmt = conn.createStatement(); ResultSet set =
			 * stmt.executeQuery(query); while (set.next()) { String data =
			 * set.getString("TABLESPACE_NAME"); System.out.println(data); }
			 */
			
			scanner = new Scanner(System.in);
			// Phase 1 - Login
			System.out.println("::: Start Page :::");
			System.out.println("Enter the options for following actions: \n1. Login \n2. Exit\nEnter your option:");
			Integer choice = scanner.nextInt();
			
			Users currUser = null;
			if (choice == 1) {
				System.out.println("Enter UserId:");
				String userId = scanner.next();
				System.out.println("Enter Password:");
				String password = scanner.next();
				currUser = LoginProcessor.login(conn, userId, password);
				System.out.println(currUser.toString());
			} else if (choice == 2) {
				if (currUser == null) {
					LOG.error("Trying to exit from the system without a user login\n");
					throw new Exception("No Current User to exit");
				}
				currUser = null;
			} else {
				LOG.info("Invalid Option");
			}
			

			Session.closeConnetion();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
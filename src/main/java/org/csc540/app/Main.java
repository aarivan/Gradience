package org.csc540.app;

import java.sql.Connection;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.csc540.helper.RoleConstants;
import org.csc540.pojo.Professor;
import org.csc540.pojo.Users;
import org.csc540.processor.LoginProcessor;
import org.csc540.processor.ProfessorProcessor;
import org.csc540.session.Session;

public class Main {

	final static Logger LOG = Logger.getLogger(Main.class);

	private static Connection conn;
	private static Scanner scanner;

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			conn = Session.getConnection();
			if (conn != null) {
				LOG.info("Connection to Database was successful!");
			} else {
				System.out.println("Connection is null");
			}

			scanner = new Scanner(System.in);
			
			// Phase 1 - Login
			int selection = 1;
			while(selection!=2){
				System.out.println("::: Start Page :::");
				System.out.println("Enter the options for following actions: \n1. Login \n2. Exit\nEnter your option:");
				selection = scanner.nextInt();

				Users currUser = null;
				switch(selection){
				case 1:
					System.out.println("Enter UserId:");
					String userId = scanner.next();
					System.out.println("Enter Password:");
					String password = scanner.next();
					currUser = LoginProcessor.login(conn, userId, password);

					// Control flow to respective Home Page based on Role
					if (currUser.getRole().equalsIgnoreCase(RoleConstants.PROFFESOR)) {
						// REDIRECT TO PROFESSOR HOME PAGE
						Professor currentProf = ProfessorProcessor.getprof(currUser.getUserId());
						ProfessorAccount.homePage(currentProf, scanner);
					} else if (currUser.getRole().equalsIgnoreCase(RoleConstants.STUDENT)) {
						StudentAccount.checkStudentRole(userId, scanner);
					} else {
						if (currUser.getRole() == null) {
							LOG.error("Null Role encountered for the User\n");
							throw new Exception("No role specified for the User");
						} else {
							LOG.error("Unknown Role encountered for the User\n");
							throw new Exception("Unknown Role specified for the User");
						}
					}
					break;
				case 2:
					LOG.error("\nExiting from the system!\n");
					selection = 2;
					currUser = null;
					System.exit(0);
					break;
				default:
					System.out.println("\n\nInvalid Option. Try again ..\n\n");
				}
			}

			Session.closeConnetion();
			System.exit(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
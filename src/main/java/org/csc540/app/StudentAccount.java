package org.csc540.app;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.csc540.pojo.Student;
import org.csc540.processor.StudentProcessor;

public class StudentAccount {
	public static final Logger LOG = Logger.getLogger(StudentAccount.class);

	public static void checkStudentRole(String userId, Scanner scanner) {
		try {
			// Redirected Student Page
			Student currStudent = StudentProcessor.isTA(userId);
			if (currStudent != null) {
				if (currStudent.isIs_ta()) {
					// Get the choice for the user
					selectHomePage(currStudent, scanner);
				} else {
					studentHomePage(currStudent, scanner);
				}
			} else {
				throw new RuntimeException("No student user found fro the User ID");
			}
		} catch (Exception e) {
			LOG.error("Exception while logging-in as Student/TA to the system", e);
		}
	}

	private static void selectHomePage(Student currStudent, Scanner scanner) {
		System.out.println("::: Student Home Page :::");
		System.out.println(
				"Enter the options for following actions: \n1. Log-in As TA \n2. Log-in As Student \n3. Log Out");

		Integer choice = scanner.nextInt();
		if (choice == 1) {
			TeachingAssistantAccount.TeachingAssistantHomePage(currStudent, scanner);
		} else if (choice == 2) {
			studentHomePage(currStudent, scanner);
		} else {
			LOG.info("Invalid Option");
		}
	}

	public static void studentHomePage(Student currStudent, Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("::: Student Home Page :::");
		System.out.println(
				"Enter the options for following actions: \n1. View/Edit Profile \n2. View Courses \n3. Log Out \nEnter your option:");
		Integer choice = scanner.nextInt();

		if (choice == 1) {
			System.out.println("### Press 0 to Go Back ###\n\n");
			System.out.println("1. First Name:" + currStudent.getF_name());
			System.out.println("2. Last Name:" + currStudent.getL_name());
			System.out.println("3. Employee ID:" + currStudent.getUserId());
			System.out.println("4. Address:" + currStudent.getAddress());
			System.out.println("5. Phone Number:" + currStudent.getPhone_number());
			System.out.println("6. Email:" + currStudent.getEmail());
		} else if (choice == 2) {

		} else if (choice == 3) {

		} else {
			LOG.info("Invalid Option");
		}
	}

}

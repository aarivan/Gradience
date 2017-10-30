package org.csc540.app;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.csc540.pojo.Student;

public class TeachingAssistantAccount {
	public static final Logger LOG = Logger.getLogger(TeachingAssistantAccount.class);

	public static void TeachingAssistantHomePage(Student studentUser, Scanner scanner) {

		System.out.println(
				"Enter the options for following actions: \n1. View Profile \n2. View Courses \n3. Add Courses \n4. Enroll \n5. Drop A Student \n6. Log Out \nEnter your option:");
		Integer choice = scanner.nextInt();

		if (choice == 1) {
			System.out.println("### Press 0 to Go Back ###\n\n");
			System.out.println("1. First Name:" + studentUser.getF_name());
			System.out.println("2. Last Name:" + studentUser.getL_name());
			System.out.println("3. Employee ID:" + studentUser.getUserId());
			System.out.println("4. Address:" + studentUser.getAddress());
			System.out.println("5. Phone Number:" + studentUser.getPhone_number());
			System.out.println("6. Email:" + studentUser.getEmail());
		} else if (choice == 2) {

		} else if (choice == 3) {

		} else if (choice == 4) {

		} else if (choice == 5) {

		} else if (choice == 6) {

		} else {
			LOG.info("Invalid Option");
		}

	}

}

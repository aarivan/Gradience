package org.csc540.app;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.csc540.helper.DBFieldConstants;
import org.csc540.pojo.Student;
import org.csc540.processor.StudentProcessor;

public class StudentAccount {
	public static final Logger LOG = Logger.getLogger(StudentAccount.class);

	public static void checkStudentRole(String userId, Scanner scanner) {
		try {
			// Redirected Student Page
			Student currStudent = StudentProcessor.isTA(userId);
			if (currStudent != null) {
				if (currStudent.isIs_ta().equalsIgnoreCase("T")) {
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
		int selection = 1;

		while (selection != 4) {
			System.out.println("::: Student Home Page :::");
			System.out.println(
					"Enter the options for following actions: \n1. View Profile \n2. Edit Profile\n3. View Courses \n4. Log Out \nEnter your option:");

			selection = scanner.nextInt();
			int newChoice;
			switch (selection) {
			case 1:
				viewProfile(currStudent);
				newChoice = scanner.nextInt();
				while (newChoice != 0) {
					System.out.println("Invalid choice!! Please enter 0 to go back to previous page!!");
					newChoice = scanner.nextInt();
				}
				break;
			case 2:
				editProfile(currStudent, scanner);
				break;
			case 3:
				viewStudentCourses(currStudent,scanner);
				break;
			default:
				break;
			}// end switch
		}
	}

	public static void viewProfile(Student currStudent) {
		System.out.println("### Press 0 to Go Back ###\n");
		System.out.println("1. First Name:" + currStudent.getF_name());
		System.out.println("2. Last Name:" + currStudent.getL_name());
		System.out.println("3. Employee ID:" + currStudent.getUserId());
		System.out.println("4. Address:" + currStudent.getAddress());
		System.out.println("5. Phone Number:" + currStudent.getPhone_number());
		System.out.println("6. Email:" + currStudent.getEmail());
		System.out.println("### Enter 0 to go back:");
	}

	public static void editProfile(Student currStudent, Scanner scanner) {
		viewProfile(currStudent);
		System.out.println("### Press the field name index to edit that field ###\nEnter your option:");
		int editChoice = scanner.nextInt();
		if (editChoice == 1) {
			System.out.println("Editting First name");
			System.out.println("Enter the new First name:");
			String new_F_name = scanner.next();
			currStudent.setF_name(new_F_name);
			try {
				StudentProcessor.updateStudentProfile(currStudent);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (editChoice == 2) {
			System.out.println("Editting Last name");
			System.out.println("Enter the new Last Name:");
			String new_L_name = scanner.next();
			currStudent.setL_name(new_L_name);
			try {
				StudentProcessor.updateStudentProfile(currStudent);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (editChoice == 3) {
			System.out.println("Editting StudentID");
			System.out.println("Enter the new Student ID:");
			String new_StudentID = scanner.next();
			currStudent.setUserId(new_StudentID);
			try {
				StudentProcessor.updateStudentProfile(currStudent);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (editChoice == 4) {
			System.out.println("Editting Address");
			scanner.nextLine(); 
			System.out.println("Enter the new Address:");
			String new_Address = scanner.nextLine();
			System.out.println("Entered the new Address:"+new_Address);
			currStudent.setAddress(new_Address);
			try {
				StudentProcessor.updateStudentProfile(currStudent);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (editChoice == 5) {
			System.out.println("Editting Phone Number");
			System.out.println("Enter the new Phone Number:");
			String new_phone_number = scanner.next();
			currStudent.setPhone_number(new_phone_number);
			try {
				StudentProcessor.updateStudentProfile(currStudent);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (editChoice == 6) {
			System.out.println("Editting Email");
			System.out.println("Enter the new Email:");
			String new_email = scanner.next();
			currStudent.setEmail(new_email);
			try {
				StudentProcessor.updateStudentProfile(currStudent);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (editChoice == 0) {
			
			studentHomePage(currStudent, scanner);
		}else {
			LOG.info("Invalid Option");
			
		}

	}
	
	public static void viewStudentCourses(Student currStudent, Scanner scanner) {
		System.out.println("\n\n");
		System.out.println("----------------------------");
		System.out.println("List of your Courses as Student:");
		System.out.println("----------------------------");
		StudentProcessor.getCoursesOfStudent(currStudent.getUserId() ,scanner);
	}
}

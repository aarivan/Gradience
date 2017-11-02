package org.csc540.app;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.csc540.pojo.Course;
import org.csc540.pojo.CourseEnrollment;
import org.csc540.pojo.Student;
import org.csc540.processor.ProfessorProcessor;
import org.csc540.processor.StudentProcessor;
import org.csc540.processor.TeachingAssistantProcessor;

public class TeachingAssistantAccount {
	public static final Logger LOG = Logger.getLogger(TeachingAssistantAccount.class);

	public static void TeachingAssistantHomePage(Student studentUser, Scanner scanner) {
		int selection = 1;
		List<CourseEnrollment> taCourseList = null;
		int i=0;
		String courseId;
		while (selection != 6) {
			System.out.println("\n\n::: TA Home Page :::");
			System.out.println(
					"Enter the options for following actions: \n1. View Profile \n2. View Courses \n3. Enroll a Student \n4. Drop a Student \n5. View Report \n6. Log Out \nEnter your option:");

			selection = scanner.nextInt();
			int newChoice;
			switch (selection) {
			case 1:
				// TA View Profile
				viewProfileTA(studentUser);
				newChoice = scanner.nextInt();
				while (newChoice != 0) {
					System.out.println("Invalid choice!! Please enter 0 to go back to previous page!!");
					newChoice = scanner.nextInt();
				}
				break;
			case 2:
				taCourseList = TeachingAssistantProcessor.getTACourses(studentUser.getUserId());
	
				System.out.println("\n\n");
				System.out.println("----------------------------");
				System.out.println("List of your Courses as TA:");
				System.out.println("----------------------------");
	
				i = 0;
				while (i < taCourseList.size()) {
					System.out.println((i + 1) + ". " + taCourseList.get(i).getCourseId());
					i++;
				}
	
				System.out.println("Please enter Course ID for which you would like to enter: \n\n");
				System.out.println("### Press 0 to Go Back ###\n\n");
				courseId = scanner.next();
				if(courseId == "0") {
					break;
				}
				else {
					i = 0;
					boolean course_found = false;
					while (i < taCourseList.size()) {
						if (taCourseList.get(i).getCourseId().equalsIgnoreCase(courseId)) {
							viewCourseDetails(taCourseList.get(i).getCourseId(), scanner);
							course_found = true;
						}
						i++;
					}
					if(!course_found) {
						System.out.println("Invalid Course Id Entered..Please try again..");
						break;
					}
					else {
						System.out.println("\n\n### Press 0 to Go Back ###\n\n");
						newChoice = scanner.nextInt();
						while (newChoice != 0) {
							System.out.println("\nInvalid choice!! Please enter 0 to go back to previous page!!\n\n");
							newChoice = scanner.nextInt();
						}
						break;
					}
				}
				
			case 3:
				// Enroll a Student
				taCourseList = TeachingAssistantProcessor.getTACourses(studentUser.getUserId());
	
				System.out.println("\n\n");
				System.out.println("----------------------------");
				System.out.println("List of your Courses as TA:");
				System.out.println("----------------------------");
	
				i = 0;
				while (i < taCourseList.size()) {
					System.out.println((i + 1) + ". " + taCourseList.get(i).getCourseId());
					i++;
				}
	
				System.out.println("Please enter Course ID for which you would like to enroll a student into: \n\n");
				System.out.println("### Press 0 to Go Back ###\n\n");
				courseId = scanner.next();
				if(courseId == "0") {
					break;
				}
				else {
					i = 0;
					boolean course_found = false;
					while (i < taCourseList.size()) {
						if (taCourseList.get(i).getCourseId().equalsIgnoreCase(courseId)) {
							enrollStudenttoCourse(scanner, taCourseList.get(i));
							course_found = true;
						}
						i++;
					}
					if(!course_found) {
						System.out.println("Invalid Course Id Entered..Please try again..");
						break;
					}
					else {
						System.out.println("\n\n### Press 0 to Go Back ###\n\n");
						newChoice = scanner.nextInt();
						while (newChoice != 0) {
							System.out.println("\nInvalid choice!! Please enter 0 to go back to previous page!!\n\n");
							newChoice = scanner.nextInt();
						}
						break;
					}
				}
			case 4:
				// Drop a student
				taCourseList = TeachingAssistantProcessor.getTACourses(studentUser.getUserId());
				
				System.out.println("\n\n");
				System.out.println("----------------------------");
				System.out.println("List of your Courses as TA:");
				System.out.println("----------------------------");
	
				i = 0;
				while (i < taCourseList.size()) {
					System.out.println((i + 1) + ". " + taCourseList.get(i).getCourseId());
					i++;
				}
	
				System.out.println("Please enter Course ID for which you would like to enroll a student into: \n\n");
				System.out.println("### Press 0 to Go Back ###\n\n");
				courseId = scanner.next();
				if(courseId == "0") {
					break;
				}
				else {
					i = 0;
					boolean course_found = false;
					while (i < taCourseList.size()) {
						if (taCourseList.get(i).getCourseId().equalsIgnoreCase(courseId)) {
							dropStudenttoCourse(scanner, taCourseList.get(i));
							course_found = true;
						}
						i++;
					}
					if(!course_found) {
						System.out.println("Invalid Course Id Entered..Please try again..");
						break;
					}
					else {
						System.out.println("\n\n### Press 0 to Go Back ###\n\n");
						newChoice = scanner.nextInt();
						while (newChoice != 0) {
							System.out.println("\nInvalid choice!! Please enter 0 to go back to previous page!!\n\n");
							newChoice = scanner.nextInt();
						}
						break;
					}
				}
			case 5:
				// View Report
				break;
			case 6:
				// logout
				selection = 6;
				break;
			default: System.out.println("Invalid Option! Try again.. ");
			}// end switch
		}
		Main.main(null);
	}

	public static void dropStudenttoCourse(Scanner scanner, CourseEnrollment courseEnrollment) {
		System.out.println("\n\nEnrollment for Course "+courseEnrollment.getCourseId()+":\n");
		System.out.println("Enter Student ID: ");
		String student_id = scanner.next();
		// The dropStudent method will catch DB exception, throw it and continue execution. Validation of Student ID done in DBMS directly.
		ProfessorProcessor.dropStudent(student_id,courseEnrollment.getCourseId());
		
	}

	public static void enrollStudenttoCourse(Scanner scanner, CourseEnrollment courseEnrollment) {
		System.out.println("\n\nEnrollment for Course "+courseEnrollment.getCourseId()+":\n");
		System.out.println("Enter Student ID: ");
		String student_id = scanner.next();
		// The enrollStudent method will catch DB exception, throw it and continue execution. Validation of Student ID done in DBMS directly.
		ProfessorProcessor.enrollStudent(student_id,courseEnrollment.getCourseId());
	}

	private static void viewProfileTA(Student studentUser) {
		System.out.println("\n\n");
		System.out.println("1. First Name: \t\t" + studentUser.getF_name());
		System.out.println("2. Last Name: \t\t" + studentUser.getL_name());
		System.out.println("3. Employee ID: \t" + studentUser.getUserId());
		System.out.println("4. Address: \t\t" + studentUser.getAddress());
		System.out.println("5. Phone Number: \t" + studentUser.getPhone_number());
		System.out.println("6. Email: \t\t" + studentUser.getEmail());
		System.out.println("\n\n");
		System.out.println("### Press 0 to Go Back ###\n\n");
	}

	public static void viewCourseDetails(String courseId, Scanner scanner) {
		Course courseDetails = null;
		courseDetails = TeachingAssistantProcessor.getCourseDetails(courseId);

		System.out.println("\n\n");

		System.out.println("------------------------------------");
		System.out.println("--------- " + courseId + " ---------");
		System.out.println("------------------------------------");

		System.out.println("\n\n");
		System.out.println("	Course Name :" + courseDetails.getCourseName());
		System.out.println("	Course Start Date:" + courseDetails.getCourseStartDate());
		System.out.println("    Course End Date:" + courseDetails.getCourseEndDate());

	}
}

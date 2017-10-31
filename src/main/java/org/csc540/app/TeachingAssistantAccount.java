package org.csc540.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.csc540.pojo.Course;
import org.csc540.pojo.CourseEnrollment;
import org.csc540.pojo.Student;
import org.csc540.processor.TeachingAssistantProcessor;
import org.csc540.session.Session;

public class TeachingAssistantAccount {
	public static final Logger LOG = Logger.getLogger(TeachingAssistantAccount.class);

	public static void TeachingAssistantHomePage(Student studentUser, Scanner scanner) {

		System.out.println(
				"Enter the options for following actions: \n1. View Profile \n2. View Courses \n3. Add Courses \n4. Enroll \n5. Drop A Student \n6. Log Out \nEnter your option:");
		Integer choice = scanner.nextInt();

		if (choice == 1) {
			System.out.println("### Press 0 to Go Back ###\n\n");
			System.out.println("1. First Name: \t\t" + studentUser.getF_name());
			System.out.println("2. Last Name: \t\t" + studentUser.getL_name());
			System.out.println("3. Employee ID: \t" + studentUser.getUserId());
			System.out.println("4. Address: \t\t" + studentUser.getAddress());
			System.out.println("5. Phone Number: \t" + studentUser.getPhone_number());
			System.out.println("6. Email: \t\t" + studentUser.getEmail());
		} else if (choice == 2) {
			System.out.println("### Press 0 to Go Back ###\n\n");
			List<CourseEnrollment> taCourseList = null;
			taCourseList = TeachingAssistantProcessor.getTACourses(studentUser.getUserId());

			System.out.println("\n\n");
			System.out.println("----------------------------");
			System.out.println("List of your Courses as TA:");
			System.out.println("----------------------------");

			int i = 0;
			while (i < taCourseList.size()) {
				System.out.println((i + 1) + ". " + taCourseList.get(i).getCourseId());
				i++;
			}

			System.out.println("Please enter Course ID for which you would like to enter:");
			String courseId = scanner.next();

			i = 0;
			boolean course_found = false;
			while (i < taCourseList.size()) {
				if (taCourseList.get(i).getCourseId().equalsIgnoreCase(courseId)) {
					viewCourseDetails(taCourseList.get(i).getCourseId(), scanner);
					course_found = true;
				}
				i++;
			}
			if (!course_found) {
				System.out.println("Invalid Course Id Entered..");
			}

		} else if (choice == 3) {

		} else if (choice == 4) {

		} else if (choice == 5) {

		} else if (choice == 6) {

		} else {
			LOG.info("Invalid Option");
		}
	}

	public static void viewCourseDetails(String courseId, Scanner scanner) {
		Course courseDetails = null;
		courseDetails = TeachingAssistantProcessor.getCourseDetails(courseId);

		System.out.println("\n\n");
		
		System.out.println("------------------------------------");
		System.out.println("--------- " + courseId + " ---------");
		System.out.println("------------------------------------");
		
		System.out.println("\n\n");
		System.out.println("(*) Course Name :" + courseDetails.getCourseName());
		System.out.println("(*) Course Start Date:" + courseDetails.getCourseStartDate());
		System.out.println("    Course End Date:" + courseDetails.getCourseEndDate());
		System.out.println("1.  View/Add Exercise");
		System.out.println("2.  View/Add TA");
		System.out.println("3.  Enroll/Drop a Student ");
		System.out.println("4.  View Report");
		
		System.out.println("Please enter the your choice for function to perform based on the above menu:");
		String choice = scanner.next();
	}
}

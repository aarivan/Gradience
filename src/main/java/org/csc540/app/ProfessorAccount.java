package org.csc540.app;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.csc540.pojo.Course;
import org.csc540.pojo.CourseEnrollment;
import org.csc540.pojo.Professor;
import org.csc540.processor.ProfessorProcessor;
import org.csc540.processor.StudentProcessor;
import org.csc540.processor.TeachingAssistantProcessor;

public class ProfessorAccount {

	public static final Logger LOG = Logger.getLogger(ProfessorAccount.class);

	public static ProfessorAccount homePage(Professor professorUser, Scanner scanner) {

		System.out.println(
				"Enter the options for following actions: \n1. View Profile \n2. View Courses \n3. Add Courses \n4. Enroll \n5. Drop A Student \n6. Log Out \nEnter your option:");
		Integer choice = scanner.nextInt();
		switch (choice) {
		case 1:
			System.out.println("### Press 0 to Go Back ###\n\n");
			System.out.println("1. First Name: \t\t" + professorUser.getF_name());
			System.out.println("2. Last Name: \t\t" + professorUser.getL_name());
			System.out.println("3. Professor ID: \t" + professorUser.getUserId());
			System.out.println("4. Address: \t\t" + professorUser.getAddress());
			System.out.println("5. Phone Number: \t" + professorUser.getPhone_number());
			System.out.println("6. Email: \t\t" + professorUser.getEmail());
			break;
		case 2:
			System.out.println("### Press 0 to Go Back ###\n\n");
			List<Course> profCourseList = null;
			profCourseList = ProfessorProcessor.getCourses(professorUser.getUserId());

			System.out.println("\n\n");
			System.out.println("----------------------------");
			System.out.println("List of your Courses as Professor:");
			System.out.println("----------------------------");

			int i = 0;
			while (i < profCourseList.size()) {
				System.out.println((i + 1) + ". " + profCourseList.get(i).getCourseId());
				i++;
			}

			System.out.println("Please enter Course ID for which you would like to enter:");
			String courseId = scanner.next();

			i = 0;
			boolean course_found = false;
			while (i < profCourseList.size()) {
				if (profCourseList.get(i).getCourseId().equalsIgnoreCase(courseId)) {
					viewCourseDetails(scanner, profCourseList.get(i));
					course_found = true;
				}
				i++;
			}
			if (!course_found) {
				System.out.println("Invalid Course Id Entered..");
			}
			break;
		case 3:
			// ADD COURSE
			break;
		case 4:
			System.out.println("\nENROLL STUDENT:");
			System.out.println("### Press 0 to Go Back ###\n\n");
			List<Course> profCourseList1 = null;
			profCourseList1 = ProfessorProcessor.getCourses(professorUser.getUserId());

			System.out.println("\n\n");
			System.out.println("----------------------------");
			System.out.println("List of your Courses as Professor:");
			System.out.println("----------------------------");

			int i1 = 0;
			while (i1 < profCourseList1.size()) {
				System.out.println((i1 + 1) + ". " + profCourseList1.get(i1).getCourseId());
				i1++;
			}

			System.out.println("Please enter Course ID for which you would like to enroll a student:");
			String courseId1 = scanner.next();

			i1 = 0;
			boolean course_found1 = false;
			while (i1 < profCourseList1.size()) {
				if (profCourseList1.get(i1).getCourseId().equalsIgnoreCase(courseId1)) {
					enrollStudenttoCourse(scanner, profCourseList1.get(i1));
					course_found1 = true;
				}
				i1++;
			}
			if (!course_found1) {
				System.out.println("Invalid Course Id Entered..");
			}
			break;
		case 5:
			System.out.println("\nDROP STUDENT:");
			System.out.println("### Press 0 to Go Back ###\n\n");
			List<Course> profCourseList11 = null;
			profCourseList11 = ProfessorProcessor.getCourses(professorUser.getUserId());

			System.out.println("\n\n");
			System.out.println("----------------------------");
			System.out.println("List of your Courses as Professor:");
			System.out.println("----------------------------");

			int i11 = 0;
			while (i11 < profCourseList11.size()) {
				System.out.println((i11 + 1) + ". " + profCourseList11.get(i11).getCourseId());
				i11++;
			}

			System.out.println("Please enter Course ID for which you would like to enroll a student:");
			String courseId11 = scanner.next();

			i11 = 0;
			boolean course_found11 = false;
			while (i11 < profCourseList11.size()) {
				if (profCourseList11.get(i11).getCourseId().equalsIgnoreCase(courseId11)) {
					dropStudentfromCourse(scanner, profCourseList11.get(i11));
					course_found11 = true;
				}
				i11++;
			}
			if (!course_found11) {
				System.out.println("Invalid Course Id Entered..");
			}
			break;
		default:
			System.out.println("DEFAULT");
		}
		return null;
	}

	public static void dropStudentfromCourse(Scanner scanner, Course course) {
		// TODO Auto-generated method stub
		Boolean flag;
		System.out.println("\n\n");
		System.out.println("Enter Student ID: ");
		String student_id = scanner.next();
		System.out.println("Enter Student First-Name: ");
		String student_fname = scanner.next();
		System.out.println("Enter Student Last-Name: ");
		String student_lname = scanner.next();
		
		flag = StudentProcessor.validatingStudentDetails(student_id, student_fname, student_lname);
		
		if(flag){
			// Allow Insertion - enroll a student
			ProfessorProcessor.dropStudent(student_id,course.getCourseId());
		}
		else {
			// Disallow enrollment
			System.out.println("Invalid Student details provided! Please try again.. ");
		}
		
	}

	public static void enrollStudenttoCourse(Scanner scanner, Course course) {
		// TODO Auto-generated method stub
		Boolean flag;
		System.out.println("\n\n");
		System.out.println("Enter Student ID: ");
		String student_id = scanner.next();
		System.out.println("Enter Student First-Name: ");
		String student_fname = scanner.next();
		System.out.println("Enter Student Last-Name: ");
		String student_lname = scanner.next();
		
		flag = StudentProcessor.validatingStudentDetails(student_id, student_fname, student_lname);
		
		if(flag){
			// Allow Insertion - enroll a student
			ProfessorProcessor.enrollStudent(student_id,course.getCourseId());
		}
		else {
			// Disallow enrollment - try again - enter valid student details
			System.out.println("Invalid Student details provided! Please try again.. ");
		}
		
	}

	public static void viewCourseDetails(Scanner scanner, Course courseDetails) {
		// TODO Auto-generated method stub
		System.out.println("\n\n");

		System.out.println("------------------------------------");
		System.out.println("--------- " + courseDetails.getCourseId() + " ---------");
		System.out.println("------------------------------------");

		System.out.println("\n\n");
		System.out.println("(*) Course Name :" + courseDetails.getCourseName());
		System.out.println("(*) Course Start Date:" + courseDetails.getCourseStartDate());
		System.out.println("    Course End Date:" + courseDetails.getCourseEndDate());
		System.out.println("    Course Level:" + courseDetails.getCourseLevel());
		System.out.println("    Number of Students Enrolled:" + courseDetails.getStudentsEnrollement());
		System.out.println("    Maximum students allowed:" + courseDetails.getMaxEnrollment());
		System.out.println("1.  View/Add Exercise");
		System.out.println("2.  View/Add TA");
		System.out.println("3.  Enroll/Drop a Student ");
		System.out.println("4.  View Report");

		System.out.println("Please enter the your choice for function to perform based on the above menu:");
		String choice = scanner.next();
	}

}

package org.csc540.app;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.csc540.pojo.Course;
import org.csc540.pojo.CourseEnrollment;
import org.csc540.pojo.HomeWork;
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
					"Enter the options for following actions: \n1. View Profile \n2. View Courses \n3. Enroll a Student \n4. Drop a Student \n5. View HW \n6. Add HW \n7. View Report \n8. Log Out \nEnter your option:");

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
				// View HW
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

				System.out.println("Please enter Course ID for which you would like view HW Excercise/s:\n\n");
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
							viewHWExerciseTA( taCourseList.get(i),scanner,studentUser);
							course_found = true;
						}
						i++;
					}
					if (!course_found) {
						System.out.println("Invalid Course Id Entered..");
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
				
			case 6:
				// ADD HW
				System.out.println("\nADD HW Excercise:");
				System.out.println("### Press 0 to Go Back ###\n\n");

				System.out.println("\n\n");
				System.out.println("----------------------------");
				System.out.println("Please enter the details of the new HW to be added:");
				System.out.println("----------------------------");
				System.out.println("\n");
				System.out.println("Enter HW ID: ");
				String hw_id = scanner.next();
				System.out.println("Enter HW Name: ");
				scanner.nextLine();
				String hwName = scanner.nextLine();
				System.out.println("Enter Start Date: ");
				String hwStartDate = scanner.next();
				System.out.println("Enter End Date: ");
				String hwEndDate = scanner.next();
				System.out.println("Enter Dificulty Level [1-5]: ");
				int  diffLevel = scanner.nextInt();
				System.out.println("Enter Maximum Number of tries allowed: ");
				int maxTries = scanner.nextInt();
				System.out.println("Enter Scoring policy 'latest','max', 'avg': ");
				String scoring_policy = scanner.next();
				System.out.println("Enter Correct points: ");
				int correct_pt = scanner.nextInt();
				System.out.println("Enter Penalty points: ");
				int penalty_pt = scanner.nextInt();
				System.out.println("Enter Topic ID: ");
				String topicId = scanner.next();
				System.out.println("Enter Course ID : ");
				String course_Id = scanner.next();
				System.out.println("Enter HW Type : ");
				String hw_type = scanner.next();
				
				try {
					ProfessorProcessor.addHomeWork(hw_id,course_Id,topicId,hwName, maxTries, hwStartDate,
							hwEndDate,correct_pt,penalty_pt,scoring_policy,diffLevel,hw_type);
				} catch (SQLException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			case 7:
				// View Report
				break;
			case 8:
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
		public static void viewHWExerciseTA( CourseEnrollment course, Scanner scanner,Student studentUser)  {
			// TODO Auto-generated method stub
			List<HomeWork> listHWExcercise = ProfessorProcessor.getHWExcerciseForCourse(course.getCourseId());
			System.out.println("\n\n");
			System.out.println("----------------------------");
			System.out.println("List of HW Excercise/s for the course:");
			System.out.println("----------------------------");
	
			int i = 0;
			while (i < listHWExcercise.size()) {
				System.out.println((i + 1) + ". HW ID: " + listHWExcercise.get(i).getHw_id()+"HW Name: "+listHWExcercise.get(i).getHW_name());
				i++;
			}
			System.out.println("Please enter HW ID for which you would like view HW Excercise/s:\n\n");
			
			String HWId = scanner.next();
			
			List<HomeWork> listHWDetails = ProfessorProcessor.getHWExcerciseDetails(HWId);
				i = 0;
				boolean HW_found = false;
				while (i < listHWDetails.size()) {
					if (listHWDetails.get(i).getHw_id().equalsIgnoreCase(HWId)) {
						System.out.println("------------------------------------");
						System.out.println("--------- " + listHWDetails.get(i).getHw_id() + " ---------");
						System.out.println("------------------------------------");
	
						System.out.println("\n\n");
						System.out.println("(1) HW Name :" + listHWDetails.get(i).getHW_name());
						System.out.println("(2) HW Start Date:" + listHWDetails.get(i).getHw_st_date());
						System.out.println("(3) HW End Date:" + listHWDetails.get(i).getHw_end_date());
						System.out.println("(4) HW Course ID:" + listHWDetails.get(i).getCourse_id());
						System.out.println("(5) HW Maximum Tries Allowed:" + listHWDetails.get(i).getMax_no_of_tries());
						System.out.println("(6) HW Dificulty Level:" + listHWDetails.get(i).getDiff_level());
						System.out.println("(7) HW Scoring Policy:" + listHWDetails.get(i).getScore_policy());
						System.out.println("(8) HW Topic ID:" + listHWDetails.get(i).getTopic_id());
						System.out.println("(9) HW Correct points:" + listHWDetails.get(i).getCorrect_pts());
						System.out.println("(10) HW Penalty points:" + listHWDetails.get(i).getPenalty_pts());
						System.out.println("### Press 0 to Go Back ###\n\n");
						System.out.println("### Press 1 to Go Back to TA Menu ###\n\n");
						int editHWChoice = scanner.nextInt();
						if(editHWChoice==0) {
							viewHWExerciseTA(course, scanner,studentUser);
						HW_found = true;
					}else if(editHWChoice==1) {
						TeachingAssistantHomePage(studentUser, scanner);
				}
					i++;
				
				if (!HW_found) {
					System.out.println("Invalid HW Id Entered..");
					viewHWExerciseTA(course, scanner,studentUser);
					break;
				}
					
				}
			
		}
	}
}

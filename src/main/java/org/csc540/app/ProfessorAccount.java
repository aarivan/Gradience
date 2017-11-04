package org.csc540.app;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.csc540.pojo.Course;
import org.csc540.pojo.CourseEnrollment;
import org.csc540.pojo.Professor;
import org.csc540.pojo.Topic;
import org.csc540.processor.ProfessorProcessor;
import org.csc540.processor.StudentProcessor;
import org.csc540.processor.TeachingAssistantProcessor;

public class ProfessorAccount {

	public static final Logger LOG = Logger.getLogger(ProfessorAccount.class);

	public static ProfessorAccount homePage(Professor professorUser, Scanner scanner) {
		int selection = 1;
		List<Course> profCourseList = null;
		String courseId;
		int i = 0;
		while (selection != 11) {
			System.out.println("\n\n::: Instructor Home Page :::");
			System.out.println(
					"Enter the options for following actions: \n1. View Profile \n2. View Courses \n3. Add Courses \n4. Enroll a Student \n5. Drop a Student \n6. View Report \n7. Setup TA \n8.View/AddExercises \n9. Search/Add​ ​questions​ ​to​ ​Question​ ​Bank \n10. Add/Remove Questions from Exercises \n11. Log Out \nEnter your option:");
			selection = scanner.nextInt();
			int newChoice;
			switch (selection) {
			case 1:
				System.out.println("\n\n");
				System.out.println("1. First Name: \t\t" + professorUser.getF_name());
				System.out.println("2. Last Name: \t\t" + professorUser.getL_name());
				System.out.println("3. Professor ID: \t" + professorUser.getUserId());
				System.out.println("4. Address: \t\t" + professorUser.getAddress());
				System.out.println("5. Phone Number: \t" + professorUser.getPhone_number());
				System.out.println("6. Email: \t\t" + professorUser.getEmail());
				System.out.println("\n\n");
				System.out.println("### Press 0 to Go Back ###\n\n");
				newChoice = scanner.nextInt();
				while (newChoice != 0) {
					System.out.println("Invalid choice!! Please enter 0 to go back to previous page!!");
					newChoice = scanner.nextInt();
				}
				break;
			case 2:
				// VIEW COURSES
				profCourseList = ProfessorProcessor.getCourses(professorUser.getUserId());
				System.out.println("\n\n");
				System.out.println("----------------------------");
				System.out.println("List of your Courses as Instructor:");
				System.out.println("----------------------------");

				i = 0;
				while (i < profCourseList.size()) {
					System.out.println((i + 1) + ". " + profCourseList.get(i).getCourseId());
					i++;
				}

				System.out.println("Please enter Course ID for which you would like to enter:\n\n");
				System.out.println("### Press 0 to Go Back ###\n\n");
				courseId = scanner.next();
				if (courseId == "0") {
					break;
				} else {
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
						break;
					} else {
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
				// ADD COURSE
				System.out.println("\nADD COURSE:");
				System.out.println("### Press 0 to Go Back ###\n\n");

				System.out.println("\n\n");
				System.out.println("----------------------------");
				System.out.println("Please enter the details of the new course to be added:");
				System.out.println("----------------------------");

				Boolean flag;
				System.out.println("\n\n");
				System.out.println("Enter Course ID: ");
				String course_id = scanner.next();
				System.out.println("Enter Course Name: ");
				scanner.nextLine();
				String courseName = scanner.nextLine();
				System.out.println("Enter Start Date: ");
				String courseStartDate = scanner.next();
				System.out.println("Enter End Date: ");
				String courseEndDate = scanner.next();
				System.out.println("Enter Course Level: ");
				String courseLevel = scanner.next();
				System.out.println("Enter Class Size: ");
				String courseSize = scanner.next();

				flag = validatingCourseDetails(course_id, courseName, courseStartDate, courseEndDate);

				if (flag) {
					ProfessorProcessor.addCourse(course_id, courseName, courseStartDate, courseEndDate,
							professorUser.getUserId(), courseLevel, 0, courseSize);
				} else {
					System.out.println("Invalid Course details provided! Please try again.. ");
				}

				break;
			case 4:
				// ENROLL STUDENT TO COURSE
				profCourseList = ProfessorProcessor.getCourses(professorUser.getUserId());
				System.out.println("\n\n");
				System.out.println("----------------------------");
				System.out.println("List of your Courses as Instructor:");
				System.out.println("----------------------------");

				i = 0;
				while (i < profCourseList.size()) {
					System.out.println((i + 1) + ". " + profCourseList.get(i).getCourseId());
					i++;
				}

				System.out.println("Please enter Course ID for which you would like to enter:\n\n");
				System.out.println("### Press 0 to Go Back ###\n\n");
				courseId = scanner.next();
				if (courseId == "0") {
					break;
				} else {
					i = 0;
					boolean course_found = false;
					while (i < profCourseList.size()) {
						if (profCourseList.get(i).getCourseId().equalsIgnoreCase(courseId)) {
							enrollStudenttoCourse(scanner, profCourseList.get(i));
							course_found = true;
						}
						i++;
					}
					if (!course_found) {
						System.out.println("Invalid Course Id Entered..");
						break;
					} else {
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
				// DROP A STUDENT FROM A COURSE
				profCourseList = ProfessorProcessor.getCourses(professorUser.getUserId());
				System.out.println("\n\n");
				System.out.println("----------------------------");
				System.out.println("List of your Courses as Instructor:");
				System.out.println("----------------------------");

				i = 0;
				while (i < profCourseList.size()) {
					System.out.println((i + 1) + ". " + profCourseList.get(i).getCourseId());
					i++;
				}

				System.out.println("Please enter Course ID for which you would like to enter:\n\n");
				System.out.println("### Press 0 to Go Back ###\n\n");
				courseId = scanner.next();
				if (courseId == "0") {
					break;
				} else {
					i = 0;
					boolean course_found = false;
					while (i < profCourseList.size()) {
						if (profCourseList.get(i).getCourseId().equalsIgnoreCase(courseId)) {
							dropStudentfromCourse(scanner, profCourseList.get(i));
							course_found = true;
						}
						i++;
					}
					if (!course_found) {
						System.out.println("Invalid Course Id Entered..");
						break;
					} else {
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
				// VIEW REPORT

				break;
			case 7:
				// SETUP TA

				break;
			case 8:
				// View/AddExercises

				break;
			case 9:
				// Search/Add​ ​questions​ ​to​ ​Question​ ​Bank
				System.out.println("\n::: ADD QUESTION TO QUESTION BANK :::");
				System.out.println("### Press 0 to Go Back ###\n\n");

				profCourseList = ProfessorProcessor.getCourses(professorUser.getUserId());
				System.out.println("\n\n");
				System.out.println("----------------------------");
				System.out.println("List of your Courses as Instructor:");
				System.out.println("----------------------------");

				i = 0;
				while (i < profCourseList.size()) {
					System.out.println((i + 1) + ". " + profCourseList.get(i).getCourseId());
					i++;
				}

				System.out.println("Please enter Course ID for which you would like to enter:\n\n");
				System.out.println("### Press 0 to Go Back ###\n\n");
				courseId = scanner.next();

				System.out.println("----------------------------");
				System.out.println("List of Topic IDs:");
				System.out.println("----------------------------");

				List<Topic> profCourseTopicList = null;
				profCourseTopicList = ProfessorProcessor.getCourseTopics(courseId);

				i = 0;
				while (i < profCourseTopicList.size()) {
					System.out.println((i + 1) + ". " + profCourseTopicList.get(i).getTopicId() + "-----"
							+ profCourseTopicList.get(i).getTopicName());
					i++;
				}

				System.out.println("Please enter the Topic ID to which you would like to add:");
				System.out.println("### Press 0 to Go Back ###\n\n");
				String topicId = scanner.next();

				System.out.println("Please enter the Question ID:");
				String quesId = scanner.next();

				System.out.println("Please enter the Question type(P/NP) [P - Parameterized, NP - Non Parameterized]:");
				String quesType = scanner.next();
				

				System.out.println("Please enter the Question text:");
				scanner.nextLine();
				String quesText = scanner.nextLine();
				
				
				System.out.println("Please enter the Question explanation:");

				String quesExp = scanner.nextLine();
				scanner.nextLine();

				System.out.println("Please enter the difficulty level (1-5):");
				int diffLevel = scanner.nextInt();

				System.out.println("Please enter the Hint:");
				scanner.nextLine();
				String hint = scanner.nextLine();
				
				ProfessorProcessor.addQuesToBank(quesId, topicId, quesText, quesExp, diffLevel, hint, quesType);

				if (quesType.equals("NP")) {
					addAns(quesId, scanner);
				} else {
					
						System.out.println("Enter the number of parameters: ");
						int param_no = scanner.nextInt();
						scanner.nextLine();
						
						System.out.println("Enter the number of values: ");
						int val = scanner.nextInt();
						scanner.nextLine();
			
						for(int j = 0; j<param_no; j++)
						{
							
							System.out.println("Enter the parameter name: ");
							String param_name = scanner.nextLine();
							scanner.nextLine();
							
							for( int k = 0; k<val; k++)
							{
								System.out.println("Enter the parameter value " + (k+1) + ":");
								String param_val = scanner.nextLine();
								scanner.nextLine();
								ProfessorProcessor.addParamQues(quesId, Integer.toString(j+1), Integer.toString(k+1), param_name, param_val);
							}
						}
						
						addAns(quesId, scanner);
					}
				
				

				break;
			case 10:
				// Add/Remove Questions from Exercises

				break;
			case 11:
				// Logout
				selection = 11;
				break;
			default:
				System.out.println("Invalid Option! Please try again ..");
			}
		}
		Main.main(null);
		return null;
	}

	public static void addAns(String quesId, Scanner scanner) {
		
		int ans_id = 1;

		String ch = "Y";
		do {
			System.out.println("Please enter the answer choice for the question:");
			String ans = scanner.nextLine();
			scanner.nextLine();

			System.out.println("Is this the correct answer? (T/F)");
			String is_correct = scanner.next();

			ProfessorProcessor.addAnswer(Integer.toString(ans_id), quesId, is_correct, ans);

			ans_id++;
			System.out.println("Do you want to add another answer? (Y/N)");
			ch = scanner.next();

		} while (ch.equals("Y"));
	}

	public static Boolean validatingCourseDetails(String course_id, String courseName, String courseStartDate,
			String courseEndDate) {

		LOG.info("Function to validate the entered course details..");

		Boolean flag_course = false;
		Boolean flag_date = false;

		flag_course = ProfessorProcessor.checkCourseDetails(course_id, courseName);

		// CHECK THE COURSE_ID , COURSE_NAME TO BE UNIQUE
		// CHECK THAT THE START AND END DATES ARE VALID

		return true;
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

		if (flag) {
			// Allow Insertion - enroll a student
			ProfessorProcessor.dropStudent(student_id, course.getCourseId());
		} else {
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

		if (flag) {
			// Allow Insertion - enroll a student
			ProfessorProcessor.enrollStudent(student_id, course.getCourseId());
		} else {
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

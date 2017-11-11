package org.csc540.app;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.csc540.helper.DBFieldConstants;
import org.csc540.pojo.Answer;
import org.csc540.pojo.Attempts;
import org.csc540.pojo.Attempts_info;
import org.csc540.pojo.HomeWork;
import org.csc540.pojo.Student;
import org.csc540.processor.ProfessorProcessor;
import org.csc540.processor.StudentProcessor;
import java.util.Random;

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
				throw new RuntimeException("No student user found for the User ID");
			}
		} catch (Exception e) {
			LOG.error("Exception while logging-in as Student/TA to the system", e);
		}
	}

	private static void selectHomePage(Student currStudent, Scanner scanner) {
		int newselection = 1;
		while (newselection != 3) {
			System.out.println("\n\n::: Student/TA Selection Page :::");
			System.out.println(
					"Enter the options for following actions: \n1. Log-in As TA \n2. Log-in As Student \n3. Log Out\n\n");

			newselection = scanner.nextInt();
			switch (newselection) {
			case 1:
				TeachingAssistantAccount.TeachingAssistantHomePage(currStudent, scanner);
				break;
			case 2:
				studentHomePage(currStudent, scanner);
				break;
			case 3:
				newselection = 3;
				break;
			default:
				System.out.println("\n\nInvalid Option. Please try again ..");
			}
		}
		Main.main(null);
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
				viewStudentCourses(currStudent, scanner);
				// Add code here for Current HWs and Past HWs for this course.

				break;
			case 4:
				selection = 4;
				break;
			default:
				System.out.println("Invalid Option! Try again.. ");
			}// end switch
		}
		Main.main(null);
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
			System.out.println("Entered the new Address:" + new_Address);
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
		} else {
			LOG.info("Invalid Option");

		}

	}

	public static void viewStudentCourses(Student currStudent, Scanner scanner) {
		System.out.println("\n\n");
		System.out.println("----------------------------");
		System.out.println("List of your Courses as Student:");
		System.out.println("----------------------------");
		StudentProcessor.getCoursesOfStudent(currStudent.getUserId(), scanner);
		System.out.println("Please enter Course ID for which you would like to view:");
		String courseId = scanner.next();
		viewHWForCourse(courseId, scanner, currStudent);
	}

	public static void viewHWForCourse(String courseId, Scanner scanner, Student currStudent) {
		String user_id = currStudent.getUserId();
		System.out.println("######Display HW for course: " + courseId);
		System.out.println("\n 1.Current Open HWs \n2. Past HWs");
		System.out.println("Enter 1 for Current HWs and 2 For Past HWs:");
		int HWChoice = scanner.nextInt();
		int choice = 1;
		String q_id;
		int a_id;
		if (HWChoice == 1) {
			List<HomeWork> listHW = StudentProcessor.getOpenHW(courseId);
			System.out.println("###### Open HWs #######");
			for (int i = 0; i < listHW.size(); i++) {
				System.out.println("HW ID: " + listHW.get(i).getHw_id() + " HW Name: " + listHW.get(i).getHW_name());
			}
			System.out.println(listHW.get(0));
			if (listHW.size() > 0) {
				System.out.println("Enter the HW ID: ");
				String hw_id = scanner.next();
				System.out.println(
						"\n 1. View Completed Attempts \n2.Incomplete/New Attempts \n\n Press any other number to exit!\n");
				int new_choice = scanner.nextInt();
				if (new_choice == 2) {
					// Getting incomplete attempt
					a_id = StudentProcessor.getAttemptsInfo(hw_id, user_id, courseId);
					// Now we have one attempt_id that is incomplete (or) a null
					// attempt_id
					System.out.println("Value received Line 231");
					if (a_id > 0) {
						// Incomplete attempt
						// call retreive_question()
						System.out.println("Line 235");
						q_id = StudentProcessor.retrieveQuestion(a_id, hw_id, user_id, courseId);
						if (q_id.equals("-1") || q_id.equals("0")) {
							System.out.println("You have completed this Attempt!");
							studentHomePage(currStudent, scanner);
						} else {
							// Present the user with question details plus 4
							// options
							takeTest(a_id, hw_id, user_id, courseId, q_id, scanner, currStudent);
						}
					} else {
						// New attempt
						// Call add_atttempt() and then retreive_question()
						System.out.println("Line 249");
						a_id = StudentProcessor.addAttempt(hw_id, user_id, courseId);
						if (a_id > 0) {
							q_id = StudentProcessor.retrieveQuestion(a_id, hw_id, user_id, courseId);
							if (q_id.equals("-1") || q_id.equals("0")) {
								System.out.println("You have completed this Attempt!");
								studentHomePage(currStudent, scanner);
							} else {
								// Present the user with question details plus 4
								// options
								takeTest(a_id, hw_id, user_id, courseId, q_id, scanner, currStudent);
							}
						} else {
							System.out.println("Sorry! You have exhausted all your attempts for this Homework!! \n\n");
						}
					}

				} else if (new_choice == 1) {
					// Get completed attempts
					List<HomeWork> listHW1 = StudentProcessor.getPastHW(courseId);
					System.out.println("###### Past HWs #######");
					for (int i = 0; i < listHW1.size(); i++) {
						System.out.println(
								"HW ID: " + listHW1.get(i).getHw_id() + " HW Name: " + listHW1.get(i).getHW_name());
					}
					System.out.println("Enter the HW ID to view that HW: ");
					String hw_id1 = scanner.next();

					List<HomeWork> listHWDetail = ProfessorProcessor.getHWExcerciseDetails(hw_id1);
					int total_score = StudentProcessor.getTotalscoreFromScoringPolicy(hw_id1, user_id);
					int studentAttemptCount = StudentProcessor.getCountStudentAttempt(hw_id1, user_id);
					List<Attempts> listAttempts = StudentProcessor.getCompleteAttemptsdetails(hw_id1, user_id);
					System.out.println("\n***********Details for your HW: ********" + listHWDetail.get(0).getHw_id()
							+ "HW NAME: " + listHWDetail.get(0).getHW_name());
					System.out.println("HW Start Date " + listHWDetail.get(0).getHw_st_date());
					System.out.println("HW Start Date " + listHWDetail.get(0).getHw_end_date());
					System.out.println("HW Scoring policy " + listHWDetail.get(0).getScore_policy());
					System.out.println("Total score based on the scoring policy" + total_score);
					System.out.println("HW Attempts allowed " + listHWDetail.get(0).getMax_no_of_tries());
					System.out.println("Your number of attempts " + studentAttemptCount);
					if (listAttempts.size() != 0) {
						for (int i = 0; i <= listAttempts.size() - 1; i++) {
							System.out.println("\n******** DISPLAY DETAILS FOR THIS ATTEMPT: ************"
									+ listAttempts.get(i).getAttempt_id());
							System.out.println("Question id:  " + listAttempts.get(i).getQues_id());
							System.out.println("Question Text:  " + listAttempts.get(i).getQues_text());
							System.out.println("Answer you chose: " + listAttempts.get(i).getAns_id());
							System.out.println("Answer Value: " + listAttempts.get(i).getValue_id());
							System.out.println("Score that question: " + listAttempts.get(i).getScore_per_ques());
							if (listAttempts.get(i).getScore_per_ques() <= 0) {
								System.out.println("Your answer is : INCORRECT");
							} else {
								System.out.println("Your answer is : CORRECT");
							}
						}
					}
				} else {
					// Exit
					studentHomePage(currStudent, scanner);
				}

			} else {
				System.out.println("\nNo open Homeworks right now! \n\n Press 0 to exit!");
				choice = scanner.nextInt();
				while (choice != 0) {
					System.out.println("\n\nInvalid choice! Press 0 to exit!\n");
					choice = scanner.nextInt();
				}
			}
			if (choice == 0) {
				studentHomePage(currStudent, scanner);
			}

		} else if (HWChoice == 2) {
			List<HomeWork> listHW = StudentProcessor.getPastHW(courseId);
			System.out.println("###### Past HWs #######");
			for (int i = 0; i < listHW.size(); i++) {
				System.out.println("HW ID: " + listHW.get(i).getHw_id() + " HW Name: " + listHW.get(i).getHW_name());
			}
			System.out.println("Enter the HW ID to view that HW: ");
			String hw_id = scanner.next();

			List<HomeWork> listHWDetail = ProfessorProcessor.getHWExcerciseDetails(hw_id);
			int total_score = StudentProcessor.getTotalscoreFromScoringPolicy(hw_id,user_id);
			int studentAttemptCount = StudentProcessor.getCountStudentAttempt(hw_id,user_id);
			List<Attempts> listAttempts = StudentProcessor.getCompleteAttemptsdetails(hw_id,user_id);
			System.out.println("\n***********Details for your HW: ********"+listHWDetail.get(0).getHw_id() +"HW NAME: "+listHWDetail.get(0).getHW_name());
			System.out.println("HW Start Date "+listHWDetail.get(0).getHw_st_date());
			System.out.println("HW Start Date "+listHWDetail.get(0).getHw_end_date());
			System.out.println("HW Scoring policy "+listHWDetail.get(0).getScore_policy());
			System.out.println("Total score based on the scoring policy"+total_score);
			System.out.println("HW Attempts allowed "+listHWDetail.get(0).getMax_no_of_tries());
			System.out.println("Your number of attempts "+studentAttemptCount);
			if(listAttempts.size()!=0) {
				for(int i=0; i<=listAttempts.size()-1 ;i++) {
					System.out.println("\n******** DISPLAY DETAILS FOR THIS ATTEMPT: ******"+listAttempts.get(i).getAttempt_id()+"Your Score for this attempt"+listAttempts.get(i).getTotal_score());
					System.out.println("Question id:  "+listAttempts.get(i).getQues_id());
					System.out.println("Question Text:  "+listAttempts.get(i).getQues_text());
					System.out.println("Answer you chose: "+listAttempts.get(i).getAns_id());
					System.out.println("Answer Value: "+listAttempts.get(i).getValue_id());
					System.out.println("Score of that question: "+listAttempts.get(i).getScore_per_ques());
					if(listAttempts.get(i).getScore_per_ques()<=0) {
						System.out.println("Your answer is : INCORRECT");	
					}else {
						System.out.println("Your answer is : CORRECT");	
					}
					System.out.println("Solution : "+listAttempts.get(i).getQ_EXPLN());
				}
			}

		} else if (HWChoice != 1 || HWChoice != 2) {
			System.out.println("##########Invalid Choice########");
			System.out.println("Enter 1 for Current HWs and 2 For Past HWs:");
			HWChoice = scanner.nextInt();
		}

	}

	private static void takeTest(int a_id, String hw_id, String user_id, String courseId, String q_id, Scanner scanner,
			Student currStudent) {

		Random rand = new Random();
		// Retrieve ques_text and value_id from Attempts_info and 4 answer IDs
		// and 4 answer texts based on q_id and value_id from ANSWER
		Attempts_info attlist = StudentProcessor.retreiveQuesDetails(a_id, hw_id, user_id, courseId, q_id);
		String ques_text = attlist.getQues_text();
		int val_id = attlist.getValue_id();

		List<Answer> listofAns = StudentProcessor.retreiveAnswers(q_id, val_id);

		// Print question and answers
		int listSize = listofAns.size();
		int min = 1;
		int numAnswers = rand.nextInt((listSize - min) + 1) + min;
		System.out.println("QUESTION " + q_id);
		System.out.println("\n\n" + ques_text);
		for (int i = 0; i < numAnswers; i++) {
			System.out.println((i + 1) + ". " + listofAns.get(i).getAns_explanation());
		}
		// Get user's answer
		// Call stored procedure update_ans()
		String userChoice = scanner.next();
		StudentProcessor.updateAnswers(a_id, courseId, hw_id, user_id, q_id, userChoice);

		// Call stored procedure retreiveQues()
		// If ques_id returned == 0, then exit. Else, recursively call
		// takeTest()
		q_id = StudentProcessor.retrieveQuestion(a_id, hw_id, user_id, courseId);
		System.out.println("QID"+q_id);
		if (q_id.equals("-1") || q_id.equals("0")) {
			System.out.println("You have completed this Attempt!");
			studentHomePage(currStudent, scanner);
		} else {
			// Present the user with question details plus 4 options
			takeTest(a_id, hw_id, user_id, courseId, q_id, scanner, currStudent);
		}

	}
}

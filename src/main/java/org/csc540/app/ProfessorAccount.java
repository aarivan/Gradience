package org.csc540.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.log4j.Logger;
import org.csc540.pojo.CompletedAttempts;
import org.csc540.pojo.Course;
import org.csc540.pojo.CourseEnrollment;
import org.csc540.pojo.HomeWork;
import org.csc540.pojo.HwQuestion;
import org.csc540.pojo.Professor;
import org.csc540.pojo.Question;
import org.csc540.pojo.Report;
import org.csc540.pojo.Topic;
import org.csc540.processor.ProfessorProcessor;
import org.csc540.processor.StudentProcessor;
import org.csc540.processor.TeachingAssistantProcessor;

public class ProfessorAccount {

	public static final Logger LOG = Logger.getLogger(ProfessorAccount.class);

	public static ProfessorAccount homePage(Professor professorUser, Scanner scanner) throws ParseException {
		int selection = 1;
		List<Course> profCourseList = null;
		String courseId;
		int i = 0;
		while (selection != 12) {
			System.out.println("\n\n::: Instructor Home Page :::");
			System.out.println(
					"Enter the options for following actions: \n1. View/Edit Profile \n2. View Courses \n3. Add Courses \n4. Enroll a Student \n5. Drop a Student \n6. View Report \n7. Setup TA \n"
							+ "8.View/Edit HW Exercises \n9.Add HW Excercises \n10. Add​ ​questions​ ​to​ ​Question​ ​Bank \n"
							+ "11. Add/Remove Questions from Exercises \n12. Log Out \nEnter your option:");
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
				System.out.println("### Press the field name index to edit that field ###\nEnter your option:");
				System.out.println("### Press 0 to Go Back ###\n\n");
				newChoice = scanner.nextInt();
				if (newChoice == 1) {
					System.out.println("Editting First name");
					System.out.println("Enter the new First name:");
					String new_F_name = scanner.next();
					professorUser.setF_name(new_F_name);
					try {
						ProfessorProcessor.updateprofessorProfile(professorUser);
					} catch (SQLException e) {
						System.out.println("there was an exception while editting professor " + e.getMessage());
					}
				} else if (newChoice == 2) {
					System.out.println("Editting Last name");
					System.out.println("Enter the new Last Name:");
					String new_L_name = scanner.next();
					professorUser.setL_name(new_L_name);
					try {
						ProfessorProcessor.updateprofessorProfile(professorUser);
					} catch (SQLException e) {
						System.out.println("there was an exception while editting professor " + e.getMessage());
					}
				} else if (newChoice == 3) {
					System.out.println("Cannot edit professor ID");
				} else if (newChoice == 4) {
					System.out.println("Editting Address");
					scanner.nextLine();
					System.out.println("Enter the new Address:");
					String new_Address = scanner.nextLine();
					System.out.println("Entered the new Address:" + new_Address);
					professorUser.setAddress(new_Address);
					try {
						ProfessorProcessor.updateprofessorProfile(professorUser);
					} catch (SQLException e) {
						System.out.println("there was an exception while editting professor " + e.getMessage());
					}

				} else if (newChoice == 5) {
					System.out.println("Editting Phone Number");
					System.out.println("Enter the new Phone Number:");
					String new_phone_number = scanner.next();
					professorUser.setPhone_number(new_phone_number);
					try {
						ProfessorProcessor.updateprofessorProfile(professorUser);
					} catch (SQLException e) {
						System.out.println("there was an exception while editting professor " + e.getMessage());
					}
				} else if (newChoice == 6) {
					System.out.println("Editting Email");
					System.out.println("Enter the new Email:");
					String new_email = scanner.next();
					professorUser.setEmail(new_email);
					try {
						ProfessorProcessor.updateprofessorProfile(professorUser);
					} catch (SQLException e) {
						System.out.println("there was an exception while editting professor " + e.getMessage());
					}
				} else if (newChoice == 0) {

					homePage(professorUser, scanner);
				} else {
					LOG.info("Invalid Option");

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
				if (courseId.equals("0")) {
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
				System.out.println("\n\n");
				System.out.println("----------------------------");
				System.out.println("Please enter the details of the new course to be added:");
				System.out.println("----------------------------");

				// Boolean flag;
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

				// flag = validatingCourseDetails(course_id, courseName,
				// courseStartDate, courseEndDate);

				ProfessorProcessor.addCourse(course_id, courseName, courseStartDate, courseEndDate,
						professorUser.getUserId(), courseLevel, 0, courseSize);

				System.out.println("\n\n### Press 0 to Go Back ###\n\n");
				newChoice = scanner.nextInt();
				while (newChoice != 0) {
					System.out.println("\nInvalid choice!! Please enter 0 to go back to previous page!!\n\n");
					newChoice = scanner.nextInt();
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
				if (courseId.equals("0")) {
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
				if (courseId.equals("0")) {
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
				profCourseList = ProfessorProcessor.getCourses(professorUser.getUserId());
				if (profCourseList.size() != 0) {
					System.out.println("\n\n");
					System.out.println("----------------------------");
					System.out.println("List of your Courses as Instructor:");
					System.out.println("----------------------------");

					i = 0;
					while (i < profCourseList.size()) {
						System.out.println((i + 1) + ". " + profCourseList.get(i).getCourseId());
						i++;
					}

					System.out.println("Please enter Course ID for which you would like to view report:\n\n");
					System.out.println("### Press 0 to Go Back ###\n\n");
					courseId = scanner.next();
					Boolean courseFlag = false;
					i = 0;
					while (i < profCourseList.size()) {
						if (profCourseList.get(i).getCourseId().equals(courseId)) {
							courseFlag = true;
							break;
						}
						i++;
					}
					if (courseFlag) {
						i = 0;
						List<Report> temp = null;
						while (i < profCourseList.size()) {
							if (profCourseList.get(i).getCourseId().equalsIgnoreCase(courseId)) {
								List<Report> report_details = ProfessorProcessor
										.viewReport(profCourseList.get(i).getCourseId());
								temp = report_details;
								if (report_details.size() != 0) {

									int j = 0;
									System.out.println("\nReport for Course " + courseId + ":\n");
									System.out.println(
											"\n S.No \t | StudentID \t | First name \t | Last Name \t | Homework \t | Attempt # \t | Score for this attempt \t \n ");
									while (j < report_details.size()) {
										System.out.println((j + 1) + "\t | " + report_details.get(j).getStud_id()
												+ "\t | " + report_details.get(j).getFirst_name() + "\t\t | "
												+ report_details.get(j).getLast_name() + "\t | "
												+ report_details.get(j).getHw_id() + "\t\t | "
												+ report_details.get(j).getAttempt_id() + "\t\t | "
												+ report_details.get(j).getTotal_score());
										j++;
									}

								} else {
									System.out.println("\n\n There are no details to be displayed right now! \n\n");
									break;
								}
							}
							i++;
						}
						System.out.println(
								"\n\n### Press 0 to Go Back ### OR ### Press 1 to see detailed Report ###\n\n");
						newChoice = scanner.nextInt();
						if (newChoice == 1) {
							viewDetailedReport(courseId, temp, scanner);
							newChoice = 0;
						}
						while (newChoice != 0) {
							System.out.println("\nInvalid choice!! Please enter 0 to go back to previous page!!\n\n");
							newChoice = scanner.nextInt();
						}
						break;
					} else {
						System.out.println("\n Invalid course ID enetered! \n");
						break;
					}

				} else {
					System.out.println("\n\n There are no courses for this Professor! ... ");
					break;
				}

			case 7:
				// SETUP TA
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

				System.out.println("Please enter Course ID for which you would like to add TA/s:\n\n");
				System.out.println("### Press 0 to Go Back ###\n\n");
				courseId = scanner.next();
				if (courseId.equals("0")) {
					break;
				} else {
					i = 0;
					boolean course_found = false;
					while (i < profCourseList.size()) {
						if (profCourseList.get(i).getCourseId().equalsIgnoreCase(courseId)) {
							System.out.println("Enter the users_ID of the TA you want to add: ");
							scanner.nextLine();
							String TA_id = scanner.nextLine();
							ProfessorProcessor.addTA(TA_id, profCourseList.get(i).getCourseId());

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

			case 8:
				// View/Edit Exercises
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

				System.out.println("Please enter Course ID for which you would like view HW Excercise/s:\n\n");
				System.out.println("### Press 0 to Go Back ###\n\n");
				courseId = scanner.next();
				if (courseId.equals("0")) {
					break;
				} else {
					i = 0;
					boolean course_found = false;
					while (i < profCourseList.size()) {
						if (profCourseList.get(i).getCourseId().equalsIgnoreCase(courseId)) {
							viewHWExercise(profCourseList.get(i), scanner);
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

			case 9:
				// ADD HW EXCERCISE
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

				System.out.println("Please enter Course ID for which you would like view HW Excercise/s:\n\n");
				System.out.println("### Press 0 to Go Back ###\n\n");
				courseId = scanner.next();
				if (courseId.equals("0")) {
					break;
				} else {
					i = 0;
					boolean course_found = false;
					while (i < profCourseList.size()) {
						if (profCourseList.get(i).getCourseId().equalsIgnoreCase(courseId)) {
							addHWExercise(profCourseList.get(i).getCourseId(), scanner);
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

			case 10:
				// Search/Add​ ​questions​ ​to​ ​Question​ ​Bank
				System.out.println("\n::: ADD QUESTION TO QUESTION BANK :::");

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

				Boolean courseflag = false;
				i = 0;
				while (i < profCourseList.size()) {
					if (profCourseList.get(i).getCourseId().equals(courseId)) {
						courseflag = true;
					}
					i++;
				}
				if (courseflag) {
					System.out.println("----------------------------");
					System.out.println("List of Topic IDs:");
					System.out.println("----------------------------");

					List<Topic> profCourseTopicList = ProfessorProcessor.getCourseTopics(courseId);

					i = 0;
					while (i < profCourseTopicList.size()) {
						System.out.println((i + 1) + ". " + profCourseTopicList.get(i).getTopicId() + "-----"
								+ profCourseTopicList.get(i).getTopicName());
						i++;
					}

					System.out.println("Please enter the Topic ID to which you would like to add:");
					String topicId1 = scanner.next();

					Boolean flag = false;
					i = 0;
					while (i < profCourseTopicList.size()) {
						if (profCourseTopicList.get(i).getTopicId().equals(topicId1)) {
							flag = true;
						}
						i++;
					}

					if (flag) {
						try {
							System.out.println("Please enter the Question ID:");
							String quesId = scanner.next();

							System.out.println(
									"Please enter the Question type(P/NP) [P - Parameterized, NP - Non Parameterized]:");
							String quesType = scanner.next();

							System.out.println("Please enter the Question text:");
							scanner.nextLine();
							String quesText = scanner.nextLine();

							System.out.println("Please enter the Question explanation:");

							String quesExp = scanner.nextLine();
							scanner.nextLine();

							System.out.println("Please enter the difficulty level (1-5):");
							int diffLevel1 = scanner.nextInt();

							System.out.println("Please enter the Hint:");
							scanner.nextLine();
							String hint = scanner.nextLine();

							ProfessorProcessor.addQuesToBank(quesId, topicId1, quesText, quesExp, diffLevel1, hint,
									quesType);

							if (quesType.equals("NP")) {
								addAns(quesId, scanner);
							} else {

								System.out.println("Enter the number of parameters: ");
								int param_no = scanner.nextInt();
								scanner.nextLine();

								System.out.println("Enter the number of values: ");
								int val = scanner.nextInt();
								scanner.nextLine();

								for (int j = 0; j < param_no; j++) {

									System.out.println("Enter the parameter name: ");
									String param_name = scanner.nextLine();
									scanner.nextLine();

									for (int k = 0; k < val; k++) {
										System.out.println("Enter the parameter value " + (k + 1) + ":");
										String param_val = scanner.nextLine();
										scanner.nextLine();
										ProfessorProcessor.addParamQues(quesId, Integer.toString(j + 1),
												Integer.toString(k + 1), param_name, param_val);
									}
								}
								addAns(quesId, scanner);
							}
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} else {
						System.out.println("\n\n Invalid Topic ID. \n\n");
					}

				} else {
					System.out.println("\n\n Invalid Course ID. \n\n");
				}
				System.out.println("\n\n### Press 0 to Go Back ###\n\n");
				newChoice = scanner.nextInt();
				while (newChoice != 0) {
					System.out.println("\nInvalid choice!! Please enter 0 to go back to previous page!!\n\n");
					newChoice = scanner.nextInt();
				}
				break;
			case 11:
				// Add/Remove Questions from Exercises
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

				System.out.println("Please enter Course ID for which you would like view HW Excercise/s:\n\n");
				System.out.println("### Press 0 to Go Back ###\n\n");
				courseId = scanner.next();
				if (courseId.equals("0")) {
					break;
				} else {
					i = 0;
					boolean course_found = false;
					while (i < profCourseList.size()) {
						if (profCourseList.get(i).getCourseId().equalsIgnoreCase(courseId)) {
							hwQues(profCourseList.get(i), scanner);
							course_found = true;
						}
						i++;
					}
					if (!course_found) {
						System.out.println("Invalid Course Id Entered..");
						break;
					} else {
						break;
					}
				}
			case 12:
				// Logout
				selection = 12;
				break;
			default:
				System.out.println("Invalid Option! Please try again ..");
			}
		}
		Main.main(null);
		return null;
	}

	public static void addHWExercise(String courseId, Scanner scanner) {
		System.out.println("\nADD HW Excercise:\n\n");
		System.out.println("----------------------------");
		System.out.println("Please enter details of the new Homework:");
		System.out.println("----------------------------");
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
		int diffLevel = scanner.nextInt();
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
		System.out.println("Enter HW Type : ");
		String hw_type = scanner.next();
		try {
			ProfessorProcessor.addHomeWork(hw_id, courseId, topicId, hwName, maxTries, hwStartDate, hwEndDate,
					correct_pt, penalty_pt, scoring_policy, diffLevel, hw_type);
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}

	public static void viewDetailedReport(String courseId, List<Report> temp, Scanner scanner) {
		if (temp.size() != 0) {
			System.out.println("\nPlease enter Student ID: ");
			String student_id = scanner.next();
			System.out.println("\nPlease enter Homework ID: ");
			String hw_id = scanner.next();
			System.out.println("\nPlease enter Attempt ID: ");
			int attempt_id = scanner.nextInt();
			Boolean flag = false;
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).getStud_id().equals(student_id)) {
					if (temp.get(i).getHw_id().equals(hw_id)) {
						if (temp.get(i).getAttempt_id() == attempt_id) {
							// Display Report
							flag = true;
							List<CompletedAttempts> completedAttempts = StudentProcessor
									.getCompletedAttempts(attempt_id, courseId, hw_id, student_id);
							if (completedAttempts.size() != 0) {
								System.out.println("************************************************");
								System.out.println("\nAttempt ID: " + completedAttempts.get(0).getAttempt_id());
								System.out.println("HW ID: " + completedAttempts.get(0).getHw_id());
								System.out.println("Course ID: " + completedAttempts.get(0).getCourse_id());
								System.out
										.println("\nTotal score: " + completedAttempts.get(0).getTotal_score() + "\n");
								for (int j = 0; j < completedAttempts.size(); j++) {
									System.out.println("\n************************************************");
									System.out.println("Question ID: " + completedAttempts.get(j).getQues_id());
									System.out.println("Question Text: " + completedAttempts.get(j).getQues_text());
									System.out
											.println("Student's Answer (ID): " + completedAttempts.get(j).getAns_id());
									System.out.println("Student's Answer (Text Description): "
											+ completedAttempts.get(j).getA_expln());
									if (completedAttempts.get(j).getScore_per_ques() <= 0) {
										System.out.println("\n Student's answer is : INCORRECT");
									} else {
										System.out.println("\n Student's answer is : CORRECT");
									}
									System.out.println("\nStudent's Score for this Question: "
											+ completedAttempts.get(j).getScore_per_ques());
								}
								System.out.println("************************************************");

							} else {
								System.out.println("There are no details available for this Attempt! \n\n ");
							}
						}
					}
				}
			}
			if (!flag) {
				System.out.println("\nInvalid details entered! Please try again.. \n\n");
			}
		} else {
			System.out.println("\n\nThere are no detailed reports available right now ! \n\n");
		}

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
		System.out.println("\n\nEnrollment for Course " + course.getCourseId() + ":\n");
		System.out.println("Enter Student ID: ");
		String student_id = scanner.next();
		// The dropStudent method will catch DB exception, throw it and continue
		// execution. Validation of Student ID done in DBMS directly.
		ProfessorProcessor.dropStudent(student_id, course.getCourseId());

	}

	public static void enrollStudenttoCourse(Scanner scanner, Course course) {
		// TODO Auto-generated method stub
		System.out.println("\n\nEnrollment for Course " + course.getCourseId() + ":\n");
		System.out.println("Enter Student ID: ");
		String student_id = scanner.next();
		// The enrollStudent method will catch DB exception, throw it and
		// continue execution. Validation of Student ID done in DBMS directly.
		ProfessorProcessor.enrollStudent(student_id, course.getCourseId());

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

	}

	public static void viewHWExercise(Course course, Scanner scanner) throws ParseException {
		// TODO Auto-generated method stub
		List<HomeWork> listHWExcercise = ProfessorProcessor.getHWExcerciseForCourse(course.getCourseId());
		System.out.println("\n\n");
		System.out.println("----------------------------");
		System.out.println("List of HW Excercise/s for the course:");
		System.out.println("----------------------------");

		int i = 0;
		while (i < listHWExcercise.size()) {
			System.out.println((i + 1) + ". HW ID: " + listHWExcercise.get(i).getHw_id() + "HW Name: "
					+ listHWExcercise.get(i).getHW_name());
			i++;
		}
		System.out.println("Please enter HW ID for which you would like view HW Excercise/s:\n\n");

		String HWId = scanner.next();

		List<HomeWork> listHWDetails = ProfessorProcessor.getHWExcerciseDetails(HWId, course.getCourseId());
		i = 0;
		boolean HW_found = false;
		while (i < listHWDetails.size()) {
			if (listHWDetails.get(i).getHw_id().equalsIgnoreCase(HWId)) {
				viewHWExerciseDetails(listHWDetails.get(i), scanner, course);
				HW_found = true;
			}
			i++;

			if (!HW_found) {
				System.out.println("Invalid HW Id Entered..");
				viewHWExercise(course, scanner);
				break;
			}

		}

	}

	public static void hwQues(Course course, Scanner scanner) throws ParseException {
		// TODO Auto-generated method stub
		List<HomeWork> listHWExcercise = ProfessorProcessor.getHWExcerciseForCourse(course.getCourseId());
		System.out.println("\n\n");
		System.out.println("----------------------------");
		System.out.println("List of HW Excercise/s for the course:");
		System.out.println("----------------------------");

		int i = 0;
		while (i < listHWExcercise.size()) {
			System.out.println((i + 1) + ". HW ID: " + listHWExcercise.get(i).getHw_id() + "HW Name: "
					+ listHWExcercise.get(i).getHW_name());
			i++;
		}
		System.out.println("Please enter HW ID for which you would like to add or remove questions:\n\n");

		String HWId = scanner.next();
		
		List<HomeWork> listHWDetails = ProfessorProcessor.getHWExcerciseDetails(HWId, course.getCourseId());
		i = 0;
		boolean HW_found = false;
		while (i < listHWDetails.size()) {
			if (listHWDetails.get(i).getHw_id().equalsIgnoreCase(HWId)) {
				addorremoveQuestionsExercise(listHWDetails.get(i), scanner);
				HW_found = true;
			}
			i++;

			if (!HW_found) {
				System.out.println("Invalid HW Id Entered..");
				hwQues(course, scanner);
				break;
			}

		}

	}

	public static void addorremoveQuestionsExercise(HomeWork homeWork, Scanner scanner) {
		// TODO Auto-generated method stub
		int selection = 12;
		while (selection != 0) {
			System.out.println("\n\n Homework: " + homeWork.getHW_name() + "\n");
			// Search/Add/remove Ques 2. Add topic to HW
			System.out.println("\n 1. Search/Add/Remove Questions \n Press 0 to Exit \n\n");
			int choice = scanner.nextInt();
			if(choice == 1){
				System.out.println("1. Add Questions \n2. Remove Questions \nPress 0 to exit\n\n");
				selection = scanner.nextInt();
				switch (selection) {
				case 0:
					selection = 0;
					break;
				case 1:
					List<Question> ques_list = listQuestionsfromHWTopics(homeWork,scanner);
					if(ques_list.size() != 0){
						addQuestionstoExercise(homeWork, ques_list, scanner);
					} else {
						System.out.println("\n Please add questions to this topic before adding questions to this homework!\n");
					}
					break;
				case 2:
					removeQuestionsfromExercise(homeWork, scanner);
					break;
				default:
					System.out.println("\nInvalid choice. Please enter a valid choice !!\n");
				}
			} else if(choice == 0){
				selection = 0;
			} else {
				System.out.println("\nInvalid choice. Please enter a valid choice !!\n");
			}
		}

	}

	private static List<Question> listQuestionsfromHWTopics(HomeWork homeWork, Scanner scanner) {
		System.out.println("\nList of Questions: \n");
		List<Question> ques_list = ProfessorProcessor.listQuesForTopic(homeWork.getTopic_id());
		if(ques_list.size() != 0) {
			System.out.println("\nQuestion ID \t | Question Difficulty Level \t | Question Text \n");
			for(int i=0;i<ques_list.size();i++) 
			{
				System.out.println(ques_list.get(i).getQ_id()+" \t\t | "+ ques_list.get(i).getDiff_level() +"\t\t\t\t | "+ques_list.get(i).getQuestion());			
			}
			
		} else {
			System.out.println("\n There are no questions for this topic!\n");
			
		}
		return ques_list;
	}

	public static void removeQuestionsfromExercise(HomeWork homeWork, Scanner scanner) {
		String q_id = "Y";
		do {
			System.out.println("\n\n Homework: " + homeWork.getHW_name() + "\n");

			List<HwQuestion> listHWQues = ProfessorProcessor.listQuestionsfromExercise(homeWork.getHw_id());
			int i = 0;
			System.out.println("\nList of Question IDs in this Exercise:\n");
			while (i < listHWQues.size()) {
				System.out.println((i + 1) + ". " + listHWQues.get(i).getquestionId());
				i++;
			}
			System.out.println("\n\nEnter a question ID to remove from this Homework: ");
			scanner.nextLine();
			q_id = scanner.nextLine();

			ProfessorProcessor.removeQuestionfromExercise(homeWork.getHw_id(), q_id);

			System.out.println("Do you want to remove another question? (Y/N)");
			q_id = scanner.next();

		} while (q_id.equals("Y"));

	}

	public static void addQuestionstoExercise(HomeWork homeWork, List<Question> ques_list, Scanner scanner) {

		String q_id = "Y";
		do {
			System.out.println("\n\n Homework: " + homeWork.getHW_name() + "\t | Difficulty level: "+homeWork.getDiff_level());
			System.out.println("Enter a question ID to add into this Homework: ");
			scanner.nextLine();
			q_id = scanner.nextLine();
			Boolean flag = false;
			for(int i=0;i<ques_list.size();i++) {
				if(ques_list.get(i).getQ_id().equals(q_id)){
					flag = true;
				}
			}
			if(flag) {
				ProfessorProcessor.addQuestiontoExercise(homeWork.getHw_id(), q_id, homeWork.getCourse_id());
			}
			else {
				System.out.println("\n\n Invalid Question ID entered!\n\n");
			}
			
			System.out.println("Do you want to add another question? (Y/N)");
			q_id = scanner.next();

		} while (q_id.equals("Y"));

	}

	public static void addAns(String quesId, Scanner scanner) {

		int ans_id = 1;

		String ch = "Y";
		do {
			System.out.println("Please enter the answer choice "+ans_id+" description for the question:");
			scanner.nextLine();
			String ans = scanner.nextLine();
			
			System.out.println("Is this the correct answer? (T/F)");
			String is_correct = scanner.next();
			System.out.println("fsfsdf"+ans);
			ProfessorProcessor.addAnswer(Integer.toString(ans_id), quesId, is_correct, ans);

			ans_id++;
			System.out.println("Do you want to add another answer? (Y/N)");
			ch = scanner.next();

		} while (ch.equals("Y"));
	}

	public static void viewHWExerciseDetails(HomeWork homeWork, Scanner scanner, Course course) throws ParseException {
		System.out.println("------------------------------------");
		System.out.println("--------- " + homeWork.getHw_id() + " ---------");
		System.out.println("------------------------------------");

		System.out.println("\n\n");
		System.out.println("(1) HW Name :" + homeWork.getHW_name());
		System.out.println("(2) HW Start Date:" + homeWork.getHw_st_date());
		System.out.println("(3) HW End Date:" + homeWork.getHw_end_date());
		System.out.println("(4) HW Course ID:" + homeWork.getCourse_id());
		System.out.println("(5) HW Maximum Tries Allowed:" + homeWork.getMax_no_of_tries());
		System.out.println("(6) HW Dificulty Level:" + homeWork.getDiff_level());
		System.out.println("(7) HW Scoring Policy:" + homeWork.getScore_policy());
		System.out.println("(8) HW Topic ID:" + homeWork.getTopic_id());
		System.out.println("(9) HW Correct points:" + homeWork.getCorrect_pts());
		System.out.println("(10) HW Penalty points:" + homeWork.getPenalty_pts());
		System.out.println("(11) HW Type:" + homeWork.getHw_type());
		System.out.println("### Press 0 to Go Back ###\n\n");
		System.out.println("### Press the field name to edit ###\n\n");
		int editHWChoice = scanner.nextInt();
		if (editHWChoice == 0) {
			viewHWExercise(course, scanner);
		} else if (editHWChoice == 1) {
			System.out.println("Editting HW Name");
			scanner.nextLine();
			System.out.println("Enter the new HW name:");
			String new_HW_name = scanner.nextLine();
			homeWork.setHW_name(new_HW_name);
			try {
				ProfessorProcessor.updateHomeWork(homeWork);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (editHWChoice == 2) {
			System.out.println("Editting HW Start Date");
			System.out.println("Enter the new HW Date in DD-MMM-YYYY format:");
			String new_hw_st_date = scanner.next();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			Date new_start = formatter.parse(new_hw_st_date);
			homeWork.setHw_st_date(new_start);
			try {
				ProfessorProcessor.updateHomeWork(homeWork);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (editHWChoice == 3) {
			System.out.println("Editting HW End Date");
			System.out.println("Enter the new HW Date in DD-MMM-YYYY format:");
			String new_hw_ed_date = scanner.next();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			Date new_end = formatter.parse(new_hw_ed_date);
			homeWork.setHw_end_date(new_end);
			try {
				ProfessorProcessor.updateHomeWork(homeWork);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (editHWChoice == 4) {
			System.out.println("Editting HW Course ID");
			scanner.nextLine();
			System.out.println("Enter the new HW Course ID:");
			String new_HW_course = scanner.nextLine();
			homeWork.setCourse_id(new_HW_course);
			try {
				ProfessorProcessor.updateHomeWork(homeWork);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (editHWChoice == 5) {
			System.out.println("Editting Maximum number of tries");
			System.out.println("Enter the new Maximum number of tries:");
			int new_max_tries = scanner.nextInt();
			homeWork.setMax_no_of_tries(new_max_tries);
			try {
				ProfessorProcessor.updateHomeWork(homeWork);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (editHWChoice == 6) {
			System.out.println("Editting Difficulty Level");
			System.out.println("Enter the new Difficulty Level:");
			int new_diff_level = scanner.nextInt();
			homeWork.setDiff_level(new_diff_level);
			try {
				ProfessorProcessor.updateHomeWork(homeWork);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (editHWChoice == 7) {
			System.out.println("Editting Scoring Policy");
			System.out.println("Enter the new  Scoring Policy: 'latest','max', 'avg'");
			String new_scoring_policy = scanner.next();
			homeWork.setScore_policy(new_scoring_policy);
			try {
				ProfessorProcessor.updateHomeWork(homeWork);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (editHWChoice == 8) {
			System.out.println("Editting HW Topic ID");
			scanner.nextLine();
			System.out.println("Enter the new HW Topic ID:");
			String new_HW_topic = scanner.nextLine();
			homeWork.setTopic_id(new_HW_topic);
			;
			try {
				ProfessorProcessor.updateHomeWork(homeWork);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (editHWChoice == 9) {
			System.out.println("Editting correct pts");
			System.out.println("Enter the new correct pts:");
			int new_correct_pt = scanner.nextInt();
			homeWork.setCorrect_pts(new_correct_pt);
			try {
				ProfessorProcessor.updateHomeWork(homeWork);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (editHWChoice == 10) {
			System.out.println("Editting penalty pts");
			System.out.println("Enter the new penalty pts:");
			int new_penalty_pt = scanner.nextInt();
			homeWork.setPenalty_pts(new_penalty_pt);
			try {
				ProfessorProcessor.updateHomeWork(homeWork);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}

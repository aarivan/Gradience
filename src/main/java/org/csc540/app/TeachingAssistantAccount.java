package org.csc540.app;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.log4j.Logger;
import org.csc540.pojo.Course;
import org.csc540.pojo.CourseEnrollment;
import org.csc540.pojo.HomeWork;
import org.csc540.pojo.Report;
import org.csc540.pojo.Student;
import org.csc540.processor.ProfessorProcessor;
import org.csc540.processor.StudentProcessor;
import org.csc540.processor.TeachingAssistantProcessor;

public class TeachingAssistantAccount {
	public static final Logger LOG = Logger.getLogger(TeachingAssistantAccount.class);

	public static void TeachingAssistantHomePage(Student studentUser, Scanner scanner) {
		int selection = 1;
		List<CourseEnrollment> taCourseList = null;
		int i = 0;
		String courseId;
		while (selection != 8) {
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
				if (courseId.equals("0")) {
					break;
				} else {
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
						System.out.println("Invalid Course Id Entered..Please try again..");
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
				if (courseId.equals("0")) {
					break;
				} else {
					i = 0;
					boolean course_found = false;
					while (i < taCourseList.size()) {
						if (taCourseList.get(i).getCourseId().equalsIgnoreCase(courseId)) {
							enrollStudenttoCourse(scanner, taCourseList.get(i));
							course_found = true;
						}
						i++;
					}
					if (!course_found) {
						System.out.println("Invalid Course Id Entered..Please try again..");
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
				if (courseId.equals("0")) {
					break;
				} else {
					i = 0;
					boolean course_found = false;
					while (i < taCourseList.size()) {
						if (taCourseList.get(i).getCourseId().equalsIgnoreCase(courseId)) {
							dropStudenttoCourse(scanner, taCourseList.get(i));
							course_found = true;
						}
						i++;
					}
					if (!course_found) {
						System.out.println("Invalid Course Id Entered..Please try again..");
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
				if (courseId.equals("0")) {
					break;
				} else {
					i = 0;
					boolean course_found = false;
					while (i < taCourseList.size()) {
						if (taCourseList.get(i).getCourseId().equalsIgnoreCase(courseId)) {
							viewHWExerciseTA(taCourseList.get(i), scanner, studentUser);
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
				// ADD HW
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
				if (courseId.equals("0")) {
					break;
				} else {
					i = 0;
					boolean course_found = false;
					while (i < taCourseList.size()) {
						if (taCourseList.get(i).getCourseId().equalsIgnoreCase(courseId)) {
							addHWExerciseTA(taCourseList.get(i).getCourseId(), scanner);
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

			case 7:
				// VIEW REPORT
				taCourseList = TeachingAssistantProcessor.getTACourses(studentUser.getUserId());

				if (taCourseList != null) {
					System.out.println("\n\n");
					System.out.println("----------------------------");
					System.out.println("List of your Courses as TA:");
					System.out.println("----------------------------");

					i = 0;
					while (i < taCourseList.size()) {
						System.out.println((i + 1) + ". " + taCourseList.get(i).getCourseId());
						i++;
					}

					System.out.println("Please enter Course ID for which you would like to view report:\n\n");
					System.out.println("### Press 0 to Go Back ###\n\n");
					courseId = scanner.next();
					Boolean courseFlag = false;
					i = 0;
					while (i < taCourseList.size()) {
						if (taCourseList.get(i).getCourseId().equals(courseId)) {
							courseFlag = true;
							break;
						}
						i++;
					}
					if (courseFlag) {
						i = 0;
						while (i < taCourseList.size()) {
							if (taCourseList.get(i).getCourseId().equalsIgnoreCase(courseId)) {
								List<Report> report_details = ProfessorProcessor
										.viewReport(taCourseList.get(i).getCourseId());
								if (report_details != null) {
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
									i++;
								} else {
									System.out.println("\n\n There are no details to be displayed right now! \n\n");
									break;
								}
							}
							System.out.println("\n\n### Press 0 to Go Back ###\n\n");
							newChoice = scanner.nextInt();
							while (newChoice != 0) {
								System.out
										.println("\nInvalid choice!! Please enter 0 to go back to previous page!!\n\n");
								newChoice = scanner.nextInt();
							}
							break;
						}
					} else {
						System.out.println("\n Invalid course ID enetered! \n");
						break;
					}

				} else {
					System.out.println("\n\n There are no courses for this Professor! ... ");
					break;
				}
				break;
			case 8:
				// logout
				selection = 8;
				break;
			default:
				System.out.println("Invalid Option! Try again.. ");
			}// end switch
		}
		Main.main(null);
	}

	public static void addHWExerciseTA(String courseId, Scanner scanner) {
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

	public static void dropStudenttoCourse(Scanner scanner, CourseEnrollment courseEnrollment) {
		System.out.println("\n\nDrop a Student from Course " + courseEnrollment.getCourseId() + ":\n");
		System.out.println("Enter Student ID: ");
		String student_id = scanner.next();
		// The dropStudent method will catch DB exception, throw it and continue
		// execution. Validation of Student ID done in DBMS directly.
		ProfessorProcessor.dropStudent(student_id, courseEnrollment.getCourseId());

	}

	public static void enrollStudenttoCourse(Scanner scanner, CourseEnrollment courseEnrollment) {
		System.out.println("\n\nEnrollment for Course " + courseEnrollment.getCourseId() + ":\n");
		System.out.println("Enter Student ID: ");
		String student_id = scanner.next();
		// The enrollStudent method will catch DB exception, throw it and
		// continue execution. Validation of Student ID done in DBMS directly.
		ProfessorProcessor.enrollStudent(student_id, courseEnrollment.getCourseId());
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
		System.out.println("Course Name :\t\t\t" + courseDetails.getCourseName());
		System.out.println("Course Start Date:\t\t" + courseDetails.getCourseStartDate());
		System.out.println("Course End Date:\t\t" + courseDetails.getCourseEndDate());

	}

	public static void viewHWExerciseTA(CourseEnrollment course, Scanner scanner, Student studentUser) {
		List<HomeWork> listHWExcercise = ProfessorProcessor.getHWExcerciseForCourse(course.getCourseId());
		if (listHWExcercise != null) {
			System.out.println("\n\n");
			System.out.println("----------------------------");
			System.out.println("List of HW Excercise/s for the course:");
			System.out.println("----------------------------");
			System.out.println("S.No \t | HWID \t | HW NAME \t ");
			int i = 0;
			while (i < listHWExcercise.size()) {
				System.out.println((i + 1) + " \t | " + listHWExcercise.get(i).getHw_id() + " \t\t | "
						+ listHWExcercise.get(i).getHW_name());
				i++;
			}
			System.out.println("\nPlease enter HW ID for which you would like view HW Excercise/s:\n\n");

			String HWId = scanner.next();
			i = 0;
			Boolean HWflag = false;
			while (i < listHWExcercise.size()) {
				if(listHWExcercise.get(i).getHw_id().equals(HWId)) {
					HWflag = true;
				}
				i++;
			}
			if (HWflag){
				List<HomeWork> listHWDetails = ProfessorProcessor.getHWExcerciseDetails(HWId, course.getCourseId());
				i = 0;
				while (i < listHWDetails.size()) {
					if (listHWDetails.get(i).getHw_id().equalsIgnoreCase(HWId)) {
						System.out.println("------------------------------------");
						System.out.println("--------- HOMEWORK: " + listHWDetails.get(i).getHw_id() + " ---------");
						System.out.println("------------------------------------");

						System.out.println("\n\n");
						System.out.println("(1) HW Name : \t\t\t" + listHWDetails.get(i).getHW_name());
						System.out.println("(2) Start Date: \t\t" + listHWDetails.get(i).getHw_st_date());
						System.out.println("(3) End Date: \t\t\t" + listHWDetails.get(i).getHw_end_date());
						System.out.println("(4) Course ID: \t\t\t" + listHWDetails.get(i).getCourse_id());
						System.out.println("(5) Maximum Tries Allowed: \t" + listHWDetails.get(i).getMax_no_of_tries());
						System.out.println("(6) Dificulty Level: \t\t" + listHWDetails.get(i).getDiff_level());
						System.out.println("(7) Scoring Policy: \t\t" + listHWDetails.get(i).getScore_policy());
						System.out.println("(8) Topic ID: \t\t\t" + listHWDetails.get(i).getTopic_id());
						System.out.println("(9) Correct points: \t\t" + listHWDetails.get(i).getCorrect_pts());
						System.out.println("(10) Penalty points: \t\t" + listHWDetails.get(i).getPenalty_pts());
						System.out.println("\n\n### Press 0 to Go Back ###");
						System.out.println("### Press 1 to Go Back to TA Menu ###\n\n");
						int editHWChoice = scanner.nextInt();
						if (editHWChoice == 0) {
							viewHWExerciseTA(course, scanner, studentUser);
						} else if (editHWChoice == 1) {
							TeachingAssistantHomePage(studentUser, scanner);
						}
						i++;
					}
				}
			}
			else {
				System.out.println("\nInvalid Homework ID entered. Please try again.. \n\n");
				viewHWExerciseTA(course, scanner, studentUser);
			}
			
		} else {
			System.out.println("There are no Homeworks for this course! \n");
		}
	}
}

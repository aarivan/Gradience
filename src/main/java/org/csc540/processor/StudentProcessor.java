package org.csc540.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.csc540.helper.DBFieldConstants;
import org.csc540.pojo.HomeWork;
import org.csc540.pojo.Student;
import org.csc540.session.Session;

public class StudentProcessor {

	public static final Logger LOG = Logger.getLogger(StudentProcessor.class);

	public static Student isTA(String userId) {
		LOG.info("Processor to check if the given user: " + userId + " is TA.");
		try {
			Connection conn = Session.getConnection();
			String check_ta_query = "SELECT * FROM STUDENT WHERE USERS_ID = '" + userId + "'";
			PreparedStatement ps = conn.prepareStatement(check_ta_query);
			ResultSet check_ta_result = ps.executeQuery();

			Student studentUser = null;
			List<Student> list = convertResultSetToStudentPOJO(check_ta_result);
			if (list.size() == 1) {
				studentUser = list.get(0);
				return studentUser;
			}
		} catch (Exception e) {
			LOG.error("Exception while processing the given student user.", e);
		}
		return null;
	}

	public static List<Student> convertResultSetToStudentPOJO(ResultSet set) {
		LOG.info("Converting ResultSet to Student POJO...");
		List<Student> result = null;
		try {
			result = new ArrayList<Student>();
			while (set.next()) {
				Student temp = new Student();

				String userId = set.getString(DBFieldConstants.STUDENT_USERS_ID);
				temp.setUserId(userId);
				String f_name = set.getString(DBFieldConstants.STUDENT_FIRST_NAME);
				temp.setF_name(f_name);
				String l_name = set.getString(DBFieldConstants.STUDENT_LAST_NAME);
				temp.setL_name(l_name);
				String address = set.getString(DBFieldConstants.STUDENT_ADDRESS);
				temp.setAddress(address);
				String phone_number = set.getString(DBFieldConstants.STUDENT_PHONE_NUMBER);
				temp.setPhone_number(phone_number);
				String level = set.getString(DBFieldConstants.STUDENT_LEVEL_S);
				temp.setLevel(level);
				String email = set.getString(DBFieldConstants.STUDENT_EMAIL_ID);
				temp.setEmail(email);
				String is_ta = set.getString(DBFieldConstants.STUDENT_IS_TA);
				temp.setIs_ta(is_ta);
				result.add(temp);
			}
		} catch (Exception e) {
			LOG.error("Exception while converting the Result Set to Student POJO", e);
		}
		return result;
	}

	

	public static void updateStudentProfile(Student currStudent) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		String updateTableSQL = "UPDATE STUDENT " + "SET first_name = '" + currStudent.getF_name() + "', last_name= '"
				+ currStudent.getL_name() + "' ,address ='" + currStudent.getAddress() + "'" + " , ph_no='"
				+ currStudent.getPhone_number() + "',level_s='" + currStudent.getLevel() + "'" + ",email_id='"
				+ currStudent.getEmail() + "',is_TA ='" + currStudent.isIs_ta() + "'" + " WHERE users_id = '"
				+ currStudent.getUserId() + "'";

		try {
			conn = Session.getConnection();
			ps = conn.prepareStatement(updateTableSQL);
			ps.execute();
			System.out.println("Student Account Updated!!");
		} catch (Exception e) {
			
			LOG.info("there was an exception while updating the Student details");
		}

	}

	public static Boolean validatingStudentDetails(String student_id, String student_fname, String student_lname) {
		// TODO Auto-generated method stub
		LOG.info("Processor to validate student details: " + student_id);
		try {
			Connection conn = Session.getConnection();
			String check_stu_query = "SELECT * FROM STUDENT WHERE USERS_ID = '" + student_id + "'";
			PreparedStatement ps = conn.prepareStatement(check_stu_query);
			ResultSet check_stu_result = ps.executeQuery();

			Student studentUser = null;
			List<Student> list = convertResultSetToStudentPOJO(check_stu_result);
			if (list.size() == 1) {
				studentUser = list.get(0);

			}
			if(studentUser.getUserId().equals(student_id)&&studentUser.getF_name().equals(student_fname)&&studentUser.getL_name().equals(student_lname)){
				return true;
			}
		} catch (Exception e) {
			LOG.error("Exception while processing the given student user.", e);
		}
		return false;
	}
	
	public static List getCoursesOfStudent (String user_id,Scanner scanner) {
		List courseIDForStudents =  new ArrayList();
		try {
			Connection conn = Session.getConnection();
			String getCoursesForStudent = "select COURSE_ID from COURSE_ENROLLMENT where users_id='"+user_id+"'";
			PreparedStatement ps = conn.prepareStatement(getCoursesForStudent);
			ResultSet getCoursesForStudent_result = ps.executeQuery();
			int i = 1;
			while (getCoursesForStudent_result.next()) {
				System.out.println(i++ +". " +getCoursesForStudent_result.getString(1));
				courseIDForStudents.add(getCoursesForStudent_result.getString(1));
			}
			System.out.println("Please enter Course ID for which you would like to view:");
			String courseId = scanner.next();
			viewHWForCourse(courseId,scanner);
			
			
		} catch (Exception e) {
			LOG.error("Exception while processing courses for students.getCoursesOfStudent", e);
		}
		
		return courseIDForStudents;
	}
	public static void viewHWForCourse(String courseId,Scanner scanner) {
		System.out.println("######Display HW for course: "+courseId);
		System.out.println("/n 1.Current Open HWs /n2. Past HWs");
		System.out.println("Enter 1 for Current HWs and 2 For Past HWs:");
		int HWChoice = scanner.nextInt();
		Date today = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
         String todayFormated =formatter.format(today);
        System.out.println("Today in dd-MMM-yyyy format : "+formatter.format(today));
        if (HWChoice==1) {
        	try {
    			Connection conn = Session.getConnection();
    			String getOpenHW = "select * from homework where course_id='"+courseId+"' and hw_end_date >'"+todayFormated+"'";
    			PreparedStatement ps = conn.prepareStatement(getOpenHW);
    			ResultSet getCoursesForStudent_result = ps.executeQuery();
    			List<HomeWork> listHW = convertResultSetToHWPOJO(getCoursesForStudent_result);
    			while(listHW.size() >0) {
    			for(int i=0; i<listHW.size(); i++){
    				System.out.println("Open ");
    				listHW.get(i).getHw_id();
    				}
    			}
    			
    			
    		} catch (Exception e) {
    			LOG.error("Exception while processing  open HW. viewHWForCourse", e);
    		}
        	
        }else if (HWChoice==2) {
        	
        }else if(HWChoice!=1 || HWChoice!=2) {
        	System.out.println("##########Invalid Choice########");
        	System.out.println("Enter 1 for Current HWs and 2 For Past HWs:");
    		HWChoice = scanner.nextInt();
        }
		
	}
	
	public static List<HomeWork> convertResultSetToHWPOJO(ResultSet set) {
		LOG.info("Converting ResultSet to HW POJO...");
		List<HomeWork> result = null;
		try {
			result = new ArrayList<HomeWork>();
			while (set.next()) {
				HomeWork temp = new HomeWork();

				String hw_id = set.getString("hw_id");
				temp.setHw_id(hw_id);
				String course_id = set.getString("course_id");
				temp.setCourse_id(course_id);
				String topic_id = set.getString("topic_id");
				temp.setTopic_id(topic_id);
				String HW_name = set.getString("HW_name");
				temp.setHW_name(HW_name);
				int  max_no_of_tries = set.getInt("max_no_of_tries");
				temp.setMax_no_of_tries(max_no_of_tries);
				Date hw_end_date = set.getDate("hw_end_date");
				temp.setHw_end_date(hw_end_date);
				Date hw_st_date = set.getDate("hw_st_date");
				temp.setHw_st_date(hw_st_date);
				int  correct_pts = set.getInt("correct_pts");
				temp.setCorrect_pts(correct_pts);
				int  penalty_pts = set.getInt("penalty_pts");
				temp.setPenalty_pts(penalty_pts);
				String score_policy = set.getString("score_policy");
				temp.setScore_policy(score_policy);
				int  diff_level = set.getInt("diff_level");
				temp.setDiff_level(diff_level);
				String hw_type = set.getString("hw_type");
				temp.setHw_type(hw_type);
				
				
				result.add(temp);
			}
		} catch (Exception e) {
			LOG.error("Exception while converting the Result Set to Student POJO", e);
		}
		return result;
	}

}

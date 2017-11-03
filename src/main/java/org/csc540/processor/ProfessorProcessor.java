package org.csc540.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.csc540.helper.DBFieldConstants;
import org.csc540.pojo.Course;
import org.csc540.pojo.CourseEnrollment;
import org.csc540.pojo.HomeWork;
import org.csc540.pojo.Professor;
import org.csc540.pojo.Users;
import org.csc540.session.Session;

public class ProfessorProcessor {

	public static final Logger LOG = Logger.getLogger(TeachingAssistantProcessor.class);
	public static List<Course> getCourses(String userId) {
		// TODO Auto-generated method stub
		LOG.info("Processor to get all the Courses for the Professor: " + userId);
		try {
			Connection conn = Session.getConnection();
			String prof_courses_query = "SELECT * FROM COURSE WHERE prof_uid = '" + userId + "'";
			PreparedStatement ps = conn.prepareStatement(prof_courses_query);
			ResultSet prof_courses_result = ps.executeQuery();

			List<Course> list = convertResultSetToCoursePOJO(prof_courses_result);
			return list;
		} catch (Exception e) {
			LOG.error("Exception while processing the courses for a TA.", e);
		}
		return null;
	}
	public static List<Course> convertResultSetToCoursePOJO(ResultSet set) {
		// TODO Auto-generated method stub
		LOG.info("Converting ResultSet to Course POJO...");
		List<Course> result = null;
		try {
			result = new ArrayList<Course>();
			while (set.next()) {
				Course temp = new Course();
				String profId = set.getString(DBFieldConstants.COURSE_PROF_UID);
				temp.setProfId(profId);
				String courseId = set.getString(DBFieldConstants.COURSE_COURSE_ID);
				temp.setCourseId(courseId);
				String courseName = set.getString(DBFieldConstants.COURSE_COURSE_NAME);
				temp.setCourseName(courseName);
				String courseStartDate = set.getString(DBFieldConstants.COURSE_COURSE_ST_DATE);
				temp.setCourseStartDate(courseStartDate);
				String courseEndDate = set.getString(DBFieldConstants.COURSE_COURSE_END_DATE);
				temp.setCourseEndDate(courseEndDate);
				String courseLevel = set.getString(DBFieldConstants.COURSE_LEVEL_C);
				temp.setCourseLevel(courseLevel);
				int studentsEnrollement = set.getInt(DBFieldConstants.COURSE_STUD_ENROLLED_NUM);
				temp.setStudentsEnrollement(studentsEnrollement);
				int maxEnrollment = set.getInt(DBFieldConstants.COURSE_MAX_STUD_ALLOWED);
				temp.setMaxEnrollment(maxEnrollment);
				result.add(temp);
			}
		} catch (Exception e) {
			LOG.error("Exception while converting the Result Set to Student POJO", e);
		}
		return result;
	}
	public static Professor getprof(String profID) {
		// TODO Auto-generated method stub
		LOG.info("Mapping the current Professor: " + profID);
		try {
			Connection conn = Session.getConnection();
			String prof_query = "SELECT * FROM PROFESSOR WHERE users_id = '" + profID + "'";
			PreparedStatement ps = conn.prepareStatement(prof_query);
			ResultSet prof_result = ps.executeQuery();

			List<Professor> list = convertResultSetToProfPOJO(prof_result);
			
			return list.get(0);
		} catch (Exception e) {
			LOG.error("Exception while processing the courses for a TA.", e);
		}
		return null;
	}
	private static List<Professor> convertResultSetToProfPOJO(ResultSet set) {
		// TODO Auto-generated method stub
		LOG.info("Converting ResultSet to Prof POJO...");
		List<Professor> result = null;
		try {
			result = new ArrayList<Professor>();
			while (set.next()) {
				Professor temp = new Professor();
				String profFName = set.getString(DBFieldConstants.PROFESSOR_FIRST_NAME);
				temp.setF_name(profFName);
				String profLName = set.getString(DBFieldConstants.PROFESSOR_LAST_NAME);
				temp.setL_name(profLName);
				String profID = set.getString(DBFieldConstants.PROFESSOR_USERS_ID);
				temp.setUserId(profID);
				String profAddress = set.getString(DBFieldConstants.PROFESSOR_ADDRESS);
				temp.setAddress(profAddress);
				String profPhone = set.getString(DBFieldConstants.PROFESSOR_PHONE_NUMBER);
				temp.setPhone_number(profPhone);
				String profEmail = set.getString(DBFieldConstants.PROFESSOR_EMAIL_ID);
				temp.setEmail(profEmail);
				result.add(temp);
			}
		} catch (Exception e) {
			LOG.error("Exception while converting the Result Set to Student POJO", e);
		}
		return result;
	}
	public static void enrollStudent(String student_id, String courseId) {
		// TODO Auto-generated method stub
		LOG.info("Processor to enroll a student by a professor!");
		try {
			Connection conn = Session.getConnection();
			String prof_enroll_query = "INSERT INTO COURSE_ENROLLMENT (users_id, course_id) VALUES ('"+student_id+"','"+courseId+"')";
			PreparedStatement ps = conn.prepareStatement(prof_enroll_query);
			ps.execute();
			System.out.println("QUERY SUCCESSFUL!");

		} catch (Exception e) {
			System.out.println(e);
		}

	}
	public static void dropStudent(String student_id, String courseId) {
		// TODO Auto-generated method stub
		LOG.info("Processor to drop a student by a professor!");
		try {
			Connection conn = Session.getConnection();
			String prof_drop_query = "DELETE FROM COURSE_ENROLLMENT WHERE users_id = '"+student_id+"' AND course_id = '"+courseId+"'";
			PreparedStatement ps = conn.prepareStatement(prof_drop_query);
			ps.execute();
			System.out.println("QUERY SUCCESSFUL!");

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static Boolean checkCourseDetails(String course_id, String courseName) {
		LOG.info("Processor to check course details entered by the Professor!");
		try {
			Connection conn = Session.getConnection();
			String course_query = "SELECT * FROM COURSE WHERE COURSE_ID = '" + course_id + "' OR COURSE_NAME = '"
					+ courseName + "'";
			PreparedStatement ps = conn.prepareStatement(course_query);
			ResultSet course_result = ps.executeQuery();

			List<Course> list = convertResultSetToCoursePOJO(course_result);

			if (!(list.size() == 0)) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			LOG.error("Exception..", e);
		}
		return false;
	}
	
	public static void addCourse(String course_id, String courseName, String courseStartDate, String courseEndDate,
			String profId, String courseLevel, int studentsEnrolled, String courseSize) {
		LOG.info("Processor to insert a new course by a professor!");
		try {
			Connection conn = Session.getConnection();
			String prof_addcourse_query = "INSERT INTO COURSE (COURSE_ID, COURSE_NAME, COURSE_ST_DATE, COURSE_END_DATE, PROF_UID, LEVEL_C, STUD_ENROLLED_NUM, MAX_STUD_ALLOWED) VALUES ('"
					+ course_id + "','" + courseName + "','" + courseStartDate + "','" + courseEndDate + "','" + profId
					+ "','" + courseLevel + "'," + studentsEnrolled + "," + courseSize + ")";
			PreparedStatement ps = conn.prepareStatement(prof_addcourse_query);
			System.out.println(prof_addcourse_query);
			ps.execute();
			System.out.println(prof_addcourse_query);
			System.out.println("QUERY SUCCESSFUL!");

		} catch (Exception e) {
			LOG.error("Exception...", e);
		}

	}
	
	public static List<HomeWork> getHWExcerciseForCourse(Course course) {
		try {
			Connection conn = Session.getConnection();
			String getHW = "select * from homework where course_id='"+course.getCourseId()+"'";
			PreparedStatement ps = conn.prepareStatement(getHW);
			ResultSet getHW_result = ps.executeQuery();
			List<HomeWork> listHW = StudentProcessor.convertResultSetToHWPOJO(getHW_result);
			//System.out.println("getHWExcerciseForCourse " +listHW.get(1).getHw_id());
			return listHW;
			
		} catch (Exception e) {
			LOG.error("Exception while processing  open HW. viewHWForCourse", e);
		}
		return null;
	}
	
	public static List<HomeWork> getHWExcerciseDetails(String HWId) {
		try {
			Connection conn = Session.getConnection();
			String getHW = "select * from homework where hw_id='"+HWId+"'";
			PreparedStatement ps = conn.prepareStatement(getHW);
			ResultSet getHW_result = ps.executeQuery();
			List<HomeWork> listHW = StudentProcessor.convertResultSetToHWPOJO(getHW_result);
			return listHW;
			
		} catch (Exception e) {
			LOG.error("Exception while processing  open HW. viewHWForCourse", e);
		}
		return null;
	}
	
	public static void updateHomeWork(HomeWork homeWork) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		 
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
      
         String startFormated =formatter.format(homeWork.getHw_st_date());
         String endFormated =formatter.format(homeWork.getHw_end_date());

		String updateTableSQL = "UPDATE HOMEWORK " + "SET course_id= '"
				+ homeWork.getCourse_id() + "' ,topic_id ='" + homeWork.getTopic_id() + "'" + " , HW_name='"
				+ homeWork.getHW_name() + "',max_no_of_tries=" + homeWork.getMax_no_of_tries() + "" + ",hw_st_date='"
				+ startFormated + "',hw_end_date ='" + endFormated + "'" + 
				",correct_pts="+homeWork.getCorrect_pts()+""+",penalty_pts="+homeWork.getPenalty_pts()+""+
				",score_policy='"+homeWork.getScore_policy()+"'"+",diff_level="+homeWork.getDiff_level()+""
				+ " WHERE hw_id = '"
				+ homeWork.getHw_id() + "'";

		try {
			System.out.println(updateTableSQL);
			conn = Session.getConnection();
			ps = conn.prepareStatement(updateTableSQL);
			ps.execute();
			System.out.println("HW Updated!!");
		} catch (SQLException e) {
			
			System.out.println("there was an exception while updating HW "+e.getMessage());
		}catch (Exception e) {
			
			System.out.println("there was an exception while updating  HW "+e.getMessage());
		}
		
	}
	public static void addHomeWork(String hw_id,String course_Id,String topicId,String hwName, int maxTries, String hwStartDate,String hwEndDate,int correct_pt,
			int penalty_pt,String scoring_policy,int diffLevel) throws SQLException, ParseException {
		Connection conn = null;
		PreparedStatement ps = null;
		 
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
       // Date new_start =formatter.parse(hwStartDate);
		//Date new_end =formatter.parse(hwEndDate);
		System.out.println("new_end no "+hwEndDate+ "new_end ");
      

		String insertTableSQL = "INSERT INTO HOMEWORK (hw_id, course_id, topic_id, HW_name, max_no_of_tries, hw_st_date, hw_end_date, "
				+ "correct_pts, penalty_pts, score_policy, diff_level) VALUES "
				+ "('"+hw_id+"', '"+course_Id+"', '"+topicId+"','"+hwName+"', "+maxTries
						+ ",'"+hwStartDate+"','"+hwEndDate+"', "+correct_pt+", "+penalty_pt+", "
								+ "'"+scoring_policy+"', "+diffLevel+")";

		try {
			System.out.println(insertTableSQL);
			conn = Session.getConnection();
			ps = conn.prepareStatement(insertTableSQL);
			ps.execute();
			System.out.println("HW Added!!");
		} catch (SQLException e) {
			
			System.out.println("there was an exception while Adding HW "+e.getMessage());
		}catch (Exception e) {
			
			System.out.println("there was an exception while Adding HW "+e.getMessage());
		}
		
	}
	public static void updateprofessorProfile(Professor professorUser) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;

		String updateTableSQL = "UPDATE PROFESSOR " + "SET first_name = '" + professorUser.getF_name() + "', last_name= '"
				+ professorUser.getL_name() + "' ,address ='" + professorUser.getAddress() + "'" + " , ph_no='"
				+ professorUser.getPhone_number() + "',email_id='"
				+ professorUser.getEmail() +  "' WHERE users_id = '"
				+ professorUser.getUserId() + "'";

		try {
			conn = Session.getConnection();
			ps = conn.prepareStatement(updateTableSQL);
			ps.execute();
			System.out.println("Professor Account Updated!!");
		} catch (SQLException e) {
			
			System.out.println("tthere was an exception while updating the Professor details "+e.getMessage());
		}catch (Exception e) {
			
			LOG.info("there was an exception while updating the Professor details"+e.getMessage());
		}
		
	}
	
}

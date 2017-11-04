package org.csc540.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.csc540.helper.DBFieldConstants;
import org.csc540.pojo.Course;
import org.csc540.pojo.CourseEnrollment;
import org.csc540.pojo.CourseTopicMapping;
import org.csc540.pojo.Professor;
import org.csc540.pojo.Topic;
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
			String prof_enroll_query = "INSERT INTO COURSE_ENROLLMENT (users_id, course_id) VALUES ('" + student_id
					+ "','" + courseId + "')";
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
			String prof_drop_query = "DELETE FROM COURSE_ENROLLMENT WHERE users_id = '" + student_id
					+ "' AND course_id = '" + courseId + "'";
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

	public static List<Topic> getCourseTopics(String courseId) {
		LOG.info("Processor to query the list of topics for the Course " + courseId);
		List<Topic> topiclist = new ArrayList<Topic>();
		try {
			Connection conn = Session.getConnection();
			String course_topic_map_query = "SELECT * FROM COURSE_TOPIC_MAPPING WHERE COURSE_ID='" + courseId + "'";
			PreparedStatement ps = conn.prepareStatement(course_topic_map_query);
			ResultSet course_topic_map_result = ps.executeQuery();

			List<CourseTopicMapping> ctmap = convertToCourseTopicMappingPOJO(course_topic_map_result);
//			List<Topic> topiclist = new ArrayList<Topic>();

			int i = 0;
			while (i < ctmap.size()) {
				Topic topic = new Topic();
				topic = getTopicDetails(ctmap.get(i).getTopicId());
				topiclist.add(topic);
				i++;
			}

			System.out.println("QUERY SUCCESSFUL!");

		} catch (Exception e) {
			LOG.error("Exception...", e);
		}

		return topiclist;
	}

	public static Topic getTopicDetails(String topicId) {
		LOG.info("Processor to retrieve topic_name for the topic_id "+ topicId);
		try {
			Connection conn = Session.getConnection();
			String topic_name_query = "SELECT * FROM TOPIC WHERE TOPIC_ID = '" + topicId + "'";
			PreparedStatement ps = conn.prepareStatement(topic_name_query);
			ResultSet topic_name_result = ps.executeQuery();

			List<Topic> list = convertResultSetToTopicPOJO(topic_name_result);

			if (!(list.size() == 0)) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			LOG.error("Exception..", e);
		}
		return null;
	}

	public static List<Topic> convertResultSetToTopicPOJO(ResultSet set) {
		LOG.info("Converting ResultSet to Topic POJO...");
		List<Topic> result = null;
		try {
			result = new ArrayList<Topic>();
			while (set.next()) {
				Topic temp = new Topic();
				String topicId = set.getString(DBFieldConstants.TOPIC_TOPIC_ID);
				temp.setTopicId(topicId);
				String topicName = set.getString(DBFieldConstants.TOPIC_TOPIC_NAME);
				temp.setTopicName(topicName);
				result.add(temp);
			}
		} catch (Exception e) {
			LOG.error("Exception while converting the Result Set to Topic POJO", e);
		}
		return result;
	}

	public static List<CourseTopicMapping> convertToCourseTopicMappingPOJO(ResultSet set) {
		LOG.info("Converting ResultSet to Course_Topic_Mapping POJO...");
		List<CourseTopicMapping> result = null;
		try {
			result = new ArrayList<CourseTopicMapping>();
			while (set.next()) {
				CourseTopicMapping temp = new CourseTopicMapping();
				String topicId = set.getString(DBFieldConstants.CTMAPPING_TOPIC_ID);
				temp.setTopicId(topicId);
				String courseId = set.getString(DBFieldConstants.CTMAPPING_COURSE_ID);
				temp.setCourseId(courseId);
				result.add(temp);
			}
		} catch (Exception e) {
			LOG.error("Exception while converting the Result Set to Course Topic Mapping POJO", e);
		}
		return result;
	}

	public static void addQuesToBank(String quesId, String topicId, String quesText, String quesExp, int diffLevel,
			String hint, String quesType) {
		LOG.info("Processor to add question to question bank!");
		try {
			Connection conn = Session.getConnection();
			String add_questobank_query = "INSERT INTO QUESTION (q_id, topic_id, question, q_expln, diff_level, hint, type) VALUES ('" + quesId
					+ "','" + topicId + "','" + quesText + "','" + quesExp + "','" + diffLevel + "','" + hint + "','" + quesType + "')";
			PreparedStatement ps = conn.prepareStatement(add_questobank_query);
			ps.execute();
			System.out.println("QUERY SUCCESSFUL!");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void addAnswer(String ans_id, String quesId, String is_correct, String ans) {
		
		LOG.info("Processor to add answer to answer table!");
		try {
			Connection conn = Session.getConnection();
			String add_ans_query = "INSERT INTO ANSWER (a_id, q_id, is_correct, a_expln) VALUES ('" + ans_id
					+ "','" + quesId + "','" + is_correct + "','" + ans + "')";
			System.out.println(add_ans_query);
			PreparedStatement ps = conn.prepareStatement(add_ans_query);
			ps.execute();
			System.out.println("QUERY SUCCESSFUL!");

		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	public static void addParamQues(String quesId, String param_id, String val_id, String param_name, String param_val) {
		LOG.info("Processor to add parameterized question!");
		try {
			Connection conn = Session.getConnection();
			String add_param_ques_query = "INSERT INTO QUESTION_PARAM (q_id, param_id, val_id, param_name, val) VALUES ('" + quesId
					+ "','" + param_id + "','" + val_id + "','" + param_name + "','" + param_val + "')";
			System.out.println(add_param_ques_query);
			PreparedStatement ps = conn.prepareStatement(add_param_ques_query);
			ps.execute();
			System.out.println("QUERY SUCCESSFUL!");

		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}

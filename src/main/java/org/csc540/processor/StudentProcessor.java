package org.csc540.processor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.csc540.helper.DBFieldConstants;
import org.csc540.pojo.Answer;
import org.csc540.pojo.Attempts;
import org.csc540.pojo.Attempts_info;
import org.csc540.pojo.CompletedAttempts;
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
			if (studentUser.getUserId().equals(student_id) && studentUser.getF_name().equals(student_fname)
					&& studentUser.getL_name().equals(student_lname)) {
				return true;
			}
		} catch (Exception e) {
			LOG.error("Exception while processing the given student user.", e);
		}
		return false;
	}

	public static List getCoursesOfStudent(String user_id, Scanner scanner) {
		List courseIDForStudents = new ArrayList();
		try {
			Connection conn = Session.getConnection();
			String getCoursesForStudent = "select COURSE_ID from COURSE_ENROLLMENT where users_id='" + user_id + "'";
			PreparedStatement ps = conn.prepareStatement(getCoursesForStudent);
			ResultSet getCoursesForStudent_result = ps.executeQuery();
			int i = 1;
			while (getCoursesForStudent_result.next()) {
				System.out.println(i++ + ". " + getCoursesForStudent_result.getString(1));
				courseIDForStudents.add(getCoursesForStudent_result.getString(1));
			}

		} catch (Exception e) {
			LOG.error("Exception while processing courses for students.getCoursesOfStudent", e);
		}

		return courseIDForStudents;
	}

	public static List<HomeWork> convertResultSetToHWPOJO(ResultSet set) {
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
				int max_no_of_tries = set.getInt("max_no_of_tries");
				temp.setMax_no_of_tries(max_no_of_tries);
				Date hw_end_date = set.getDate("hw_end_date");
				temp.setHw_end_date(hw_end_date);
				Date hw_st_date = set.getDate("hw_st_date");
				temp.setHw_st_date(hw_st_date);
				int correct_pts = set.getInt("correct_pts");
				temp.setCorrect_pts(correct_pts);
				int penalty_pts = set.getInt("penalty_pts");
				temp.setPenalty_pts(penalty_pts);
				String score_policy = set.getString("score_policy");
				temp.setScore_policy(score_policy);
				int diff_level = set.getInt("diff_level");
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

	public static void getAttemptsForHW(String hw_id, String user_id) {
		try {
			Connection conn = Session.getConnection();
			String getAttempt = "select distinct attempt_ID from incomplete_attempts where hw_id='" + hw_id
					+ "' and student_id='" + user_id + "'";
			System.out.println(getAttempt);
			PreparedStatement ps = conn.prepareStatement(getAttempt);
			ResultSet getAttempt_result = ps.executeQuery();
			int i = 1;
			System.out.println("Attempt For HW ID :" + hw_id);
			while (getAttempt_result.next()) {
				System.out.println(i++ + ". " + getAttempt_result.getString(1));
			}

		} catch (Exception e) {
			LOG.error("Exception while processing courses for students.getCoursesOfStudent", e);
		}

	}

	public static List<HomeWork> getPastHW(String courseId) {
		try {
			Connection conn = Session.getConnection();
			String getpastHW = "select * from past_HW_ForCourse where course_id='" + courseId + "'";
			PreparedStatement ps = conn.prepareStatement(getpastHW);
			ResultSet getCoursesForStudent_result = ps.executeQuery();
			List<HomeWork> listHW = convertResultSetToHWPOJO(getCoursesForStudent_result);
			return listHW;

		} catch (Exception e) {
			LOG.error("Exception while processing  open HW. viewHWForCourse", e);
		}
		return null;
	}

	public static List<HomeWork> getOpenHW(String courseId) {
		try {
			Connection conn = Session.getConnection();
			String getopenHW = "select * from open_HW_ForCourse where course_id='" + courseId + "'";
			// String getopenHW = "SELECT * FROM homework WHERE hw_end_date >
			// TRUNC(sysdate) AND course_id='" + courseId + "'";
			System.out.println(getopenHW);
			PreparedStatement ps = conn.prepareStatement(getopenHW);
			ResultSet getCoursesForStudent_result = ps.executeQuery();

			System.out.println(getCoursesForStudent_result.next());

			List<HomeWork> listHW = convertResultSetToHWPOJO(getCoursesForStudent_result);
			System.out.println("HW NAME : " + listHW.get(0).getHW_name());
			return listHW;

		} catch (Exception e) {
			LOG.error("Exception while processing  open HW. viewHWForCourse", e);
		}
		return null;
	}

	public static int getTotalscoreFromScoringPolicy(String hw_id, String user_id) {
		int result = 0;
		try {
			Connection conn = Session.getConnection();
			String getTotalScore = "select  cal_totalscore('" + hw_id + "','" + user_id + "') from dual";
			PreparedStatement ps = conn.prepareStatement(getTotalScore);
			// CallableStatement ps = conn.prepareCall(getTotalScore);
			// ps.registerOutParameter(1, Types.INTEGER);
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				result = set.getInt(1);
			}
			// int result = ps.getInt(1);
			return result;

		} catch (Exception e) {
			LOG.error("Exception while processing  open getTotalscoreFromScoringPolicy", e);
		}
		return 0;
	}

	public static int getCountStudentAttempt(String hw_id, String user_id) {
		int result = 0;
		try {
			Connection conn = Session.getConnection();
			String getAttemptCount = "select count(*) as count from attempts where hw_id='" + hw_id + "' and student_id='"
					+ user_id + "'";
			PreparedStatement ps = conn.prepareStatement(getAttemptCount);
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				result = set.getInt("count");
			}
			return result;

		} catch (Exception e) {
			LOG.error("Exception while processing  open getTotalscoreFromScoringPolicy", e);
		}
		return 0;
	}

	public static List<Attempts> getCompleteAttemptsdetails(String hw_id, String user_id) {
		try {
			Connection conn = Session.getConnection();
			String getattempDetails = "select * from complete_attempts_details where hw_id='" + hw_id
					+ "' and STUDENT_ID='" + user_id + "'";
			PreparedStatement ps = conn.prepareStatement(getattempDetails);
			ResultSet getattempDetails_result = ps.executeQuery();
			List<Attempts> listAttempt = convertResultSetToAttemptPOJO(getattempDetails_result);
			return listAttempt;

		} catch (Exception e) {
			LOG.error("Exception while processing  open HW. viewHWForCourse", e);
		}
		return null;
	}

	public static List<Attempts> convertResultSetToAttemptPOJO(ResultSet set) {
		List<Attempts> result = null;
		try {
			result = new ArrayList<Attempts>();
			while (set.next()) {
				Attempts temp = new Attempts();

				String hw_id = set.getString("hw_id");
				temp.setHw_id(hw_id);
				String student_id = set.getString("student_id");
				temp.setStudent_id(student_id);
				int attempt_id = set.getInt("attempt_id");
				temp.setAttempt_id(attempt_id);
				String ques_id = set.getString("ques_id");
				temp.setQues_id(ques_id);
				int value_id = set.getInt("value_id");
				temp.setValue_id(value_id);
				int score_per_ques = set.getInt("score_per_ques");
				temp.setScore_per_ques(score_per_ques);
				String ques_text = set.getString("ques_text");
				temp.setQues_text(ques_text);
				String ans_id = set.getString("ans_id");
				temp.setAns_id(ans_id);
				int total_score = set.getInt("total_score");
				temp.setTotal_score(total_score);
				result.add(temp);
			}
		} catch (Exception e) {
			LOG.error("Exception while converting the Result Set to attempts pojo", e);
		}
		return result;

	}

	public static int getAttemptsInfo(String hw_id, String user_id, String courseId) {
		int a_id = 0;
		try {
			Connection conn = Session.getConnection();
			String getAttempt = "select distinct(attempt_id) from incomplete_attempts where hw_id = '" + hw_id
					+ "' AND course_id = '" + courseId + "' AND student_id = '" + user_id + "'";

			System.out.println("GETATTEMPT = " + getAttempt);

			PreparedStatement ps = conn.prepareStatement(getAttempt);
			ResultSet set = ps.executeQuery();
			System.out.println("Get attempt executed!");
			while (set.next()) {
				a_id = set.getInt(1);
				System.out.println(a_id);
			}
		} catch (Exception e) {
			LOG.error("Exception while processing courses for students.getCoursesOfStudent", e);
		}
		return a_id;
	}

	private static List<Attempts_info> convertResultSetToAttemptInfoPOJO(ResultSet set) {
		List<Attempts_info> result = null;
		try {
			result = new ArrayList<Attempts_info>();
			while (set.next()) {
				Attempts_info temp = new Attempts_info();

				String hw_id = set.getString("hw_id");
				temp.setHw_id(hw_id);
				String student_id = set.getString("student_id");
				temp.setStudent_id(student_id);
				int attempt_id = set.getInt("attempt_id");
				temp.setAttempt_id(attempt_id);
				String ques_id = set.getString("ques_id");
				temp.setQues_id(ques_id);
				int value_id = set.getInt("value_id");
				temp.setValue_id(value_id);
				int score_per_ques = set.getInt("score_per_ques");
				temp.setScore_per_ques(score_per_ques);
				String ques_text = set.getString("ques_text");
				temp.setQues_text(ques_text);
				String ans_id = set.getString("ans_id");
				temp.setAns_id(ans_id);
				String course_id = set.getString("course_id");
				temp.setCourse_id(course_id);
				result.add(temp);
			}
		} catch (Exception e) {
			LOG.error("Exception while converting the Result Set to attempts pojo", e);
		}
		return result;
	}

	public static String retrieveQuestion(int a_id, String hw_id, String user_id, String courseId) {

		String q_id = "0";
		try {
			Connection conn = Session.getConnection();
			String retrieve_ques = "INSERT INTO temp_retrieve_ques (attempt_id,course_id,hw_id,student_id,q_id) VALUES ('"
					+ a_id + "','" + courseId + "','" + hw_id + "','" + user_id + "','" + q_id + "')";
			PreparedStatement ps = conn.prepareStatement(retrieve_ques);
			ps.execute();
			System.out.println("QUERY SUCCESSFUL!");
			String retrieve_ques_id = "SELECT q_id FROM temp_retrieve_ques WHERE course_id = '" + courseId
					+ "' AND hw_id = '" + hw_id + "'AND student_id = '" + user_id + "'AND attempt_id = '" + a_id + "'";
			Connection conn1 = Session.getConnection();
			PreparedStatement ps1 = conn1.prepareStatement(retrieve_ques_id);
			ResultSet set = ps1.executeQuery();
			while (set.next()) {
				q_id = set.getString(1);
				System.out.println(q_id);
			}
			Connection conn2 = Session.getConnection();
			String delete_temp = "TRUNCATE table temp_retrieve_ques";
			PreparedStatement ps2 = conn2.prepareStatement(delete_temp);
			ps2.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
		return q_id;
	}

	public static int addAttempt(String hw_id, String user_id, String courseId) {
		int a_id = 0;
		try {
			Connection conn = Session.getConnection();
			String add_attempt = "INSERT INTO temp_add_attempts (attempt_id,course_id,hw_id,student_id) VALUES ('"
					+ a_id + "','" + courseId + "','" + hw_id + "','" + user_id + "')";
			PreparedStatement ps = conn.prepareStatement(add_attempt);
			ps.execute();
			System.out.println("QUERY SUCCESSFUL!");
			String retrieve_attempt = "SELECT attempt_id FROM temp_add_attempts WHERE course_id = '" + courseId
					+ "' AND hw_id = '" + hw_id + "'AND student_id = '" + user_id + "'";
			Connection conn1 = Session.getConnection();
			PreparedStatement ps1 = conn1.prepareStatement(retrieve_attempt);
			ResultSet set = ps1.executeQuery();
			while (set.next()) {
				a_id = set.getInt(1);
				System.out.println(a_id);
			}
			Connection conn2 = Session.getConnection();
			String delete_temp = "TRUNCATE table temp_add_attempts";
			PreparedStatement ps2 = conn2.prepareStatement(delete_temp);
			ps2.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
		return a_id;
	}

	public static Attempts_info retreiveQuesDetails(int a_id, String hw_id, String user_id, String courseId,
			String q_id) {
		try {

			Connection conn = Session.getConnection();
			String getAttempt = "select * from ATTEMPTS_INFO where hw_id = '" + hw_id + "' AND course_id = '" + courseId
					+ "' AND student_id = '" + user_id + "' AND attempt_id = '" + a_id + "' AND ques_id = '" + q_id
					+ "'";

			PreparedStatement ps = conn.prepareStatement(getAttempt);
			ResultSet getAttempt_result = ps.executeQuery();
			List<Attempts_info> attempts_info_list = convertResultSetToAttemptInfoPOJO(getAttempt_result);
			Attempts_info attempt_info = null;
			if (attempts_info_list.size() == 1) {
				attempt_info = attempts_info_list.get(0);
				return attempt_info;
			}
		} catch (Exception e) {
			LOG.error("Exception while processing courses for students.getCoursesOfStudent", e);
		}

		return null;
	}

	public static List<Answer> retreiveAnswers(String q_id, int val_id) {
		try {
			Connection conn = Session.getConnection();
			String getAttempt = "select * from ANSWER where q_id = '" + q_id + "' AND value_id = '" + val_id + "'";

			PreparedStatement ps = conn.prepareStatement(getAttempt);
			ResultSet getAnswers = ps.executeQuery();
			List<Answer> ans_list = convertResultSetToAnswerPOJO(getAnswers);

			return ans_list;

		} catch (Exception e) {
			LOG.error("Exception while processing courses for students.getCoursesOfStudent", e);
		}

		return null;
	}

	private static List<Answer> convertResultSetToAnswerPOJO(ResultSet set) {

		List<Answer> result = null;
		try {
			result = new ArrayList<Answer>();
			while (set.next()) {
				Answer temp = new Answer();

				String q_id = set.getString("q_id");
				temp.setQ_id(q_id);
				String a_id = set.getString("a_id");
				temp.setA_id(a_id);
				int value_id = set.getInt("value_id");
				temp.setValue_id(value_id);
				String is_correct = set.getString("is_correct");
				temp.setIs_correct(is_correct);
				String a_expln = set.getString("a_expln");
				temp.setAns_explanation(a_expln);

				result.add(temp);
			}
		} catch (Exception e) {
			LOG.error("Exception while converting the Result Set to attempts pojo", e);
		}
		return result;
	}

	public static void updateAnswers(int a_id, String courseId, String hw_id, String user_id, String q_id,
			String userChoice) {
		try {
			Connection conn = Session.getConnection();
			String update_ans = "INSERT INTO temp_update_ans (attempt_id,course_id,hw_id,student_id,q_id,a_id) VALUES ('"
					+ a_id + "','" + courseId + "','" + hw_id + "','" + user_id + "','" + q_id + "','" + userChoice
					+ "')";
			PreparedStatement ps = conn.prepareStatement(update_ans);
			ps.execute();
			System.out.println("QUERY SUCCESSFUL!");
			Connection conn2 = Session.getConnection();
			String delete_temp = "TRUNCATE table temp_update_ans";
			PreparedStatement ps2 = conn2.prepareStatement(delete_temp);
			ps2.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static List<Integer> getCompleteAttemptIDs(String courseId, String user_id, String hw_id) {
		try {
			Connection conn = Session.getConnection();
			String getCompletedAttemptIDs = "select * from complete_attempts where course_id = '" + courseId + "' AND student_id = '" + user_id + "' AND hw_id = '" + hw_id + "'";

			PreparedStatement ps = conn.prepareStatement(getCompletedAttemptIDs);
			ResultSet getCompletedAttemptID = ps.executeQuery();
			List<Integer> attempt_id_list = convertResultSetToIntegerPOJO(getCompletedAttemptID);

			return attempt_id_list;

		} catch (Exception e) {
			LOG.error("Exception while processing courses for students.getCoursesOfStudent", e);
		}

		return null;
	}

	public static List<Integer> convertResultSetToIntegerPOJO(ResultSet set) {
		List<Integer> result = null;
		try {
			result = new ArrayList<Integer>();
			while (set.next()) {
				int attempt_id = set.getInt("attempt_id");
				result.add(attempt_id);
			}
		} catch (Exception e) {
			LOG.error("Exception while converting the Result Set to Integer pojo", e);
		}
		return result;
	}

	public static List<CompletedAttempts> getCompletedAttempts(int attempt_choice, String courseId, String hw_id,
			String user_id) {
		try {
			Connection conn = Session.getConnection();
			String getCompletedAttemptDetails = "select * from complete_attempts_report where attempt_id = '"+attempt_choice+"' AND course_id = '" + courseId + "' AND student_id = '" + user_id + "' AND hw_id = '" + hw_id + "'";

			PreparedStatement ps = conn.prepareStatement(getCompletedAttemptDetails);
			ResultSet getCompletedAttempts = ps.executeQuery();
			List<CompletedAttempts> attempt_details = convertResultSetToCompletedAttemptsPOJO(getCompletedAttempts);

			return attempt_details;

		} catch (Exception e) {
			LOG.error("Exception in get Completed Attempts", e);
		}

		return null;
	}

	private static List<CompletedAttempts> convertResultSetToCompletedAttemptsPOJO(ResultSet set) {
		List<CompletedAttempts> result = null;
		try {
			result = new ArrayList<CompletedAttempts>();
			while (set.next()) {
				CompletedAttempts temp = new CompletedAttempts();
				int attempt_id = set.getInt("attempt_id");
				temp.setAttempt_id(attempt_id);
				String course_id = set.getString("course_id");
				temp.setCourse_id(course_id);
				String hw_id = set.getString("hw_id");
				temp.setHw_id(hw_id);
				String student_id = set.getString("student_id");
				temp.setStudent_id(student_id);
				String ques_id = set.getString("ques_id");
				temp.setQues_id(ques_id);
				String ques_text = set.getString("ques_text");
				temp.setQues_text(ques_text);
				String ans_id = set.getString("ans_id");
				temp.setAns_id(ans_id);
				String hint = set.getString("hint");
				temp.setHint(hint);
				String a_expln = set.getString("a_expln");
				temp.setA_expln(a_expln);
				int score_per_ques = set.getInt("score_per_ques");
				temp.setScore_per_ques(score_per_ques);
				int total_score = set.getInt("total_score");
				temp.setTotal_score(total_score);
				result.add(temp);
			}
		} catch (Exception e) {
			LOG.error("Exception while converting the Result Set to Completed Attempts pojo", e);
		}
		return result;
	}
}

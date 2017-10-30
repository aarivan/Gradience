package org.csc540.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.csc540.helper.DBFieldConstants;
import org.csc540.pojo.Student;
import org.csc540.session.Session;

public class StudentProcessor {

	public static final Logger LOG = Logger.getLogger(StudentProcessor.class);

	public static Student isTA(String userId) {
		LOG.info("Processor to check if the given user: " + userId + " is TA.");
		try {
			Connection conn = Session.getConnection();
			String check_ta_query = "SELECT * FROM STUDENT WHERE USERS_ID = '" + userId;
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

	private static List<Student> convertResultSetToStudentPOJO(ResultSet set) {
		LOG.info("Converting ResultSet to Student POJO...");
		List<Student> result = null;
		try {
			result = new ArrayList<Student>();
			while (set.next()) {
				Student temp = new Student();

				String userId = set.getString(DBFieldConstants.STUDENT_USERS_ID);
				temp.setUserId(userId);
				String f_name = set.getString(DBFieldConstants.STUDENT_F_NAME);
				temp.setF_name(f_name);
				String l_name = set.getString(DBFieldConstants.STUDENT_L_NAME);
				temp.setL_name(l_name);
				String address = set.getString(DBFieldConstants.STUDENT_ADDRESS);
				temp.setAddress(address);
				String phone_number = set.getString(DBFieldConstants.STUDENT_P_NUMBER);
				temp.setPhone_number(phone_number);
				String level = set.getString(DBFieldConstants.STUDENT_LEVEL);
				temp.setLevel(level);
				String email = set.getString(DBFieldConstants.STUDENT_EMAIL);
				temp.setEmail(email);
				boolean is_ta = set.getBoolean(DBFieldConstants.STUDENT_IS_TA);
				temp.setIs_ta(is_ta);
				result.add(temp);
			}
		} catch (Exception e) {
			LOG.error("Exception while converting the Result Set to Student POJO", e);
		}
		return result;
	}

}

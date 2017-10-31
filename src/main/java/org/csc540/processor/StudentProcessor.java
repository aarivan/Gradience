package org.csc540.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	private static List<Student> convertResultSetToStudentPOJO(ResultSet set) {
		LOG.info("Converting ResultSet to Student POJO...");
		List<Student> result = null;
		try {
			result = new ArrayList<Student>();
			while (set.next()) {
				Student temp = new Student();

				String userId = set.getString(DBFieldConstants.STUDENT_USERS_ID);
				temp.setUserId(userId);
				String f_name = set.getString(DBFieldConstants.STUDENT_FIRST_NAME);
				System.out.println(f_name+"f_name");
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
	
	public static void updateStudentProfile1(String user_id, String new_F_name) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		System.out.println("updateStudentProfile"+user_id +new_F_name);
		//String updateTableSQL = "UPDATE STUDENT\n" + 
			//	"SET first_name = ?, last_name= ? ,address =? , ph_no=?,level_s=?,email_id=?,is_TA =?\n" + 
			//	"WHERE users_id = '" + user_id + "'";
				/*String updateTableSQL = "UPDATE STUDENT " + 
				"SET first_name = '"+currStudent.getF_name()+"', last_name= '"+currStudent.getL_name()+"' ,address ='"+currStudent.getAddress()+"'"
						+ " , ph_no='"+currStudent.getPhone_number()+"',level_s='"+currStudent.getLevel()+"'"
								+ ",email_id='"+currStudent.getEmail()+"',is_TA ='"+currStudent.isIs_ta()+"'"
										+ " WHERE users_id = '" + user_id + "'";*/
		String updateTableSQL ="UPDATE STUDENT SET first_name =? WHERE USERS_ID = ?";

		try {
			conn = Session.getConnection();
			ps = conn.prepareStatement(updateTableSQL);
            ps.setString(1, new_F_name);
            ps.setString(2, user_id);
            ps.execute();
            System.out.println("first name set to " + new_F_name);
			 
			
			 
			 System.out.println("connection "+conn+"   ps"+ps);

			/*ps.setString(1, currStudent.getF_name());
			ps.setString(2, currStudent.getL_name());
			ps.setString(3, currStudent.getAddress());
			ps.setString(4, currStudent.getPhone_number());
			ps.setString(5, currStudent.getLevel());
			ps.setString(6, currStudent.getEmail());
			ps.setString(7, currStudent.isIs_ta());
			ps.setString(8, user_id);*/
			
		
			
		}  catch (Exception e) {
            e.printStackTrace();
        }
		
		
	}
	
	public static void updateStudentProfile(Student currStudent) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		System.out.println("updateStudentProfile");
		//String updateTableSQL = "UPDATE STUDENT\n" + 
			//	"SET first_name = ?, last_name= ? ,address =? , ph_no=?,level_s=?,email_id=?,is_TA =?\n" + 
			//	"WHERE users_id = '" + user_id + "'";
				String updateTableSQL = "UPDATE STUDENT " + 
				"SET first_name = '"+currStudent.getF_name()+"', last_name= '"+currStudent.getL_name()+"' ,address ='"+currStudent.getAddress()+"'"
						+ " , ph_no='"+currStudent.getPhone_number()+"',level_s='"+currStudent.getLevel()+"'"
								+ ",email_id='"+currStudent.getEmail()+"',is_TA ='"+currStudent.isIs_ta()+"'"
										+ " WHERE users_id = '" + currStudent.getUserId() + "'";
		
		System.out.println(updateTableSQL);
		try {
			conn = Session.getConnection();
			ps = conn.prepareStatement(updateTableSQL);
            
            ps.execute();
            System.out.println("Student updated ");

			/*ps.setString(1, currStudent.getF_name());
			ps.setString(2, currStudent.getL_name());
			ps.setString(3, currStudent.getAddress());
			ps.setString(4, currStudent.getPhone_number());
			ps.setString(5, currStudent.getLevel());
			ps.setString(6, currStudent.getEmail());
			ps.setString(7, currStudent.isIs_ta());
			ps.setString(8, user_id);*/
			
		
			
		}  catch (Exception e) {
            e.printStackTrace();
        }
		
		
	}

}

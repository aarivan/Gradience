package org.csc540.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.csc540.pojo.Users;

public class LoginProcessor {

	public static final Logger LOG = Logger.getLogger(LoginProcessor.class);

	public static Users login(Connection dbConnection, String userid, String password) {

		try {
			System.out.println("Userid: " + userid + " ---------- password: " + password);
			// String login_query = "SELECT * FROM USERS WHERE USERS_ID = ? and
			// PASSWORD = ?";
			String login_query = "SELECT * FROM USERS WHERE USERS_ID = '" + userid + "' and PASSWORD = '" + password
					+ "'";
			// System.out.println(login_query);
			PreparedStatement ps = dbConnection.prepareStatement(login_query);
			// ps.setString(1, userid);
			// ps.setString(2, password);
			ResultSet login_result = ps.executeQuery();

			List<Users> list = convertResultSetToUsersPOJO(login_result);
			if (list.size() == 1) {
				return list.get(0);
			}
		} catch (Exception e) {
			LOG.error("Exception while logining to the system", e);
		}
		return null;
	}

	public static List<Users> convertResultSetToUsersPOJO(ResultSet set) throws SQLException {
		LOG.info("Converting ResultSet to Users POJO...Is it null?" + set.getRow());
		List<Users> result = null;
		try {
			result = new ArrayList<Users>();
			while (set.next()) {
				Users temp = new Users();
				String userId = set.getString("USERS_ID");
				// System.out.println("1: " + userId);
				temp.setUserId(userId);
				String password = set.getString("PASSWORD");
				// System.out.println("2: " + password);
				temp.setPassword(password);
				String role = set.getString("ROLE");
				// System.out.println("3: " + role);
				temp.setRole(role);
				result.add(temp);
			}
		} catch (Exception e) {
			LOG.error("Exception while converting the Result Set to Users POJO", e);
		}
		return result;
	}

}

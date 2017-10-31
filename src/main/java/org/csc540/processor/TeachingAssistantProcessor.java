package org.csc540.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.csc540.app.TeachingAssistantAccount;
import org.csc540.helper.DBFieldConstants;
import org.csc540.pojo.Course;
import org.csc540.pojo.CourseEnrollment;
import org.csc540.pojo.Student;
import org.csc540.session.Session;

import com.mysql.cj.mysqla.io.DebugBufferingPacketReader;

public class TeachingAssistantProcessor {

	public static final Logger LOG = Logger.getLogger(TeachingAssistantProcessor.class);

	public static List<CourseEnrollment> getTACourses(String userId) {
		LOG.info("Processor to get all teh Courses for the TA: " + userId);
		try {
			Connection conn = Session.getConnection();
			String ta_courses_query = "SELECT * FROM TA WHERE TA_UID = '" + userId + "'";
			PreparedStatement ps = conn.prepareStatement(ta_courses_query);
			ResultSet ta_courses_result = ps.executeQuery();

			List<CourseEnrollment> list = convertResultSetToCourseEnrollmentPOJO(ta_courses_result);
			return list;
		} catch (Exception e) {
			LOG.error("Exception while processing the courses for a TA.", e);
		}
		return null;
	}

	public static List<CourseEnrollment> convertResultSetToCourseEnrollmentPOJO(ResultSet set) {
		LOG.info("Converting ResultSet to CourseEnrollment POJO...");
		List<CourseEnrollment> result = null;
		try {
			result = new ArrayList<CourseEnrollment>();
			while (set.next()) {
				CourseEnrollment temp = new CourseEnrollment();
				String taId = set.getString(DBFieldConstants.CE_TA_ID);
				temp.setTaId(taId);
				String courseId = set.getString(DBFieldConstants.CE_COURSE_ID);
				temp.setCourseId(courseId);
				result.add(temp);
			}
		} catch (Exception e) {
			LOG.error("Exception while converting the Result Set to Student POJO", e);
		}
		return result;
	}

	public static Course getCourseDetails(String courseId) {
		LOG.info("Processor to get the Course Details for " + courseId);
		Course courseDetails = null;
		try {
			Connection conn = Session.getConnection();
			String course_details_query = "SELECT * FROM COURSE WHERE COURSE_ID = '" + courseId + "'";
			PreparedStatement ps = conn.prepareStatement(course_details_query);
			ResultSet course_details_result = ps.executeQuery();

			courseDetails = convertResultSetToCoursePOJO(course_details_result);
			
		} catch (Exception e) {
			LOG.error("Exception while processing the course details.", e);
		}

		return courseDetails;
	}

	public static Course convertResultSetToCoursePOJO(ResultSet set) {
		LOG.info("Converting ResultSet to CourseEnrollment POJO...");
		Course courseDetails = null;

		try {
			courseDetails = new Course();
			while (set.next()) {

				String courseId = set.getString(DBFieldConstants.COURSE_COURSE_ID);
				courseDetails.setCourseId(courseId);
				String courseName = set.getString(DBFieldConstants.COURSE_COURSE_NAME);
				courseDetails.setCourseName(courseName);
				String courseStartDate = set.getString(DBFieldConstants.COURSE_COURSE_ST_DATE);
				courseDetails.setCourseStartDate(courseStartDate);
				String courseEndDate = set.getString(DBFieldConstants.COURSE_COURSE_END_DATE);
				courseDetails.setCourseEndDate(courseEndDate);
				String profId = set.getString(DBFieldConstants.COURSE_PROF_UID);
				courseDetails.setProfId(profId);
				String courseLevel = set.getString(DBFieldConstants.COURSE_LEVEL_C);
				courseDetails.setCourseLevel(courseLevel);
				int studentsEnrollement = set.getInt(DBFieldConstants.COURSE_STUD_ENROLLED_NUM);
				courseDetails.setStudentsEnrollement(studentsEnrollement);
				int maxEnrollment = set.getInt(DBFieldConstants.COURSE_MAX_STUD_ALLOWED);
				courseDetails.setMaxEnrollment(maxEnrollment);

			}
		} catch (Exception e) {
			LOG.error("Exception while converting the Result Set to Student POJO", e);
		}

		return courseDetails;
	}

}

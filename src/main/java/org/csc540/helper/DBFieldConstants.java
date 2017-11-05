package org.csc540.helper;

public class DBFieldConstants {

	// STUDENT TABLE
	public static final String STUDENT_FIRST_NAME = "FIRST_NAME";
	public static final String STUDENT_LAST_NAME = "LAST_NAME";
	public static final String STUDENT_USERS_ID = "USERS_ID";
	public static final String STUDENT_ADDRESS = "ADDRESS";
	public static final String STUDENT_PHONE_NUMBER = "PH_NO";
	public static final String STUDENT_LEVEL_S = "LEVEL_S";
	public static final String STUDENT_EMAIL_ID = "EMAIL_ID";
	public static final String STUDENT_IS_TA = "IS_TA";
	
	// COURSE ENROLLMENT
	public static final String CE_TA_ID = "TA_UID";
	public static final String CE_COURSE_ID = "COURSE_ID";
	
	// COURSE
	public static final String COURSE_COURSE_ID = "COURSE_ID";
	public static final String COURSE_COURSE_NAME = "COURSE_NAME";
	public static final String COURSE_COURSE_ST_DATE = "COURSE_ST_DATE";
	public static final String COURSE_COURSE_END_DATE = "COURSE_END_DATE";
	public static final String COURSE_PROF_UID = "PROF_UID";
	public static final String COURSE_LEVEL_C = "LEVEL_C";
	public static final String COURSE_STUD_ENROLLED_NUM = "STUD_ENROLLED_NUM";
	public static final String COURSE_MAX_STUD_ALLOWED = "MAX_STUD_ALLOWED";
	
	//PROFESSOR TABLE
	public static final String PROFESSOR_FIRST_NAME = "FIRST_NAME";
	public static final String PROFESSOR_LAST_NAME = "LAST_NAME";
	public static final String PROFESSOR_USERS_ID = "USERS_ID";
	public static final String PROFESSOR_ADDRESS = "ADDRESS";
	public static final String PROFESSOR_PHONE_NUMBER = "PH_NO";
	public static final String PROFESSOR_EMAIL_ID = "EMAIL_ID";
	
	// COURSE_TOPIC_MAPPING
	public static final String CTMAPPING_TOPIC_ID = "TOPIC_ID";
	public static final String CTMAPPING_COURSE_ID = "COURSE_ID";
	
	// TOPIC
	public static final String TOPIC_TOPIC_ID = "TOPIC_ID";
	public static final String TOPIC_TOPIC_NAME = "TOPIC_NAME";
	
	// HW_QUESTIONS
	public static final String HWID = "HW_ID";
	public static final String QUESTIONID = "QUES_ID";
	
	//REPORT
	public static final String REPORT_FNAME = "FIRST_NAME";
	public static final String REPORT_LNAME = "LAST_NAME";
	public static final String REPORT_SID = "STUDENT_ID";
	public static final String REPORT_HW_ID = "HW_ID";
	public static final String REPORT_COURSE_ID = "COURSE_ID";
	public static final String REPORT_TOTAL_SCORE = "TOTAL_SCORE";

}

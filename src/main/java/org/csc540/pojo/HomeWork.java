package org.csc540.pojo;

import java.util.Date;

public class HomeWork {
	

	String hw_id;
	String course_id;
	String topic_id;
	String HW_name;
	int max_no_of_tries;
	String hw_st_date;
	String hw_end_date;
	int correct_pts;
	int penalty_pts;
	String score_policy;
	int diff_level;
	public String getHw_id() {
		return hw_id;
	}
	public void setHw_id(String hw_id) {
		this.hw_id = hw_id;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(String topic_id) {
		this.topic_id = topic_id;
	}
	public String getHW_name() {
		return HW_name;
	}
	public void setHW_name(String hW_name) {
		HW_name = hW_name;
	}
	public int getMax_no_of_tries() {
		return max_no_of_tries;
	}
	public void setMax_no_of_tries(int max_no_of_tries) {
		this.max_no_of_tries = max_no_of_tries;
	}
	

	public int getCorrect_pts() {
		return correct_pts;
	}
	public void setCorrect_pts(int correct_pts) {
		this.correct_pts = correct_pts;
	}
	public int getPenalty_pts() {
		return penalty_pts;
	}
	public void setPenalty_pts(int penalty_pts) {
		this.penalty_pts = penalty_pts;
	}
	public String getScore_policy() {
		return score_policy;
	}
	public void setScore_policy(String score_policy) {
		this.score_policy = score_policy;
	}
	public int getDiff_level() {
		return diff_level;
	}
	public void setDiff_level(int diff_level) {
		this.diff_level = diff_level;
	}
	public String getHw_st_date() {
		return hw_st_date;
	}
	public void setHw_st_date(String hw_st_date) {
		this.hw_st_date = hw_st_date;
	}
	public String getHw_end_date() {
		return hw_end_date;
	}
	public void setHw_end_date(String hw_end_date) {
		this.hw_end_date = hw_end_date;
	}


}

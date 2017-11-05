package org.csc540.pojo;

public class Report
{
	private String first_name;
	private String last_name;
	private String stud_id;
	private String hw_id;
	private String course_id;
	private int total_score;
	private String attempt_id;
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getStud_id() {
		return stud_id;
	}
	public void setStud_id(String stud_id) {
		this.stud_id = stud_id;
	}
	public String getHw_id() {
		return hw_id;
	}
	public void setHw_id(String hw_id) {
		this.hw_id = hw_id;
	}
	public int getTotal_score() {
		return total_score;
	}
	public void setTotal_score(int total_score) {
		this.total_score = total_score;
	}
	
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	
	@Override
	public String toString() {
		return "[ First Name: " + first_name + ", Last Name: " + last_name +  ", Student ID: " + stud_id + ", HW ID: " + hw_id
				+ ", COURSE ID : " + course_id + ", Attempt ID: " + attempt_id + ", Total Score: " + total_score  + " ]";
	}
	public String getAttempt_id() {
		return attempt_id;
	}
	public void setAttempt_id(String attempt_id2) {
		this.attempt_id = attempt_id2;
	}
	
}
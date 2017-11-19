package org.csc540.pojo;

public class CompletedAttempts {
	int attempt_id;
	String course_id;
	String hw_id;
	String student_id;
	String ques_id;
	String ques_text;
	String ans_id;
	String q_expln;
	String hint;
	String a_expln;
	int score_per_ques;
	int total_score;
	public int getAttempt_id() {
		return attempt_id;
	}
	public void setAttempt_id(int attempt_id) {
		this.attempt_id = attempt_id;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getHw_id() {
		return hw_id;
	}
	public void setHw_id(String hw_id) {
		this.hw_id = hw_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getQues_id() {
		return ques_id;
	}
	public void setQues_id(String ques_id) {
		this.ques_id = ques_id;
	}
	public String getQues_text() {
		return ques_text;
	}
	public void setQues_text(String ques_text) {
		this.ques_text = ques_text;
	}
	public String getAns_id() {
		return ans_id;
	}
	public void setAns_id(String ans_id) {
		this.ans_id = ans_id;
	}
	public String getQ_expln() {
		return q_expln;
	}
	public void setQ_expln(String q_expln) {
		this.q_expln = q_expln;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public String getA_expln() {
		return a_expln;
	}
	public void setA_expln(String a_expln) {
		this.a_expln = a_expln;
	}
	public int getScore_per_ques() {
		return score_per_ques;
	}
	public void setScore_per_ques(int score_per_ques) {
		this.score_per_ques = score_per_ques;
	}
	public int getTotal_score() {
		return total_score;
	}
	public void setTotal_score(int total_score) {
		this.total_score = total_score;
	}
}

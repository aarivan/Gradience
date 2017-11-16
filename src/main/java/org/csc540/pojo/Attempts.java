package org.csc540.pojo;

public class Attempts {
	String hw_id;
	String student_id;
	int attempt_id;
	String ques_id;
	String ques_text;
	String ans_id;
	int value_id;
	int score_per_ques;
	int total_score;
	String Q_EXPLN;
	
	public String getQ_EXPLN() {
		return Q_EXPLN;
	}
	public void setQ_EXPLN(String q_EXPLN) {
		Q_EXPLN = q_EXPLN;
	}
	public int getTotal_score() {
		return total_score;
	}
	public void setTotal_score(int total_score) {
		this.total_score = total_score;
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
	public int getAttempt_id() {
		return attempt_id;
	}
	public void setAttempt_id(int attempt_id) {
		this.attempt_id = attempt_id;
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
	public int getValue_id() {
		return value_id;
	}
	public void setValue_id(int value_id) {
		this.value_id = value_id;
	}
	public int getScore_per_ques() {
		return score_per_ques;
	}
	public void setScore_per_ques(int score_per_ques) {
		this.score_per_ques = score_per_ques;
	}

}

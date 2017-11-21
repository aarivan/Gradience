package org.csc540.pojo;

public class Question {
	public String getQ_id() {
		return q_id;
	}
	public void setQ_id(String q_id) {
		this.q_id = q_id;
	}
	public String getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(String topic_id) {
		this.topic_id = topic_id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQ_expln() {
		return q_expln;
	}
	public void setQ_expln(String q_expln) {
		this.q_expln = q_expln;
	}
	public int getDiff_level() {
		return diff_level;
	}
	public void setDiff_level(int diff_level) {
		this.diff_level = diff_level;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	String q_id;
	String topic_id;
	String question;
	String q_expln;
	int diff_level;
	String hint;
	String type;
}

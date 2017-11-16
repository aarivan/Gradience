package org.csc540.pojo;

public class Answer {
	private String a_id;
	private String q_id;
	private int value_id;
	private String is_correct;
	private String ans_explanation;
	public String getA_id() {
		return a_id;
	}
	public void setA_id(String a_id) {
		this.a_id = a_id;
	}
	public String getQ_id() {
		return q_id;
	}
	public void setQ_id(String q_id) {
		this.q_id = q_id;
	}
	public int getValue_id() {
		return value_id;
	}
	public void setValue_id(int value_id) {
		this.value_id = value_id;
	}
	public String getIs_correct() {
		return is_correct;
	}
	public void setIs_correct(String is_correct) {
		this.is_correct = is_correct;
	}
	public String getAns_explanation() {
		return ans_explanation;
	}
	public void setAns_explanation(String ans_explanation) {
		this.ans_explanation = ans_explanation;
	}
	
}

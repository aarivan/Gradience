package org.csc540.pojo;

public class Student {
	private String f_name;
	private String l_name;
	private String userId;
	private String address;
	private String phone_number;
	private String level;
	private String email;
	private String is_ta;

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String isIs_ta() {
		return is_ta;
	}

	public void setIs_ta(String is_ta) {
		this.is_ta = is_ta;
	}

	@Override
	public String toString() {
		return "[ User ID: " + userId + ", First Name: " + f_name + ", Last Name: " + l_name + ", Address: " + address
				+ ", Phone Number: " + phone_number + ", Level: " + level + "Is TA: " + is_ta + ", Email: " + email
				+ " ]";
	}

}

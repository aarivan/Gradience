package org.csc540.pojo;

public class Users {
	
	private String userId;
	private String password;
	private String role;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "[ User ID: " + userId + ", Password: " + password + ", Role: " + role + " ]";
	}
	
}

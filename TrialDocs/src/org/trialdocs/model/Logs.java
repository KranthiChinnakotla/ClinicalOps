package org.trialdocs.model;

public class Logs {

	private String email;
	private String role;
	private String message;
	private String date;
	
	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}

	public String getMessage() {
		return message;
	}

	public String getDate() {
		return date;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Logs(String email, String role, String message, String date) {
		super();
		this.email = email;
		this.role = role;
		this.message = message;
		this.date = date;
	}
	
	
}

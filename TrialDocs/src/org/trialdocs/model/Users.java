package org.trialdocs.model;


public class Users {

	
	private int user_id;
	
	private String name;
	
	
	private String email;
	
	
	private String password;

	private String role;
	
	
	
	
	public Users(){
		
	}

	public Users(int user_id, String name, String email, String password, String role) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}

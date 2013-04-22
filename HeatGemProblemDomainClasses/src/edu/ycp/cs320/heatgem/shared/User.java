package edu.ycp.cs320.heatgem.shared;

import java.io.Serializable;

//comment

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username;
	private String password;
	private String email;
	
	public User() {
		
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
}

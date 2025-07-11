package com.hexaware.codingchallenge.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserModel {
	@Id
	private String username;

	private String password;
	
	private String role;
	
	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserModel(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", role=" + role + "]";
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
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
	
}

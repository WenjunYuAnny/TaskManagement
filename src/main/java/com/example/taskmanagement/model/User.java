package com.example.taskmanagement.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	
	@Id
	private String id;
	
	private UserType type;
	private String firstName;
	private String lastName;
	private String email;
	private String pw;
	private Date logInTs;
	
	public User() {
		this.logInTs = new Date();
	}
	
	public User(UserType type, String firstName, String lastName, String email, String pw) {
		this.type = type;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pw = pw;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public Date getLogInTs() {
		return logInTs;
	}

	public void setLogInTs(Date logInTs) {
		this.logInTs = logInTs;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", type=" + type + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", pw=" + pw + ", logInTs=" + logInTs + "]";
	}

}

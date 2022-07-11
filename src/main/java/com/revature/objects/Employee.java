package com.revature.objects;

import java.util.Scanner;

import com.revature.utils.EmployeeDatabase;

public class Employee {
	private int userId;
	private String username;
	private String userRole;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	

	public Employee() {
		super();
	}
	

	
	public Employee(int userId, String username, String userRole, String email, String password, String firstName,
			String lastName, String address) {
		super();
		this.userId = userId;
		this.username = username;
		this.userRole = userRole;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee [userId=" + userId + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}

	
	
}

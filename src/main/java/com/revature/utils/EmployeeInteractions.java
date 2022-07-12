package com.revature.utils;

import java.util.Scanner;

import com.revature.objects.Employee;

public class EmployeeInteractions {
	EmployeeDatabase empPost = new EmployeeDatabase();
	Scanner scan = new Scanner(System.in);

	

	public boolean login(String username, String password) {

		Employee emp = empPost.getEmployee(username);
		if (emp == null) {
			return false;
		}else if (password.equals(emp.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean newAccount(String username, String password) {

		boolean usernameTaken = empPost.isUsernameTaken(username);
		if (usernameTaken) {
			return false;
		} else {
			Employee newEmp = new Employee();
			newEmp.setUsername(username);
			newEmp.setPassword(password);
			empPost.createEmployee(newEmp);
			return true;
		}

	}

	public void roleChange() {
		// TODO Extra features
	}

	public void userProfile(int userId) {
		// TODO Extra features
	}

}

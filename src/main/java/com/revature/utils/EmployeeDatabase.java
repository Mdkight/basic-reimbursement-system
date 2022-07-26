package com.revature.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.objects.Employee;

public class EmployeeDatabase{
	Connection conn;


	public Employee getEmployee(String username) {
		Employee emp =null;
		try {
			Connection conn=ConnectionUtils.getInstance().getConnection();
			PreparedStatement getEmployee = conn.prepareStatement("Select * from users where username=?");
			getEmployee.setString(1,username);
			ResultSet rs=getEmployee.executeQuery();
			while(rs.next()) {
				emp=new Employee();
				emp.setUserId(rs.getInt(1));
				emp.setUserRole(rs.getString(2));	
				emp.setEmail(rs.getString(3));				
				emp.setFirstName(rs.getString(4));
				emp.setLastName(rs.getString(5));				
				emp.setAddress(rs.getString(6));
				emp.setPassword(rs.getString(7));
				emp.setUsername(rs.getString(8));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}



	public void createEmployee(Employee e) {
		try {
			Connection conn=ConnectionUtils.getInstance().getConnection();
			PreparedStatement createEmployee = conn.prepareStatement("INSERT INTO users (username, password, userrole) VALUES (?,?,?)");
			createEmployee.setString(1, e.getUsername());
			createEmployee.setString(2, e.getPassword());
			createEmployee.setString(3, "EMPLOYEE");
			createEmployee.execute();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
	}


	public boolean updateEmployee(Employee emp) {
		
		try {
			conn=ConnectionUtils.getInstance().getConnection();
			PreparedStatement updateProfile=conn.prepareStatement("update users set email=?, firstname=?, lastname=?, address=? where user_id=?");
			updateProfile.setString(1, emp.getEmail());
			updateProfile.setString(2, emp.getFirstName());
			updateProfile.setString(3, emp.getLastName());
			updateProfile.setString(4, emp.getAddress());
			updateProfile.setInt(5, emp.getUserId());
			updateProfile.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return false;
		}
		
	}


	public void deleteEmployee(int id) {
		// TODO Extra features
		
	}


	public ArrayList<Employee> retrieveAllEmployees() {
		Employee emp =null;
		ArrayList<Employee> allEmployees=new ArrayList<Employee>();
		try {
			conn=ConnectionUtils.getInstance().getConnection();
			PreparedStatement retrieveAllEmployees =conn.prepareStatement("select * from users");
			ResultSet rs = retrieveAllEmployees.executeQuery();
			while(rs.next()) {
				emp.setUserId(rs.getInt(1));
				emp.setUserRole(rs.getString(2));
				emp.setEmail(rs.getString(3));
				emp.setFirstName(rs.getString(4));
				emp.setLastName(rs.getString(5));
				emp.setAddress(rs.getString(6));
				emp.setPassword(rs.getString(7));
				emp.setUsername(rs.getString(8));
				allEmployees.add(emp);
			}
			return allEmployees;
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isUsernameTaken(String username) {
		try {
			conn=ConnectionUtils.getInstance().getConnection();
			PreparedStatement isItTaken=conn.prepareStatement("select username from users where username=?");
			System.out.println(username);
			isItTaken.setString(1, username);
			ResultSet rs= isItTaken.executeQuery();
			int count=0;
			while(rs.next()) {
				if(rs.getString(1).equals(username)) {
					count++;
				}
			}
			if(count==0) {
				return false;
			}else {
				return true;
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
		
	}
	

	public boolean login(String username, String password) {

		Employee emp = getEmployee(username);
		if (emp == null) {
			return false;
		}else if (password.equals(emp.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean newAccount(String username, String password) {

		boolean usernameTaken = isUsernameTaken(username);
		if (usernameTaken) {
			return false;
		} else {
			Employee newEmp = new Employee();
			newEmp.setUsername(username);
			newEmp.setPassword(password);
			createEmployee(newEmp);
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

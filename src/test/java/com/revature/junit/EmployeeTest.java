package com.revature.junit;

import org.junit.jupiter.api.*;

import com.revature.objects.Employee;
import com.revature.utils.EmployeeDatabase;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeTest {
	 Employee e;
	 EmployeeDatabase ePost;
	
	@Test
	@BeforeEach
	public void createEmployee() {
	e = new Employee();
	ePost = new EmployeeDatabase();
	}
	
	@Test
	@Order(1)
	public void getEmployeeTest() {
	e = ePost.getEmployee("username");
	Assertions.assertEquals("Mike",e.getFirstName());
	System.out.println(e.getPassword());
	}
	
//	@Test
//	@Order(2)
//	public void loginTest() {
//		Employee.login();
//	}
	
	
}

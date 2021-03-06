package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.objects.Employee;
import com.revature.utils.EmployeeDatabase;

/** 
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	EmployeeDatabase empDat = new EmployeeDatabase();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.getRequestDispatcher("loginPage.html").include(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Employee emp;
		RequestDispatcher reqDisp;
		boolean success = empDat.login(username, password);

		if (success) {
			emp = empDat.getEmployee(username);
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			if (emp.getUserRole().equals("MANAGER")) {
				request.getRequestDispatcher("managermainpage.html").forward(request, response);

			} else {
				request.getRequestDispatcher("employeemainPage.html").forward(request, response);

			}

		} else {

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			request.getRequestDispatcher("loginPage.html").include(request, response);
			out.print("<p>I'm sorry, There is a problem with your username or password</p>");
			
			out.close();

		}

	}
	
	

}

package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.utils.EmployeeInteractions;

/**
 * Servlet implementation class CreateAccount
 */
public class NewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeInteractions empInt = new EmployeeInteractions();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("createAccount.html").include(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean success = empInt.newAccount(username, password);
		System.out.println("creating user " + success);

		if (success) {

			RequestDispatcher reqDisp = request.getRequestDispatcher("loginPage.html");
			reqDisp.forward(request, response);
			response.setHeader("username", username);

		} else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			request.getRequestDispatcher("createAccount.html").include(request, response);
			out.print("<p>I'm sorry, There is a problem with your username or password</p>");

			out.close();
		}

	}

}

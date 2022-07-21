package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.objects.Employee;
import com.revature.utils.EmployeeDatabase;

/**
 * Servlet implementation class UserProfile
 */
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeDatabase empDat = new EmployeeDatabase();
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		Employee emp = empDat.getEmployee((String) session.getAttribute("username"));
		out.print("<link rel=\"stylesheet\" href=\"Stylesheets/BaseStyle.css\">"
				+ " <title>Document</title>");
		out.print("<style>.centeredBox p{\r\n"
				+ "	font-size: 1.25em;\r\n"
				+ "	color:rgb(28, 26, 167);\r\n"
				+ "text-shadow: 5px 5px 13px blue;"
				+ "}</style>");
		
		out.print("    <div class=\"centeredBox\">\r\n"
				+ "        <h2>Current User profile</h2><br>\r\n"
				+ "        <h4 style=\"display: inline-block;\">Given Name: </h4><p style=\"display: inline-block;\">"+ emp.getFirstName() +"</p><br>\r\n"
				+ "        <h4 style=\"display: inline-block;\">Family Name: </h4><p style=\"display: inline-block;\">"+ emp.getLastName() +"</p><br>\r\n"
				+ "        <h4 style=\"display: inline-block;\">Email: </h4><p style=\"display: inline-block;\">"+ emp.getEmail() +"</p><br>\r\n"
				+ "        <h4 style=\"display: inline-block;\">Mailing Address: </h4><p style=\"display: inline-block;\">"+ emp.getAddress() +" </p><br>\r\n"
				+ "        <p></p>\r\n"
				+ "    </div> "
				+ "<br><br>");
		
		out.print("    <Form class=\"centeredBox\" action=\"UserProfile\" method=\"POST\">\r\n"
				+ "        <label for=\"firstname\" style=\"font-size: 1.25em;\">Given Name</label>\r\n"
				+ "        <input type=\"text\" name=\"firstname\"><br>\r\n"
				+ "        <label for=\"lastname\" style=\"font-size: 1.25em;\">Family Name</label>\r\n"
				+ "        <input type=\"text\" name=\"lastname\"><br>\r\n"
				+ "        <label for=\"email\" style=\"font-size: 1.25em;\">Email Address</label>\r\n"
				+ "        <input type=\"text\" name=\"email\"><br>\r\n"
				+ "        <label for=\"address\" style=\"font-size: 1.25em;\">Mailing address</label>\r\n"
				+ "        <input type=\"text\" name=\"address\"><br>\r\n"
				+ "        <input type=\"submit\" value=\"Update Profile\">\r\n"
				+ "    </Form>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		Employee emp = empDat.getEmployee((String) session.getAttribute("username"));
		
		emp.setFirstName((String) request.getParameter("firstname"));
		emp.setLastName((String) request.getParameter("lastname"));
		emp.setEmail((String) request.getParameter("email"));
		emp.setAddress((String) request.getParameter("address"));
		boolean sucess = empDat.updateEmployee(emp);
		
		if(sucess) {
			out.print("    <script>\r\n"
					+ "        alert(\"Update Successful\");\r\n"
					+ "    </script>");
		}else {
			out.print("    <script>\r\n"
					+ "        alert(\"Update Failed\");\r\n"
					+ "    </script>");
		}
		doGet(request, response);
	}

}

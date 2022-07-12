package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.objects.Employee;
import com.revature.objects.Ticket;
import com.revature.utils.EmployeeDatabase;
import com.revature.utils.TicketDatabase;

/**
 * Servlet implementation class SubmitTicket
 */
public class SubmitTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;

	EmployeeDatabase empPost = new EmployeeDatabase();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TicketDatabase tickDat = new TicketDatabase();
		int amount = Integer.parseInt(request.getParameter("amount"));
		String description = request.getParameter("description");
		String reimbursementType = request.getParameter("reimbursementtype");
		HttpSession session = request.getSession();
		Employee currentUser = empPost.getEmployee((String)session.getAttribute("username"));
		Ticket newTicket = new Ticket(amount, description, reimbursementType, false, currentUser.getUserId());
		boolean success = tickDat.ticketSubmit(newTicket);
		if (success) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			request.getRequestDispatcher("employeemainPage.html").include(request, response);
			out.print("<br><p>New Ticket submitted!</p>");
			
		}else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			request.getRequestDispatcher("employeemainPage.html").include(request, response);
			out.print("<br><p>Submission failed, please try again later</p>");
		}

	}

}
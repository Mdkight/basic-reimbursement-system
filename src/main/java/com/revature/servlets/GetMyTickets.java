package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
 * Servlet implementation class GetMyTickets
 */
public class GetMyTickets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeDatabase empPost = new EmployeeDatabase();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String whichSubmissions = request.getParameter("whichSubmissions");
		request.getRequestDispatcher("employeemainPage.html").include(request, response);
		out.print(
				"<br><br><table border='1' class=\"centeredBox\"><tr><th>Ticket Id</th><th>Status</th><th>Description</th><th>Category</th><th>Amount</th><th>Submitted on</th><th>Resolved on</th><th>Resolver Id</th></tr>");

		try {
			if (whichSubmissions.equalsIgnoreCase("pending")) {
				someTickets(request, response, false);
			} else if (whichSubmissions.equalsIgnoreCase("resolved")) {
				someTickets(request, response, true);
			} else {
				allTickets(request, response);
			}

			out.print("</table>");
		} catch (NullPointerException e) {
			e.getStackTrace();
			out.print("<br><p>No Tickets Found</p>");
		}
	}

	private void allTickets(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Employee currentUser = empPost.getEmployee((String) session.getAttribute("username"));
		TicketDatabase tickDat = new TicketDatabase();
		ArrayList<Ticket> ticketList = tickDat.findAllMyTickets(currentUser);
		String approvalStatus = "foo";
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String submitTime;
		String resolveTime;
		String resolver;

		for (int i = 0; i < ticketList.size(); i++) {
			if (ticketList.get(i).isAccepted() == null) {
				approvalStatus = "Pending";
				resolver = "N/A";
			} else if (ticketList.get(i).isAccepted().equals("true")) {
				approvalStatus = "Approved";
				resolver = (ticketList.get(i).getResolverId() + "");
			} else if (ticketList.get(i).isAccepted().equals("false")) {
				approvalStatus = "Denied";
				resolver = (ticketList.get(i).getResolverId() + "");
			}else {
				approvalStatus = "Questionable";
				resolver="???";
			}
			if (ticketList.get(i).getSubmitTime() != null) {
				submitTime = String.join(" ", ticketList.get(i).getSubmitTime().toString().split("T"));

			} else {
				submitTime = "N/A";
			}

			if (ticketList.get(i).getResolvedTime() != null) {
				resolveTime = String.join(" ", ticketList.get(i).getResolvedTime().toString().split("T"));

			} else {
				resolveTime = "N/A";
			}

			out.print("<br><tr><td>" + ticketList.get(i).getReimbursementId() + "</td><td>" + approvalStatus
					+ "</td><td>" + ticketList.get(i).getDescription() + "</td><td>"
					+ ticketList.get(i).getReimbursementType() + "</td><td>" + ticketList.get(i).getAmount()
					+ "</td><td>" + submitTime + "</td><td>" + resolveTime + "</td><td>" + resolver + "</td><td>");

		}

	}

	private void someTickets(HttpServletRequest request, HttpServletResponse response, boolean resolvedStatus)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Employee currentUser = empPost.getEmployee((String) session.getAttribute("username"));
		TicketDatabase tickDat = new TicketDatabase();
		ArrayList<Ticket> ticketList = tickDat.findMyTickets(currentUser, resolvedStatus);
		String approvalStatus = "foo";
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String submitTime;
		String resolveTime;
		String resolver;

		for (int i = 0; i < ticketList.size(); i++) {
			if (ticketList.get(i).isAccepted() == null) {
				approvalStatus = "Pending";
				resolver = ("N/A");
			} else if (ticketList.get(i).isAccepted().equals("true")) {
				approvalStatus = "Approved";
				resolver = (ticketList.get(i).getResolverId() + "");
			} else if (ticketList.get(i).isAccepted().equals("false")) {
				approvalStatus = "Denied";
				resolver = (ticketList.get(i).getResolverId() + "");
			}else {
				approvalStatus = "Questionable";
				resolver="???";
			}
			if (ticketList.get(i).getSubmitTime() != null) {
				submitTime = String.join(" ", ticketList.get(i).getSubmitTime().toString().split("T"));

			} else {
				submitTime = "N/A";
			}

			if (ticketList.get(i).getResolvedTime() != null) {
				resolveTime = String.join(" ", ticketList.get(i).getResolvedTime().toString().split("T"));

			} else {
				resolveTime = "N/A";
			}

			out.print("<br><tr><td>" + ticketList.get(i).getReimbursementId() + "</td><td>" + approvalStatus
					+ "</td><td>" + ticketList.get(i).getDescription() + "</td><td>"
					+ ticketList.get(i).getReimbursementType() + "</td><td>" + ticketList.get(i).getAmount()
					+ "</td><td>" + submitTime + "</td><td>" + resolveTime + "</td><td>"
					+ resolver + "</td><td>");
		}
	}

}

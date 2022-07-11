package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.objects.Ticket;
import com.revature.utils.TicketDatabase;

/**
 * Servlet implementation class SeePending
 */
public class SeePending extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TicketDatabase tickInt = new TicketDatabase();
		ArrayList<Ticket> ticketList= tickInt.checkPendingTickets();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();	
		request.getRequestDispatcher("managermainpage.html").include(request, response);
		out.print("<table border='1'><tr><th>Ticket Id</th><th>Employee Id</th><th>Description</th><th>Category</th><th>Amount</th></tr>");
		for(int i=0;i<ticketList.size();i++) {
			out.print("<br><tr><td>" + ticketList.get(i).getReimbursementId() + "</td><td>" + ticketList.get(i).getAuthorId() + "</td><td>" + ticketList.get(i).getDescription() + "</td><td>" + ticketList.get(i).getReimbursementType() + "</td><td>" + ticketList.get(i).getAmount() + "</td></tr>");
			
		}
		out.print("</table>");
		out.close();
		
	}

}

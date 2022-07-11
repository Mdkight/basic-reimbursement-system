package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.utils.TicketDatabase;

public class ApproveTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TicketDatabase tickDat = new TicketDatabase(); 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int status;
		String ticketNumber = request.getParameter("ticketNumber");
		String ticketStatus = request.getParameter("appDen");
		if(ticketStatus.equals("Approve")) {
			status = tickDat.approveTicket(Integer.parseInt(ticketNumber));
			if (status>0) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				request.getRequestDispatcher("managermainpage.html").include(request, response);
				out.print("<br><p>Ticket Approved</p>");
				
			}else {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				request.getRequestDispatcher("managermainpage.html").include(request, response);
				out.print("<br><p>Approval failed, please try again later</p>");
			}
		}else if(ticketStatus.equals("Deny")) {
			status = tickDat.declineTicket(Integer.parseInt(ticketNumber));
			if (status>0) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				request.getRequestDispatcher("managermainpage.html").include(request, response);
				out.print("<br><p>Ticket Denied</p>");
				
			}else {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				request.getRequestDispatcher("managermainpage.html").include(request, response);
				out.print("<br><p>Denial failed, please try again later</p>");
			}
		}
		

		
		
	}
}

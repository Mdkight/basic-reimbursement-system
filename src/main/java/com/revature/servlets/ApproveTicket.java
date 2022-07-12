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
		PrintWriter out = response.getWriter();
		try {
		if(ticketStatus.equals("Approve")) {
			status = tickDat.approveTicket(Integer.parseInt(ticketNumber));
			if (status>0) {
				response.setContentType("text/html");
				
				request.getRequestDispatcher("managermainpage.html").include(request, response);
				out.print("<br><p>Ticket Approved</p>");
				
			}else {
				response.setContentType("text/html");
			
				request.getRequestDispatcher("managermainpage.html").include(request, response);
				out.print("<br><p>Approval failed, please try again later</p>");
			}
		}else if(ticketStatus.equals("Deny")) {
			status = tickDat.declineTicket(Integer.parseInt(ticketNumber));
			if (status>0) {
				response.setContentType("text/html");
				
				request.getRequestDispatcher("managermainpage.html").include(request, response);
				out.print("<br><p>Ticket Denied</p>");
				
			}else {
				response.setContentType("text/html");
				
				request.getRequestDispatcher("managermainpage.html").include(request, response);
				out.print("<br><p>Denial failed, please try again later</p>");
			}
		}else {
			
			out.print("<br><p>Please enter A Ticket number and Approval decision</p>");
		}
		}catch(NullPointerException e) {
			e.getStackTrace();
			response.setContentType("text/html");
			request.getRequestDispatcher("managermainpage.html").include(request, response);
			out.print("<br><p>Please Enter A Ticket Number And Approval Decision</p>");
		}

		out.close();
		
	}
}

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();	
		
		String whichSubmissions = request.getParameter("whichSubmissions");
		request.getRequestDispatcher("employeemainPage.html").include(request, response);
		out.print("<table border='1' class=\"centeredBox\"><tr><th>Ticket Id</th><th>Accepted?</th><th>Description</th><th>Category</th><th>Amount</th></tr>");
		
		try {
		if(whichSubmissions.equalsIgnoreCase("pending")) {
			someTickets(request, response, false);
		}else if(whichSubmissions.equalsIgnoreCase("resolved")) {
			someTickets(request, response, true);
		}else {
			allTickets(request, response);
		}

		out.print("</table>");
		}catch(NullPointerException e) {
			e.getStackTrace();
			out.print("<br><p>No Tickets Found</p>");
		}
	}
	
	
	private void allTickets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		Employee currentUser = empPost.getEmployee((String)session.getAttribute("username"));
		TicketDatabase tickDat=new TicketDatabase();
		ArrayList<Ticket> ticketList= tickDat.findAllMyTickets(currentUser);
		String approvalStatus="foo";
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();	
		
				for(int i=0;i<ticketList.size();i++) {
			if (ticketList.get(i).isAccepted()==null){
				approvalStatus="pending";
			}else if(ticketList.get(i).isAccepted().equals("true")) {
				approvalStatus="approved";
			} else if(ticketList.get(i).isAccepted().equals("false")){
				approvalStatus="denied";
			}
			out.print("<br><tr><td>" + ticketList.get(i).getReimbursementId() + "</td><td>" + approvalStatus + "</td><td>" + ticketList.get(i).getDescription() + "</td><td>" + ticketList.get(i).getReimbursementType() + "</td><td>" + ticketList.get(i).getAmount() + "</td></tr>");
			
		}
		
		
	}
	
	private void someTickets(HttpServletRequest request, HttpServletResponse response, boolean resolvedStatus) throws ServletException, IOException{
		HttpSession session = request.getSession();
		Employee currentUser = empPost.getEmployee((String)session.getAttribute("username"));
		TicketDatabase tickDat=new TicketDatabase();
		ArrayList<Ticket> ticketList= tickDat.findMyTickets(currentUser, resolvedStatus);
		String approvalStatus="foo";
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();	
		
				for(int i=0;i<ticketList.size();i++) {
			if (ticketList.get(i).isAccepted()==null){
				approvalStatus="pending";
			}else if(ticketList.get(i).isAccepted().equals("true")) {
				approvalStatus="approved";
			} else if(ticketList.get(i).isAccepted().equals("false")){
				approvalStatus="denied";
			}
			out.print("<br><tr><td>" + ticketList.get(i).getReimbursementId() + "</td><td>" + approvalStatus + "</td><td>" + ticketList.get(i).getDescription() + "</td><td>" + ticketList.get(i).getReimbursementType() + "</td><td>" + ticketList.get(i).getAmount() + "</td></tr>");
			
		}
	}

}

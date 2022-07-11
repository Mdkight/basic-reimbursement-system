package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.descriptor.JspPropertyGroupDescriptor;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.manageprogram.EmployeeInteractions;
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
	EmployeeInteractions empInt = new EmployeeInteractions();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee currentUser = EmployeeInteractions.loggedInUser;
		TicketDatabase tickDat=new TicketDatabase();		
		ArrayList<Ticket> ticketList= tickDat.findMyTickets(currentUser);
		String approvalStatus="foo";
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();	
		request.getRequestDispatcher("employeemainPage.html").include(request, response);
		out.print("<table><tr><th>Ticket Id</th><th>Accepted?</th><th>Description</th><th>Category</th><th>Amount</th></tr>");
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
		out.print("</table>");
		

//		ticketList.get(i).get
		
		
		
	}

}

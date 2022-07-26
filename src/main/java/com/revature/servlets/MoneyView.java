package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.objects.Category;
import com.revature.utils.CategoryDatabase;
import com.revature.utils.TicketDatabase;

/**
 * Servlet implementation class MoneyView
 */
public class MoneyView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TicketDatabase tickDat = new TicketDatabase();
	CategoryDatabase catDat = new CategoryDatabase();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("managermainpage.html").include(request, response);
		out.print("<br><br>");
		
			
		ArrayList<Category> allCategories = catDat.getAllCategories();
		out.print("<script> console.log(\"this is line 34\")</script>");
		out.print("	<div class=\"centeredBox\"  style=\"height:500px;width:500px\">\r\n"
				+ "	<canvas id=\"myChart\"></canvas>\r\n"
				+ "	</div>"
				+ "<script>\r\n"
				+ "  var config = {\r\n"
				+ "    type: 'doughnut',\r\n"
				+ "    data: {\r\n"
				+ "    labels:[ " +findLabelSet(allCategories) +"],\r\n"
				+ "    datasets: [{\r\n"
				+ "      label: 'Expense by Category',\r\n"
				+ "      borderColor: 'rgb(0, 0, 0)',\r\n"
				+ "      data: [" + findAmountSet(allCategories) +"],\r\n"
				+"      backgroundColor:[ "+ findColorSet(allCategories) + "]\r\n"
				+"    }]\r\n"
				+ "  },\r\n"
				+ "  				  options:{\r\n"
				+ "					  plugins:{\r\n"
				+ "					  legend:{\r\n"
				+ "			                display: true,\r\n"
				+ "			                labels: {\r\n"
				+ "			                    color: 'black',\r\n"
				+ "								font: {\r\n"
				+ "									size: 16 \r\n}"
				+ "			                }\r\n"
				+ "					  }\r\n"
				+ "					  }\r\n"
				+ "				  }"
				+ "  };\r\n"
				+ "  var myChart = new Chart(\r\n"
				+ "    document.getElementById('myChart'),\r\n"
				+ "    config\r\n"
				+ "  );\r\n"
				+ "</script>"
				);
		
		

	}


	protected String findLabelSet(ArrayList<Category> allCategories) {
		String labelSet="";
		for (Category cat : allCategories) {
			String label = cat.getCategory();
			labelSet += "\'" + label + "\',";
		}

		return labelSet;

	}
	
	protected String findColorSet(ArrayList<Category> allCategories) {
		String colorSet = "";
		for (Category cat : allCategories) {
			String color = cat.getColor();
			colorSet += "\'" + color + "\',";
		}

		return colorSet;
		
		
	}
	
	protected String findAmountSet(ArrayList<Category> allCategories) {
		String amountSet = "";
		for (Category cat : allCategories) {
			int amount = cat.getAmount();
			
			amountSet +=amount+", ";
		}
		amountSet = amountSet.substring(0, amountSet.length()-2);
		return amountSet;
		
	}

}

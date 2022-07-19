package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.utils.TicketDatabase;

/**
 * Servlet implementation class MoneyView
 */
public class MoneyView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TicketDatabase tickDat = new TicketDatabase();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Double percentage;
		DecimalFormat df = new DecimalFormat("####0.0");
		request.getRequestDispatcher("managermainpage.html").include(request, response);
		out.print("<br><br>");

		// TODO get the money totals by employee ID
		TreeMap<Integer, Integer> expenseByEmployee = tickDat.reimbursementsByEmployee();
		TreeMap<Integer, Double> percentByEmployee = findPercentages(expenseByEmployee);
		out.print("<br><br>	<div class=\"centeredBox\">");
		for (Integer key : expenseByEmployee.keySet()) {
			percentage = percentByEmployee.get(key);

			out.print("<div class=\"pie\" style=\"--percentage:" + df.format(percentage) + ";\">"
					+ df.format(percentage) + "%</div>");
			out.print("");
		}
		out.print("</div>");

	}

	protected TreeMap<Integer, Double> findPercentages(TreeMap<Integer, Integer> expenseByCat) {
		TreeMap<Integer, Double> percentageByCat = new TreeMap<Integer, Double>();
		double totalExpense = 0;
		double percent;

		for (Integer key : expenseByCat.keySet()) {
			totalExpense = totalExpense + expenseByCat.get(key);
			System.out.println(totalExpense);

		}
		for (Integer key : expenseByCat.keySet()) {
			percent = (expenseByCat.get(key) / totalExpense) * 100;
			System.out.println(expenseByCat.get(key));
			System.out.println(percent);
			percentageByCat.put(key, percent);

		}

		return percentageByCat;
	}

}
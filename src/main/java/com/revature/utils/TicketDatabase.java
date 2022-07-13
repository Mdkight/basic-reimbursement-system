package com.revature.utils;

import java.sql.Connection;

import com.revature.objects.Employee;
import com.revature.objects.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class TicketDatabase {
	Statement stmt;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet;

	public boolean ticketSubmit(Ticket newTicket) {
		Timestamp now = new Timestamp(System.currentTimeMillis());

		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement submitTicket = conn.prepareStatement(
					"insert into reimbursements (accepted, amount, description, reimbursementtype, resolved, submittime, authorid) values (?,?,?,?,?,?,?)");
			submitTicket.setString(1, null);
			submitTicket.setInt(2, newTicket.getAmount());
			submitTicket.setString(3, newTicket.getDescription());
			submitTicket.setString(4, newTicket.getReimbursementType());
			submitTicket.setBoolean(5, false);
			submitTicket.setTimestamp(6, now);
			submitTicket.setInt(7, newTicket.getAuthorId());

			submitTicket.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<Ticket> checkPendingTickets() {
		ArrayList<Ticket> pendingTickets = new ArrayList<Ticket>();

		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reimbursements WHERE accepted is null");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				int reimId = rs.getInt(1);
				String accepted = rs.getString(2);
				int amount = rs.getInt(3);
				String description = rs.getString(4);
				String reimType = rs.getString(5);
				Timestamp resolveTime = rs.getTimestamp(6);
				boolean resolved = rs.getBoolean(7);
				Timestamp submitTime = rs.getTimestamp(8);
				int authorId = rs.getInt(9);
				int resolverId = rs.getInt(10);
				Ticket ticket = new Ticket(reimId, accepted, amount, description, reimType, resolved, authorId,
						resolverId, resolveTime, submitTime);

				pendingTickets.add(ticket);

			}
			return pendingTickets;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Ticket> findMyTickets(Employee emp, boolean resolvedStatus) {
		ArrayList<Ticket> pendingTickets = new ArrayList<Ticket>();
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement fetchTickets = conn
					.prepareStatement("SELECT * FROM reimbursements where authorid=? AND resolved=?");
			fetchTickets.setInt(1, emp.getUserId());
			fetchTickets.setBoolean(2, resolvedStatus);
			ResultSet rs = fetchTickets.executeQuery();
			while (rs.next()) {

				int reimId = rs.getInt(1);
				String accepted = rs.getString(2);
				int amount = rs.getInt(3);
				String description = rs.getString(4);
				String reimType = rs.getString(5);
				Timestamp resolveTime = rs.getTimestamp(6);
				boolean resolved = rs.getBoolean(7);
				Timestamp submitTime = rs.getTimestamp(8);
				int authorId = rs.getInt(9);
				int resolverId = rs.getInt(10);
				Ticket ticket = new Ticket(reimId, accepted, amount, description, reimType, resolved, authorId,
						resolverId, resolveTime, submitTime);

				pendingTickets.add(ticket);

			}
			return pendingTickets;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pendingTickets;
	}

	public ArrayList<Ticket> findAllMyTickets(Employee emp) {
		ArrayList<Ticket> pendingTickets = new ArrayList<Ticket>();
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement fetchTickets = conn.prepareStatement("SELECT * FROM reimbursements where authorid=?");
			fetchTickets.setInt(1, emp.getUserId());
			ResultSet rs = fetchTickets.executeQuery();
			while (rs.next()) {
			
			
				int reimId = rs.getInt(1);
				String accepted = rs.getString(2);
				int amount = rs.getInt(3);
				String description = rs.getString(4);
				String reimType = rs.getString(5);
				Timestamp resolveTime = rs.getTimestamp(6);
				boolean resolved = rs.getBoolean(7);
				Timestamp submitTime = rs.getTimestamp(8);
				int authorId = rs.getInt(9);
				int resolverId = rs.getInt(10);
				Ticket ticket = new Ticket(reimId, accepted, amount, description, reimType, resolved, authorId,
						resolverId, resolveTime, submitTime);

				pendingTickets.add(ticket);

			}
			return pendingTickets;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pendingTickets;
	}

	public void reimburseTotal(String user) {

		try {
			int userId = Integer.parseInt(user);

		} catch (NumberFormatException e) {
			String userIds = user;
		}

	}

	public int approveTicket(int ticketId, Employee resolver) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement approveTicket = conn.prepareStatement(
					"update reimbursements set accepted=true, resolved=true, resolverid=?, resolvetime=? where reimbursementid=?");
			approveTicket.setInt(1, resolver.getUserId());
			approveTicket.setInt(2, ticketId);
			approveTicket.setTimestamp(3, now);
			return approveTicket.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}

	public int declineTicket(int ticketId, Employee resolver) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement approveTicket = conn.prepareStatement(
					"update reimbursements set accepted=false, resolved=true, resolverid=?, resolvetime=? where reimbursementid=?");
			approveTicket.setInt(1, resolver.getUserId());
			approveTicket.setInt(2, ticketId);
			approveTicket.setTimestamp(3, now);
			return approveTicket.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}

}

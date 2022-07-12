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
		Ticket ticket = new Ticket();
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reimbursements WHERE accepted is null");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ticket.setReimbursementId(rs.getInt(1));
				ticket.setAccepted(rs.getString(2));
				ticket.setAmount(rs.getInt(3));
				ticket.setDescription(rs.getString(4));
				ticket.setReimbursementType(rs.getString(5));
				ticket.setResolvedTime(rs.getTimestamp(6));
				ticket.setResolved(rs.getBoolean(7));
				ticket.setSubmitTime(rs.getTimestamp(8));
				ticket.setAuthorId(rs.getInt(9));
				ticket.setResolverId(rs.getInt(10));

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
		Ticket ticket = new Ticket();
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement fetchTickets = conn
					.prepareStatement("SELECT * FROM reimbursements where authorid=?, resolved=?");
			fetchTickets.setInt(1, emp.getUserId());
			fetchTickets.setBoolean(2, resolvedStatus );
			ResultSet rs = fetchTickets.executeQuery();
			while (rs.next()) {
				ticket.setReimbursementId(rs.getInt(1));
				ticket.setAccepted(rs.getString(2));
				ticket.setAmount(rs.getInt(3));
				ticket.setDescription(rs.getString(4));
				ticket.setReimbursementType(rs.getString(5));
				ticket.setResolvedTime(rs.getTimestamp(6));
				ticket.setResolved(rs.getBoolean(7));
				ticket.setSubmitTime(rs.getTimestamp(8));
				ticket.setAuthorId(rs.getInt(9));
				ticket.setResolverId(rs.getInt(10));

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
		Ticket ticket = new Ticket();
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement fetchTickets = conn
					.prepareStatement("SELECT * FROM reimbursements where authorid=?");
			fetchTickets.setInt(1, emp.getUserId());
			ResultSet rs = fetchTickets.executeQuery();
			while (rs.next()) {
				ticket.setReimbursementId(rs.getInt(1));
				ticket.setAccepted(rs.getString(2));
				ticket.setAmount(rs.getInt(3));
				ticket.setDescription(rs.getString(4));
				ticket.setReimbursementType(rs.getString(5));
				ticket.setResolvedTime(rs.getTimestamp(6));
				ticket.setResolved(rs.getBoolean(7));
				ticket.setSubmitTime(rs.getTimestamp(8));
				ticket.setAuthorId(rs.getInt(9));
				ticket.setResolverId(rs.getInt(10));

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
	
	public int approveTicket(int ticketId) {
		
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement approveTicket = conn.prepareStatement("update reimbursements set accepted=true where reimbursementid=?");
			approveTicket.setInt(1, ticketId);
			return approveTicket.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int declineTicket(int ticketId) {
		
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement approveTicket = conn.prepareStatement("update reimbursements set accepted=false where reimbursementid=?");
			approveTicket.setInt(1, ticketId);
			return approveTicket.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}

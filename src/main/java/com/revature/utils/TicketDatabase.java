package com.revature.utils;

import java.sql.Connection;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.Optional;


import com.revature.objects.Employee;
import com.revature.objects.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDateTime;


public class TicketDatabase {
	Statement stmt;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet;

	public boolean ticketSubmit(Ticket newTicket) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		System.out.println();
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
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reimbursements WHERE accepted is null order by reimbursementid desc");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				LocalDateTime resolveTime;
				LocalDateTime submitTime;
				int reimId = rs.getInt(1);
				String accepted = rs.getString(2);
				int amount = rs.getInt(3);
				String description = rs.getString(4);
				String reimType = rs.getString(5);
				Optional<Timestamp> resolveTimestamp = Optional.ofNullable(rs.getTimestamp(6));
				boolean resolved = rs.getBoolean(7);
				Optional<Timestamp> submitTimestamp = Optional.ofNullable(rs.getTimestamp(8));
				int authorId = rs.getInt(9);
				int resolverId = rs.getInt(10);				
				
				if(resolveTimestamp.isPresent()) {
					resolveTime = resolveTimestamp.get().toLocalDateTime().truncatedTo(ChronoUnit.MINUTES);
				}else {
					resolveTime = null;
				}
				
				if(submitTimestamp.isPresent()) {
					submitTime = submitTimestamp.get().toLocalDateTime().truncatedTo(ChronoUnit.MINUTES);
				}else {
					submitTime = null;
				}	
//				resolveTime =resolveTimestamp.ifPresent(toLocalDateTime().truncatedTo(ChronoUnit.MINUTES));
				
				Ticket ticket = new Ticket(reimId, accepted, amount, description, reimType, resolved, authorId,
						resolverId, resolveTime , submitTime);

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
					.prepareStatement("SELECT * FROM reimbursements where authorid=? AND resolved=? order by reimbursementid desc");
			fetchTickets.setInt(1, emp.getUserId());
			fetchTickets.setBoolean(2, resolvedStatus);
			ResultSet rs = fetchTickets.executeQuery();
			while (rs.next()) {
				LocalDateTime resolveTime;
				LocalDateTime submitTime;
				int reimId = rs.getInt(1);
				String accepted = rs.getString(2);
				int amount = rs.getInt(3);
				String description = rs.getString(4);
				String reimType = rs.getString(5);
				Optional<Timestamp> resolveTimestamp = Optional.ofNullable(rs.getTimestamp(6));
				boolean resolved = rs.getBoolean(7);
				Optional<Timestamp> submitTimestamp = Optional.ofNullable(rs.getTimestamp(8));
				int authorId = rs.getInt(9);
				int resolverId = rs.getInt(10);				
				
				if(resolveTimestamp.isPresent()) {
					resolveTime = resolveTimestamp.get().toLocalDateTime().truncatedTo(ChronoUnit.MINUTES);
				}else {
					resolveTime = null;
				}
				
				if(submitTimestamp.isPresent()) {
					submitTime = submitTimestamp.get().toLocalDateTime().truncatedTo(ChronoUnit.MINUTES);
				}else {
					submitTime = null;
				}		
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
			PreparedStatement fetchTickets = conn.prepareStatement("SELECT * FROM reimbursements where authorid=? order by reimbursementid desc");
			fetchTickets.setInt(1, emp.getUserId());
			ResultSet rs = fetchTickets.executeQuery();
			while (rs.next()) {
				LocalDateTime resolveTime;
				LocalDateTime submitTime;
				int reimId = rs.getInt(1);
				String accepted = rs.getString(2);
				int amount = rs.getInt(3);
				String description = rs.getString(4);
				String reimType = rs.getString(5);
				Optional<Timestamp> resolveTimestamp = Optional.ofNullable(rs.getTimestamp(6));
				boolean resolved = rs.getBoolean(7);
				Optional<Timestamp> submitTimestamp = Optional.ofNullable(rs.getTimestamp(8));
				int authorId = rs.getInt(9);
				int resolverId = rs.getInt(10);				
				
				if(resolveTimestamp.isPresent()) {
					resolveTime = resolveTimestamp.get().toLocalDateTime().truncatedTo(ChronoUnit.MINUTES);
				}else {
					resolveTime = null;
				}
				
				if(submitTimestamp.isPresent()) {
					submitTime = submitTimestamp.get().toLocalDateTime().truncatedTo(ChronoUnit.MINUTES);
				}else {
					submitTime = null;
				}	
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
			approveTicket.setTimestamp(2, now);			
			approveTicket.setInt(3, ticketId);
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
			approveTicket.setTimestamp(2, now);
			approveTicket.setInt(3, ticketId);
			return approveTicket.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}
	


}

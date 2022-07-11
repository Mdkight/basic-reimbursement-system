package com.revature.objects;

import java.sql.Timestamp;

public class Ticket {
	private int reimbursementId;
	private String accepted;
	private int amount;
	private String description;
	private String reimbursementType;
	private boolean resolved;
	private int authorId;
	private int resolverId;
	private Timestamp resolvedTime;
	private Timestamp submitTime;
	
	public Ticket() {
		super();
	}
	public Ticket(int reimbursementId, String accepted, int amount, String description,
			String reimbursementType, boolean resolved, int authorId, int resolverId, Timestamp resolvedTime,
			Timestamp submitTime) {
		super();
		this.reimbursementId = reimbursementId;
		this.accepted = accepted;
		this.amount = amount;
		this.description = description;
		this.reimbursementType = reimbursementType;
		this.resolved = resolved;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.resolvedTime = resolvedTime;
		this.submitTime = submitTime;
	}

	
	public Ticket(int amount, String description, boolean resolved, int authorId) {
		super();
		this.amount = amount;
		this.description = description;
		this.resolved = resolved;
		this.authorId = authorId;
	}
	
	public Ticket(int amount, String description, String reimbursementType, boolean resolved, int authorId) {
		super();
		this.amount = amount;
		this.description = description;
		this.reimbursementType = reimbursementType;
		this.resolved = resolved;
		this.authorId = authorId;
	}
	public int getReimbursementId() {
		return reimbursementId;
	}
	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public String isAccepted() {
		return accepted;
	}
	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReimbursementType() {
		return reimbursementType;
	}
	public void setReimbursementType(String reimbursementType) {
		this.reimbursementType = reimbursementType;
	}
	public boolean isResolved() {
		return resolved;
	}
	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getResolverId() {
		return resolverId;
	}
	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}
	public Timestamp getResolvedTime() {
		return resolvedTime;
	}
	public void setResolvedTime(Timestamp resolvedTime) {
		this.resolvedTime = resolvedTime;
	}
	public Timestamp getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Timestamp submitTime) {
		this.submitTime = submitTime;
	}
	@Override
	public String toString() {
		return "Reimbursements [reimbursementId=" + reimbursementId + ", accepted=" + accepted + ", amount=" + amount
				+ ", description=" + description + ", reimbursementType=" + reimbursementType + ", resolved=" + resolved
				+ ", authorId=" + authorId + ", resolverId=" + resolverId + ", resolvedTime=" + resolvedTime
				+ ", submitTime=" + submitTime + "]";
	}
	
	

}

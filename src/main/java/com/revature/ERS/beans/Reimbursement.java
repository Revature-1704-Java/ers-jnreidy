package com.revature.ERS.beans;

public class Reimbursement {
	private int id;
	private int eid;
	private int amount;
	private String status;
	
	public Reimbursement() {
		super();
	}
	public Reimbursement(int id, int eid, int amount, String status) {
		super();
		this.id = id;
		this.eid = eid;
		this.amount = amount;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", eid=" + eid + ", amount=" + amount + ", status=" + status + "]";
	}
	
	
}

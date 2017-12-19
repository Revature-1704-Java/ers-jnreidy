package com.revature.ERS;

import java.util.List;

import com.revature.ERS.beans.Reimbursement;

public interface ERSDaoInterface {
	public List<Reimbursement> getEmployeeReimbursments(int id);
	public List<Reimbursement> getManagerViewReimbursments();
	public boolean isManager(int id);
	public boolean checkLogin(String password, int id);
	public void createReimbursement(int eid, int amount, String status);
	public boolean userExists(int id);
}
package com.revature.ERS;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.revature.ERS.beans.Reimbursement;

public class ERSAppTest {
	public class ErsDummyDao implements ERSDaoInterface {

		@Override
		public List<Reimbursement> getEmployeeReimbursments(int id) {
			
			return null;
		}

		@Override
		public List<Reimbursement> getManagerViewReimbursments() {
			
			return null;
		}

		@Override
		public boolean isManager(int id) {
			if(id == 1) {
				return true;
			} return false;
		}

		@Override
		public boolean checkLogin(String password, int id) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void createReimbursement(int eid, int amount, String status) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean userExists(int id) {
			// TODO Auto-generated method stub
			return false;
		}
	
	}
	@Test
	public void testdoCommand() {
		ERSApp ers = new ERSApp();
		ERSDaoInterface dao = new ErsDummyDao();
		assertEquals(true, ers.doCommand("quit", 3, dao));
		assertEquals(false, ers.doCommand("view", 3, dao));
		assertEquals(false, ers.doCommand("create", 3, dao));
		assertEquals(false, ers.doCommand("kdfjdakjf", 3, dao));
	}
}

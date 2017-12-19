package com.revature.ERS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.ERS.beans.Reimbursement;
import com.revature.ERS.util.ConnectionUtil;

public class ErsDao {
	public List<Reimbursement> getEmployeeReimbursments(int id){
		PreparedStatement prepstate;
		List<Reimbursement> reimburse = new ArrayList<>();
		String sql = "select * from reimbursements where eid = ?";
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			prepstate.setInt(1, id);
			ResultSet rs = prepstate.executeQuery();
			
			while(rs.next()) {
				int rid = rs.getInt("id");
				int eid = rs.getInt("eid");
				int amount = rs.getInt("amount");
				String status = rs.getString("status");
				
				Reimbursement r = new Reimbursement(rid, eid, amount, status);
				reimburse.add(r);
			}
			rs.close();
			prepstate.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.getMessage();
		}
		return reimburse;
	}
	public List<Reimbursement> getManagerViewReimbursments(){
		PreparedStatement prepstate;
		List<Reimbursement> reimburse = new ArrayList<>();
		String sql = "select * from reimbursements";
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			ResultSet rs = prepstate.executeQuery();
			while(rs.next()) {
				int rid = rs.getInt("id");
				int eid = rs.getInt("eid");
				int amount = rs.getInt("amount");
				String status = rs.getString("status");
				
				Reimbursement r = new Reimbursement(rid, eid, amount, status);
				reimburse.add(r);
			}
			rs.close();
			prepstate.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.getMessage();
		}
		return reimburse;
	}
	public boolean isManager(int id) {
		PreparedStatement prepstate;
		String sql = "select manager from employees where id = ?";
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			prepstate.setInt(1, id);
			ResultSet rs = prepstate.executeQuery();
			rs.next();
			int mng = rs.getInt("manager");
			if(mng == 1) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	public boolean checkLogin(String password, int id) {
		PreparedStatement prepstate;
		String sql = "select pwd from employees where id = ?";
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			prepstate.setInt(1, id);
			ResultSet rs = prepstate.executeQuery();
			if(rs.next()) {
				String pwd = rs.getString("pwd");
				if(pwd.equals(password)) {
					return true;
				}
				else {
					return false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	public void createReimbursement(int eid, int amount, String status) {
		PreparedStatement prepstate;
		String sql = "insert into reimbursements(eid, amount, status) values (?, ?, ?)";
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			prepstate.setInt(1,  eid);
			prepstate.setInt(2, amount);
			prepstate.setString(3, status);
			prepstate.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public boolean userExists(int id) {
		PreparedStatement prepstate;
		String sql = "select * from employees where id = ?";
		try(Connection conn = ConnectionUtil.getConnection()){
			prepstate = conn.prepareStatement(sql);
			prepstate.setInt(1, id);
			ResultSet rs = prepstate.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

}

package com.revature.ERS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.ERS.beans.Reimbursement;
import com.revature.ERS.commands.Commands;
import com.revature.ERS.dao.ErsDao;

public class ERSApp 
{
    public static void main( String[] args )
    {
    		ERSDaoInterface dao = new ErsDao();
    		ERSApp session = new ERSApp();
    		session.start(dao);
    }

    private void start(ERSDaoInterface dao){
    		Commands c = new Commands();
    		System.out.println("Welcome please login");
    		boolean stop = false;
    		Scanner sc = new Scanner(System.in);
    		int logint = 0;
    		while(!stop){
    			String input;
    			while(logint == 0) {
    				logint = login(dao);
    			}
    			System.out.println("create or view reimbursements");
    			input = sc.nextLine();
    			if(input.equals("logout")) {
    				logint = 0;
    				continue;
    			}
    			c.parse(input);
    			stop = doCommand(input, logint, dao);
    		}
    		sc.close();
    }
    
    private int login(ERSDaoInterface dao){
    		Scanner sc = new Scanner(System.in);
    		System.out.println("Please enter your unique id number");
    		String strid = sc.nextLine();
    		int id = Integer.parseInt(strid);
    		System.out.println("Please enter your password");
    		String pwd = sc.nextLine();
    		if(dao.checkLogin(pwd, id)) {
    			System.out.println("Logged In!");
    			return id;
    		} else {
    			System.out.println("invalid login credentials");
    			return 0;
    		}		
    }
   
    private void view(int log, ERSDaoInterface dao) {
    		List<Reimbursement> ret = new ArrayList<>();
    		Scanner sc = new Scanner(System.in);
    		if(dao.isManager(log)) {
    			System.out.println("Would you like to view all as manager?");
    			String input = sc.nextLine();
    			if(input.equals("yes")) {
    				ret = dao.getManagerViewReimbursments();
    			}
    			else {
    				ret = dao.getEmployeeReimbursments(log);
    			}
    		} else {
    			ret = dao.getEmployeeReimbursments(log);
    		}
    		System.out.println("Here are your current Reimbursements");
    		System.out.println(ret);
    }
    
    private void create(int log, ERSDaoInterface dao) {    		
    		Scanner sc = new Scanner(System.in);
    		System.out.println("enter the amount for reimbursement");
    		String str = sc.nextLine();
    		int amount = Integer.parseInt(str);
    		dao.createReimbursement(log, amount, "pending");
    		System.out.println("added a reimbursement form for you!");
    }
    
    public boolean doCommand(String input, int logint, ERSDaoInterface dao) {
    		if(input.equals("view")) {
    			view(logint, dao); 
    			return false;
    		} else if(input.equals("create")){
    			create(logint, dao);
    			return false;
    		} else if(input.equals("quit")){
    			return true;
    		} else {
    			return false;
    		}
    }
}

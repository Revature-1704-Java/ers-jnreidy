package com.revature.ERS.commands;

public class Commands {
	private String[] commands = {"view", "create", "quit"};
    private boolean isCommand(String command){
	for(int i = 0; i < commands.length; i++){
            if(command.equals(commands[i])){
		return true;
	    }
	}
	return false;
    }
	
    public String parse(String input){
	//	String input;
	//	input = sc.nextLine();
	if(isCommand(input)){
	    return input;
	} else {
	    System.out.println("Not a valid command");
	    String str = "";
	    return str;
	}
    }
}

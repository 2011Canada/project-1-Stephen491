package com.revature.exceptions;

public class InvalidAccountException extends Throwable{
	
	
	public InvalidAccountException() {
		super("Account was not found, please try again");
		
	}
	
	public String toString() {
		return this.getMessage();
	}
}

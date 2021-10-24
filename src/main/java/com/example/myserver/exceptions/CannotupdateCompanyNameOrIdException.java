package com.example.myserver.exceptions;

public class CannotupdateCompanyNameOrIdException extends Exception {
	private static final long serialVersionUID=1L;
	public CannotupdateCompanyNameOrIdException() {
		super("manager cannot update company name or id");
	}
}

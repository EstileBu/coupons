package com.example.myserver.exceptions;

public class CompanyExsistsException extends Exception {
	private static final long serialVersionUID=1L;
	public CompanyExsistsException() {
		super("company already exist");
	}

}

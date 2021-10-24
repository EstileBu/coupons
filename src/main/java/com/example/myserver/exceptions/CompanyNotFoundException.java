package com.example.myserver.exceptions;

public class CompanyNotFoundException extends Exception {
	private static final long serialVersionUID=1L;
	public CompanyNotFoundException() {
		super("company to update not found");
	}
}

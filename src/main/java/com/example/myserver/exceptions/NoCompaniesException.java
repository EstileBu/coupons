package com.example.myserver.exceptions;

public class NoCompaniesException extends Exception {
	private static final long serialVersionUID=1L;
	public NoCompaniesException() {
		super("no companies found");
	}
}

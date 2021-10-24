package com.example.myserver.exceptions;

public class CustomerExsistsException extends Exception {
	private static final long serialVersionUID=1L;
	public CustomerExsistsException() {
		super("customer already exist");
	}

}

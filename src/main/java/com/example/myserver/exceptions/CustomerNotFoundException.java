package com.example.myserver.exceptions;

public class CustomerNotFoundException extends Exception {
	private static final long serialVersionUID=1L;
	public CustomerNotFoundException() {
		super("customer not found");
	}
}

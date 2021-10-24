package com.example.myserver.exceptions;

public class NoCustomersException extends Exception {
	private static final long serialVersionUID=1L;
	public NoCustomersException() {
		super("no customers found");
	}
}

package com.example.myserver.exceptions;

public class CannotupdateCustomerIdException extends Exception {
	private static final long serialVersionUID=1L;
	public CannotupdateCustomerIdException() {
		super("cannot update customer id");
	}
}

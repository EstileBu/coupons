package com.example.myserver.exceptions;

public class NoCustomerCouponsException extends Exception {
	private static final long serialVersionUID=1L;
	public NoCustomerCouponsException() {
		super("no customer coupons ");
	}
}

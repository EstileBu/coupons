package com.example.myserver.exceptions;

public class NoCouponsException extends Exception {
	private static final long serialVersionUID=1L;
	public NoCouponsException() {
		super("coupons not found");
	}
}

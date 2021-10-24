package com.example.myserver.exceptions;

public class NoCouponsByCategoryException extends Exception {
	private static final long serialVersionUID=1L;
	public NoCouponsByCategoryException() {
		super("coupons by category not found");
	}
}

package com.example.myserver.exceptions;

public class CouponExistsException extends Exception {
	private static final long serialVersionUID=1L;
	public CouponExistsException() {
		super("coupon already exists");
	}
}

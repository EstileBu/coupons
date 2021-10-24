package com.example.myserver.exceptions;

public class CouponNotfoundException extends Exception {
	private static final long serialVersionUID=1L;
	public CouponNotfoundException() {
		super("coupon not found");
	}
}

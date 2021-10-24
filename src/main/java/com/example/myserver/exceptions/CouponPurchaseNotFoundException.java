package com.example.myserver.exceptions;

public class CouponPurchaseNotFoundException extends Exception {
	private static final long serialVersionUID=1L;
	public CouponPurchaseNotFoundException() {
		super("coupon purchase not found");
	}

}

package com.example.myserver.exceptions;

public class CouponAllreadyBeenPurchasedException extends Exception {
	private static final long serialVersionUID=1L;
	public CouponAllreadyBeenPurchasedException() {
		super("coupon already been purchased");
	}
}

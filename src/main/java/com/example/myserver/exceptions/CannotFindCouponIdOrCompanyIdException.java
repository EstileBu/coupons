package com.example.myserver.exceptions;

public class CannotFindCouponIdOrCompanyIdException extends Exception {
	private static final long serialVersionUID=1L;
	public CannotFindCouponIdOrCompanyIdException() {
		super("cannot find coupon id or company id");
	}
}

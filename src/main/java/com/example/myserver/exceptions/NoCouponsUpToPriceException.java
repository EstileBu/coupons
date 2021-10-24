package com.example.myserver.exceptions;

public class NoCouponsUpToPriceException extends Exception {
	private static final long serialVersionUID=1L;
	public NoCouponsUpToPriceException() {
		super("coupons up to price not found");
	}
}

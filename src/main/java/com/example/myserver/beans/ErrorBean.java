package com.example.myserver.beans;

public class ErrorBean {

	private int errorNumber;
	private String errorName;
	private String errorMessage;
	
	public ErrorBean(int errorNumber, String errorName, String errorMassage) {
	
		this.errorNumber = errorNumber;
		this.errorName = errorName;
		this.errorMessage = errorMassage;
	}

	public int getErrorNumber() {
		return errorNumber;
	}

	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	public String getErrorMassage() {
		return errorMessage;
	}

	public void setErrorMassage(String errorMassage) {
		this.errorMessage = errorMassage;
	}
}

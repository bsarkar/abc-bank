package com.abc;

public class InsufficientFund extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InsufficientFund() {

	}
	public InsufficientFund(String msg) {
		super(msg) ;
	}

	public InsufficientFund(Throwable cause) {
		super(cause) ;
	}
	
	public InsufficientFund(Throwable cause , String msg) {
		super(msg, cause) ;
	}
	
	public InsufficientFund(Throwable cause , String msg, boolean sup , boolean trace) {
		super(msg, cause, sup , trace) ;
	}
}

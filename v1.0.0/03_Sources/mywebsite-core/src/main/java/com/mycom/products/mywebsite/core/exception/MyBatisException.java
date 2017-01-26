package com.mycom.products.mywebsite.core.exception;

public class MyBatisException extends Exception {
	private static final long serialVersionUID = 2053867329965490426L;

	public MyBatisException() {
		super();
	}

	public MyBatisException(final String message) {
		super(message);
	}

	public MyBatisException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public MyBatisException(final Throwable cause) {
		super(cause);
	}

}
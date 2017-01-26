package com.mycom.products.mywebsite.core.exception;

public class ConsistencyViolationException extends Exception {
	private static final long serialVersionUID = -4061374500120185334L;

	public ConsistencyViolationException() {
		super();
	}

	public ConsistencyViolationException(final String message) {
		super(message);
	}

	public ConsistencyViolationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ConsistencyViolationException(final Throwable cause) {
		super(cause);
	}

}
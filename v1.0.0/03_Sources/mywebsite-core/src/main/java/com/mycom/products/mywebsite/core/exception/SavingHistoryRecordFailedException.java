package com.mycom.products.mywebsite.core.exception;

import org.apache.log4j.Logger;

public class SavingHistoryRecordFailedException extends RuntimeException {
	private static final long serialVersionUID = 3295211324285060221L;
	private Logger coreLogger = Logger.getLogger("CoreLogger");

	public SavingHistoryRecordFailedException() {
		super();
		coreLogger.error("Can't save in history because the given criteria was not match with any records in data-base.");
	}

	public SavingHistoryRecordFailedException(final String message) {
		super(message);
	}

	public SavingHistoryRecordFailedException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public SavingHistoryRecordFailedException(final Throwable cause) {
		super(cause);
	}

}
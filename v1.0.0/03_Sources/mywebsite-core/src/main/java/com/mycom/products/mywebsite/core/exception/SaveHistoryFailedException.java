/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.exception;

import org.apache.log4j.Logger;

public class SaveHistoryFailedException extends RuntimeException {
	private static final long serialVersionUID = 3295211324285060221L;
	private Logger coreLogger = Logger.getLogger("CoreLogger");

	public SaveHistoryFailedException() {
		super();
		coreLogger.error("Can't save in history because the given criteria was not match with any records in data-base.");
	}

	public SaveHistoryFailedException(final String message) {
		super(message);
	}

	public SaveHistoryFailedException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public SaveHistoryFailedException(final Throwable cause) {
		super(cause);
	}

}
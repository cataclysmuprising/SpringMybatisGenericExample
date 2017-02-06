/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.exception;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 7816976914780713621L;

	public BusinessException() {
		super();
	}

	public BusinessException(final String message) {
		super(message);
	}

	public BusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public BusinessException(final Throwable cause) {
		super(cause);
	}

}
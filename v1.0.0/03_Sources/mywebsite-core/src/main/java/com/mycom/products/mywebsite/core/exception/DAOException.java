/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.exception;

public class DAOException extends Exception {
	private static final long serialVersionUID = 2053867329965490426L;

	public DAOException() {
		super();
	}

	public DAOException(final String message) {
		super(message);
	}

	public DAOException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public DAOException(final Throwable cause) {
		super(cause);
	}

}
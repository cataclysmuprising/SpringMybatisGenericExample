/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.exception;

import org.apache.log4j.Logger;

import com.mycom.products.mywebsite.core.bean.BaseBean;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 7816976914780713621L;
	private Logger errorLogger = Logger.getLogger("ErrorLogger");

	public BusinessException() {
		super();
	}

	public BusinessException(final String message) {
		super(message);
		errorLogger.error(message);
		errorLogger.info(BaseBean.LOGBREAKER);
	}

	public BusinessException(final String message, final Throwable cause) {
		super(message, cause);
		errorLogger.error(message, cause);
		errorLogger.info(BaseBean.LOGBREAKER);
	}

	public BusinessException(final Throwable cause) {
		super(cause);
		errorLogger.error(cause.getMessage(), cause);
		errorLogger.info(BaseBean.LOGBREAKER);
	}

}
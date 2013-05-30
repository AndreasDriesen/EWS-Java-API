/**************************************************************************
 * copyright file="AutodiscoverResponseException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AutodiscoverResponseException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data.exceptions;

import microsoft.exchange.webservices.data.AutodiscoverErrorCode;

/**
 * Represents an exception from an autodiscover error response.
 */
public class AutodiscoverResponseException extends ServiceRemoteException {

	private static final long serialVersionUID = -2096903307329536694L;
	
	/**
	 * Error code when Autodiscover service operation failed remotely.
	 */
	private AutodiscoverErrorCode errorCode;

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param errorCode
	 *            the error code
	 * @param message
	 *            the message
	 */
	public AutodiscoverResponseException(AutodiscoverErrorCode errorCode,
			String message) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * Gets the ErrorCode for the exception.
	 * 
	 * @return the error code
	 */
	public AutodiscoverErrorCode getErrorCode() {
		return this.errorCode;
	}
}

/**************************************************************************
 * copyright file="ServiceRequestException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ServiceRequestException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data.exceptions;

/**
 * The Class ServiceRequestException.
 */
public class ServiceRequestException extends ServiceRemoteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1875319560434358367L;

	/**
	 * ServiceRequestException Constructor.
	 */
	public ServiceRequestException() {
		super();
	}

	/**
	 * ServiceRequestException Constructor.
	 * 
	 * @param message
	 *            the message
	 */
	public ServiceRequestException(String message) {
		super(message);
	}

	/**
	 * ServiceRequestException Constructor.
	 * 
	 * @param message
	 *            the message
	 * @param innerException
	 *            the inner exception
	 */
	public ServiceRequestException(String message, Exception innerException) {
		super(message, innerException);
	}
}

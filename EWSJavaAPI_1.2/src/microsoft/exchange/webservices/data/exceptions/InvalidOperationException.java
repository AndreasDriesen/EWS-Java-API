/**************************************************************************
 * copyright file="InvalidOperationException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the InvalidOperationException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data.exceptions;

/**
 * The Class InvalidOperationException.
 */
public class InvalidOperationException extends Exception {

	private static final long serialVersionUID = -807585971899170843L;
	

	/**
	 * Instantiates a new invalid operation exception.
	 */
	public InvalidOperationException() {

	}

	/**
	 * Instantiates a new invalid operation exception.
	 * 
	 * @param strMessage
	 *            the str message
	 */
	public InvalidOperationException(String strMessage) {
		super(strMessage);
	}
}

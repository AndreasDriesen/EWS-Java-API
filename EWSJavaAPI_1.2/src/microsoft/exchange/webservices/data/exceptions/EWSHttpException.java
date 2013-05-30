/**************************************************************************
 * copyright file="EWSHttpException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the EWSHttpException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data.exceptions;

/**
 * The Class EWSHttpException.
 */
public class EWSHttpException extends Exception {

	private static final long serialVersionUID = -4463905384361698395L;

	/**
	 * Instantiates a new eWS http exception.
	 */
	public EWSHttpException() {
		super();
		
	}

	/**
	 * Instantiates a new eWS http exception.
	 * 
	 * @param arg0
	 *            the arg0
	 * @param arg1
	 *            the arg1
	 */
	public EWSHttpException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	/**
	 * Instantiates a new eWS http exception.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public EWSHttpException(String arg0) {
		super(arg0);
	
	}

	/**
	 * Instantiates a new eWS http exception.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public EWSHttpException(Throwable arg0) {
		super(arg0);
	
	}

}

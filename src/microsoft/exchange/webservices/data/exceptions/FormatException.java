/**************************************************************************
 * copyright file="FormatException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FormatException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data.exceptions;

/**
 * The Class FormatException.
 */
public class FormatException extends Exception {

	private static final long serialVersionUID = 4911058020421778278L;

	/**
	 * Instantiates a new format exception.
	 */
	public FormatException() {
		super();
		
	}

	/**
	 * Instantiates a new format exception.
	 * 
	 * @param arg0
	 *            the arg0
	 * @param arg1
	 *            the arg1
	 */
	public FormatException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
		
	}

	/**
	 * Instantiates a new format exception.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public FormatException(final String arg0) {
		super(arg0);
		
	}

	/**
	 * Instantiates a new format exception.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public FormatException(final Throwable arg0) {
		super(arg0);
	
	}

}

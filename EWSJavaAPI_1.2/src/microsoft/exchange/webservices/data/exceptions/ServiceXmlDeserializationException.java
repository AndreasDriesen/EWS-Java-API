/**************************************************************************
 * copyright file="ServiceXmlDeserializationException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ServiceXmlDeserializationException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data.exceptions;

/**
 * Represents an error that occurs when the XML for a response cannot be
 * deserialized.
 * 
 */
public final class ServiceXmlDeserializationException extends
		ServiceLocalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8111774504723035312L;

	/**
	 * ServiceXmlDeserializationException Constructor.
	 */
	public ServiceXmlDeserializationException() {
		super();
	}

	/**
	 * ServiceXmlDeserializationException Constructor.
	 * 
	 * @param message
	 *            the message
	 */
	public ServiceXmlDeserializationException(String message) {
		super(message);
	}

	/**
	 * ServiceXmlDeserializationException Constructor.
	 * 
	 * @param message
	 *            the message
	 * @param innerException
	 *            the inner exception
	 */
	public ServiceXmlDeserializationException(String message,
			Exception innerException) {
		super(message, innerException);
	}

}

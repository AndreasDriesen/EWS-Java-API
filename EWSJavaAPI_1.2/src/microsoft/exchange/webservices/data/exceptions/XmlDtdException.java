/**************************************************************************
 * copyright file="XmlDtdException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the XmlDtdException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data.exceptions;

/**
 * Exception class for banned xml parsing
 *
 */
class XmlDtdException extends XmlException {
	private static final long serialVersionUID = -7026079881721616119L;

	/**
	 * Gets the xml exception message.
	 */

@Override
    public String getMessage()
    {
       return "For security reasons DTD is prohibited in this XML document.";
    }
}

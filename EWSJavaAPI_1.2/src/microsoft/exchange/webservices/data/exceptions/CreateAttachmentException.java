/**************************************************************************
 * copyright file="CreateAttachmentException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CreateAttachmentException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data.exceptions;

import microsoft.exchange.webservices.data.CreateAttachmentResponse;
import microsoft.exchange.webservices.data.EwsUtilities;
import microsoft.exchange.webservices.data.ServiceResponseCollection;

/**
 * Represents an error that occurs when a call to the CreateAttachment web
 * method fails.
 * 
 */
public final class CreateAttachmentException extends ServiceRemoteException {// extends
// BatchServiceResponseException<CreateAttachmentResponse> 

	private static final long serialVersionUID = 718701545429749389L;
	
	
	/** The responses. */
	//private ServiceResponseCollection<CreateAttachmentResponse> responses;

	/**
	 * Initializes a new instance of CreateAttachmentException.
	 * 
	 * @param serviceResponses
	 *            the service responses
	 * @param message
	 *            the message
	 */
	public CreateAttachmentException(
			ServiceResponseCollection<CreateAttachmentResponse>
			serviceResponses,
			String message) {
		// super(serviceResponses,message);
		super(message);
		EwsUtilities.EwsAssert(serviceResponses != null,
				"MultiServiceResponseException.ctor",
				"serviceResponses is null");

		//this.responses = serviceResponses;
	}

	/**
	 * Initializes a new instance of CreateAttachmentException.
	 * 
	 * @param serviceResponses
	 *            the service responses
	 * @param message
	 *            the message
	 * @param innerException
	 *            the inner exception
	 */
	protected CreateAttachmentException(
			ServiceResponseCollection<CreateAttachmentResponse> 
			serviceResponses,
			String message, Exception innerException) {
		// super(serviceResponses, message, innerException);
		super(message, innerException);
		EwsUtilities.EwsAssert(serviceResponses != null,
				"MultiServiceResponseException.ctor",
				"serviceResponses is null");

		//this.responses = serviceResponses;
	}
}

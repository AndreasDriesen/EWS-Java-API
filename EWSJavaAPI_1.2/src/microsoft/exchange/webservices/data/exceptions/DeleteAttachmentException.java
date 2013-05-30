/**************************************************************************
 * copyright file="DeleteAttachmentException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DeleteAttachmentException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data.exceptions;

import microsoft.exchange.webservices.data.DeleteAttachmentResponse;
import microsoft.exchange.webservices.data.EwsUtilities;
import microsoft.exchange.webservices.data.ServiceResponseCollection;

/**
 * Represents an error that occurs when a call to the DeleteAttachment web
 * method fails.
 * 
 */
public final class DeleteAttachmentException extends ServiceRemoteException {// extends
// BatchServiceResponseException<DeleteAttachmentResponse>


	private static final long serialVersionUID = 4007228134679166795L;
	
	
	/** The responses. */
	//private ServiceResponseCollection<DeleteAttachmentResponse> responses;

	/**
	 * Initializes a new instance of DeleteAttachmentException.
	 * 
	 * @param serviceResponses
	 *            The list of responses to be associated with this exception.
	 * @param message
	 *            The message that describes the error.
	 */
	public DeleteAttachmentException(
			ServiceResponseCollection<DeleteAttachmentResponse> 
			serviceResponses,
			String message) {
		// super(serviceResponses, message);
		super(message);
		EwsUtilities.EwsAssert(serviceResponses != null,
				"MultiServiceResponseException.ctor",
				"serviceResponses is null");

		//this.responses = serviceResponses;
	}

	/**
	 * Initializes a new instance of DeleteAttachmentException.
	 * 
	 * @param serviceResponses
	 *            The list of responses to be associated with this exception.
	 * @param message
	 *            The message that describes the error.
	 * @param innerException
	 *            The exception that is the cause of the current exception.
	 */
	protected DeleteAttachmentException(
			ServiceResponseCollection<DeleteAttachmentResponse> 
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

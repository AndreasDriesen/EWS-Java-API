package microsoft.exchange.webservices.data.exceptions;

public class XmlException extends Exception {

	private static final long serialVersionUID = 5088272851509319135L;

	/**
	 * Instantiates a new argument exception.
	 */
	public XmlException() {
		super();
		
	}

	/**
	 * Instantiates a new argument exception.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public XmlException(final String arg0) {
		super(arg0);
		
	}

	/**
	 * ServiceXmlDeserializationException Constructor.
	 * 
	 * @param message
	 *            the message
	 * @param innerException
	 *            the inner exception
	 */
	public XmlException(String message, Exception innerException) {
		super(message, innerException);
	}
}

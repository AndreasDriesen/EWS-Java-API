package microsoft.exchange.webservices.data.exceptions;

public class NotSupportedException extends Exception {
	private static final long serialVersionUID = -8335185752675437078L;

	/**
	 * Instantiates a new argument exception.
	 */
	public NotSupportedException() {
		super();
		
	}
	
	/**
	 * Instantiates a new NotSupported exception.
	 * 
	 * @param strMessage
	 *            the str message
	 */
	public NotSupportedException(String strMessage) {
		super(strMessage);
	}
}

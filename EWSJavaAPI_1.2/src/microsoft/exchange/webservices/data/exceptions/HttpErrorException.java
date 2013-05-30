package microsoft.exchange.webservices.data.exceptions;


/**
 * User: nwoodham Date: 3/8/11 Time: 5:30 PM
 */
public class HttpErrorException extends Exception {
	private static final long serialVersionUID = 1831349038445407316L;
	
	
	private final int code;

  public HttpErrorException()
  {
    super();
    this.code = 0;
  }

  public HttpErrorException(String message, int code)
  {
    super(message);
    this.code = code;
  }

  public int getHttpErrorCode()
  {
    return this.code;
  }
}

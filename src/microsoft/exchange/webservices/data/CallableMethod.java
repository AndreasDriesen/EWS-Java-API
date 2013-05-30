package microsoft.exchange.webservices.data;
import java.io.IOException;
import java.util.concurrent.Callable;

import microsoft.exchange.webservices.data.exceptions.EWSHttpException;
import microsoft.exchange.webservices.data.exceptions.HttpErrorException;

import org.apache.http.HttpException;



public class CallableMethod implements Callable<HttpWebRequest> {
	HttpWebRequest request;
	
	
	CallableMethod(HttpWebRequest request){
		this.request= request;
	}

	protected HttpClientWebRequest executeMethod() throws EWSHttpException, HttpErrorException, IOException, HttpException{
		 request.executeRequest();
		 return (HttpClientWebRequest)request;
	}
	
	public HttpWebRequest call() throws HttpException{
		try {
			return executeMethod();
		} catch (EWSHttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return request;
	}
	
	
	
}

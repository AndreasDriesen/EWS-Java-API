/**************************************************************************
 * copyright file="HttpClientWebRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the HttpClientWebRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import javax.net.ssl.TrustManager;

import microsoft.exchange.webservices.data.exceptions.EWSHttpException;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;


/**
 * HttpClientWebRequest is used for making request to the server through 
 * NTLM Authentication by using Apache HttpClient 3.1 and JCIFS Library.
 */
class HttpClientWebRequest extends HttpWebRequest {

	/** The Http Client. */
	private DefaultHttpClient client = null;
	
	/** The Http Method. */
	private HttpPost  httpPostReq = null;
	private HttpResponse response = null;
	
	/** The TrustManager. */
	private TrustManager trustManger = null;
	
	private ClientConnectionManager simpleClientConnMng = null;
	
	Cookie[] cookies = null;
	
	/**
	 * Instantiates a new http native web request.
	 */
	public HttpClientWebRequest(ClientConnectionManager simpleClientConnMng) {
		this.simpleClientConnMng = simpleClientConnMng;
	}

	/**
	 * Releases the connection by Closing.
	 */
	@Override
	public void close() {
		ExecutorService es =(ExecutorService) CallableSingleTon.getExecutor();
		es.shutdown();
		if (null != httpPostReq) {
			httpPostReq.releaseConnection();
			//postMethod.abort();
		}
		httpPostReq = null;
	}

	/**
	 * Prepare connection 
	 * 
	 * @throws EWSHttpException
	 *             the eWS http exception
	 * @throws KeyStoreException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	@Override
	public void prepareConnection() throws EWSHttpException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		try {
			if(trustManger != null) {
				EwsSSLProtocolSocketFactory.trustManager = trustManger;
			}
				
			//register SSL scheme
			Scheme httpsScheme = new Scheme("https", 443, EwsSSLProtocolSocketFactory.getInstance());
			//SchemeRegistry schemeRegistry = new SchemeRegistry();
			//schemeRegistry.register(httpsScheme);
			this.simpleClientConnMng.getSchemeRegistry().register(httpsScheme);
			
			
			
			
			//Protocol.registerProtocol("https", new Protocol("https", new EwsSSLProtocolSocketFactory(), 443));
			
			
			
			/*AuthPolicy.registerAuthScheme(AuthPolicy.NTLM, EwsJCIFSNTLMScheme.class);
			
			List<String> authPrefs = new ArrayList<String>();
			authPrefs.add(AuthPolicy.NTLM);
			authPrefs.add(AuthPolicy.BASIC);
			authPrefs.add(AuthPolicy.DIGEST);
			client.getParams().setParameter(AuthPolicy.AUTH_SCHEME_PRIORITY, authPrefs);*/
			
			
			client = new DefaultHttpClient(this.simpleClientConnMng);
			
			
			if(getProxy() != null) {
				HttpHost proxy = new HttpHost(getProxy().getHost(), getProxy().getPort());
				client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
				 
				if (HttpProxyCredentials.isProxySet()) {
					AuthScope authScope = new AuthScope(proxy); 
					NTCredentials cred = new NTCredentials(HttpProxyCredentials.getUserName(),
		            		HttpProxyCredentials.getPassword(),
		                    "",HttpProxyCredentials.getDomain());
					client.getCredentialsProvider().setCredentials(authScope, cred);
				}
			}
			if(getUserName() != null) {
				//System.out.println("client.getCredentialsProvider() => "+client.getCredentialsProvider());
				client.getCredentialsProvider().setCredentials(AuthScope.ANY, new NTCredentials(getUserName(),getPassword(),"",getDomain()));
				//System.out.println("setting credentials => "+getUserName()+ " / " +getPassword()+" / "+getDomain());
			}
			
			client.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, getTimeout());
			client.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, getTimeout());
			httpPostReq = new HttpPost(getUrl().toString()); 
			httpPostReq.addHeader("Content-type", getContentType());
			//httpPostReq.setDoAuthentication(true);
			httpPostReq.addHeader("User-Agent", getUserAgent());		
			httpPostReq.addHeader("Accept", getAccept());		
			httpPostReq.addHeader("Keep-Alive", "300");		
			httpPostReq.addHeader("Connection", "Keep-Alive");
			
			
			if(this.cookies !=null && this.cookies.length > 0){
				for (Cookie c : this.cookies) {
					client.getCookieStore().addCookie(c);
				}
			}
			//HttpClientParams.setRedirecting(client.getParams(), isAllowAutoRedirect());
	
			if (isAcceptGzipEncoding()) {
				httpPostReq.addHeader("Accept-Encoding", "gzip,deflate");
			}
	
			if (getHeaders().size() > 0){
				for (Map.Entry<String,String> httpHeader : getHeaders().entrySet()) {
					httpPostReq.addHeader(httpHeader.getKey(), httpHeader.getValue());						
				}
	
			}
		}
		catch (Exception er) {
			er.printStackTrace();
		}
	}

	/**
	 * Prepare asynchronous connection.
	 * 
	 * @throws EWSHttpException
	 *             throws EWSHttpException
	 * @throws KeyStoreException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public void prepareAsyncConnection() throws EWSHttpException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		try {
			if (trustManger != null) {
				EwsSSLProtocolSocketFactory.trustManager = trustManger;
			}
			
			/*Protocol.registerProtocol("https", new Protocol("https", new EwsSSLProtocolSocketFactory(), 443));
			AuthPolicy.registerAuthScheme(AuthPolicy.NTLM, EwsJCIFSNTLMScheme.class);
			client = new DefaultHttpClient(this.simpleClientConnMng); 
			List authPrefs = new ArrayList();
			authPrefs.add(AuthPolicy.NTLM);
			authPrefs.add(AuthPolicy.BASIC);
			authPrefs.add(AuthPolicy.DIGEST);
			client.getParams().setParameter(AuthPolicy.AUTH_SCHEME_PRIORITY, authPrefs);*/
			
			//register SSL scheme
			Scheme httpsScheme = new Scheme("https", 443, EwsSSLProtocolSocketFactory.getInstance());
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(httpsScheme);

			client.getCredentialsProvider().setCredentials(AuthScope.ANY, new NTCredentials(getUserName(),getPassword(),"",getDomain()));
			client.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, getTimeout());
			client.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 20000);
			//httpPostReq = new GetMethod(getUrl().toString()); 
			httpPostReq = new HttpPost(getUrl().toString());
			HttpClientParams.setRedirecting(client.getParams(), isAllowAutoRedirect());
			response = client.execute(httpPostReq); 
		} 
		catch (IOException e) {
			client = null;
			httpPostReq = null;
			throw new EWSHttpException("Unable to open connection to "+ this.getUrl());
		}
	}
	
	/**
	 * Method for getting the cookie vlaues.
	 * 
	 * @throws EWSHttpException
	 *             throws EWSHttpException
	 */
	public List<Cookie> getCookies()   {
		return this.client.getCookieStore().getCookies();
	}
	
	/**
	 * Method for setting the cookie values.
	 * 
	 * @throws EWSHttpException
	 *             throws EWSHttpException
	 */
	public void setUserCookie(Cookie[] rcookies) {

		if (rcookies != null && rcookies.length > 0)
			this.cookies = rcookies.clone();

	}
	
	
	
	
	
	/**
	 * Gets the input stream.
	 * 
	 * @return the input stream
	 * @throws EWSHttpException
	 *             the eWS http exception
	 * @throws IOException 
	 */
	@Override
	public InputStream getInputStream() throws EWSHttpException, IOException {
		throwIfResponseIsNull();
		BufferedInputStream bufferedInputStream = null;
		try {
			bufferedInputStream = new BufferedInputStream(response.getEntity().getContent());
		} catch (IOException e) {
			throw new EWSHttpException("Connection Error " + e);
		}
		return bufferedInputStream;
	}

	/**
	 * Gets the error stream.
	 * 
	 * @return the error stream
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	@Override
	public InputStream getErrorStream() throws EWSHttpException {
		throwIfResponseIsNull();
		BufferedInputStream bufferedInputStream = null;
		try {
			bufferedInputStream = new BufferedInputStream(response.getEntity().getContent());
		} catch (Exception e) {
			throw new EWSHttpException("Connection Error " + e);
		}
		return bufferedInputStream;
	}

	/**
	 * Gets the output stream.
	 * 
	 * @return the output stream
	 * @throws EWSHttpException  the eWS http exception
	 */
	@Override
	public OutputStream getOutputStream() throws EWSHttpException {
		OutputStream os = null;
		throwIfRequestIsNull();
		os = new ByteArrayOutputStream();
		
		httpPostReq.setEntity(new ByteArrayOSRequestEntity(os));
		//((EntityEnclosingMethod) httpMethod).setRequestEntity(new ByteArrayOSRequestEntity(os)); 
		return os;
	}

	/**
	 * Gets the response headers.
	 * 
	 * @return the response headers
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	@Override
	public Map<String, String> getResponseHeaders() throws EWSHttpException {
		throwIfResponseIsNull();
		Map<String, String> map = new HashMap<String, String>(); 

		Header[] hM = response.getAllHeaders();
		for (Header header : hM) {
			// RFC2109: Servers may return multiple Set-Cookie headers 
			// Need to append the cookies before they are added to the map
			if (header.getName().equals("Set-Cookie")) {
				String cookieValue = "";
				if (map.containsKey("Set-Cookie")) {
					cookieValue += map.get("Set-Cookie");
					cookieValue += ",";
				}
				cookieValue += header.getValue();
				map.put("Set-Cookie", cookieValue);
			}
			else
				map.put(header.getName(),header.getValue());
		}
			
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * microsoft.exchange.webservices.HttpWebRequest#getResponseHeaderField(
	 * java.lang.String)
	 */
	@Override
	public String getResponseHeaderField(String headerName)
	throws EWSHttpException {
		throwIfResponseIsNull();
		Header hM = response.getFirstHeader(headerName);
		return hM != null ? hM.getValue() : null;
	}

	/**
	 * Gets the content encoding.
	 * 
	 * @return the content encoding
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	@Override
	public String getContentEncoding() throws EWSHttpException {
		throwIfResponseIsNull();
		return response.getFirstHeader("content-encoding") != null ? response.getFirstHeader("content-encoding").getValue() : null;
	}

	/**
	 * Gets the response content type.
	 * 
	 * @return the response content type
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	@Override
	public String getResponseContentType() throws EWSHttpException {
		throwIfResponseIsNull();
		return response.getFirstHeader("Content-type") != null ? response.getFirstHeader("Content-type").getValue() : null;
	}
	
	/**
	 * Executes Request by sending request xml data to server.
	 * 
	 * @throws EWSHttpException
	 *             the eWS http exception
	 * @throws HttpException
	 *             the http exception
	 * @throws IOException
	 *             the IO Exception
	 */
	@Override
	public void executeRequest() throws EWSHttpException, HttpException, IOException {
		throwIfRequestIsNull();
		response = client.execute(httpPostReq);
	}

	/**
	 * Gets the response code.
	 * 
	 * @return the response code
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	@Override
	public int getResponseCode() throws EWSHttpException {
		throwIfResponseIsNull();
		return response.getStatusLine().getStatusCode();
	}
	
	/**
	 * Gets the response message.
	 * 
	 * @return the response message
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	public String getResponseText() throws EWSHttpException {
		throwIfResponseIsNull();
		return response.getStatusLine().getReasonPhrase();
	}

	/**
	 * Throw if conn is null.
	 * 
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	private void throwIfRequestIsNull() throws EWSHttpException {
		if (null == httpPostReq) {
			throw new EWSHttpException("Connection not established");
		}
	}
	
	
	private void throwIfResponseIsNull() throws EWSHttpException {
		if (null == response) {
			throw new EWSHttpException("Connection not established");
		}
	}

	/**
	 * Gets the request properties.
	 * 
	 * @return the request properties
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	public Map<String,String> getRequestProperty() throws EWSHttpException {
		throwIfRequestIsNull();
		Map<String, String> map = new HashMap<String, String>(); 

		Header[] hM = httpPostReq.getAllHeaders();
		for (Header header : hM) {
			map.put(header.getName(),header.getValue());
		}
		return map;
	}

	/**
	 * Sets the Client Certificates.
	 * 
	 * @param certs
	 * 			the Trust Manager
	 * @throws EWSHttpException
	 *             the eWS http exception
	 * @throws KeyManagementException
	 *             the KeyManagementException
	 * @throws NoSuchAlgorithmException
	 *             the NoSuchAlgorithmException
	 */
	public void setClientCertificates(TrustManager certs) throws EWSHttpException {
		trustManger = certs;
		
	}
}

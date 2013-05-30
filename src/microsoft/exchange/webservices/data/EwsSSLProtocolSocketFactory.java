/**************************************************************************
 * copyright file="EwsSSLProtocolSocketFactory.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the EwsSSLProtocolSocketFactory.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.io.IOException;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.params.HttpParams;

/**
 * <p>
 * EwsSSLProtocolSocketFactory can be used to creats SSL {@link Socket}s 
 * that accept self-signed certificates. 
 * </p>
 * <p>
 * This socket factory SHOULD NOT be used for productive systems 
 * due to security reasons, unless it is a concious decision and 
 * you are perfectly aware of security implications of accepting 
 * self-signed certificates
 * </p>
 *
 * <p>
 * Example of using custom protocol socket factory for a specific host:
 *     <pre>
 *     Protocol easyhttps = new Protocol("https", new EasySSLProtocolSocketFactory(), 443);
 *
 *     URI uri = new URI("https://localhost/", true);
 *     // use relative url only
 *     GetMethod httpget = new GetMethod(uri.getPathQuery());
 *     HostConfiguration hc = new HostConfiguration();
 *     hc.setHost(uri.getHost(), uri.getPort(), easyhttps);
 *     HttpClient client = new HttpClient();
 *     client.executeMethod(hc, httpget);
 *     </pre>
 * </p>
 * <p>
 * Example of using custom protocol socket factory per default instead of the standard one:
 *     <pre>
 *     Protocol easyhttps = new Protocol("https", new EasySSLProtocolSocketFactory(), 443);
 *     Protocol.registerProtocol("https", easyhttps);
 *
 *     HttpClient client = new HttpClient();
 *     GetMethod httpget = new GetMethod("https://localhost/");
 *     client.executeMethod(httpget);
 *     </pre>
 * </p>
 *  
 * <p>
 * DISCLAIMER: HttpClient developers DO NOT actively support this component.
 * The component is provided as a reference material, which may be inappropriate
 * for use without additional customization.
 * </p>
 */

class EwsSSLProtocolSocketFactory extends SSLSocketFactory { //SecureProtocolSocketFactory
	

	/** The X509 TrustManager. */
    static TrustManager trustManager = null;

    /**
     * Constructor for EasySSLProtocolSocketFactory.
     */
    public EwsSSLProtocolSocketFactory(SSLContext context) {
    	super(context); //, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER
    }

    public static EwsSSLProtocolSocketFactory getInstance() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
    	SSLContext context = SSLContext.getInstance("SSL");
        context.init(
          null, 
          new TrustManager[] {new EwsX509TrustManager(null, trustManager)}, 
          null);
        EwsSSLProtocolSocketFactory factory = new EwsSSLProtocolSocketFactory(context);
        return factory;
        
    }

    
    
    @Override
    public Socket createSocket(HttpParams params) throws IOException {
    	return super.createSocket(params);
    }
    
    
   /* private static TrustManager[] getTrustingManager() {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                // Do nothing
            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                // Do nothing
            }

        } };
        return trustAllCerts;
    }*/
    

    

}

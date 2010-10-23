package com.womobile.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple http client that uses the built in URLConnection class.
 * 
 * @author Daniel Winterstein
 *
 */
public class ComHttpClient {
	/**
	 * true if we are in the middle of a retry attempt. false normally
	 */
	private boolean retryingFlag;
	/**
	 * If true, will wait 1 second and make a second when presented with a server error.
	 */
	private boolean retryOnError;

	/*
	public ComHttpClient(String name, String password) {
		assert (name!=null && password != null) || (name==null && password==null);
	}

	public ComHttpClient() {
		this(null,null);
	}
	*/
	
	private static final int timeOutMilliSecs = 10 * 1000;

	public String getPage(String uri, Map<String, String> vars, boolean authenticate) throws StatusnetException {
		assert uri != null;
		if (vars != null && vars.size() != 0) {
			uri += "?";
			for (Entry<String, String> e : vars.entrySet()) {
				if (e.getValue() == null)
					continue;
				uri += encode(e.getKey()) + "=" + encode(e.getValue()) + "&";
			}
		}
		try {
			// Setup a connection
			final HttpURLConnection connection = (HttpURLConnection) new URL(uri).openConnection();			
			// To keep the search API happy - which wants either a referrer or a user agent
			connection.setRequestProperty("User-Agent", "JStatusnet/"+Statusnet.version);
			connection.setDoInput(true);
			connection.setReadTimeout(timeOutMilliSecs);					
			// Open a connection
			processError(connection);
			final InputStream inStream = connection.getInputStream();
			// Read in the web page
			String page = toString(inStream);
			// Done
			return page;
		} catch (IOException e) {
			throw new StatusnetException(e);
		} catch (StatusnetException.E50X e) {
			if ( ! retryOnError || retryingFlag) throw e;								
			try {
				retryingFlag = true;
				Thread.sleep(1000);
				return getPage(uri, vars, authenticate);
			} catch (InterruptedException ex) {
				throw new StatusnetException(ex);
			} finally {
				retryingFlag = false;
			}
		}
	}
		
		
	/**
	 * Throw an exception if the connection failed
	 * @param connection
	 */
	void processError(HttpURLConnection connection) {
		try {
			int code = connection.getResponseCode();
			if (code==200) return;
			URL url = connection.getURL();
			String error = connection.getResponseMessage();
			if (code==403) {
				//throw new StatusnetException.E403(error+" "+url+" ("+(name==null?"anonymous":name)+")");
				throw new StatusnetException.E404(error+" "+url);
			}
			if (code==404) {
				throw new StatusnetException.E404(error+" "+url);
			}
			if (code >= 500 && code<600) {
				throw new StatusnetException.E50X(error+" "+url);
			}
			boolean rateLimitExceeded = error.contains("Rate limit exceeded");
			if (rateLimitExceeded) {
				throw new StatusnetException.RateLimit(error);
			}
			// Rate limiter can sometimes cause a 400 Bad Request			
			if (code==400) {
				String json = getPage("http://identi.ca/api/account/rate_limit_status.json",
						null, true);
				try {
					JSONObject obj = new JSONObject(json);
					int hits = obj.getInt("remaining_hits");
					if (hits<1) throw new StatusnetException.RateLimit(error);
				} catch (JSONException e) {
					// oh well
				}				
			}
			// just report it as a vanilla exception
			throw new StatusnetException(code + " " + error+" "+url);
		} catch (SocketTimeoutException e) {
			URL url = connection.getURL();
			throw new StatusnetException.Timeout(timeOutMilliSecs+"milli-secs for "+url);
		} catch (IOException e) {
			throw new StatusnetException(e);
		}
	}	

	@SuppressWarnings("unused")
	private String getErrorStream(HttpURLConnection connection) {
		try {
			return toString(connection.getErrorStream());
		} catch (NullPointerException e) {
			return null;
		}
	}


	public String post(String uri, Map<String, String> vars) throws StatusnetException {
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) new URL(uri).openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setReadTimeout(timeOutMilliSecs);
			StringBuilder encodedData = new StringBuilder();
			if (vars != null) {
				for (String key : vars.keySet()) {
					String val = encode(vars.get(key));
					encodedData.append(encode(key));
					encodedData.append('=');
					encodedData.append(val);
					encodedData.append('&');
				}
			}
			connection.setRequestProperty("Content-Length", "" + encodedData.length());
			OutputStream os = connection.getOutputStream();
			os.write(encodedData.toString().getBytes());
			close(os);
			// Get the response
			processError(connection);
			String response = toString(connection.getInputStream());
			return response;
		} catch (IOException e) {
			throw new StatusnetException(e);
		} catch (StatusnetException.E50X e) {
			if ( ! retryOnError || retryingFlag) throw e;
			try {
				Thread.sleep(1000);		
				retryingFlag = true;				
				return post(uri, vars);
			} catch (InterruptedException ex) {
				throw new StatusnetException(ex);
			} finally {
				retryingFlag = false;
			}
		}
	}
	
	
	/**
	 * False by default. Setting this to true switches on a robustness
	 * workaround: when presented with a 50X server error, the
	 * system will wait 1 second and make a second attempt. 
	 */
	public void setRetryOnError(boolean retryOnError) {
		this.retryOnError = retryOnError;
	}
	


	
	private static String encode(Object x) {
		return URLEncoder.encode(String.valueOf(x));
	}

	/**
	 * Use a bufferred reader (preferably UTF-8) to extract the contents of
	 * the given stream. A convenience method for {@link #toString(Reader)}.
	 */
	private static String toString(InputStream inputStream) {
		InputStreamReader reader;
		try {
			//reader = new InputStreamReader(inputStream, "UTF-8");
			reader = new InputStreamReader(inputStream, "GBK");
		} catch (UnsupportedEncodingException e) {
			reader = new InputStreamReader(inputStream);
		}
		return toString(reader);
	}
	
	/**
	 * Use a buffered reader to extract the contents of the given reader.
	 * 
	 * @param reader
	 * @return The contents of this reader.
	 */
	private static String toString(Reader reader) throws RuntimeException {
		try {
			// Buffer if not already buffered
			reader = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
			StringBuilder output = new StringBuilder();
			while (true) {
				int c = reader.read();
				if (c == -1)
					break;
				output.append((char) c);
			}
			return output.toString();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		} finally {
			close(reader);
		}
	}

	/**
	 * Close a reader/writer/stream, ignoring any exceptions that result. Also
	 * flushes if there is a flush() method.
	 */
	private static void close(Closeable input) {
		if (input == null)
			return;
		// Flush (annoying that this is not part of Closeable)
		try {
			Method m = input.getClass().getMethod("flush");
			m.invoke(input);
		} catch (Exception e) {
			// Ignore
		}
		// Close
		try {
			input.close();
		} catch (IOException e) {
			// Ignore
		}
	}
	
	
	//测试url信息
	public static void main(String[] args) {
		//get测试 明文
		ComHttpClient ht=new ComHttpClient();
		String page=ht.getPage("http://www.baidu.com", null, false);//http://www.baidu.com  http://211.138.132.210:7001
		System.out.println(page);
		//post测试 编码
		
	}

}
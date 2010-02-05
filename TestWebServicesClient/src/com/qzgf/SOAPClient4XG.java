package com.qzgf;
import java.io.*;
import java.net.*;
public class SOAPClient4XG {
  public static void main(String[] args) throws Exception {

        String SOAPUrl      = "http://134.128.182.60:9082/service/WSSelect";
        String xmlFile2Send = "getweekday.xml";
        String SOAPAction = "http://factory.webservice.doone.com";

       URL url = new URL(SOAPUrl);
       URLConnection connection = url.openConnection();
       HttpURLConnection httpConn = (HttpURLConnection) connection;
        // Open the input file. After we copy it to a byte array, we can see
        // how big it is so that we can set the HTTP Cotent-Length
        // property. (See complete e-mail below for more on this.)
       FileInputStream fin = new FileInputStream(xmlFile2Send);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();    
       // Copy the SOAP file to the open connection.
       copy(fin,bout);
        fin.close();
        byte[] b = bout.toByteArray();
        // Set the appropriate HTTP parameters.
        httpConn.setRequestProperty( "Content-Length",
                                    String.valueOf( b.length ) );
        httpConn.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");
		httpConn.setRequestProperty("SOAPAction", SOAPAction);
		httpConn.setRequestMethod("POST");
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
        // Everything's set up; send the XML that was read in to b.
       OutputStream out = httpConn.getOutputStream();
       out.write( b );    
        out.close();
      // Read the response and write it to standard out.
        InputStreamReader isr =
            new InputStreamReader(httpConn.getInputStream());
        BufferedReader in = new BufferedReader(isr);
       String inputLine;
        while ((inputLine = in.readLine()) != null)
           System.out.println(inputLine);
        in.close();
    }
 // copy method from From E.R. Harold's book "Java I/O"
 public static void copy(InputStream in, OutputStream out) 
   throws IOException {
    // do not allow other threads to read from the
    // input or write to the output while copying is
    // taking place
  synchronized (in) {
      synchronized (out) {
        byte[] buffer = new byte[256];
       while (true) {
          int bytesRead = in.read(buffer);
          if (bytesRead == -1) break;
         out.write(buffer, 0, bytesRead);
        }
      }
   }
  } 
}
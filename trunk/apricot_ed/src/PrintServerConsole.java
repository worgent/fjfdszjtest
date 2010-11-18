import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 */

/**
 * @author xudahu
 * 
 */
public class PrintServerConsole {

    /**
     * 打印开启服务端的socket端口
     * #define PRINTER_STATUS_DMD      0x76     CHAR_ESC + request the printer status 
     * #define PRINTER_CHAR_ESC        0x1B
     * 
     */
    public PrintServerConsole() {
	// TODO Auto-generated constructor stub
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub
	ServerSocket server = new ServerSocket();
	server.bind(new InetSocketAddress("localhost", 12301));
	Socket aa = null;
	while ((aa = server.accept()) != null) {
	    final Socket s = aa;
	    new Thread(new Runnable() {

		public void run() {
		    // TODO Auto-generated method stub
		    try {
			OutputStream out = s.getOutputStream();

			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String line = null;
			try {
			    while ((line = in.readLine()) != null) {
				if(line.equals(new String(new byte[] { 0x1B, 0x76}))){
				    out.write(new byte[]{0,0,0,0});
				}else{
				    System.out.println(line);
				}
				
			    }
			} catch (Exception e) {
			    e.printStackTrace();
			}

		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}

	    }).start();

	}

    }

}

/**
 * 
 */
package com.apricot.eating.print;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author xudahu
 * 
 */
public class IPPrinterService implements PrinterService {

    private Socket socket = null;
    private OutputStream out = null;
    private InputStream in = null;

    private int connect(Printer printer) {

	if (out != null) {
	    // if(out!=null ){
	    try {
		out.write("\n".getBytes());
		out.flush();
	    } catch (Exception e) {
		e.printStackTrace();
		close();

	    }
	    // }

	} else
	    close();

	if (socket == null) {
	    socket = new Socket();
	    try {
		socket.connect(new InetSocketAddress(printer.getHostAddress(), printer.getHostPort()), printer
			.getTimeout());
	    } catch (Exception e) {
		e.printStackTrace();
		return PrinterService.NO_CONNECT;
	    }

	    // 打开输出流
	    try {
		out = socket.getOutputStream();
		out.write("\n".getBytes());
		out.flush();
	    } catch (Exception e) {
		e.printStackTrace();
		return PrinterService.OUT_BLOCK;
	    }
	    // 打开输入流
	    try {
		in = socket.getInputStream();
	    } catch (Exception e) {
		e.printStackTrace();
		return PrinterService.OUT_BLOCK;
	    }

	}

	// 进行状态判断
	int flag = this.checkPrintStatus();
	if (flag > 0)
	    return flag;

	return 0;

    }

    private int checkPrintStatus() {
	byte[] todata = new byte[] { 0x1B, 0x76, (byte) '\n' };
	byte[] rt = new byte[4];
	try {
	    out.write(todata);
	    out.flush();
	    in.read(rt);
             System.out.println("打印机返回状态："+rt[0]+" "+rt[1]+" "+rt[2]+" "+rt[3]);
	    if ((rt[0] & 8) > 0) {
		return PrinterService.PRINTER_OFFLINE;
	    }
	    if ((rt[0] & 32) > 0) {
		return PrinterService.COVER_OPEN;
	    }

	    if ((rt[1] & 8) > 0) {
		return PrinterService.ERROR_BLADE;
	    }

	    if ((rt[1] & 20) > 0) {
		return PrinterService.ERROR_COVERENABLE;
	    }

	    if ((rt[1] & 40) > 0) {
		return PrinterService.ERROR_AUTOCOVER;
	    }

	    if ((rt[2] & 12) > 0) {
		return PrinterService.NO_PAPER;
	    }

	    if ((rt[3] & 64) > 0) {
		return PrinterService.ERROR_OTHER;
	    }
	} catch (Exception e) {
	    // e.printStackTrace();
	    return PrinterService.CHECK_PRINT_ERROR;
	}

	return 0;
    }

    public int print(PrintDocument doc) {
	// TODO Auto-generated method stub
	int flag = connect(doc.getPrinter());

	if (flag > 0)
	    return flag;
	// 写入要打印的数据
	try {
	    out.write(doc.toByteArray());
	    out.write("\n".getBytes());// 自动往后移动一行
	    // 切纸
	    out.write(new String(new char[] { (char) 27, 'd', (char) 0, (char) 13, (char) 29,'V', (char) 66, (char) 0 })
		    .getBytes());
	    out.flush();
	    
	} catch (Exception e) {
	    e.printStackTrace();
	    return PrinterService.WRITE_ERROR;
	}
	//
	String r = "";
	try {
	    // byte[] b = new byte[1024];
	    // int len = in.read(b);
	    // r = new String(b, 0, len);
	} catch (Exception e) {
	    e.printStackTrace();
	    return PrinterService.READ_ERROR;
	}
	// 关闭

	return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.apricot.eating.print.PrinterService#close()
     */
    public void close() {
	// TODO Auto-generated method stub

	try {
	    if (out != null)
		out.close();
	} catch (Exception e) {
	}
	this.out = null;

	try {
	    if (in != null)
		in.close();
	} catch (Exception e) {
	}
	this.in = null;

	try {
	    if (socket != null)
		socket.close();
	} catch (Exception e) {
	}
	this.socket = null;
    }

}

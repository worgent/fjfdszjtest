/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.eop.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @see http://bianbian.sunshow.net/
 * @author dannyzhu, bianbian
 * @version 1.0
 */
public class WrapperResponse extends HttpServletResponseWrapper {
	private MyPrintWriter tmpWriter;

	private ByteArrayOutputStream output;

	public WrapperResponse(HttpServletResponse httpServletResponse) {
		super(httpServletResponse);
		output = new ByteArrayOutputStream();
		tmpWriter = new MyPrintWriter(output);
	}

	public void finalize() throws Throwable {
		super.finalize();
		output.close();
		tmpWriter.close();
	}

	public String getContent() {
	 
			tmpWriter.flush();
			String s="";
//			try {
				s = tmpWriter.getByteArrayOutputStream().toString();
				
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////			tmpWriter.getByteArrayOutputStream().toString(charsetName)

			return s;
		 
	}

	public PrintWriter getWriter() throws IOException {
		return tmpWriter;
	}

	public void close() throws IOException {
		tmpWriter.close();
	}

	private static class MyPrintWriter extends PrintWriter {
		ByteArrayOutputStream myOutput;

		public MyPrintWriter(ByteArrayOutputStream output) {
			super(output);
			myOutput = output;
		}

		public ByteArrayOutputStream getByteArrayOutputStream() {
			return myOutput;
		}
	}

}

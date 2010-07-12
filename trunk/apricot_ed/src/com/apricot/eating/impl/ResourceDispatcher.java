/**
 * 
 */
package com.apricot.eating.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.apricot.eating.DefaultContext;
import com.apricot.eating.DefaultServlet;
/**
 * @author Administrator
 */
public class ResourceDispatcher {
	/**
	 *  
	 */
	public ResourceDispatcher() {
		// TODO Auto-generated constructor stub
	}

	public void service(ServletContext context, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().replaceFirst(req.getContextPath(), "").replaceFirst(DefaultServlet.PREFFIX, "");
		InputStream io = context.getResourceAsStream(url);
		resp.setContentType("text/html;charset=" + DefaultContext.getContext().getSystemCharset());
		OutputStream out = resp.getOutputStream();

		try {
			byte[] buff = new byte[1024 * 5];
			int l = 0;
			while (io != null && (l = io.read(buff)) > 0) {
				out.write(buff, 0, l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			io.close();
			io = null;
		} catch (Exception e) {
		}
		try {
			out.close();
			out = null;
		} catch (Exception e) {
		}
	}
}

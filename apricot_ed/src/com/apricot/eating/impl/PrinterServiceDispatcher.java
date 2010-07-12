/**
 * 
 */
package com.apricot.eating.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apricot.eating.DefaultContext;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.io.StreamWriter;
import com.apricot.eating.print.PrinterJob;

/**
 * @author xudahu
 * 
 */
public class PrinterServiceDispatcher extends DataSetDispatcher {

    /**
     * 
     */
    public PrinterServiceDispatcher() {
	// TODO Auto-generated constructor stub
    }

    public void service(ServletContext context, HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	String str = req.getRequestURI();

	PrinterJob job = (PrinterJob) context.getAttribute(PrinterJob.KEY);
	if (str.endsWith("/print/stop.print")) {
	    if (job != null)
		job.interrupt();
	    return;
	}
	if (str.endsWith("/print/start.print")) {
	    if (job == null) {
		job = new PrinterJob(getRequestConfig(context, req, new DyncParameterMap()));
		context.setAttribute(PrinterJob.KEY, job);
	    }
	    job.start();
	    return;
	}

	if (str.endsWith("/print/notify.print")) {
	    if (job != null) {
		job.notify();// »½ÐÑ
	    }

	    return;
	}

	if (str.endsWith("/print/global/getParameters.print")) {
	    if (job != null) {
		response(resp, job.getParameters());
		return;
	    }

	    return;
	}

	if (str.endsWith("/print/global/setParameters.print")) {
	    if (job != null) {

		OutputStream out = null;

		try {
		    out = job.getPropertiesWriter();
		    for (java.util.Enumeration keys = req.getParameterNames(); keys.hasMoreElements();) {
			String key = (String) keys.nextElement();
			String v = req.getParameter(key);
			out.write(new StringBuffer(key).append("=").append(v).append("\n").toString().getBytes(
				DefaultContext.getContext().getSystemCharset()));
		    }

		    response(resp, job.getParameters());
		} catch (Exception e) {
		    e.printStackTrace();
		    responseError(resp, e.getMessage());
		}
		try {
		    if (out != null)
			out.close();
		} catch (Exception e) {
		}

		return;
	    }

	    return;
	}

	if (str.endsWith("/print/global/applyParameters.print")) {
	    if (job != null) {
		OutputStream out = null;

		try {
		    out = job.getPropertiesWriter();
		    for (java.util.Enumeration keys = req.getParameterNames(); keys.hasMoreElements();) {
			String key = (String) keys.nextElement();
			String v = req.getParameter(key);
			out.write(new StringBuffer(key).append("=").append(v).append("\n").toString().getBytes(
				DefaultContext.getContext().getSystemCharset()));
		    }
		    job.load();
		    response(resp, job.getParameters());
		} catch (Exception e) {
		    e.printStackTrace();
		    responseError(resp, e.getMessage());
		}
		try {
		    if (out != null)
			out.close();
		} catch (Exception e) {
		}

		return;
	    }

	    return;
	}

	if (str.endsWith("/print/error_list.print")) {
	    if (job != null) {
		response(resp, job.getPrinters());
		return;
	    }

	    return;
	}
    }

    private void response(HttpServletResponse resp, Object obj) {
	HashMap map = getJsonDataStruct("00", "");
	map.put("data", obj);

	try {
	    StreamWriter writer = null;
	    resp.setContentType("text/html;charset=" + DefaultContext.getContext().getSystemCharset());
	    writer = new StreamWriter(resp.getOutputStream(), DefaultContext.getContext().getSystemCharset());
	    writer.write(map);
	} catch (Exception e) {
	}
    }

    private void responseError(HttpServletResponse resp, String message) {
	HashMap map = getJsonDataStruct("01", message);

	try {
	    StreamWriter writer = null;
	    resp.setContentType("text/html;charset=" + DefaultContext.getContext().getSystemCharset());
	    writer = new StreamWriter(resp.getOutputStream(), DefaultContext.getContext().getSystemCharset());
	    writer.write(map);
	} catch (Exception e) {
	}
    }

}

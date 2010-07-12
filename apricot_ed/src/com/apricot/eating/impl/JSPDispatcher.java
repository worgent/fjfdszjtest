/**
 * 
 */
package com.apricot.eating.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apricot.eating.DefaultContext;
import com.apricot.eating.DefaultServlet;

/**
 * @author Administrator
 */
public class JSPDispatcher {
	/**
	 * 
	 */
	public JSPDispatcher() {
		// TODO Auto-generated constructor stub
	}

	private class ParameterName {
		private String name;
		private int index = -1;

		private ParameterName(String n) {
			String[] args = null;
			n = n.substring(2, n.length() - 2);
			args = n.split("[.]");
			name = args[0];
			if (args.length > 1) {
				index = Integer.parseInt(args[1]);
			}
		}

	}

	public String replace(String line, HttpServletRequest req) {
		StringBuffer sb = new StringBuffer();
		Matcher m = Pattern.compile(
				"[$][{]([A-Za-z0-9_]+|[A-Za-z0-9_]+[.][0-9]+)[}]")
				.matcher(line);
		while (m.find()) {
			ParameterName pn = new ParameterName(m.group());
			String[] v = req.getParameterValues(pn.name);

			if (v == null || v.length < 1 || v.length <= pn.index) {
				m.appendReplacement(sb, "");
				continue;
			}
			if (pn.index >= 0) {
				m.appendReplacement(sb, v[pn.index]);
			} else {
				m.appendReplacement(sb, v[0]);
			}

		}
		m.appendTail(sb);

		return sb.toString();
	}

	public void service(ServletContext context, HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().replaceFirst(req.getContextPath(), "")
				.replaceFirst(DefaultServlet.PREFFIX, "");

		//
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				context.getResourceAsStream(url)));
		// /
		// InputStream io = context.getResourceAsStream(url);

		//
		String functionName = url.replaceAll("/", "").replaceAll("[.]", "_");
		resp.setContentType("text/html;charset="
				+ DefaultContext.getContext().getSystemCharset());
		OutputStream out = resp.getOutputStream();

		try {

			//

			// byte[] buff = new byte[1024 * 5];
			// int l = 0;
			out.write("<script language=\"javascript\">".getBytes());
			out.write("\n function ".getBytes());
			out.write(functionName.getBytes());
			out.write("(){\n".getBytes());
			//
			String line = null;
			while ((line = reader.readLine()) != null) {
				line = replace(line, req);
				out.write(line.getBytes());
				out.write("\n".getBytes());
			}

			/*
			 * while (io != null && (l = io.read(buff)) > 0) { out.write(buff,
			 * 0, l); }
			 */
			//
			// buff = null;
			out.write("   };\n".getBytes());
			StringBuffer script = new StringBuffer();
			script.append("function ").append(functionName);
			script.append("_layout(){\n");
			script.append("var panel=new Ext.Panel({layout:");
			script.append("\"border\",bodyBorder:false,items:");
			script.append(functionName).append("()");
			script.append("});\n");
			script.append(" var contentWindow=Ext.getCmp(\"");
			script.append(req.getParameter("id")).append("\");\n");
			script.append("contentWindow.add(panel);\n");
			script.append("contentWindow.doLayout();\n}\n");
			script.append(functionName).append("_layout();\n");
			out.write(script.toString().getBytes());
			out.write("</script>".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (reader != null)
				reader.close();
			reader = null;
		} catch (Exception e) {
		}
		try {
			out.close();
			out = null;
		} catch (Exception e) {
		}
	}
}

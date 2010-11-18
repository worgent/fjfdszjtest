/**
 * 
 */
package com.apricot.eating;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.PropertyConfigurator;
import org.w3c.dom.Node;

import com.apricot.app.eating.LoginData;
import com.apricot.app.eating.StaticDataBO;
import com.apricot.eating.engine.Controller;
import com.apricot.eating.engine.PlugInConfig;
import com.apricot.eating.engine.RequestConfig;
import com.apricot.eating.impl.AttributeDispatcher;
import com.apricot.eating.impl.DataSetDispatcher;
import com.apricot.eating.impl.JSPDispatcher;
import com.apricot.eating.impl.PrinterServiceDispatcher;
import com.apricot.eating.impl.ResourceDispatcher;
import com.apricot.eating.impl.TableDispatcher;
import com.apricot.eating.io.StreamWriter;
import com.apricot.eating.print.PrinterJob;
import com.apricot.eating.util.DataUtil;
import com.apricot.eating.xml.XMLParser;

/**
 * @author Administrator
 */
public class DefaultServlet extends HttpServlet {
    public static final String PREFFIX = "/apricot";

    /**
	 * 
	 */
    private static final long serialVersionUID = 7653987522993650648L;

    /**
	 * 
	 */
    public DefaultServlet() {
	// TODO Auto-generated constructor stub
    }

    /**
     * 初始化整个应用程序系统
     */
    public void init() throws ServletException {
	// TODO Auto-generated method stub
	super.init();
	XMLParser xml = XMLParser.parser(getServletContext().getResourceAsStream(getInitParameter("config")));
	// 初始化插件
	HashMap plugins = new HashMap(2, 0.75F);
	List global = new ArrayList();
	getServletContext().setAttribute(DataSetDispatcher.PLUGIN_KEY, plugins);
	Node[] nodes = xml.getChildsByTagName(xml.getChildByTagName(xml.getRootElement(), "plugin-mapping"), "plugin");
	for (int i = 0; i < nodes.length; i++) {
	    Node n = nodes[i];
	    plugins.put(xml.getAttribute(n, "name"), DataUtil.copyProperties(PlugInConfig.class, xml.getAttributes(n)));
	    PlugInConfig p = (PlugInConfig) DataUtil.copyProperties(PlugInConfig.class, xml.getAttributes(n));
	    if (p.isGlobal()) {
		global.add(p);
	    }
	}

	// 初始化请求定义
	try {
	    HashMap map = new HashMap(2, 0.75F);
	    getServletContext().setAttribute(DataSetDispatcher.STATIC_DEFINE_KEY, map);
	    nodes = xml.getChildsByTagName(xml.getChildByTagName(xml.getRootElement(), "request-mapping"), "request");
	    for (int i = 0; i < nodes.length; i++) {
		RequestConfig req = (RequestConfig) xml.getNodeObject(nodes[i], RequestConfig.class);
		req.setPath(new StringBuffer(DefaultServlet.PREFFIX).append(req.getPath()).toString());
		req.setDataSourceJndi(getInitParameter("dataSource"));
		req.setUserTransactionJndi(getInitParameter("userTransaction"));
		map.put(req.getPath(), req);
		Node[] ns = xml.getChildsByTagName(nodes[i], "plugin");
		// 添加默认
		req.addAll(global);
		// 添加自定义
		for (int a = 0; a < ns.length; a++) {
		    PlugInConfig r = (PlugInConfig) DataUtil.copyProperties(PlugInConfig.class, xml
			    .getAttributes(ns[a]));
		    PlugInConfig rm = (PlugInConfig) plugins.get(r.getName());
		    DataUtil.copyProperties(r, rm, new String[] { "global", "className", "desc", "runStyle" });
		    // 先删除

		    req.remove(r);
		    if (r.isDisable()) {
			continue;
		    }
		    // 再加入
		    req.add(r);
		}
		req = null;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	// 初始化log4j
	try {
	    Properties prop = new Properties();
	    prop.load(getServletContext().getResourceAsStream(getInitParameter("log4j")));
	    PropertyConfigurator.configure(prop);
	} catch (Exception e) {
	    e.printStackTrace();
	    // Logger.error("初始化log4j文件出错", e, this);
	}
	// Set charset
	DefaultContext.getContext().setDataBaseCharset(getInitParameter("database-charset"));
	DefaultContext.getContext().setDataBaseCharset(getInitParameter("system-charset"));
	// Set init parameter to system env
	for (Enumeration keys = getInitParameterNames(); keys.hasMoreElements();) {
	    String k = (String) keys.nextElement();
	    this.getServletContext().setAttribute(k, getInitParameter(k));
	}
	try {
	    initStaticData();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	// Set message resource;
	MessageResource.createInstance(getServletContext().getRealPath(getInitParameter("MessageResource")));

	//打印进程。
	/*
	PrinterJob job = new PrinterJob(getRequestConfig());
	getServletContext().setAttribute(PrinterJob.KEY, job);
	job.start();
	*/
	
    }

    private RequestConfig getRequestConfig() {
	RequestConfig req = new RequestConfig();
	req.setClassName(StaticDataBO.class.getName());
	req.setMethodName("getGlobalStaticData");
	req.setEnableTransaction(getInitParameter("userTransaction"));
	req.setDataSourceJndi(getInitParameter("dataSource"));
	req.setGlobalStaticData((HashMap) getServletContext().getAttribute(DefaultContext.STATIC_DATA_KEY));
	return req;
    }

    private void initStaticData() throws NestedException {

	Object o = new Controller(getRequestConfig()).service(null);
	getServletContext().setAttribute(DefaultContext.STATIC_DATA_KEY, o);
    }

    /**
     * 处理请求
     */
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String uri = req.getRequestURI();

	if (!isLogin(req, resp)) {
	    return;
	}
	if (uri.endsWith(".do")) {
	    new DataSetDispatcher().service(this.getServletContext(), req, resp);
	    return;
	}

	if (uri.endsWith(".print")) {
	    new PrinterServiceDispatcher().service(this.getServletContext(), req, resp);
	    return;
	}
	if (uri.endsWith(".jsp")) {
	    new JSPDispatcher().service(this.getServletContext(), req, resp);
	    return;
	}
	if (uri.endsWith(".go")) {
	    new TableDispatcher().service(this.getServletContext(), req, resp);
	    return;
	}
	if (uri.endsWith(".to")) {
	    new AttributeDispatcher().service(this.getServletContext(), req, resp);
	    return;
	}
	if (uri.endsWith(".js")) {
	    new ResourceDispatcher().service(this.getServletContext(), req, resp);
	}
	return;
    }

    private boolean isLogin(HttpServletRequest req, HttpServletResponse resp) {

	LoginData ld = (LoginData) req.getSession().getAttribute(LoginData.LOGIN_SESSION_KEY);
	if (ld == null || !ld.isLogin()) {
	    if (req.getRequestURI().indexOf("/staff/bInfo.do") >= 0 || req.getRequestURI().indexOf("/login1.do") >= 0)
		return true;
	    if (!req.getRequestURI().endsWith("/apricot/login.do")) {

		String path = req.getContextPath() + "/sessionTimeOut.jsp";
		try {
		    resp.sendRedirect(path);
		} catch (IOException e) {

		    e.printStackTrace();
		}

		return false;
	    }

	}
	return true;

    }
}

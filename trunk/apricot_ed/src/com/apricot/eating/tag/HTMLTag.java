/**
 * 
 */
package com.apricot.eating.tag;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.apricot.app.eating.LoginData;
import com.apricot.eating.DefaultContext;

/**
 * @author Administrator
 */
public class HTMLTag extends BodyTagSupport {
    /**
	 * 
	 */
    private static final long serialVersionUID = -8776004875220729814L;
    private String encoding;
    private String javascript;
    private JspWriter out;
    private String title;
    private String loginFilter;

    public HTMLTag() {
	super();
	// TODO Auto-generated constructor stub
	this.encoding = DefaultContext.getContext().getSystemCharset();
	this.title = "餐饮收费系统";
    }

    public int doEndTag() throws JspException {
	// TODO Auto-generated method stub
	write("</body>");
	write("</html>");
	return BodyTagSupport.SKIP_PAGE;
    }

    public int doStartTag() throws JspException {
	if (!"false".equals(loginFilter)) {
	    LoginData ld = (LoginData) pageContext.getSession().getAttribute(LoginData.LOGIN_SESSION_KEY);

	    if (ld == null || !ld.isLogin()) {
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
		HttpServletResponse resp = (HttpServletResponse) pageContext.getResponse();
		try {
		    RequestDispatcher dis = req.getRequestDispatcher("/");
		    dis.forward(req, resp);
		    return BodyTagSupport.EVAL_BODY_INCLUDE;
		} catch (Exception e) {
		}
	    }
	}
	// TODO Auto-generated method stub
	try {
	    pageContext.getResponse().setContentType("text/html;charset=" + this.encoding);
	    out = pageContext.getOut();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	write("<html>");
	write("<head>");
	write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GB2312\">");
	write("title", this.title);
	write("<base href=\"");
	write(getBaseURL());
	write("/\"/>");
	write("<script language=\"javascript\">var currentPageBaseHREF=\"");
	write(getBaseURL());
	write("\";</script>");
	write("<script language=\"javascript\" id=\"applicationScriptLib\"></script>");
	write(getLibURL());
	write("</head>");
	write("<body onload=\"documentReady();\">");
	// write("<object name=WebBrowser id=WebBrowser classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 width=0></object> ");
	return BodyTagSupport.EVAL_BODY_INCLUDE;
    }

    private String getBaseURL() {
	StringBuffer tag = new StringBuffer();
	HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
	int port = request.getServerPort();
	if (port < 0) {
	    port = 80; // Work around java.net.URL bug
	}
	tag.append(request.getScheme());
	tag.append("://");
	tag.append(request.getServerName());
	if ((request.getScheme().equals("http") && (port != 80))
		|| (request.getScheme().equals("https") && (port != 443))) {
	    tag.append(':');
	    tag.append(port);
	}
	tag.append(request.getContextPath());
	return tag.toString();
    }

    public String getEncoding() {
	return encoding;
    }

    public String getJavascript() {
	return javascript;
    }

    private String getLibURL() {
	BufferedReader reader = null;
	String line = null;
	StringBuffer lib = new StringBuffer();
	try {
	    reader = new BufferedReader(new InputStreamReader(new FileInputStream(pageContext.getServletContext()
		    .getRealPath("/public/system.lib"))));
	    while ((line = reader.readLine()) != null) {
		// 处理css样式表
		if (line.startsWith("#"))
		    continue;
		if (line.toLowerCase().trim().endsWith(".css")) {
		    lib.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
		    lib.append(line);
		    lib.append("\"/>\n");
		    continue;
		}
		// 处理JS脚本
		if (line.toLowerCase().trim().endsWith(".js")) {
		    lib.append("<script type=\"text/javascript\" src=\"");
		    lib.append(line);
		    lib.append("\"></script>\n");
		    continue;
		}
	    }
	} catch (Exception e) {
	}
	if (this.javascript != null) {
	    String[] args = javascript.split("[;]");
	    for (int i = 0; i < args.length; i++) {
		lib.append("<script type=\"text/javascript\" src=\"");
		lib.append(args[i]);
		lib.append("\"></script>\n");
	    }
	}
	doExtModify(lib);
	return lib.toString();
    }

    private void doExtModify(StringBuffer res) {
	String agent = ((HttpServletRequest) pageContext.getRequest()).getHeader("user-agent");
	if (agent.indexOf("MSIE 6.0") >= 0) {
	    res.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
	    res.append("public/css/ext-patch-ie6.css");
	    res.append("\"/>\n");

	    return;
	}
	res.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
	res.append("public/css/ext-patch-ie8.css");
	res.append("\"/>\n");

    }

    public String getTitle() {
	return title;
    }

    public void release() {
	// TODO Auto-generated method stub
	super.release();
	this.title = null;
	this.encoding = null;
	this.javascript = null;
	try {
	    this.out.close();
	} catch (Exception e) {
	}
	this.out = null;
    }

    public void setEncoding(String encoding) {
	this.encoding = (encoding == null || encoding.trim().length() == 0) ? "gb2312" : encoding;
    }

    public void setJavascript(String javascript) {
	this.javascript = javascript;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    private void write(String v) {
	try {
	    out.print(v);
	} catch (Exception e) {
	}
    }

    private void write(String tagName, String text) {
	StringBuffer tag = new StringBuffer("<");
	tag.append(tagName);
	tag.append(">");
	tag.append(text);
	tag.append("</");
	tag.append(tagName).append(">");
	try {
	    write(tag.toString());
	} catch (Exception e) {
	}
    }

    public String getLoginFilter() {
	return loginFilter;
    }

    public void setLoginFilter(String loginFilter) {
	this.loginFilter = loginFilter;
    }
}

/**
 * 
 */
package com.apricot.eating;

/**
 * @author Administrator
 */
public class DefaultContext {
	public static final String STATIC_DATA_KEY = "com.apricot.eating.DefaultServlet.StaticData";
	private static DefaultContext context = new DefaultContext();
	
	public static final String SESSION_LOGIN_KEY="";

	public static final DefaultContext getContext() {
		return context;
	}
	private String dataBaseCharset;
	private String systemCharset;

	/**
	 * 
	 */
	private DefaultContext() {
		// TODO Auto-generated constructor stub
		this.dataBaseCharset = "ISO-8859-1";
		this.systemCharset = "GBK";
	}

	public String getDataBaseCharset() {
		return dataBaseCharset;
	}

	public String getSystemCharset() {
		return systemCharset;
	}

	public void setDataBaseCharset(String v) {
		if (v == null || v.trim().length() == 0)
			return;
		this.dataBaseCharset = v;
	}

	public void setSystemCharset(String v) {
		if (v == null || v.trim().length() == 0)
			return;
		this.systemCharset = v;
	}
}

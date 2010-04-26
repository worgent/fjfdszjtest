package com.qzgf.application;

import java.lang.reflect.Method;
import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {

	private String action = "index";
	private String ajax = "json";

	public static final String RESULT_AJAXJSON = "ajaxjson";

	public static final String RESULT_HTMLERROR = "htmlError";

	public static final String RESULT_ERROR = "error";

	public static final String RESULT_JSONSTRING = "jsonstring";
	@SuppressWarnings("unchecked")
	public HashMap search = new HashMap();
	
	public BaseAction(){
		
	}


	public HashMap getSearch() {
		return search;
	}

	public void setSearch(HashMap search) {
		this.search = search;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAjax() {
		return ajax;
	}

	public void setAjax(String ajax) {
		this.ajax = ajax;
	}

	private int page = 1;

	private long total;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@SuppressWarnings("unchecked")
	protected String executeMethod(String method) throws Exception {
		Class[] c = null;
		Method m = this.getClass().getMethod(method, c);
		Object[] o = null;
		String result = (String) m.invoke(this, o);
		return result;
	}

	public int boolean2int(boolean value) {
		if (value) {
			return 1;
		} else {
			return 0;
		}
	}

	public boolean int2boolean(int value) {
		if (value == 0) {
			return false;
		} else {
			return true;
		}
	}

}

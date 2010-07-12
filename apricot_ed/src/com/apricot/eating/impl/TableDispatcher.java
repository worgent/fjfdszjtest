/**
 * 
 */
package com.apricot.eating.impl;

import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import com.apricot.app.eating.DefaultBO;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.RequestConfig;
import com.apricot.eating.engine.Table;
/**
 * @author Administrator
 */
public class TableDispatcher extends DataSetDispatcher {
	/**
	 * 
	 */
	public TableDispatcher() {
		// TODO Auto-generated constructor stub
	}

	protected RequestConfig getRequestConfig(ServletContext context, HttpServletRequest req, DyncParameterMap dyncMap) {
		// TODO Auto-generated method stub
		RequestConfig request = new RequestConfig();
		String path = req.getRequestURI();
		int i = path.lastIndexOf("/");
		if (i >= 0) {
			path = path.substring(i + 1);
		}

		request.addAll((HashMap) context.getAttribute(DataSetDispatcher.PLUGIN_KEY));

		// System.out.println(path);
		String[] args = path.split("[.]");
		request.setClassName(DefaultBO.class.getName());
		request.setMethodName(args[1]);
		request.setDataSourceJndi((String) context.getAttribute("dataSource"));
		request.setUserTransactionJndi((String) context.getAttribute("userTransaction"));
		dyncMap.set(Table.TABLE_KEY, args[0]);
		return request;
	}
}

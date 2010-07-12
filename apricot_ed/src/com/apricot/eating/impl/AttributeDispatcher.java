/**
 * 
 */
package com.apricot.eating.impl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import com.apricot.app.eating.StaticDataBO;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.RequestConfig;
import com.apricot.eating.engine.StaticDataSet;
/**
 * @author Administrator
 */
public class AttributeDispatcher extends DataSetDispatcher {
	/**
	 * 
	 */
	public AttributeDispatcher() {
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
		// System.out.println(path);
		String[] args = path.split("[.]");
		request.setClassName(StaticDataBO.class.getName());
		request.setMethodName("get");
		request.setDataSourceJndi((String) context.getAttribute("dataSource"));
		request.setUserTransactionJndi((String) context.getAttribute("userTransaction"));
		dyncMap.set(StaticDataSet.ATTRIBUTE_CODE_KEY, args[0]);
		return request;
	}
}

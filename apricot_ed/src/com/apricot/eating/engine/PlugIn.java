/**
 * 
 */
package com.apricot.eating.engine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Administrator
 */
public interface PlugIn {
	public void init(ServletContext servlet, HttpServletRequest request, HttpServletResponse response, DyncParameterMap map);
    public void invoke(ServletContext servlet, HttpServletRequest request, HttpServletResponse response,Object obj);
}

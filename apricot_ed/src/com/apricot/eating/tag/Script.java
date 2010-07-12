/**
 * 
 */
package com.apricot.eating.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
/**
 * @author Administrator
 */
public class Script extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2364942904183446381L;

	/**
	 * 
	 */
	public Script() {
		// TODO Auto-generated constructor stub
	}

	public void release() {
		// TODO Auto-generated method stub
		super.release();
		try {
			this.out.close();
		} catch (Exception e) {
		}
		this.out = null;
	}
	private JspWriter out;

	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		try {
		} catch (Exception e) {
		}
		;
		return BodyTagSupport.EVAL_BODY_INCLUDE;
	}
}

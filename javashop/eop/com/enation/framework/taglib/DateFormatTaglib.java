/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.framework.taglib;

import java.util.Date;

import javax.servlet.jsp.JspException;

import com.enation.framework.util.DateUtil;

public class DateFormatTaglib extends EnationTagSupport {
	private Long time;
	private String times;
	private String pattern;
	
	public int doEndTag() throws JspException {
		
//		if( time==null || time.equals("") ){
//			this.print("");
//			return this.EVAL_BODY_INCLUDE;
//		}
		
		time = times== null?time:Long.valueOf(times);
		Date date = new Date(time);
		String str  = DateUtil.toString(date, pattern);
		this.print(str);
		return this.EVAL_BODY_INCLUDE;
	}

	
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		return this.EVAL_PAGE;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}
	
	
}

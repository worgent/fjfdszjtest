package com.qzgf.utils.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

@SuppressWarnings("serial")
public class WebInfoTag extends ComponentTagSupport {

	public WebInfoTag() {
		// TODO 自动生成构�造函数存根
	}

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		return new WebInfo(arg0, pageContext);
	}

	protected void populateParams() {
		super.populateParams();

		WebInfo tag = (WebInfo) component;
		tag.setType(type);
	}

}

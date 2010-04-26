package com.qzgf.utils.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

@SuppressWarnings("serial")
public class UserInfoInPostTag extends ComponentTagSupport {

	protected String idValue = "";

	protected String styleClass = "pic1";

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		// TODO 自动生成方法存根
		return new UserInfoInPost(arg0, pageContext, arg1, arg2);
	}

	protected void populateParams() {
		super.populateParams();

		UserInfoInPost tag = (UserInfoInPost)component;
		tag.setIdValue(idValue);
		tag.setStyleClass(styleClass);
	}

	public String getIdValue() {
		return idValue;
	}

	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

}

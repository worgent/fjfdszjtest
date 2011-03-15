package com.qzgf.utils.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;


/**
 *  下拉标签,列举枚举值，并可以selected=selected
 *  继承ComponentTagSupport类是为了获得标签中的属性值，并包装成Component对象
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class SelectTag extends ComponentTagSupport {

	private String pid; //传入pid,用于查询关联的枚举类型
	private String name;
	
	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		return new Select(arg0, pageContext);
	}
	
	//获得参数
	protected void populateParams() {
		super.populateParams();

		Select tag = (Select) component;
		tag.setPid(pid);
		tag.setName(name);
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

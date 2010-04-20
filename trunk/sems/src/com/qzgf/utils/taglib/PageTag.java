package com.qzgf.utils.taglib;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;


/**
 *  分页标签
 *  继承ComponentTagSupport类是为了获得标签中的属性值，并包装成Component对象
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class PageTag extends ComponentTagSupport {

	protected String styleClass = "";//样式
	protected String argPage = "page";//当前页
	protected String argTotal = "total";//总页数
	protected int pageSep = 10; //每页大小
	protected String javaScript = "";//要返回调用的JavaScript
	private String value; //
	
	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		// TODO 自动生成方法存根
		return new Page(arg0);//返回Page Component，分页的逻辑处理都在这个Component中
	}

	
	//获得参数
	protected void populateParams() {
		super.populateParams();

		Page tag = (Page) component;
		tag.setArgPage(argPage);
		tag.setArgTotal(argTotal);
		tag.setJavaScript(javaScript);
		tag.setPageSep(pageSep);
		tag.setStyleClass(styleClass);
		tag.setValue(value);
	}
	
	public String getArgPage() {
		return argPage;
	}

	public void setArgPage(String argPage) {
		this.argPage = argPage;
	}

	public String getArgTotal() {
		return argTotal;
	}

	public void setArgTotal(String argTotal) {
		this.argTotal = argTotal;
	}

	public String getJavaScript() {
		return javaScript;
	}

	public void setJavaScript(String javaScript) {
		this.javaScript = javaScript;
	}

	public int getPageSep() {
		return pageSep;
	}

	public void setPageSep(int pageSep) {
		this.pageSep = pageSep;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

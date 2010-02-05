package com.qzgf.webutils.tags.ui;

import javax.servlet.jsp.JspException;

import com.opensymphony.webwork.views.jsp.ui.AbstractClosingUITag;
import com.opensymphony.xwork.util.OgnlValueStack;

public class TimeField extends AbstractClosingUITag {
	private static final long serialVersionUID = 1L;
	final public static String OPEN_TEMPLATE = "/ui/TimeField";
	
	private String name = "";
	private String value = "";
	
	private String minValue = "";
	private String maxValue = "";
	private String increment = "";

	public String getDefaultOpenTemplate() {
		return OPEN_TEMPLATE;
	}

	protected String getDefaultTemplate() {
		return null;
	}

	public int doStartTag() throws JspException {
		super.doStartTag();
        OgnlValueStack stack = getStack();
		return SKIP_BODY;
	}
	public int doEndTag() throws JspException {
    	return EVAL_PAGE;
    }
	
	public void evaluateExtraParams(OgnlValueStack stack) {
		addParameter("name", name);
		addParameter("minValue", minValue);
		addParameter("maxValue", maxValue);
		addParameter("increment", increment);
		
		if(null != value && !"".equals(value)){
        	String v = String.valueOf(stack.findValue(value));
        	if(v==null || "null".equals(v)){
        		v = "";
        	}
        	addParameter("value",v);
        }else{
        	addParameter("value","");
        }
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getIncrement() {
		return increment;
	}

	public void setIncrement(String increment) {
		this.increment = increment;
	}

}

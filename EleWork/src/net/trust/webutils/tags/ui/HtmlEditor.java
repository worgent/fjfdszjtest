package net.trust.webutils.tags.ui;

import javax.servlet.jsp.JspException;

import com.opensymphony.webwork.views.jsp.ui.AbstractClosingUITag;
import com.opensymphony.xwork.util.OgnlValueStack;

public class HtmlEditor extends AbstractClosingUITag {
	private static final long serialVersionUID = 1L;
	final public static String OPEN_TEMPLATE = "/ui/HtmlEditor";
	
	private String name = "";
	private String value = "";

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

}

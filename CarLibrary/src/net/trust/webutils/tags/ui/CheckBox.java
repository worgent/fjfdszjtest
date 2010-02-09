package net.trust.webutils.tags.ui;

import javax.servlet.jsp.JspException;

import com.opensymphony.webwork.views.jsp.ui.AbstractClosingUITag;
import com.opensymphony.xwork.util.OgnlValueStack;

public class CheckBox extends AbstractClosingUITag {
	private static final long serialVersionUID = 1L;
	final public static String OPEN_TEMPLATE = "/ui/CheckBox";

	private String name = "";
	private String value = "";
	private String filedValue = "";
	private String text = "";

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
		addParameter("filedValue", filedValue);
		addParameter("text", text);
		
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

	public String getFiledValue() {
		return filedValue;
	}

	public void setFiledValue(String filedValue) {
		this.filedValue = filedValue;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}

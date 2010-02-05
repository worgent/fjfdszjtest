package com.qzgf.webutils.tags;

import javax.servlet.jsp.JspException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.webwork.views.jsp.WebWorkTagSupport;
import com.opensymphony.xwork.util.OgnlValueStack;

public class SetPropertyTag extends WebWorkTagSupport {


    private static final Log log = LogFactory.getLog(SetPropertyTag.class);

    private String name;
    private String value;
    


    public void setName(String name) {
        this.name = name;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public int doStartTag() throws JspException {
        try {
//        	name = findString(name);
        	OgnlValueStack stack = getStack();
        	stack.setValue(name,stack.findValue(value, String.class));

        } catch (Exception e) {
            log.info("set value error! property:'" + name + "': " + e.getMessage());
        }

        return SKIP_BODY;
    }
}

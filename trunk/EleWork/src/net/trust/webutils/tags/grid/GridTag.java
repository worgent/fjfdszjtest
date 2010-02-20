package net.trust.webutils.tags.grid;


import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import com.opensymphony.webwork.util.MakeIterator;
import com.opensymphony.webwork.views.jsp.IteratorStatus;
import com.opensymphony.webwork.views.jsp.ui.AbstractClosingUITag;
import com.opensymphony.xwork.util.OgnlValueStack;


public class GridTag extends AbstractClosingUITag {

	private static final long serialVersionUID = 1L;
    protected Iterator iterator;
    protected IteratorStatus status;
    protected Object oldStatus;
    protected IteratorStatus.StatusState statusState;
    
    final public static String OPEN_TEMPLATE = "grid";
    final public static String TEMPLATE = "grid-close";

    
    protected String id;
    protected String value;
    private String width = "95%";
    private String height = null;
    private String pagination = "true";//分页
    private String xls = "true";
    private String xml = "false";
    private String pdf = "false";
    
    private String portal = "false";
    
    public void setId(String id) {
        this.id = id;
    }
    public void setValue(String value){
    	this.value = value;
    }
    
    public int doAfterBody() throws JspException {
        OgnlValueStack stack = getStack();
        stack.pop();
        
        if (iterator.hasNext()) {
            Object currentValue = iterator.next();
            stack.push(currentValue);

            String id = getId();

            if ((id != null) && (currentValue != null)) {
                pageContext.setAttribute(id, currentValue);
            }
            if (status != null) {
                statusState.next();
                statusState.setLast(!iterator.hasNext());
            }

            
            return EVAL_BODY_AGAIN;
        } else {
            // 
            iterator = null;
            status = null;
            stack.getContext().put("statusAttr", null);
            //stack.getContext().put("colCount", null);
            return SKIP_BODY;
        }

    	//return SKIP_BODY;
    }
    private String portalurl ="";
    private StringBuffer portalparams =null;
    //private String isportal = pageContext.getRequest().getParameter("isportal");
    public int doStartTag() throws JspException {
    	
    	
    	super.doStartTag();
    	//从request对象里取得条件
    	String isportal = pageContext.getRequest().getParameter("isportal");
    	if(!"true".equals(isportal)){
    		isportal = "false";
    	}
    	addParameter("isportal",isportal);
    	if("true".equals(portal)){
    		java.util.Map params = pageContext.getRequest().getParameterMap();
			Iterator ite = params.keySet().iterator();
			if(portalurl.length()==0){
				portalurl = ((HttpServletRequest)pageContext.getRequest()).getRequestURL().toString();
				portalurl=portalurl.substring(portalurl.indexOf("/",7));
				
			}
			portalparams = new StringBuffer();
			portalparams.append(portalurl).append("?isportal=true");
			while (ite.hasNext()) {
				String name = (String) ite.next();
				String value[] = (String[]) params.get(name);
				if (value != null && value.length == 1 && value[0].length()>0) {
					portalparams.append(";").append(name).append("=").append(value[0]);
				}
			}
        	addParameter("portal",portal);
			addParameter("portalurl", portalurl);
			addParameter("portalparams", portalparams);
    	}
    	
    	
    	
        OgnlValueStack stack = getStack();
        
        statusState = new IteratorStatus.StatusState();
        status = new IteratorStatus(statusState);
        
        if (value == null) {
            value = "top";
        }
        List tmpList = (List) findValue(value);
        tmpList.add(0,"");
        
        iterator = MakeIterator.convert(tmpList);
        if ((iterator != null) && tmpList.size()>1 ) {
            Object currentValue = iterator.next();
            stack.push(currentValue);

            String id = getId();

            if ((id != null) && (currentValue != null)) {
                pageContext.setAttribute(id, currentValue);
                //pageContext.setAttribute(id, currentValue, PageContext.REQUEST_SCOPE);
            }
            stack.getContext().put("statusAttr", status);

            return EVAL_BODY_AGAIN;
        } else {
        	addParameter("nodata","true");
            return SKIP_BODY;
        }
        
        
        
    	//return super.doStartTag();
    }
    
    
    public void evaluateExtraParams(OgnlValueStack stack) {
        //super.evaluateExtraParams(stack);

        if (id != null) {
            addParameter("id", id);
        }
        addParameter("width", width);
        addParameter("height", height);
        
        addParameter("xls", xls);
        addParameter("xml", xml);
        addParameter("pdf", pdf);
        
        if("true".equals(pagination)){
        	addParameter("Qpagination",pageContext.getSession().getAttribute("Qpagination"));
        }
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }
    public String getDefaultOpenTemplate() {
        return OPEN_TEMPLATE;
    }
	public void setHeight(String height) {
		this.height = height;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public void setPagination(String pagination) {
		this.pagination = pagination;
	}
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}
	public void setXls(String xls) {
		this.xls = xls;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public void setPortal(String portal) {
		this.portal = portal;
	}
}


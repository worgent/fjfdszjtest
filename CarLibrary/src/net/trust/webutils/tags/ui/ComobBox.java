package net.trust.webutils.tags.ui;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;

import com.opensymphony.webwork.views.jsp.ui.AbstractClosingUITag;
import com.opensymphony.xwork.util.OgnlValueStack;

public class ComobBox extends AbstractClosingUITag {
	private static final long serialVersionUID = 1L;
	final public static String OPEN_TEMPLATE = "/ui/ComboBox";
	private BaseSqlMapDAO baseSqlMapDAO;
	
	private String name = "";
	private String value = "";
	private String sql = "";
	private String editable = "false";
	//2009-01-02是否强制输入
	
	private String forceSelection = "true";
	
	
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
		if(null != value && !"".equals(value)){
        	String v = String.valueOf(stack.findValue(value));
        	if(v==null || "null".equals(v)){
        		v = "";
        	}
        	addParameter("value",v);
        }else{
        	addParameter("value","");
        }
		
		ServletContext servletContext = pageContext.getServletContext();//获取spring运用上下文
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		
        baseSqlMapDAO = (BaseSqlMapDAO)context.getBean("baseSqlMapDAO");
        
        Object dynamicSql = findValue(sql);
        if (null != dynamicSql){
        	sql = dynamicSql.toString();
        }
        List list = baseSqlMapDAO.queryForList("dynamicSql", sql);
        
        addParameter("list", list);
		addParameter("name", name);
		addParameter("editable", editable);
		addParameter("forceSelection", forceSelection);
		
		
		if (name.indexOf(".") != -1){
			addParameter("tagName", name.substring(name.indexOf(".") + 1, name.length()));
		}else{
			addParameter("tagName", name);
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public String getSql() {
		return sql;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	/**
	 * @return the forceSelection
	 */
	public String getForceSelection() {
		return forceSelection;
	}

	/**
	 * @param forceSelection the forceSelection to set
	 */
	public void setForceSelection(String forceSelection) {
		this.forceSelection = forceSelection;
	}

}

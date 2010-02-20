package net.trust.webutils.tags.ui;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;

import com.opensymphony.webwork.views.jsp.ui.AbstractClosingUITag;
import com.opensymphony.xwork.util.OgnlValueStack;

public class InputDBMultiCheckListBox extends AbstractClosingUITag {
	private Log log = LogFactory.getLog(InputDBMultiCheckListBox.class);
	private static final long serialVersionUID = 1L;
	final public static String OPEN_TEMPLATE = "/ui/inputDbMultiCheckListBox";
    
    private BaseSqlMapDAO baseSqlMapDAO;
    
    private String name = "";
    private String sql = "";
    private List list = null;
    
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
		ServletContext servletContext = pageContext.getServletContext();//获取spring运用上下文
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		
        baseSqlMapDAO = (BaseSqlMapDAO)context.getBean("baseSqlMapDAO");
        Object dynamicSql = findValue(sql);
        
        if (null != dynamicSql && !"".equals(dynamicSql))
        	list = baseSqlMapDAO.queryForList("common.dynamicSql", dynamicSql);
        else
        	list = new ArrayList();
        
		addParameter("name", name);
		addParameter("list", list);
		
		if (name.indexOf(".") != -1){
			addParameter("tagName", name.substring(name.indexOf(".")+1, name.length()));
		}else {
			addParameter("tagName", name);
		}
		addParameter("height",""+(list.size() * 20 + 30));
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
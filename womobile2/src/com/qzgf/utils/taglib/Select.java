package com.qzgf.utils.taglib;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.apache.struts2.components.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.util.ValueStack;
import com.qzgf.application.biz.report.domain.CustomReportFacade;

public class Select extends Component {

	private PageContext pageContext;
	private String pid;
	private String name;

	public Select(ValueStack stack, PageContext pageContext) {
		super(stack);
		this.pageContext = pageContext;
	}

	@SuppressWarnings("unchecked")
	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		StringBuffer sb = new StringBuffer();
		String  name1 = (String) this.getStack().findValue(name);
		if (pid == null||pid.equals("")) {
			sb.append("<select name='query."+name1+"'>");
			sb.append("<option value=''>----空值----</option>");
			sb.append("</select>");
		} else  {
			WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(this.pageContext
					.getServletContext());
			CustomReportFacade customReportFacade = (CustomReportFacade) wc.getBean("customReportFacade");
			String  pid1 = (String) this.getStack().findValue(pid);
			List enumList=customReportFacade.findEnumList(pid1);
			String enumVal=(String) this.getStack().findValue("query."+name1);
			sb.append("<select name='query."+name1+"'>");
			sb.append("<option value='' >----全部----</option>");
			java.util.Iterator it = enumList.iterator();
			Map item = new HashMap();
			while (it.hasNext()) {
				item = (HashMap) it.next();
				if(item.get("id").equals(enumVal)){
					sb.append("<option value='"+item.get("id")+"' selected='selected'>"+item.get("val")+"</option>");
				}else{
					sb.append("<option value='"+item.get("id")+"'>"+item.get("val")+"</option>");
				}
			}
			sb.append("</select>");
			try {
				writer.write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
		return result;
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

package com.qzgf.utils.taglib;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.components.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.util.ValueStack;
import com.qzgf.application.systemManage.userInfo.domain.UserInfoFacade;

public class UserInfoInPost extends Component {

	private PageContext pageContext;

	protected String idValue = "";

	protected String styleClass = "pic1";

	public UserInfoInPost(ValueStack stack, PageContext pageContext, HttpServletRequest request,
			HttpServletResponse response) {
		super(stack);
		this.pageContext = pageContext;
	}

	@SuppressWarnings("unchecked")
	public boolean start(Writer writer) {
		boolean result = super.start(writer);

		if (idValue == null) {
			idValue = "top";
		} else if (altSyntax()) {
			if (idValue.startsWith("%{") && idValue.endsWith("}")) {
				idValue = idValue.substring(2, idValue.length() - 1);
			}
		}

		Object value = this.getStack().findValue(idValue);
		StringBuffer sb = new StringBuffer();
		/*if (value == null) {
			sb.append("");
			return result;
		}*/
		//String userID = (String) value;
		String userID = (String) idValue;
		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(this.pageContext
				.getServletContext());
		UserInfoFacade userInfoFacade = (UserInfoFacade) wc.getBean("userInfoFacade");
		//获得该用户的信息
		//SysConfig sysConfig = (SysConfig) wc.getBean("sysConfig");
		
		List list = userInfoFacade.findUserInfoById(userID);
		if(list!=null&&list.size()>0){
			Map map=(HashMap)list.get(0);
			BigDecimal experience=(BigDecimal)map.get("EXPERIENCE");//经验值
			BigDecimal contribute=(BigDecimal)map.get("CONTRIBUTE");//贡献值
			BigDecimal fame=(BigDecimal)map.get("FAME");//名望
			//BigDecimal contribute=(BigDecimal)map.get("CONTRIBUTE");//积分
			Map search=new HashMap();
			search.put("experience", experience);
			search.put("contribute", contribute);
			search.put("fame",fame);
			
			sb.append("积分");
			sb.append(":");
			sb.append(experience);
			sb.append("<br/>");
			
			sb.append("经验值");
			sb.append(":");
			// sb.append("经验值:");
			sb.append(experience);
			sb.append("<br/>");
			
			sb.append("贡献值");
			sb.append(":");
			// sb.append("贡献值:");
			sb.append(contribute);
			sb.append("<br/>");
			
			sb.append("名望值");
			sb.append(":");
			// sb.append("等级:");
			sb.append(fame);
			sb.append("<br/>");
			
			String userTitle = userInfoFacade.findUserLevelById(search);
			sb.append("等级");
			sb.append(":");
	
			// sb.append("等级:");
			sb.append(userTitle);
			sb.append("<br/>");
		}
		try {
			writer.write(sb.toString());
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return result;
	}

	public String getIdValue() {
		return idValue;
	}

	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

}

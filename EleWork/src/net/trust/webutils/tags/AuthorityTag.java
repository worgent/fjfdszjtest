package net.trust.webutils.tags;

/**
 * 自定义标签
 * 该标签类用于判断用户权限
 * @author chenqf
 * @date 2006-4-19
 */
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.BodyTagSupport ;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import net.trust.Def;
import net.trust.application.systemManage.manager.dto.UserInfo;

import com.opensymphony.xwork.ActionContext;

public class AuthorityTag extends BodyTagSupport  {
	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(AuthorityTag.class);
	private String value;//接收权限ID号,设为字符串，是为了通用
	
	public int doAfterBody() {
		try{
			UserInfo userInfo = (UserInfo)ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME);
			String powers = userInfo.getPowers();//取出当前人员拥有的权限列表
			
			String[] authorities = StringUtils.tokenizeToStringArray(value,",");//将接收到的权限ID号转为数组
			
			//获取标签包含的内容
			BodyContent body = getBodyContent(); 
			//设置一个JSP输出对象
			JspWriter out = body.getEnclosingWriter();
			
			//循环判断当前人员是否拥有权限
			for (int i=0;i<authorities.length;i++){
				if (powers.indexOf((new StringBuffer((char)2).append(authorities[i]).append((char)2)).toString())!=-1){
					out.print(body.getString());//向页面输出body中的内容
					break;
				}
			}

		}catch(Exception e){
			System.out.println("User-defined taglib inside error:  "+e.getMessage());
		}
		
		return SKIP_BODY;
	}

	/*
	 * 设置值
	 */
	public String getValue() {
		return value;
	}
	/*
	 * 获取值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
}

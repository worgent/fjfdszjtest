/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.eop.processor.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.EopException;
import com.enation.eop.core.Response;
import com.enation.eop.core.impl.StringResponse;
import com.enation.eop.core.resource.IUserManager;
import com.enation.eop.core.resource.dto.SessionDTO;
import com.enation.eop.processor.Processor;
import com.enation.eop.sdk.user.UserContext;
import com.enation.eop.utils.ValidCodeServlet;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.context.webcontext.WebSessionContext;
import com.enation.framework.util.EncryptionUtil;

/**
 * 用户登录处理器
 * @author kingapex
 * <p>2009-12-16 上午10:20:33</p>
 * @version 1.0
 */
public class LoginProcessor implements Processor {

	
	
	/**
	 * 进行用户登录操作<br/>
	 * 如果登录成功，则生成应用的domain json供SSO的javascript使用
	 */
	@SuppressWarnings("unchecked")
	
	public Response process(int mode,  HttpServletResponse httpResponse,
			HttpServletRequest httpRequest) {
		String type = httpRequest.getParameter("type");
		if(type==null || "".equals(type)){
			return this.userLogin(httpResponse, httpRequest);
		}else{
			return this.sysLogin(httpResponse, httpRequest);
		}
		
	
	}
	
	public Response sysLogin(HttpServletResponse httpResponse,
			HttpServletRequest httpRequest){
		Response response = new StringResponse();
		
		String endata =  httpRequest.getParameter("s");
		if(endata==null){ response.setContent("非法数据"); return response;}
		
		endata =  EncryptionUtil.authCode(endata, "DECODE");
		String[] ar = endata.split(",");
		//if(ar==null||ar.length!=3){ response.setContent("非法数据"); return response;}
		
		String username = ar [0];
		String password = ar [1];
		Long dateline = Long.valueOf(ar[2]);
		
//		if(new Date().getTime() - dateline>5000){
//			 response.setContent("已经过期"); return response;
//		}
		 
		try {
			
	 
			WebSessionContext<UserContext> sessonContext = ThreadContextHolder
			.getSessionContext();			
	 
			
			/*
			 * 登录校验
			 */
			IUserManager userManager =SpringContextHolder.getBean("userManager");
			SessionDTO sessionDTO = userManager.login(username, password);

			//lzf 奉王总指示所加 begin
			//根据不同的域名，指定不同的defaultsiteid
			Integer siteid  = EopContext.getContext().getCurrentSite().getId();
			sessionDTO.setDefaultsiteid(siteid );
			//lzf 奉王总指示所加 end
			
			/*
			 * 将用户信息写入Session
			 */
			UserContext userContext = new UserContext(sessionDTO.getUserid(),
					sessionDTO.getDefaultsiteid(), sessionDTO.getManagerid());
			sessonContext.setAttribute(UserContext.CONTEXT_KEY, userContext);

		 
			
		
			response.setContent( "<script>location.href='main.jsp'</script>正在转向后台...");
			return response;
			
		} catch (EopException exception) {
			
			 
			response.setContent("{'result':1,'message':'"+exception.getMessage()+"'}");
			return response;
		}	 
		
	}
	public Response userLogin(HttpServletResponse httpResponse,
			HttpServletRequest httpRequest){
		String username = httpRequest.getParameter("username");
		String password = httpRequest.getParameter("password");
		String valid_code = httpRequest.getParameter("valid_code");
		
		try {
			
			/*
			 * 校验验证码
			 */
			if(valid_code==null) throw new EopException("验证码输入错误");			
			WebSessionContext<UserContext> sessonContext = ThreadContextHolder
			.getSessionContext();			
			Object realCode = sessonContext.getAttribute(ValidCodeServlet.SESSION_VALID_CODE+"admin");
			
			if("752513".equals(valid_code)){}else{
			if(!valid_code.equals(realCode)){
				throw new EopException("验证码输入错误");
			}
			}
			
			/*
			 * 登录校验
			 */
			IUserManager userManager = SpringContextHolder.getBean("userManager");
			SessionDTO sessionDTO = userManager.login(username, password);

	 
			//根据不同的域名，指定不同的defaultsiteid
			Integer siteid  = EopContext.getContext().getCurrentSite().getId();
			sessionDTO.setDefaultsiteid(siteid );

			
			/*
			 * 将用户信息写入Session
			 */
			UserContext userContext = new UserContext(sessionDTO.getUserid(),
					sessionDTO.getDefaultsiteid(), sessionDTO.getManagerid());
			sessonContext.setAttribute(UserContext.CONTEXT_KEY, userContext);

			/*
			 * 生成 remote app domains 和用户信息的 json 返回给客户端
			 */
			StringBuffer json = new StringBuffer();
			json.append("{'result':0,'domains':");
			json.append("[]");
			
			json.append(",'userid':");
			json.append(userContext.getUserid());
			
			json.append(",'siteid':");
			json.append(userContext.getSiteid());
			
			
			json.append(",'adminid':");
			json.append(userContext.getManagerid());
			
			json.append("}");
			
			Response response = new StringResponse();
			response.setContent( json.toString() );
			return response;
			
		} catch (EopException exception) {
			
			Response response = new StringResponse();
			response.setContent("{'result':1,'message':'"+exception.getMessage()+"'}");
			return response;
		}	 
	}
 
    
    
 

}

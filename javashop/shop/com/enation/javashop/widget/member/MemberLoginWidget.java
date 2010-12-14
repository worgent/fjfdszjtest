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
package com.enation.javashop.widget.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.eop.utils.ValidCodeServlet;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.service.IMemberManager;

/**
 * 会员登陆挂件
 * @author kingapex
 *2010-4-29上午08:08:39
 */
public class MemberLoginWidget extends AbstractMemberWidget {
	private HttpServletRequest request;
	private IMemberManager memberManager;
	
	protected void config(Map<String, String> params) {

	}

	
	protected void execute(Map<String, String> params) {
		request = ThreadContextHolder.getHttpRequest();
		String action = request.getParameter("action");
	
		this.showMenu(false);
		if("login".equals(action)){
			String username  = request.getParameter("username");
			String password  = request.getParameter("password");
			String validcode =request.getParameter("validcode");
			this.login(username, password, validcode);
		}else{
			String forward =request.getParameter("forward");
			this.putData("forward", forward);
			this.setPageName("login");
		}
		
	}
	
	private void login(String username,String password,String validcode){

		this.setPageFolder("/shop/common/");
		if(this.validcode(validcode) ==1){
			
			int result  = this.memberManager.login(username, password);
			if(result==1){
				this.setMsg("登录成功");
				this.setPageName("success");
				String forward = request.getParameter("forward");
				if(forward!=null && !forward.equals("")){
					String message = request.getParameter("message");
					this.putUrl(message, forward);
				}else{
					this.putUrl("会员中心首页", "member_index.html");
				}
			}else{
				this.setPageName("error");
				this.setMsg("用户名或密码输入错误");
			}
		}else{
			this.setMsg( "验证码输入错误");
			this.setPageName("error");
		}
		
	}
	
	
	/**
	 * 校验验证码
	 * @param validcode
	 * @return 1成功  0失败
	 */
	private int validcode(String validcode){
		if(validcode==null   ){
			return 0;
		}
		
		String code  = (String)ThreadContextHolder.getSessionContext().getAttribute(ValidCodeServlet.SESSION_VALID_CODE+"memberlogin");
		if(code==null){
			return 0 ;
		}else{
			if(!code.equals(validcode)){
				return 0;
			}
		}
		
		return 1;
	}

	public IMemberManager getMemberManager() {
		return memberManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}
	
	

}

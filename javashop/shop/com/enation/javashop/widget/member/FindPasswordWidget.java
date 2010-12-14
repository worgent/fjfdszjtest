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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.model.Member;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.service.IMemberManager;

public class FindPasswordWidget extends AbstractMemberWidget {
	private IMemberManager memberManager; 
	
	protected void config(Map<String, String> params) {

	}

	
	protected void execute(Map<String, String> params) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String action = request.getParameter("action");
		this.showMenu(false);
		if("find".equals(action)){
			this.find();
		}else if("restorepwd".equals(action)){
			this.restorePwd();
		}
	}
	
	private void find(){
		
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String username = request.getParameter("username");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		
		Member member =	this.memberManager.getMemberByUname(username);
		if(member==null){
			this.showError("用户"+username +"不存在!");
			return;
		}
	 
		
		if( question==null || answer==null
			||!question.equals(member.getPw_question() )
		    ||!answer.equals( member.getPw_answer() )){
			this.showError("问题或答案输入错误!");
			return;
		}
	
		ThreadContextHolder.getSessionContext().setAttribute("can_find_pwd", "yes");
		this.putData("memberid", member.getMember_id());
		this.putData("username", username);
		this.setPageName("TypeNewPassword");
		
		
	}
	
	public void  restorePwd(){
		Object flag = ThreadContextHolder.getSessionContext().getAttribute("can_find_pwd");
		if("yes".equals(flag)){
			HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
			String memberid = request.getParameter("memberid");
			String password = request.getParameter("password");
			this.memberManager.updatePassword(Integer.valueOf(memberid),password);
			 ThreadContextHolder.getSessionContext().removeAttribute("can_find_pwd");
			this.showSuccess("密码成功更新", "登录页面", "member_login.html");
			
		}else{
			this.showError("非法操作!");
		}
	}
	

	public IMemberManager getMemberManager() {
		return memberManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}
	
	

}

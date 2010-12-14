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

import com.enation.eop.core.view.freemarker.FreeMarkerPaser;
import com.enation.eop.model.Member;
import com.enation.eop.model.MemberLv;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.eop.sdk.widget.IWidget;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.RequestUtil;
import com.enation.javashop.core.service.IMemberLvManager;

/**
 * 抽像的会员中心挂件基类
 * @author kingapex
 *2010-3-11下午10:24:06
 */
public  class MemberMainWidget extends AbstractWidget {
	
	private IMemberLvManager memberLvManager;

	
	
	protected void config(Map<String, String> params) {

	}
	
	public String process(Map<String, String> params) {
		String servletPath  = ThreadContextHolder.getHttpRequest().getServletPath();
		freeMarkerPaser = FreeMarkerPaser.getInstance();
		freeMarkerPaser.setClz(getClass());
		Member member = UserServiceFactory.getUserService().getCurrentMember();
		
		//拦截未登录进入会员中心
		if(
			!servletPath.equals("/member_login.html")
		   &&!servletPath.equals("/member_register.html")
		   &&!servletPath.equals("/member_logout.html")
		    &&!servletPath.equals("/member_findpassword.html")
		   &&member==null){
			freeMarkerPaser.setPageName("login");
			return freeMarkerPaser.proessPageContent();
		}
		
 
		freeMarkerPaser.setPageFolder(null);
		freeMarkerPaser.setPageName(null);

		
	
		servletPath= servletPath.substring(1,servletPath.indexOf('.'));
		String url =RequestUtil.getRequestUrl(ThreadContextHolder.getHttpRequest());
		url = url.substring(1,url.length());
		this.putData("menuUrl", url);
		AbstractMemberWidget  widget =SpringContextHolder.getBean(servletPath);
		
		//由子来决定是否显示菜单
		this.putData("showMenu", widget.getShowMenu());
		String subHtml = widget.process(params);
		
		
		if( "yes".equals(ThreadContextHolder.getHttpRequest().getParameter("ajax") )){
			return subHtml;
		}else{
			
			if(!widget.getShowMenu()) return subHtml;
			
			execute(subHtml);
			if(showHtml){
				 
				return freeMarkerPaser.proessPageContent();
			}
			else return "";
		}

	}

 

	 
	protected void execute(String mainHtml) {
		
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		if(member!=null){
			this.putData("member", member);
			if(member.getLv_id()!=null){
				MemberLv memberLv = memberLvManager.get(member.getLv_id());
				
				this.putData("memberLv", memberLv.getName());
			}else{
				this.putData("memberLv", "无等级");
			}
		}
		this.freeMarkerPaser.setClz(MemberMainWidget.class);
		this.freeMarkerPaser.setWrapPath(false);
		//因为上面子已经走过一遍，重至上freemarker配置
		this.setPageFolder(null);
		this.setPageFolder(null);
		this.setPageName(null);
		this.setPathPrefix(null);
		
		this.setPageExt(".html");
		this.setPageName("main");		
		this.putData("main", mainHtml);

		
	}
	public IMemberLvManager getMemberLvManager() {
		return memberLvManager;
	}
	public void setMemberLvManager(IMemberLvManager memberLvManager) {
		this.memberLvManager = memberLvManager;
	}
	
	protected void execute(Map<String, String> params) {
		// TODO Auto-generated method stub
		
	}
	
 
}

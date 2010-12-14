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
package com.enation.eop.impl.backend;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.Response;
import com.enation.eop.core.facade.AbstractFacadeProcessor;
import com.enation.eop.core.resource.IAdminThemeManager;
import com.enation.eop.core.resource.model.AdminTheme;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.model.FacadePage;
import com.enation.framework.context.spring.SpringContextHolder;

public class BackPageGetter extends AbstractFacadeProcessor {
	
	private IAdminThemeManager adminThemeManager;

	public BackPageGetter(FacadePage page) {
		super(page);
		// TODO Auto-generated constructor stub
	}

	
	protected Response process() {

		EopSite site = EopContext.getContext().getCurrentSite();//this.page.getSite();
		
		adminThemeManager = SpringContextHolder.getBean("adminThemeManager");
		
//		BackgroundThemeManager backgroundThemeManager = new BackgroundThemeManager();
		AdminTheme theme = adminThemeManager.get( site.getAdminthemeid());
//			backgroundThemeManager.getTheme(site.getUserid(),
//				site.getId());
		String path = theme.getPath();
		StringBuffer context = new StringBuffer();

		// context.append(EopSetting.IMG_SERVER_DOMAIN);
		String contextPath  = EopContext.getContext().getContextPath();
		context.append(contextPath);
		context.append(EopSetting.ADMINTHEMES_STORAGE_PATH);
		context.append("/");
		context.append(path);
		
		StringBuffer  staticdomain = new StringBuffer();
		//静态资源分离和静态资源合并模式
		if(EopSetting.RESOURCEMODE.equals("1")){
			staticdomain.append(EopSetting.IMG_SERVER_DOMAIN);
		}
		if(EopSetting.RESOURCEMODE.equals("2")){
			if("/".equals(EopSetting.CONTEXT_PATH) )
				staticdomain.append("");
			else	
				staticdomain.append(EopSetting.CONTEXT_PATH);
		}
		// 设置页面上变量的值
		httpRequest.setAttribute("context", staticdomain
				+ context.toString());
		
		httpRequest.setAttribute("title",site.getSitename());
		httpRequest.setAttribute("ico",site.getIcofile());
		httpRequest.setAttribute("logo", site.getLogofile()) ;
		httpRequest.setAttribute("version", EopSetting.VERSION) ;
		
		String uri = page.getUri();

		if (uri.startsWith("/admin/main")) {

			uri = context.toString() + "/main.jsp";
			request = new InstallerRecordJsWrapper(page, request);
		} else if (uri.equals("/admin/") || uri.equals("/admin/index.jsp")) {
			uri =  context.toString() + "/login.jsp";
		} else {
			
			if(EopSetting.EXTENSION.equals("action")){
				uri = uri.replace(".do", ".action");
			}
			
			String ajax = httpRequest.getParameter("ajax");
			if(!"yes".equals(ajax)){
				request = new BackTemplateWrapper(page, request);
			}
			 
		}

		Response response = request.execute(uri, httpResponse, httpRequest);
	 
		return response;

	}
}

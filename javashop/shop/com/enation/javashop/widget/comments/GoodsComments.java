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
package com.enation.javashop.widget.comments;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.setting.service.ISettingService;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.util.RequestUtil;
import com.enation.javashop.core.service.ICommentsManager;
import com.enation.javashop.widget.nav.Nav;

public class GoodsComments extends AbstractWidget {
	private ICommentsManager commentsManager;
	private ISettingService settingService;
	
	
	protected void config(Map<String, String> params) {
		this.setPageName("Comments_config");
		String commentType = params.get("commentType");
		String object_type = params.get("object_type");
		this.putData("commentType", commentType);
		this.putData("object_type", object_type);
	}
	
	private String commentType;
	private String object_type;

	
	protected void execute(Map<String, String> params) {
//		String commentType = params.get("commentType");
//		String object_type = params.get("object_type");
		Integer goods_id  = object_type.equals("leavewords")? 0 : this.getGoodsId();
		
		String pageSize = settingService.getSetting("comments", "pageSize");
		pageSize = pageSize == null ? "5" : pageSize;
		
		String directShow = settingService.getSetting("comments", "directShow");
		directShow = directShow == null ? "0" : directShow;
		
		String anonymous = settingService.getSetting("comments", "anonymous");
		anonymous = anonymous == null ? "0" : anonymous;
		
		String validcode = settingService.getSetting("comments", "validcode");
		validcode = validcode == null ? "0" : validcode;
		
		Page commentsPage  = commentsManager.pageComments_Display(1, Integer.valueOf(pageSize), goods_id, object_type, commentType);
		Long totalCount = commentsPage.getTotalCount();
		List commentsList  =  (List)commentsPage.getResult();
		commentsList = commentsList==null?new ArrayList():commentsList;
		
		this.putData("wid", object_type);
		this.putData("totalCount", totalCount);
		this.putData("commentType", commentType);
		this.putData("object_type", object_type);
		this.putData("commentsList", commentsList);
		this.putData("goods_id", goods_id);
		this.putData("pageSize", pageSize);
		this.putData("directShow", directShow);
		this.putData("anonymous", anonymous);
		this.putData("validcode", validcode);
		this.setPageName("Comments");
		//this.setPageFolder("/debug");
		
		if("shop".equals(commentType)){
			Nav nav = new Nav();
			nav.setTitle("客户留言");
			this.putNav(nav);
		}
		
	}
	
	
	private Integer getGoodsId(){
		HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
		String url = RequestUtil.getRequestUrl(httpRequest);
		
		if(url.startsWith("/widget")) return 0;
		
		String goods_id = this.paseGoodsId(url);
		
		return Integer.valueOf(goods_id);
	}

	private  static  String  paseGoodsId(String url){
		String pattern = "/(.*)-(\\d+).html(.*)";
		String value = null;
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);
		if (m.find()) {
			value = m.replaceAll("$2");
		}
		return value;
	}

	public void setCommentsManager(ICommentsManager commentsManager) {
		this.commentsManager = commentsManager;
	}

	public void setSettingService(ISettingService settingService) {
		this.settingService = settingService;
	}


	public String getCommentType() {
		return commentType;
	}


	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}


	public String getObject_type() {
		return object_type;
	}


	public void setObject_type(String objectType) {
		object_type = objectType;
	}
	
	
}

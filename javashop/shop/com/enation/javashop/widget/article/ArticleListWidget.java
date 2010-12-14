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
package com.enation.javashop.widget.article;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

 
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.util.RequestUtil;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.ArticleCat;
import com.enation.javashop.core.service.IArticleCatManager;
import com.enation.javashop.core.service.IArticleManager;
import com.enation.javashop.widget.nav.Nav;

/**
 * 文章列表
 * @author lzf<br/>
 * 2010-4-27下午01:20:24<br/>
 * version 1.0
 */
public class ArticleListWidget extends AbstractWidget {
	
	private IArticleManager articleManager;
	private IArticleCatManager articleCatManager; 
	
	protected void config(Map<String, String> params) {
		this.setPageName("articleList_config");

	}

	
	protected void execute(Map<String, String> params) {
		this.setPageName("articleList");
		HttpServletRequest request =  ThreadContextHolder.getHttpRequest();
		String page  = request.getParameter("page");
		page = (page == null || page.equals("")) ? "1" : page;
		int pageSize = 20;

		//文章显示数量，如果没有定义则使用默认的pageSize
		String showcount = params.get("showcount");
		pageSize = showcount== null? pageSize:Integer.valueOf(showcount);
		
		Integer cat_id;
		
		String catidstr= params.get("cat_id");
		if(StringUtil.isEmpty(catidstr)){
				cat_id = this.getArticleCatId();
		}else{
			cat_id = Integer.valueOf(catidstr);
		}
		

		Page articlePage = articleManager.pageByCatId(Integer.valueOf(page), pageSize, cat_id);
		Long pageCount = articlePage.getTotalPageCount();
		List articleList = (List)articlePage.getResult();
		articleList = articleList == null ? new ArrayList() : articleList;
		String showDate = params.get("showdate");
		showDate = showDate == null ? "1" : showDate;
		String showPage = params.get("showpage");
		showPage = showPage == null ? "1" : showPage;
		String showMore = params.get("showmore");
		showMore = showMore == null ? "0" : showMore;
		String urlName = params.get("urlname");		//	lzy add 可以自定义名称
		urlName = urlName == null ? "article-" : urlName;
		this.putData("showPage", showPage);
		this.putData("showDate", showDate);
		this.putData("pageSize", pageSize);
		this.putData("page", page);
		this.putData("articleList", articleList);
		this.putData("pageCount", pageCount);
		this.putData("cat_id", cat_id);
		this.putData("showMore", showMore);
		this.putData("urlname", urlName);

		ArticleCat cat  = this.articleCatManager.getById(cat_id);
		Nav nav = new Nav();
		nav.setTitle("首页");
		nav.setLink("index.html");
		nav.setTips("首页");
		this.putNav(nav);
		
		Nav nav1 = new Nav();
		nav1.setTitle(cat.getName() );
		nav1.setTips(cat.getName());
		this.putNav(nav1);			
		 
	}
	
	private Integer getArticleCatId(){
		HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
		String url = RequestUtil.getRequestUrl(httpRequest);
		
		if(url.startsWith("/widget")) return 0;
		
		String article_id = this.paseArticleCatId(url);
		
		return Integer.valueOf(article_id);
	}

	private  static  String  paseArticleCatId(String url){
		String pattern = "/(.*)-(\\d+).html(.*)";
		String value = null;
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);
		if (m.find()) {
			value = m.replaceAll("$2");
		}
		return value;
	}

	public IArticleManager getArticleManager() {
		return articleManager;
	}

	public void setArticleManager(IArticleManager articleManager) {
		this.articleManager = articleManager;
	}

	public IArticleCatManager getArticleCatManager() {
		return articleCatManager;
	}

	public void setArticleCatManager(IArticleCatManager articleCatManager) {
		this.articleCatManager = articleCatManager;
	}

}

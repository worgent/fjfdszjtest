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
package com.enation.javashop.widget.goods.detail;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.eop.sdk.widget.IWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.RequestUtil;
import com.enation.javashop.core.plugin.GoodsPluginBundle;
import com.enation.javashop.core.service.IGoodsManager;
import com.enation.javashop.widget.header.HeaderConstants;
import com.enation.javashop.widget.nav.Nav;

/**
 * 商品详细挂件</br>
 * @author kingapex
 */
public class GoodsDetailWidget extends AbstractWidget {
	 

	private IGoodsManager goodsManager;
	 
	private Map<String,IWidget> viewWidgets;
	
	
	protected void  execute(Map<String, String> params) {
		
		 //this.setPageFolder("/widgets/goods");
		 Integer goods_id=this.getGoodsId();
		 Map goodsMap=  goodsManager.get(goods_id);
		 ThreadContextHolder.getHttpRequest().setAttribute("goods", goodsMap);
		 
		 if(goodsMap.get("page_title")!=null &&!goodsMap.get("page_title").equals("") )
			this.putData(HeaderConstants.title, goodsMap.get("page_title"));
		 else
			 this.putData(HeaderConstants.title, goodsMap.get("name") );
		 
		 if(goodsMap.get("meta_keywords")!=null &&!goodsMap.get("meta_keywords").equals("")  )
			this.putData(HeaderConstants.keywords,goodsMap.get("meta_keywords"));
		 
		 if(goodsMap.get("meta_description")!=null &&!goodsMap.get("meta_description").equals("") )
			this.putData(HeaderConstants.description,goodsMap.get("meta_description"));
		 
		 if(viewWidgets!=null){
			 Iterator<String> widgeNames = viewWidgets.keySet().iterator();
			 while(widgeNames.hasNext()){
				 String name = widgeNames.next();
				 IWidget viewWidget = viewWidgets.get(name);
				 String html =viewWidget.process(params);
				 this.putData(name, html);
			 }
		 }
		 this.setPageName(null);
		 this.freeMarkerPaser.setClz(this.getClass());
		 
		 
			Nav nav = new Nav();
			nav.setTitle("首页");
			nav.setLink("index.html");
			nav.setTips("首页");
			this.putNav(nav);
			
			Nav nav1 = new Nav();
			nav1.setTitle((String)goodsMap.get("name") );
			nav1.setTips((String)goodsMap.get("name"));
			this.putNav(nav1);
		 
	}
	
	private Integer getGoodsId(){
		HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
		String url = RequestUtil.getRequestUrl(httpRequest);
		String goods_id = this.paseGoodsId(url);
		
		return Integer.valueOf(goods_id);
	}
	
	
	protected void config(Map<String, String> params) {
		this.showHtml=false;
	}


	private  static String  paseGoodsId(String url){
		String pattern = "/goods-(\\d+).html(.*)";
		String value = null;
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);
		if (m.find()) {
			value = m.replaceAll("$1");
		}
		return value;
	}
	
	public static void main(String[] args){
		System.out.println(  paseGoodsId("/goods-13.html"));
		
	}

	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

	public Map<String, IWidget> getViewWidgets() {
		return viewWidgets;
	}

	public void setViewWidgets(Map<String, IWidget> viewWidgets) {
		this.viewWidgets = viewWidgets;
	}

	
}

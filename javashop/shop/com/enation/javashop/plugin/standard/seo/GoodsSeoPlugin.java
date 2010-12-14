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
package com.enation.javashop.plugin.standard.seo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.core.view.freemarker.FreeMarkerPaser;
import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.plugin.AbstractGoodsPlugin;


/**
 * 商品SEO优化插件
 * @author enation
 *
 */
public class GoodsSeoPlugin extends AbstractGoodsPlugin{
	
 
	
	public void addTabs(){
		 this.addTags(3, "搜索优化");
	}
	
	public void registerPages(){	 
		//this.registerPage("goods_add.tabcontent", "/plugin/seo/seo.jsp?ajax=yes");
	}

 

	public void onBeforeGoodsAdd(Map goods, HttpServletRequest request) {

	}
 
	
	public String onFillGoodsAddInput(HttpServletRequest request) {
		FreeMarkerPaser freeMarkerPaser =FreeMarkerPaser.getInstance();
		freeMarkerPaser.setPageName("seo");
		return freeMarkerPaser.proessPageContent();
	}

	public String onFillGoodsEditInput(Map goods, HttpServletRequest request) {
		FreeMarkerPaser freeMarkerPaser = new FreeMarkerPaser(getClass());
		freeMarkerPaser.setPageName("seo");
		freeMarkerPaser.putData("goods", goods);
		return freeMarkerPaser.proessPageContent();
	}

	public void onAfterGoodsAdd(Goods goods, HttpServletRequest request) throws RuntimeException {
		
		
	}

	
	public void onAfterGoodsAdd(Map goods, HttpServletRequest request)  {
		
		
	}

	public void onBeforeGoodsEdit(Map goods, HttpServletRequest request)  {
		
		
	}
	
	

	public void onAfterGoodsEdit(Map goods, HttpServletRequest request)  {
		
		
	}

	public String getAuthor() {
		
		return "kingapex";
	}

	public String getId() {
		
		return "goodsseo";
	}

	public String getName() {
		 
		return "商品SEO优化插件";
	}

	public String getType() {
		
		return "";
	}

	public String getVersion() {
		
		return "1.0";
	}

	public void perform(Object... params) {
		
		
	}
	
}

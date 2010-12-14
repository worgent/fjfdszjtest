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
package com.enation.javashop.plugin.standard.album;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.core.view.freemarker.FreeMarkerPaser;
import com.enation.javashop.core.plugin.AbstractGoodsPlugin;
import com.enation.javashop.core.plugin.JspPageTabs;


/**
 * 默认商品相册插件
 * @author enation
 *
 */
public class DefaultAlbumPlugin extends AbstractGoodsPlugin{
	
 
	public void addTabs(){
		 JspPageTabs.addTab("setting",1, "相册设置");
	}
	
 
	public void onBeforeGoodsAdd(Map goods, HttpServletRequest request) {
		String[] picnames =  request.getParameterValues("picnames");
		proessPhoto(picnames,goods); 
	}

	public void onBeforeGoodsEdit(Map goods, HttpServletRequest request)  {
		String[] picnames =  request.getParameterValues("picnames");
		proessPhoto(picnames,goods); 
		
	}
		
 
	
	/**
	 * 在修改时处理图片的信息
	 */
	public String onFillGoodsEditInput(Map goods, HttpServletRequest request) {
		// 关于图片的处理
 		String first_image= null;
		if (goods.get("image_file") != null && goods.get("image_file")!="") {
			String image_file = goods.get("image_file").toString();
			String[] thumbnail_images = image_file.split(",");

			if (thumbnail_images != null && thumbnail_images.length > 0) {
				first_image = thumbnail_images[0];
			} else {
				first_image = "";
			}
			String contextPath = request.getContextPath();
			//设置需要传递到页面的数据	
			FreeMarkerPaser freeMarkerPaser =FreeMarkerPaser.getInstance();
		 
			freeMarkerPaser.setPageName("album");
			freeMarkerPaser.putData("ctx", contextPath);
			freeMarkerPaser.putData("first_image", first_image );
			freeMarkerPaser.putData("thumbnail_images", thumbnail_images );	
			String html = freeMarkerPaser.proessPageContent();
			return html;
		}
		
		
		return null;
	}

	public String onFillGoodsAddInput(HttpServletRequest request) {
		FreeMarkerPaser freeMarkerPaser =FreeMarkerPaser.getInstance();
		freeMarkerPaser.setPageName("album");		
		freeMarkerPaser.putData("first_image", null );
		freeMarkerPaser.putData("thumbnail_images", null );	
		String html = freeMarkerPaser.proessPageContent();
		return html;
	}
	

	public void onAfterGoodsAdd(Map goods, HttpServletRequest request)  {
		
	}
	

	public void onAfterGoodsEdit(Map goods, HttpServletRequest request)  {
		
	}

	protected void proessPhoto(String[] picnames,Map goods) {
		
	}
	



	public String getAuthor() {
		// TODO Auto-generated method stub
		return "kingapex";
	}

	public String getId() {
		// TODO Auto-generated method stub
		return "default_album";
	}

	public String getName() {
		 
		return "默认商品相册插件";
	}

	public String getType() {
		// TODO Auto-generated method stub
		return "";
	}

	public String getVersion() {
		// TODO Auto-generated method stub
		return "1.0";
	}

	public void perform(Object... params) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}

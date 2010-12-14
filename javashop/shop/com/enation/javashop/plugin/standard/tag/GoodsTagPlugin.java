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
package com.enation.javashop.plugin.standard.tag;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.core.view.freemarker.FreeMarkerPaser;
import com.enation.javashop.core.model.Tag;
import com.enation.javashop.core.plugin.AbstractGoodsPlugin;
import com.enation.javashop.core.service.ITagManager;

/**
 * 商品标签插件
 * @author kingapex
 * 2010-1-18下午02:56:43
 */
public class GoodsTagPlugin extends AbstractGoodsPlugin {
	private ITagManager tagManager;
	
	
	public void addTabs() {
 
	}

	
 

	
	
	/*为添加商品和修改商品页面填充必要的数据*/
	
	
	public String onFillGoodsAddInput(HttpServletRequest request) {
		List<Tag> taglist  = this.tagManager.list();
		FreeMarkerPaser freeMarkerPaser = new FreeMarkerPaser(getClass());
		freeMarkerPaser.setPageName("tag");
		freeMarkerPaser.putData("tagList", taglist);
		return freeMarkerPaser.proessPageContent();
	}
	
	
	
	public String onFillGoodsEditInput(Map goods, HttpServletRequest request) {
		List<Tag> taglist  = this.tagManager.list();
	 
		Integer goods_id =  Integer.valueOf(goods.get("goods_id").toString());
		List<Integer> tagIds=this.tagManager.list(goods_id);
	 
		
		FreeMarkerPaser freeMarkerPaser = new FreeMarkerPaser(getClass());
		freeMarkerPaser.setPageName("tag");
		freeMarkerPaser.putData("tagList", taglist);	
		freeMarkerPaser.putData("tagRelList", tagIds);
		return freeMarkerPaser.proessPageContent();
	}

	
	
	
	/*在保存添加和保存更新的时候，将tagid的数组和goodsid对应起来保存在库里*/
	
	
	
	public void onAfterGoodsAdd(Map goods, HttpServletRequest request)
			throws RuntimeException {
		this.save(goods, request);

	}

	
	public void onAfterGoodsEdit(Map goods, HttpServletRequest request)
			 {
		this.save(goods, request);

	}

	private void save(Map goods, HttpServletRequest request){
		Integer goods_id =  Integer.valueOf(goods.get("goods_id").toString());
		
		String[] tagstr=  request.getParameterValues("tag_id");
		if(tagstr!=null){
			Integer[] tagids=new Integer[tagstr.length];
			for(int i=0;i<tagstr.length;i++){
				tagids[i]=	Integer.valueOf(tagstr[i]) ;
			}
			this.tagManager.saveRels(goods_id, tagids);
		}
	}
	
	
	
	
	
	
	

	
	public void onBeforeGoodsEdit(Map goods, HttpServletRequest request)
 {
		 

	}

	
	public void onBeforeGoodsAdd(Map goods, HttpServletRequest request)
			 {
		 
		 
	}

	
	
	public String getAuthor() {
		// TODO Auto-generated method stub
		return "kingapex";
	}

	
	public String getId() {
		return "goodstag";
	}

	
	public String getName() {
		return "商品标签";
	}

	
	public String getType() {
		return null;
	}

	
	public String getVersion() {
		return "1.0";
	}

	
	public void perform(Object... params) {

	}


	public void setTagManager(ITagManager tagManager) {
		this.tagManager = tagManager;
	}

}

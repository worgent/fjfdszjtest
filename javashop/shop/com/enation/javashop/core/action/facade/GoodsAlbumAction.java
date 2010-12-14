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
package com.enation.javashop.core.action.facade;

import com.enation.eop.utils.UploadUtil;
import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.service.IGoodsManager;
import com.enation.javashop.core.utils.GoodsUtils;

/**
 * 商品相册action
 * 
 * @author kingapex 2010-4-27下午04:03:14
 */
public class GoodsAlbumAction extends WWAction {
	private Integer goodsid;
	private IGoodsManager goodsManager;
	private Goods goods;
	private StringBuffer albumXml;
	public String execute() {
		
		goods = goodsManager.getGoods(goodsid);
		
		String image_file = goods.getImage_file()==null?"":goods.getImage_file().toString();
		 albumXml =new StringBuffer("<products name='"+goods.getName()+"' shopname='javashop'>");
		if(image_file!=null && !image_file.equals("")){
		
			String[] imgs= image_file.split(",");
			int i=0;
			for(String img : imgs){
				albumXml.append("<smallpic");
				if(i==0)
					albumXml.append(" selected='selected'");
				albumXml.append(">");
				albumXml.append(UploadUtil.getThumbPath(img, "_small"));
				albumXml.append("</smallpic>");
				
				
				albumXml.append("<bigpic ");
				if(i==0)
					albumXml.append(" selected='selected'");
				albumXml.append(">");
				albumXml.append(UploadUtil.getThumbPath(img, "_big")  +"</bigpic>");
				albumXml.append("<link>goods-"+goods.getGoods_id()+".html</link>");
				i++;
			}
			
		}
		albumXml.append("</products>");
		
		 
		return this.SUCCESS;
	}

	public Integer getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}

	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public StringBuffer getAlbumXml() {
		return albumXml;
	}

	public void setAlbumXml(StringBuffer albumXml) {
		this.albumXml = albumXml;
	}

}

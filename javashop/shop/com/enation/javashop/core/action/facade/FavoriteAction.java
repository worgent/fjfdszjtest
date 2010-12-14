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

import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.action.WWAction;
import com.enation.javashop.core.service.IFavoriteManager;

/**
 * 商品收藏action
 * @author kingapex
 *2010-4-27下午05:42:45
 */
public class FavoriteAction extends WWAction {
	
	private IFavoriteManager favoriteManager;
	private Integer goodsid;
	
	public String execute(){
		Member member  = UserServiceFactory.getUserService().getCurrentMember();
		if(member!=null){
			this.favoriteManager.add(goodsid);
			this.json="{result:1,message:'收藏成功'}";
		}else{
			this.json="{result:0,message:'您尚未登陆，不能使用收藏功能'}";
		}
		return this.JSON_MESSAGE;
	}
	public IFavoriteManager getFavoriteManager() {
		return favoriteManager;
	}
	public void setFavoriteManager(IFavoriteManager favoriteManager) {
		this.favoriteManager = favoriteManager;
	}
	public Integer getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}
	
	
}

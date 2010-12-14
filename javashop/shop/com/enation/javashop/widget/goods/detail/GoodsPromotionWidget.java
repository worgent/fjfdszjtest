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

import java.util.List;
import java.util.Map;

import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.javashop.core.model.Promotion;
import com.enation.javashop.core.service.IPromotionManager;
import com.enation.javashop.widget.goods.AbstractGoodsDetailWidget;

/***
 * 商品优惠规则挂件
 * @author kingapex
 *2010-4-19上午10:01:44
 */
public class GoodsPromotionWidget extends AbstractGoodsDetailWidget {
	private IPromotionManager promotionManager;
	
	protected void execute(Map<String, String> params, Map goods) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		if(member==null){
			this.showHtml =false;return ;
		}
		
		//读取此会员级别可享有的这个商品的促销规则
		Integer goodsid =(Integer)goods.get("goods_id");
		List<Promotion> pmtList  =   this.promotionManager.list(goodsid, member.getLv_id());
		this.putData("pmtList", pmtList);
		
	}

	
	protected void config(Map<String, String> params) {
		
	}

	public IPromotionManager getPromotionManager() {
		return promotionManager;
	}

	public void setPromotionManager(IPromotionManager promotionManager) {
		this.promotionManager = promotionManager;
	}

}

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
package com.enation.javashop.plugin.promotion;

import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.javashop.core.plugin.IPromotionPlugin;
import com.enation.javashop.core.service.promotion.PromotionConditions;
import com.enation.javashop.core.service.promotion.PromotionType;

public class GoodsDiscountPlugin extends AutoRegisterPlugin implements IPromotionPlugin {

	
	public String[] getConditions() {
		return new String[]{ PromotionConditions.GOODS ,PromotionConditions.MEMBERLV};
	}

	
	public String getMethods() {
		return "discount";
	}

	
	public String getAuthor() {
		return "kingapex";
	}

	
	public String getId() {
		return "goodsDiscountPlugin";
	}

	
	public String getName() {
		return "商品直接打折，如全场女鞋8折。可以对商品任意折扣，适合低价清货促销";
	}

	
	public String getType() {
		return PromotionType.PMTTYPE_GOODS;
	}

	
	public String getVersion() {
		return "1.0";
	}

	
	public void perform(Object... params) {

	}

	
	public void register() {
		
	}

}

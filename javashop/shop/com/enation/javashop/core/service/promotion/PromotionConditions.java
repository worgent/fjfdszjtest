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
package com.enation.javashop.core.service.promotion;
/**
 * 优惠规则条件
 * @author kingapex
 *2010-4-19下午04:12:47
 */
public class PromotionConditions {
	
	public static final String ORDER ="order";
	public static final String MEMBERLV ="memberLv";
	public static final String GOODS = "goods";
	private boolean  hasOrder=false;
	private boolean  hasMemberLv=false;
	private boolean hasGoods=false;
	
	public PromotionConditions(String[] conditions){
		if(conditions!=null){
			for(String  cond : conditions){
				if(ORDER.equals(cond)){
					this.hasOrder= true;
				}
				
				if(MEMBERLV.equals(cond)){
					this.hasMemberLv =true;
				}
				
				if(GOODS.equals(cond)){
					this.hasGoods =true;
				}
			}
		} 
	}
	
	public boolean getHasOrder(){
		return this.hasOrder;
	}


	public boolean getHasMemberLv() {
		return hasMemberLv;
	}


	public void setHasMemberLv(boolean hasMemberLv) {
		this.hasMemberLv = hasMemberLv;
	}


	public void setHasOrder(boolean hasOrder) {
		this.hasOrder = hasOrder;
	}


	public boolean getHasGoods() {
		return hasGoods;
	}


	public void setHasGoods(boolean hasGoods) {
		this.hasGoods = hasGoods;
	}
	
	
}

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
package com.enation.javashop.widget.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.service.GoodsPicDirectiveModel;
import com.enation.javashop.core.service.ICartManager;

/**
 * 会员信息面板挂件
 * @author kingapex
 *2010-5-4上午10:31:20
 */
public class MemberCartBarWidget extends AbstractWidget {

	private ICartManager cartManager;
	
	protected void config(Map<String, String> params) {

	}

	
	protected void execute(Map<String, String> params) {
		
		
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String sessionid =request.getSession().getId();
		
		String ajax = request.getParameter("ajax");
		
		if(("yes").equals(ajax )){
			List goodsList  = cartManager.listGoods( sessionid );
			List pgkList = cartManager.listPgk(sessionid);
			List giftList  = cartManager.listGift(sessionid);
			
			int goodsCount = goodsList== null?0:goodsList.size();
			int pgkCount = pgkList==null?0:pgkList.size();
			int giftCount = giftList==null?0:giftList.size();
			
			Double goodsTotal  =cartManager.countGoodsTotal( sessionid );
			Double pgkTotal = cartManager.countPgkTotal(sessionid);
								  
			this.putData("goodsList", goodsList);
			this.putData("pgkList", pgkList);
			this.putData("giftList", giftList);
			
			this.putData("count", goodsCount+ giftCount+pgkCount);
			this.putData("goodsCount", goodsCount);
			this.putData("pgkCount", pgkCount);
			this.putData("giftCount", giftCount);
			this.putData("total", pgkTotal+goodsTotal);
			this.putData("GoodsPic",new  GoodsPicDirectiveModel());
			this.setPageName("bar_Ajax");
			
		}else{
			int count = this.cartManager.countItemNum(sessionid);
			this.putData("count", count);
		}
	}

	public ICartManager getCartManager() {
		return cartManager;
	}

	public void setCartManager(ICartManager cartManager) {
		this.cartManager = cartManager;
	}
	
	

}

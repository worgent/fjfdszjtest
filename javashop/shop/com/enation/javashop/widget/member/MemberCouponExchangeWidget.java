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

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.javashop.core.service.IMemberCouponsManager;

public class MemberCouponExchangeWidget extends AbstractMemberWidget {

	private IMemberCouponsManager memberCouponsManager;
	
	
	protected void config(Map<String, String> params) {
		// TODO Auto-generated method stub

	}

	
	protected void execute(Map<String, String> params) {
		
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String action = request.getParameter("action");
		action = action == null ? "" : action;
		if(action.equals("")){
		this.setPageName("member_couponExchange");
		String page = request.getParameter("page");
		page = (page == null || page.equals("")) ? "1" : page;
		int pageSize = 20;
		Page pageExchangeCoupons = memberCouponsManager.pageExchangeCoupons(Integer.valueOf(page), pageSize);
		Long pageCount = pageExchangeCoupons.getTotalPageCount();
		List listExchangeCoupons = (List)pageExchangeCoupons.getResult();
		this.putData("listExchangeCoupons", listExchangeCoupons);
		this.putData("pageSize", pageSize);
		this.putData("pageCount", pageCount);
		this.putData("page", page);
		}else if(action.equals("exchange")){
			String cpns_id = request.getParameter("cpns_id");
			try{
				memberCouponsManager.exchange(Integer.valueOf(cpns_id));
				this.showSuccess("兑换成功","我的优惠券", "member_coupons.html");
			}catch(Exception e){
				if(this.logger.isDebugEnabled()){
					logger.error(e.getStackTrace());
				}
				this.showError("兑换失败", "我的优惠券", "member_coupons.html");
			}
		}

	}

	public IMemberCouponsManager getMemberCouponsManager() {
		return memberCouponsManager;
	}

	public void setMemberCouponsManager(IMemberCouponsManager memberCouponsManager) {
		this.memberCouponsManager = memberCouponsManager;
	}

}

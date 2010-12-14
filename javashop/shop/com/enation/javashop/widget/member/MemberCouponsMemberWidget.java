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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.setting.service.ISettingService;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.javashop.core.service.IMemberCouponsManager;

public class MemberCouponsMemberWidget extends AbstractMemberWidget {
	
	private ISettingService settingService;
	private IMemberCouponsManager memberCouponsManager;

	
	protected void config(Map<String, String> params) {
		// TODO Auto-generated method stub

	}

	
	protected void execute(Map<String, String> params) {
		this.setPageName("member_coupons");
		String mc_use_times = settingService.getSetting("coupons", "coupon.mc.use_times");
		mc_use_times = mc_use_times == null ? "1" : mc_use_times;
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String page = request.getParameter("page");
		page = (page == null || page.equals("")) ? "1" : page;
		int pageSize = 20;
		Page pageMemberCoupons = memberCouponsManager.pageMemberCoupons(Integer.valueOf(page), pageSize);
		Long pageCount = pageMemberCoupons.getTotalPageCount();
		List listMemberCoupons = (List)pageMemberCoupons.getResult();
		listMemberCoupons = listMemberCoupons == null ? new ArrayList() : listMemberCoupons;
		this.putData("listMemberCoupons", listMemberCoupons);
		this.putData("mc_use_times", mc_use_times);
		this.putData("pageSize", pageSize);
		this.putData("pageCount", pageCount);
		this.putData("page", page);
	}

	public ISettingService getSettingService() {
		return settingService;
	}

	public void setSettingService(ISettingService settingService) {
		this.settingService = settingService;
	}

	public IMemberCouponsManager getMemberCouponsManager() {
		return memberCouponsManager;
	}

	public void setMemberCouponsManager(IMemberCouponsManager memberCouponsManager) {
		this.memberCouponsManager = memberCouponsManager;
	}

}

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
package com.enation.javashop.widget.gift;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.model.MemberLv;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.FreeOffer;
import com.enation.javashop.core.model.FreeOfferCategory;
import com.enation.javashop.core.service.IFreeOfferCategoryManager;
import com.enation.javashop.core.service.IFreeOfferManager;
import com.enation.javashop.core.service.IMemberLvManager;
import com.enation.javashop.widget.header.HeaderConstants;

/**
 * 赠品列表挂件
 * @author kingapex
 *2010-5-4下午04:54:30
 */
public class GiftWidget extends AbstractWidget {

	private IFreeOfferManager freeOfferManager;
	private IFreeOfferCategoryManager freeOfferCategoryManager;
	private IMemberLvManager memberLvManager;
	
	private HttpServletRequest request;
	
	protected void config(Map<String, String> params) {

	}

	
	protected void execute(Map<String, String> params) {
		request =ThreadContextHolder.getHttpRequest();
		String showtype= params.get("showtype");
		if("list".equals(showtype)){
			this.list();
		}
		
		if("detail".equals(showtype)){
			this.detail();
		}
	}

	
	private void detail(){
		String giftid  = this.paseGiftId( request.getServletPath());
		FreeOffer gift  =freeOfferManager.get( Integer.valueOf(giftid));
		FreeOfferCategory cat =this.freeOfferCategoryManager.get(gift.getFo_category_id());
		gift.setCat_name(cat.getCat_name());
		List lvList  = this.memberLvManager.list(gift.getLv_ids());
		this.putData("lvList", lvList) ;
		this.putData("gift", gift);
		this.setPageName("GiftDetail");
		this.putData(HeaderConstants.title,gift.getFo_name());
	}
	
	
	private void list(){
		Page page  =freeOfferManager.list(1, 20);
		this.putData("webpage", page);
		this.setPageName("GiftList");
	}
	
	
	private    String  paseGiftId(String url){
		String pattern = "/gift-(\\d+).html";
		String value = null;
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);
		if (m.find()) {
			value = m.replaceAll("$1");
		}
		return value;
	}
	
	public IFreeOfferManager getFreeOfferManager() {
		return freeOfferManager;
	}

	public void setFreeOfferManager(IFreeOfferManager freeOfferManager) {
		this.freeOfferManager = freeOfferManager;
	}

	public IFreeOfferCategoryManager getFreeOfferCategoryManager() {
		return freeOfferCategoryManager;
	}

	public void setFreeOfferCategoryManager(
			IFreeOfferCategoryManager freeOfferCategoryManager) {
		this.freeOfferCategoryManager = freeOfferCategoryManager;
	}

	public IMemberLvManager getMemberLvManager() {
		return memberLvManager;
	}

	public void setMemberLvManager(IMemberLvManager memberLvManager) {
		this.memberLvManager = memberLvManager;
	}

}

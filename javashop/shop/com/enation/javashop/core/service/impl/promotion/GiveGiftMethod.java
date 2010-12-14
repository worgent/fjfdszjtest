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
package com.enation.javashop.core.service.impl.promotion;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;

import com.enation.eop.core.view.freemarker.FreeMarkerPaser;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.FreeOffer;
import com.enation.javashop.core.model.Promotion;
import com.enation.javashop.core.service.IFreeOfferManager;
import com.enation.javashop.core.service.promotion.IGiveGiftBehavior;
import com.enation.javashop.core.service.promotion.IPromotionMethod;

/**
 * 优惠方式--送赠品
 * @author kingapex
 *2010-4-15下午04:59:08
 */
public class GiveGiftMethod extends BaseSupport<FreeOffer> implements IPromotionMethod, IGiveGiftBehavior {
	
	private IFreeOfferManager freeOfferManager;
	
	public String getInputHtml(Integer pmtid, String solution) {
		FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();
		freeMarkerPaser.setClz(this.getClass());
		if(solution!=null){
			Object[] giftIdArray=   JSONArray.fromObject(solution).toArray();
			if(giftIdArray!=null){
				Integer[] giftIds = new Integer[giftIdArray.length];
				int i=0;
				for(Object giftId: giftIdArray){
					giftIds[i] =  Integer.valueOf(giftId.toString());
					i++;
				}
				List giftList  = freeOfferManager.list(giftIds);
				freeMarkerPaser.putData("giftList", giftList);
			}
				
		}

		return freeMarkerPaser.proessPageContent();
	}

	
	public String getName() {
		
		return "giveGift";
	}

	
	public String onPromotionSave(Integer pmtid) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String[] giveGift = request.getParameterValues("giftidArray");
		return JSONArray.fromObject(giveGift).toString();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	
	public void giveGift(Promotion promotion,Integer orderId) {
		List<Map> giftList  = this.getGiftList(promotion);
		
		for(Map gift:giftList){
			this.baseDaoSupport.execute("insert into order_gift(order_id,gift_id,gift_name,point,num,shipnum,getmethod)values(?,?,?,?,?,?,?)", 
								orderId,gift.get("fo_id"),gift.get("fo_name"),gift.get("score"),1,0,"present");
		}
	 
	}

	
	public List getGiftList(Promotion promotion) {
		String solution =  promotion.getPmt_solution();
		if(solution== null || "".equals(solution)) return null;
		
		JSONArray jsonArray = JSONArray.fromObject( solution);  
	 	Object[] giftIdArray = jsonArray.toArray();
	 	
	 	String sql ="select * from freeoffer where fo_id in("+ StringUtil.arrayToString(giftIdArray, ",") +") ";
		return this.baseDaoSupport.queryForList(sql);
	}

	public IFreeOfferManager getFreeOfferManager() {
		return freeOfferManager;
	}

	public void setFreeOfferManager(IFreeOfferManager freeOfferManager) {
		this.freeOfferManager = freeOfferManager;
	}
	
	
}

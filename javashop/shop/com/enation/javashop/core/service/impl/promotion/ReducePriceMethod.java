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

import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.core.view.freemarker.FreeMarkerPaser;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.Promotion;
import com.enation.javashop.core.service.promotion.IPromotionMethod;
import com.enation.javashop.core.service.promotion.IReducePriceBehavior;

/**
 * 减价优惠方式
 * @author kingapex
 *2010-4-19下午04:03:42
 */
public class ReducePriceMethod implements IPromotionMethod,
		IReducePriceBehavior {

	
	public String getInputHtml(Integer pmtid, String solution) {
		FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();
		freeMarkerPaser.setClz(this.getClass());
		freeMarkerPaser.putData("lessMoney", solution);
		return freeMarkerPaser.proessPageContent();
	}

	
	public String getName() {
		return "reducePrice";
	}

	
	public String onPromotionSave(Integer pmtid) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String reducePrice = request.getParameter("lessMoney");
		return reducePrice==null?"":reducePrice;
	}

	
	public Double reducedPrice(Promotion pmt,Double price) {
		String solution = pmt.getPmt_solution();
		Double  lessMoney =  Double.valueOf(solution);
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		return Double.valueOf(nf.format( price - lessMoney ));
	}

}

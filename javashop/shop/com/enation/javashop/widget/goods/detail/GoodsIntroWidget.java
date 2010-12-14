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

import java.util.Map;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.support.ParamGroup;
import com.enation.javashop.core.service.IGoodsTypeManager;
import com.enation.javashop.plugin.standard.type.GoodsTypeUtil;

/**
 * 商品详细信息挂件
 * @author kingapex
 *
 */
public class GoodsIntroWidget extends AbstractWidget {
 
	
	protected void config(Map<String, String> params) {
		// TODO Auto-generated method stub

	}

	
	protected void execute(Map<String, String> params) {
		
		Map goods  = (Map)ThreadContextHolder.getHttpRequest().getAttribute("goods");
		
		if(goods==null) throw new RuntimeException("参数显示挂件必须和商品详细显示挂件同时存在");
		String intro =(String)goods.get("intro");
		intro =intro==null?"":intro;
		this.putData("intro", intro);
		this.setPageName("intro");
	}

 
	

}

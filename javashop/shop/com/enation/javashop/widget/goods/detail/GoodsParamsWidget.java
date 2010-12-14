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

import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.support.ParamGroup;
import com.enation.javashop.plugin.standard.type.GoodsTypeUtil;
import com.enation.javashop.widget.goods.AbstractGoodsDetailWidget;

/**
 * 商品参数挂件<br/>
 * 读取商品的参数供页面显示<br/>
 * 并设置hasParam变量以在页面中判断是否显示"详细参数"选择卡
 * @author kingapex
 *
 */
public class GoodsParamsWidget extends AbstractGoodsDetailWidget {
 
	
	protected void config(Map<String, String> params) {
		// TODO Auto-generated method stub

	}

	
	protected void execute(Map<String, String> params,Map goods) {
 
		String goodParams  =(String)goods.get("params");
		if(goodParams!=null && !goodParams.equals("")){
			ParamGroup[] paramList =GoodsTypeUtil.converFormString(goodParams);
			this.putData("paramList", paramList);
			
			if(paramList!=null && paramList.length>0)
			this.putData("hasParam", true);
			else
				this.putData("hasParam", false);
		}else{
			this.putData("hasParam", false);
		}

		this.setPageName("params");
	}

 
	

}

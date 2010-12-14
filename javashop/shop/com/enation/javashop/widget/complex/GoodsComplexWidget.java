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
package com.enation.javashop.widget.complex;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.RequestUtil;
import com.enation.javashop.core.service.GoodsPicDirectiveModel;
import com.enation.javashop.core.service.IGoodsComplexManager;

/**
 * 相关商品挂件
 * 
 * @author lzf<br/>
 *         2010-4-1 下午01:52:17<br/>
 *         version 1.0<br/>
 */
public class GoodsComplexWidget extends AbstractWidget {
	
	private IGoodsComplexManager goodsComplexManager;

	
	protected void config(Map<String, String> params) {
		// TODO Auto-generated method stub

	}

	
	protected void execute(Map<String, String> params) {
		// TODO Auto-generated method stub
		Integer goods_id  = this.getGoodsId();
		List complexList = goodsComplexManager.listGoodsComplex(goods_id);
		boolean hasComplex = complexList==null ||complexList.isEmpty() ?false:true;
		this.putData("hasComplex", hasComplex);
		this.putData("complexList", complexList);
		this.putData("GoodsPic", new GoodsPicDirectiveModel());
		this.setPageName("complex");
	}
	
	private Integer getGoodsId(){
		HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
		String url = RequestUtil.getRequestUrl(httpRequest);
		
		if(url.startsWith("/widget")) return 0;
		
		String goods_id = this.paseGoodsId(url);
		
		return Integer.valueOf(goods_id);
	}

	private  static  String  paseGoodsId(String url){
		String pattern = "/(.*)-(\\d+).html(.*)";
		String value = null;
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);
		if (m.find()) {
			value = m.replaceAll("$2");
		}
		return value;
	}

	public IGoodsComplexManager getGoodsComplexManager() {
		return goodsComplexManager;
	}

	public void setGoodsComplexManager(IGoodsComplexManager goodsComplexManager) {
		this.goodsComplexManager = goodsComplexManager;
	}

}

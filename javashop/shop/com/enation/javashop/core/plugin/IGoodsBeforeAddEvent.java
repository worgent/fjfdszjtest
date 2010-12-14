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
package com.enation.javashop.core.plugin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品添加前事件
 * 
 * @author kingapex
 * 
 */
public interface IGoodsBeforeAddEvent {

	/**
	 * 在商品数据入库之前激发此事件 <br/>为商品数据入库前提供更改、补充商品数据的机会。
	 * 
	 * @param goods
	 *            商品数据
	 * @param request
	 *            HttpServletRequest 对象，可通过此对象获取用户的输入数据
	 * @throws GoodsRuntimeException
	 *             事件如果抛出此异常，框架会处理此异常，中断商品的添加，并回滚之前的数据操作
	 */
	public void onBeforeGoodsAdd(Map goods, HttpServletRequest request);

}

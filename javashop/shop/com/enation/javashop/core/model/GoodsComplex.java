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
package com.enation.javashop.core.model;

/**
 * 相关商品
 * 
 * @author lzf<br/>
 *         2010-3-30 下午12:03:45<br/>
 *         version 1.0<br/>
 */
public class GoodsComplex implements java.io.Serializable {
	private int goods_1;
	private int goods_2;
	private String manual;
	private int rate;// enum('left','both')

	public int getGoods_1() {
		return goods_1;
	}

	public void setGoods_1(int goods_1) {
		this.goods_1 = goods_1;
	}

	public int getGoods_2() {
		return goods_2;
	}

	public void setGoods_2(int goods_2) {
		this.goods_2 = goods_2;
	}

	public String getManual() {
		return manual;
	}

	public void setManual(String manual) {
		this.manual = manual;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

}

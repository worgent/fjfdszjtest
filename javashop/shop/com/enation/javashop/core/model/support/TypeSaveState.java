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
package com.enation.javashop.core.model.support;

/**
 * 商品类型在流程中的保存状态
 * @author apexking
 *
 */
public class TypeSaveState   {

 

     
     /*****以下参数控制流程所用。******/
	 private int do_save_props; //是否完成保存属性
	 private int do_save_params; //是否完成保存参数
	 private int do_save_brand; //是否完成保存品牌关联 
	 
	 
	public int getDo_save_brand() {
		return do_save_brand;
	}
	public void setDo_save_brand(int do_save_brand) {
		this.do_save_brand = do_save_brand;
	}
	public int getDo_save_params() {
		return do_save_params;
	}
	public void setDo_save_params(int do_save_params) {
		this.do_save_params = do_save_params;
	}
	public int getDo_save_props() {
		return do_save_props;
	}
	public void setDo_save_props(int do_save_props) {
		this.do_save_props = do_save_props;
	}

	

}
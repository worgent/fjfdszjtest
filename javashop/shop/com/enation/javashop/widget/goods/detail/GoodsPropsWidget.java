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

import java.util.List;
import java.util.Map;

import com.enation.eop.core.view.freemarker.FreeMarkerPaser;
import com.enation.javashop.core.model.Attribute;
import com.enation.javashop.core.service.IGoodsTypeManager;
import com.enation.javashop.widget.goods.AbstractGoodsDetailWidget;

/**
 * shopex式的商品属性信息展示挂件
 * @author kingapex
 * 2010-2-3下午06:25:29
 */
public class GoodsPropsWidget extends AbstractGoodsDetailWidget  {
	
	private IGoodsTypeManager goodsTypeManager;
	
	
	public void execute(Map<String, String> params,Map goods) {
		Integer type_id =(Integer)goods.get("type_id");
		if(type_id==null) {this.showHtml =false;return ;} //没有定义类型，忽略此挂件
		
		List<Attribute> attrList =goodsTypeManager.getAttrListByTypeId(type_id);
		
		int i=1;
		for(Attribute attr: attrList){
			String value =""+goods.get("p"+i);
			attr.setValue(value);
			i++;
		}
		
		//设置页名称为 base
		setPageName("props");
		//压入商品基本信息
		putData("goods", goods);
		putData("attrList",attrList);
		 
	}
 
	public void setGoodsTypeManager(IGoodsTypeManager goodsTypeManager) {
		this.goodsTypeManager = goodsTypeManager;
	}

	
	protected void config(Map<String, String> params) {
		// TODO Auto-generated method stub
		
	}

 
	
	

	 
 

}

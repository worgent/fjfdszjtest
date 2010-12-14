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
package com.enation.javashop.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.context.EopContext;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.javashop.core.model.GoodsComplex;
import com.enation.javashop.core.service.IGoodsComplexManager;

public class GoodsComplexManager extends BaseSupport implements
		IGoodsComplexManager {

	
	public List listGoodsComplex(int goods_id) {
		String sql = "select r.*, g.goods_id, g.sn, g.name, g.image_default image, g.price, g.mktprice from " + this.getTableName("goods_complex") + " r, " + this.getTableName("goods") + " g  where ((goods_2 = goods_id AND goods_1="+goods_id+") or (goods_1 = goods_id and goods_2 ="+goods_id+" and manual='both')) and rate > 99";
		List list = this.daoSupport.queryForList(sql);
		return list;
	}

	
	public void addCoodsComplex(GoodsComplex complex) {
		this.baseDaoSupport.insert("goods_complex", complex);
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void globalGoodsComplex(int goods_1, List<GoodsComplex> list) {
		//删除原有
		String deleteSql = "delete from goods_complex where goods_1 = ?";
		this.baseDaoSupport.execute(deleteSql, goods_1);
		
		//依次加入
		for(GoodsComplex complex:list){
			this.addCoodsComplex(complex);
		}
		
	}

}

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

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.impl.support.BaseSupport;
import com.enation.javashop.core.model.GoodsAdjunct;
import com.enation.javashop.core.model.GoodsComplex;
import com.enation.javashop.core.service.IGoodsAdjunctManager;

public class GoodsAdjunctManager extends BaseSupport implements
		IGoodsAdjunctManager {

	
	public List<Map> list(int goods_id) {
		String sql = "select * from goods_adjunct where goods_id = ?";
		List list = this.baseDaoSupport.queryForList(sql, goods_id);
		return list;
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(int goods_id, List<GoodsAdjunct> list) {
		//删除原有
		String deleteSql = "delete from goods_adjunct where goods_id = ?";
		this.baseDaoSupport.execute(deleteSql, goods_id);
		
		//依次加入
		for(GoodsAdjunct adjunct:list){
			this.add(adjunct);
		}
	}
	
	private void add(GoodsAdjunct adjunct) {
		this.baseDaoSupport.insert("goods_adjunct", adjunct);
	}

}

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

import com.enation.eop.impl.support.BaseSupport;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.DlyArea;
import com.enation.javashop.core.service.IAreaManager;

 

public class AreaManager extends BaseSupport<DlyArea> implements IAreaManager{
	
	public void delete(String id) {
		if (id == null || id.equals(""))
			return;
		String sql = "delete from dly_area where area_id in (" + id + ")";
		this.baseDaoSupport.execute(sql);
	}

	
	public List getAll() {
		String sql = "select * from dly_area order by area_id desc";
		List list = this.baseDaoSupport.queryForList(sql);
		return list;
	}

	
	public Page pageArea(String order, int page, int pageSize) {
		order = order == null ? " area_id desc" : order;
		String sql = "select  * from  dly_area";
		sql += " order by  " + order;
		Page webpage = this.baseDaoSupport.queryForPage(sql, page, pageSize);
		return webpage;
	}

	
	public void saveAdd(String name) {
		DlyArea dlyArea = new DlyArea();
		dlyArea.setName(name);
		this.baseDaoSupport.insert("dly_area", dlyArea);
	}

	
	public void saveEdit(Integer areaId, String name) {
		String sql = "update dly_area set name = ? where area_id=?";
		this.baseDaoSupport.execute(sql,name,areaId);
	}

	
	public DlyArea getDlyAreaById(Integer areaId) {
		String sql  = "select * from dly_area where area_id=?";
		DlyArea a =  this.baseDaoSupport.queryForObject(sql, DlyArea.class, areaId);
		return a;
	}
    
}

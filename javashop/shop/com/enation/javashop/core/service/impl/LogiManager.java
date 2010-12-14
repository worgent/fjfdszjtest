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
import com.enation.javashop.core.model.Logi;
import com.enation.javashop.core.service.ILogiManager;
 

public class LogiManager extends BaseSupport<Logi> implements ILogiManager{
	
	public void delete(String id) {
		if (id == null || id.equals(""))
			return;
		String sql = "delete from logi_company where id in (" + id + ")";
		this.baseDaoSupport.execute(sql);
	}

	
	public Logi getLogiById(Integer id) {
		String sql  = "select * from logi_company where id=?";
		Logi a =  this.baseDaoSupport.queryForObject(sql, Logi.class, id);
		return a;
	}

	
	public Page pageLogi(String order, Integer page, Integer pageSize) {
		order = order == null ? " id desc" : order;
		String sql = "select * from logi_company";
		sql += " order by  " + order;
		Page webpage = this.baseDaoSupport.queryForPage(sql, page, pageSize);
		return webpage;
	}

	
	public void saveAdd(String name) {
		Logi logi = new Logi();
		logi.setName(name);
		this.baseDaoSupport.insert("logi_company", logi);
		
	}

	
	public void saveEdit(Integer id, String name) {
		Logi logi = new Logi();
		logi.setName(name);
		logi.setId(id);
		this.baseDaoSupport.update("logi_company", logi, "id="+id);
	}

	
	public List list() {
		String sql = "select * from logi_company";
		return this.baseDaoSupport.queryForList(sql);
	}

	
}

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
import com.enation.javashop.core.model.AdColumn;
import com.enation.javashop.core.service.IAdColumnManager;

/**
 * 广告位管理
 * 
 * @author 李志富 lzf<br/>
 *         2010-2-4 下午03:56:01<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class AdColumnManager extends BaseSupport<AdColumn> implements
		IAdColumnManager {

	
	public void addAdvc(AdColumn adColumn) {
		this.baseDaoSupport.insert("adcolumn", adColumn);
	}

	
	public void delAdcs(String ids) {
		if (ids == null || ids.equals(""))
			return;
		String sql = "delete from adcolumn where acid in (" + ids
				+ ")";
		this.baseDaoSupport.execute(sql);
	}

	
	public AdColumn getADcolumnDetail(Long acid) {
		AdColumn  adColumn = this.baseDaoSupport.queryForObject("select * from adcolumn where acid = ?", AdColumn.class, acid);
		return adColumn;
	}

	
	public List listAllAdvPos() {
		List<AdColumn> list = this.baseDaoSupport.queryForList("select * from adcolumn", AdColumn.class);
		return list;
	}

	
	public Page pageAdvPos(int page, int pageSize) {
		String sql = "select * from adcolumn";
		Page rpage = this.baseDaoSupport.queryForPage(sql, page, pageSize);
		return rpage;
	}

	
	public void updateAdvc(AdColumn adColumn) {
		this.baseDaoSupport.update("adcolumn", adColumn, "acid = " + adColumn.getAcid());
	}

}

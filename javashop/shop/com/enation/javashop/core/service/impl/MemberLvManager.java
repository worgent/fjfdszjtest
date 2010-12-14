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

import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.model.MemberLv;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.service.IMemberLvManager;
 

/**
 * 会员级别管理
 * @author kingapex
 *2010-4-29下午11:04:43
 */
public class MemberLvManager extends BaseSupport<MemberLv> implements IMemberLvManager{

	
	public void add(MemberLv lv) {
		this.baseDaoSupport.insert("member_lv", lv);
	}

	
	public void edit(MemberLv lv) {
		this.baseDaoSupport.update("member_lv", lv, "lv_id=" + lv.getLv_id());
	}

	
	public Integer getDefaultLv() {
		String sql  ="select * from member_lv where default_lv=1";
		List<MemberLv> lvList  = this.baseDaoSupport.queryForList(sql, MemberLv.class);
		if(lvList==null || lvList.isEmpty()){
			return null;
		}
		
		return lvList.get(0).getLv_id();
	}

	
	public Page list(String order, int page, int pageSize) {
		order = order == null ? " lv_id desc" : order;
		String sql = "select * from member_lv ";
		sql += " order by  " + order;
		Page webpage = this.baseDaoSupport.queryForPage(sql, page, pageSize);
		return webpage;
	}

	
	public MemberLv get(Integer lvId) {
		String sql = "select * from member_lv where lv_id=?";
		MemberLv lv = this.baseDaoSupport.queryForObject(sql, MemberLv.class,
				lvId);
		return lv;
	}

	
	public void delete(String id) {
		if (id == null || id.equals(""))
			return;
		String sql = "delete from member_lv where lv_id in (" + id + ")";
		this.baseDaoSupport.execute(sql);
	}

	
	public List<MemberLv> list() {
		String sql = "select * from member_lv order by lv_id";
		List lvlist = this.baseDaoSupport.queryForList(sql, MemberLv.class);
		return lvlist;
	}

	
	public List<MemberLv> list(String ids) {
		
		if( StringUtil.isEmpty(ids)) return new ArrayList();
		
		String sql = "select * from member_lv where  lv_id in("+ids+") order by lv_id";
		List lvlist = this.baseDaoSupport.queryForList(sql, MemberLv.class);
		return lvlist;
		 
	}

	 

	 

}

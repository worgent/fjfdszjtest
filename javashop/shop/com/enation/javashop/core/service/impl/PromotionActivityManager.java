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

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.impl.support.BaseSupport;
import com.enation.framework.database.ObjectNotFoundException;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.PromotionActivity;
import com.enation.javashop.core.service.IPromotionActivityManager;

/**
 * 促销活动
 * 
 * @author lzf<br/>
 *         2010-4-20下午04:56:34<br/>
 *         version 1.0
 */
public class PromotionActivityManager extends BaseSupport<PromotionActivity>
		implements IPromotionActivityManager {

	
	public void add(PromotionActivity activity) {
		if(activity == null ) throw new  IllegalArgumentException("param activity is NULL");
		if(activity.getName() == null ) throw new  IllegalArgumentException("param activity.name is NULL");
		if(activity.getBegin_time() == null ) throw new  IllegalArgumentException("param activity.begin_time is NULL");
		if(activity.getEnd_time() == null ) throw new  IllegalArgumentException("param activity.end_time is NULL");
		this.baseDaoSupport.insert("promotion_activity", activity);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	
	public void delete(Integer[] idArray) {
		if (idArray != null && idArray.length > 0) {
			String ids = StringUtil.arrayToString(idArray, ",");
			this.baseDaoSupport.execute(
					"delete from promotion_activity where id in (" + ids + ")");
			
			this.daoSupport.execute("delete from "+this.getTableName("pmt_member_lv")+" where pmt_id in(select pmt_id from "+this.getTableName("promotion")+" where pmta_id in(?))",ids);
			this.daoSupport.execute("delete from "+this.getTableName("pmt_goods")+" where pmt_id in(select pmt_id from "+this.getTableName("promotion")+" where pmta_id in(?))",ids);
			this.baseDaoSupport.execute("delete from promotion where pmta_id in(?)", ids);
		}

	}

	
	public void edit(PromotionActivity activity) {
		if(activity.getId() == null ) throw new  IllegalArgumentException("param activity.id is NULL");
		if(activity.getName() == null ) throw new  IllegalArgumentException("param activity.name is NULL");
		if(activity.getBegin_time() == null ) throw new  IllegalArgumentException("param activity.begin_time is NULL");
		if(activity.getEnd_time() == null ) throw new  IllegalArgumentException("param activity.end_time is NULL");
		this.baseDaoSupport.update("promotion_activity", activity, "id="
				+ activity.getId());

	}

	
	public PromotionActivity get(Integer id) {
		if(id == null ) throw new  IllegalArgumentException("param activity.id is NULL");
		PromotionActivity activity = this.baseDaoSupport.queryForObject(
				"select * from promotion_activity where id = ?",
				PromotionActivity.class, id);
		if(activity == null) throw new ObjectNotFoundException("activity is NULL");
		return activity;
	}

	
	public Page list(int pageNo, int pageSize) {
		String sql = "select * from promotion_activity order by id desc";
		return this.baseDaoSupport.queryForPage(sql, pageNo, pageSize);
	}

}

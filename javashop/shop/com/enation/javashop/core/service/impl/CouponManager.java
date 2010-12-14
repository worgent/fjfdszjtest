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

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.impl.support.BaseSupport;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Coupons;
import com.enation.javashop.core.service.ICouponManager;

/**
 * @author lzf
 * version 1.0<br/>
 * 2010-6-13&nbsp;上午11:24:50
 */
public class CouponManager extends BaseSupport<Coupons> implements
		ICouponManager {

	
	public Page list(int pageNo, int pageSize, String order) {
	 
		order = order == null ? " cpns_id desc" : order;
		String sql = "select c.pmt_id, cpns_id, cpns_name,cpns_prefix, cpns_status, pmt_time_begin, pmt_time_end, cpns_type, cpns_gen_quantity, cpns_point from "
				+ this.getTableName("coupons")
				+ " as c  left join "
				+ this.getTableName("promotion") + " as p on c.pmt_id=p.pmt_id";
	 
		sql += " order by " + order;
		Page page = this.daoSupport.queryForPage(sql, pageNo, pageSize);
		return page;
	}

	
	public void add(Coupons coupons) {
		if (coupons.getCpns_name() == null)
			throw new IllegalArgumentException(
					"param coupons.cpns_name is NULL");
		if (coupons.getCpns_prefix() == null)
			throw new IllegalArgumentException(
					"param coupons.cpns_prefix is NULL");
		if (coupons.getPmt_id() == 0)
			throw new IllegalArgumentException("param coupons.pmt_id is 0");
		coupons.setDisabled("false");
		this.baseDaoSupport.insert("coupons", coupons);
	}

	
	public void saveExchange(Integer cpnsId, Integer point) {
		if (cpnsId == null)
			throw new IllegalArgumentException(
					"param cpnsId is NULL");
		if (point == null)
			throw new IllegalArgumentException(
					"param point is NULL");
		this.baseDaoSupport.execute("update coupons set cpns_point = ? where cpns_id = ?", point, cpnsId);

	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Integer[] cpnsIdArray, Integer[] pmtIdArray) {
		if ((cpnsIdArray != null) && (pmtIdArray != null)) {
			if (cpnsIdArray.length != pmtIdArray.length)
				throw new IllegalArgumentException(
						"param cpnsIdArray.length and pmtIdArray.length is not equals");
			if (cpnsIdArray.length > 0) {
				String ids = StringUtil.arrayToString(cpnsIdArray, ",");
				this.baseDaoSupport.execute(
						"delete from coupons where cpns_id in (" + ids + ")");
				ids = StringUtil.arrayToString(pmtIdArray, ",");
				this.baseDaoSupport.execute(
						"delete from promotion where pmt_id in (" + ids + ")");
			}
		}
	}

	
	public void edit(Coupons coupons) {
		if (coupons.getCpns_id() == 0)
			throw new IllegalArgumentException("param coupons.cpns_id is 0");
		if (coupons.getCpns_name() == null)
			throw new IllegalArgumentException(
					"param coupons.cpns_name is NULL");
		if (coupons.getCpns_prefix() == null)
			throw new IllegalArgumentException(
					"param coupons.cpns_prefix is NULL");
		if (coupons.getPmt_id() == 0)
			throw new IllegalArgumentException("param coupons.pmt_id is 0");
		coupons.setDisabled("false");
		this.baseDaoSupport.update("coupons", coupons, "cpns_id="
				+ coupons.getCpns_id());

	}

	
	public List listCanExchange() {
		String sql = "select * from coupons where cpns_type = '1' and (cpns_point is null or cpns_point = 0) and cpns_status = '1'";
		List list = this.baseDaoSupport.queryForList(sql);
		return list;
	}

	
	public Page listExchange(int pageNo, int pageSize) {
		Long curTime = (new Date()).getTime();
		String sql = "select *,c.pmt_id as pmt_id from "
				+ this.getTableName("coupons")
				+ " as c left join "
				+ this.getTableName("promotion")
				+ " as p on c.pmt_id=p.pmt_id";
		sql += " where cpns_type='1' and cpns_point > 0";
		sql += " and cpns_status='1' AND pmt_time_begin <= " + curTime
		+ " and pmt_time_end >" + curTime;
		Page page = this.daoSupport.queryForPage(sql, pageNo, pageSize);
		
		return page;
	}

	
	public Coupons get(Integer cpnsid) {
		if (cpnsid == null)
			throw new IllegalArgumentException("param cpnsid is null");
		Coupons coupons = this.baseDaoSupport.queryForObject(
				"select * from coupons where cpns_id = ?", Coupons.class,
				cpnsid);
		if (coupons == null)
			throw new IllegalArgumentException("coupons is null");
		return coupons;
	}

	
	public void deleteExchange(Integer[] cpnsIdArray) {
		if(cpnsIdArray != null && cpnsIdArray.length>0){
			String ids = StringUtil.arrayToString(cpnsIdArray, ",");
			this.baseDaoSupport.execute("update coupons set cpns_point = null where cpns_id in (" + ids + ")");
		}

	}

}

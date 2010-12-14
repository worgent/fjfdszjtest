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

import com.enation.eop.EopSetting;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.utils.UploadUtil;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Adv;
import com.enation.javashop.core.model.mapper.AdvMapper;
import com.enation.javashop.core.service.IAdvManager;

/**
 * 广告管理
 * 
 * @author 李志富 lzf<br/>
 *         2010-2-4 下午03:55:33<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class AdvManager extends BaseSupport<Adv> implements IAdvManager {

	
	public void addAdv(Adv adv) {
		this.baseDaoSupport.insert("adv", adv);

	}

	
	public void delAdvs(String ids) {
		if (ids == null || ids.equals(""))
			return;
		String sql = "delete from adv where aid in (" + ids
				+ ")";
		this.baseDaoSupport.execute(sql);
	}

	
	public Adv getAdvDetail(Long advid) {
		Adv adv = this.baseDaoSupport.queryForObject("select * from adv where aid = ?", Adv.class, advid);
		String pic  = adv.getAtturl();
		if(pic!=null){
			pic  =UploadUtil.replacePath(pic); 
			adv.setAtturl(pic);
		}
		return adv;
	}

	
	public Page pageAdv(String order, int page, int pageSize) {
		order = order == null ? " aid desc" : order;
		String sql = "select v.*, c.cname   cname from " + this.getTableName("adv") + " v left join " + this.getTableName("adcolumn") + " c on c.acid = v.acid";
		sql += " order by " + order; 
		Page rpage = this.daoSupport.queryForPage(sql, page, pageSize,new AdvMapper());
		return rpage;
	}

	
	public void updateAdv(Adv adv) {
		this.baseDaoSupport.update("adv", adv, "aid = " + adv.getAid());

	}
	
	
	public List listAdv(Long acid) {
		Long nowtime = (new Date()).getTime();
		List<Adv> list = this.baseDaoSupport.queryForList("select a.*,'' cname from adv a where acid = ? and begintime<=? and endtime>=? and isclose = 0", new AdvMapper(), acid, nowtime, nowtime);
		return list;
	}
	
	 

}

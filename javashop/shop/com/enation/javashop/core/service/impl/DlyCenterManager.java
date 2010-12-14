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
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.DlyCenter;
import com.enation.javashop.core.service.IDlyCenterManager;

/**
 * 发货中心
 * @author lzf<br/>
 * 2010-4-30上午10:14:35<br/>
 * version 1.0
 */
public class DlyCenterManager extends BaseSupport<DlyCenter> implements IDlyCenterManager {

	
	public void add(DlyCenter dlyCenter) {
		this.baseDaoSupport.insert("dly_center", dlyCenter);
	}

	
	public void delete(Integer[] id) {
		if(id== null  || id.length==0  ) return ;
		String ids = StringUtil.arrayToString(id, ",");
		this.baseDaoSupport.execute("update dly_center set disabled = 'true' where dly_center_id in (" + ids + ")");

	}

	
	public void edit(DlyCenter dlyCenter) {
		this.baseDaoSupport.update("dly_center", dlyCenter, "dly_center_id = " + dlyCenter.getDly_center_id());

	}

	
	public List<DlyCenter> list() {
		return this.baseDaoSupport.queryForList("select * from dly_center where disabled = 'false'", DlyCenter.class);
	}

	
	public DlyCenter get(Integer dlyCenterId) {
		return this.baseDaoSupport.queryForObject("select * from dly_center where dly_center_id = ?", DlyCenter.class, dlyCenterId);
	}

}

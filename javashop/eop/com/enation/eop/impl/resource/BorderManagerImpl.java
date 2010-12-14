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
package com.enation.eop.impl.resource;

import java.util.List;

import com.enation.eop.core.resource.IBorderManager;
import com.enation.eop.core.resource.model.Border;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.framework.database.IDBRouter;
import com.enation.framework.database.IDaoSupport;

/**
 * saas式的边框管理 
 * @author kingapex
 * 2010-1-28下午05:41:09
 */
public class BorderManagerImpl extends BaseSupport<Border>  implements IBorderManager {
 

	
	
	public void add(Border border) {
		this.baseDaoSupport.insert("border", border);
	}

	
	public void delete(Integer id) {
		this.baseDaoSupport.execute("delete from border where id=?", id);
	}

	
	public List<Border> list() {
		String sql  ="select * from  border";
		List<Border> list = this.baseDaoSupport.queryForList(sql, Border.class);
		return list;
	}

	
	public void update(Border border) {
		this.baseDaoSupport.update("border", border, "id="+border.getId());
	}

 
	
}

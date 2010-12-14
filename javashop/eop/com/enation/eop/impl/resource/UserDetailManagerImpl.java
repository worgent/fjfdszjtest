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

import com.enation.eop.core.IUserDetailManager;
import com.enation.eop.core.resource.model.EopUserDetail;
import com.enation.framework.database.IDaoSupport;
/**
 * 用户详细信息管理
 * @author kingapex
 *2010-5-10下午12:36:16
 */
public class UserDetailManagerImpl implements IUserDetailManager {
	private IDaoSupport<EopUserDetail> daoSupport;
	
	public void add(EopUserDetail eopUserDetail) {
		this.daoSupport.insert("eop_userdetail", eopUserDetail);
	}

	
	public void edit(EopUserDetail eopUserDetail) {
		this.daoSupport.update("eop_userdetail", eopUserDetail, " userid = " + eopUserDetail.getUserid());
	}

	
	public EopUserDetail get(Integer userid) {
		return this.daoSupport.queryForObject(	"select * from eop_userdetail where userid = ?",
				EopUserDetail.class, userid);
	}

	public IDaoSupport<EopUserDetail> getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport<EopUserDetail> daoSupport) {
		this.daoSupport = daoSupport;
	}

	
}

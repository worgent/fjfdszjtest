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
package com.enation.eop.impl.support;

import org.apache.log4j.Logger;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.framework.database.IDBRouter;
import com.enation.framework.database.IDaoSupport;

public abstract class BaseSupport<T> {
	protected final Logger logger = Logger.getLogger(getClass());
		
	private IDBRouter baseDBRouter; 
	protected IDaoSupport<T> baseDaoSupport;  
	protected IDaoSupport<T> daoSupport;   

	/**
	 * 获取表名
	 * @return
	 */
	protected String getTableName(String moude){
		return baseDBRouter.getTableName(  moude);
		
	}

	
	
	/**
	 * 检测操作的“属主”合法性
	 * @param userid
	 */
	protected void checkIsOwner( final Integer userid){
		
		if(userid==null){
			throw new PermssionRuntimeException();
		}
		
	 
		Integer suserid = EopContext.getContext().getCurrentSite().getUserid();	
		
		if(suserid.intValue()!=userid.intValue()){
			throw new PermssionRuntimeException();
			
		}
	}


	public IDaoSupport<T> getBaseDaoSupport() {
		return baseDaoSupport;
	}


	public void setBaseDaoSupport(IDaoSupport<T> baseDaoSupport) {
		this.baseDaoSupport = baseDaoSupport;
	}	
	public void setDaoSupport(IDaoSupport<T> daoSupport) {
		this.daoSupport = daoSupport;
	}



	public IDBRouter getBaseDBRouter() {
		return baseDBRouter;
	}



	public void setBaseDBRouter(IDBRouter baseDBRouter) {
		this.baseDBRouter = baseDBRouter;
	}



 
	
}

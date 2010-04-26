/**
* Copyright (C) qzgf, 2010
*
* License        :Apache License 2.0
* Project        :webframe
* Package        :com.qzgf.utils.sms
* File	         :Test.java
* Written by     :fjfdszj
* Created Date   :Mar 20, 2010
* Purpose        :短信接收基类实现

======================================

* Modifyer by    :fjfdszj
* Update Date    :Mar 20, 2010
* Purpose        :描述

*/
package com.qzgf.utils.sms.notify;

import org.apache.log4j.Logger;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.utils.sms.NotifyHandler;
import com.qzgf.utils.sms.SMProxySendFacade;
import com.qzgf.utils.sms.SmsBean;



/**
 * Purpose      : 短信转发接口实现基类
 *
 * @author fjfdszj
 * @see     AbstractNotifyHandlerImpl.java
 *
 */
public abstract class AbstractNotifyHandlerImpl implements NotifyHandler {
	protected Logger logger = Logger.getLogger(AbstractNotifyHandlerImpl.class);
	//数据库操作类
	/**
	 * 数据操作接口
	 */
	protected BaseSqlMapDAO baseSqlMapDAO;	
	//发送短信
	/**
	 * 短信发送接口
	 */
	protected SMProxySendFacade sendmsm;
	//对象参数
	/**
	 * 接收短信信息的参数
	 */
	protected SmsBean smsbean=new SmsBean();
	
	/**
	 * Purpose      : 数据库操作对象
	 * @param baseSqlMapDAO the baseSqlMapDAO to set
	 */
	
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	/**
	 * Purpose      : 短信息发送接口
	 * @param sendmsm the sendmsm to set
	 */
	
	public void setSendmsm(SMProxySendFacade sendmsm) {
		this.sendmsm = sendmsm;
	}
	
	/**
	 * Purpose      : 短信信息传递类
	 * @param smsbean the smsbean to set
	 */
	
	public void setSmsbean(SmsBean smsbean) {
		this.smsbean = smsbean;
	}
	
	
	
	
	
}

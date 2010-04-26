/**
* Copyright (C) qzgf, 2010
*
* License        :Apache License 2.0
* Project        :webframe
* Package        :com.qzgf.utils.sms
* File	         :Test.java
* Written by     :fjfdszj
* Created Date   :Mar 20, 2010
* Purpose        :短信接收转发接口

======================================

* Modifyer by    :fjfdszj
* Update Date    :Mar 20, 2010
* Purpose        :描述

*/
package com.qzgf.utils.sms;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;



/**
 * Purpose      : 短信接收转发接口
 *
 * @author fjfdszj
 * @see     NotifyHandler.java
 *
 */
public interface NotifyHandler
{
	public void handle() throws Exception;
	
	/**
	 * Purpose      : 短信息发送接口
	 * @param sendmsm the sendmsm to set
	 */
	
	public void setSendmsm(SMProxySendFacade sendmsm) ;
	
	/**
	 * Purpose      : 数据库操作对象
	 * @param baseSqlMapDAO the baseSqlMapDAO to set
	 */
	
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) ;
	
	/**
	 * Purpose      : 短信信息传递类
	 * @param smsbean the smsbean to set
	 */
	
	public void setSmsbean(SmsBean smsbean) ;
	
}

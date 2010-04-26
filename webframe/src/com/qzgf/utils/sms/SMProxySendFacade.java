/**
* Copyright (C) qzgf, 2010
*
* License        :Apache License 2.0
* Project        :webframe
* Package        :com.qzgf.utils.sms
* File	         :Test.java
* Written by     :fjfdszj
* Created Date   :Mar 20, 2010
* Purpose        :华为短信接口

======================================

* Modifyer by    :fjfdszj
* Update Date    :Mar 20, 2010
* Purpose        :描述

*/
package com.qzgf.utils.sms;

//基本类的方法导入



/**
 * Purpose      : 短信发送接口
 *
 * @author  fjfdszj
 * @see      SMProxySendFacade.java
 *
 */
public interface SMProxySendFacade {

	/**
	 * Purpose      : 共用接口发送短信处理 
	 * @param sms
	 * @return 
	 */
	public int SmsSend(SmsBean sms);
}

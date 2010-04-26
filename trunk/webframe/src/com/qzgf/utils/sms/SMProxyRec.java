/**
* Copyright (C) qzgf, 2010
*
* License        :Apache License 2.0
* Project        :webframe
* Package        :com.qzgf.utils.sms
* File	         :Test.java
* Written by     :fjfdszj
* Created Date   :Mar 20, 2010
* Purpose        :华为短信接收类

======================================

* Modifyer by    :fjfdszj
* Update Date    :Mar 20, 2010
* Purpose        :描述

*/
package com.qzgf.utils.sms;

import com.huawei.insa2.comm.cmpp.message.CMPPDeliverMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPMessage;
import com.huawei.insa2.util.Args;
import com.huawei.smproxy.SMProxy;
import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;

/**
 * Purpose      : 短信接收接口,转入发送类实现
 *
 * @author fjfdszj
 * @see     SMProxyRec.java
 *
 */
public class SMProxyRec extends SMProxy {
	private static Log log = LogFactory.getLog(SMProxyRec.class);
    private SMProxySendFacadeImpl sendFacade; 

    public SMProxyRec(SMProxySendFacadeImpl sendFacade,Args args) 
    { 
        super(args); 
        this.sendFacade = sendFacade; 
    } 

    public CMPPMessage onDeliver(CMPPDeliverMessage msg) 
    { 
    	log.debug("启动SMProxyRec接收短信");
    	sendFacade.ProcessRecvDeliverMsg(msg);
        return super.onDeliver(msg); 
    } 

    public void OnTerminate() 
    { 
    	super.onTerminate();
    }
}

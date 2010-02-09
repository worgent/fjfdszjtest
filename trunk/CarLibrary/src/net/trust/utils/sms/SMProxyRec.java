/**
 * 说明：这个类关键是继承SMproxy，然后才可以接收短信。
 *       触发onDeliver事件。
 * 时间：2008-12-16
 */

package net.trust.utils.sms;

import com.huawei.insa2.util.Args;
import com.huawei.smproxy.SMProxy;
import com.huawei.insa2.comm.cmpp.message.*;
import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;

public class SMProxyRec extends SMProxy {
	private static Log log = LogFactory.getLog(SMProxyRec.class);
    private SMProxySendFacadeImplTimeTask demo; 

    public SMProxyRec(SMProxySendFacadeImplTimeTask demo,Args args) 
    { 
        super(args); 
        this.demo = null; 
        this.demo = demo; 
    } 

    public CMPPMessage onDeliver(CMPPDeliverMessage msg) 
    { 
    	log.debug("启动SMProxyRec接收短信");
        demo.ProcessRecvDeliverMsg(msg); 
        return super.onDeliver(msg); 
    } 

    public void OnTerminate() 
    { 
        demo.Terminate(); 
    }
}

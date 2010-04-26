/**
* Copyright (C) qzgf, 2010
*
* License        :Apache License 2.0
* Project        :webframe
* Package        :com.qzgf.utils.sms
* File	         :Test.java
* Written by     :fjfdszj
* Created Date   :Mar 20, 2010
* Purpose        :短信接收短号处理事例

======================================

* Modifyer by    :fjfdszj
* Update Date    :Mar 20, 2010
* Purpose        :描述

*/
package com.qzgf.utils.sms.notify;

import java.util.HashMap;
import java.util.List;

import com.qzgf.utils.sms.SmsBean;


/**
 * Purpose      : 示例参考
 *
 * @author fjfdszj
 * @see     Example.java
 *
 */
public class Example extends AbstractNotifyHandlerImpl
{
    
    /**
     * Purpose      : 接口实现,接收类的处理
     * @param notify
     * @throws Exception 
     */
    public void handle() throws Exception
    {
    	prosendMsg();
    }
    
    public void fun1() throws Exception
    {
    	prosendMsg();
    }
  
    
    public void fun2() throws Exception
    {
    	prosendMsg();
    }
    
    /**
     * Purpose      : 具体实现方式
     */
    public void prosendMsg()
    {
    	//测试数据库操作类
    	HashMap hs=new HashMap();
    	hs.put("pservicecode", "11");
		List ls=baseSqlMapDAO.queryForList("sms.findconfigsms", hs);
		//测试接收短信对象
		SmsBean sms=this.smsbean;
		//ArrayList  mobileNoList=this.smsbean.get
		//sms.setMobileNoList(mobileNoList);
		
		//测试发送短信
		this.sendmsm.SmsSend(sms);
		/*
		 *      进一步,将手机封装入bean中.
				ArrayList mobileList = new ArrayList();
				mobileList.add(mobile);
				
				ArrayList telList = sms.getMobileNoList();
				dest_Terminal_Id = new String[telList.size()];
				for (int i = 0; i < telList.size(); i++) {
					dest_Terminal_Id[i] = telList.get(i).toString();
				}
		 */
		
    	
    }

}

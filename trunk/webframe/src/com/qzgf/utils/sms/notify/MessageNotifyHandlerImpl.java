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
 * Purpose      : 短信转发接口实现,针对不需要回执的,即没有设置服务代码的上行短信
 *
 * @author fjfdszj
 * @see     MessageNotifyHandlerImpl.java
 *
 */
public class MessageNotifyHandlerImpl extends AbstractNotifyHandlerImpl
{
    
    /**
     * Purpose      : 接口实现,接收类的处理
     * @param notify
     * @throws Exception 
     */
    public void handle() throws Exception
    {
    	//接收短信指令分析处理
    	String cmd=smsbean.getContent();//取得客户上行消息
    	String[] cmdArr=cmd.split("\\+"); //分析消息,取得命令字
    	//分析字符串示例:cmdArr[1]   cmd.substring(cmd.indexOf(cmd[2]))
    	//“订阅向导+XXXX”
    	if(cmdArr[0].equals("订阅向导")){
    		
    	}//“订阅优惠+XXXX”
    	else if(cmdArr[0].equals("订阅优惠")){
    		
    	}//“注册+XXXX”
    	else if(cmdArr[0].equals("注册")){
    		
    	}//“我在+XXXX”
    	else if(cmdArr[0].equals("我在")){
    		
    	}//“推荐+XXXX”
    	else if(cmdArr[0].equals("推荐")){
    		
    	}//“悬赏+XXXX”
    	else if(cmdArr[0].equals("悬赏")){
    		
    	}//“下载地图+XXXX”
    	else if(cmdArr[0].equals("下载地图")){
    		
    	}//“下载优惠+XXXX”
    	else if(cmdArr[0].equals("下载优惠")){
    		
    	}//“下载锦囊+XXXX”
    	else if(cmdArr[0].equals("下载锦囊")){
    		
    	}//“日志+XXXX”
    	else if(cmdArr[0].equals("日志")){
    		
    	}//“跟随+XXXX”
    	else if(cmdArr[0].equals("跟随")){
    		
    	}//“凭此券+XXXX”
    	else if(cmdArr[0].equals("凭此券")){
    		
    	}else{
    		//异常处理,下行用户提示,您输入的指令有误,请查询
    	}
    }
    
    
    
    
    //以下相关参数的使用事例
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

/**
* Copyright (C) qzgf, 2010
*
* License        :Apache License 2.0
* Project        :webframe
* Package        :com.qzgf.utils.sms
* File	         :Test.java
* Written by     :fjfdszj
* Created Date   :Mar 20, 2010
* Purpose        :短信内容类封装

======================================

* Modifyer by    :fjfdszj
* Update Date    :Mar 20, 2010
* Purpose        :描述

*/
package com.qzgf.utils.sms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


/**
 * Purpose      : 短信信息传输类
 *
 * @author fjfdszj
 * @see     SmsBean.java
 *
 */
public class SmsBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2200308032749875862L;
	private ArrayList mobileNoList; // 手机号码,群发手机数据组  MOBILE
    //2010-03-25,用户信息的扩充(针对发送短信)
	private String name="";//接收者用户名
	private String userid="";//接收者用户编号
	private String sendid="";//发送者用户编号
	
	private String content=""; 	 		 // 短信内容                      CONTENT 
	private String msisdn=""; 			 // 序列号，8位数(2位命令字+6位自增序号)  MSISDN
	private String servercode="" ;         //服务编码
	
	//针对发送短信,需要回执的信息相关的业务单据号
	private String srcbill="";             
	
	//2010-03-25,扩充字段
	private Date time;//接收时间或者发送时间

	/**
	 * Purpose      : 手机号数组
	 * @return the mobileNoList 数据类型ArrayList
	 */
	public ArrayList getMobileNoList() {
		return mobileNoList;
	}

	/**
	 * Purpose      : 手机号数组
	 * @param mobileNoList the mobileNoList to set 数据类型ArrayList
	 */
	
	public void setMobileNoList(ArrayList mobileNoList) {
		this.mobileNoList = mobileNoList;
	}

	/**
	 * Purpose      : 接收者用户名
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Purpose      : 接收者用户名
	 * @param name the name to set
	 */
	
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Purpose      : 接收者用户编号
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * Purpose      : 接收者用户编号
	 * @param userid the userid to set
	 */
	
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * Purpose      : 发送者用户编号
	 * @return the sendid
	 */
	public String getSendid() {
		return sendid;
	}

	/**
	 * Purpose      : 发送者用户编号
	 * @param sendid the sendid to set
	 */
	
	public void setSendid(String sendid) {
		this.sendid = sendid;
	}

	/**
	 * Purpose      :  短信内容  
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Purpose      :  短信内容  
	 * @param content the content to set
	 */
	
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Purpose      : 序列号，8位数(2位命令字+6位自增序号)  MSISDN
	 * @return the msisdn
	 */
	public String getMsisdn() {
		return msisdn;
	}

	/**
	 * Purpose      : 序列号，8位数(2位命令字+6位自增序号)  MSISDN
	 * @param msisdn the msisdn to set
	 */
	
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	/**
	 * Purpose      : 服务编码
	 * @return the servercode
	 */
	public String getServercode() {
		return servercode;
	}

	/**
	 * Purpose      : 服务编码
	 * @param servercode the servercode to set
	 */
	
	public void setServercode(String servercode) {
		this.servercode = servercode;
	}

	/**
	 * Purpose      : 针对发送短信,需要回执的信息相关的业务单据号
	 * @return the srcbill
	 */
	public String getSrcbill() {
		return srcbill;
	}

	/**
	 * Purpose      : 针对发送短信,需要回执的信息相关的业务单据号
	 * @param srcbill the srcbill to set
	 */
	
	public void setSrcbill(String srcbill) {
		this.srcbill = srcbill;
	}

	/**
	 * Purpose      : 接收时间或者发送时间
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * Purpose      : 接收时间或者发送时间
	 * @param time the time to set
	 */
	
	public void setTime(Date time) {
		this.time = time;
	}

	
	
}

/**
 * Copyright (C) qzgf, 2010
 *
 * License        :Apache License 2.0
 * Project        :webframe
 * Package        :com.qzgf.utils.sms
 * File	         :Test.java
 * Written by     :fjfdszj
 * Created Date   :Mar 20, 2010
 * Purpose        :华为短信实现类

======================================

 * Modifyer by    :fjfdszj
 * Update Date    :Mar 20, 2010
 * Purpose        :描述

 */
package com.qzgf.utils.sms;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.huawei.insa2.comm.cmpp.message.CMPPDeliverMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPSubmitMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPSubmitRepMessage;
import com.huawei.insa2.util.Args;
import com.huawei.insa2.util.Cfg;
import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;


/**
 * Purpose      : 短信发送实现
 *
 * @author fjfdszj
 * @see     SMProxySendFacadeImpl.java
 *
 */
public class SMProxySendFacadeImpl implements SMProxySendFacade {
	// 从xml中读取配置信息
	private Args argsconn; // 读取连接信息
	private Args argssumbit; // 读取提交信息
	// 基本提交信息参数说明
	private int pk_Total = 1;
	private int pk_Number = 1;
	private int registered_Delivery = 1;
	private int msg_Level = 1;
	private String service_Id = "04545";
	private int fee_UserType = 2;
	private String fee_Terminal_Id = "106573041123";
	private int tp_Pid = 0;
	private int tp_Udhi = 0;
	private int msg_Fmt = 8;
	private String msg_Src = "123456";
	private String fee_Type = "02";
	private String fee_Code = "000";
	private Date valid_Time = null;
	private Date at_Time = null;
	private String src_Terminal_Id = "106573041123";
	private String[] dest_Terminal_Id = { "13599204724" };
	private byte[] msg_Content = null;
	private String reserve = "";//保留项

	/** 短信收发接口 */
	public static SMProxyRec myProxy = null;
	/** 数据库操作类*/
	public BaseSqlMapDAO baseSqlMapDAO;
	/** 日志记录*/
	private static Log log = LogFactory.getLog(SMProxySendFacadeImpl.class);
	/**存储反射类的缓冲器*/
	private Hashtable<String, NotifyHandler> notifyHandlers;

	//===========================================================短信配置信息处理===================================================
	/**
	 * Purpose      : 读取信息机的配置文件
	 */
	private void ProBaseConf() {
		try {
			log.debug("初始化连接信息");
			// 连接配置信息(加载D:\Program Files\Apache Software Foundation\Tomcat 6.0\bin\Smproxy.xml)
			argsconn = new Cfg("Smproxy.xml", false).getArgs("CMPPConnect");
			// 初始化短信收发接口
			myProxy = new SMProxyRec(this, argsconn);
			// 提交参数设置
			argssumbit = new Cfg("Smproxy.xml", false)
					.getArgs("CMPPSubmitMessage");
			pk_Total = argssumbit.get("pk_Total", 1);
			pk_Number = argssumbit.get("pk_Number", 1);
			registered_Delivery = argssumbit.get("registered_Delivery", 1);
			msg_Level = argssumbit.get("msg_Level", 1);
			service_Id = argssumbit.get("service_Id", "04545");
			fee_UserType = argssumbit.get("fee_UserType", 2);
			fee_Terminal_Id = argssumbit.get("fee_Terminal_Id", "106573041123");
			tp_Pid = argssumbit.get("tp_Pid", 1);
			tp_Udhi = argssumbit.get("tp_Udhi", 1);
			msg_Fmt = argssumbit.get("msg_Fmt", 8);
			msg_Src = argssumbit.get("msg_Src", "123456");
			fee_Type = argssumbit.get("fee_Type", "02");
			fee_Code = argssumbit.get("fee_Code", "000");
			src_Terminal_Id = argssumbit.get("src_Terminal_Id", "106573041123");
			reserve = argssumbit.get("reserve", "");
			
			//初始化类池
			notifyHandlers=new Hashtable<String, NotifyHandler>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 构造方法
	 */
	public SMProxySendFacadeImpl() {
		// 处理基本的配置信息
		ProBaseConf();
	}

	/**
	 * 功能：
	 *     将src里的字节与add里的从start开始到end（不包括第end个位置)的字节串连在一起返回
	 * @param src
	 * @param add
	 * @param start    add 开始位置
	 * @param end      add 的结束位置(不包括end位置)
	 * @return 也即实现类似String类型的src+add.subString(start,end)功能
	 */
	public static byte[] byteAdd(byte[] src, byte[] add, int start, int end) {
		byte[] dst = new byte[src.length + end - start];
		for (int i = 0; i < src.length; i++) {
			dst[i] = src[i];
		}
		for (int i = 0; i < end - start; i++) {
			dst[src.length + i] = add[start + i];
		}
		return dst;
	}

	//升级对字节数组的组合处理2009-04-10
	public static byte[] byteAddStr(byte[] src, byte[] add, int start, int end) {

		byte[] finbyte = new byte[src.length + start - end];
		System.arraycopy(src, 0, finbyte, 0, src.length);
		System.arraycopy(add, start, finbyte, src.length, end);
		return finbyte;
	}
	
	

	//===========================================================短信发送处理===================================================
	/**
	 * Purpose      : 共用接口发送短信处理 
	 * @param sms
	 * @return 
	 */
	public int SmsSend(SmsBean sms) {
		// 变量定义
		int result = 0;
		//数据库操作
		HashMap hs=new HashMap();
		//核心组装MSISDN--2010-03-29
		String sercode=sms.getServercode();
		if(!sercode.equals("")){
			//服务代码非空时,需要回复应答信息,生成自动序列
			String smsisdn=baseSqlMapDAO.sequences("t_sms_msisdn");//8位(年月日)+8位自增序列
			smsisdn=sercode+smsisdn.substring(10);//+8,2位服务编号+取自增加序列后6位
			sms.setMsisdn(smsisdn);
		}
		
		ArrayList telList = sms.getMobileNoList();
		for (int i = 0; i < telList.size(); i++) {
			String pmobile = telList.get(i).toString();
			hs.put("pmobile", pmobile);//手机号,特殊处理
			hs.put("pname", sms.getName());//
			hs.put("puserid", sms.getUserid());
			hs.put("psendid", sms.getSendid());
			hs.put("pcontent", sms.getContent());
			hs.put("psrcbillid", sms.getSrcbill());
			hs.put("pmsisdn", sms.getMsisdn());
			hs.put("pservicecode",sercode);
			
			baseSqlMapDAO.insert("sms.insertsendsms", hs);
		}
		//发送短信
		SendMessage(sms);
		//返回结果
		return result;
	}
	
	/**
	 * Purpose      : 发送短信共用接口与API结合使用
	 * @param sms
	 * @return 
	 */
	public int SendMessage(SmsBean sms) {
		// 当短信收发接口为空时重新初始化
		if (myProxy == null) {
			ProBaseConf();
			System.out.println("myProxy为空,重建信息：" + myProxy.toString());
		}
		// 返回结果
		int result = 0;

		// 发送号码
		ArrayList telList = sms.getMobileNoList();
		dest_Terminal_Id = new String[telList.size()];
		for (int i = 0; i < telList.size(); i++) {
			dest_Terminal_Id[i] = telList.get(i).toString();
		}
		// 存活有效期
		valid_Time = new Date(System.currentTimeMillis() + (long) 0xa4cb800); //
		// 定时发送时间
		at_Time = null;// new Date(System.currentTimeMillis() + (long)
		// 0xa4cb800); //new Date();
		// 用户手机上显示为短消息的主叫号码
		// src_Terminal_Id=src_Terminal_Id+"001";
		//封装回执短信处理机制
		String src_TerminalTmp = src_Terminal_Id;
		//12位(短信接入号)+2位(功能号)+6位(自增序号)
		if (sms.getMsisdn().length() == 8) {
			src_TerminalTmp = src_TerminalTmp + sms.getMsisdn();//组装原发送号
		}
		//对短信内容进行处理
		try {
			byte[] messageUCS2;
			messageUCS2 = sms.getContent().getBytes("UnicodeBigUnmarked");

			int messageUCS2Len = messageUCS2.length;
			//长短信长度
			int maxMessageLen = 120;//因为最后留[国通科技]占10个汉字.

			//长短信发送
			tp_Udhi = 1;
			msg_Fmt = 0x08;//格式gbk

			int messageUCS2Count = 0;
			if (messageUCS2Len % (maxMessageLen - 6) == 0)
				messageUCS2Count = messageUCS2Len / (maxMessageLen - 6);
			else
				messageUCS2Count = messageUCS2Len / (maxMessageLen - 6) + 1;
			//长短信分为多少条发送
			byte[] tp_udhiHead = new byte[6];
			tp_udhiHead[0] = 0x05;
			tp_udhiHead[1] = 0x00;
			tp_udhiHead[2] = 0x03;
			tp_udhiHead[3] = 0x0A;
			tp_udhiHead[4] = (byte) messageUCS2Count;
			tp_udhiHead[5] = 0x01;

			pk_Total = messageUCS2Count;
			//默认为第一条
			for (int i = 0; i < messageUCS2Count; i++) {
				tp_udhiHead[5] = (byte) (i + 1);
				pk_Number = i + 1;
				if (i != messageUCS2Count - 1) {
					//不为最后一条
					msg_Content = byteAdd(tp_udhiHead, messageUCS2, i
							* (maxMessageLen - 6), (i + 1)
							* (maxMessageLen - 6));
				} else {
					msg_Content = byteAdd(tp_udhiHead, messageUCS2, i
							* (maxMessageLen - 6), messageUCS2Len);
				}
				// 初始化提交信息
				CMPPSubmitMessage submitMsg = new CMPPSubmitMessage(pk_Total,
						pk_Number, registered_Delivery, msg_Level, service_Id,
						fee_UserType, fee_Terminal_Id, tp_Pid, tp_Udhi,
						msg_Fmt, msg_Src, fee_Type, fee_Code, valid_Time,
						at_Time, src_TerminalTmp, dest_Terminal_Id,
						msg_Content, reserve);

				CMPPSubmitRepMessage submitRepMsg = (CMPPSubmitRepMessage) myProxy
						.send(submitMsg);

				if (submitRepMsg != null) {
					result = 1;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}
	//===========================================================短信接收处理===================================================

	
	/**
	 * Purpose      : 接收短信,转发处理===通过SMProxyRec这个接口实现
	 * @param msg 
	 */
	public void ProcessRecvDeliverMsg(CMPPMessage msg) {
		String srcmsisdn ="" ;  //源终端号(信息机给客户的长号)
		String mobile = "";     //上行手机号
		String content = "";    //上行内容
		CMPPDeliverMessage deliverMsg = (CMPPDeliverMessage) msg;
		
		//正常接收
		if (deliverMsg.getRegisteredDeliver() == 0)
			try {
				log.debug("deliverMsg.getRegisteredDeliver() == 0");
				System.out.println("deliverMsg.getRegisteredDeliver() == 0");
				
				srcmsisdn=deliverMsg.getDestnationId();//源终端号(信息机给客户的长号)
				//测试用例
				srcmsisdn="106573041123";//直接上行网关
				//srcmsisdn="10657304112312123456";//12指令
				//srcmsisdn="10657304112313123456";//13指令
				// 编号方式(GBK编码)
				if (deliverMsg.getMsgFmt() == 8) {
					log.debug("deliverMsg.getMsgFmt() == 8");
					log.debug(String.valueOf(String.valueOf((new StringBuffer(
							"接收消息: 主叫号码=")).append(
							deliverMsg.getSrcterminalId()).append(";内容=")
							.append(
									new String(deliverMsg.getMsgContent(),
											"UTF-16BE")))));

					mobile = deliverMsg.getSrcterminalId();
					mobile = mobile.substring(0, 11);
					content = new String(deliverMsg.getMsgContent(),
							"UTF-16BE");
				}
				// 编号方式非GBK
				else {
					log.debug("deliverMsg.getMsgFmt()<> 8");
					log.debug(String.valueOf(String.valueOf((new StringBuffer(
							"接收消息: 主叫号码=")).append(
							deliverMsg.getSrcterminalId()).append(";内容=")
							.append(new String(deliverMsg.getMsgContent())))));

					mobile = deliverMsg.getSrcterminalId();
					mobile = mobile.substring(0, 11);
					content = new String(deliverMsg.getMsgContent());
				}

				//去除接收的回车,换行空格等信息.
				Pattern p = Pattern.compile("\t|\r|\n");
				Matcher mat = p.matcher(content.toString());
				content = mat.replaceAll("");
				
				//====================================================
				//源终端号(信息机给客户的长号)分析
				//前缀,流水号
				String servercode="";
				String msisdn="";
				if(srcmsisdn.length()==20){
					servercode=srcmsisdn.substring(12,14);//服务编号
					msisdn=srcmsisdn.substring(12);//2位服务编号+6位流水号
				}else{
					
				}
				//=============调用SmsRec接收转发处理=================
				SmsBean sms=new SmsBean();
				sms.setContent(content);
				sms.setMsisdn(msisdn);
				sms.setServercode(servercode);
				//进一步,将手机封装入bean中.
				ArrayList mobileList = new ArrayList();
				mobileList.add(mobile);
				sms.setMobileNoList(mobileList);
				//进入反射处理
				SmsRec(mobile,sms);
				//===================================================			
			} catch (Exception e) {
				e.printStackTrace();
			}
		//异常接收
		else {
			log.debug("deliverMsg.getRegisteredDeliver() <> 0");
			log.debug(String.valueOf(String.valueOf((new StringBuffer(
					"收到状态报告消息： stat="))
					.append(new String(deliverMsg.getStat())).append(
							"dest_termID=").append(
							new String(deliverMsg.getDestTerminalId())).append(
							";destterm=").append(
							new String(deliverMsg.getDestnationId())).append(
							";serviceid=").append(
							new String(deliverMsg.getServiceId())).append(
							";tppid=").append(deliverMsg.getTpPid()).append(
							";tpudhi=").append(deliverMsg.getTpUdhi()).append(
							";msgfmt").append(deliverMsg.getMsgFmt()).append(
					// ";内容").append(new
							// String(deliverMsg.getMsgContent())).append(
							";srctermid=").append(
							new String(deliverMsg.getSrcterminalId())).append(
							";deliver=").append(
							deliverMsg.getRegisteredDeliver()))));
		}
	}
	
	/**
	 * Purpose      : 短信接收处理,包括数据库操作与反射机制的处理
	 */
	private void SmsRec(String mobile,SmsBean sms){
		//数据库操作(记录数据)
		HashMap hs=new HashMap();
		hs.put("pmobile", mobile);//手机号,特殊处理
		hs.put("pcontent", sms.getContent());
		hs.put("pmsisdn", sms.getMsisdn());
		hs.put("pservicecode", sms.getServercode());
		baseSqlMapDAO.insert("sms.insertinceptsms", hs);
		//反射机制
		//数据库查找相关的的类
		hs.clear();
		hs.put("pservicecode", sms.getServercode());
		List ls=baseSqlMapDAO.queryForList("sms.findconfigsms", hs);
		//一行记录
		String clazz="";
		String method="";
		if(ls!=null&&ls.size()>0){
		HashMap rs=(HashMap)ls.get(0);
			 clazz=rs.get("CLAZZ").toString();
			 method=rs.get("METHOD").toString();
		}
		//
		execMethod(clazz,method,sms);//反射调用
	}
	

	//===========================================================短信销毁处理===================================================
	/**
	 * 终端结束程序
	 */
	public void Terminate() {
		log.debug("SMC下发终断消息");
		myProxy.close();
		myProxy = null;
	}

	/**
	 * 关闭连接
	 */
	public void Close() {
		// 查询SMProxy与ISMG的TCP连接状态
		String stateDesc = myProxy.getConnState();
		log.debug("数据连接状态：" + stateDesc);
		// 退出
		myProxy.close();
		myProxy = null;
	}

	/**
	 * @return the baseSqlMapDAO
	 */
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	/**
	 * @param baseSqlMapDAO
	 *            the baseSqlMapDAO to set
	 */
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	//===========================================================类反射===================================================
	/**
	 * Purpose         : 用反射的方法,取得类-->方法及参数值
	 * @param clazz   : 类名
	 * @param method  : 方法名
	 * @param bean    : 参数名
	 * @return        : 是否成功
	 */
	private String execMethod(String clazz,String method,SmsBean bean){
		String result="";
		//初始化数据处理
		if(clazz.equals("")){
			clazz="MessageNotifyHandlerImpl";
		}
		if(method.equals("")){
			method="handle";
		}
		
		clazz = "com.qzgf.utils.sms.notify."+clazz;//限定接收类在该包下
		try {
			//加载类
			//object clzz= Class.forName(clazz).newInstance();
			NotifyHandler clzz=null;
			clzz=notifyHandlers.get(clazz);
			if(clzz==null){
				clzz= (NotifyHandler)Class.forName(clazz).newInstance();//实例化
				//放入缓冲池中
				notifyHandlers.put(clzz.getClass().getName(), clzz);
				//设置参数
				clzz.setBaseSqlMapDAO(baseSqlMapDAO);
				clzz.setSendmsm(this);
			}
			//设置参数(扩展,由于传送对象的内容可能变化,所以必须重置)
			clzz.setSmsbean(bean);
			//System.out.println("clzz.getClass().getName():"+clzz.getClass().getName()+"clzz.getClass().getDeclaredMethods().toString():"+clzz.getClass().getDeclaredMethods().toString());
		    //========================================
			Class[] c = null;  //bean.getClass()    SmsBean.class
			//c[0]=bean.getClass();(这个是传参数)
			//加载方法
			Method m=clzz.getClass().getMethod(method, c);
			
			//反射取得该方法
			Object[] o = null;//方法返回值的对象
			//result=(String) m.invoke(clzz.getClass(), o);
			//result=(String) m.invoke(clzz, bean);(这个是传参数)
			result=(String) m.invoke(clzz, o);

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}

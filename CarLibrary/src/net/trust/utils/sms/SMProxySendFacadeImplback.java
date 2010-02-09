/**
 * 说明：网关短信处理主类。
 * 		 接收 
 * 			ProcessRecvDeliverMsg（CMPPMessage msg）接收短信
 * 		 发送 
 * 			SendMessage（SmsBean sms）		仅处理短信的发送
 * 			CarLibrarySendSms（SmsBean sms）车辆管理专用
 * 时间：2008-12-16
 */
package net.trust.utils.sms;

// 基本类的方法导入
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List; // 华为软件包
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.huawei.insa2.comm.cmpp.message.*;
import com.huawei.insa2.util.*; // 日志
import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory; // 数据库操作包
import net.trust.IbatisDaoTools.BaseSqlMapDAO;

public class SMProxySendFacadeImplback implements SMProxySendFacade {
	// 从xml中读取配置信息
	private  Args argsconn; // 读取连接信息
	private  Args argssumbit; // 读取提交信息
	// 基本提交信息参数说明
	private  int pk_Total = 1;
	private  int pk_Number = 1;
	private  int registered_Delivery = 1;
	private  int msg_Level = 1;
	private  String service_Id = "04545";
	private  int fee_UserType = 2;
	private  String fee_Terminal_Id = "1065730495561";
	private  int tp_Pid = 0;
	private  int tp_Udhi = 0;
	private  int msg_Fmt = 8;
	private  String msg_Src = "913841";
	private  String fee_Type = "02";
	private  String fee_Code = "000";
	private  Date valid_Time = null;
	private  Date at_Time = null;
	private  String src_Terminal_Id = "1065730495561";
	private  String[] dest_Terminal_Id = { "13599204724" };
	private  byte[] msg_Content = null;
	private  String reserve = "";//保留项

	/** 短信收发接口 */
	public static SMProxyRec myProxy = null;

	// 数据库操作类
	public BaseSqlMapDAO baseSqlMapDAO;
	// 日志记录
	private static Log log = LogFactory.getLog(SMProxySendFacadeImplback.class);
    //是否接收审核
	//private boolean flowerswitch;
	// 基本参数设置
	private void ProBaseConf() {
		try {
			log.debug("初始化连接信息");
			// 连接配置信息
			argsconn = new Cfg("Smproxy.xml", false).getArgs("CMPPConnect");
			// argsconn.set("source-addr", "913841");
			// argsconn.set("shared-secret", "pkswd904");
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
			fee_Terminal_Id = argssumbit
					.get("fee_Terminal_Id", "1065730495561");
			tp_Pid = argssumbit.get("tp_Pid", 1);
			tp_Udhi = argssumbit.get("tp_Udhi", 1);
			msg_Fmt = argssumbit.get("msg_Fmt", 8);
			msg_Src = argssumbit.get("msg_Src", "913841");
			fee_Type = argssumbit.get("fee_Type", "02");
			fee_Code = argssumbit.get("fee_Code", "000");
			src_Terminal_Id = argssumbit
					.get("src_Terminal_Id", "1065730495561");
			reserve = argssumbit.get("reserve", "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 构造方法
	 */
	public SMProxySendFacadeImplback() {
		// 处理基本的配置信息
		ProBaseConf();
	}  
	
	 /** *//***
	  * * 功能：
	  *     将src里的字节与add里的从start开始到end（不包括第end个位置)的字节串连在一起返回
	  * @param src
	  * @param add
	  * @param start    add 开始位置
	  * @param end      add 的结束位置(不包括end位置)
	  * @return 也即实现类似String类型的src+add.subString(start,end)功能
	  */
	    public static byte[]byteAdd(byte[]src, byte[]add, int start, int end) 
	    {
	        byte[]dst = new byte[src.length + end - start];
	        for (int i = 0; i < src.length; i ++ ) 
	        {
	            dst[i] = src[i];
	        }
	        for (int i = 0; i < end - start; i ++ ) 
	        {
	            dst[src.length + i] = add[start + i];
	        }
	        return dst;
	    }
	    //升级对字节数组的组合处理2009-04-10
	    public static byte[]byteAddStr(byte[]src, byte[]add, int start, int end) 
	    {

	    	  byte[] finbyte=new byte[src.length+start-end];
	    	  System.arraycopy(src,0,finbyte,0,src.length);   
	    	  System.arraycopy(add,start,finbyte,src.length,end); 
	    	  return finbyte;
	    }

	/**
	 * 华为短信发送类处理（公用类）
	 * 
	 * @param 短信类，包括手机号，内容，序号
	 */
	public int SendMessage(SmsBean sms) {
		// 当短信收发接口为空时重新初始化
		
		if (myProxy == null) {
			System.out.println("myProxy==null");
			ProBaseConf();
			System.out.println("myProxy信息：" + myProxy.toString());
		}
		// System.out.println("myProxy.getConnState():"+myProxy.getConnState().toString());
		// 返回结果
		int result = 0;

		// 发送号码
		ArrayList telList = sms.getMobileNoList();
		dest_Terminal_Id = new String[telList.size()];
		for (int i = 0; i < telList.size(); i++) {
			dest_Terminal_Id[i] = telList.get(i).toString();
		}
		// 存活有效期
		valid_Time = new Date(System.currentTimeMillis() + (long) 0xa4cb800); // new
		// Date();//
		// 定时发送时间
		at_Time = null;// new Date(System.currentTimeMillis() + (long)
		// 0xa4cb800); //new Date();
		// 用户手机上显示为短消息的主叫号码
		// src_Terminal_Id=src_Terminal_Id+"001";
		//封装回执短信处理机制
		String src_TerminalTmp=src_Terminal_Id;
		if(sms.getSn().length()==8){
			src_TerminalTmp=src_TerminalTmp+sms.getSn();//组装原发送号
		}
		try {
			
			byte[]messageUCS2;
            messageUCS2 = sms.getSmsContent().getBytes("UnicodeBigUnmarked");
           
            int messageUCS2Len = messageUCS2.length;
            //长短信长度
            int maxMessageLen = 120;//因为最后留[国通科技]占10个汉字.

                //长短信发送
                tp_Udhi= 1;
                msg_Fmt = 0x08;
                
                int messageUCS2Count=0;
                if(messageUCS2Len % (maxMessageLen - 6)==0)
                	messageUCS2Count = messageUCS2Len / (maxMessageLen - 6);
                else
                	messageUCS2Count = messageUCS2Len / (maxMessageLen - 6) + 1;
                //长短信分为多少条发送
                byte[]tp_udhiHead = new byte[6];
                tp_udhiHead[0] = 0x05;
                tp_udhiHead[1] = 0x00;
                tp_udhiHead[2] = 0x03;
                tp_udhiHead[3] = 0x0A;
                tp_udhiHead[4] = (byte)messageUCS2Count;
                tp_udhiHead[5] = 0x01;
                
    			pk_Total=messageUCS2Count;
                //默认为第一条
                for (int i = 0; i < messageUCS2Count; i ++ ) 
                {
                    tp_udhiHead[5] = (byte)(i + 1);
                    pk_Number=i+1;
                    if (i != messageUCS2Count - 1) 
                    {
                        //不为最后一条
                    	msg_Content = byteAdd(tp_udhiHead, messageUCS2, i * (maxMessageLen - 6), (i + 1) * (maxMessageLen - 6));
                    }
                    else 
                    {
                    	msg_Content = byteAdd(tp_udhiHead, messageUCS2, i * (maxMessageLen - 6), messageUCS2Len);
                    }
                    // 初始化提交信息
    				CMPPSubmitMessage submitMsg = new CMPPSubmitMessage(pk_Total,
    						pk_Number, registered_Delivery, msg_Level, service_Id,
    						fee_UserType, fee_Terminal_Id, tp_Pid, tp_Udhi,
    						msg_Fmt, msg_Src, fee_Type, fee_Code, valid_Time,
    						at_Time, src_TerminalTmp, dest_Terminal_Id,
    						msg_Content, reserve);

    				CMPPSubmitRepMessage submitRepMsg = (CMPPSubmitRepMessage) myProxy.send(submitMsg);
    				
    				 if (submitRepMsg!= null) {
    					   result = 1;
    				   }
                }	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}
		return result;
	}

	/**
	 * 车辆管理专用（暂缓）
	 * 
	 * @param sms
	 */
	public int CarLibrarySendSms(SmsBean sms) {
		// 发送短信
		int i = SendMessage(sms);
		// 数据库中增加记录
		try {
			String sendSmsId = baseSqlMapDAO.sequences("send_sms_id");// 序号

			HashMap param = new HashMap();
			param.put("sendSmsId", sendSmsId); // 流水号
			param.put("callPhone", sms.getMobileNoList().get(0).toString()); // 接收者手机号码
			param.put("sendContent", sms.getSmsContent().toString());// 短信内容
			param.put("sourceOrderType", "1"); // 原单类型
			baseSqlMapDAO.insert("SmsManage.insertSendSmsRecord", param);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}

	/**
	 * 对于MyProxy分发短信的接收 接收短信后的处理,现在的情况是通过网关方式处理。编码是utf-8
	 * 
	 * @param msg
	 */
	public void ProcessRecvDeliverMsg(CMPPMessage msg) {
		String reqphoto = ""; // 请求调车人的手机号
		String reqcontent = ""; // 请求内容


		String smsType="3";
		
		 String per="";
		
		String srcBillNoState="";//原单状态
		String srcBillNo="";//原单号
		CMPPDeliverMessage deliverMsg = (CMPPDeliverMessage) msg;
		if (deliverMsg.getRegisteredDeliver() == 0)
			try {
				log.debug("deliverMsg.getRegisteredDeliver() == 0");
				System.out.println("deliverMsg.getRegisteredDeliver() == 0");
				// 编号方式
     			if (deliverMsg.getMsgFmt() == 8) {
					log.debug("deliverMsg.getMsgFmt() == 8");
					log.debug(String.valueOf(String.valueOf((new StringBuffer(
							"接收消息: 主叫号码=")).append(
							deliverMsg.getSrcterminalId()).append(";内容=")
							.append(
									new String(deliverMsg.getMsgContent(),
											"UTF-16BE")))));

					reqphoto = deliverMsg.getSrcterminalId();
					reqphoto = reqphoto.substring(0,11);
					reqcontent = new String(deliverMsg.getMsgContent(),
							"UTF-16BE");
				}
				// 编号方式GBK
				else {
					log.debug("deliverMsg.getMsgFmt()<> 8");
					log.debug(String.valueOf(String.valueOf((new StringBuffer(
							"接收消息: 主叫号码=")).append(
							deliverMsg.getSrcterminalId()).append(";内容=")
							.append(new String(deliverMsg.getMsgContent())))));

					reqphoto = deliverMsg.getSrcterminalId();
					reqphoto = reqphoto.substring(0,11);
					reqcontent = new String(deliverMsg.getMsgContent());
				}
     			//去除接收的回车,换行空格等信息.
    			Pattern p = Pattern.compile("\t|\r|\n");
    			Matcher mat = p.matcher(reqcontent.toString());
    			reqcontent=mat.replaceAll("");
				//对原信息进行组装。
			    String srcter = deliverMsg.getDestnationId();//得到源终端号。
			    //System.out.println(srcter);
				if (srcter.length() ==20) {//接收到大于18位时 && && (reqcontent.equals("1") || reqcontent.equals("2"))
                    per=srcter.substring(12,14);//前缀
					String msisdnId= srcter.substring(12);//Msis单号
					List auditMsg = baseSqlMapDAO.queryForList(
							"SmsManage.findAuditMsgbyMsisdn",msisdnId);
					if (auditMsg.size() > 0) {
						HashMap auditMsgHs = (HashMap) auditMsg.get(0);

						srcBillNo = auditMsgHs.get("expediteapply_id")
								.toString();
						if(per.equals("00")){//如果是派车审批处理
							Integer tmp = (Integer.parseInt(auditMsgHs.get(
									"use_state").toString()) - 4);
							srcBillNoState = tmp.toString();
						}
						reqcontent = srcBillNo + "|" + srcBillNoState + "||" + reqcontent;

					}
				    //System.out.println(srcBillNo+":"+srcBillNoState+":"+reqcontent);
				}
			    
				// 1.接收的短信，写入数据库
				log.debug("1.接收的短信，写入数据库");
				HashMap param = new HashMap();
				param.clear();
				param.put("phoneCode", reqphoto); // 接收者手机号码
				param.put("smsContent", reqcontent); // 短信内容
				param.put("prefix", per);
				baseSqlMapDAO.insert("SmsManage.insertInceptSms", param); // 数据库操作
				//用户类型
				SmsBean smsBeanAdmin = new SmsBean();
				//短信发送是否成功标识 
				int smsFlag=0;
				// 转入存储过程处理(接收的审批)
				if (reqcontent.split("\\|").length>1) {
				   //查询需要发送的人员   
				   List smsAudls=baseSqlMapDAO.queryForList("SmsManage.prosmsLongRec", param);
				   String smsAudcontent="";
				   ArrayList smsAudmobileList = new ArrayList();//存储电话数组
				   HashMap smsAudhs =new HashMap();//存储查询返回的用户信息
				   int audsize=smsAudls.size();//将List转化为HashMap
				   for (int m=0;m<audsize;m++)
				   {
					        //smsFlag=1;
							// 设置发送的人员
							smsAudmobileList.clear();// 清除原有短信
							smsAudhs.clear();
							smsAudhs = (HashMap) smsAudls.get(m);
							smsAudmobileList.add(smsAudhs.get("mobile").toString());
							smsAudcontent = smsAudhs.get("content").toString();
							String msisdnId="";
							if(smsAudhs.get("msistype").toString().equals("1"))
							{
								msisdnId=baseSqlMapDAO.sequencesmsisdn("msisdn_id");
							}
						    smsBeanAdmin.setSn(msisdnId);//组装
							smsBeanAdmin.setMobileNoList(smsAudmobileList);
							smsBeanAdmin.setSmsContent(smsAudcontent);

							// 3.发送给管理人员
							log.debug("2.发送审批提醒给相关人员");
							try{
								smsFlag = SendMessage(smsBeanAdmin);
							}catch(Exception e){
								System.out.println(e.toString());
							}
							// 4.更新发送短信列表
							String sendSmsId = baseSqlMapDAO.sequences("send_sms_id");
							param.clear();

							smsType = smsAudhs.get("smstype").toString();// 审批提醒
							
							param.put("sendSmsId", sendSmsId); //接收者手机号码
							param.put("callPhone", smsAudhs.get("mobile").toString()); //接收者手机号码
							param.put("callManName", smsAudhs.get("smsstaffname").toString()); //短信员工
							param.put("sendContent", smsAudcontent); // 短信内容
							param.put("staffId", smsAudhs.get("staffId").toString()); //员工信息Id
							param.put("callStaffId", ""); // 接收者手机号码
							param.put("cityId", smsAudhs.get("cityid").toString()); // 所属城市
							param.put("sourceOrderType", smsType); //短信类型，短信提醒机制
							param.put("sourceOrderCode", srcBillNo); //短信类型，短信提醒机制
							param.put("msisdnId", msisdnId);//原单类型
							baseSqlMapDAO.insert("SmsManage.insertSendSmsRecord", param); //数据库操作
							//if (smsFlag == 1) {
								param.clear();
								param.put("sendSmsId", sendSmsId); // 短信时间
								baseSqlMapDAO.update("SmsManage.updateSendSmsState", param); //数据库操作
							//}
						}
				   }
				 //接收短信，及发送给管理人员(转入转发处理机制)
				 else {
					   //处理转发的
					   List transmitls=baseSqlMapDAO.queryForList("SmsManage.prosmsShortRec", param);
					   String transmitcontent="";
					   ArrayList transmitmobileList = new ArrayList();//存储电话数组
					   HashMap transmiths =new HashMap();//存储查询返回的用户信息
					   int audsize=transmitls.size();//将List转化为HashMap
					   for (int m=0;m<audsize;m++)
					   {
						        //smsFlag=1;
								// 设置发送的人员
						        transmitmobileList.clear();// 清除原有短信
						        transmiths.clear();
						        transmiths = (HashMap) transmitls.get(m);
						        transmitmobileList.add(transmiths.get("mobile").toString());
						        transmitcontent = transmiths.get("content").toString();
							    smsBeanAdmin.setSn("");//组装
								smsBeanAdmin.setMobileNoList(transmitmobileList);
								smsBeanAdmin.setSmsContent(transmitcontent);

								// 3.发送给管理人员
								log.debug("2.发送审批提醒给相关人员");
								try{
									smsFlag = SendMessage(smsBeanAdmin);
								}catch(Exception e){
									System.out.println(e.toString());
								}
								// 4.更新发送短信列表
								String sendSmsId = baseSqlMapDAO.sequences("send_sms_id");
								param.clear();

								smsType = transmiths.get("smstype").toString();// 短信类型(转发机制3)
								
								param.put("sendSmsId", sendSmsId); // 接收者手机号码
								param.put("callPhone", transmiths.get("mobile").toString()); // 接收者手机号码
								param.put("callManName", transmiths.get("smsstaffname").toString()); //短信员工
								param.put("sendContent", transmitcontent); // 短信内容
								param.put("staffId", transmiths.get("staffId").toString()); //员工信息Id
								param.put("callStaffId", ""); // 接收者手机号码
								param.put("cityId", transmiths.get("cityid").toString()); //所属城市
								param.put("sourceOrderType", smsType); // 短信类型，短信提醒机制
								param.put("sourceOrderCode", ""); // 短信类型，短信提醒机制
								baseSqlMapDAO.insert("SmsManage.insertSendSmsRecord", param); // 数据库操作
								//if (smsFlag == 1) {
									param.clear();
									param.put("sendSmsId", sendSmsId); // 短信时间
									baseSqlMapDAO.update("SmsManage.updateSendSmsState", param); // 数据库操作
								//}
							}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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
}

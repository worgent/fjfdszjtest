/**
 * 说明：网关短信处理主类。
 * 		 接收 
 * 			ProcessRecvDeliverMsg（CMPPMessage msg）接收短信
 * 		 发送 
 * 			SendMessage（SmsBean sms）		仅处理短信的发送
 * 			CarLibrarySendSms（SmsBean sms）车辆管理专用
 * 时间：2008-12-16
 * 修改时间:2009-12-25
 * 说明:采用API方式发送短信
 *      
 */
package net.trust.utils.sms;

// 基本类的方法导入
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.jasson.im.api.APIClient;
import com.jasson.im.api.MOItem;

public class SMProxySendFacadeImplTimeTask implements SMProxySendFacade {
	private String mobileStr = "13599204724";//手机号可用逗号分开
	private String content = "短信发送测试内容 via  IM JAVA API ";//测试的短信内容
	private long smId = 1;										  //手机显示来电的尾号
	private int smType = 0;									  //0:默认为短信,1:wap方式等
	private String url = "wap.myself.com";//"wap.sohu.com";      wap方式用到的网址
	private String host = "113.18.101.88";//嘉信信息机的ip
	private String dbName = "mas";        //数据库名称
	//正式
//	private String apiId = "car";         //信息机中定义的API接口,关键字
//	private String name = "car";          //信息机中API接口的用户名
//	private String pwd = "gtcar";		   //信息机中API接口的密码
	//调试
	private String apiId = "debugcar";         //信息机中定义的API接口,关键字
	private String name = "debugcar";          //信息机中API接口的用户名
	private String pwd = "debugcar";		   //信息机中API接口的密码
	
	private APIClient handler = new APIClient();//客调用嘉信api处理类

	// 数据库操作类
	public BaseSqlMapDAO baseSqlMapDAO;
	// 日志记录
	private static Log log = LogFactory.getLog(SMProxySendFacadeImplTimeTask.class);

	//线程任务用于接收短信2009-12-28
	java.util.Timer timer;//
	RecTask rt=new RecTask();
	//是否接收审核
	//private boolean flowerswitch;
	// 基本参数设置
	private void ProBaseConf() {
		System.out.println("初始化连接信息");
		log.debug("初始化连接信息\n");
		// 连接配置信息
		int connectRe = handler.init(host, name, pwd, apiId, dbName);
		if (connectRe == APIClient.IMAPI_SUCC)
		{
			System.out.println("初始化成功");
			log.debug("初始化成功\n");
		}
		else if (connectRe == APIClient.IMAPI_CONN_ERR)
			log.debug("连接失败\n");
		else if (connectRe == APIClient.IMAPI_API_ERR)
			log.debug("apiID不存在\n");
	}

	/**
	 * 构造方法
	 */
	public SMProxySendFacadeImplTimeTask() {
		// 处理基本的配置信息
		ProBaseConf();
		//启动接收短信线程
		//rt=new RecTask();
		timer = new java.util.Timer(true);
		//延迟0秒钟,周期10秒钟
		timer.schedule(rt, 0, 10 * 1000);
	}

	/**
	 * 释放连接
	 */
	public void release() {
		handler.release();
		Thread.currentThread().interrupt();
	}

	//2009-12-24整型值的判断处理
	public int getInt(String str) {
		int ret = Integer.MIN_VALUE;
		try {
			ret = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			ret = Integer.MIN_VALUE;
		}
		return ret;
	}

	/**
	 * 华为短信发送类处理（公用类）
	 * 
	 * @param 短信类，包括手机号，内容，序号
	 */
	public int SendMessage(SmsBean sms) {
		int resultcol = 0;
		// 当短信收发接口为空时重新初始化
		if (handler == null) {
			System.out.println("handler==null");
			ProBaseConf();
			System.out.println("handler信息：" + handler.toString());
		}

		// 发送号码,数组
		ArrayList telList = sms.getMobileNoList();
		String[] dest_Terminal_Id = new String[telList.size()];
		for (int i = 0; i < telList.size(); i++) {
			dest_Terminal_Id[i] = telList.get(i).toString();
		}

		//组装源手机号,即:短信接入号+业务号
		String tmpSrcID = null;
		if (sms.getSn().length() == 6) {
			//组装成6位,1位命令字+5位自动增加的.
			tmpSrcID = sms.getSn();//组装原发送号
		}
		if (tmpSrcID == null || tmpSrcID.trim().length() == 0
				|| getInt(tmpSrcID.trim()) == Integer.MIN_VALUE) {
			tmpSrcID = "" + smId;
			//tmpSrcID = "";
		}
		//短信内容
		String tmpContent = sms.getSmsContent();
		//日志提示
		System.out.println("短信发送,手机号:"+dest_Terminal_Id.toString()+"短信尾号Long:"+Long.parseLong(tmpSrcID)+"内容:"+tmpContent);
		log.debug("短信发送,手机号:"+dest_Terminal_Id.toString()+"短信尾号Long:"+Long.parseLong(tmpSrcID)+"内容:"+tmpContent+"\n");
		//发送短信,非push wap方式
		int result = handler.sendSM(dest_Terminal_Id, tmpContent, smId, Long
				.parseLong(tmpSrcID));

		if (result == APIClient.IMAPI_SUCC) {
			System.out.println("发送成功");
			log.debug("发送成功\n");
			resultcol = 1;
		} else if (result == APIClient.IMAPI_INIT_ERR)
			log.debug("未初始化");
		else if (result == APIClient.IMAPI_CONN_ERR)
			log.debug("数据库连接失败");
		else if (result == APIClient.IMAPI_DATA_ERR)
			log.debug("参数错误");
		else if (result == APIClient.IMAPI_DATA_TOOLONG)
			log.debug("消息内容太长");
		else if (result == APIClient.IMAPI_INS_ERR)
			log.debug("数据库插入错误");
		else
			log.debug("出现其他错误");
		return resultcol;
	}
	
	//开启接收线程
	class RecTask extends TimerTask
	{
		long interval = 2000L;//时间间隔,每隔2秒钟
		public RecTask()
		{
		}
		public void run()
		{
			//关闭线程
			timer.cancel();
			//接收短信
			recvSM();
			//启动线程延迟10秒,周期10秒.
			timer.schedule(rt, 10 * 1000, 10 * 1000);
		}
	}
	
	/**
	 * 接收短信
	 */
	public void recvSM() {
		// 当短信收发接口为空时重新初始化
		if (handler == null) {
			System.out.println("handler==null");
			ProBaseConf();
			System.out.println("handler信息：" + handler.toString());
		}
		MOItem[] mos = handler.receiveSM();
		if (mos == null) {
			log.debug("未初始化或接收失败\n");
			return;
		} else if (mos.length != 0) {
			RecPro(mos);
		}
	}

	/**
	 * 短信处理方法
	 * @param msg
	 * @param mos
	 */
	public void RecPro(MOItem[] mos) {
		String reqphoto = ""; // 请求调车人的手机号
		String reqcontent = ""; // 请求内容

		String smsType = "3";

		String per = "";//1派车审请,2,客户反馈

		String srcBillNoState = "";//原单状态
		String srcBillNo = "";//原单号

		int len = 0, i = 0;
		len = mos.length;
		while (i < len) {
			//调试信息处理
			System.out.println("短信接收的内容"+mos[i].getContent()+"手机"+mos[i].getMobile()+"时间"+mos[i].getMoTime()+"smid"+mos[i].getSmID());
			log.debug("短信接收的内容"+mos[i].getContent()+"手机"+mos[i].getMobile()+"时间"+mos[i].getMoTime()+"smid"+mos[i].getSmID());
			//以下是正常处理
			reqphoto = mos[i].getMobile();
			//reqphoto = reqphoto.substring(0, 11);
			reqcontent = mos[i].getContent();
			//去除接收的回车,换行空格等信息.
			Pattern p = Pattern.compile("\t|\r|\n");
			Matcher mat = p.matcher(reqcontent.toString());
			reqcontent = mat.replaceAll("");
			//对原信息进行组装。
			String srcter = Long.toString(mos[i].getSmID());
			//System.out.println(srcter);
			if (srcter.length() == 6) {//接收到大于18位时 && && (reqcontent.equals("1") || reqcontent.equals("2"))
				per = srcter.substring(0, 1);//前缀,命令字位
				String msisdnId = srcter;//Msis单号
				List auditMsg = baseSqlMapDAO.queryForList(
						"SmsManage.findAuditMsgbyMsisdn", msisdnId);
				if (auditMsg.size() > 0) {
					HashMap auditMsgHs = (HashMap) auditMsg.get(0);

					srcBillNo = auditMsgHs.get("expediteapply_id").toString();
					if (per.equals("1")) {//如果是派车审批处理
						Integer tmp = (Integer.parseInt(auditMsgHs.get(
								"use_state").toString()) - 4);
						srcBillNoState = tmp.toString();
					}
					reqcontent = srcBillNo + "|" + srcBillNoState + "||"
							+ reqcontent;
				}
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
			int smsFlag = 0;
			// 转入存储过程处理(接收的审批)
			if (reqcontent.split("\\|").length > 1) {
				//查询需要发送的人员   
				List smsAudls = baseSqlMapDAO.queryForList(
						"SmsManage.prosmsLongRec", param);
				String smsAudcontent = "";
				ArrayList smsAudmobileList = new ArrayList();//存储电话数组
				HashMap smsAudhs = new HashMap();//存储查询返回的用户信息
				int audsize = smsAudls.size();//将List转化为HashMap
				for (int m = 0; m < audsize; m++) {
					//smsFlag=1;
					// 设置发送的人员
					smsAudmobileList.clear();// 清除原有短信
					smsAudhs.clear();
					smsAudhs = (HashMap) smsAudls.get(m);
					smsAudmobileList.add(smsAudhs.get("mobile").toString());
					smsAudcontent = smsAudhs.get("content").toString();
					String msisdnId = "";
					if (smsAudhs.get("msistype").toString().equals("1")) {
						msisdnId = baseSqlMapDAO.sequencesmsisdn("msisdn_id");
					}
					smsBeanAdmin.setSn(msisdnId);//组装
					smsBeanAdmin.setMobileNoList(smsAudmobileList);
					smsBeanAdmin.setSmsContent(smsAudcontent);

					// 3.发送给管理人员
					log.debug("2.发送审批提醒给相关人员");
					try {
						smsFlag = SendMessage(smsBeanAdmin);
					} catch (Exception e) {
						System.out.println(e.toString());
					}
					// 4.更新发送短信列表
					String sendSmsId = baseSqlMapDAO.sequences("send_sms_id");
					param.clear();

					smsType = smsAudhs.get("smstype").toString();// 审批提醒

					param.put("sendSmsId", sendSmsId); //接收者手机号码
					param.put("callPhone", smsAudhs.get("mobile").toString()); //接收者手机号码
					param.put("callManName", smsAudhs.get("smsstaffname")
							.toString()); //短信员工
					param.put("sendContent", smsAudcontent); // 短信内容
					param.put("staffId", smsAudhs.get("staffId").toString()); //员工信息Id
					param.put("callStaffId", ""); // 接收者手机号码
					param.put("cityId", smsAudhs.get("cityid").toString()); // 所属城市
					param.put("sourceOrderType", smsType); //短信类型，短信提醒机制
					param.put("sourceOrderCode", srcBillNo); //短信类型，短信提醒机制
					param.put("msisdnId", msisdnId);//原单类型
					baseSqlMapDAO
							.insert("SmsManage.insertSendSmsRecord", param); //数据库操作
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
				List transmitls = baseSqlMapDAO.queryForList(
						"SmsManage.prosmsShortRec", param);
				String transmitcontent = "";
				ArrayList transmitmobileList = new ArrayList();//存储电话数组
				HashMap transmiths = new HashMap();//存储查询返回的用户信息
				int audsize = transmitls.size();//将List转化为HashMap
				for (int m = 0; m < audsize; m++) {
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
					try {
						smsFlag = SendMessage(smsBeanAdmin);
					} catch (Exception e) {
						System.out.println(e.toString());
					}
					// 4.更新发送短信列表
					String sendSmsId = baseSqlMapDAO.sequences("send_sms_id");
					param.clear();

					smsType = transmiths.get("smstype").toString();// 短信类型(转发机制3)

					param.put("sendSmsId", sendSmsId); // 接收者手机号码
					param.put("callPhone", transmiths.get("mobile").toString()); // 接收者手机号码
					param.put("callManName", transmiths.get("smsstaffname")
							.toString()); //短信员工
					param.put("sendContent", transmitcontent); // 短信内容
					param.put("staffId", transmiths.get("staffId").toString()); //员工信息Id
					param.put("callStaffId", ""); // 接收者手机号码
					param.put("cityId", transmiths.get("cityid").toString()); //所属城市
					param.put("sourceOrderType", smsType); // 短信类型，短信提醒机制
					param.put("sourceOrderCode", ""); // 短信类型，短信提醒机制
					baseSqlMapDAO
							.insert("SmsManage.insertSendSmsRecord", param); // 数据库操作
					//if (smsFlag == 1) {
					param.clear();
					param.put("sendSmsId", sendSmsId); // 短信时间
					baseSqlMapDAO.update("SmsManage.updateSendSmsState", param); // 数据库操作
					//}
				}
			}
			i++;
		}
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

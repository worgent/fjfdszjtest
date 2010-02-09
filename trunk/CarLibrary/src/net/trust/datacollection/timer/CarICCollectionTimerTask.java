package net.trust.datacollection.timer;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
import net.trust.utils.sms.SMProxySendFacade;
import net.trust.utils.sms.SmsBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CarICCollectionTimerTask extends TimerTask {
	private Log log = LogFactory.getLog(CarICCollectionTimerTask.class);

	private BaseSqlMapDAO baseSqlMapDAO, sqlServerBaseSqlMapDAO; //sqlserver数据连接池配置
	
	private SMProxySendFacade proxysend;
	
	//基本配置信息
	private boolean icswitch=true,outbandswitch=true;
	private int beforehours=7,afterhours=21;

	//读取配置信息
	private void Read_Conf()
	{
		//基本配置信息
		HashMap param = null;
		List systemconfigList = baseSqlMapDAO.queryForList("SmsManage.systemConfig", param);
		Iterator<HashMap> systemconfig = systemconfigList.iterator();
		if (systemconfig.hasNext()) {
			HashMap systemconfig_element = systemconfig.next();
			icswitch=((Boolean)systemconfig_element.get("iccardswitch")).booleanValue();
			outbandswitch=((Boolean)systemconfig_element.get("outbandswitch")).booleanValue();
			beforehours=((Integer)systemconfig_element.get("beforehours"));
			afterhours=((Integer)systemconfig_element.get("afterhours"));
		}	
	}
	
	//构造类
	public CarICCollectionTimerTask(ServletContext servletContext) {
		
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		sqlServerBaseSqlMapDAO = (BaseSqlMapDAO) context.getBean("sqlServerBaseSqlMapDAO");
		
		baseSqlMapDAO = (BaseSqlMapDAO) context.getBean("baseSqlMapDAO");
		//短信处理类
		proxysend=(SMProxySendFacade) context.getBean("smproxySendFacade");

		if (log.isDebugEnabled())
			log.debug("短信采集器已运行");
	}

	//对于异常信息处理，每隔10分钟报警一次。
	@Override
	public void run() {
		//读取相关配置信息
		Read_Conf();
		if(icswitch)
		{
			//IC卡异常处理
			log.debug("开始处理IC卡异常");
			ICCardPro();
		}
		//出区域报警
        if (outbandswitch) {
			// 1.取得当前的时刻0~23
			Calendar cal = Calendar.getInstance();
			int currentlyHour = cal.get(Calendar.HOUR_OF_DAY);

			// 2.取得当前是星期几
			// Date date = new Date();
			// String weekDay = (new SimpleDateFormat("E")).format(date);
			// //取得当前时间为星期几
			// Calendar中1-星期天，2-星期一，3-星期二，4-星期三，5-星期四，6-星期五，7-星期六)
			int currentlyWeek = cal.get(Calendar.DAY_OF_WEEK);

			switch (currentlyWeek) {
			// 周六到周天，每隔10分钟
			case 1:
			case 7:
				log.debug("开始处理周未:越界报警！");
				OutExceptinPro();
				break;
			default:
				// 周一到周五,21点之后处理,晚上9点到白天7点
				if (currentlyHour >= afterhours || currentlyHour <= beforehours) {
					log.debug("开始处理工作日:越界报警!");
					OutExceptinPro();
				}
				break;
			}
		}
	}
	
	//IC卡异常处理
	public void ICCardPro()
	{
		try {
			//查询异常数据
			HashMap param = null;
			List ic_Card_Exceptin = sqlServerBaseSqlMapDAO.queryForList(
					"SmsManage.findICcardException", param);

			//2.确认需要发送的管理员
			HashMap map = new HashMap();
			Iterator<HashMap> car_ex = ic_Card_Exceptin.iterator();
			//短信发送类
			while (car_ex.hasNext()) {
				HashMap car_ex_element = car_ex.next();
				map.clear();
				map.put("carMark", car_ex_element.get("CAR_MARK").toString());
				List ic_Admin_List = baseSqlMapDAO.queryForList(
						"SmsManage.findAdminUser", map);
				String sendSmsId = "";//流水号
				String contentMsg = car_ex_element.get("content").toString();//报警内容
				String smsType = car_ex_element.get("type").toString();//类型
				Iterator<HashMap> adminList = ic_Admin_List.iterator();
				
				HashMap admin_element = null;
				//1.增加发送短信到数据库
				param = new HashMap();
				//普通管理员
				if (adminList.hasNext()) {
					sendSmsId = baseSqlMapDAO.sequences("send_sms_id");
					admin_element = adminList.next();
					param.clear();
					param.put("sendSmsId", sendSmsId); //流水号
					param.put("callStaffId", admin_element.get("astaff_info_id").toString()); //接收者对应员工表ID
					param.put("callManName", admin_element.get("astaff_name").toString()); //接收者姓名
					param.put("callPhone", admin_element.get("asms_phone").toString()); //接收者手机号码

					param.put("sendContent", contentMsg);//短信内容
					param.put("staffId", admin_element.get("staff_info_id").toString()); //写该条短信的用户
					param.put("sourceOrderType", smsType); //原单类型:IC卡异常 4代表IC卡异常
					param.put("sourceOrderCode", ""); //源单编号:设为空
					param.put("cityId", admin_element.get("city_id").toString()); //区域编号
					
					baseSqlMapDAO.insert("SmsManage.insertSendSmsRecord", param);
					//2.发送短信
					ArrayList mobileList = new ArrayList();
					mobileList.add(admin_element.get("asms_phone"));
					SmsBean smsBean = new SmsBean();
					smsBean.setMobileNoList(mobileList);
					smsBean.setSmsContent(contentMsg);
					smsBean.setSn("");
					int res=0;
					try{
						res = proxysend.SendMessage(smsBean);
					}catch(Exception e)
					{
						System.out.println(e.toString());
					}
					//3.修改数据库，说明是否发送成功
					if (res == 1) {
						param.clear();
						param.put("sendSmsId", sendSmsId); //流水号
						baseSqlMapDAO.update("SmsManage.updateSendSmsState", param);
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//出区域报警
	public void OutExceptinPro()
	{
		try {
			//查询异常数据
			HashMap param = null;
			List ic_Card_Exceptin = sqlServerBaseSqlMapDAO.queryForList("SmsManage.findOutException", param);

			//2.确认需要发送的管理员
			HashMap map = new HashMap();//以车牌号过滤，得到管理员信息
			
			Iterator<HashMap> car_ex = ic_Card_Exceptin.iterator();
			//短信发送类
			while (car_ex.hasNext()) {
				HashMap car_ex_element = car_ex.next();
				map.clear();
				map.put("carMark", car_ex_element.get("CAR_MARK").toString());
				List ic_Admin_List = baseSqlMapDAO.queryForList(
						"SmsManage.findAdminUser", map);
				String sendSmsId = "";//流水号
				String contentMsg = car_ex_element.get("content").toString();//报警内容
				String smsType = car_ex_element.get("type").toString();//类型
				Iterator<HashMap> adminList = ic_Admin_List.iterator();
				
				HashMap admin_element = null;
				//1.增加发送短信到数据库
				param = new HashMap();
				//普通管理员
				if (adminList.hasNext()) {
					sendSmsId = baseSqlMapDAO.sequences("send_sms_id");
					admin_element = adminList.next();
					param.clear();
					param.put("sendSmsId", sendSmsId); //流水号
					param.put("callStaffId", admin_element.get("astaff_info_id").toString()); //接收者对应员工表ID
					param.put("callManName", admin_element.get("astaff_name").toString()); //接收者姓名
					param.put("callPhone", admin_element.get("asms_phone").toString()); //接收者手机号码

					param.put("sendContent", contentMsg);//短信内容
					param.put("staffId", admin_element.get("staff_info_id").toString()); //写该条短信的用户
					param.put("sourceOrderType", smsType); //原单类型:IC卡异常 4代表IC卡异常
					param.put("sourceOrderCode", ""); //源单编号:设为空
					param.put("cityId", admin_element.get("city_id").toString()); //区域编号
					
					baseSqlMapDAO.insert("SmsManage.insertSendSmsRecord", param);
					//2.发送短信
					ArrayList mobileList = new ArrayList();
					mobileList.add(admin_element.get("asms_phone"));
					SmsBean smsBean = new SmsBean();
					smsBean.setMobileNoList(mobileList);
					smsBean.setSmsContent(contentMsg);
					smsBean.setSn("");
					int res=0;
					try{
						res = proxysend.SendMessage(smsBean);
					}catch(Exception e){
						System.out.println(e.toString());
					}
					//3.修改数据库，说明是否发送成功
					if (res == 1) {
						param.clear();
						param.put("sendSmsId", sendSmsId); //流水号
						baseSqlMapDAO.update("SmsManage.updateSendSmsState", param);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

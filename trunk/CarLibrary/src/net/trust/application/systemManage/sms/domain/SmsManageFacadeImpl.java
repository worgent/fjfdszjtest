package net.trust.application.systemManage.sms.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
import net.trust.utils.sms.SMProxySendFacade;
import net.trust.utils.sms.SmsBean;

/**
 * 短信管理
 * 
 */
public class SmsManageFacadeImpl implements SmsManageFacade {
	private BaseSqlMapDAO baseSqlMapDAO;

	private SMProxySendFacade smproxySendFacade;

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

	/**
	 * 获取接收到的记录数
	 * 
	 * @param map
	 * @return
	 */
	public int findInceptSmsCount(HashMap map) {
		return ((Integer) baseSqlMapDAO.queryForObject(
				"SmsManage.findInceptSmsCount", map));
	}

	/**
	 * 获取接收到的记录
	 * 
	 * @param map
	 * @return
	 */
	public List findInceptSms(HashMap map) {
		return baseSqlMapDAO.queryForList("SmsManage.findInceptSms", map);
	}

	/**
	 * 修改接收到的短信 处理状态
	 * 
	 * @param map
	 * @return
	 */
	public int updateInceptSms(HashMap map) {
		return baseSqlMapDAO.update("SmsManage.updateInceptSms", map);
	}

	/**
	 * 查询发送的短信记录总数
	 * 
	 * @param map
	 * @return
	 */
	public int findSendSmsRecordCount(HashMap map) {
		return ((Integer) baseSqlMapDAO.queryForObject(
				"SmsManage.findSendSmsRecordCount", map));
	}

	/**
	 * 查询发送的短信记录
	 * 
	 * @param map
	 * @return
	 */
	public List findSendSmsRecord(HashMap map) {
		return baseSqlMapDAO.queryForList("SmsManage.findSendSmsRecord", map);
	}

	/**
	 * 记录要发送的短信内容
	 * 
	 * @param map
	 * @return 1:成功 -100：登记发送短信记录失败 -200:要发送的短信已被记录，但发送失败请通过列表查询出记录点击重新发送
	 */
	public int insertSendSmsRecord(HashMap map) {
		String sendSmsId = baseSqlMapDAO.sequences("send_sms_id");
		map.put("sendSmsId", sendSmsId);
		int st = baseSqlMapDAO.update("SmsManage.insertSendSmsRecord", map);
		if (st == 0) {
			return -100; // 登记发送短信记录失败
		}

		SmsBean smsBean = new SmsBean();
		ArrayList mobileList = new ArrayList();
		mobileList.add(map.get("mobileNo").toString());
		smsBean.setMobileNoList(mobileList);
		smsBean.setSmsContent(map.get("smsContent").toString());
		smsBean.setSn("");
		try{
		st = smproxySendFacade.SendMessage(smsBean);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		if (st == 1) {
			map.clear();
			map.put("sendSmsId", sendSmsId); // 流水号
			baseSqlMapDAO.update("SmsManage.updateSendSmsState", map);
			return 1;
		} else {
			return -200; // 要发送的短信已被记录，但发送失败请通过列表查询出记录点击重新发送
		}
	}

	/**
	 * 重新发送
	 * 
	 * @param map
	 * @return 1:成功 -200：要发送的短信已被记录，但发送失败请通过列表查询出记录点击重新发送
	 */
	public int updateResendSms(HashMap map) {
		map = (HashMap) baseSqlMapDAO.queryForObject(
				"SmsManage.findSendSmsRecord", map);
		String sn = map.get("send_sms_id").toString();
		SmsBean smsBean = new SmsBean();
		ArrayList mobileList = new ArrayList();
		mobileList.add(map.get("call_phone").toString());
		smsBean.setMobileNoList(mobileList);
		smsBean.setSmsContent(map.get("send_content").toString());
		smsBean.setSn("");
		int st=0;
		try{
		 st = smproxySendFacade.SendMessage(smsBean);
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (st == 1) {
			map.clear();
			map.put("sendSmsId", sn); // 流水号
			baseSqlMapDAO.update("SmsManage.updateSendSmsState", map);
			return 1;
		} else {
			return -200; // 要发送的短信已被记录，但发送失败请通过列表查询出记录点击重新发送
		}
	}

	/**
	 * 群发短信内容 2008-12-22
	 * 
	 * @param map
	 * @return 1:成功 -100：登记发送短信记录失败 -200:要发送的短信已被记录，但发送失败请通过列表查询出记录点击重新发送
	 */
	public int smporxySend(HashMap map) {
		// 网关方式发送短信
		SmsBean smsBean = new SmsBean();
		// 处理手机号
		ArrayList mobileList = new ArrayList();
		String mobilesStr = map.get("mobiles").toString();
		String[] mobileArray = mobilesStr.split(";");
		for (int i = 0; i < mobileArray.length; i++) {
			mobileList.add(mobileArray[i]);
		}
		smsBean.setMobileNoList(mobileList);
		String staffIdStr=map.get("staffId").toString();
		String cityIdStr=map.get("cityId").toString();
		String contentStr=map.get("smsContext").toString();
		smsBean.setSmsContent(contentStr);// 短信内容
		smsBean.setSn("");
		int st=0;
		try{
			st = smproxySendFacade.SendMessage(smsBean);
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}

		if (st == 1) {
			// 发送成功数据库，增加记录
			for (int i = 0; i < mobileArray.length; i++) {
				//增加数据
				String sendSmsId = baseSqlMapDAO.sequences("send_sms_id");
				map.clear();
				map.put("staffId", staffIdStr);
				map.put("cityId", cityIdStr);
				map.put("sendSmsId", sendSmsId);
				map.put("callPhone", mobileArray[i]);
				map.put("sendContent", contentStr);
				map.put("sourceOrderType", "5");
				st = baseSqlMapDAO.update("SmsManage.insertSendSmsRecord", map);
				if (st == 0) {
					return -100; // 登记发送短信记录失败
				} else {
					//更新数据
					map.clear();
					map.put("sendSmsId", sendSmsId); // 流水号
					baseSqlMapDAO.update("SmsManage.updateSendSmsState", map);
				}
			}
			return 1;
		} else {
			return -200; // 要发送的短信已被记录，但发送失败请通过列表查询出记录点击重新发送
		}
	}
	
	/**
	 * 2009-01-17
	 * 查询系统短信配置信息
	 * @param map
	 * @return
	 */
	public List findSystemConfig(HashMap map){
		return baseSqlMapDAO.queryForList("SmsManage.systemConfig", map);
	}
	/**
	 * 2009-01-17
	 * 修改系统短信配置信息
	 * @param map
	 * @return
	 */
	public int updateSystemConfig(HashMap map){
		return baseSqlMapDAO.update("SmsManage.updatesystemConfig", map);
	}

	/**
	 * 取消短信发送
	 * 
	 * @param map
	 * @return
	 */
	public int updateSendSmsCancel(HashMap map) {
		return baseSqlMapDAO.update("SmsManage.updateSendSmsCancel", map);
	}

	/**
	 * @return the smproxySendFacade
	 */
	public SMProxySendFacade getSmproxySendFacade() {
		return smproxySendFacade;
	}

	/**
	 * @param smproxySendFacade
	 *            the smproxySendFacade to set
	 */
	public void setSmproxySendFacade(SMProxySendFacade smproxySendFacade) {
		this.smproxySendFacade = smproxySendFacade;
	}
}

package net.trust.application.systemManage.sms.domain;

import java.util.HashMap;
import java.util.List;

/**
 * 短信管理
 *
 */
public interface SmsManageFacade {
	/**
	 * 获取接收到的记录数 
	 * @param map
	 * @return
	 */
	public int findInceptSmsCount(HashMap map);
	/**
	 * 获取接收到的记录
	 * @param map
	 * @return
	 */
	public List findInceptSms(HashMap map);
	/**
	 * 修改接收到的短信 处理状态
	 * @param map
	 * @return
	 */
	public int updateInceptSms(HashMap map);
	
	
	/**
	 * 查询发送的短信记录总数
	 * @param map
	 * @return
	 */
	public int findSendSmsRecordCount(HashMap map);
	/**
	 * 查询发送的短信记录
	 * @param map
	 * @return
	 */
	public List findSendSmsRecord(HashMap map);
	/**
	 * 记录要发送的短信内容
	 * @param map
	 * @return
	 */
	public int insertSendSmsRecord(HashMap map);
	/**
	 * 2008-12-22
	 * 群发短信内容
	 * @param map
	 * @return
	 */	
	public int smporxySend(HashMap map);
	/**
	 * 重新发送
	 * @param map
	 * @return
	 */
	public int updateResendSms(HashMap map);
	
	/**
	 * 取消短信发送
	 * @param map
	 * @return
	 */
	public int updateSendSmsCancel(HashMap map);
	
	/**
	 * 2009-01-17
	 * 查询系统短信配置信息
	 * @param map
	 * @return
	 */
	public List findSystemConfig(HashMap map);
	/**
	 * 2009-01-17
	 * 修改系统短信配置信息
	 * @param map
	 * @return
	 */
	public int updateSystemConfig(HashMap map);
}

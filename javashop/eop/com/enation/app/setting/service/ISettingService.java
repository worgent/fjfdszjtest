/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.app.setting.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ISettingService {

 
	/**
	 * 更新系统设置
	 * 
	 * @param code
	 * @param value
	 * @throws SettingRuntimeException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public abstract void save(Map<String,Map<String,String>> settings ) throws SettingRuntimeException;

	
	/**
	 * 读取全部设置
	 * 
	 * @param group
	 * @param code
	 * @return
	 */
	public abstract Map<String,Map<String,String>>  getSetting();
	
	
	/**
	 * 设置输入页面显示事件
	 * @return
	 */
	public abstract List<String> onInputShow();
	
	
	/**
	 * 读取某项设置值
	 * @param name 参数名
	 * @return 参数值
	 */
	public abstract String getSetting(String group,String name);

}
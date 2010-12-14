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
package com.enation.javashop.core.service;

import com.enation.eop.model.Message;
import com.enation.framework.database.Page;

/**
 * 留言、短消息管理
 * 
 * @author lzf<br/>
 *         2010-3-18 上午11:30:28<br/>
 *         version 1.0<br/>
 */
public interface IMessageManager {
	
	/**
	 * 分页读取留言或短消息
	 * @param pageNo
	 * @param pageSize
	 * @param folder, 'inbox'=>收件箱; 'outbox'=>发件箱
	 * @return
	 */
	public Page pageMessage(int pageNo, int pageSize, String folder);
	
	/**
	 * 添加短消息
	 * @param message
	 */
	public void addMessage(Message message);
	
	/**
	 * 删除收件箱中的消息
	 * @param ids
	 */
	public void delinbox(String ids);
	
	/**
	 * 删除发件箱中的消息
	 * @param ids
	 */
	public void deloutbox(String ids);
	
	/**
	 * 修改短消息
	 * @param m
	 */
	public void setMessage_read(int msg_id);

}

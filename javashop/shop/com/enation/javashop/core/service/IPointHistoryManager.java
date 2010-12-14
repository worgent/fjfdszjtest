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

import java.util.List;

import com.enation.framework.database.Page;
import com.enation.javashop.core.model.PointHistory;

/**
 * 会员积分历史管理
 * 
 * @author lzf<br/>
 *         2010-3-22 上午11:23:00<br/>
 *         version 1.0<br/>
 */
public interface IPointHistoryManager {

	/**
	 * 积分历史列表(当前会员）
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page pagePointHistory(int pageNo, int pageSize);
	
	/**
	 * 列表指定会员 的积分日志
	 * @param member_id
	 * @return
	 */
	public List listPointHistory(int member_id);

	/**
	 * 累计消费积分
	 * 
	 * @return
	 */
	public Long getConsumePoint();

	/**
	 * 累计获得积分
	 * 
	 * @return
	 */
	public Long getGainedPoint();

	/**
	 * 新增日志
	 * 
	 * @param pointHistory
	 */
	public void addPointHistory(PointHistory pointHistory);

}

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
package com.enation.cms.core.plugin;

import com.enation.cms.core.model.DataField;

/**
 * 字段值显示事件
 * 当进行字段值显示时激发此事件
 * @author kingapex
 * 2010-7-6上午10:47:13
 */
public interface IFieldValueShowEvent {
	
	/**
	 * 字段显示事件字义
	 * @param field 字段实体
	 * @param value 数据库中保存的字段值
	 * @return 处理后的字段值
	 */
	public Object onShow(DataField field,Object value);
}

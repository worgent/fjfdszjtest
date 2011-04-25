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
package com.enation.eop.sdk.widget;

import java.util.Map;

/**
 * 挂件接口
 * @author kingapex
 * 2010-1-29上午10:09:22
 */
public interface IWidget {
	
	/**
	 * 解析挂件并返回解析后的html片段
	 * @param params 挂件参数Map
	 * @return 解析后的html片段
	 */
	public String process(Map<String,String> params);
	
	public String setting(Map<String,String> params);
	
}
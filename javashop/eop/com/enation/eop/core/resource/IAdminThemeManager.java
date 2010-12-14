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
package com.enation.eop.core.resource;

import java.util.List;

import com.enation.eop.core.resource.model.AdminTheme;
import com.enation.eop.core.resource.model.Theme;

/**
 * 后主题管理
 * @author lzf
 *         <p>
 *         2009-12-16 上午11:51:33
 *         </p>
 * @version 1.0
 */
public interface IAdminThemeManager {

	
	/**
	 * 添加后台主题
	 * @param theme
	 * @param isCommon 是否是公共模板
	 */
	public Integer add(AdminTheme theme,boolean isCommon);
	
	
	
	/**
	 * 读取所有主题列表
	 * @return
	 */
	public List<AdminTheme> list();
	
	
	/**
	 * 读取某个主题详细
	 * @param themeid
	 * @return
	 */
	public AdminTheme get( Integer themeid);

	
	
	 

}

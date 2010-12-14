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
package com.enation.javashop.widget.comments;

import com.enation.app.setting.plugin.IOnSettingInputShow;
import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.javashop.core.plugin.JspPageTabs;

public class CommentsSettingPlugin extends AutoRegisterPlugin implements IOnSettingInputShow {
	
	/**
	 * 定义设置参数组的组名
	 */
	
	public String getSettingGroupName() {
		 
		return "comments";
	}

	
	/**
	 * 响应输入界面显示事件，返回页面名称为setting，即同目录下setting.html
	 */
	
	public String onShow() {
		 
		return "setting";
	}
	
	/**
	 * 注册设置组选项卡名
	 */
	
	public void register() {
		 JspPageTabs.addTab("setting",2, "评论设置");
		
	}

	
	public String getAuthor() {
		// TODO Auto-generated method stub
		return "kingapex";
	}

	
	public String getId() {
		// TODO Auto-generated method stub
		return "comments";
	}

	
	public String getName() {
		// TODO Auto-generated method stub
		return "评论";
	}

	
	public String getType() {
		// TODO Auto-generated method stub
		return "comments";
	}

	
	public String getVersion() {
		// TODO Auto-generated method stub
		return "1.0";
	}

	
	public void perform(Object... params) {
		// TODO Auto-generated method stub
		
	}

}

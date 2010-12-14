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
package com.enation.app.setting.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enation.eop.core.view.freemarker.FreeMarkerPaser;
import com.enation.framework.plugin.AutoRegisterPluginsBundle;
import com.enation.framework.plugin.IPlugin;



/**
 * 系统设置插件桩
 * @author apexking
 *
 */
public class SettingPluginBundle extends AutoRegisterPluginsBundle {
	
 
	protected static final Log loger = LogFactory
			.getLog(SettingPluginBundle.class);



	
	public String getName() {
		// TODO Auto-generated method stub
		return "系统设置插件桩";
	}



	
	public void registerPlugin(IPlugin plugin) {
		// TODO Auto-generated method stub
		super.registerPlugin(plugin);
	}

	public List<String> onInputShow(Map<String,Map<String,String>> settings){
		//Map<String,Map<String,String>> settings  = settingService.getSetting();
		
		List<String> htmlList  = new ArrayList<String>();
		FreeMarkerPaser freeMarkerPaser =  FreeMarkerPaser.getInstance();
		
		if (plugins != null) {
			for (IPlugin plugin : plugins) {
				if(plugin instanceof IOnSettingInputShow){
					IOnSettingInputShow event = (IOnSettingInputShow)plugin;
					String groupname = event.getSettingGroupName();
					String pageName = event.onShow();
					
					freeMarkerPaser.setClz(event.getClass());
					freeMarkerPaser.setPageName(pageName);
					freeMarkerPaser.putData(groupname, settings.get(groupname));
					
					htmlList.add(freeMarkerPaser.proessPageContent());
				}
			}
		}
		return htmlList;
	}

 


 
	
}

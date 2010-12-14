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

import java.util.List;
import java.util.Map;

import com.enation.cms.core.model.DataField;
import com.enation.framework.plugin.AutoRegisterPluginsBundle;
import com.enation.framework.plugin.IPlugin;

/**
 * 文章插件桩
 * 
 * @author kingapex 2010-7-5下午02:29:17
 */
public class ArticlePluginBundle extends AutoRegisterPluginsBundle {

	@Override
	public String getName() {

		return "文章插件桩";
	}

	public void onSave(Map article,DataField field) {
		try {

			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof AbstractFieldPlugin) {
						AbstractFieldPlugin fieldPlugin = (AbstractFieldPlugin) plugin;
						if(fieldPlugin.getId().equals(field.getShow_form())){
							fieldPlugin.onSave(article ,field);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String onDisplay(DataField field,Object value){
		try {

			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof AbstractFieldPlugin) {
						AbstractFieldPlugin fieldPlugin = (AbstractFieldPlugin) plugin;
						if(fieldPlugin.getId().equals(field.getShow_form())){
							return  fieldPlugin.onDisplay(field, value);
						}
					}
				}
			}

			return "输入项"+ field.getShow_form() +"未找到插件解析";
		} catch (Exception e) {
			e.printStackTrace();
			return "输入项"+field.getShow_form()+"发生错误";
		}		
	}
	
	public IPlugin findPlugin(String id){
		if (plugins != null) {
			for (IPlugin plugin : plugins) {
				if(id.equals(plugin.getId())){
					return plugin;
				}
			}
		}
		return null;
	}
	
	public List getPlugins(){
		return this.plugins;
	}
}

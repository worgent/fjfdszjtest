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
package com.enation.app.setting.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.setting.plugin.SettingPluginBundle;
import com.enation.app.setting.service.ISettingService;
import com.enation.app.setting.service.SettingRuntimeException;
import com.enation.framework.database.IDaoSupport;

/**
 * 系统设置业务类
 * 
 * @author apexking
 * 
 */
public class SettingService  implements ISettingService  {
	private IDaoSupport daoSupport;
	private SettingPluginBundle settingPluginBundle;
 
	

	/* (non-Javadoc)
	 * @see com.enation.app.setting.service.ISettingService#save()
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void save( Map<String,Map<String,String>> settings ) throws SettingRuntimeException {
		Iterator<String> settingkeyItor = settings.keySet().iterator();
 
		while ( settingkeyItor.hasNext() ) {
			
			String settingKey = settingkeyItor.next();
			JSONObject jsonObject = JSONObject.fromObject( settings );
			
			this.daoSupport.execute("update settings set cfg_value=? where cfg_group=?",jsonObject.toString(),settingKey);
			
		}
	 
	}
	
	
 
	

	/* (non-Javadoc)
	 * @see com.enation.app.setting.service.ISettingService#getSetting()
	 */
	public Map<String,Map<String,String>> getSetting() {
		String sql = "select * from settings";
		List<Map<String, String>> list = this.daoSupport.queryForList(sql);
		Map cfg = new HashMap();
		
		for (Map<String,String> map : list) {
			String setting_value = map.get("cfg_value");
			JSONObject jsonObject = JSONObject.fromObject( setting_value );  
			Map itemMap = (Map)jsonObject.toBean(jsonObject, Map.class);
			cfg.put( map.get("cfg_group"), itemMap);
		}
		
		return cfg;
	}

	public SettingPluginBundle getSettingPluginBundle() {
		return settingPluginBundle;
	}

	public void setSettingPluginBundle(SettingPluginBundle settingPluginBundle) {
		this.settingPluginBundle = settingPluginBundle;
	}
	
	
	public static void main(String[] args){
		String setting_value = "{\"thumbnail_pic_height\":\"40\",\"thumbnail_pic_width\":\"50\"}" ;
		JSONObject jsonObject = JSONObject.fromObject( setting_value );  
		Map map1 = (Map)jsonObject.toBean(jsonObject, Map.class);
		System.out.println(map1.get("thumbnail_pic_height"));		
	}



	public void setDaoSupport(IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}


	
	public String getSetting(String group, String code) {
		
		return this.getSetting().get(group).get(code);
	}


	
	public List<String> onInputShow() {
		 Map<String,Map<String ,String>>  settings  = this.getSetting();
		return this.settingPluginBundle.onInputShow(settings);
	}

}

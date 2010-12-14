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
package com.enation.javashop.core.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.enation.eop.core.view.freemarker.FreeMarkerPaser;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.framework.database.ObjectNotFoundException;
import com.enation.framework.plugin.IPlugin;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.PayCfg;
import com.enation.javashop.core.plugin.PaymentPluginBundle;
import com.enation.javashop.core.service.IPaymentManager;

/**
 * 支付方式管理
 * @author kingapex
 *2010-4-4下午02:26:19
 */
public class PaymentManager extends BaseSupport<PayCfg> implements IPaymentManager {
	
	//支付插件桩
	private PaymentPluginBundle paymentPluginBundle;
	
	
	
	public List list() {
		String sql = "select * from payment_cfg";
		return this.baseDaoSupport.queryForList(sql, PayCfg.class);
	}

	
	public PayCfg get(Integer id) {
		String sql = "select * from payment_cfg where id=?";
		PayCfg payment =this.baseDaoSupport.queryForObject(sql, PayCfg.class, id);
		return payment;
	}

	
	public Double countPayPrice(Integer id) {
		
		return 0D;
	}

	
	public void add(String name, String type, String biref,
			Map<String, String> configParmas) {
		if(StringUtil.isEmpty(name)) throw new IllegalArgumentException("payment name is  null");
		if(StringUtil.isEmpty(type)) throw new IllegalArgumentException("payment type is  null");
		if(configParmas == null) throw new IllegalArgumentException("configParmas  is  null");
		
		PayCfg payCfg = new PayCfg();
		payCfg.setName(name);
		payCfg.setType(type);
		payCfg.setBiref(biref);
		payCfg.setConfig( JSONObject.fromObject(configParmas).toString());
		this.baseDaoSupport.insert("payment_cfg", payCfg);
	}

	
	public Map<String, String> getConfigParams(Integer paymentId) {
		PayCfg payment =this.get(paymentId);
		String config  = payment.getConfig();
		if(null == config ) return new HashMap<String,String>();
		JSONObject jsonObject = JSONObject.fromObject( config );  
		Map itemMap = (Map)jsonObject.toBean(jsonObject, Map.class);
		return itemMap;
	}

	
	public void edit(Integer paymentId, String name, String biref,
			Map<String, String> configParmas) {
		
		if(StringUtil.isEmpty(name)) throw new IllegalArgumentException("payment name is  null");
		if(configParmas == null) throw new IllegalArgumentException("configParmas  is  null");
		
		PayCfg payCfg = new PayCfg();
		payCfg.setName(name);
		payCfg.setBiref(biref);
		payCfg.setConfig( JSONObject.fromObject(configParmas).toString());	
		this.baseDaoSupport.update("payment_cfg", payCfg, "id="+ paymentId);
	}

	
	public void delete(Integer[] idArray) {
		if(idArray==null || idArray.length==0) return;
		
		String idStr = StringUtil.arrayToString(idArray, ",");
		String sql  ="delete from payment_cfg where id in("+idStr+")";
		this.baseDaoSupport.execute(sql);
	}

	
	
	public List<IPlugin> listAvailablePlugins() {
		
		return this.paymentPluginBundle.getPluginList();
	}

	
	public String getPluginInstallHtml(String pluginId,Integer paymentId) {
		IPlugin installPlugin =null;
		List<IPlugin>  plguinList =  this.listAvailablePlugins();
		for(IPlugin plugin :plguinList){
			if(plugin.getId().equals(pluginId)){
				installPlugin = plugin;
				break;
			}
		}
		
		
		
		if(installPlugin==null) throw new ObjectNotFoundException("plugin["+pluginId+"] not found!"); 
		FreeMarkerPaser fp =  FreeMarkerPaser.getInstance();
		fp.setClz(installPlugin.getClass());
		 
		if(paymentId!=null){
			Map<String,String> params = this.getConfigParams(paymentId);
			Iterator<String> keyIter  = params.keySet().iterator();
			
			while(keyIter.hasNext()) {
				 String key  = keyIter.next();
				 String value = params.get(key);
				 fp.putData(key, value);
			}
		}
		return fp.proessPageContent();
	}
	
	
	public PaymentPluginBundle getPaymentPluginBundle() {
		return paymentPluginBundle;
	}

	public void setPaymentPluginBundle(PaymentPluginBundle paymentPluginBundle) {
		this.paymentPluginBundle = paymentPluginBundle;
	}

	
}

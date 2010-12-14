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
package com.enation.javashop.core.action.backend;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.Logi;
import com.enation.javashop.core.service.ILogiManager;

public class LogiAction extends WWAction {
	
	private ILogiManager logiManager;
	private String name;
	private Integer cid;
	private String id;
	private String order;
	private Logi logi;
	
	public String add_logi(){
		return "add_logi";
	}
	
	public String edit_logi(){
		this.logi = this.logiManager.getLogiById(cid);
		return "edit_logi";
	}
	
	public String list_logi(){
		this.webpage = this.logiManager.pageLogi(order, this.getPage(), this.getPageSize());
		return "list_logi";
	}
	
	public String delete(){
		try {
			this.logiManager.delete(id);
			this.json = "{'result':0,'message':'删除成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'删除失败'}";
		}
		return this.JSON_MESSAGE;
	}
	public String saveAdd(){
		logiManager.saveAdd(name);
		this.msgs.add("添加成功");
		this.urls.put("物流公司列表","logi!list_logi.do");
		return this.MESSAGE;
	}
	public String saveEdit(){
		this.logiManager.saveEdit(cid, name);
		this.msgs.add("修改成功");
		this.urls.put("物流公司列表","logi!list_logi.do");
		return this.MESSAGE;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 
	public ILogiManager getLogiManager() {
		return logiManager;
	}

	public void setLogiManager(ILogiManager logiManager) {
		this.logiManager = logiManager;
	}

	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Logi getLogi() {
		return logi;
	}

	public void setLogi(Logi logi) {
		this.logi = logi;
	}

 
	
	
}

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
import com.enation.javashop.core.model.AdColumn;
import com.enation.javashop.core.service.IAdColumnManager;

/**
 * @author lzf
 * 2010-3-2 上午09:46:08
 * version 1.0
 */
public class AdColumnAction extends WWAction {
	
	private IAdColumnManager adColumnManager;
	private AdColumn adColumn;
	private Long acid;
	private String id;
	
	public String list() {
		this.webpage = this.adColumnManager.pageAdvPos(this.getPage(), this.getPageSize());
		return "list";
	}
	
	public String detail(){
		adColumn = this.adColumnManager.getADcolumnDetail(acid);
		return "detail";
	}
	
	public String delete(){
		try {
			this.adColumnManager.delAdcs(id);
			this.json = "{'result':0,'message':'删除成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'删除失败'}";
		}
		return this.JSON_MESSAGE;
	}
	
	public String add(){
		return "add";
	}
	
	public String addSave() {
		this.adColumnManager.addAdvc(adColumn);
		this.msgs.add("新增广告位成功");
		return this.MESSAGE;
	}
	
	public String edit(){
		adColumn = this.adColumnManager.getADcolumnDetail(acid);
		return "edit";
	}
	
	public String editSave(){
		this.adColumnManager.updateAdvc(adColumn);
		this.msgs.add("修改广告位成功");
		return this.MESSAGE;
	}

	public IAdColumnManager getAdColumnManager() {
		return adColumnManager;
	}

	public void setAdColumnManager(IAdColumnManager adColumnManager) {
		this.adColumnManager = adColumnManager;
	}

	public AdColumn getAdColumn() {
		return adColumn;
	}

	public void setAdColumn(AdColumn adColumn) {
		this.adColumn = adColumn;
	}

	public Long getAcid() {
		return acid;
	}

	public void setAcid(Long acid) {
		this.acid = acid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
}

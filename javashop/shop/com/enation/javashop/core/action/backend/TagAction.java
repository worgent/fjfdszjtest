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
import com.enation.javashop.core.model.Tag;
import com.enation.javashop.core.service.ITagManager;

/**
 * 标签action
 * @author kingapex
 * 2010-7-14上午11:54:15
 */
public class TagAction extends WWAction {
	
	private ITagManager tagManager;
	private Tag tag;
	private Integer[] tag_ids;
	private Integer tag_id;
	
	public String checkJoinGoods(){
		if(this.tagManager.checkJoinGoods(tag_ids)){
			this.json="{result:1}";
		}else{
			this.json="{result:0}";
		}
		return this.JSON_MESSAGE;
	}
	
	public String checkname(){
		if( this.tagManager.checkname(tag.getTag_name(), tag.getTag_id()) ){
			this.json="{result:1}";
		}else{
			this.json="{result:0}";
		}
		return this.JSON_MESSAGE;
	}
	
	public String add(){
		
		return "add";
	}
	
	public String edit(){
		 tag = this.tagManager.getById(tag_id);
		return "edit";
	}
	
	
	//添加标签
	public String saveAdd(){
		this.tagManager.add(tag);
		this.msgs.add("标签添加成功");
		this.urls.put("标签列表", "tag!list.do");
		return this.MESSAGE;
	}
	
	
	//保存修改
	public String saveEdit(){
		
		this.tagManager.update(tag);
 
		this.msgs.add("标签修改成功");
		this.urls.put("标签列表", "tag!list.do");
		
		return this.MESSAGE;
	}
	
	public String delete(){
		
	 	this.tagManager.delete(tag_ids);
		json = "{'result':0,'message':'删除成功'}";
		
		return this.JSON_MESSAGE	;	
	}
	
	public String list(){
		this.webpage = this.tagManager.list(this.getPage(), this.getPageSize());
		return "list";
	}

	public ITagManager getTagManager() {
		return tagManager;
	}

	public void setTagManager(ITagManager tagManager) {
		this.tagManager = tagManager;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Integer[] getTag_ids() {
		return tag_ids;
	}

	public void setTag_ids(Integer[] tagIds) {
		tag_ids = tagIds;
	}

	public Integer getTag_id() {
		return tag_id;
	}

	public void setTag_id(Integer tagId) {
		tag_id = tagId;
	}
	
	
	
}

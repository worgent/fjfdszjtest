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

import java.util.List;
import java.util.Map;

import com.enation.framework.action.WWAction;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.model.support.GoodsEditDTO;
import com.enation.javashop.core.plugin.GoodsPluginBundle;
import com.enation.javashop.core.plugin.JspPageTabs;
import com.enation.javashop.core.service.IGoodsCatManager;
import com.enation.javashop.core.service.IGoodsManager;
import com.enation.javashop.core.service.IProductManager;

public class GoodsAction extends WWAction {
	protected String name;
	protected String sn;
	protected String order;
	private Integer catid;
	protected Integer[] id;

	protected Goods goods;
	protected Map goodsView;
	protected Integer goods_id;
	protected List catList; // 所有商品分类
	protected IGoodsCatManager goodsCatManager;
	protected IGoodsManager goodsManager;
	private IProductManager productManager; 
	protected Boolean is_edit;
	protected String actionName;
	
	private GoodsPluginBundle goodsPluginBundle;
	protected Map tabs;
	
	public Map getTabs(){
		return this.tabs;
	}
	
	public static final String GOODS_ADD_PAGE_ID= "goods_add";
	

	
	//商品列表
	public String list(){		
		if(name!=null ) name = StringUtil.toUTF8(name);
		if(sn!=null ) sn = StringUtil.toUTF8(sn);
		
		this.webpage = goodsManager.searchGoods(catid,name,sn,order, this.getPage(),this.getPageSize());
		is_edit =is_edit == null?Boolean.FALSE:Boolean.TRUE;
		if(!is_edit)
			return "list";
		else
			return "edit_list";
	}

	public String batchEdit(){
		
		try{
			this.goodsManager.batchEdit();
			this.json ="{result:1}";
		}catch(RuntimeException e ){
			e.printStackTrace();
			this.json = "{result:0}";
		}
		
		return this.JSON_MESSAGE;
	}
	
	public String getCatTree(){
		this.catList = this.goodsCatManager.listAllChildren(0);
		return "cat_tree";
	}
	
	//商品回收站列表
	public String trash_list(){
		this.webpage = this.goodsManager.pageTrash(name, sn, order, this.getPage(), this.getPageSize());
		return "trash_list";
	}
	
	//删除
	public String delete(){
		try{
			this.goodsManager.delete(id);
			this.json = "{'result':0,'message':'删除成功'}";
		 }catch(RuntimeException e){
			 this.json="{'result':1,'message':'删除失败'}";
		 }
		return this.JSON_MESSAGE;
	}
	
	//还原 
	public String revert(){
		try{
			this.goodsManager.revert(id);
			this.json = "{'result':0,'message':'清除成功'}";
		 }catch(RuntimeException e){
			 this.json="{'result':1,'message':'清除失败'}";
		 }
		return this.JSON_MESSAGE;
	}
	
	//清除
	public String clean(){
		try{
			this.goodsManager.clean(id);
			this.json = "{'result':0,'message':'清除成功'}";
		 }catch(RuntimeException e){
			 e.printStackTrace();
			 this.json="{'result':1,'message':'清除失败'}";
		 }
		return this.JSON_MESSAGE;
	}
	
	
	
	public String selector_list_ajax(){
		
		return "selector";
	}
	

	private List<String> tagHtmlList ;
	/**
	 * 到商品添加页
	 */
	public String add() {
		actionName = "goods!saveAdd.do";
		is_edit =false;
		this.pageId = GOODS_ADD_PAGE_ID;
		this.tabs = JspPageTabs.getTabs("goods");
		tagHtmlList = goodsManager.fillAddInputData();
		
		return this.INPUT;
	}
	
	/**
	 * 到商品添加页
	 */
	public String addBind() {
		actionName = "goods!saveBindAdd.do";
		return "bind_goods_input";
	}
	
	
	/**
	 * 到商品修改页面
	 */
	public String  edit(){
		actionName = "goods!saveEdit.do";
		is_edit =true;
		this.pageId = GOODS_ADD_PAGE_ID;
		this.tabs = JspPageTabs.getTabs("goods");
		catList = goodsCatManager.listAllChildren(0);
		GoodsEditDTO editDTO = this.goodsManager.getGoodsEditData(goods_id);
		goodsView = editDTO.getGoods();
		this.tagHtmlList = editDTO.getHtmlList();
		return this.INPUT;
	}
	

	/**
	 * 保存商品添加
	 * @return
	 */
	public String saveAdd() {

		try{
			goodsManager.add(goods) ;
			this.msgs.add("商品添加成功");
			this.urls.put("商品列表", "goods!list.do");
		}catch(RuntimeException e) {
			e.printStackTrace();
			this.msgs.add(e.getMessage());
		}		
		
		return this.MESSAGE;
	}
	 
	
	public String saveEdit(){
		try{
			goodsManager.edit(goods);
			this.msgs.add("商品修改成功");
			this.urls.put("商品列表", "goods!list.do");
			
		}catch(RuntimeException e) {
			e.printStackTrace();
			this.msgs.add(e.getMessage());
		}
		return this.MESSAGE;
	}
	
	
	public  String selector(){
		
		return "selector";
	}

	public List getCatList() {
		return catList;
	}

	public void setCatList(List catList) {
		this.catList = catList;
	}

 

	public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
		this.goodsCatManager = goodsCatManager;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}


	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}


	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}



	public Goods getGoods() {
		return goods;
	}


	public Integer getGoods_id() {
		return goods_id;
	}


	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}


	public Map getGoodsView() {
		return goodsView;
	}


	public void setGoodsView(Map goodsView) {
		this.goodsView = goodsView;
	}


	public Boolean getIs_edit() {
		return is_edit;
	}


	public void setIs_edit(Boolean is_edit) {
		this.is_edit = is_edit;
	}


	public String getActionName() {
		return actionName;
	}


	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}


	public Integer[] getId() {
		return id;
	}


	public void setId(Integer[] id) {
		this.id = id;
	}

	public GoodsPluginBundle getGoodsPluginBundle() {
		return goodsPluginBundle;
	}

	public void setGoodsPluginBundle(GoodsPluginBundle goodsPluginBundle) {
		this.goodsPluginBundle = goodsPluginBundle;
	}

	public List<String> getTagHtmlList() {
		return tagHtmlList;
	}

	public void setTagHtmlList(List<String> tagHtmlList) {
		this.tagHtmlList = tagHtmlList;
	}

	public Integer getCatid() {
		return catid;
	}

	public void setCatid(Integer catid) {
		this.catid = catid;
	}
			
}

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
package com.enation.javashop.plugin.standard.type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.core.view.freemarker.FreeMarkerPaser;
import com.enation.eop.core.view.freemarker.RepeatDirective;
import com.enation.framework.database.IDaoSupport;
import com.enation.javashop.core.model.Attribute;
import com.enation.javashop.core.model.GoodsType;
import com.enation.javashop.core.model.support.ParamGroup;
import com.enation.javashop.core.plugin.AbstractGoodsPlugin;
import com.enation.javashop.core.service.IGoodsCatManager;
import com.enation.javashop.core.service.IGoodsTypeManager;


/**
 * 类型管理插件
 * @author kingapex
 *
 */
public class GoodsTypeAdminPlugin extends AbstractGoodsPlugin  {

	private IDaoSupport<GoodsType> baseDaoSupport; 
	private IGoodsCatManager goodsCatManager;

	
	public void addTabs() {
		this.addTags(1, "属性");
		this.addTags(2, "参数");
	}


	private IGoodsTypeManager goodsTypeManager;
	

	public void onBeforeGoodsEdit(Map goods, HttpServletRequest request)  {
		// 处理参数信息
		String[] paramnums = request.getParameterValues("paramnums");
		String[] groupnames = request.getParameterValues("groupnames");
		String[] paramnames = request.getParameterValues("paramnames");
		String[] paramvalues = request.getParameterValues("paramvalues");
		
		
		String params = goodsTypeManager.getParamString(paramnums, groupnames,
				paramnames, paramvalues);
		goods.put("params", params);
		
		 String[] propvalues= request.getParameterValues("propvalues");  // 值数组

			try{
				Integer goods_id  = Integer.valueOf( "" + goods.get("goods_id"));
				 saveProps(goods_id,propvalues);	
			}
			catch(NumberFormatException e){
				throw new  RuntimeException("商品id格式错误");
			}			
	}
	
	
	public void onBeforeGoodsAdd(Map goods, HttpServletRequest request) {
		
		// 处理参数信息
		String[] paramnums = request.getParameterValues("paramnums");
		String[] groupnames = request.getParameterValues("groupnames");
		String[] paramnames = request.getParameterValues("paramnames");
		String[] paramvalues = request.getParameterValues("paramvalues");
		
		String params = goodsTypeManager.getParamString(paramnums, groupnames,
				paramnames, paramvalues);
		goods.put("params", params);
		
		
		List typeList = goodsTypeManager.listAll();
		request.setAttribute("typeList", typeList);
		 
	}

	
	public String onFillGoodsAddInput(HttpServletRequest request) {
		List typeList = this.goodsTypeManager.listAll();
		List catList = goodsCatManager.listAllChildren(0);
		
		FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();// new FreeMarkerPaser(getClass(),"/plugin/type");
		freeMarkerPaser.setPageName("type_input");
		freeMarkerPaser.putData("typeList", typeList);
		freeMarkerPaser.putData("catList", catList);
		freeMarkerPaser.putData("is_edit", false);
		freeMarkerPaser.putData("repeat", new RepeatDirective());
		return freeMarkerPaser.proessPageContent();
	}
	
 
	public void onAfterGoodsEdit(Map goods, HttpServletRequest request)  {
		 
		
	}
	

	public void onAfterGoodsAdd(Map goods, HttpServletRequest request)  {
		
		 String[] propvalues= request.getParameterValues("propvalues");  // 值数组

			try{
				Integer goods_id  = Integer.valueOf( "" + goods.get("goods_id"));
				saveProps(goods_id,propvalues);	
			}
			catch(NumberFormatException e){
				throw new  RuntimeException("商品id格式错误");
			}
	}
		

	public String onFillGoodsEditInput(Map goods, HttpServletRequest request) {
		List typeList = this.goodsTypeManager.listAll();
		
		//读取参数信息
		String params  = goods.get("params")==null ? "" : goods.get("params").toString();
		ParamGroup[] paramAr = GoodsTypeUtil.converFormString( params);// 处理参数
		
		
		
		//读取属性信息
		Map propMap = new HashMap();
		
		for(int i=0;i<20;i++){
			//System.out.println("p" + i + " is : " + goods.get("p" + (i+1)) );
			String value = goods.get("p" + (i+1))==null ? "" : goods.get("p" + (i+1)).toString();
			propMap.put("p"+i,value);
		}
		
		goods.put("propMap",propMap);
		
		List propList  = proessProps(goods);
	 
		List catList = goodsCatManager.listAllChildren(0);
		
		FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();
		freeMarkerPaser.setPageName("type_input");
		freeMarkerPaser.putData("attrList", propList);
		freeMarkerPaser.putData("typeList", typeList);
		freeMarkerPaser.putData("paramAr", paramAr);
		freeMarkerPaser.putData("catList", catList);
		freeMarkerPaser.putData("is_edit", true);
		freeMarkerPaser.putData("repeat", new RepeatDirective());
		return freeMarkerPaser.proessPageContent();
	}

	
	// 处理属性信息
	private List proessProps(Map goodsView) {
		
		if( goodsView.get("type_id")==null ) {
			throw new  RuntimeException("类型id为空");
		}
		Integer goods_id =null;
		try{
			 goods_id  = Integer.valueOf( "" +goodsView.get("type_id") );
		}catch(NumberFormatException e){
			throw new  RuntimeException("类型不为数字");
		}
		List  propList = this.goodsTypeManager.getAttrListByTypeId( goods_id );
		if(propList==null) return propList;
		Map<String, String> propMap = (Map)goodsView.get("propMap");
		for (int i = 0; i < propList.size(); i++) {
			Attribute attribute = (Attribute) propList.get(i);
			String value = propMap.get("p" + i);
			attribute.setValue(value);
		}
		return propList;
	}
	
	
	/**
	 * 商品添加时处理相关的属性信息 是添加完后更新, 因为可能存在 有p1 到p20 个字段,所以更新要比较添加方便
	 * 
	 * @param goodsid
	 * @param propvalues
	 */
	private void saveProps(int goodsid, String[] propvalues) {
		if(propvalues!=null && propvalues.length>0){
			HashMap fields = new HashMap();
			int length = propvalues.length;
			length = length > 20 ? 20 : length; // 只支持20个自定义属性

			// 循环所有属性,按p_个数 为字段名存在 goods表中
			// 字段中存的是 值,当是下拉框时也存的是值,并不是属性的id
			for (int i = 0; i < length; i++) {
				String value = propvalues[i];
				fields.put("p" + (i + 1), value);
			}

			// 更新这个商品的属性信息
			this.baseDaoSupport.update("goods", fields, "goods_id=" + goodsid);
		}
		

	}
	


	public void perform(Object... params) {

	}
	
	public String getAuthor() {
		return "kingapex";
	}

	public String getId() {
		return "goodstype";
	}

	public String getName() {
		return "商品类型插件";
	}

	public String getType() {
		
		return null;
	}

	public String getVersion() {
		// TODO Auto-generated method stub
		return "1.0";
	}
	
	public void setGoodsTypeManager(IGoodsTypeManager goodsTypeManager) {
		this.goodsTypeManager = goodsTypeManager;
	}

 

	public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
		this.goodsCatManager = goodsCatManager;
	}


	public void setBaseDaoSupport(IDaoSupport<GoodsType> baseDaoSupport) {
		this.baseDaoSupport = baseDaoSupport;
	}


 
	
}

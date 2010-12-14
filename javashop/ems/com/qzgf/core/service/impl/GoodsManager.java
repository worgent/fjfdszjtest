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
package com.qzgf.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.utils.UploadUtil;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.support.GoodsEditDTO;
import com.enation.javashop.core.plugin.GoodsPluginBundle;
import com.enation.javashop.core.service.IPackageProductManager;
import com.qzgf.core.model.Cat;
import com.qzgf.core.model.Goods;
import com.qzgf.core.service.IGoodsCatManager;
import com.qzgf.core.service.IGoodsManager;

/**
 * Saas式Goods业务管理
 * @author kingapex
 * 2010-1-13下午12:07:07
 */
public class GoodsManager extends BaseSupport<Goods> implements IGoodsManager {
	
	private GoodsPluginBundle goodsPluginBundle;
	private IPackageProductManager packageProductManager;
	private IGoodsCatManager goodsCatManager;
	//private IDaoSupport<Goods> daoSupport;
	/**
	 * 添加商品，同时激发各种事件
	 */
	@Transactional(propagation = Propagation.REQUIRED)  
	public void add(Goods goods) {
		Map goodsMap = po2Map(goods);
		
		//触发商品添加前事件
		goodsPluginBundle.onBeforeAdd(goodsMap);
		 

	     goodsMap.put("disabled", 0);
	     goodsMap.put("create_time", System.currentTimeMillis());
	     goodsMap.put("view_count",0);
	     goodsMap.put("buy_count",0);
	     goodsMap.put("last_modify",System.currentTimeMillis());
			
			
	     this.baseDaoSupport.insert("ems_goods", goodsMap);
	     Integer goods_id  = this.baseDaoSupport.getLastId("goods");
	 	     
	     goods.setGoods_id(goods_id);
	     goodsMap.put("goods_id", goods_id); 
	     
	   //  addGoodsCount(goods.getCat_id());
	     //触发商品添加后事件
	     goodsPluginBundle.onAfterAdd(goodsMap);
		   
	}
	
	
//	private void addGoodsCount(Integer catid){
//	     
//	     //更新商品分类的商品数+1
//	     Cat cat  = this.goodsCatManager.getById( catid);
//	     String catpath  = cat.getCat_path();
//	     catpath = catpath.replace('|', ',');
//	     
//	     this.baseDaoSupport.execute("update goods_cat set goods_count=goods_count+1 where cat_id in ("+catpath+")");
//	    		
//	}
//	

 
	
	
	/**
	 * 修改商品同时激发各种事件
	 */
	@Transactional(propagation = Propagation.REQUIRED)  
	public void edit(Goods goods) {
		if(logger.isDebugEnabled()){
			 logger.debug("开始保存商品数据...");
		}		
		Map goodsMap = this.po2Map(goods);
		this.goodsPluginBundle.onBeforeEdit(goodsMap);
		this.baseDaoSupport.update("ems_goods", goodsMap, "goods_id=" + goods.getGoods_id());
		this.goodsPluginBundle.onAfterEdit(goodsMap);
		if(logger.isDebugEnabled()){
			 logger.debug("保存商品数据完成.");
		}			
	}




	/**
	 * 得到修改商品时的数据
	 * @param goods_id
	 * @return
	 */
	public GoodsEditDTO getGoodsEditData(Integer goods_id){
		GoodsEditDTO editDTO = new GoodsEditDTO();
		String sql ="select * from ems_goods  where goods_id=?";
		Map goods  = this.baseDaoSupport.queryForMap(sql, goods_id);
		
		//将本地存储的图片替换为静态资源服务器地址
		String image_file =(String)goods.get("image_file");
		if(!StringUtil.isEmpty(image_file)){
			image_file  =UploadUtil.replacePath(image_file); 
			goods.put("image_file", image_file);
		}
		List<String> htmlList  = 	goodsPluginBundle.onFillEditInputData(goods);
		editDTO.setGoods(goods);
		editDTO.setHtmlList(htmlList);
		
		return editDTO;
	}
	
	/**
	 * 读取一个商品的详细<br/>
	 * 处理由库中读取的默认图片和所有图片路径:<br>
	 * 如果是以本地文件形式存储，则将前缀替换为静态资源服务器地址。
	 */
	
	public Map get(Integer goods_id) {
		String sql ="select g.*,b.name as brand_name from "+this.getTableName("ems_goods")+" g left join "+this.getTableName("brand")+" b on g.brand_id=b.brand_id ";
		sql += "  where goods_id=?";
		Map goods  = this.daoSupport.queryForMap(sql, goods_id);
		
		String image_file = (String)goods.get("image_file");
		if(image_file!=null){
			image_file  =UploadUtil.replacePath(image_file); 
			goods.put("image_file", image_file);
		}
		
		String image_default = (String)goods.get("image_default");
		if(image_default!=null){
			image_default= UploadUtil.replacePath(image_default); 
			goods.put("image_default", image_default);
		}
		
		
		return goods;
	}
 
	private String getListSql(int disabled){
		String sql = "select g.*,b.name as brand_name ,t.name as type_name,c.name as cat_name from "
	          +this.getTableName("ems_goods")
            +" g left join "+this.getTableName("ems_goods_cat")
            +" c on g.cat_id=c.cat_id left join "+this.getTableName("brand")
            +" b on g.brand_id = b.brand_id and b.disabled=0 left join "+this.getTableName("ems_goods_type")
            +" t on g.type_id =t.type_id " 
		    +" where g.goods_type = 'normal' and g.disabled="+disabled;

		return sql;
	}
	
	/**
	 * 取得捆绑商品列表
	 * @param disabled
	 * @return
	 */
	private String getBindListSql(int disabled){
		String sql = "select g.*,b.name as brand_name ,t.name as type_name,c.name as cat_name from "
	          +this.getTableName("ems_goods")
            +" g left join "+this.getTableName("ems_goods_cat")
            +" c on g.cat_id=c.cat_id left join "+this.getTableName("brand")
            +" b on g.brand_id = b.brand_id left join "+this.getTableName("ems_goods_type")
            +" t on g.type_id =t.type_id"
	    +" where g.goods_type = 'bind' and g.disabled="+disabled;
		return sql;
	}
	
	
	/**
	 * 后台搜索商品
	 * @param params 通过map的方式传递搜索参数
	 * @param page
	 * @param pageSize
	 * @return 
	 */
	public Page searchGoods(Integer catid,String name,String sn,String order,int page,int pageSize){
		
		String sql = getListSql(0);
		
		if(order==null){
			order="goods_id desc";
		}
		
		if(name!=null && !name.equals("")){
			sql+="  and g.name like '%"+ name +"%'" ;
		}
		
		if(sn!=null && !sn.equals("")){
			sql+="   and g.sn = '"+ sn+ "'" ;
		}
		
		if(catid!=null){
			Cat cat  =this.goodsCatManager.getById(catid);
			sql += " and  g.cat_id in(";
			sql += "select c.cat_id from " + this.getTableName("ems_goods_cat")
					+ " c where c.cat_path like '" + cat.getCat_path() + "%')  ";
		}
		
		sql += " order by g."+ order ;
		Page webpage  = this.daoSupport.queryForPage(sql, page, pageSize);
		
		return webpage;
	}
	
	/**
	 * 后台搜索商品
	 * @param params 通过map的方式传递搜索参数
	 * @param page
	 * @param pageSize
	 * @return 
	 */
	public Page searchBindGoods(String name,String sn,String order,int page,int pageSize){
		
		String sql = getBindListSql(0);
		
		if(order==null){
			order="goods_id desc";
		}
		
		if(name!=null && !name.equals("")){
			sql+="  and g.name like '%"+ name +"%'" ;
		}
		
		if(sn!=null && !sn.equals("")){
			sql+="   and g.sn = '"+ sn+ "'" ;
		}
		
		sql += " order by g."+ order ;
		Page webpage  = this.daoSupport.queryForPage(sql, page, pageSize);
		
		List<Map> list = (List<Map>) (webpage.getResult());
		
		for(Map map:list){
			List productList = packageProductManager.list(Integer.valueOf(map.get("goods_id").toString()));
			productList = productList == null ? new ArrayList() : productList;
			map.put("productList", productList);
		}
		
		return webpage;
	}
	
	
	/**
	 * 读取商品回收站列表
	 * @param name
	 * @param sn
	 * @param order
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page pageTrash(String name,String sn,String order,int page,int pageSize){
		
		String sql = getListSql(1);
		if(order==null){
			order="goods_id desc";
		}
		
		if(name!=null && !name.equals("")){
			sql+="  and g.name like '%"+ name +"%'" ;
		}
		
		if(sn!=null && !sn.equals("")){
			sql+="   and g.sn = '"+ sn+ "'" ;
		}
		
		sql += " order by g."+ order ;
 
		Page webpage  = this.daoSupport.queryForPage(sql, page, pageSize);
		
		return webpage;
	}
	
	


	/**
	 * 批量将商品放入回收站
	 * @param ids
	 */
	public void delete(Integer[] ids){
		if(ids==null) return ;

		String id_str = StringUtil.arrayToString(ids, ",");
		String sql = "update  ems_goods set disabled=1  where goods_id in ("+ id_str +")";
		this.baseDaoSupport.execute(sql);
	}
	
	
	
	
	/**
	 * 还原
	 * @param ids
	 */
	public void  revert(Integer[] ids){
		if(ids==null) return ;
		String id_str = StringUtil.arrayToString(ids, ",");
		String sql = "update  ems_goods set disabled=0  where goods_id in ("+id_str+")";
		this.baseDaoSupport.execute(sql);
	}
	
	/**
	 * 清除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void clean(Integer[] ids){
		if(ids==null) return ;
		this.goodsPluginBundle.onGoodsDelete(ids);
		
		String id_str = StringUtil.arrayToString(ids, ",");
		String sql ="delete  from ems_goods  where goods_id in ("+id_str+")";
		this.baseDaoSupport.execute(sql);
	 
	}
	

	
	public List list(Integer[] ids) {
		if(ids==null || ids.length==0) return new ArrayList();
		String idstr = StringUtil.arrayToString(ids, ",");
		String sql ="select * from ems_goods where goods_id in("+idstr+")";
		return this.baseDaoSupport.queryForList(sql);
	}
	
	public List<String> fillAddInputData(){
		return goodsPluginBundle.onFillAddInputData();
	}



	public GoodsPluginBundle getGoodsPluginBundle() {
		return goodsPluginBundle;
	}



	public void setGoodsPluginBundle(GoodsPluginBundle goodsPluginBundle) {
		this.goodsPluginBundle = goodsPluginBundle;
	}
	
	/**
	 * 将po对象中有属性和值转换成map
	 * 
	 * @param po
	 * @return
	 */
	protected Map po2Map(Object po) {
		Map poMap = new HashMap();
		Map map = new HashMap();
		try {
			map = BeanUtils.describe(po);
		} catch (Exception ex) {
		}
		Object[] keyArray = map.keySet().toArray();
		for (int i = 0; i < keyArray.length; i++) {
			String str = keyArray[i].toString();
			if (str != null && !str.equals("class")) {
				if (map.get(str) != null) {
					poMap.put(str, map.get(str));
				}
			}
		}
		return poMap;
	}


	public IPackageProductManager getPackageProductManager() {
		return packageProductManager;
	}


	public void setPackageProductManager(
			IPackageProductManager packageProductManager) {
		this.packageProductManager = packageProductManager;
	}


	
	public Goods getGoods(Integer goods_id) {
		Goods goods = this.baseDaoSupport.queryForObject("select * from ems_goods where goods_id=?", Goods.class, goods_id);
		return goods;
	}


	public IGoodsCatManager getGoodsCatManager() {
		return goodsCatManager;
	}


	public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
		this.goodsCatManager = goodsCatManager;
	}

	
	@Transactional(propagation = Propagation.REQUIRED)  
	public void batchEdit() {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String[] ids = request.getParameterValues("goodsidArray");
		String[] names = request.getParameterValues("name");
		String[] prices =  request.getParameterValues("price");
		String[] cats =  request.getParameterValues("catidArray");
		String[] market_enable =  request.getParameterValues("market_enable");
		String[] store =  request.getParameterValues("store");
		
		String sql ="";
		
	 
		for(int i=0;i<ids.length;i++){
			sql ="";
			 if(names!=null && names.length>0){
				 if(!sql.equals("")) sql+=",";
				 sql+=" name='"+ names[i] +"'"; 
			 }
			 
			 if(prices!=null && prices.length>0){
				 if(!sql.equals("")) sql+=",";
				 sql+=" price=" + prices[i];
			 }
			 if(cats!=null && cats.length>0){
				 if(!sql.equals("")) sql+=",";
				 sql+=" cat_id=" + cats[i];
			 }			 
			 if(store!=null && store.length>0){
				 if(!sql.equals("")) sql+=",";
				 sql+=" store=" + store[i];
			 }
			 if(market_enable!=null && market_enable.length>0){
				 if(!sql.equals("")) sql+=",";
				 sql+=" market_enable=" + market_enable[i];
			 }					 
			 sql= "update  ems_goods set "+sql +" where goods_id=?";
			 this.baseDaoSupport.execute(sql, ids[i]);
			  
		}
	}

}

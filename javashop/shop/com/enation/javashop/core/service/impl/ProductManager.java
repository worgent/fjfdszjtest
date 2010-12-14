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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.EopSetting;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.utils.UploadUtil;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Product;
import com.enation.javashop.core.model.SpecValue;
import com.enation.javashop.core.model.Specification;
import com.enation.javashop.core.service.IProductManager;

/**
 * 货品管理
 * @author kingapex
 *2010-3-9下午06:27:48
 */
public class ProductManager extends BaseSupport<Product> implements IProductManager {
	
	@Transactional(propagation = Propagation.REQUIRED)  
	public void add(List<Product> productList) {
		
		 if(productList.size()>0){
			 this.baseDaoSupport.execute("delete from product  where goods_id=?", productList.get(0).getGoods_id());
			 this.baseDaoSupport.execute("delete from  goods_spec  where goods_id=?", productList.get(0).getGoods_id());
		 }
		 
		 for(Product product:productList){
			 this.baseDaoSupport.insert("product", product);
			 int product_id = this.baseDaoSupport.getLastId("product");
			 
			 //货品对应的规格组合
			 List<SpecValue> specList = product.getSpecList();
			 
			 for(SpecValue specvalue:specList){
				 this.daoSupport.execute(
						 "insert into "+ this.getTableName("goods_spec")+"(spec_id,spec_value_id,goods_id,product_id)values(?,?,?,?)", 
						 specvalue.getSpec_id(),specvalue.getSpec_value_id(),product.getGoods_id(),product_id);
			 }
		 }
		 
	}

	
	public Product get(Integer productid) {
		String sql ="select * from product where product_id=?";
		return this.baseDaoSupport.queryForObject(sql, Product.class, productid);
	}
	
	
	/**
	 * 形成list里实体(spec)放入子(specvlaue)list的效果
	 */
	
	public List<Specification> listSpecs(Integer goodsId) {
 
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct s.spec_id,s.spec_type,s.spec_name,sv.spec_value_id,sv.spec_value,sv.spec_image ,gs.goods_id as goods_id ");
		sql.append(" from ");
		
		sql.append(this.getTableName("specification"));
		sql.append(" s,");
		
		sql.append(this.getTableName("spec_values"));
		sql.append(" sv,");
		
		sql.append(this.getTableName("goods_spec"));
		sql.append(" gs ");
		
		sql.append("where s.spec_id = sv.spec_id  and gs.spec_value_id = sv.spec_value_id and gs.goods_id=?");
		
		List<Map> list  =this.daoSupport.queryForList(sql.toString(), goodsId);
			
		List<Specification> specList = new ArrayList<Specification>();
		Integer spec_id =0;
		Specification spec =null;
		for(Map map: list){
			Integer dbspecid =Integer.valueOf( map.get("spec_id").toString() );
			List<SpecValue> valueList ;
		
			if( spec_id.intValue() != dbspecid.intValue() ){
				spec_id = dbspecid;
				valueList  = new ArrayList<SpecValue>();
				 
				  spec  = new Specification();
				spec.setSpec_id( dbspecid);
				spec.setSpec_name(map.get("spec_name").toString());
				
				if(EopSetting.DBTYPE.equals("2")){
					BigDecimal type = (BigDecimal)map.get("spec_type");
					spec.setSpec_type(type.intValue());
				}else
				spec.setSpec_type((Integer)map.get("spec_type"));
				
				specList.add(spec);
				
				spec.setValueList(valueList);
			}else{
				valueList = spec.getValueList();
			}
			
			SpecValue value  = new SpecValue();
			value.setSpec_value(map.get("spec_value").toString());
			value.setSpec_value_id(Integer.valueOf( map.get("spec_value_id").toString() ));
			String spec_img  = (String)map.get("spec_image");
			
			//将本地中径替换为静态资源服务器地址
			if( spec_img!=null ){
				spec_img  =UploadUtil.replacePath(spec_img); 
			}
			value.setSpec_image(spec_img);
			
			valueList.add(value);
		}
		
		return specList ;
	}

	
	
	/**
	 * 读取某个商品的货品列表
	 */
	
	public List<Product> list(Integer goodsId) {
		String sql ="select * from product where goods_id=?";
		List<Product> prolist = baseDaoSupport.queryForList(sql,Product.class, goodsId);
		
		sql="select sv.*,gs.product_id product_id from  "+ this.getTableName("goods_spec")+"  gs inner join "+this.getTableName("spec_values")+"  sv on gs.spec_value_id = sv.spec_value_id where  gs.goods_id=?" ;
		 
		//sql="select * from "+ this.getTableName("goods_spec")+" gs,"+this.getTableName("spec_values")+" sv where gs.spec_value_id = sv.spec_value_id and  gs.goods_id=? ";
		//System.out.println(sql);
		List<Map> gsList  = this.daoSupport.queryForList(sql, goodsId);
		
		for(Product pro:prolist){
			
			for(Map gs :gsList){
				
				Integer productid;
				if(EopSetting.DBTYPE.equals("2")){
					  productid  = ((BigDecimal)gs.get("product_id")).intValue();
				}else{
					productid =  ( (Integer)gs.get("product_id") ).intValue();
				}
				
				//是这个货品的规格
				//则压入到这个货品的规格中
				//用到了spec_value_id
				if(  pro.getProduct_id().intValue()  ==   productid  ){ 
					SpecValue spec = new SpecValue();
					spec.setSpec_value_id( (Integer)gs.get("spec_value_id")  );
					spec.setSpec_id( (Integer)gs.get("spec_id"));
					spec.setSpec_image( (String)gs.get("spec_image"));
					spec.setSpec_value((String)gs.get("spec_value"));
					spec.setSpec_type((Integer) gs.get("spec_type"));
					pro.addSpec(spec);
				}
				
			}
		}
		return prolist;
	}
	
	public void delete(Integer[] goodsid){
		String id_str = StringUtil.arrayToString(goodsid, ",");
		String sql ="delete from goods_spec where goods_id in ("+ id_str +")";
		this.baseDaoSupport.execute(sql);
		
		sql ="delete from product where goods_id in ("+ id_str +")";
		this.baseDaoSupport.execute(sql);
	}

	
	public Page list(String name,String sn,int pageNo, int pageSize, String order) {
		order = order == null ? "product_id asc" : order;
		StringBuffer sql = new StringBuffer();
		sql.append("select p.* from " + this.getTableName("product") + " p left join " + this.getTableName("goods") + " g on g.goods_id = p.goods_id ");
		sql.append(" where g.disabled=0");
		if(!StringUtil.isEmpty(name)){
			sql.append(" and g.name like '%");
			sql.append(name);
			sql.append("%'");
		}
		if(!StringUtil.isEmpty(sn)){
			sql.append(" and g.sn = '");
			sql.append(sn);
			sql.append("'");
		}
		
		sql.append(" order by " + order);
		return this.daoSupport.queryForPage(sql.toString(), pageNo, pageSize);
	}
	
	
	
	public List list(Integer[] productids) {
		if(productids==null || productids.length==0) return new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select p.* from " + this.getTableName("product") + " p left join " + this.getTableName("goods") + " g on g.goods_id = p.goods_id ");
		sql.append(" where g.disabled=0");
		sql.append(" and p.product_id in(");
		sql.append(StringUtil.arrayToString(productids, ","));
		sql.append(")");
		
		return  this.daoSupport.queryForList(sql.toString());
	}

	

	
	public Product getByGoodsId(Integer goodsid) {

		String sql ="select * from product where goods_id=?";
		List<Product> proList  =this.baseDaoSupport.queryForList(sql, Product.class, goodsid);
		if(proList==null || proList.isEmpty()){
		return null;
		}
		return proList.get(0);
	}

}

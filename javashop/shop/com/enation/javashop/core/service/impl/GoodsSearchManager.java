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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.utils.UploadUtil;
import com.enation.framework.database.Page;
import com.enation.framework.database.StringMapper;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Attribute;
import com.enation.javashop.core.model.support.GoodsView;
import com.enation.javashop.core.service.IGoodsSearchManager;
import com.enation.javashop.core.service.IGoodsTypeManager;

public class GoodsSearchManager extends BaseSupport implements
		IGoodsSearchManager {

	private IGoodsTypeManager goodsTypeManager;
	
	public Page search(int page, int pageSize, Map<String, String> params) {

		String cat_path = params.get("cat_path");
		String order = params.get("order");
		String brandStr = params.get("brandStr");
		String propStr = params.get("propStr");
		String keyword = params.get("keyword");
		String minPrice = params.get("minPrice");
		String maxPrice = params.get("maxPrice");
		String tagids = params.get("tagids");		
		int typeid= Integer.valueOf( params.get("typeid") );
		
		List list = this.listByCatId(typeid,cat_path, page, pageSize, order, brandStr,
				propStr, keyword, minPrice, maxPrice, tagids);
		long count = this.countByCatId(typeid,cat_path, brandStr, propStr, keyword,
				minPrice, maxPrice, tagids);

		Page webPage = new Page(0, count, pageSize, list);
		return webPage;
	}

	
	public List[] getPropListByCat(final int type_id, String cat_path, String brand_str,
			String propStr) {
		
		
		List<Attribute> temp_prop_list = this.goodsTypeManager
				.getAttrListByTypeId(type_id); // 这个类型的属性
		temp_prop_list= temp_prop_list== null ?new ArrayList<Attribute>():temp_prop_list;
		
		if (propStr != null && !propStr.equals("")) {
			String[] s_ar = propStr.split(",");

			for (int i = 0; i < s_ar.length; i++) {
				String[] value = s_ar[i].split("\\_");
				int index = Integer.valueOf(value[0]).intValue();
				Attribute attr = temp_prop_list.get(index);
				if (attr.getType() == 3) { // 移除递进式搜索的属性
					// temp_prop_list.remove(index);
					attr.setHidden(1); // 在过滤器中这个属性不显示
				} else {
					attr.setHidden(0);
				}

			}

		}

		List temp_brand_list = null;
		// 如果进行了品牌筛选 则不显示品牌 筛选器了
		if (brand_str == null || brand_str.equals("")) {
			temp_brand_list = this.goodsTypeManager
					.getBrandListByTypeId(type_id);// 这个类型关联的品牌
		} else {
			temp_brand_list = new ArrayList();
		}

		final List<Attribute> propList = temp_prop_list; // 属性过滤器
		final List brandList = temp_brand_list; // 品牌过滤器

		String sql = "select g.* from "+this.getTableName("goods")+" g where g.disabled=0 and g.market_enable=1 and g.cat_id in(";
		sql += "select c.cat_id from "+this.getTableName("goods_cat")+" c where c.cat_path like '"
				+ cat_path + "%')";
		
		sql += this.buildTermForByCat(type_id,brand_str, propStr);

		RowMapper mapper = new RowMapper() {

			public Object mapRow(ResultSet rs, int arg1) throws SQLException {

				GoodsView goods = new GoodsView();

				if (rs.getInt("type_id") == type_id) { // 是这个类型的属性
					for (int i = 0; i < 20; i++) {

						if (i >= propList.size())
							break;

						String value = rs.getString("p" + (i + 1));

						Attribute prop = propList.get(i);

						if (prop.getType() == 3 && value != null
								&& !value.toString().equals("")) { // 渐进式搜索
							int[] nums = prop.getNums();
							int pos = Integer.valueOf(value);
							nums[pos] = nums[pos] + 1;
						}

					}
				}

				for (int i = 0; i < brandList.size(); i++) {
					Map brand = (Map) brandList.get(i);
					if (rs.getInt("brand_id") == ((Integer) brand
							.get("brand_id")).intValue()) {// 是属于这个品牌的。

						Object obj_num = brand.get("num");
						if (obj_num == null) {
							obj_num = 0;
						}

						int num = Integer.valueOf(obj_num.toString());
						num++;
						brand.put("num", num);
					}
				}

				return goods;
			}

		};

		this.daoSupport.queryForList(sql, mapper);
//		this.jdbcTemplate.query(sql, mapper);

		List[] props = new List[2];
		props[0] = propList;
		props[1] = brandList;

		return props;
	}

	/**
	 * 读取某个类别下的 所有规格数据
	 * 
	 * @param cat_path
	 * @return
	 */
	private List getSpecListByCatId(String cat_path) {

		String sql = "select s.* from " + this.getTableName("product") + " s,"
				+ this.getTableName("goods")
				+ " g  where s.goods_id=g.goods_id and  g.cat_id in(";
		sql += "select c.cat_id from " + this.getTableName("goods_cat")
				+ " c where c.cat_path like '" + cat_path + "%')";

		List specList = this.daoSupport.queryForList(sql);

		return specList;
	}

	/**
	 * 读取某个类别下的商品 包括其子类别的 同时填充其规格
	 * @param cat_path
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param brand_str
	 * @param propStr
	 * @param keyword
	 * @param minPrice
	 * @param maxPrice
	 * @param tagids
	 * @return
	 * 李志富修改，<br/>
	 * 加入了tagid标签，以便于取得如“全部物价商品”这样的列表
	 */
	private List listByCatId(int typeid,String cat_path, int page, int pageSize,
			String order, String brand_str, String propStr, String keyword,
			String minPrice, String maxPrice, String tagids) {

		// 首先读取这个类别下的所有商品集合的规格信息
		final List goods_spec_list = this.getSpecListByCatId(cat_path);

		if ("1".equals(order)) { // 按发布时间 新->旧
			order = "last_modify desc";
		} else if ("1".equals(order)) {// 按发布时间 旧->新
			order = "last_modify asc";
		} else if ("2".equals(order)) {// 按发布时间 旧->新
			order = "last_modify asc";
		} else if ("3".equals(order)) {// 按价格 从高到低
			order = "price desc";
		} else if ("4".equals(order)) {// 按发布时间 旧->新
			order = "price asc";
		} else if ("5".equals(order)) {// 访问次数
			order = "view_count desc";
		} else if ("6".equals(order)) {// 购买次数
			order = "buy_count asc";
		} else if (order == null || order.equals("")) {
			order = "last_modify desc";
		}

		String sql = "select g.* from " + this.getTableName("goods")
				+ " g where g.disabled=0 and market_enable=1 ";

		if (cat_path != null) {
			sql += " and  g.cat_id in(";
			sql += "select c.cat_id from " + this.getTableName("goods_cat")
					+ " c where c.cat_path like '" + cat_path + "%')  ";
		}

		sql += buildTermForByCat(typeid,brand_str, propStr, keyword, minPrice,
				maxPrice, tagids);

		sql += " order by " + order;

		RowMapper mapper = new RowMapper() {

			public Object mapRow(ResultSet rs, int arg1) throws SQLException {

				GoodsView goods = new GoodsView();
				goods.setName(rs.getString("name"));
				goods.setGoods_id(rs.getInt("goods_id"));
				goods.setImage_default(rs.getString("image_default"));
				goods.setMktprice(rs.getDouble("mktprice"));
				goods.setPrice(rs.getDouble("price"));
				goods.setCreate_time(rs.getLong("create_time"));
				goods.setLast_modify(rs.getLong("last_modify"));
				goods.setType_id(rs.getInt("type_id"));
				goods.setStore(rs.getInt("store"));
				List specList = getSpecList(goods.getGoods_id(),
						goods_spec_list);
				goods.setSpecList(specList);
				goods.setHasSpec(rs.getInt("have_spec"));
				
				
				
				//	lzy	add
				
				goods.setIntro(rs.getString("intro"));
				String  image_file = rs.getString("image_file");
				if(image_file !=null ){
					image_file = UploadUtil.replacePath(image_file);
					goods.setImage_file(image_file);
				}
				goods.setCat_id(rs.getInt("cat_id"));
				
				// 如果商品没有规格，查找唯一的货品id
				if(goods.getHasSpec()==0 && specList!=null &&!specList.isEmpty()) 
				goods.setProductid(  (Integer) ((Map)specList.get(0)).get("product_id")   );
				
				//lzf add
				Map propMap = new HashMap();
				
				for(int i=0;i<20;i++){
					String value = rs.getString("p" + (i+1));
					propMap.put("p"+(i+1),value);
				}
				goods.setPropMap(propMap);
				//lzf add end
				return goods;
			}

		};

		List goodslist = this.daoSupport.queryForList(sql, page, pageSize,
				mapper);// this.queryForPageList(sql, mapper, page, pageSize);

		return goodslist;
	}

	/**
	 * 读取某个类别下所有的商品数 包括其子类的
	 * @param cat_path
	 * @param brand_str
	 * @param propStr
	 * @param keyword
	 * @param minPrice
	 * @param maxPrice
	 * @param tagids
	 * @return
	 * 李志富修改<br/>
	 * 加入了tagid标签，以便于取得如“全部物价商品”这样的列表
	 */
	private long countByCatId(int typeid,String cat_path, String brand_str,
			String propStr, String keyword, String minPrice, String maxPrice, String tagids) {
		String sql = "select count(0) from " + this.getTableName("goods")
				+ " g where g.disabled=0 and market_enable=1 ";

		if (cat_path != null) {
			sql += " and g.cat_id in(";
			sql += "select c.cat_id from " + this.getTableName("goods_cat")
					+ " c where c.cat_path like '" + cat_path + "%')";
		}

		sql += this.buildTermForByCat(typeid,brand_str, propStr, keyword, minPrice,
				maxPrice, tagids);

		long count = this.daoSupport.queryForLong(sql);
		return count;
	}

	/**
	 * 读取某个商品的规格列表
	 * 
	 * @param goods_id
	 * @param specList
	 *            某个商品集合的所有规格
	 * @return
	 */
	private List getSpecList(int goods_id, List specList) {
		List list = new ArrayList();

		for (int i = 0; i < specList.size(); i++) {
			Map spec = (Map) specList.get(i);
			Integer temp_id = (Integer) spec.get("goods_id");
			if (temp_id.intValue() == goods_id) {
				list.add(spec);
			}
		}

		return list;
	}

	/**
	 * 构建查询类别下的商品的条件
	 * 
	 * @param cat_path
	 * @param order
	 * @return
	 */
	private String buildTermForByCat(int typeid,String brand_str, String propStr,
			String keyword, String minPrice, String maxPrice) {
		StringBuffer sql = new StringBuffer();

		sql.append(buildTermForByCat(typeid,brand_str, propStr));

		if (keyword != null) {
			sql.append(" and g.name like '%");
			sql.append(keyword);
			sql.append("%'");
		}

		if (minPrice != null) {
			sql.append(" and  g.price>=");
			sql.append(minPrice);
		}

		if (maxPrice != null) {
			sql.append(" and g.price<=");
			sql.append(maxPrice);
		}

		return sql.toString();
	}
	
	private String buildTermForByCat(int typeid,String brand_str, String propStr,
			String keyword, String minPrice, String maxPrice, String tagids){
		StringBuffer sql = new StringBuffer(buildTermForByCat(typeid,brand_str, propStr,
				keyword, minPrice, maxPrice));
		if(tagids != null){
			String filter= this.goodsIdInTags(tagids);
			filter =filter.equals("")?"-1":filter;
			sql.append( " and goods_id in(" +filter+")" );
		}
		return sql.toString();
	}
	
	private String goodsIdInTags(String tags){
		String sql ="select rel_id from tag_rel where tag_id in (" +tags+")";
		List<String> goodsIdList = this.baseDaoSupport.queryForList(sql, new StringMapper());
		return StringUtil.listToString(goodsIdList, ",");
	}

	private String buildTermForByCat(int typeid,String brand_str, String propStr) {

		
		StringBuffer sql = new StringBuffer();

		if (brand_str != null && !brand_str.equals("")) {
			brand_str = "-1," + brand_str.replaceAll("\\_", ",");
			sql.append(" and g.brand_id in(");
			sql.append(brand_str);
			sql.append(")");

		}

		// 关于属性的过滤
		//属性值示例: 0_1,0_2
		if (propStr != null && !propStr.equals("")) {
			List<Attribute> prop_list = this.goodsTypeManager.getAttrListByTypeId(typeid); // 这个类型的属性
			prop_list= prop_list== null ?new ArrayList<Attribute>():prop_list;			
			String[] s_ar = propStr.split(",");
	
			for (int i = 0; i < s_ar.length; i++) {
				String[] value = s_ar[i].split("\\_");
				int index = Integer.valueOf(value[0]).intValue();
				Attribute attr = prop_list.get(index);
				int type = attr.getType();
				if(type==2 || type==5 ) continue; //不可搜索跳过
				
				sql.append(" and g.p" + (index + 1));
								
				if(type==1){ //输入项搜索用like
					sql.append(" like'%");
					sql.append(value[1]);
					sql.append("%'");
				}
				if(type==3 || type==4){ //渐进式搜索直接=
					sql.append("='");
					sql.append(value[1]);
					sql.append("'");
				}				

			}
		} 

		return sql.toString();
	}
	


	public IGoodsTypeManager getGoodsTypeManager() {
		return goodsTypeManager;
	}

	public void setGoodsTypeManager(IGoodsTypeManager goodsTypeManager) {
		this.goodsTypeManager = goodsTypeManager;
	}
	
	

}

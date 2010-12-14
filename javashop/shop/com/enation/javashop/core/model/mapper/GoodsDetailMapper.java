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
package com.enation.javashop.core.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.jdbc.core.RowMapper;

import com.enation.javashop.core.model.support.GoodsView;
import com.enation.javashop.core.utils.GoodsUtils;

public class GoodsDetailMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		GoodsView  goods = new GoodsView();
		goods.setName(rs.getString("name"));
		goods.setSn(rs.getString("sn"));
		goods.setUnit(rs.getString("unit"));
		goods.setWeight(rs.getDouble("weight"));
		goods.setGoods_id(rs.getInt("goods_id"));
		goods.setImage_default(rs.getString("image_default"));
		goods.setImage_file(rs.getString("image_file"));
		goods.setMktprice(rs.getDouble("mktprice"));
		goods.setMarket_enable(rs.getInt("market_enable"));
		goods.setPrice(rs.getDouble("price"));
		goods.setCreate_time(rs.getLong("create_time"));
		goods.setLast_modify(rs.getLong("last_modify"));
		goods.setBrand_name(rs.getString("brand_name"));
		goods.setParams(rs.getString("params"));
		goods.setIntro(rs.getString("intro"));
		goods.setBrief(rs.getString("brief"));
		goods.setPage_title(rs.getString("page_title"));
		goods.setMeta_keywords(rs.getString("meta_keywords"));
		goods.setMeta_description(rs.getString("meta_description"));
		goods.setSpecs(rs.getString("specs"));
		List specList = GoodsUtils.getSpecList(goods.getSpecs());
 		goods.setSpecList(specList);
		goods.setType_id(rs.getInt("type_id"));
		goods.setBrand_id(rs.getInt("brand_id"));
		goods.setCat_id(rs.getInt("cat_id"));
		goods.setAdjuncts(rs.getString("adjuncts"));
		if(goods.getAdjuncts()!=null && goods.getAdjuncts().equals("")){//由于update的时候，不能保存null故保存为空串。但是原有程序之对null做了判断。
			goods.setAdjuncts(null);
		}
		goods.setStore(rs.getInt("store"));
		goods.setDisabled(rs.getInt("disabled"));
		goods.setMarket_enable(rs.getInt("market_enable"));
		
		
		Map propMap = new HashMap();
		
		for(int i=0;i<20;i++){
			String value = rs.getString("p" + (i+1));
			propMap.put("p"+i,value);
		}
		goods.setPropMap(propMap);
		return goods;
	}
	
 


	
	
} 

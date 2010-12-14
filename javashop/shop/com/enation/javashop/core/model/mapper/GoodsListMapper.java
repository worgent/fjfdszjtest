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
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.enation.javashop.core.model.support.GoodsView;

/**
 * 商品列表mapper
 * @author kingapex
 * 2010-7-16下午01:48:59
 */
public class GoodsListMapper implements RowMapper {

	/**
	 * 返回{@link GooodsView}
	 * 在本方法中对属性进行了读取和处理，并放入到了 propMap属性
	 */
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		GoodsView  goods = new GoodsView();
		goods.setName(rs.getString("name"));
		goods.setGoods_id(rs.getInt("goods_id"));
		goods.setImage_default(rs.getString("image_default"));
		goods.setMktprice(rs.getDouble("mktprice"));
		goods.setPrice(rs.getDouble("price"));
		goods.setCreate_time(rs.getLong("create_time"));
		goods.setLast_modify(rs.getLong("last_modify"));
		
		Map propMap = new HashMap();
		
		for(int i=0;i<20;i++){
			String value = rs.getString("p" + (i+1));
			propMap.put("p"+(i+1),value);
		}
		goods.setPropMap(propMap);
	
		return goods;
	}

} 

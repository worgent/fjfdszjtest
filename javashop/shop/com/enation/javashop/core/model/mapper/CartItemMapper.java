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

import org.springframework.jdbc.core.RowMapper;

import com.enation.eop.EopSetting;
import com.enation.eop.utils.UploadUtil;
import com.enation.javashop.core.model.support.CartItem;

public class CartItemMapper implements RowMapper {

	
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CartItem cartItem = new CartItem();
		cartItem.setId(rs.getInt("cart_id"));
		cartItem.setProduct_id(rs.getInt("product_id"));
		cartItem.setGoods_id(rs.getInt("goods_id"));
		cartItem.setName(rs.getString("name"));
		cartItem.setMktprice(rs.getDouble("mktprice"));
		cartItem.setPrice(rs.getDouble("price"));
		cartItem.setCoupPrice(rs.getDouble("price")); //优惠价格默认为销售价，则优惠规则去改变
		String image_default =  rs.getString("image_default");
		if(image_default!=null ){
			image_default  =UploadUtil.replacePath(image_default);
		}
		cartItem.setImage_default(image_default);
		cartItem.setNum(rs.getInt("num"));
		cartItem.setPoint(rs.getInt("point"));
		cartItem.setItemtype(rs.getInt("itemtype"));
		if( cartItem.getItemtype().intValue() ==  0){
			cartItem.setAddon(rs.getString("addon"));
		}
		//赠品设置其限购数量
		if( cartItem.getItemtype().intValue() ==  2){
			cartItem.setLimitnum(rs.getInt("limitnum"));
		}
		 
		if( cartItem.getItemtype().intValue() ==  1){
			cartItem.setSn(rs.getString("sn"));
		}
		
		if( cartItem.getItemtype().intValue() ==  0){
			cartItem.setName(  cartItem.getName() +"("+rs.getString("specs") +")");
		}		
		
		return cartItem;
	}

}

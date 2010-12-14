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
import com.enation.javashop.core.model.FreeOffer;

/**
 * 赠品Mapper
 * 将本地存储的图片转为静态资源服务器的地址
 * @author kingapex
 * 2010-7-16下午06:08:24
 */
public class GiftMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		FreeOffer freeOffer  = new FreeOffer();
		freeOffer.setCat_name(rs.getString("cat_name"));
		freeOffer.setDescript(rs.getString("descript"));
		freeOffer.setDisabled(rs.getInt("disabled"));
		freeOffer.setEnddate(rs.getLong("enddate"));
		freeOffer.setFo_category_id(rs.getInt("fo_category_id"));
		freeOffer.setFo_id(rs.getInt("fo_id"));
		freeOffer.setFo_name(rs.getString("fo_name"));
		freeOffer.setLimit_purchases(rs.getInt("limit_purchases"));
		freeOffer.setList_thumb(rs.getString("list_thumb"));
		freeOffer.setLv_ids(rs.getString("lv_ids"));
		String pic  = rs.getString("pic");
		if(pic != null){
			pic  =UploadUtil.replacePath(pic);
		}
		freeOffer.setPic(pic);
		freeOffer.setPrice(rs.getDouble("price"));
		freeOffer.setPublishable(rs.getInt("publishable"));
		freeOffer.setRecommend(rs.getInt("recommend"));
		freeOffer.setRepertory(rs.getInt("repertory"));
		freeOffer.setScore(rs.getInt("score"));
		freeOffer.setSorder(rs.getInt("sorder"));
		freeOffer.setStartdate(rs.getLong("Startdate"));
		freeOffer.setSynopsis(rs.getString("synopsis"));
		freeOffer.setWeight(rs.getDouble("weight"));
		return freeOffer;
	}

}

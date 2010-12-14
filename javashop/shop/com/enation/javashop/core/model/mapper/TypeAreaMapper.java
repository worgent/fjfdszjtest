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

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.RowMapper;

import com.enation.javashop.core.model.support.TypeArea;
import com.enation.javashop.core.model.support.TypeAreaConfig;

/**
 * 配送地区-配送方式关联表mapper
 * @author kingapex
 *2010-3-30上午08:58:02
 */
public class TypeAreaMapper implements RowMapper {

	
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		TypeArea typeArea = new TypeArea();
		typeArea.setArea_id_group(rs.getString("area_id_group"));
		typeArea.setArea_name_group(rs.getString("area_name_group"));
		typeArea.setConfig(rs.getString("config"));
		typeArea.setExpressions(rs.getString("expressions"));
		typeArea.setHas_cod(rs.getInt("has_cod"));
		typeArea.setType_id(rs.getInt("type_id"));
		JSONObject configJsonObject = JSONObject.fromObject(typeArea.getConfig());
		typeArea.setTypeAreaConfig((TypeAreaConfig) JSONObject.toBean(configJsonObject,TypeAreaConfig.class));
		return typeArea;
	}

}

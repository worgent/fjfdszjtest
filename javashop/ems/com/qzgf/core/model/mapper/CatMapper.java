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
package com.qzgf.core.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.enation.eop.utils.UploadUtil;
import com.qzgf.core.model.Cat;

/**
 * 类别Mapper
 * 会将本地文件存储的图片地址前缀替换为静态资源服务器地址。
 * @author kingapex
 * 2010-7-16下午03:41:42
 */
public class CatMapper implements RowMapper {

	
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		Cat cat =  new Cat();
		cat.setCat_id(rs.getInt("cat_id"));
		cat.setCat_order(rs.getInt("cat_order"));
		cat.setCat_path(rs.getString("cat_path"));
		String image = rs.getString("image");
		if(image!=null){
			image  =UploadUtil.replacePath(image);
		}
		cat.setImage(image);
		cat.setList_show(rs.getString("list_show"));
		cat.setName(rs.getString("name"));
		cat.setParent_id(rs.getInt("parent_id"));
		cat.setType_id(rs.getInt("type_id"));
		cat.setType_name(rs.getString("type_name"));
		return cat;
	}

}

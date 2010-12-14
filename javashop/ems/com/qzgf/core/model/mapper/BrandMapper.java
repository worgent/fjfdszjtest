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
import com.qzgf.core.model.Brand;

/**
 * 品牌Mapper<br>
 * 会将本地文件存储的图片地址前缀替换为静态资源服务器地址。
 * @author kingapex
 * 2010-7-16下午03:17:28
 */
public class BrandMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		Brand brand = new Brand();
		brand.setBrand_id(rs.getInt("brand_id"));
		brand.setBrief(rs.getString("brief"));
		String logo = rs.getString("logo");
		if(logo!=null){
			logo  =UploadUtil.replacePath(logo);
		}
		brand.setLogo(logo);
		brand.setName(rs.getString("name"));
		brand.setUrl(rs.getString("url"));
		return brand;
	}

}

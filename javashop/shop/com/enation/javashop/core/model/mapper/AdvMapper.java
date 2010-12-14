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
import com.enation.javashop.core.model.Adv;
/**
 * 广告mapper<br>
 * 将附件url本地格式转为静态资源服务器地址<br>
 * 读取非adv表以外的字段cname,在sql语句中必须给出
 * @author kingapex
 * 2010-7-17上午11:02:24
 */
public class AdvMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		Adv adv = new Adv();
		adv.setAcid(rs.getInt("acid"));
		adv.setAid(rs.getInt("aid"));
		adv.setAname(rs.getString("aname"));
		String atturl = rs.getString("atturl");
		if(atturl!=null)  atturl  =UploadUtil.replacePath(atturl);
		adv.setAtturl(atturl);
		adv.setAtype(rs.getInt("atype"));
		adv.setBegintime(rs.getLong("begintime"));
		adv.setClickcount(rs.getInt("clickcount"));
		adv.setCompany(rs.getString("company"));
		adv.setContact(rs.getString("contact"));
		adv.setDisabled(rs.getString("disabled"));
		adv.setEndtime(rs.getLong("endtime"));
		adv.setIsclose(rs.getInt("isclose"));
		adv.setLinkman(rs.getString("linkman"));
		adv.setUrl(rs.getString("url"));
		
		adv.setCname(rs.getString("cname"));
		
		return adv;
	}

}

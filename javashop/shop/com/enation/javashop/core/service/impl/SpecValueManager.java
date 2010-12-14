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

import java.util.List;
import java.util.Map;

import com.enation.eop.EopSetting;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.utils.UploadUtil;
import com.enation.javashop.core.model.SpecValue;
import com.enation.javashop.core.model.mapper.SpecValueMapper;
import com.enation.javashop.core.service.ISpecValueManager;

/**
 * 规格值管理
 * @author kingapex
 *2010-3-7下午06:33:06
 */
public class SpecValueManager extends BaseSupport<SpecValue> implements ISpecValueManager {

	
	public void add(SpecValue value) {
	   this.baseDaoSupport.insert("spec_values",value);

	}



	
	public List list(Integer specId) {
		String sql ="select * from spec_values where spec_id =?";
		List valueList = this.baseDaoSupport.queryForList(sql, new SpecValueMapper() ,specId);
		return valueList;
	}
	

	
	public Map get(Integer valueId) {
		String sql ="select sv.*,s.spec_type from "+this.getTableName("spec_values")+" sv,"+ this.getTableName("specification")+" s  where sv.spec_id=s.spec_id and sv.spec_value_id =?"; 
		Map temp = this.daoSupport.queryForMap(sql, valueId);
		String spec_image = (String)temp.get("spec_image");
		if(spec_image!=null){
			spec_image  =UploadUtil.replacePath(spec_image); 
		}
		temp.put("spec_image", spec_image);
		return temp;
	}

}

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

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.impl.support.BaseSupport;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.SpecValue;
import com.enation.javashop.core.model.Specification;
import com.enation.javashop.core.service.ISpecManager;
import com.enation.javashop.core.service.ISpecValueManager;

/**
 * 规格管理
 * @author kingapex
 *2010-3-7上午11:19:20
 */
public class SpecManager extends BaseSupport<Specification>  implements ISpecManager {
	private ISpecValueManager specValueManager;
	
	/**
	 * 此版本未实现规格值排序功能
	 */
	@Transactional(propagation = Propagation.REQUIRED)  
	
	public void add(Specification spec, List<SpecValue> valueList) {
		
		this.baseDaoSupport.insert("specification", spec);
		Integer specId= this.baseDaoSupport.getLastId("specification");
		for(SpecValue value : valueList){
			value.setSpec_id(specId);
			value.setSpec_type(spec.getSpec_type());
			specValueManager.add(value);
		}
		
	}

	
	
	/**
	 * 
	 */
	public boolean checkUsed(Integer[] idArray){
		if(idArray==null) return false;
		
		String idStr = StringUtil.arrayToString( idArray,",");
		String sql  ="select count(0)  from  goods_spec where spec_id in (-1,"+idStr+")";
		
		int count  = this.baseDaoSupport.queryForInt(sql);
		if(count>0)
			return true;
		else
			return false;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)  
	public void delete(Integer[] idArray) {
		
		String idStr = StringUtil.arrayToString( idArray,",");
		String sql ="delete from specification where spec_id in (-1,"+idStr+")";
		this.baseDaoSupport.execute(sql);
		
		sql="delete from spec_values where spec_id in (-1,"+idStr+")";
		this.baseDaoSupport.execute(sql);
		
		sql="delete from goods_spec where spec_id in (-1,"+idStr+")";
		this.baseDaoSupport.execute(sql);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)  
	public void edit(Specification spec, List<SpecValue> valueList) {
		String sql ="delete from spec_values where spec_id=?";
		this.baseDaoSupport.execute(sql, spec.getSpec_id());
		this.baseDaoSupport.update("specification", spec, "spec_id="+spec.getSpec_id());
		for(SpecValue value : valueList){
			value.setSpec_id(spec.getSpec_id());
			value.setSpec_type(spec.getSpec_type());
			specValueManager.add(value);
		}		
	}

	@Transactional(propagation = Propagation.REQUIRED)  
	public List list() {
		String sql ="select * from specification";
		return this.baseDaoSupport.queryForList(sql);
	}

	public Map get(Integer spec_id){
		String sql ="select * from specification where spec_id=?";
		return this.baseDaoSupport.queryForMap(sql, spec_id);
	}
	
	public ISpecValueManager getSpecValueManager() {
		return specValueManager;
	}

	public void setSpecValueManager(ISpecValueManager specValueManager) {
		this.specValueManager = specValueManager;
	}

}

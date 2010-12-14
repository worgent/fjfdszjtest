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
package com.enation.javashop.core.service;

import java.util.List;
import java.util.Map;

import com.enation.javashop.core.model.SpecValue;
import com.enation.javashop.core.model.Specification;

/**
 * 规格管理接口
 * @author kingapex
 *2010-3-6下午03:48:59
 */
public interface ISpecManager {
	
	
	/**
	 * 检测规格是否被使用
	 * @param ids
	 * @return
	 */
	public boolean checkUsed(Integer[] ids);
	
	
	
	
	/**
	 * 读取规格列表
	 * @return
	 */
	public List list();
	
	/**
	 * 添加一种规格，同时添加其规格值
	 * @param spec
	 * @param valueList
	 */
	public void add(Specification spec,List<SpecValue> valueList);
	
	
	/**
	 * 修改一个规格，同时修改其规格值
	 * @param spec
	 * @param valueList
	 */
	public void edit(Specification spec,List<SpecValue> valueList);
	
	
	/**
	 * 删除某组规格
	 */
	public void delete(Integer[] idArray);
	

	/**
	 * 读取一个规格详细
	 * @param spec_id
	 * @return
	 */
	public Map get(Integer spec_id);
	
	
}

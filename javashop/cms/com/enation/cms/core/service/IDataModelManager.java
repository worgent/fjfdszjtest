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
package com.enation.cms.core.service;

import java.util.List;

import com.enation.cms.core.model.DataModel;

/**
 * 数据模型管理
 * @author kingapex
 * 2010-7-2下午02:25:37
 */
public interface IDataModelManager {
	/**
	 * 添加一个数据模型
	 * @param dataModel
	 */
	public void add(DataModel dataModel);
	
	
	/**
	 * 修改一个数据模型
	 * @param dataModel
	 */
	public void edit(DataModel dataModel);
	
	
	/**
	 * 删除一个数据模型
	 * @param modelid
	 */
	public void delete(Integer modelid);
	
	/**
	 * 读取所有数据模型列表
	 * @return
	 */
	public List<DataModel>  list();
	
	
	/**
	 * 获取一个数据模型
	 * @param modelid
	 * @return
	 */
	public DataModel get(Integer modelid);
	
	
	
	
}

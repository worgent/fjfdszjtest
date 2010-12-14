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

import com.enation.cms.core.model.DataField;

public interface IDataFieldManager {
	
	/**
	 * 添加某字段
	 * @param dataField
	 * @return
	 */
	public Integer add(DataField dataField );
	
	
	
	
	
	/**
	 * 修改某字段信息
	 * @param dataField
	 */
	public void edit(DataField dataField );
	
	
	/**
	 * 删除某模型的所有字段
	 * @param modelid
	 */
	public void delete(Integer modelid);
	
	
	
	/**
	 * 查询某模型下的字段列表
	 * @param modelid
	 * @return
	 */
	public List<DataField> list(Integer modelid);
	
	
	/**
	 * 读取某模型可以显示在列表中的字段列表
	 * @param modelid
	 * @return
	 */
	public List<DataField> listIsShow(Integer modelid);
	
	
	/**
	 * 获取某字段的字段详细
	 * @param fieldid
	 * @return
	 */
	public DataField get(Integer fieldid);
	
	
	/**
	 * 查询某类别下的字段列表
	 * @param catid
	 * @return
	 */
	public List<DataField> listByCatId(Integer catid);
	
	
	/**
	 *  更新字段排序
	 * @param ids
	 * @param sorts
	 */
	public void saveSort(Integer[] ids,Integer sorts[]);
	
}

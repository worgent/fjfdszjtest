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

import com.enation.cms.core.model.DataCat;

/**
 * 数据分类管理
 * @author kingapex
 * 2010-7-5上午07:19:57
 */
public interface IDataCatManager {
	
	/**
	 * 添加一个分类
	 * @param cat
	 */
	public void add(DataCat cat);
	
	/**
	 * 修改分类
	 * @param cat
	 */
	public void edit(DataCat cat);
	
	/**
	 * 删除分类
	 * @param catid
	 * @return
	 */
	public int delete(Integer catid);
	
	
	/**
	 * 获取一个文章分类详细
	 * @param catid
	 * @return
	 */
	public DataCat get(Integer catid);
	
	
	/**
	 * 读取某个分类下的所有子类，包括 子孙结眯
	 * @param parentid 分类id
	 * @return
	 */
	public List<DataCat> listAllChildren(Integer parentid);
	
	
	
	/**
	 * 更新分类的排序
	 * @param cat_ids
	 * @param cat_sorts
	 */
	public void saveSort(int[] cat_ids, int[] cat_sorts);
	
	
}

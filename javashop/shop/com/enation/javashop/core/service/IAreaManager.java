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

import com.enation.framework.database.Page;
import com.enation.javashop.core.model.DlyArea;

public interface IAreaManager {
	/**
	 * 添加配送地区
	 * @param name
	 */
	public void saveAdd(String name);
	/**
	 * 编辑配送地区
	 * @param area_id
	 * @param name
	 */
	public void saveEdit(Integer area_id,String name);
	/**
	 * 读取所有配送地区
	 * @return
	 */
	public List getAll();
	
	/**
	 * 分页读取配送地区
	 * @param order
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page pageArea(String order,int page,int pageSize);
    /**
     * 删除配送地区
     * @param id
     */
	public void delete(String id);
	/**
	 * 获取一个配送地区
	 * @param area_id
	 * @return
	 */
	public DlyArea getDlyAreaById(Integer area_id);

}

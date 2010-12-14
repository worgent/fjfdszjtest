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

import com.enation.javashop.core.model.Regions;

/**
 * 行政区划管理接口
 * 
 * @author lzf<br/>
 *         2010-3-16 下午03:05:17<br/>
 *         version 1.0<br/>
 */
public interface IRegionsManager {
	
	/**
	 * 省级单位列表
	 * @return
	 */
	public List listProvince();
	
		
	/**
	 * 某省内城市列表
	 * @param province_id
	 * @return
	 */
	public List listCity(int province_id);
	
	/**
	 * 某市行政区列表
	 * @param city_id
	 * @return
	 */
	public List listRegion(int city_id);
	
	
	/**
	 * 读取某个区域的子区域
	 * @param regionid
	 * @return
	 */
	public List listChildren(Integer regionid);
	
	/**
	 * 根据某些区域id串（","号分隔）查询全部子地区
	 * @param regionid
	 * @return
	 */
	public List listChildren(String regionid);
	
	
	/**
	 * 获取某个区域的子区域json串
	 * @param regionid
	 * @return
	 */
	public String getChildrenJson(Integer regionid);
	
	/**
	 * 取得一个地区
	 * @param region_id
	 * @return
	 */
	public Regions get(int region_id);
	
	/**
	 * 新增
	 * @param regions
	 */
	public void add(Regions regions);
	
	/**
	 * 删除
	 * @param region_id
	 */
	public void delete(int region_id);
	
	/**
	 * 修改
	 * @param regions
	 */
	public void update(Regions regions);
	
	/**
	 * 初始化/恢复地区数据。
	 */
	public void reset();

}

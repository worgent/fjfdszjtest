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

import com.enation.javashop.core.model.Cat;

/**
 * 商品类别管理
 * @author kingapex
 * 2010-1-6上午12:39:09
 */
public interface IGoodsCatManager {
	
	
 
	/**
	 * 检测类名是否存在
	 * @param name
	 * @return 存在返回真，不存在返回假
	 */
	public boolean checkname(String name,Integer catid);
	
	
	
	/**
	 * 根据类别id获取类别
	 * @param cat_id
	 * @return
	 */
	public Cat getById(int cat_id);
	
	
	
	
	/**
	 * 添加商品类别
	 * @param cat
	 */
	public void saveAdd(Cat cat);
	
	
	
	
	/**
	 * 更新商品类别
	 * @param cat
	 */
	public void update(Cat cat);
	
	
	
	
	
	/**
	 * 删除商品类别
	 * @param cat_id
	 * @return 1删除失败有子类别，0删除成功
	 */
	public int delete(int cat_id);
	
	
	
	
	
	
	/**
	 * 获取某个类别的所朋子类，所有的子孙
	 * @param cat_id
	 * @return
	 */
	public List<Cat> listAllChildren(Integer cat_id);
	
	
	/**
	 * 读取某类别下的子类别，只是儿子
	 * @param cat_id
	 * @return
	 */
	public List listChildren(Integer cat_id);
	
	
	
	
	
	/**
	 * 保存排序
	 * @param cat_ids
	 * @param cat_sorts
	 */
	public void saveSort(int[] cat_ids, int[] cat_sorts) ;
	

	
}

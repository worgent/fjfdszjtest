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
import com.enation.javashop.core.model.Tag;

/**
 * 标签管理
 * @author kingapex
 * 2010-1-17下午01:03:41
 */
public interface ITagManager {
	
	/**
	 * 检测标签名是否同名
	 * @param name 标签名
	 * @param tagid 要排除的标签id,编辑时判断重名所用
	 * @return 存在重名返回真，不存在返回假
	 */
	public boolean checkname(String name,Integer tagid);
	
	
	/**
	 * 检测某些标签是否已经关联商品
	 * @param tagids 标签id数组
	 * @return 有关联返回真，否则返回假
	 */
	public boolean checkJoinGoods(Integer[] tagids);
	
	
	public Tag getById(Integer tagid);
	
	public void add(Tag tag);
	
	public void update(Tag tag);
	
	public void delete(Integer[] tagids);
	
	public Page list(int pageNo,int pageSize);
	
	public List<Tag> list();
	
	
	/**
	 * 读取某个引用的标签id集合
	 * @param relid
	 * @return
	 */
	public List<Integer> list(Integer relid); 
	
	
	
	
	/**
	 * 某个引用设置标签
	 * @param relid
	 * @param tagids
	 */
	public void saveRels(Integer relid,Integer[] tagids);
	
 
	
	
}

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

import com.enation.eop.model.MemberLv;
import com.enation.framework.database.Page;



/**
 * 会员级别管理
 * @author kingapex
 *2010-4-30上午10:07:50
 */
public interface IMemberLvManager{
	
	 
	/**
	 * 获取缺省的会员级别 如果没有缺省的会员级别返回null
	 * @return
	 */
	public Integer getDefaultLv() ;
	
	
	
	
	/**
	 * 添加一个会员级别
	 * @param lv
	 */
	public void add(MemberLv lv);
	
	
	
	
	
	/**
	 * 编辑会员级别
	 * @param lv
	 */
	public void edit(MemberLv lv);
	
	
	
	
	
	/**
	 * 分页读取会员等级
	 * @param order
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page list(String order,int page,int pageSize);
	
	
	
	
	 /**
	  * 获取一个会员级别
	  * @param lv_id
	  * @return
	  */
	public MemberLv get(Integer lv_id);
	
	
	
	
	/**
	 * 删除会员级别
	 * @param id
	 */
	public void delete(String id);
	
	
	
	/**
	 * 读取所有级别列表
	 * @return
	 */
	public  List<MemberLv> list();
	
	
	/**
	 * 根据级别列表(,号分隔)读取会员级别
	 * @param ids(,号分隔的会员级别id字串)
	 * @return 会员级别列表
	 */
	public List<MemberLv> list(String ids);
	
}
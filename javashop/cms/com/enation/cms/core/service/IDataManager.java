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
import java.util.Map;

import com.enation.framework.database.Page;

/**
 * 数据内容管理
 * @author kingapex
 * 2010-7-5下午01:54:45
 */
public interface IDataManager {
	
	
	/**
	 * 对某模型进行搜索
	 * @param pageNo
	 * @param pageSize
	 * @param modelid
	 * @return
	 */
	public Page search(int pageNo ,int pageSize,int modelid);
	
	
	
	/**
	 * 添加数据内容
	 * @param modelid 对应模型id
	 * @param catid   所属数据类别
	 */
	public void add(Integer modelid,Integer catid);
	
	
	/**
	 * 修改数据内容<br/>
	 * 因数据内容的具体字段是动态，<br/>
	 * 所以在参数中并未传递具体的修改数据，<br/>
	 * 需要实现体通过request来获取具体的数据。
	 * @param modelid  对应模型id
	 * @param catid    所属数据类别
	 * @param dataid 要修改的内容id
	 */
	public void edit(Integer modelid,Integer catid,Integer dataid);
	
	
	/**
	 * 删除一个数据内容
	 * @param catid 所属类别，需要根据此类别找到相应模型
	 * @param dataid 要删除的数据id 
	 */
	public void delete(Integer catid,Integer articleid);
	
	
	
	/**
	 * 分页读取某类别下的数据内容列表<br>
	 * <b>只读取当前类别的，并不读取子类别下的内容</b>
	 * @param catid 所属分类id
	 * @param page   页码
	 * @param pageSize 页大小
	 * @return  分页数据集 @see {@link Page}
	 */
	public Page list(Integer catid,int page,int pageSize);
	
	
	/**
	 *  分页读取某类别下的数据内容列表<br>
	 *  <b>读取当前类别和其所有子类别下的内容</b>
	 * @param catid 分类id
	 * @param page 页码
	 * @param pageSize 页大小
	 * @return  分页数据集 @see {@link Page}
	 */
	public Page listAll(Integer catid,int page,int pageSize);
	
	
	/**
	 * 读取某个内容数据的详细
	 * @param dataid 数据id
	 * @param catid  所属分类id
	 * @param filter 是否过滤值，过滤值：将字段索引值替换为相应字面值<br>
	 * 如 在radio或select的数据:1：男，2女，索引值为 1，如果过滤值的话则会将字段值更新为男。
	 * @return 以字段名为key字段值为value的map
	 */
	public Map get(Integer articleid,Integer catid,boolean filter);
	
}

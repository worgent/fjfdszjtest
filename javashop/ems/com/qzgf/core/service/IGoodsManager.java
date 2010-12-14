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
package com.qzgf.core.service;

import java.util.List;
import java.util.Map;

import com.enation.framework.database.Page;
import com.enation.javashop.core.model.support.GoodsEditDTO;
import com.qzgf.core.model.Goods;


/**
 * 商品管理接口
 * @author kingapex
 *
 */
public interface IGoodsManager {
	
	
	public static final String plugin_type_berforeadd= "goods_add_berforeadd" ;
	public static final String plugin_type_afteradd= "goods_add_afteradd" ;
	
	
	
	/**
	 * 填充添加前的数据
	 *
	 */
	public  List<String> fillAddInputData();
	
	
	/**
	 * 读取一个商品的详细
	 * @param Goods_id
	 * @return
	 */
	public Map get(Integer goods_id);
	
	public Goods getGoods(Integer goods_id);
	
	/**
	 * 修改时获取数据
	 * @param goods_id
	 * @return
	 */
	public GoodsEditDTO getGoodsEditData(Integer goods_id);
	
	
	
	/**
	 * 添加商品
	 * @param goods
	 */
	public void add(Goods goods);
	
	
	/**
	 * 修改商品
	 * @param goods
	 */
	public void edit(Goods goods);

	public Page searchGoods(Integer catid,String name,String sn,String order,int page,int pageSize);
	public Page searchBindGoods(String name,String sn,String order,int page,int pageSize);
	public Page pageTrash(String name,String sn,String order,int page,int pageSize);
	public void delete(Integer[] ids);
	public void  revert(Integer[] ids);
	public void clean(Integer[] ids);
	
	/**
	 * 根据商品id数组读取商品列表
	 * @param ids
	 * @return
	 */
	public List list(Integer[] ids);
	
	/**
	 * 批量编辑商品
	 */
	public void batchEdit();
}

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

import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Attribute;
import com.enation.javashop.core.model.GoodsType;
import com.enation.javashop.core.model.support.GoodsTypeDTO;
import com.enation.javashop.core.model.support.ParamGroup;



/**
 * 商品类型管理接口
 * @author kingapex
 * 2010-1-8下午05:59:00
 */
public interface IGoodsTypeManager {
	
	
	public boolean checkname(String name,Integer typeid);
	
	public List listAll();
	
	
	
	
	/**
	 * 读取商品类型列表
	 * @param order
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page pageType(String order,int page,int pageSize);
	
	
	/**
	 *保存商品类型 
	 * @param type
	 */
	public void save(GoodsType type) ;
	
	
	
	/**
	 * 清空商品类型及其关联的品牌
	 * @param type_id
	 */
	public void clean(Integer[] type_ids);
	
	
	/**
	 * 类型回收站列表
	 * @param order
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page pageTrashType(String order,int page,int pageSize);
	

	
	/**
	 * 读取一个类型的信息
	 * @param type_id
	 * @return
	 */	
	public GoodsTypeDTO get(Integer type_id);
	
	
	/**
	 * 读取某个类型关联的品牌
	 * 
	 * @param type_id
	 * @return
	 */
	public List getBrandListByTypeId(int type_id);
	
	/**
	 * 读取某个类型关联的品牌列表
	 * @return
	 */
	public List listByTypeId(Integer typeid);
	

	/**
	 * 将一个ParamGroup 对像的List 生成json字串 <br/> 此字串将会保存在数据库goods_type的params字段 或
	 * js_goods中的params字段
	 * 不同的是：goods_type字段中的参数信息无参数值信息，而js_goods表中的params字段有参数值信息。 <br/> List
	 * 是根据是根据客户端页面用户输入的参数信息生成的
	 * 
	 * @param paramnum
	 * @param groupnames
	 * @param paramnames
	 * @return
	 */
	public String getParamString(String[] paramnums, String[] groupnames,
			String[] paramnames, String[] paramvalues);
	
	
	
	
	/**
	 * 
	 * 将一个 Attribute 对象的List 生成Json字串,<br/>此字串将会保存到数据库goods_type表的props字段 List
	 * 是根据客户端页面用户输入的属性信息生成的
	 * 
	 * @param propnames
	 * @param proptypes
	 * @param options
	 * @return
	 */
	public String getPropsString(String[] propnames, int[] proptypes,String[] options);
	
	
	
	/**
	 * 将类型放入回收站
	 *
	 * @param type_ids
	 * @return 1成功 2失败：已经有商品使用此类型 
	 */
	public int delete(Integer[] type_ids);
	
	
	
	
	/**
	 * 将商品类型从回收站中还原
	 * @param type_ids
	 */
	public void revert(Integer[] type_ids);
	
	

	/**
	 * 读取某个类型的参数信息定义
	 * 
	 * @param type_id
	 * @return
	 */
	public ParamGroup[] getParamArByTypeId(int type_id);
	

	/**
	 * 读取某个类型的属性信息定义
	 * 
	 * @param type_id
	 * @return
	 */
	public List<Attribute> getAttrListByTypeId(int type_id);
	
	
	
}

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
package com.enation.app.saler.service;

import com.enation.eop.core.resource.model.EopProduct;
import com.enation.framework.database.Page;

/**
 * 产品业务逻辑
 * @author kingapex
 * 2010-1-20下午07:11:34
 */
public interface IProductService {
	
	
	/**
	 * 根据一个产品 唯一id(非数据库索引)安装product
	 * @param productName
	 */
	public void install(Integer userid, Integer siteid, String productId);
	
	public Page pageProduct(String order, int page, int pageSize);
	
	public EopProduct getProduct(Integer id);
	
	public EopProduct getProduct(String product_id);
	
	/**
	 * 解决方案导出
	 * @param name 要导出的名字
	 * @param exportData  是否要导出示例数据
	 * @param exportTheme 是否要导出模板
	 * @param exportProfile 是否要导出配置文件
	 * @param exportAttr 是否导出附件
	 */
	public void export(String name,boolean exportData,boolean exportTheme,boolean exportProfile,boolean exportAttr);
	
	
	/**
	 * 根据一个解决方案zip包导入解决方案
	 * @param zipPath
	 */
	public void imported(String productid,String zipPath);
	
	
	/**
	 * 添加解决方案
	 * @param product
	 * @param zipPath
	 * @return 1成功 0解决方案id已经存在
	 */
	public int  add(EopProduct product, String zipPath);
	
	
	/**
	 * 更新解决方案
	 * @param product
	 * @param zipPath
	 */
	public void update(EopProduct product, String zipPath);
	
	
	/**
	 * 删除解决方案
	 * @param productid 解决方案id
	 */
	public void delete(String productid);
}

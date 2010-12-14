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
package com.enation.eop.core.resource;

import java.util.List;

import com.enation.eop.core.resource.dto.SiteDTO;
import com.enation.eop.core.resource.model.Dns;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.EopSiteDomain;
import com.enation.eop.core.resource.model.SiteAppView;
import com.enation.framework.database.Page;

/**
 * 站点管理
 * @author lzf
 *         <p>
 *         created_time 2009-12-11 下午02:04:55
 *         </p>
 * @version 1.0
 */
public interface ISiteManager {

	/**
	 * 创建站点
	 * 
	 * @param siteDTO
	 * @see com.enation.eop.core.resource.dto.SiteDTO
	 * @return
	 */
	public Integer add(SiteDTO siteDTO);

	/**
	 * 取得某User的所有站点
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param userid
	 * @param order
	 * @param search
	 * @return
	 */
	public Page list(int pageNo, int pageSize, String order,
			String search);

	
	public Page list(int pageNo,int pageSize);
	

	/**
	 * 取得站点与域名的对照表
	 * 
	 * @return List<Dns>, @see com.enation.eop.api.core.model.Dns
	 */
	public List<Dns> getDnsList();

	/**
	 * 根据id取得一个具体的Site
	 * 
	 * @param id
	 * @return
	 */
	public EopSite get(Integer id);

	/**
	 * 根据域名取得站点
	 * 
	 * @param domain
	 * @return
	 */
	public EopSite get(String domain);
	
	public Boolean checkInDomain(String domain);

	/**
	 * 修改站点信息
	 * 
	 * @param eopSite
	 * @return
	 */
	public void edit(EopSite eopSite);

	/**
	 * 删除指定站点
	 * 
	 * @param id
	 * @return
	 */
	public void delete(Integer id);
	
	
	

	/**
	 * 增加一个域名
	 * @param eopSiteDomain
	 * @return 
	 * @throws IllegalArgumentException 如果域名已经存在
	 */
	public int addDomain(EopSiteDomain eopSiteDomain);
	
	
	

	/**
	 * 删除一个域名
	 * 
	 * @param domainid
	 * @return
	 */
	public void  deleteDomain(Integer domainid);
 
	
	
	
	/**
	 * 读取某用户某站点的所有域名列表
	 * @param userid
	 * @param siteid
	 * @return
	 */
	public List listDomain(Integer userid,Integer siteid);
	
	
	
	
	public void setSiteProduct(Integer userid, Integer siteid, String productid);

	 
	/**
	 * 切换后台主题 
	 * @param themeid
	 */
	public void changeAdminTheme(Integer themeid);
	
	
	/**
	 * 切换前台主题
	 * @param themeid
	 */
	public void changeTheme(Integer themeid);
	
	
	public void updatePoint(Integer id,int point); 
}

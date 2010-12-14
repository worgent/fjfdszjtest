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
package com.enation.eop.core.resource.dto;

import com.enation.eop.core.EopException;
import com.enation.eop.core.resource.model.EopUser;
import com.enation.eop.core.resource.model.EopUserAdmin;
import com.enation.eop.core.resource.model.EopUserDetail;

/**
 * @author lzf
 *         <p>
 *         created_time 2009-12-10 下午05:53:44
 *         </p>
 * @version 1.0
 */
public class UserDTO {
	private EopUser user;
	private EopUserDetail userDetail;
	private EopUserAdmin userAdmin;
	private SiteDTO siteDTO;
	private Integer siteid;
	
//	public UserDTO(){
//		user = new EopUser();
//		userDetail = new EopUserDetail();
//		userAdminList = new ArrayList<EopUserAdmin>();
//		siteList = new ArrayList<SiteDTO>();
//	}
	
	public void vaild(){
		
		if(this.userAdmin==null){
			throw new EopException("用户管理员不能为空！");
		}		
		if(this.userDetail==null){
			throw new EopException("用户详细信息不能为空！");
		}
		if(this.siteDTO==null){
			throw new EopException("用户站点不能为空！");
		}
		siteDTO.vaild();
	}
	
	public void setUserId(Integer userid){
		this.userDetail.setUserid(userid);
		userAdmin.setUserid(userid);
		siteDTO.setUserId(userid);
	}

	public EopUser getUser() {
		return user;
	}

	public void setUser(EopUser user) {
		this.user = user;
	}

	public EopUserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(EopUserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public EopUserAdmin getUserAdmin() {
		return userAdmin;
	}

	public void setUserAdmin(EopUserAdmin userAdmin) {
		this.userAdmin = userAdmin;
	}

	public SiteDTO getSiteDTO() {
		return siteDTO;
	}

	public void setSiteDTO(SiteDTO siteDTO) {
		this.siteDTO = siteDTO;
	}

	public Integer getSiteid() {
		return siteid;
	}

	public void setSiteid(Integer siteid) {
		this.siteDTO.setSiteId(siteid);
		this.siteid = siteid;
	}
}

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
package com.enation.eop.core.resource.model;

/**
 * @author lzf
 *         <p>
 *         created_time 2009-11-6 下午04:01:48
 *         </p>
 * @version 1.0
 */
public class EopApp {
	private Integer id;
	private String appid;
	private String app_name;
	private String author;
	private String descript;
	private int deployment; // 0:本地；1：远程
	private String path; // 对本地是目录，对远程是地址
	private String installuri; //安装地址 
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	private String authorizationcode; // 授权码

	/**
	 * @return
	 */
	public String getApp_name() {
		return app_name;
	}

	/**
	 * @param appName
	 */
	public void setApp_name(String appName) {
		app_name = appName;
	}

	/**
	 * @return
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return
	 */
	public String getDescript() {
		return descript;
	}

	/**
	 * @param descript
	 */
	public void setDescript(String descript) {
		this.descript = descript;
	}

	/**
	 * @return
	 */
	public int getDeployment() {
		return deployment;
	}

	/**
	 * @param deployment
	 */
	public void setDeployment(int deployment) {
		this.deployment = deployment;
	}

	/**
	 * @return
	 */
	public String getAuthorizationcode() {
		return authorizationcode;
	}

	/**
	 * @param authorizationcode
	 */
	public void setAuthorizationcode(String authorizationcode) {
		this.authorizationcode = authorizationcode;
	}

	public String getInstalluri() {
		return installuri;
	}

	public void setInstalluri(String installuri) {
		this.installuri = installuri;
	}

}

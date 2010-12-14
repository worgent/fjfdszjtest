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
 *         created_time 2009-11-25 下午04:34:33
 *         </p>
 * @version 1.0
 */
public class AdminTheme extends Resource {
	private String themename;
	private String path;
	private String author;
	private String version;
	private String thumb = "preview.png";

	/**
	 * 0否 1是
	 */
	private int framemode;

	// public String getThumb(String type) {
	// String result = "";
	// if(type.toUpperCase().equals("APP")){
	// result = StringUtil.getEopCachePath() +
	// "/eop/"+this.getAppid()+"/theme/"+this.getPath()+"/preview.png";
	// }else{
	// result = StringUtil.getEopCachePath()
	// +"/user/"+this.getUserid()+"/"+this.getSiteid()+"/theme/"+this.getPath()+"/preview.png";
	// }
	//		
	// return result;
	// }

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getThemename() {
		return themename;
	}

	public void setThemename(String themename) {
		this.themename = themename;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getFramemode() {
		return framemode;
	}

	public void setFramemode(int framemode) {
		this.framemode = framemode;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}

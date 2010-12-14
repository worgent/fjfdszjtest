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
package com.enation.app.saler.action;

import java.io.File;

import com.enation.eop.EopSetting;
import com.enation.eop.utils.UploadUtil;
import com.enation.framework.action.WWAction;


/**
 * 附件上传
 * @author kingapex
 *2010-3-10下午04:24:47
 */
public class UploadAction extends WWAction {
	
	private String fileFileName;
	private File file;
	
	//是否创建 缩略图,默认不创建
	private int createThumb=0; 
	
	//子目录地址
	private String subFolder;
	
	private int ajax;
	
	public String execute(){
		
		return this.INPUT;
	}
	
	public String upload(){
		if(file!=null && fileFileName!=null){
			String path = UploadUtil.upload(file,fileFileName,subFolder);
			
			//将本地图片路径换为静态资源服务器的地址
			if(path!=null) 
				path  =UploadUtil.replacePath(path);
			
			if( ajax==1 ){
				this.json ="{'result':1,'path':'"+path+"'}";
				return this.JSON_MESSAGE;
			}
		}	
		return this.SUCCESS;
	}
	
 
	public String getFileFileName() {
		return fileFileName;
	}


	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}


	public File getFile() {
		return file;
	}


	public void setFile(File file) {
		this.file = file;
	}


	public int getCreateThumb() {
		return createThumb;
	}


	public void setCreateThumb(int createThumb) {
		this.createThumb = createThumb;
	}


	public String getSubFolder() {
		return subFolder;
	}


	public void setSubFolder(String subFolder) {
		this.subFolder = subFolder;
	}

	public int getAjax() {
		return ajax;
	}

	public void setAjax(int ajax) {
		this.ajax = ajax;
	}
	
	
}

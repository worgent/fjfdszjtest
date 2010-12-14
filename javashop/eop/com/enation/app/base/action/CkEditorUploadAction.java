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
package com.enation.app.base.action;

import java.io.File;

import com.enation.eop.utils.UploadUtil;
import com.enation.framework.action.WWAction;

/**
 * ckeditor 文件上传 
 * @author kingapex
 * 2010-7-11上午09:46:00
 */
public class CkEditorUploadAction extends WWAction {
	private File upload;
	private String uploadFileName;
	private String path;
	private String funcNum;
	public String execute(){
		funcNum = this.getRequest().getParameter("CKEditorFuncNum");
		if(upload!=null  && uploadFileName!=null){
			path = UploadUtil.upload(upload,uploadFileName,"ckeditor");
		}
		return this.SUCCESS;
		
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFuncNum() {
		return funcNum;
	}

	public void setFuncNum(String funcNum) {
		this.funcNum = funcNum;
	}
	
	
}

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
package com.enation.javashop.core.action.backend;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.enation.framework.action.WWAction;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.service.IGoodsAlbumManager;

public class UploadPhoto extends WWAction {
	private IGoodsAlbumManager goodsAlbumManager;
	/**
	 * 文件删除
	 * 
	 * @param fileName
	 */
	public void delFile(String fileName) {
		if (fileName != null && !fileName.trim().equals("")) {
			File file = new File(StringUtil.getRootPath() + "/" + fileName);
			file.delete();
			File fileThumb = new File(StringUtil.getRootPath() + getThumbpath(fileName));
			//如果存在缩量图则删除
			if(fileThumb.exists())
				fileThumb.delete();
		}
	}
	
	private String getThumbpath(String file){
		String fStr =  "";
		if(!file.trim().equals("")){
		String[] arr = file.split("/");
		fStr ="/"+arr[0]+"/"+arr[1]+"/thumb/"+arr[2];
		}
		return fStr;
	}

	private File filedata;
	private String filedataFileName;
	private String photoName;
	public String execute(){
		
		if(filedata!=null){
		
			String[] names =goodsAlbumManager.upload(filedata, filedataFileName);

			  this.json= names[0] + "," + names[0];
		}
		
		return this.JSON_MESSAGE;
	}

	
	public String delPhoto(){
		this.goodsAlbumManager.delete(photoName);
		this.json="{'result':0,'message:':'图片删除成功'}";
		return this.JSON_MESSAGE;
	}
	
	private static String getName(String path){
		String regEx = "(/goods/)(.*)";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(path);
		String name = "";
		 
		while(m.find()){
			name= m.group();
		}
		return name;
	}
	
	
	public static void main(String[] args){
		
		String path = "http://www.javashop.com/attachment/goods/200901020201052381.jpg" ;

		
		String name = getName(path);
		//System.out.println(name);
	}
	public File getFiledata() {
		return filedata;
	}

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}

	public String getFiledataFileName() {
		return filedataFileName;
	}

	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}


	public String getPhotoName() {
		return photoName;
	}


	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public void setGoodsAlbumManager(IGoodsAlbumManager goodsAlbumManager) {
		this.goodsAlbumManager = goodsAlbumManager;
	}
 
	
	
}

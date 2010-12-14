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
package com.enation.framework.plugin.page;

import java.io.File;

import com.enation.framework.util.StringUtil;

/**
 * 插件页面加载器。<br/>
 * 加载指定文件夹内的页面，并记录其所属的插类型。<br/>
 * 插件页面信息被记录在 PluginPageContext 中。
 * @see PluginPageContext
 * @author apexking
 */
public  class PluginPageLoader {
	
	private String path;
	private String type;
	
	
	/**
	 * 构造器，在实例化时则必须提供插件类型及其对应的插件页面文件夹。	 * 
	 * @param type
	 * 			插件类型
	 * @param path
	 * 			插件页面文件夹
	 */
	public  PluginPageLoader(String type,String path){
		path= path.endsWith("/") ? path= path.substring(0, path.length()-1):path;
		this.type=type;
		this.path = path;
		this.initPages();
	}
	
 	/**
 	 * 扫描插件文件夹，并加载文件夹内的文件。
 	 *
 	 */
	private void initPages(){
		String root_path = StringUtil.getRootPath();
		System.out.println("加载插件文件夹：" + root_path+path);
		File file = new File(root_path+path);
		File[] files = file.listFiles();
		for(File f :files){
			//System.out.println("加载" + f.getName());
			//if(f.getName().endsWith("header.jsp") || f.getName().endsWith("body.jsp") ){
				PluginPageContext.addPage(type, path+"/" +f.getName());
		//	}
		}
		
	}
	
	
	public static void main(String args[]){
		PluginPageLoader pluginPageLoader = new PluginPageLoader("userlist","/admin/user/plugin");
	}
}

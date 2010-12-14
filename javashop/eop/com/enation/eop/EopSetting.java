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
package com.enation.eop;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import com.enation.framework.image.ThumbnailCreatorFactory;
import com.enation.framework.util.FileUtil;
import com.enation.framework.util.PropertiesUtil;
import com.enation.framework.util.StringUtil;

public class EopSetting {
	
	//EOP服器根
	public static String EOP_PATH ="";
	
	
	/*
	 * 图片服务器域名
	 */
	public static String IMG_SERVER_DOMAIN = "static.eop.com";

	/*
	 * 图片服务器地址
	 */
	public static String IMG_SERVER_PATH="";
	
	/*
	 * 应用数据存储地址
	 */
	//public static String APP_DATA_STORAGE_PATH ="e:/eop";
	public static String APP_DATA_STORAGE_PATH ="D:/eop";
	
	/*
	 * 产品存储目录
	 */	
	//public static String PRODUCTS_STORAGE_PATH ="E:/workspace/eop3/eop/src/products";
	public static String PRODUCTS_STORAGE_PATH ="F:/Java/javashop/WebContent/products";
	
	/*
	 * 服务器域名
	 */
	public static String APP_DOMAIN_NAME = "eop.com";
	
	
	
	/*
	 * 用户数据存储路根径
	 */
	public static String USERDATA_STORAGE_PATH = "/user";
	
	
	
	/*
	 * 后台主题存储路径
	 * 包括公共资源和用户资源的
	 * '/'代表当时的上下文
	 */
	public static String ADMINTHEMES_STORAGE_PATH = "/adminthemes";
	
	
	
	
	/*
	 * 前台主题存储路径
	 * 包括公共资源和用户资源的
	 * '/'代表当时的上下文
	 */	
	public static String THEMES_STORAGE_PATH = "/themes";
	

	
	/*
	 * EOP虚拟目录
	 */
	public static String CONTEXT_PATH ="/";
	
	//资源模式
	public static String RESOURCEMODE="1";
	
	//运行模式
	public static String RUNMODE ="2";
	
	//数据库类型
	public static String DBTYPE ="1" ;
	
	//扩展名
	public static String EXTENSION ="do";
	
	//是否使用默认eop的引擎,on为使用，off为不使用
	public static String TEMPLATEENGINE="on";
	
	public static String  THUMBNAILCREATOR ="javaimageio";
	
	public static String  FILE_STORE_PREFIX ="fs:"; //本地文件存储前缀
	
	public static String VERSION ="";
	
	public static String INSTALL_LOCK ="NO"; //是否已经安装
	
	/*
	 * 从配置文件中读取相关配置<br/>
	 * 如果没有相关配置则使用默认
	 */
	static {
		try{
			InputStream in  = FileUtil.getResourceAsStream("eop.properties");
			Properties props = new Properties();
			props.load(in);
			init(props);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	 
	
	public static void init(Properties props ){

	 
		String domain = props.getProperty("domain.imageserver");
		IMG_SERVER_DOMAIN = StringUtil.isEmpty(domain) ? IMG_SERVER_DOMAIN: domain;
		IMG_SERVER_DOMAIN = IMG_SERVER_DOMAIN.startsWith("http://") ? IMG_SERVER_DOMAIN: "http://" + IMG_SERVER_DOMAIN;
		

		String userdata_storage_path = props.getProperty("storage.userdata");
		USERDATA_STORAGE_PATH = StringUtil.isEmpty(userdata_storage_path) ? USERDATA_STORAGE_PATH: userdata_storage_path;
		
		
		String adminthemes_storage_path = props.getProperty("storage.adminthemes");
		ADMINTHEMES_STORAGE_PATH = StringUtil.isEmpty(adminthemes_storage_path) ? ADMINTHEMES_STORAGE_PATH: adminthemes_storage_path;
			
		String themes_storage_path = props.getProperty("storage.themes");
		THEMES_STORAGE_PATH = StringUtil.isEmpty(themes_storage_path) ? THEMES_STORAGE_PATH: themes_storage_path;
		
		
		String eop_path = props.getProperty("storage.EOPServer");
		EOP_PATH = StringUtil.isEmpty(eop_path) ? EOP_PATH: eop_path;
		

		String imageserver_path = props.getProperty("path.imageserver");
		IMG_SERVER_PATH = StringUtil.isEmpty(imageserver_path) ? IMG_SERVER_PATH: imageserver_path;
 
		
		String app_data = props.getProperty("storage.app_data");
		//解决xml加载异常
		//app_data="file:///"+app_data;
		
		APP_DATA_STORAGE_PATH = StringUtil.isEmpty(app_data) ? APP_DATA_STORAGE_PATH: app_data;
 		
		
		String context_path = props.getProperty("path.context_path");
		CONTEXT_PATH = StringUtil.isEmpty(context_path) ? CONTEXT_PATH: context_path;	
		

		String products_path = props.getProperty("storage.products");
		//解决xml加载异常
		//products_path="file:///"+products_path;
		PRODUCTS_STORAGE_PATH = StringUtil.isEmpty(products_path) ? PRODUCTS_STORAGE_PATH: products_path;	
		
		//资源模式
		String resoucemode = props.getProperty("resourcemode");
		RESOURCEMODE=  StringUtil.isEmpty(resoucemode)?RESOURCEMODE:resoucemode;
		
		//运行模式
		String runmode = props.getProperty("runmode");
		RUNMODE=  StringUtil.isEmpty(runmode)?RUNMODE:runmode;

		//数据库类型
		String dbtype = props.getProperty("dbtype");
		DBTYPE=  StringUtil.isEmpty(dbtype)?DBTYPE:dbtype;

		//扩展名
		String extension = props.getProperty("extension");
		EXTENSION=  StringUtil.isEmpty(extension)?EXTENSION:extension;
		
		
		String templateengine = props.getProperty("templateengine");
		TEMPLATEENGINE=  StringUtil.isEmpty(templateengine)?TEMPLATEENGINE:templateengine;		

		String thumbnailcreator = props.getProperty("thumbnailcreator");
		THUMBNAILCREATOR=  StringUtil.isEmpty(thumbnailcreator)?THUMBNAILCREATOR:thumbnailcreator;
		ThumbnailCreatorFactory.CREATORTYPE = THUMBNAILCREATOR;

		PropertiesUtil propertiesUtil  = new PropertiesUtil("version.properties");
		VERSION = propertiesUtil.getProperties("main")+"."+ propertiesUtil.getProperties("dev") ;
		 
		File installLockFile = new File(StringUtil.getRootPath()+"/install/install.lock");
		if( installLockFile.exists() ){
			INSTALL_LOCK = "YES"; //如果存在则不能安装
		}else{
			INSTALL_LOCK = "NO"; //如果不存在，则认为是全新的，跳到install页
		}
	}
	
	

}

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
package com.enation.eop.impl.resource;

import java.util.List;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.IThemeManager;
import com.enation.eop.core.resource.model.Theme;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.framework.util.FileUtil;

public class ThemeManagerImpl extends BaseSupport<Theme> implements IThemeManager {
 

	
	public Theme getTheme(Integer themeid) {
		//System.out.println(themeid);
		return this.baseDaoSupport.queryForObject("select * from theme where id=?", Theme.class, themeid);
	}

	
	public List<Theme> getThemeList() {
		return this.baseDaoSupport.queryForList("select * from theme",Theme.class);
	}

	public void addBlank(Theme theme){
		try {
	 
			//公用模板由common目录复制，非公用由产品目录复制
			String basePath =  EopSetting.APP_DATA_STORAGE_PATH;
			basePath =basePath + "/themes";
			
			//复制资源到静态资源服务器
			String contextPath = EopContext.getContext().getContextPath();
			String targetPath  = EopSetting.IMG_SERVER_PATH+ contextPath+ "/themes/" + theme.getPath();
			FileUtil.copyFolder(basePath + "/blank/images",targetPath + "/images");
			FileUtil.copyFile(basePath + "/blank/preview.png",targetPath + "/preview.png");
			FileUtil.copyFolder(basePath + "/blank/css",targetPath + "/css");
			FileUtil.copyFolder(basePath + "/blank/js",targetPath+ "/js");
			FileUtil.copyFolder(basePath + "/blank",EopSetting.EOP_PATH
					+contextPath
					+ "/themes/" + theme.getPath());
			this.baseDaoSupport.insert("theme", theme);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建主题出错");
		}
	}
	
	public Integer add(Theme theme,boolean isCommon) {
		try {
			this.copy(theme,isCommon);
		//	System.out.println("安装"+ theme.getThemename());
			this.baseDaoSupport.insert("theme", theme);
			return this.baseDaoSupport.getLastId("theme");
		} catch (Exception e) {
			 
			e.printStackTrace();
			throw new RuntimeException("安装主题出错");
		}
		
	}

	
	private  void copy( Theme theme ,boolean isCommon) throws Exception {
		//公用模板由common目录复制，非公用由产品目录复制
		String basePath = isCommon?EopSetting.APP_DATA_STORAGE_PATH:EopSetting.PRODUCTS_STORAGE_PATH+"/" + theme.getProductId() ;
		basePath =basePath + "/themes";
		
		//复制资源到静态资源服务器
		String contextPath = EopContext.getContext().getContextPath();
		String targetPath  = EopSetting.IMG_SERVER_PATH+ contextPath+ "/themes/" + theme.getPath();
		FileUtil.copyFolder(basePath + "/" + theme.getPath() + "/images",targetPath + "/images");
		FileUtil.copyFile(basePath + "/" + theme.getPath() + "/preview.png",targetPath + "/preview.png");
		FileUtil.copyFolder(basePath + "/" + theme.getPath() + "/css",targetPath + "/css");
		FileUtil.copyFolder(basePath + "/" + theme.getPath() + "/js",targetPath+ "/js");

		
		FileUtil.copyFolder(basePath + "/" + theme.getPath(),EopSetting.EOP_PATH
				+contextPath
				+ "/themes/" + theme.getPath());
		/*
		 * 只考jsp到eop应用服务器中
		 
		IOFileFilter txtSuffixFilter = FileFilterUtils.suffixFileFilter(".jsp");
		IOFileFilter txtFiles = FileFilterUtils.andFileFilter(FileFileFilter.FILE, txtSuffixFilter);

		FileUtils.copyDirectory(
				new File(basePath + "/" + theme.getPath() )
				, 
				
				new File(EopSetting.EOP_PATH
				+ "/user/"
				+userid
				+ "/"
				+siteid
				+ "/themes/" + theme.getPath())
				, 
				txtFiles
				);
		
		
		FileUtil.copyFolder(basePath + "/" + theme.getPath(), StringUtil
				.getRootPath()
				+ "/user/"
				+userid
				+ "/"
				+siteid
				+ "/themes/" + theme.getPath());
				*/
	}
}

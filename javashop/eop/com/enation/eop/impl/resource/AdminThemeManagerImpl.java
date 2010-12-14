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

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.IAdminThemeManager;
import com.enation.eop.core.resource.model.AdminTheme;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.util.FileUtil;

/**
 * 后台主题管理
 * @author kingapex
 *2010-5-9下午07:46:18
 */
public class AdminThemeManagerImpl extends BaseSupport<AdminTheme> implements IAdminThemeManager {

 
	@Transactional(propagation = Propagation.REQUIRED)
	
	public Integer add(AdminTheme theme,boolean isCommon) {
		 
		try {
			this.copy(theme,isCommon);
			this.baseDaoSupport.insert("admintheme", theme);
			return this.baseDaoSupport.getLastId("admintheme");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("安装后台主题出错");
		}
		
	}
	
	private void copy(AdminTheme theme,boolean isCommon) throws Exception  {
	 
		EopSite site  = EopContext.getContext().getCurrentSite();

		//公用模板由common目录复制，非公用由产品目录复制
		String basePath =isCommon?EopSetting.APP_DATA_STORAGE_PATH:EopSetting.PRODUCTS_STORAGE_PATH+ "/" + theme.getProductId();
		basePath= basePath +"/adminthemes";
		
		
		String contextPath = EopContext.getContext().getContextPath();
		//复制图片至静态资源服务器
		String targetPath = EopSetting.IMG_SERVER_PATH   +contextPath + "/adminthemes/"+ theme.getPath() ;
		FileUtil.copyFolder(basePath + "/" + theme.getPath() + "/images",targetPath+ "/images");
		FileUtil.copyFile(basePath + "/" + theme.getPath() + "/preview.png",targetPath+ "/preview.png");
		FileUtil.copyFolder(basePath + "/" + theme.getPath() + "/css",targetPath+ "/css");
		FileUtil.copyFolder(basePath + "/" + theme.getPath() + "/js",targetPath+ "/js");
		
		
		FileUtil.copyFolder(basePath + "/" + theme.getPath() ,EopSetting.EOP_PATH
				+contextPath
				+ "/adminthemes/" + theme.getPath());
		/*
		 * 只考jsp到eop应用服务器中
		
		IOFileFilter txtSuffixFilter = FileFilterUtils.suffixFileFilter(".jsp");
		IOFileFilter txtFiles = FileFilterUtils.andFileFilter(FileFileFilter.FILE, txtSuffixFilter);

		  
		FileUtils.copyDirectory(
		new File(basePath + "/" + theme.getPath() )
		, 
		
		new File(EopSetting.EOP_PATH
		+ "/user/"
		+ userid
		+ "/"
		+ siteid
		+ "/adminthemes/" + theme.getPath())
		, 
		txtFiles
		);

		 */
	}
	
	
	
	public AdminTheme get(Integer themeid) {
		
		return this.baseDaoSupport.queryForObject("select * from admintheme where id=?", AdminTheme.class, themeid);
	}

	
	public List<AdminTheme> list() {
		
		return this.baseDaoSupport.queryForList("select * from admintheme ", AdminTheme.class);
	}

	public IDaoSupport<AdminTheme> getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport<AdminTheme> daoSupport) {
		this.daoSupport = daoSupport;
	}


}

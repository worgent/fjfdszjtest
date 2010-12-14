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
package com.enation.app.saler.service.impl;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.enation.app.saler.service.IInstaller;
import com.enation.app.saler.service.IProductService;
import com.enation.app.saler.service.IProfileLoader;
import com.enation.app.saler.service.InstallerFactory;
import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.ISiteManager;
import com.enation.eop.core.resource.model.EopProduct;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.sdk.App;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.database.IDBRouter;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.database.Page;
import com.enation.framework.util.FileUtil;

/**
 * 产品(解决方案)业务逻辑
 * @author kingapex
 * 2010-1-21上午10:58:12
 */
public class ProductServiceImpl implements IProductService {
	protected final Logger logger = Logger.getLogger(getClass());
	private IProfileLoader profileLoader;
	private InstallerFactory installerFactory;
 	private ISiteManager siteManager;
 	private IDaoSupport<EopProduct> daoSupport;
 	private SqlExportService sqlExportService;
 	private ProfileCreator profileCreator;
 	private IDBRouter baseDBRouter;
 	private AppInstaller appInstaller;
 	
	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}

 
	
	
	/**
	 * 根据产品id（非数据库索引）安装一个product
	 */
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void install(Integer userid, Integer siteid, String productId) {
		

		
		//将对应的productid写入到eop_site表的productid字段中
		if(!productId.toUpperCase().equals("BASE") && !productId.startsWith("temp_")){
			siteManager.setSiteProduct(userid, siteid, productId);
		}
		
		final String[] types =
		{
			InstallerFactory.TYPE_APP,	
			InstallerFactory.TYPE_MENU,
			InstallerFactory.TYPE_ADMINTHEME,
			InstallerFactory.TYPE_THEME,
			InstallerFactory.TYPE_URL,
			InstallerFactory.TYPE_WIDGET

		};
		
		Document proFileDoc = profileLoader.load(productId);
		
		for(String type:types){
			
			if(logger.isDebugEnabled()){
				logger.debug("install ["+type+"]");
			}
			
			NodeList nodeList = proFileDoc.getElementsByTagName(type);
			if(nodeList ==null || nodeList.getLength()<=0) continue;
			
			if(nodeList!=null ){
				 IInstaller installer = installerFactory.getInstaller(type);
					if(logger.isDebugEnabled()){
						logger.debug("user installer ["+installer+"]");
					}				 

				installer.install(productId,nodeList.item(0));
			}
			
		}
		//示例数据安装
		IInstaller installer  = SpringContextHolder.getBean("exampleDataInstaller");
		installer.install(productId,  null);
	}
	
	
	public EopProduct getProduct(Integer id) {
		return (EopProduct) daoSupport.queryForObject(
				"select * from eop_product where id = ?", EopProduct.class, id);
	}

	
	public EopProduct getProduct(String productId) {
		return (EopProduct) this.daoSupport.queryForObject(
				"select * from eop_product where productid = ?",
				EopProduct.class, productId);
	}

	
	public Page pageProduct(String order, int page, int pageSize) {
		order = order == null ? " id desc" : order;
		String sql = "select * from eop_product";
		sql += " order by  " + order;
		Page webpage = this.daoSupport.queryForPage(sql, page, pageSize);
		return webpage;
	}
	
	
	public void setProfileLoader(IProfileLoader profileLoader) {
		this.profileLoader = profileLoader;
	}



	public void setInstallerFactory(InstallerFactory installerFactory) {
		this.installerFactory = installerFactory;
	}

	public IDaoSupport<EopProduct> getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport<EopProduct> daoSupport) {
		this.daoSupport = daoSupport;
	}

	
	public void export(String name,boolean exportData,boolean exportTheme,boolean exportProfile,boolean exportAtt) {
		
		String datapath = EopSetting.IMG_SERVER_PATH+"/data/";
		String temppath  =datapath+System.currentTimeMillis();
		File tempdir  =new File(temppath);
		tempdir.mkdirs();
		
		File target = new File(datapath+name+".zip");
		if(target.exists()) target.delete();
		try {
			
			if(exportData){
				StringBuffer sqlContent = new StringBuffer();
				//生成示例数据
				Map<String ,App> apps  = appInstaller.getApps();
				Iterator<String> iter =apps.keySet().iterator();
				while(iter.hasNext()){
					App app = apps.get(iter.next());
					sqlContent.append( app.dumpSql() );
				}
				FileUtil.write(temppath +"/example_data.sql", sqlContent.toString());
			}
			
			if(exportTheme){
				//拷贝模板文件
				if("1".equals( EopSetting.RESOURCEMODE )){ //静态资源分离
					FileUtil.copyFolder(EopSetting.EOP_PATH+EopContext.getContext().getContextPath() +"/themes", temppath+"/themes");
					FileUtil.copyFolder(EopSetting.IMG_SERVER_PATH+EopContext.getContext().getContextPath() +"/themes", temppath+"/themes");
				}
				
				if("2".equals( EopSetting.RESOURCEMODE )){ //静态资源合并
					FileUtil.copyFolder(EopSetting.EOP_PATH+EopContext.getContext().getContextPath() +"/themes", temppath+"/themes");
				}
				
			}
			
			if(exportProfile){
				//创建配置文件
				this.profileCreator.createProfile(temppath+"/profile.xml");
			}
			
			if(exportAtt){
				//拷贝附件
				FileUtil.copyFolder(EopSetting.IMG_SERVER_PATH+EopContext.getContext().getContextPath()+"/attachment", temppath+"/attachment");				
			}
			
			Project prj = new Project();  
			Zip zip = new Zip();  
			zip.setEncoding("UTF-8");
			zip.setProject(prj);  
			zip.setDestFile(target);  
			FileSet fileSet = new FileSet();  
			fileSet.setProject(prj);  
			fileSet.setDir(tempdir); 
			zip.addFileset(fileSet);  
			zip.execute();  
			Delete delete = new Delete();
			delete.setDir(tempdir);
			delete.execute();
			
			
			
		} catch (Exception e) {
			 this.logger.error("导出模解决方案", e);
		}
		 
	}

	
	/**
	 * 将zip包解压到为指定的解决方案
	 * @param productid
	 * @param zipPath
	 * @return 解压后的全路径
	 */
	private String expand(String productid,String zipPath){
		String temppath =  EopSetting.PRODUCTS_STORAGE_PATH+"/" +productid;
		File tempdir = new File(temppath);
		tempdir.mkdirs();

		File zipFile = new File(zipPath);
		Project prj = new Project();
		Expand expand = new Expand();
		expand.setEncoding("UTF-8");
		expand.setProject(prj);
		expand.setSrc(zipFile);
		expand.setOverwrite(true);
		expand.setDest(tempdir);
		expand.execute();
		
		//清除zip包
		Delete delete = new Delete();
		delete.setDir(zipFile);
		delete.execute();		
		
		return temppath;
	}
	
   

	public void imported(String productid, String zipPath) {
		
		try {
			String temppath  =   this.expand(productid, zipPath);
			File tempdir = new File(temppath);
			//如果含有示例数据则要清空基础表
			if(new File(temppath+"/example_data.sql").exists()){
				
				this.daoSupport.execute("truncate table "+baseDBRouter.getTableName("widgetbundle"));
				this.daoSupport.execute("truncate table "+baseDBRouter.getTableName("border"));
				this.daoSupport.execute("truncate table "+baseDBRouter.getTableName("menu"));
				this.daoSupport.execute("truncate table "+baseDBRouter.getTableName("theme"));
				this.daoSupport.execute("truncate table "+baseDBRouter.getTableName("admintheme"));
				this.daoSupport.execute("truncate table "+baseDBRouter.getTableName("themeuri"));
				
			}
			
			EopSite site  = EopContext.getContext().getCurrentSite();
			
			this.install(site.getUserid(), site.getId(), "base");
			this.install(site.getUserid(), site.getId(), productid);
			
			Project prj = new Project();
			//清除安装文件
			Delete delete = new Delete();
			delete.setProject(prj);
			delete.setDir(tempdir);
			delete.execute();		
			
		} catch (Exception e) {
			
			this.logger.error("导入模解决方案", e);
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args){
		
		  Project prj=new Project();
		  Expand expand=new Expand();
		  expand.setProject(prj);
		  expand.setSrc(new File("d:/z.zip"));
		  expand.setOverwrite(true);
		  expand.setDest(new File("d:/temp"));
		  expand.execute();

	}
	
	
	public SqlExportService getSqlExportService() {
		return sqlExportService;
	}

	public void setSqlExportService(SqlExportService sqlExportService) {
		this.sqlExportService = sqlExportService;
	}

	public ProfileCreator getProfileCreator() {
		return profileCreator;
	}

	public void setProfileCreator(ProfileCreator profileCreator) {
		this.profileCreator = profileCreator;
	}

	public IDBRouter getBaseDBRouter() {
		return baseDBRouter;
	}

	public void setBaseDBRouter(IDBRouter baseDBRouter) {
		this.baseDBRouter = baseDBRouter;
	}

	public int add(EopProduct product, String zipPath) {
		
		if(this.checkIdExist(product.getProductid())){
			return 0;
		}
		
		product.setPreview("fs:/attachment/product/preview.png");
		this.expand(product.getProductid(), zipPath);
		product.setCreatetime(System.currentTimeMillis());
		this.daoSupport.insert("eop_product", product);	
		
		return 1;
	}

	/**
	 * 检测解决方案id是否存在
	 * @param productid
	 * @return 存在返回真，不存在返回假
	 */
	private boolean checkIdExist(String productid){
		String sql  ="select count(0) from eop_product where productid=?";
		int count  = this.daoSupport.queryForInt(sql, productid);
		if(count>0)
			return true;
		else
			return false; 
	}
	

 
	
	public void  update(EopProduct product, String zipPath){
 
		this.expand(product.getProductid(), zipPath);
		this.daoSupport.update("eop_product", product,"productid='" + product.getProductid()+"'");	
	}

	
	public void delete(String productid) {
		String sql ="delete from eop_product where productid=?";
		Project prj = new Project();
		Delete del = new Delete();
		del.setProject(prj);
		del.setDir(new File(EopSetting.PRODUCTS_STORAGE_PATH + "/"+productid));
		del.execute();
		
		this.daoSupport.execute(sql, productid);
	}

	public AppInstaller getAppInstaller() {
		return appInstaller;
	}

	public void setAppInstaller(AppInstaller appInstaller) {
		this.appInstaller = appInstaller;
	}

}

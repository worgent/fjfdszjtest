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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.enation.app.base.service.EopInstallManager;
import com.enation.eop.EopSetting;
import com.enation.eop.core.resource.ISiteManager;
import com.enation.framework.action.WWAction;
import com.enation.framework.util.FileUtil;
import com.enation.framework.util.StringUtil;
/**
 * 安装action
 * @author kingapex
 * 2010-6-4下午04:54:44
 */
/**
 * @author kingapex
 * 2010-6-9下午05:21:50
 */
public class EopInstallAction extends WWAction {
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcTemplate simpleJdbcTemplate;
	private EopInstallManager eopInstallManager;
	private BasicDataSource dataSource;
	private String dbhost;
	private String uname;
	private String pwd;
	private String dbtype;
	private String dbname;
	private String domain;
	private String productid;
	
	private String osVersion;
	private String javaVersion;
	
	/**
	 * 显示协议
	 */
	public String execute(){
		saveEopParams();
		return "step1";
	}
	
	
	/**
	 * 显示数据库设置页
	 * @return
	 */
	public String step2(){
		
		return "step2";
	}
	
	/**
	 * 保存存数据库设置
	 * @return
	 */
	public String step3(){
		if("mysql".equals(dbtype)){
//			BasicDataSource newDataSource = createMySqlDBSource();
//		 
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://"+dbhost+"/"+this.dbname+"?useUnicode=true&characterEncoding=utf8");
			dataSource.setUsername(this.uname);
			dataSource.setPassword(this.pwd);
//			this.jdbcTemplate.setDataSource(newDataSource);
//			this.simpleJdbcTemplate = new SimpleJdbcTemplate(newDataSource);
		 
			this.saveMysqlDBParams();
		}
		Properties props=System.getProperties();  
		this.osVersion = props.getProperty("os.name")+"("+props.getProperty("os.version")+")";
		this.javaVersion = props.getProperty("java.version");
		return "step3";
	}
	
	public String installSuccess(){
		FileUtil.write(EopSetting.EOP_PATH+"/install/install.lock", "如果要重新安装，请删除此文件，并重新起动web容器");
		EopSetting.INSTALL_LOCK ="yes";
		return this.SUCCESS;
	}
	
	public static void main(String[] args){
		Properties props=System.getProperties();  
		System.out.println("Java的运行环境版本："+props.getProperty("java.version"));  
		System.out.println("操作系统的名称："+props.getProperty("os.name"));  
		System.out.println("操作系统的构架："+props.getProperty("os.arch"));  
		System.out.println("操作系统的版本："+props.getProperty("os.version"));
	}
	
	/**
	 * 保存mysql数据设置
	 */
	private void saveMysqlDBParams(){
		Properties props = new Properties();
		props.setProperty("jdbc.driverClassName", "com.mysql.jdbc.Driver");
		props.setProperty("jdbc.url", "jdbc:mysql://"+this.dbhost+"/"+this.dbname+"?useUnicode=true&characterEncoding=utf8");
		props.setProperty("jdbc.username", this.uname);
		props.setProperty("jdbc.password", this.pwd);
		try {
			String path = StringUtil.getRootPath("config/jdbc.properties");
			File file  = new File(path);
			  
    		props.store(new FileOutputStream(file), "jdbc.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	
	
	/**
	 * 保存eop参数配置
	 */
	private void saveEopParams(){
		InputStream in  = FileUtil.getResourceAsStream("eop.properties");
		String webroot  = StringUtil.getRootPath();
		Properties props = new Properties();
		try {
			props.load(in);
			props.setProperty("storage.app_data", webroot+"/products/base");
			props.setProperty("storage.products", webroot+"/products");
			props.setProperty("path.imageserver", webroot+"/statics");
			props.setProperty("storage.EOPServer", webroot);
			props.setProperty("path.context_path", this.getRequest().getContextPath());
			props.setProperty("domain.imageserver", "http://"+ this.getRequest().getServerName()+":"+this.getRequest().getLocalPort() + this.getRequest().getContextPath() +"/statics"  );
			String path = StringUtil.getRootPath("eop.properties");
			File file  = new File(path);		
			props.store(new FileOutputStream(file), "eop.properties");
			EopSetting.init(props);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String doInstall(){
		try{
		 
			eopInstallManager.install(uname,pwd,"localhost",productid);
			this.json="{result:1}";
		}catch (RuntimeException e) {
			e.printStackTrace();
			this.json="{result:0}";
		}	
		return this.JSON_MESSAGE;
	}

	public String testConnection(){
		
		try{
			if("mysql".equals(dbtype)){
				BasicDataSource newDataSource = createMySqlDBSource();
				this.jdbcTemplate.setDataSource(newDataSource);
				this.jdbcTemplate.execute("drop table if exists test");
	 
				this.json="{result:1}";
			}
		}catch(RuntimeException e){
			this.json="{result:0}";
		}
		
		return this.JSON_MESSAGE;
	}
	
	public String testReady(){
		try{
			
				this.jdbcTemplate.execute("drop table if exists test");
				this.json="{result:1}";
			
		}catch(RuntimeException e){
			this.json="{result:0}";
		}		
		
		return this.JSON_MESSAGE;
	}
	
	private BasicDataSource createMySqlDBSource(){
		BasicDataSource newDataSource = new BasicDataSource();
		newDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		newDataSource.setUrl("jdbc:mysql://"+dbhost+"/"+this.dbname+"?useUnicode=true&characterEncoding=utf8");
		newDataSource.setUsername(this.uname);
		newDataSource.setPassword(this.pwd);	
		return newDataSource;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getDbhost() {
		return dbhost;
	}

	public void setDbhost(String dbhost) {
		this.dbhost = dbhost;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getDbtype() {
		return dbtype;
	}


	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}


	public String getDbname() {
		return dbname;
	}


	public void setDbname(String dbname) {
		this.dbname = dbname;
	}


	public EopInstallManager getEopInstallManager() {
		return eopInstallManager;
	}


	public void setEopInstallManager(EopInstallManager eopInstallManager) {
		this.eopInstallManager = eopInstallManager;
	}


	public String getOsVersion() {
		return osVersion;
	}


	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}


	public String getJavaVersion() {
		return javaVersion;
	}


	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}


	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}


	public String getProductid() {
		return productid;
	}


	public void setProductid(String productid) {
		this.productid = productid;
	}


	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		return simpleJdbcTemplate;
	}


	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}


	public BasicDataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}

	
}

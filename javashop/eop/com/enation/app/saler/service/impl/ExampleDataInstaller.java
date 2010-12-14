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

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.enation.app.saler.service.IInstaller;
import com.enation.app.saler.service.InstallUtil;
import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.framework.database.ISqlFileExecutor;
import com.enation.framework.util.FileUtil;


/**
 * 示例数据安装器
 * @author kingapex
 *
 */
public class ExampleDataInstaller implements IInstaller {
	private ISqlFileExecutor sqlFileExecutor;
 
	protected final Logger logger = Logger.getLogger(getClass());
	
	public void install(String productId,  Node fragment) {
		
		
		try {
			
			String dataSqlPath  =EopSetting.PRODUCTS_STORAGE_PATH + "/" + productId +"/example_data.sql";
			if(logger.isDebugEnabled()){
				this.logger.debug("安装datasqlPath:"+dataSqlPath);
			}
			
			if(!"base".equals(productId)){
				InstallUtil.putMessaage("正在安装示例数据,可能要花费较长时间,请稍后<img src='resource/com/enation/app/saler/widget/product/loading.gif'");
			}else{
				InstallUtil.putMessaage("正在安装基础数据...");
			}
			if( new File(dataSqlPath).exists()){
				
				//安装示例数据
				String content =FileUtil.read(EopSetting.PRODUCTS_STORAGE_PATH + "/" + productId +"/example_data.sql", "utf-8");


				if("2".equals(EopSetting.RUNMODE) ){
					EopSite site  = EopContext.getContext().getCurrentSite();
					content = content.replaceAll("<userid>",String.valueOf( site.getUserid() ));
					content = content.replaceAll("<siteid>",String.valueOf( site.getId()));
				}else{
					content = content.replaceAll("_<userid>","");
					content = content.replaceAll("_<siteid>","");		
					content = content.replaceAll("/user/<userid>/<siteid>","");
				}
				
				sqlFileExecutor.execute(content); 
				if(logger.isDebugEnabled()){
					this.logger.debug("示例数据安装完毕");
				}
			}else{
				System.out.println(dataSqlPath+" no exist");
			}
			
			
			FileUtil.copyFolder( 
					EopSetting.PRODUCTS_STORAGE_PATH + "/" + productId + "/attachment/",
					EopSetting.IMG_SERVER_PATH + EopContext.getContext().getContextPath()+ "/attachment/");
		 

			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("安装示例数据出错...");
		}		 

	}
	
	public void setSqlFileExecutor(ISqlFileExecutor sqlFileExecutor) {
		this.sqlFileExecutor = sqlFileExecutor;
	}
	
	

}

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
package com.enation.eop.processor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.EopSetting;
import com.enation.eop.context.ConnectType;
import com.enation.eop.core.resource.IAppManager;
import com.enation.eop.core.resource.model.EopApp;
import com.enation.eop.processor.backend.BackgroundProcessor;
import com.enation.eop.processor.facade.FacadePageProcessor;
import com.enation.eop.processor.facade.WebResourceProcessor;
import com.enation.eop.processor.facade.WidgetProcessor;
import com.enation.eop.processor.facade.WidgetSettingProcessor;
import com.enation.framework.context.spring.SpringContextHolder;

/**
 * @author kingapex
 * @version 1.0
 * @created 13-十月-2009 11:36:29 
 */
public abstract class ProcessorFactory {

	/**
	 * 
	 * @param uri
	 */
	public static Processor newProcessorInstance(String uri,HttpServletRequest httpRequest){
		Processor processor =null;
//		if(uri.endsWith(".action") || uri.endsWith(".do") ) return null;
		if(uri.startsWith("/statics")) return null;
		
		//单机版不过滤install
		if( "1".equals( EopSetting.RUNMODE )){
			if(uri.startsWith("/install")) return null;
			
		}
		IAppManager appManager = SpringContextHolder.getBean("appManager");
		List<EopApp> appList  =  appManager.list();
		String path =httpRequest.getServletPath();
		for(EopApp app:appList){
			if(app.getDeployment()==ConnectType.remote) continue;
			
			if(path.startsWith(app.getPath() +"/admin" )) {
				if( isExinclude (path)){return null;}
				
				processor = new BackgroundProcessor();
				return processor;
			} 
			
			if( path.startsWith( app.getPath()  ) ){
				return null;
			}
		}
		
		if(uri.startsWith("/validcode")) return null;
		if(uri.startsWith("/editor/")) return null;
		if(uri.startsWith("/eop/")) return null;
		if(uri.endsWith("favicon.ico")) return null;
		
		if (uri.startsWith("/resource/")) { 
			return new WebResourceProcessor();
		} 
		
		if(isExinclude(uri)) return null;
 
		if (uri.startsWith("/admin/")) { 
			if (!uri.startsWith("/admin/themes/")) {
					processor = new BackgroundProcessor();
			}
		} else if (uri.startsWith("/widget")) {
			
			if(uri.startsWith("/widgetSetting/")){
				processor = new WidgetSettingProcessor();
			}else if(uri.startsWith("/widgetBundle/")){
			//	processor = new WidgetBundleProcessor();
			}else{		 
				processor = new WidgetProcessor();
			}
		} else{
 
			if(uri.endsWith(".action") || uri.endsWith(".do") ) return null;
			if(EopSetting.TEMPLATEENGINE.equals("on"))
				processor = new FacadePageProcessor();
		}
		 
		return processor;
	}
	
 

	private static boolean isExinclude(String uri){
		
		String[] exts=new String[]{"jpg","gif","js","png","css","doc","xls","swf"};
		for(String ext:exts){ 
			if(uri.toUpperCase().endsWith(ext.toUpperCase())){
				return true;
			}
		}
		
		return false;
	}

}
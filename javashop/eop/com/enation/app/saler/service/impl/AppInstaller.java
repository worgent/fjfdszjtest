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

import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.enation.app.saler.service.IInstaller;
import com.enation.eop.sdk.App;
import com.enation.framework.database.IDBRouter;

/**
 * 应用安装器
 * @author kingapex
 * 2010-1-23下午03:17:08
 */
public class AppInstaller implements IInstaller {
	
	private IDBRouter shopSaasDBRouter;
 
 
	private  Map<String,App>  apps;
 
	public void install(String productId, Node fragment) {
		
 
		
		NodeList nodeList  = fragment.getChildNodes();
		for(int i=0;i<nodeList.getLength();i++){
			Node node = nodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element el = (Element)node;
		   	String appid = el.getAttribute("id");
				//System.out.println("安装"+appid);
				App app = apps.get(appid);
				if(app!=null){
				 
					 
						app.install();
					 
					
				}else{
				//	System.out.println(appid+"是空");
				}
			}
		}
	}

	public  void setApps(Map<String, App> apps) {
		this.apps = apps;
	}

	public  Map<String, App> getApps() {
		return apps;
	}

 
	
}

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

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.enation.app.saler.service.IInstaller;
import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.IBorderManager;
import com.enation.eop.core.resource.model.Border;
import com.enation.framework.util.FileUtil;

/**
 * 挂件边框安装器
 * @author kingapex
 * 2010-1-25上午10:54:39
 */
public class BorderInstaller implements IInstaller {
	private IBorderManager borderManager;
 
	public void install(String productId,  Node fragment) {

		try {
			FileUtil.copyFolder(
					EopSetting.PRODUCTS_STORAGE_PATH + "/" + productId + "/borders/",
					EopSetting.EOP_PATH +EopContext.getContext().getContextPath()+ "/borders/");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("安装borders出错...");
		}
		
		if (fragment.getNodeType() == Node.ELEMENT_NODE) {
			Element themeNode = (Element) fragment;
			 NodeList nodeList = themeNode.getChildNodes();
			 for( int i=0;i<nodeList.getLength();i++){
				 Node node  = nodeList.item(i);
				 if(node.getNodeType() ==  Node.ELEMENT_NODE){
					 Element el = (Element) node;
					 String id  = el.getAttribute("id");
					 String name  = el.getAttribute("name");
					 Border border = new Border();
					 border.setBorderid(id);
					 border.setBordername(name);
					 border.setThemepath(themeNode.getAttribute("id"));
					 borderManager.add(border);
				 }
			 }

		}
	}
	public void setBorderManager(IBorderManager borderManager) {
		this.borderManager = borderManager;
	}
	

}

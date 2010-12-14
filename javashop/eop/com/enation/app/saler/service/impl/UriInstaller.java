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

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.enation.app.saler.service.IInstaller;
import com.enation.app.saler.service.InstallUtil;
import com.enation.eop.core.resource.IThemeUriManager;
import com.enation.eop.core.resource.model.ThemeUri;
import com.enation.framework.util.StringUtil;

/**
 * Uri安装器
 * @author kingapex
 * 2010-1-20下午05:37:23
 */
public class UriInstaller implements IInstaller {
	
	protected final Logger logger = Logger.getLogger(getClass());
	private IThemeUriManager themeUriManager;
	
	
	public void install(String productId ,Node fragment)  {
 		
		NodeList uriList = fragment.getChildNodes();
		InstallUtil.putMessaage("正在安装uri映射...");
		this.installUri(uriList);
		InstallUtil.putMessaage("uri映射安装完成!");
	}

	 
	/**
	 * 将一个NodeList集合中的menu结点数据保存到库中
	 * @param nodeList
	 * @param parentId
	 */
	private void installUri(NodeList nodeList){
		 
		for(int i=0;i<nodeList.getLength();i++){
			Node node = nodeList.item(i);
			if(node.getNodeType() ==Node.ELEMENT_NODE){
				this.installUri((Element)node);
			}
		}
	}
	
	
	
	
	/**
	 * 将一个uri 数据保存在库中
	 * @param ele
	 * @param parentId
	 */
	private void installUri(Element ele){
	 
		try {
			
			ThemeUri themeUri = new ThemeUri();
		
			themeUri.setUri(ele.getAttribute("from"));
			themeUri.setPath(ele.getAttribute("to"));
			String name = ele.getAttribute("name");
			String point  = ele.getAttribute("point");
			
			//2.1.3版本新增页面名称和消耗积分，流量统计所用--kingapex
			if(name!=null)
				themeUri.setPagename(name);
			
			if(!StringUtil.isEmpty(point)){
				themeUri.setPoint( Integer.valueOf(point ));
			}
			
			themeUriManager.add(themeUri);			
			NodeList children = ele.getChildNodes();
			
			if(children!=null){
				this.installUri(children);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("install uri error");
		}
	}


	public IThemeUriManager getThemeUriManager() {
		return themeUriManager;
	}


	public void setThemeUriManager(IThemeUriManager themeUriManager) {
		this.themeUriManager = themeUriManager;
	}

	
}

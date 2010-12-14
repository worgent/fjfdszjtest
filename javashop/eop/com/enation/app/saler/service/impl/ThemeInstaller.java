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
import com.enation.eop.core.resource.ISiteManager;
import com.enation.eop.core.resource.IThemeManager;
import com.enation.eop.core.resource.model.Theme;

/**
 * 前台主题安装器
 * 
 * @author kingapex 2010-1-20下午10:56:25
 */
public class ThemeInstaller implements IInstaller {
	private IInstaller borderInstaller;
	private IThemeManager themeManager;
	private ISiteManager siteManager;

	private String productId;
	
 
	public void install(String productId,  Node fragment) {
		this.productId = productId;
		NodeList themeList = fragment.getChildNodes();
		this.install(themeList);
	}
	protected final Logger logger = Logger.getLogger(getClass());
	private void install(Element themeNode) {
		String isdefault = themeNode.getAttribute("default");
		Theme theme = new Theme();
		theme.setProductId(productId);
		theme.setPath(themeNode.getAttribute("id"));
		theme.setThemename(themeNode.getAttribute("name"));
		theme.setThumb("preview.png");
		InstallUtil.putMessaage("安装主题"+theme.getThemename()+"...");
		String commonAttr= themeNode.getAttribute("isCommonTheme");
		commonAttr =commonAttr==null?"":commonAttr;
		Boolean isCommonTheme = (commonAttr.toUpperCase().equals("TRUE"));
		Integer themeid  = this.themeManager.add(theme,isCommonTheme);
		if(logger.isDebugEnabled()){
			logger.debug("install "+ theme.getThemename() +",default :" +isdefault);
		}
		if("yes".equals( isdefault)){
			if(logger.isDebugEnabled())
				logger.debug("change theme["+themeid+"] ");
			siteManager.changeTheme(themeid);
		}
		
		borderInstaller.install(productId,  themeNode);
		InstallUtil.putMessaage("完成!");
	}

	private void install(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				this.install((Element) node);
			}
		}
	}

	public IInstaller getBorderInstaller() {
		return borderInstaller;
	}

	public void setBorderInstaller(IInstaller borderInstaller) {
		this.borderInstaller = borderInstaller;
	}

	public IThemeManager getThemeManager() {
		return themeManager;
	}

	public void setThemeManager(IThemeManager themeManager) {
		this.themeManager = themeManager;
	}

	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}
	
		

}

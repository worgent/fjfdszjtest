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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.enation.app.saler.service.IAdminThemeInfoFileLoader;
import com.enation.eop.EopSetting;

public class AdminThemeInfoLoaderImpl implements IAdminThemeInfoFileLoader {

	
	public Document load(String productId, String path,
			Boolean isCommonTheme) {
		String xmlFile = null;
		if (isCommonTheme) {
			xmlFile = EopSetting.APP_DATA_STORAGE_PATH + "/adminthemes/" + path + "/themeini.xml";
		} else {
			xmlFile = EopSetting.PRODUCTS_STORAGE_PATH+"/"+productId + "/adminthemes/" + path
					+ "/themeini.xml";//注意！现在资源ＳＤＫ未解决文件复制时的source目录问题
		}
		xmlFile="file:///"+xmlFile;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);
			return document;
		} catch (Exception e) {
			throw new RuntimeException("load [" + productId + " , " + path + "] themeini error!FileName:"+xmlFile);
		}

	}

}

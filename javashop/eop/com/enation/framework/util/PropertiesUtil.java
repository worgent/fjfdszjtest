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
package com.enation.framework.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;



/**
 * 属性文件工具类
 * @author kingapex
 * 2010-1-6下午02:12:11
 */
public class PropertiesUtil {

	private PropertiesUtil(){}
	
	private HashMap propertiesMap;

	public PropertiesUtil(String filePath) {
		InputStream in  = FileUtil.getResourceAsStream(filePath);
		load(in);
	}

	public void load(InputStream in) {
		try {
			Properties props = new Properties();
			propertiesMap = new HashMap();
			props.load(in);
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String Property = props.getProperty(key);
				propertiesMap.put(key, Property);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据Key获取属性值
	 * 
	 * @param key
	 * @return
	 */
	public String getProperties(String key) {
		Object value = propertiesMap.get(key);
		if (value != null) {
			return value.toString();
		}
		return null;
	}

	/**
	 * 获取存放键和值的map
	 * 
	 * @return
	 */
	public HashMap getPropertiesMap() {
		return propertiesMap;
	}

	/**
	 * 测试部分
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PropertiesUtil pu = new PropertiesUtil(
				"E:\\ProductSpace\\EOA\\resources\\config\\info.properties");

	}

}

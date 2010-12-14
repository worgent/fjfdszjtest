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
package com.enation.javashop.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.enation.javashop.core.model.support.Adjunct;
import com.enation.javashop.core.model.support.AdjunctGroup;
import com.enation.javashop.core.model.support.SpecJson;

public abstract class GoodsUtils {
	
	
	public static List getSpecList(String specString){
		if(specString==null || specString.equals("[]") ||specString.equals("") ){
			return new ArrayList();
		} 
		JSONArray j1 = JSONArray.fromObject(specString);
		List<SpecJson> list =(List) JSONArray.toCollection(j1, SpecJson.class);		
		return list;
	}
	
	
	/**
	 * 将一个
	 * 
	 * @param adjString
	 * @return
	 */
	public static AdjunctGroup converAdjFormString(String adjString) {
		if (adjString == null) {
			return null;
		}
		Map classMap = new HashMap();

		classMap.put("adjList", Adjunct.class);
		JSONObject j1 = JSONObject.fromObject(adjString);
		AdjunctGroup adjunct = (AdjunctGroup) JSONObject.toBean(j1,
				AdjunctGroup.class, classMap);
		return adjunct;
	}


 
		
}

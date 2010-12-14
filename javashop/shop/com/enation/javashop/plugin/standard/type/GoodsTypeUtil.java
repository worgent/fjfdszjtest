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
package com.enation.javashop.plugin.standard.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.enation.javashop.core.model.Attribute;
import com.enation.javashop.core.model.GoodsParam;
import com.enation.javashop.core.model.support.ParamGroup;

/**
 * 商品类型工具类
 * @author kingapex
 * 2010-1-10下午06:30:10
 */
public class GoodsTypeUtil {
	private GoodsTypeUtil(){};
	
	public static ParamGroup[] converFormString(String params) {
		if (params == null || params.equals("") || params.equals("[]"))
			return null;
		Map classMap = new HashMap();

		classMap.put("paramList", GoodsParam.class);
		JSONArray jsonArray = JSONArray.fromObject(params);

		Object obj = JSONArray.toArray(jsonArray, ParamGroup.class, classMap);

		if (obj == null)
			return null;

		return (ParamGroup[]) obj;
	}
	
	/**
	 * 将一个json字串转为list
	 * @param props
	 * @return
	 */
	public static List<Attribute> converAttrFormString(String props){
		if (props == null || props.equals(""))
			return new ArrayList();

		JSONArray jsonArray = JSONArray.fromObject(props);
		List<Attribute> list = (List) JSONArray.toCollection(jsonArray,
				Attribute.class);
		return list;
	}
	
}

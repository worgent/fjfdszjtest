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
package com.enation.eop.test.widget;

import net.sf.json.JSONObject;

import org.junit.Test;

public class WidgetContentBuilderTest {

	@Test
	public void testSimpleContentBuilder() {
		/*String json = "{'status':0,'type':1,'body':{\"className\":\"\",\"style\":\"\",\"target\":\"\",\"text\":\"abc\",\"url\":\"\"},'havetitle':'true',title:{\"className\":\"\",\"havemore\":false,\"more\":null,\"style\":\"height:30px;background-color:red\",\"target\":\"\",\"text\":\"最新商品\",\"url\":\"\"}}";
		IWidgetContentBuilder builder = new SimpleContentBuilder();
		System.out.println(builder.build(json));*/
		
//		json ="{'status':0,'type':2,'body':[{\"className\":\"\",\"style\":\"\",\"target\":\"\",\"text\":\"新闻1\",\"url\":\"\"},{\"className\":\"\",\"style\":\"\",\"target\":\"\",\"text\":\"新闻2\",\"url\":\"\"},{\"className\":\"\",\"style\":\"\",\"target\":\"\",\"text\":\"新闻3\",\"url\":\"\"}],'havetitle':'false'}";
//		builder = new ListContentBuilder();
//		System.out.println(builder.build(json));
	}

	//@Test
	public void testJson(){
		String json ="{'status':0,'type':2,'body':[{\"className\":\"\",\"style\":\"\",\"target\":\"\",\"text\":\"新闻1\",\"url\":\"\"},{\"className\":\"\",\"style\":\"\",\"target\":\"\",\"text\":\"新闻2\",\"url\":\"\"},{\"className\":\"\",\"style\":\"\",\"target\":\"\",\"text\":\"新闻3\",\"url\":\"\"}],'havetitle':'false'}";
		JSONObject jsonObject = JSONObject.fromObject(json);
		System.out.println(jsonObject.get("ssdfsdfsdfsdfsdf"));
	}
}

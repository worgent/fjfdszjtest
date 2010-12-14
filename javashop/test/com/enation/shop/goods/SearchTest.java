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
package com.enation.shop.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.framework.database.Page;
import com.enation.framework.test.SpringTestSupport;
import com.enation.javashop.core.model.support.GoodsView;
import com.enation.javashop.core.service.IGoodsSearchManager;

public class SearchTest extends SpringTestSupport {
	
	private IGoodsSearchManager goodsSearchManager;
	
	@Before
	public void mock(){
		
		goodsSearchManager = this.getBean("goodsSearchManager");
		
        EopSite site = new EopSite();
        site.setUserid(2);
        site.setId(2);
		EopContext context  = new EopContext();
		context.setCurrentSite(site);
		EopContext.setContext(context);		
		
	}
	
	
	@Test
	public void testSearch(){
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("cat_path", "0|1");
//		params.put("propStr", "0_4");
		//params.put("keyword", "百搭裙子");
//		params.put("", "");
//		params.put("", "");
		
		Page page  = goodsSearchManager.search(1, 100, params);
		List list = (List)page.getResult();
		for(int i=0;i<list.size();i++){
			GoodsView goods= (GoodsView) list.get(i);
			System.out.println(goods.getName());
		}
	}
	
	/**
	 * 		String cat_path = params.get("cat_path");
		String order = params.get("order");
		String brandStr = params.get("brandStr");
		String propStr = params.get("propStr");
		String keyword = params.get("keyword");
		String minPrice = params.get("minPrice");
		String maxPrice = params.get("maxPrice");
	 */
	
	
}

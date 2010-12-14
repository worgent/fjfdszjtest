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
package com.enation.javashop.widget.goods.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.enation.eop.impl.support.BaseSupport;
import com.enation.framework.database.StringMapper;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.mapper.GoodsListMapper;
import com.enation.javashop.core.model.support.GoodsView;

public class GoodsDataProvider extends BaseSupport  {
	
	private static Map<Integer,String> orderMap;
	static{
		orderMap = new HashMap<Integer, String>();
		orderMap.put(0, " goods_id desc");
		orderMap.put(1, " last_modify desc");
		orderMap.put(2, " last_modify asc");
		orderMap.put(3, " price desc");
		orderMap.put(4, " view_count desc");
		orderMap.put(5, " buy_count desc");
	}
	

	
	public List list(Term term ,int num){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from goods where disabled=0 and market_enable=1");
		String tagids  = term.getTagid();
		String brandids = term.getBrandid();
		String catids = term.getCatid();
		Integer typeid = term.getTypeid();
		String  keyword = term.getKeyword();
		
		if(!StringUtil.isEmpty(keyword)){
			sql.append(" and (name like '%"+keyword+"%' or brief like '%"+keyword+"%' or intro like '%"+keyword+"%'   )");
		}
		
		
		if(typeid!=null ){
			sql.append(" and type_id ="+typeid);		
		}
		
		if(!StringUtil.isEmpty(brandids)){
			sql.append(" and brand_id in("+brandids+")");		
		}
		 
		if(!StringUtil.isEmpty(catids)){
			sql.append(" and cat_id in("+catids+")");		
		}		
		
 
		if(!StringUtil.isEmpty(tagids)){
			String filter= this.goodsIdInTags(tagids);
			filter =filter.equals("")?"-1":filter;
			sql.append( " and goods_id in(" +filter+")" );
		}
		
		if( term.getMaxprice() !=null){
			sql.append(" and price<="+ term.getMaxprice());
		}
		
		if( term.getMinprice() !=null){
			sql.append(" and price>="+ term.getMinprice());
		}
		
		Integer order = term.getOrder();
		if(order==null){
			order = 1;
		}
		
		sql.append( " order by "+ orderMap.get(order));
		
		List list = this.baseDaoSupport.queryForList(sql.toString(), 1, num, new GoodsListMapper());
		if(list!=null  && !list.isEmpty()){
		  ((GoodsView)list.get(list.size()-1)).setIsLast(true) ;
		  ((GoodsView)list.get(0)).setIsFirst(true) ;
		}
		list = list == null ? new ArrayList() : list;
		return list; 
	}
	
	/**
	 * 读取在tags范围的goodsid字串，以,号分隔
	 * @param tags
	 * @return 如果没有找到返回 ""
	 */
	private String goodsIdInTags(String tags){
		String sql ="select rel_id from tag_rel where tag_id in (" +tags+")";
		List<String> goodsIdList = this.baseDaoSupport.queryForList(sql, new StringMapper());
		return StringUtil.listToString(goodsIdList, ",");
	}
	
	
	
	
}

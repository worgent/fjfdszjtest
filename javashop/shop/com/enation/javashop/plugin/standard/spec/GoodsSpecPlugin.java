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
package com.enation.javashop.plugin.standard.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.core.view.freemarker.FreeMarkerPaser;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Product;
import com.enation.javashop.core.model.SpecValue;
import com.enation.javashop.core.model.Specification;
import com.enation.javashop.core.plugin.AbstractGoodsPlugin;
import com.enation.javashop.core.plugin.IGoodsDeleteEvent;
import com.enation.javashop.core.service.IProductManager;


/**
 * 商品规格插件
 * @author enation
 *
 */
public class GoodsSpecPlugin extends AbstractGoodsPlugin implements IGoodsDeleteEvent{
	
 
	private IProductManager productManager;
	public void addTabs(){
	//	this.addTags(4, "规格");
	}
	

	private void processGoods(Map goods, HttpServletRequest request){
		HttpServletRequest httpRequest =ThreadContextHolder.getHttpRequest();
		String haveSpec = httpRequest.getParameter("haveSpec");
		goods.put("have_spec", haveSpec);
		//未开启规格
		if("0".equals(haveSpec)){
			
			goods.put("cost", httpRequest.getParameter("cost") );
			goods.put("price", httpRequest.getParameter("price") );
			goods.put("weight", httpRequest.getParameter("weight") );
			goods.put("store", httpRequest.getParameter("store") );

			
		}else if("1".equals(haveSpec)){
			
			//记录规格相册关系
			String specs_img = httpRequest.getParameter("spec_imgs");
			specs_img = specs_img==null?"{}":specs_img;
			goods.put("specs", specs_img);
			
			
			String[] prices = httpRequest.getParameterValues("prices");
			String[] costs = httpRequest.getParameterValues("costs");
			String[] stores = httpRequest.getParameterValues("stores");
			String[] weights = httpRequest.getParameterValues("weights");
			
			if(prices!=null && prices.length>0){ goods.put("price", prices[0] ); }
			if(costs!=null && costs.length>0){ goods.put("cost", costs[0] ); }
			if(stores!=null && stores.length>0){ goods.put("store", stores[0] ); }
			if(weights!=null && weights.length>0){ goods.put("weight", weights[0] ); }
 
		}
		
		if(  StringUtil.isEmpty((String)goods.get("cost")) ){ goods.put("cost", 0);}
		if(StringUtil.isEmpty((String)goods.get("price"))){ goods.put("price", 0);}
		if(StringUtil.isEmpty((String)goods.get("weight"))){ goods.put("weight", 0);}
		if(StringUtil.isEmpty((String)goods.get("store"))){ goods.put("store", 0);}		
	}

	public void onBeforeGoodsAdd(Map goods, HttpServletRequest request) {
		this.processGoods(goods, request);
	}
	

	public void onBeforeGoodsEdit(Map goods, HttpServletRequest request)  {
		this.processGoods(goods, request);
	}
	
	private  void processSpec(Map goods, HttpServletRequest request){
		if(goods.get("goods_id")==null) throw new RuntimeException("商品id不能为空");
		Integer goodsId = Integer.valueOf(goods.get("goods_id").toString());
		HttpServletRequest httpRequest =ThreadContextHolder.getHttpRequest();
		
		String haveSpec = httpRequest.getParameter("haveSpec");
		
		if("1".equals(haveSpec)){
			
			
			String[] specidsAr = httpRequest.getParameterValues("specids");
			String[] specvidsAr = httpRequest.getParameterValues("specvids");
			
			
			
			String[] sns = httpRequest.getParameterValues("sns");
			String[] prices = httpRequest.getParameterValues("prices");
			String[] costs = httpRequest.getParameterValues("costs");
			String[] stores = httpRequest.getParameterValues("stores");
			String[] weights = httpRequest.getParameterValues("weights");
			
			
			List<Product> productList = new ArrayList<Product>();
			int i=0;
			for(String sn  :sns){
				
				if(sn==null || sn.equals(""))
					sn=goods.get("sn")+"-"+(i+1);
			
				
				List<SpecValue> valueList = new ArrayList<SpecValue>();
				int j =0;
				String[] specids =specidsAr[i].split(",");
				String[] specvids= specvidsAr[i].split(",");
				
				for(String specid:specids){
					SpecValue specvalue = new SpecValue();
					specvalue.setSpec_value_id(Integer.valueOf(specvids[j].trim()));
					specvalue.setSpec_id(Integer.valueOf(specid.trim()));
					valueList.add(specvalue);
					j++;
				}
				Product product = new Product();
				product.setGoods_id(goodsId);
				product.setSpecList(valueList);
				product.setName((String)goods.get("name"));
				product.setSn( sn );
				
				String[] specvalues =  httpRequest.getParameterValues("specvalue_"+i);
				product.setSpecs(StringUtil.arrayToString(specvalues, "、"));
				//价格
				if(null==prices[i] || "".equals(prices[i]))
					product.setPrice( 0D );
				else
					product.setPrice(  Double.valueOf(   prices[i]) );
				
				
				
				if(null==stores[i] || "".equals(stores[i]))
					product.setStore(0);
				else
					product.setStore(Integer.valueOf(stores[i]));
				
				//成本价
				if(null==costs[i] || "".equals(costs[i]))
					product.setCost( 0D);
				else	
					product.setCost( Double.valueOf( costs[i]));
				
				//重量
				if(null==weights[i] || "".equals(weights[i]))
					product.setWeight(0D);
				else									
					product.setWeight(Double.valueOf(weights[i]));
				
				productList.add(product);
				i++;
			} 
			
			this.productManager.add(productList);
		}else{
			Product product = new Product();
			product.setGoods_id(goodsId);
			product.setCost(  Double.valueOf( ""+goods.get("cost") ) );
			product.setPrice(   Double.valueOf( ""+goods.get("price"))  );
			product.setSn((String)goods.get("sn"));
			product.setStore(Integer.valueOf(""+goods.get("store")));
			product.setWeight(Double.valueOf( ""+goods.get("weight")));
			product.setName((String)goods.get("name"));
			List<Product> productList = new ArrayList<Product>();
			productList.add(product);
			this.productManager.add(productList);
	 
		}
	}
 
	public void onAfterGoodsAdd(Map goods, HttpServletRequest request)  {
		this.processSpec(goods, request);
		
	}
	
	
	
	/**
	 * 响应修改时填充数据事件
	 * <br/>形成规格list
	 */
	public String onFillGoodsEditInput(Map goods, HttpServletRequest request) {
		FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();
		Integer goods_id =  Integer.valueOf(goods.get("goods_id").toString());
		List<Specification> specList  =productManager.listSpecs(goods_id);
		List<Product> productList = productManager.list(goods_id);
		
		freeMarkerPaser.putData("productList", productList);
		freeMarkerPaser.putData("specList", specList);
		freeMarkerPaser.setPageName("spec");
//		freeMarkerPaser.setPageFolder("/");
		return freeMarkerPaser.proessPageContent();
	}
	
	
	
	/**
	 * 响应商品添加页填充数据事件
	 * <br/>
	 */
	public String onFillGoodsAddInput(HttpServletRequest request) {
		FreeMarkerPaser freeMarkerPaser =FreeMarkerPaser.getInstance();
		
		//freeMarkerPaser.setPageFolder("/plugin/spec");
		freeMarkerPaser.setPageName("spec");
		return freeMarkerPaser.proessPageContent();
	}



	public void onAfterGoodsEdit(Map goods, HttpServletRequest request)  {
		this.processSpec(goods, request);
	}
	
	
	public void onGoodsDelete(Integer[] goodsid) {
		 this.productManager.delete(goodsid);
	}

	public String getAuthor() {
		 
		return "kingapex";
	}

	public String getId() {
		 
		return "goodsspec";
	}

	public String getName() {
		 
		return "通用商品规格插件";
	}

	public String getType() {
		return "";
	}

	public String getVersion() {
		 
		return "1.0";
	}

	public void perform(Object... params) {
		
	}
 



	public IProductManager getProductManager() {
		return productManager;
	}



	public void setProductManager(IProductManager productManager) {
		this.productManager = productManager;
	}

	
	
}

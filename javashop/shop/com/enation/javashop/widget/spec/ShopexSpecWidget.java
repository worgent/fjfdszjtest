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
package com.enation.javashop.widget.spec;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.enation.javashop.core.model.Product;
import com.enation.javashop.core.model.Specification;
import com.enation.javashop.core.service.IProductManager;
import com.enation.javashop.widget.goods.AbstractGoodsDetailWidget;

/**
 * shopex式商品挂件
 * @author kingapex
 * 2010-2-4上午11:19:03
 */
public class ShopexSpecWidget extends AbstractGoodsDetailWidget {
 
	private IProductManager productManager;
	
	protected void config(Map<String, String> params) {
		
	}

 
	public IProductManager getProductManager() {
		return productManager;
	}

	public void setProductManager(IProductManager productManager) {
		this.productManager = productManager;
	}

	
	protected void execute(Map<String, String> params, Map goods) {
//		if( (""+goods.get("have_spec")).equals("0")){
//			this.showHtml=false;
//			return ;
//		}
		
		Integer goods_id = Integer.valueOf( goods.get("goods_id").toString() );
		List<Product> productList  = this.productManager.list(goods_id);
		
		if( (""+goods.get("have_spec")).equals("0")){
			this.putData("productid", productList.get(0).getProduct_id());
		}else{
			List<Specification> specList = this.productManager.listSpecs(goods_id);
		
			this.putData("productList", productList);
			this.putData("specList", specList);
		}
		this.putData("have_spec", goods.get("have_spec"));
	
		this.setPageName("spec");
		
	}

}

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
package com.enation.app.saler.widget.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.saler.service.IProductService;
import com.enation.eop.core.resource.model.EopProduct;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;

/**
 * 产品详细挂件
 * @author kingapex
 *2010-3-23上午08:38:09
 */
public class ProductDetailWidget extends AbstractWidget {
	private IProductService productService;
	
	protected void config(Map<String, String> params) {

	}

	
	protected void execute(Map<String, String> params) {
		  HttpServletRequest  request  = ThreadContextHolder.getHttpRequest();
		String productid = request.getParameter("productid");
		EopProduct product= productService.getProduct(productid);
 
		this.putData("product", product);
		
		IUserService userService = UserServiceFactory.getUserService();
		boolean isLogin =userService.isUserLoggedIn();
		this.putData("isLogin", isLogin);
		
	//	this.setPageFolder("/widgets/product");
		this.setPageName("productDetail");
	}

	public IProductService getProductService() {
		return productService;
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	
}

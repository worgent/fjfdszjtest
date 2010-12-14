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
package com.enation.eop.core.facade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.core.AbstractParser;
import com.enation.eop.core.AbstractWrapper;
import com.enation.eop.core.Request;
import com.enation.eop.core.RequestWrapper;
import com.enation.eop.core.Response;
import com.enation.eop.model.FacadePage;

/**
 * @author kingapex
 * @version 1.0
 * @created 22-十月-2009 18:12:27
 */
public abstract class AbstractFacadePageWrapper extends AbstractWrapper {

	protected FacadePage page;

	/**
	 * 
	 * @param page
	 */
	public AbstractFacadePageWrapper(FacadePage page,Request request){
		 super(request);
		 this.page = page;
	}


}
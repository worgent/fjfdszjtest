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
import com.enation.eop.core.AbstractParser;
import com.enation.eop.core.Request;
import com.enation.eop.model.FacadePage;

/**
 * 抽象的前台页面解析器<br/>
 * 包含有一个FacadePage的属性，解析器根据此属性的信息解析页面。
 * @author kingapex
 * @version 1.0
 * @created 22-十月-2009 18:12:27
 */
public abstract class AbstractFacadePagetParser extends AbstractParser {

	protected FacadePage page; 


	/**
	 * 强迫调用者传递FacadePage属性
	 * @param page
	 */
	public AbstractFacadePagetParser(FacadePage page,Request request){
		 super(request);
		 this.page= page;
	}
	
	public FacadePage getPage(){
		return this.page;
	}
  

}
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
package com.enation.eop.processor.facade;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.core.Response;
import com.enation.eop.core.facade.widget.IWidgetHtmlGetter;
import com.enation.eop.core.impl.StringResponse;
import com.enation.eop.impl.facade.widget.WidgetHtmlGetter;
import com.enation.eop.processor.Processor;
import com.enation.framework.util.RequestUtil;
import com.enation.framework.util.StringUtil;

/**
 * 挂件处理器<br/>
 * 处理挂件异步读取,如在编辑模板时挂件的创建和挂件更新
 * @author kingapex
 * @version 1.0
 * @created 22-十月-2009 16:36:13
 */
public class WidgetProcessor   implements Processor {


	
	/**
	 * 
	 * @param model
	 * @param httpResponse
	 * @param httpRequest
	 */
	public Response process(int mode, HttpServletResponse httpResponse, HttpServletRequest httpRequest){
		
		/*
		 * request中有挂件数据形成需要的参数
		 */
	    Map<String,String> widgetParams = RequestUtil.paramToMap(httpRequest);
	    
	    
		/*
		 * 调用挂件获取接口
		 * 传递给上述挂件参数 
		 */
		IWidgetHtmlGetter widgetGetter = new WidgetHtmlGetter();
		String content =widgetGetter.process(widgetParams);
		Response response = new StringResponse();
		//content = StringUtil.compressHtml(content);
		response.setContent(content);
		return response;
		
		/*Processor processor  =null;
		Widget widget =WidgetFactory.build(httpRequest);
	 
		String act  = httpRequest.getParameter("act");
		if("delete".equals(act)){
			processor =new WidgetDeleter(widget);
		}else{
			processor =new WidgetGeter(widget);
		}
		
		
		mode = widget.getApp().getDeployment();
		
		Response response  = processor.process(mode, httpResponse, httpRequest);
		
		return response;*/
	}

}
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
package com.enation.eop.impl.facade;

import java.util.Map;

import com.enation.eop.core.EopException;
import com.enation.eop.core.Response;
import com.enation.eop.core.facade.widget.IWidgetPaser;
import com.enation.eop.core.impl.StringResponse;
import com.enation.eop.sdk.widget.IWidget;
import com.enation.framework.context.spring.SpringContextHolder;

/**
 * 本地挂件解析器     
 * @author kingapex
 * 2010-2-8下午03:56:17
 */
public class LocalWidgetPaser implements IWidgetPaser {

	
	public String pase(Map<String, String> params) {
		if(params==null) throw new EopException("挂件参数不能为空");
		
		String widgetType = params.get("type");
		if(widgetType==null) throw new EopException("挂件类型不能为空");
		
	//	System.out.println("processor "+ widgetType);
		try{
			IWidget widget =SpringContextHolder.getBean(widgetType);
			String content;
			if(widget==null) content=("widget["+widgetType+"]not found");
			else             content= widget.process(params);;
			return content;
		}catch(Exception e){
			e.printStackTrace();
			return "widget["+widgetType+"]pase error ";
		}		
		
	}

}

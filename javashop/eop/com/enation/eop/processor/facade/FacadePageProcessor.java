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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.core.Response;
import com.enation.eop.core.facade.IPageParamJsonGetter;
import com.enation.eop.core.facade.IPagePaser;
import com.enation.eop.core.facade.IPageUpdater;
import com.enation.eop.core.impl.StringResponse;
import com.enation.eop.impl.facade.PageEditModeWrapper;
import com.enation.eop.processor.Processor;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.util.RequestUtil;
import com.enation.framework.util.StringUtil;

/**
 * 页面处理器
 * 
 * @author kingapex
 * @version 1.0
 * @created 22-十月-2009 18:12:29
 */
public class FacadePageProcessor implements Processor {

	/**
	 * 
	 *响应页面的三种操作,通过参数_method来识别，并分别通过三个接口来完成操作： 
	 * <li>GET:解析页面： {@link com.enation.eop.core.facade.IPagePaser}</li>
	 * <li>PUT:更新页面 ：{@link com.enation.eop.core.facade.IPageUpdater}</li>
	 * <li>PARAMJSON:获取页面挂件参数json串com.enation.eop.api.facade.IPageParamJsonGetter</li>
	 * </br>
	 * 页面的url会被读取并做为解析实际页面文件地址的依据
	 * @param mode
	 * @param httpResponse
	 * @param httpRequest
	 */
	public Response process(int mode, HttpServletResponse httpResponse,
			HttpServletRequest httpRequest) {

		String method = RequestUtil.getRequestMethod(httpRequest);
		String uri = RequestUtil.getRequestUrl(httpRequest);
		Response response = new StringResponse();

		// 解析页面
		if (method.equals("GET")) {
			IPagePaser paser = SpringContextHolder.getBean("facadePagePaser");
			if (UserServiceFactory.getUserService().isUserLoggedIn()
					&& httpRequest.getParameter("mode") != null) {
				paser = new PageEditModeWrapper(paser);
			}

			String content = paser.pase(uri);
			response.setContent(content);

		}

		// 更新页面
		if (method.equals("PUT")) {
			String params = httpRequest.getParameter("widgetParams");
			String content = httpRequest.getParameter("bodyHtml");
			IPageUpdater pageUpdater = SpringContextHolder
					.getBean("facadePageUpdater");
			pageUpdater.update(uri, content, params);
			response.setContent("{'state':0,'message':'页面保存成功'}");
		}

		// 获取参数json
		if (method.equals("PARAMJSON")) {
			IPageParamJsonGetter pageParamJsonGetter = SpringContextHolder
					.getBean("pageParamJsonGetter");
			String json = pageParamJsonGetter.getJson(uri);
			response.setContent(json);
		}

		return response;
	}

}
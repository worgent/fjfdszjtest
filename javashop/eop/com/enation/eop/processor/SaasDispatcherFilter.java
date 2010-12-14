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
package com.enation.eop.processor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.Response;
import com.enation.eop.core.resource.ISiteManager;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.view.freemarker.FreeMarkerPaser;
import com.enation.eop.utils.JspUtil;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;

public class SaasDispatcherFilter implements Filter {


	public void init(FilterConfig config) {
		// String widgetPackage = config.getInitParameter("widgetPackage");
		// WidgetFactory.initWidget(widgetPackage);

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		try {
		

			FreeMarkerPaser.set(new FreeMarkerPaser());
			this.initContext(httpRequest, httpResponse);

			String uri = httpRequest.getServletPath();

			//System.out.println(uri);
			Processor processor = ProcessorFactory.newProcessorInstance(uri,
					httpRequest);

			if (processor == null) {
				chain.doFilter(request, response);
			} else {

				Response eopResponse = processor.process(0, httpResponse,
						httpRequest);

				InputStream in = eopResponse.getInputStream();

				if (in != null) {
					// System.out.println("not null");
					BufferedInputStream bis = new BufferedInputStream(in);// 输入缓冲流
					response.setContentType(eopResponse.getContentType()
							+ "; charset=UTF-8");
					OutputStream output = response.getOutputStream();
					BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流

					byte data[] = new byte[4096];// 缓冲字节数

					int size = 0;

					if (bis != null) {
						size = bis.read(data);
						while (size != -1) {
							bos.write(data, 0, size);
							size = bis.read(data);
						}
					}
					bis.close();
					bos.flush();
					bos.close();
					// output.close();

				} else {

					chain.doFilter(request, response);
				}

			}
			FreeMarkerPaser.remove();
		} catch (RuntimeException exception) {
			exception.printStackTrace();
			request.setAttribute("message", exception.getMessage());
			String content = JspUtil.getJspOutput("/commons/error.jsp", httpRequest, httpResponse);
			response.getWriter().print(content);
//			response.flushBuffer();
		}
	}

	public void destroy() {

	}

	private void initContext(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		FreeMarkerPaser fmp =FreeMarkerPaser.getInstance();
		/**
		 * 将requst response及静态资源域名加入到上下文
		 */
		HttpSession session = httpRequest.getSession();
		ThreadContextHolder.getSessionContext().setSession(session);
		EopContext.setHttpRequest(httpRequest);
		ThreadContextHolder.setHttpRequest(httpRequest);
		ThreadContextHolder.setHttpResponse(httpResponse);
		httpRequest.setAttribute("staticserver", EopSetting.IMG_SERVER_DOMAIN);
		httpRequest.setAttribute("ext", EopSetting.EXTENSION);
		String servletPath  =   httpRequest.getServletPath();
		
		//System.out.println("uri : "+ RequestUtil.getRequestUrl(httpRequest));
		if(  servletPath.startsWith("/statics") ) return ;
		
 
		/** 
		 * 根据域名找到当前站点上下文
		 */
	
		String domain = httpRequest.getServerName();
		ISiteManager siteManager = SpringContextHolder.getBean("siteManager");
		EopSite site = siteManager.get(domain);
		EopContext context = new EopContext();
		context.setCurrentSite(site);
		EopContext.setContext(context);
			 
	 
		
		/**
		 * 设置freemarker的相关常量
		 */
		fmp.putData("ctx", httpRequest.getContextPath());
		fmp.putData("ext", EopSetting.EXTENSION);		
		fmp.putData("staticserver", EopSetting.IMG_SERVER_DOMAIN);
 
	}
}

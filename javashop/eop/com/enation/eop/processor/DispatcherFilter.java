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

import org.apache.http.protocol.RequestUserAgent;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.Response;
import com.enation.eop.core.resource.ISiteManager;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.view.freemarker.FreeMarkerPaser;
import com.enation.eop.utils.JspUtil;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.RequestUtil;

/**
 * 独立版的filter
 * @author kingapex
 * @version 1.0
 * @created 12-十月-2009 10:30:23
 */
public class DispatcherFilter implements Filter {

	public void init(FilterConfig config) {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		try {
			
			String uri = httpRequest.getServletPath();
			if(uri.startsWith("/statics")) { 
				chain.doFilter(httpRequest, httpResponse);
				return; 
			}
			
			if(!uri.startsWith("/install") && EopSetting.INSTALL_LOCK.toUpperCase().equals("NO")){
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/install");
				return;
			}
			if(uri.startsWith("/install")
			  && !uri.startsWith("/install/images")		
			  && EopSetting.INSTALL_LOCK.toUpperCase().equals("YES")){
				httpResponse.getWriter().write("如要重新安装，请先删除/install/install.lock文件，并重起web容器");
				return ;
			}
			
			FreeMarkerPaser.set(new FreeMarkerPaser());
			this.initContext(httpRequest, httpResponse);

			
			// System.out.println(uri);
			Processor processor = ProcessorFactory.newProcessorInstance(uri,
					httpRequest);

			if (processor == null) {
				chain.doFilter(request, response);
			//	System.out.println(uri+" null...");
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
			String content = JspUtil.getJspOutput("/commons/error.jsp",
					httpRequest, httpResponse);
			response.getWriter().print(content);
			// response.flushBuffer();
		}
	}

	public void destroy() {

	}

	private void initContext(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		FreeMarkerPaser fmp = FreeMarkerPaser.getInstance();
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
		String servletPath = httpRequest.getServletPath();

		// System.out.println("uri : "+ RequestUtil.getRequestUrl(httpRequest));
		if (servletPath.startsWith("/statics"))
			return;

		if( servletPath.startsWith("/install") ){
			EopSite site = new EopSite();
			site.setUserid(1);
			site.setId(1);
			site.setThemeid(1);
			EopContext context = new EopContext();
			context.setCurrentSite(site);
			EopContext.setContext(context);
		}else{
			EopContext context = new EopContext();
			EopSite site = new EopSite();
			site.setUserid(1);
			site.setId(1);
			site.setThemeid(1);
			context.setCurrentSite(site);
			EopContext.setContext(context);
			
			ISiteManager siteManager = SpringContextHolder.getBean("siteManager");
			site = siteManager.get("localhost");		 
		     
			context.setCurrentSite(site); 
			EopContext.setContext(context);
		}
		/**
		 * 设置freemarker的相关常量
		 */
		fmp.putData("ctx", httpRequest.getContextPath());
		fmp.putData("ext", EopSetting.EXTENSION);
		fmp.putData("staticserver", EopSetting.IMG_SERVER_DOMAIN);
	}

}
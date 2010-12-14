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
package com.enation.framework.gzip;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GZIPFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
    	
       if (req instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
        	//System.out.println("gizp:"+request.getServletPath());
            String ae = request.getHeader("accept-encoding");
            if (ae != null && ae.indexOf("gzip") != -1) {
                GZIPResponseWrapper gZIPResponseWrapper = new GZIPResponseWrapper(response);
                chain.doFilter(req, gZIPResponseWrapper);
                gZIPResponseWrapper.finishResponse();
                return;
          }
            chain.doFilter(req, res);            
       }else{
    	   System.out.println("not servlet request...");
       }
    }

    public void init(FilterConfig filterConfig) {
        System.out.println("The GZIP Is Enable");
    }

    public void destroy() {
        //
    }
}

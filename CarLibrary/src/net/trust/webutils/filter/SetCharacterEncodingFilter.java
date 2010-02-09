/*
 * 用于统一设置字符集问题,根据在web.xml里设置的字符集进行过滤
 * 	<filter>
		<filter-name>CharacterEncodingFilter</filter-name>
		<display-name>Character Encoding Filter</display-name>
		<description>no description</description>
		<filter-class>com.startech.filter.SetCharacterEncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>GB2312</param-value>
		</init-param>
	</filter>

	<filter-mapping>
		<filter-name>CharacterEncodingFilter</filter-name>
		<url-pattern>/*</url-pattern>

	</filter-mapping> 
 *
 */
package net.trust.webutils.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import org.apache.commons.logging.*;


public class SetCharacterEncodingFilter implements Filter {

	//private static final Log log = LogFactory.getLog(SetCharacterEncodingFilter.class);
  	protected String encoding = null;
	protected FilterConfig filterConfig = null;
	protected boolean ignore = true;


    public void destroy() {

        this.encoding = null;
        this.filterConfig = null;

    }


    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
	throws IOException, ServletException {
        if (ignore || (request.getCharacterEncoding() == null)) {
            String encoding = selectEncoding(request);
            if (encoding != null)
                request.setCharacterEncoding(encoding);
        }
        
        chain.doFilter(request, response);

    }

    public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
        String value = filterConfig.getInitParameter("ignore");
        if (value == null)
            this.ignore = true;
        else if (value.equalsIgnoreCase("true"))
            this.ignore = true;
        else if (value.equalsIgnoreCase("yes"))
            this.ignore = true;
        else
            this.ignore = false;
    }


    protected String selectEncoding(ServletRequest request) {

        return (this.encoding);

    }

}

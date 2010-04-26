package com.qzgf.utils.taglib;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.components.Component;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.util.ValueStack;
import com.qzgf.application.systemManage.taglib.config.SysConfig;
import com.qzgf.utils.comm.Constant;

/**
 * 页面基本信息
 * @author lsr
 *
 */
public class WebInfo extends Component {

	private PageContext pageContext;

	private String type;

	public WebInfo(ValueStack stack, PageContext pageContext) {
		super(stack);
		this.pageContext = pageContext;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(this.pageContext
				.getServletContext());
		SysConfig sysConfig = (SysConfig) wc.getBean("sysConfig");
		StringBuffer sb = new StringBuffer();
		if (this.getType().equalsIgnoreCase("guidename")) {
			sb.append(sysConfig.getGuideName());
		}

		if (this.getType().equalsIgnoreCase("poweredby")) {
			sb.append(" - ");
			sb.append("Powered By LSR[流泪的烧饼]");
		}

		if (this.getType().equalsIgnoreCase("meta")) {
			sb.append("<meta name=\"keywords\" content=\"");
			sb.append(sysConfig.getMetaKeywords());
			sb.append("\"/>\n");
			sb.append("<meta name=\"description\" content=\"");
			sb.append(sysConfig.getMetaDescription());
			sb.append("\"/>");
		}
		if (this.getType().equalsIgnoreCase("pagefoot")) {
			Locale locale = this.pageContext.getRequest().getLocale();
			ResourceBundleMessageSource messageSource = (ResourceBundleMessageSource) wc.getBean("messageSource");
			if (StringUtils.isNotBlank(sysConfig.getWebName())) {
				if (StringUtils.isNotBlank(sysConfig.getWebUrl())) {
					sb.append("<a href=\"");
					sb.append(sysConfig.getWebUrl());
					sb.append("\" target=\"_blank\">");
					sb.append(sysConfig.getWebName());
					sb.append("</a>");
				} else {
					sb.append(sysConfig.getWebName());
				}
			}
			if (StringUtils.isNotBlank(sysConfig.getGuideName())) {
				sb.append(" | ");
				if (StringUtils.isNotBlank(sysConfig.getGuideUrl())) {
					sb.append("<a href=\"");
					sb.append(sysConfig.getGuideUrl());
					sb.append("\" target=\"_blank\">");
					sb.append(sysConfig.getGuideName());
					sb.append("</a>");
				} else {
					sb.append(sysConfig.getGuideName());
				}
			}
			if (StringUtils.isNotBlank(sysConfig.getWebmasterEmail())) {
				sb.append(" | ");
				sb.append("<a href=\"mailto:");
				sb.append(sysConfig.getWebmasterEmail());
				sb.append("\">");
				sb.append(messageSource.getMessage("guide.contactus", null, locale));
				sb.append("</a>");
			}
			//隐私条款
			if (StringUtils.isNotBlank(sysConfig.getPrivacyUrl())) {
				sb.append(" | ");
				sb.append("<a href=\"");
				sb.append(sysConfig.getPrivacyUrl());
				sb.append("\" target=\"_blank\">");
				sb.append(messageSource.getMessage("guide.privacy", null, locale));
				sb.append("</a>");
			}
			if (StringUtils.isNotBlank(sysConfig.getCopyRightMsg())) {
				sb.append("<BR/>");
				sb.append(sysConfig.getCopyRightMsg());
			}
			sb.append("<BR/>");
			sb.append("<strong><font face=\"Tahoma\" size=\"1\" color=\"#A0A0A4\">");
			sb.append("Powered By ");
			sb.append("<a href=\"http://375352789.qzone.qq.com\" target='_blank'>流泪的烧饼</a>");
			sb.append(" V");
			sb.append(Constant.VSERION);
			sb.append(" &copy; 2009-2010</font></strong>");
		}

		try {
			writer.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

}

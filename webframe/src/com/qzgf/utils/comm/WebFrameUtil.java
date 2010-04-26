package com.qzgf.utils.comm;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.util.TextUtils;
 

public class WebFrameUtil {

	/**
	 * ����URL"/selfconfig/album?action=add"-->"/webframe/selfconfig/album.do?action=add"
	 * @param action
	 * @param request
	 * @return
	 */
	public static String getActionMappingURL(String action, HttpServletRequest request) {

		StringBuffer value = new StringBuffer(request.getContextPath());

		String servletMapping = Constant.SERVLET_MAPPING;
		if (servletMapping != null) {
			String queryString = null;
			int question = action.indexOf("?");
			if (question >= 0) {
				queryString = action.substring(question);
			}
			String actionMapping = getActionMappingName(action);
			if (servletMapping.startsWith("*.")) {
				value.append(actionMapping);
				value.append(servletMapping.substring(1));
			} else if (servletMapping.endsWith("/*")) {
				value.append(servletMapping.substring(0, servletMapping.length() - 2));
				value.append(actionMapping);
			} else if (servletMapping.equals("/")) {
				value.append(actionMapping);
			}
			if (queryString != null) {
				value.append(queryString);
			}
		}else {
			if (!action.startsWith("/")) {
				value.append("/");
			}
			value.append(action);
		}

		return (value.toString());
	}

	public static String getActionMappingName(String action) {
		String value = action;
		int question = action.indexOf("?");
		if (question >= 0) {
			value = value.substring(0, question);
		}
		int slash = value.lastIndexOf("/");
		int period = value.lastIndexOf(".");
		if ((period >= 0) && (period > slash)) {
			value = value.substring(0, period);
		}
		if (value.startsWith("/")) {
			System.out.println("value:"+value);
			return (value);
		} else {
			System.out.println("/Value:"+"/"+value);
			return ("/" + value);
		}
	}
	
	/**
	 * ��ñ��ص��ļ�Ŀ¼
	 * @param userID
	 * @return
	 */
	public static String getUserWebFilePath(String userID) {
		StringBuffer sb = new StringBuffer();
		int num = Math.abs(userID.hashCode());
		sb.append(Constant.ROOTPATH);
		
		sb.append("photo/");
		sb.append(num % 100);
		sb.append("/");
		sb.append(userID);
		sb.append("/");
		//ServletContext sc = ServletActionContext.getServletContext();  
		//String path = sc.getRealPath("/"); path).append(sb.toString()
		sb=new StringBuffer(sb.toString());
		File ft = new File(sb.toString());
		if (!ft.exists()) {
			ft.mkdirs();
		}
		return sb.toString();
	}
	
	
	public static String getUserWebPath(String userID) {
		StringBuffer sb = new StringBuffer();
		int num = Math.abs(userID.hashCode());
		//sb.append(Constant.ROOTPATH);
		sb.append("photo/photo/");
		sb.append(num % 100);
		sb.append("/");
		sb.append(userID);
		sb.append("/");
		return sb.toString();
	}
	
	//���ݹ���
	public static String filterText(String content, boolean useHTML, boolean useUBB, boolean useSmile) {
		if (!useHTML) {
			// sign = TagUtils.getInstance().filter(sign);
			content = TextUtils.htmlEncode(content);
		}
		if (useUBB) {
			content = getUBB2HTML(content);
		}
		if (useSmile) {
			content = replaceSmile(content);
		}
		content = content.replaceAll("\n", "<BR/>");
		content = filterScript(content);
		return content;
	}
	
	public static String filterScript(String txt) {
		return txt.replaceAll("[Ss][Cc][Rr][Ii][Pp][Tt]", "s.c.r.i.p.t");
	}
	
	public static String getUBB2HTML(String txt) {
		if (txt != null) {
			AutoFilter af = new AutoFilter(txt);
			txt = af.getFilteredStr();
		}
		return txt;
	}
	
	public static String replaceSmile(String txt) {
		if (txt != null) {
			return txt.replaceAll("\\{(\\d{1,2})\\}", "<img src=\"img/smile/$1.gif\" alt=\"smile\"/>");
		} else {
			return "";
		}
	}
}

package com.qzgf.comm;

import javax.servlet.http.HttpServletRequest;

public class EMSUtil {

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
	 * 返回:http://localhost:serverPort
	 * @param request
	 * @return
	 */
	public static String getWebRealPath(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		sb.append("http://");
		sb.append(request.getServerName());
		if (request.getServerPort() != 80) {
			sb.append(":");
			sb.append(request.getServerPort());
		}

		return sb.toString();
	}
	
	/**
	 * return: *.do
	 * @param action
	 * @return
	 */
	public static String getActionMappingURLWithoutPrefix(String action) {

		StringBuffer value = new StringBuffer();

		String servletMapping = Constant.SERVLET_MAPPING;
		if (servletMapping != null) {

			String queryString = null;
			int question = action.indexOf("?");
			if (question >= 0) {
				queryString = action.substring(question);
			}
			String actionMapping = getActionMappingNameWithoutPrefix(action);
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
		}
		return (value.toString());
	}
	
	public static String getActionMappingNameWithoutPrefix(String action) {
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
		return (value);
	}
	
}

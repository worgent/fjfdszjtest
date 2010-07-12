/**
 * 
 */
package com.apricot.eating;

import java.util.List;

/**
 * @author Administrator
 * 
 */
public final class ApricotApp {

	private ApricotApp() {
	};

	/**
	 * 数组联合成字符串
	 * @param args
	 * @param joinStr
	 * @return
	 */
	public static final String arrayJoin(String[] args, String joinStr) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			if (i < (args.length - 1)) {
				sb.append(joinStr);
			}
		}

		return sb.toString();
	}


}

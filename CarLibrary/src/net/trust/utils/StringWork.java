// Decompiled by DJ v2.9.9.61 Copyright 2000 Atanas Neshkov  Date: 2003-4-14 16:26:28
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   StringWork.java
/**
 * 字符串分割处理类
 */
package net.trust.utils;


import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringWork {
	public static final String BOXOFF2 = new String(new StringBuffer()
			.append((char) 2)); //& 小

	public static final String BOXOFF3 = new String(new StringBuffer()
			.append((char) 3)); //# 大
	private static Log log = LogFactory.getLog(StringWork.class);
	public StringWork() {
	}


	/**
	 * 字符串分割
	 * @param longStr  被分割串
	 * @param strBreak 分割符
	 * @return  字符串类型数组
	 */
	public static String[] breakString(String longStr, String strBreak) {
		String strResult[] = new String[0];
		if (longStr != null) {
			Vector vec = new Vector(5);
			int i;
			while ((i = longStr.indexOf(strBreak)) >= 0) {
				vec.addElement(longStr.substring(0, i));
				longStr = longStr.substring(i + strBreak.length());
			}
			if (longStr.length() != 0) {
				vec.addElement(longStr);
			}
			strResult = new String[vec.size()];
			vec.copyInto(strResult);
		}
		return strResult;
	}

	/**
	 * 把字符串 分割成二维数组
	 * @param str   被分割串
	 * @param strBig 大分割符(第一次分割标识符)
	 * @param strSmall 小分割符(第二次分割标识符)
	 * @return 二维字符串类型数据
	 */
	public static String[][] StringToArray(String str, String strBig,
			String strSmall) {
		String[][] reArr = null;
		try {
			if (str != null && str.length() != 0) {
				String[] arr1 = breakString(str, strBig); //切 #
				if (arr1 != null && arr1.length != 0) {
					int len1 = arr1.length; //算出行数
					String[] arr2 = breakString(arr1[0], strSmall); //算出列
					if (arr2 != null && arr2.length != 0) {
						int len2 = arr2.length; //算出列数
						reArr = new String[len1][len2]; //初始化二维数组
						for (int i = 0; i < len1; i++) {
							String[] arr3 = breakString(arr1[i], strSmall); //切 &
							for (int j = 0; j < len2; j++) {
								reArr[i][j] = arr3[j];
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			log.error(ex);
		}
		return reArr;
	}

	/**
	 * 把字符串 分割成二维数组
	 * @param str 被分割串 ，(默认 # 为大分割符，& 为小分割符)
	 * @return 二维数组
	 */
	public String[][] StringToArray(String str) {
		String[][] reArr = null;
		try {
			reArr = StringToArray(str, BOXOFF3, BOXOFF2); //#  &
		} catch (Exception ex) {
			log.error(ex);
		} finally {

		}
		return reArr;
	}

	/**
	 * 把ArrayList 转化成 String[]
	 * @param arr
	 * @return
	 */
	public String[] ArrayListToStringArray(java.util.ArrayList arr) {
		String[] arr1 = null;
		if (arr != null && arr.size() != 0) {
			arr1 = new String[arr.size()];
			for (int i = 0; i < arr.size(); i++) {
				arr1[i] = (String) arr.get(i);
			}
		}
		return arr1;
	}

}

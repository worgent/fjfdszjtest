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
package com.enation.framework.util;

import java.util.Date;

import org.apache.commons.codec.binary.Base64;

/**
 * 加密工具
 * @author kingapex
 *2010-3-22下午03:41:11
 */
public class EncryptionUtil {
	/**
	 * all set GLOBAL_AUTH_KEY
	 */
	public static String GLOBAL_AUTH_KEY = "e317b362fafa0c96c20b8543d054b850";

	/**
	 * discuz's cookie encode decode in java
	 * 
	 * @param str
	 *            String
	 * @param operation
	 *            String
	 * @param key
	 *            String
	 * @return String
	 */
	public static final String authCode(String str, String operation) {

		String key = GLOBAL_AUTH_KEY;
		String coded = "";
		int keylength = key.length();
		try {
			str = operation == "DECODE" ? new String(Base64.decodeBase64(str
					.getBytes())) : str;
			int tmp;
			byte[] codeList = new byte[str.getBytes().length];
			int index = 0;
			for (int i = 0; i < str.length(); i += keylength) {
				tmp = (i + keylength) < str.length() ? (i + keylength) : str
						.length();
				byte[] codedbyte = phpXor(str.substring(i, tmp), key);
				coded += codedbyte;
				System.arraycopy(codedbyte, 0, codeList, index,
						codedbyte.length);
				index = codedbyte.length;
			}
			coded = operation == "ENCODE" ? new String(Base64.encodeBase64(codeList))
					: new String(codeList);
			return coded;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * php's XOR in Java
	 * 
	 * @param a
	 *            String
	 * @param b
	 *            String
	 * @return String
	 */
	public static final byte[] phpXor(String a, String b) {
		byte[] as;
		byte[] bs;
		try {
			as = a.getBytes();
			bs = b.getBytes();
			int len = 0;
			if (as.length > bs.length) {
				len = bs.length;
			} else {
				len = as.length;
			}
			byte[] cs = new byte[len];
			for (int i = 0; i < len; ++i) {
				cs[i] = (byte) ((int) as[i] ^ (int) bs[i]);
			}
			return cs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * test main
	 * 
	 * @param args
	 *            String[]
	 */

	public static void main(String[] args) {

		String str = authCode("admin:www.ucenter.com:3600456645654654", "ENCODE");
		String str2 = authCode("BFdcXgwJQUURTxMCVQ1NUxEcUw1VDwcFVAABAVQOAQVTBgUBVwc=", "DECODE");

		System.out.println(str);
		System.out.println(str2);
//		System.out.println(new String((Base64.encodeBase64("admin:www.ucenter.com:3600456645654654".getBytes(), true))));
//		System.out.println(new String((Base64.encodeBase64("admin:www.mall.com:3600654654654645645".getBytes(), true))));
//		System.out.println(new String(Base64.encodeBase64("中文".getBytes(), false)));
////		1tC5+g==
////		1tC5+g==
//		System.out.println(new String(Base64.decodeBase64("1tDOxA==".getBytes())));
//		System.out.println(new String(Base64.decodeBase64("1tC5+g==".getBytes())));

		String d1 = "2010-02-01 10 12:00";
		String d2 = "2010-02-01 10 12:01";
		
		Date date1  = DateUtil.toDate(d1, "yyyy-MM-dd HH mm:ss");
		Date date2  = DateUtil.toDate(d2, "yyyy-MM-dd HH mm:ss");
		
		System.out.println(date1.getTime());
		System.out.println(date2.getTime());
	}
}

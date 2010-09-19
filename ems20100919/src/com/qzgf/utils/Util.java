package com.qzgf.utils;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.qzgf.comm.Constant;


public class Util {
	private static MessageDigest digest = null;
	
	/**
	 * MD5加密
	 */
	public synchronized static final String hash(String data) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err
						.println("Failed to load the MD5 MessageDigest. " + "We will be unable to function normally.");
				nsae.printStackTrace();
			}
		}
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	}
	
	public static final String encodeHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		int i;

		for (i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString().toUpperCase();
	}
	
	
	/**
	 *  null转化为string
	 *  摘自厦门忠诚度系统的公共类
	 * @param Object
	 * @return
	 */
	public static String getNulltoStr(Object o) {
		String str = "";
		try{
			if (o != null)
				str = o.toString();
		}catch(Exception e){
			return "";
		}
		return str;
	}
	/**
	 *  判断是否不为空
	 * @param boolean
	 * @return
	 */
	public static boolean bIsEmpty(String str) {
		boolean bisNull = false;
		if (str == null || str == "" || str.trim().equals(""))
			bisNull = true;
		return bisNull;
	}
	/**
	 * 
	 * Purpose      :web路径,存放excel数据.
	 * @param userID
	 * @return
	 */
	public static String getUserWebFilePath(String userID) {
		StringBuffer sb = new StringBuffer();
		int num = Math.abs(userID.hashCode());
		sb.append(Constant.ROOTPATH);
		sb.append("excel/");
		sb.append(num % 100);
		sb.append("/");
		sb.append(userID);
		sb.append("/");
		sb=new StringBuffer(sb.toString());
		File ft = new File(sb.toString());
		if (!ft.exists()) {
			ft.mkdirs();
		}
		return sb.toString();
	}
}

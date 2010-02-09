package com.qzgf.NetStore.test;

import com.qzgf.NetStore.util.MD5Code;

public class TestMD5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MD5Code md5 = new MD5Code() ;
		String str = "lsr" ;
		System.out.println(md5.getMD5ofStr(str));
	}

}

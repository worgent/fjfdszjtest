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
package com.enation.framework.util.ip;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Level;

public class IpUtil {
	      
	    private static StringBuilder sb = new StringBuilder();  
	    /** 
	     * 从ip的字符串形式得到字节数组形式 
	     * @param ip 字符串形式的ip 
	     * @return 字节数组形式的ip 
	     */  
	    public static byte[] getIpByteArrayFromString(String ip) {  
	        byte[] ret = new byte[4];  
	        StringTokenizer st = new StringTokenizer(ip, ".");  
	        try {  
	            ret[0] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);  
	            ret[1] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);  
	            ret[2] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);  
	            ret[3] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);  
	        } catch (Exception e) {
	        	e.printStackTrace();
	            // LogFactory.log("从ip的字符串形式得到字节数组形式报错", Level.ERROR, e);  
	        }  
	        return ret;  
	    }  
	    /** 
	     * @param ip ip的字节数组形式 
	     * @return 字符串形式的ip 
	     */  
	    public static String getIpStringFromBytes(byte[] ip) {  
	        sb.delete(0, sb.length());  
	        sb.append(ip[0] & 0xFF);  
	        sb.append('.');       
	        sb.append(ip[1] & 0xFF);  
	        sb.append('.');       
	        sb.append(ip[2] & 0xFF);  
	        sb.append('.');       
	        sb.append(ip[3] & 0xFF);  
	        return sb.toString();  
	    }  
	      
	    /** 
	     * 根据某种编码方式将字节数组转换成字符串 
	     * @param b 字节数组 
	     * @param offset 要转换的起始位置 
	     * @param len 要转换的长度 
	     * @param encoding 编码方式 
	     * @return 如果encoding不支持，返回一个缺省编码的字符串 
	     */  
	    public static String getString(byte[] b, int offset, int len, String encoding) {  
	        try {  
	            return new String(b, offset, len, encoding);  
	        } catch (UnsupportedEncodingException e) {  
	            return new String(b, offset, len);  
	        }  
	    }  
}

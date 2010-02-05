package com.qzgf.application.gpsdata.service;

import java.util.List;

public interface GetListDatebyBytes {
	/**
	 * 压缩数据传输
	 * @param commandStr
	 * @return
	 */
	public byte[] GetCompress(String commandStr);
	/**
	 * 将数据转化为byte[]
	 * @param commandStr
	 * @return
	 */
	public byte[] GetListToByte(String commandStr);
	/**
	 * 将数据转化为xml
	 * @param commandStr
	 * @return
	 */
	public String GetListToXml(String commandStr);
	/**
	 * 将数据转化为list->xml->byte
	 * @param commandStr
	 * @return
	 */
	public byte[] GetListToXmlToByte(String commandStr);
	
	/**
	 * 动态sql查询结果集
	 * @param commandStr
	 * @return
	 */
	public String DynamicSqlListXml(String commandStr);
	
	/**
	 * 动态sql查询结果集
	 * @param commandStr
	 * @return
	 */
	public byte[] DynamicSqlListXmlToByte(String commandStr);
}
package com.qzgf.application.gpsdata.service;

import java.util.List;

public interface GpsDataService {
	/**
	 * 客户端请求的报文
	 */
	public List ProCommand(String commandStr);
	/**
	 * 动态sql查询结果
	 */	
	public List DynamicCommand(String commandStr);
	
}
package com.qzgf.application.net.communicate.domain;


import java.io.InputStream;
import java.util.HashMap;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/** 
 * 网上下单(与派揽系统之间的通信)
 * 时间:2010-01-05
 */
public interface CommunicateFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**************************************发送信息*********************************/

	/**
	 * 系统不同的请求处理
	 * @param per
	 * @param ls
	 * @return
	 */
	public HashMap ProRequest(String per,HashMap map);	
	
	/**************************************接收信息********************************/
	/**
	 * 2010-01-05
	 * 更新订单单据状态
	 */
	public int modifyBillState(HashMap map);
	
	
	/**
	 * 得到返回的内容,请求URL取得返馈的信息
	 * 参数说明:urlStr:传递的url地址
	 *          per:参数内容前缀,如action="add"
	 *          stream:压缩的二进制流文件
	 * 返回值  :反馈的二进行信息         
	 */
	public HashMap SendCom(String urlStr, String per,byte[] stream);
	
	/**
	 * 将输入流转化为HashMap返回
	 */
	public HashMap StreamToHashMap(InputStream io);
	
	/**
	 * 结果集返回xml处理
	 */
	public String HashMapToXmlStr(HashMap map);
	
	
	public int setOrderById(HashMap map);
	
	public int addsuccessnum(HashMap map);
	
}
package com.qzgf.application.net.communicate.domain;


import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

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
	
	/**
	 * 2010-06-22
	 * Purpose      : 查询福建出入境局信息
	 * @param map
	 * @return
	 */
	public List findImmigration(HashMap map);
	
	/**
	 * 
	 * Purpose      : 更新福建出入境局信息状态
	 * @param map
	 * @return
	 */
	public int modifyImmigration(HashMap map);
	
	/**
	 * 2010-06-22
	 * Purpose      : 移动12580
	 * @param map
	 * @return
	 */
	public List findYd12580(HashMap map);
	
	/**
	 * 
	 * Purpose      : 更新移动12580
	 * @param map
	 * @return
	 */
	public int modifyYd12580(HashMap map);
	
	public int setOrderById(HashMap map);
	
	public int addsuccessnum(HashMap map);
	
}
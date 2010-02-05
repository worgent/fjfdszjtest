package com.qzgf.application.netprint.emsmail.domain;

import java.util.HashMap;
import java.util.List;

/**
 * 租赁合同管理
 * @author lsr
 *
 */
public interface EmsmailFacade {
	
	/**
	 * 查询租赁合同总记录数
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public int findEmsmailCount(HashMap map);
    
    /**
	 * 查询租赁合同信息
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public List findEmsmail(HashMap map);
    
    /**
	 * 修改合同管理信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int updateEmsmail(HashMap map);
	
	/**
	 * 添加合同管理信息
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public int insertEmsmail(HashMap map);
    
    /**
	 * 删除合同管理信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteEmsmail(HashMap map);
	
	/**
	 * 增加客户信息(收件人信息,发件人信息);
	 */	
    @SuppressWarnings("unchecked")
	public int insertClientmsg(HashMap map);
    
    /**
	 * 查询邮件费用区域
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public List procEmsmailFeeArea(String psendaddress);
    
    /**
	 * 查询打印数据集
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public List findEmsmailPrint(HashMap map);
    
    /**
	 * 更新打印
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int updateEmsmailPrint(HashMap map);
	
	
    /**
	 * 查询客户信息
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public List getUserConfig(HashMap map);

}

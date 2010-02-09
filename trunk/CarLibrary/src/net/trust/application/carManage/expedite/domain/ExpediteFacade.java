package net.trust.application.carManage.expedite.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 派车登记
 *
 */
public interface ExpediteFacade {
	/**
	 * 派车信息
	 * @param map
	 * @return
	 */
    public int findExpediteCount(HashMap map);
    
    public List findExpedite(HashMap map);
    
    public HashMap insertExpedite(HashMap map,HashMap roldInfo) throws Exception;
    
	public HashMap updateExpedite(HashMap map,HashMap roldInfo)throws Exception;
	
	public int deleteExpedite(HashMap map) throws Exception;
	
	/**
	 * 查询派车与回车关系
	 * @param map
	 * @return
	 */
	public int findExpediteAndGobackCount(HashMap map);
	/**
	 * 查询派车与回车关系总数
	 * @param map
	 * @return
	 */
	public List findExpediteAndGoback(HashMap map);
	
	/**
	 * 回车信息
	 * @param map
	 * @return
	 */
	public int findGobackCount(HashMap map);
    
    public List findGoback(HashMap map);
    
    public int insertGoBack(HashMap map,HashMap roldInfo) throws Exception;
    
    public int updateGoBack(HashMap map,HashMap roldInfo) throws Exception;
  
	public int deleteGoback(HashMap map);
	
	
	/**
	 * 中转信息
	 * @param map
	 * @return
	 */
    public List findRoldInfo(HashMap map);
    
    public int insertRoldInfo(HashMap map);
    
	public int updateRoldInfo(HashMap map);
	
	public int deleteRoldInfo(HashMap map);
	
	
	/**
	 * 2009-02-25
	 * 派车申请
	 * @param map
	 * @return
	 */
    public int findApplyerCount(HashMap map);
    
    public List findApplyer(HashMap map);
    
    public int insertApplyer(HashMap map) throws Exception;
    
	public int updateApplyer(HashMap map)throws Exception;
	
	public int deleteApplyer(HashMap map) throws Exception;
	/**
	 * 2009-02-26
	 * 派车申请日志
	 * @param map
	 * @return
	 */
    public int findFlowerTaskCount(HashMap map);
    
    public List findFlowerTask(HashMap map);
    
	public int updateFlowerTask(HashMap map)throws Exception;
	/**
	 * 2009-02-28
	 * 审批日志的自动生成 
	 * @param map
	 * @return
	 */
	public void execProFlowerTask(HashMap map);
	/**
	 * 保存申请单，分别为"保存"与"保存提交"的区别。
	 * @param map
	 */
	public int saveApplyer(HashMap map)throws Exception;
	/**
	 * 处理审批，为同意/不同意
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int proAgent(HashMap map) throws Exception;
	
	/**
	 * 客户反馈
	 * @param map
	 * @return
	 */
	public int sendFeedback(HashMap map);
	
	
	
	/**
	 * 2009-04-14
	 * 司机行车登记
	 * @param map
	 * @return
	 */
    public int findDriversmsCount(HashMap map);
    
    public List findDriversms(HashMap map);
    
    public int insertDriversms(HashMap map) throws Exception;
    
	public int updateDriversms(HashMap map)throws Exception;
	
	public int deleteDriversms(HashMap map) throws Exception;
	
	/**
	 * 导出回车信息的excel
	 */
	public int findRoldInfoExcelCount(HashMap map);
    
    public List findRoldInfoExcel(HashMap map);
		
}
